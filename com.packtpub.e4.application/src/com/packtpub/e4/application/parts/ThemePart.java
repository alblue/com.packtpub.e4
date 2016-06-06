/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.application.parts;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.css.swt.theme.ITheme;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

@SuppressWarnings("restriction")
public class ThemePart {
	@Inject
	private IThemeEngine themeEngine;

	@PostConstruct
	public void create(Composite parent) {
		ListViewer lv = new ListViewer(parent, SWT.NONE);
		lv.setContentProvider(new ArrayContentProvider());
		lv.addSelectionChangedListener(event -> {
			IStructuredSelection sel = (IStructuredSelection) event.getSelection();
			if (sel instanceof IStructuredSelection && !sel.isEmpty()) {
				Object selectedElement = ((IStructuredSelection) sel).getFirstElement();
				for (ITheme theme : themeEngine.getThemes()) {
					if (selectedElement.equals(theme.getLabel())) {
						themeEngine.setTheme(theme, false);
					}
				}
			}
		});
		lv.setInput(themeEngine.getThemes().stream().map(ITheme::getLabel).collect(Collectors.toList()));
	}
}