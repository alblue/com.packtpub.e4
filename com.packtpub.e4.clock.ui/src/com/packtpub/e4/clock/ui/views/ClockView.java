/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.packtpub.e4.clock.ui.ClockWidget;

public class ClockView extends ViewPart {
	public void createPartControl(Composite parent) {
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		parent.setLayout(layout);
		final ClockWidget clock1 = new ClockWidget(parent, SWT.NONE);
		final ClockWidget clock2 = new ClockWidget(parent, SWT.NONE);
		final ClockWidget clock3 = new ClockWidget(parent, SWT.NONE);
		clock1.setLayoutData(new RowData(20, 20));
		clock2.setLayoutData(new RowData(50, 50));
		clock3.setLayoutData(new RowData(100, 100));
	}

	public void setFocus() {
	}
}
