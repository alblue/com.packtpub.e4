/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.junit.plugin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.hamcrest.Matcher;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class UITest {
	private static SWTWorkbenchBot bot;

	@BeforeClass
	public static void beforeClass() {
		bot = new SWTWorkbenchBot();
		try {
			bot.viewByTitle("Welcome").close();
		} catch (WidgetNotFoundException e) {
			// ignore
		}
	}

	@Test
	public void testUI() {
		SWTBotShell[] shells = bot.shells();
		boolean found = false;
		for (int i = 0; i < shells.length && !found; i++) {
			if (shells[i].isVisible()) {
				String text = shells[i].getText();
				if (text.contains("Eclipse") || text.contains("Resource") || text.contains("Java")) {
					found = true;
				}
			}
		}
		assertTrue(found);
	}

	@Test
	public void createProject() {
		bot.menu("File").menu("Project...").click();
		SWTBotShell shell = bot.shell("New Project");
		shell.activate();
		bot.tree().expandNode("General").select("Project");
		bot.button("Next >").click();
		bot.textWithLabel("Project name:").setText("SWTBot Test Project");
		bot.button("Finish").click();
	}

	@Test
	public void testTimeZoneView() {
		bot.menu("Window").menu("Show View").menu("Other...").click();
		SWTBotShell shell = bot.shell("Show View");
		shell.activate();
		bot.tree().expandNode("Timekeeping").select("Time Zone View");
		bot.button("OK").click();
		SWTBotView timeZoneView = bot.viewByTitle("Time Zone View");
		assertNotNull(timeZoneView);
		Widget widget = timeZoneView.getWidget();
		Matcher<CTabItem> matcher = WidgetMatcherFactory.widgetOfType(CTabItem.class);
		List<? extends CTabItem> ctabs = bot.widgets(matcher, widget);
		Set<String> regions = ZoneId.getAvailableZoneIds().stream().filter(s -> s.contains("/"))
				.map(s -> s.split("/")[0]).collect(Collectors.toSet());
		assertEquals(regions.size(), ctabs.size());
		String tabText = UIThreadRunnable.syncExec(() -> ctabs.get(0).getText());
		assertEquals("Africa", tabText);
	}

	@Test
	public void createJavaProject() throws CoreException, UnsupportedEncodingException {
		bot.menu("File").menu("Project...").click();
		SWTBotShell shell = bot.shell("New Project");
		shell.activate();
		bot.tree().expandNode("Java").select("Java Project");
		bot.button("Next >").click();
		String projectName = "SWTBot Java Project";
		bot.textWithLabel("Project name:").setText(projectName);
		bot.button("Finish").click();
		IProject project = getProject(projectName);
		assertTrue(project.exists());
		IFolder src = project.getFolder("src");
		IFolder bin = project.getFolder("bin");
		if (!src.exists()) {
			src.create(true, true, null);
		}
		IFile test_java = src.getFile("Test.java");
		test_java.create(new ByteArrayInputStream("class Test{}".getBytes()), true, null);
		try {
			bot.shell("Open Associated Perspective?");
			bot.button("Yes").click();
		} catch (WidgetNotFoundException e) {
			// ignore
		}
		bot.waitUntil(new DefaultCondition() {
			public boolean test() throws Exception {
				return bin.getFile("Test.class").exists();
			}

			public String getFailureMessage() {
				return "File bin/Test.class does not exist";
			}
		});
		assertTrue(bin.getFile("Test.class").exists());
	}

	private IProject getProject(String projectName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}
}
