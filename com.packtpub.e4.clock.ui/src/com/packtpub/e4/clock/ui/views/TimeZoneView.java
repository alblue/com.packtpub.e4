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

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.packtpub.e4.clock.ui.internal.TimeZoneComparator;

public class TimeZoneView extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
		  Map<String, Set<TimeZone>> timeZones = TimeZoneComparator.getTimeZones();
		  CTabFolder tabs = new CTabFolder(parent, SWT.BOTTOM);
		  Iterator<Entry<String, Set<TimeZone>>> regionIterator =
		   timeZones.entrySet().iterator();
		  while (regionIterator.hasNext()) {
		    Entry<String, Set<TimeZone>> region =
		     regionIterator.next();
		    CTabItem item = new CTabItem(tabs, SWT.NONE);
		    item.setText(region.getKey());
		  }
		  tabs.setSelection(0);
		}
	@Override
	public void setFocus() {
	}

}
