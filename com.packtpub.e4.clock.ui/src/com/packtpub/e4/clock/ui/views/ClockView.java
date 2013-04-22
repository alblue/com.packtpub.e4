/*
 * Copyright (c) 2013, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2013, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui.views;

import java.util.TimeZone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.packtpub.e4.clock.ui.ClockWidget;

/**
 * Provides a ClockView to show clocks
 */
public class ClockView extends ViewPart {
	private Combo timezones;

	/**
	 * Called by Eclipse 3.x to populate the view
	 */
	@SuppressWarnings("unused")
	public void createPartControl(Composite parent) {
		Object[] oo = parent.getDisplay().getDeviceData().objects;
		int c = 0;
		for (int i = 0; i < oo.length; i++)
			if (oo[i] instanceof Color)
				c++;
		System.err.println("There are " + c + " Color instances");
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		parent.setLayout(layout);
		final ClockWidget clock1 = new ClockWidget(parent, SWT.NONE, new RGB(
				255, 0, 0));
		final ClockWidget clock2 = new ClockWidget(parent, SWT.NONE, new RGB(0,
				255, 0));
		final ClockWidget clock3 = new ClockWidget(parent, SWT.NONE, new RGB(0,
				0, 255));
		clock1.setLayoutData(new RowData(20, 20));
		clock3.setLayoutData(new RowData(100, 100));
		String[] ids = TimeZone.getAvailableIDs();
		timezones = new Combo(parent, SWT.SIMPLE);
		timezones.setVisibleItemCount(5);
		for (int i = 0; i < ids.length; i++) {
			timezones.add(ids[i]);
		}
		timezones.addSelectionListener(new SelectionListener() {
			  public void widgetSelected(SelectionEvent e) {
			    String z = timezones.getText();
			    TimeZone tz = z == null ? null : TimeZone.getTimeZone(z);
			    TimeZone dt = TimeZone.getDefault();
			    int offset = tz == null ? 0 : (
			      tz.getOffset(System.currentTimeMillis()) - 
			      dt.getOffset(System.currentTimeMillis())) / 3600000;
			    clock3.setOffset(offset);
			    clock3.redraw();
			  }
			  public void widgetDefaultSelected(SelectionEvent e) {
			    clock3.setOffset(0);
			    clock3.redraw();
			  }
			});
	}

	/**
	 * Called by Eclipse 3.x to notify the view has focus
	 */
	public void setFocus() {
		timezones.setFocus();
	}
}
