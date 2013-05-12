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

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

@SuppressWarnings("restriction")
public class Hello {
	private Label label;

	@PostConstruct
	public void create(Composite parent) {
		label = new Label(parent, SWT.NONE);
		label.setText("Hello");
	}

	@Focus
	public void focus() {
		label.setFocus();
	}
}