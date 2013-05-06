/*
 * Copyright (c) 2013, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2013, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.application.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.swt.modeling.EMenuService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.osgi.service.log.LogService;
import org.osgi.service.prefs.BackingStoreException;

@SuppressWarnings("restriction")
public class Hello {
	private Label label;
	private Button button;
	@Inject
	private MWindow window;
	@Optional
	@Inject
	private LogService log;
	@Inject
	@Named("math.random")
	private Object random;
	@Inject
	@Preference(nodePath = "com.packtpub.e4.application", value = "greeting")
	private String greeting;
	@Inject
	@Preference(nodePath = "com.packtpub.e4.application")
	private IEclipsePreferences prefs;
	@Inject
	private UISynchronize ui;

	@PostConstruct
	public void create(Composite parent, EMenuService menu) {
		menu.registerContextMenu(parent, "com.packtpub.e4.application.popupmenu.hello");
		button = new Button(parent, SWT.PUSH);
		button.setText("Do not push");
		button.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				button.setEnabled(false);
				new Job("Button Pusher") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						ui.asyncExec(new Runnable() {
							@Override
							public void run() {
								button.setEnabled(true);
							}
						});
						return Status.OK_STATUS;
					}
				}.schedule(1000);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		label = new Label(parent, SWT.NONE);
		label.setText(greeting + " " + window.getLabel() + " " + random);
		if (log != null) {
			log.log(LogService.LOG_ERROR, "Hello");
		}
	}

	@Focus
	public void focus() {
		label.setFocus();
	}

	@Inject
	@Optional
	public void setSelection(
			@Named(IServiceConstants.ACTIVE_SELECTION) Object selection) {
		if (selection != null) {
			label.setText(selection.toString());
		}
	}

	@Inject
	@Optional
	public void receiveEvent(@UIEventTopic("rainbow/colour") String data)
			throws BackingStoreException {
		// This needs to use @UIEventTopic
		// If @EventTopic is used it will not run on the UI thread
		label.setText(data);
		prefs.put("greeting", "I like " + data);
		prefs.sync();
	}

	@Inject
	@Optional
	void setText(
			@Preference(nodePath = "com.packtpub.e4.application", value = "greeting") String text) {
		if (text != null && label != null && !label.isDisposed()) {
			// NB Run in UI thread!
			label.setText(text);
		}
	}
}