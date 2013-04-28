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

import java.util.TimeZone;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

@SuppressWarnings("rawtypes")
public class TimeZoneAdapterFactory implements IAdapterFactory {
	public Class[] getAdapterList() {
		return new Class[] { IPropertySource.class };
	}

	public Object getAdapter(Object object, Class type) {
		if (type == IPropertySource.class && object instanceof TimeZone) {
			return new TimeZonePropertySource((TimeZone) object);
		} else {
			return null;
		}
	}

}