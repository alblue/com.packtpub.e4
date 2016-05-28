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
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ClockView extends ViewPart {
	public void createPartControl(Composite parent) {
		final Canvas clock = new Canvas(parent, SWT.NONE);
		clock.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.drawArc(e.x, e.y, e.width - 1, e.height - 1, 0, 360);
			}
		});
	}

	public void setFocus() {
	}
}
