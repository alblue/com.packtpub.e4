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

import java.time.ZoneId;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.part.ViewPart;

import com.packtpub.e4.clock.ui.ClockWidget;
import com.packtpub.e4.clock.ui.internal.TimeZoneComparator;

public class TimeZoneView extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
		Map<String, Set<ZoneId>> timeZones = TimeZoneComparator.getTimeZones();
		CTabFolder tabs = new CTabFolder(parent, SWT.BOTTOM);
		timeZones.forEach((region, zones) -> {
			CTabItem item = new CTabItem(tabs, SWT.NONE);
			item.setText(region);
			ScrolledComposite scrolled = new ScrolledComposite(tabs, SWT.H_SCROLL | SWT.V_SCROLL);
			Composite clocks = new Composite(scrolled, SWT.NONE);
			clocks.setBackground(clocks.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
			item.setControl(scrolled);
			scrolled.setContent(clocks);
			clocks.setLayout(new RowLayout());
			RGB rgb = new RGB(128, 128, 128);
			zones.forEach(zone -> {
				Group group = new Group(clocks, SWT.SHADOW_ETCHED_IN);
				group.setText(zone.getId().split("/")[1]);
				group.setLayout(new FillLayout());
				new ClockWidget(group, SWT.NONE, rgb).setZone(zone);
			});
			Point size = clocks.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			scrolled.setMinSize(size);
			scrolled.setExpandHorizontal(true);
			scrolled.setExpandVertical(true);
		});
		tabs.setSelection(0);
	}

	@Override
	public void setFocus() {
	}

}
