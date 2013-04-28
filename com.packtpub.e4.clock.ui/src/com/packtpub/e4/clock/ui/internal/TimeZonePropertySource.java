/*
 * Copyright (c) 2013, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2013, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui.internal;

import java.util.Date;
import java.util.TimeZone;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class TimeZonePropertySource implements IPropertySource {
	private TimeZone timeZone;

	public TimeZonePropertySource(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	private static final Object ID = new Object();
	private static final Object DAYLIGHT = new Object();
	private static final Object NAME = new Object();

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] {
				new PropertyDescriptor(ID, "Time Zone"),
				new PropertyDescriptor(DAYLIGHT, "Daylight Savings"),
				new PropertyDescriptor(NAME, "Name") };
	}

	public Object getPropertyValue(Object id) {
		if (ID.equals(id)) {
			return timeZone.getID();
		} else if (DAYLIGHT.equals(id)) {
			return timeZone.inDaylightTime(new Date());
		} else if (NAME.equals(id)) {
			return timeZone.getDisplayName();
		} else {
			return null;
		}
	}

	@Override
	public Object getEditableValue() {
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
	}
}