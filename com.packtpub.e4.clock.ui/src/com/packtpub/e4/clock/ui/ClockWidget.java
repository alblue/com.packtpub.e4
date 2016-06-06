/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class ClockWidget extends Canvas {
	private final Color color;
	private ZoneId zone = ZoneId.systemDefault();

	public ClockWidget(Composite parent, int style, RGB rgb) {
		super(parent, style);
		this.color = new Color(parent.getDisplay(), rgb);
		addDisposeListener(e -> color.dispose());
		addPaintListener(this::drawClock);
		Runnable redraw = () -> {
			while (!this.isDisposed()) {
				this.getDisplay().asyncExec(() -> this.redraw());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					return;
				}

			}
		};
		new Thread(redraw, "TickTock").start();
	}

	private void drawClock(PaintEvent e) {
		e.gc.drawArc(e.x, e.y, e.width - 1, e.height - 1, 0, 360);
		ZonedDateTime now = ZonedDateTime.now(zone);
		int seconds = now.getSecond();
		int arc = (15 - seconds) * 6 % 360;
		if (handColor == null) {
			e.gc.setBackground(color);
		} else {
			e.gc.setBackground(handColor);
		}
		e.gc.fillArc(e.x, e.y, e.width - 1, e.height - 1, arc - 1, 2);
		e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
		int hours = now.getHour();
		arc = (3 - hours) * 30 % 360;
		e.gc.fillArc(e.x, e.y, e.width - 1, e.height - 1, arc - 5, 10);
	}

	public Point computeSize(int w, int h, boolean changed) {
		int size;
		if (w == SWT.DEFAULT) {
			size = h;
		} else if (h == SWT.DEFAULT) {
			size = w;
		} else {
			size = Math.min(w, h);
		}
		if (size == SWT.DEFAULT) {
			size = 50;
		}
		return new Point(size, size);
	}

	public void setZone(ZoneId zone) {
		this.zone = zone;
	}

	// This does not work - add a dispose listener instead
	// @Override
	// public void dispose() {
	// if (color != null && !color.isDisposed())
	// color.dispose();
	// super.dispose();
	// }

	private Color handColor;

	public Color getHandColor() {
		if (handColor == null) {
			return color;
		} else {
			return handColor;
		}
	}

	public void setHandColor(Color color) {
		handColor = color;
	}
}
