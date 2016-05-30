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

import org.eclipse.jface.viewers.LabelProvider;

public class TimeZoneLabelProvider extends LabelProvider {
	@SuppressWarnings("rawtypes")
	public String getText(Object element) {
		if (element instanceof Map) {
			return "Time Zones";
		} else if (element instanceof Map.Entry) {
			return ((Map.Entry) element).getKey().toString();
		} else if (element instanceof ZoneId) {
			return ((ZoneId) element).getId().split("/")[1];
		} else {
			return "Unknown type: " + element.getClass();
		}
	}

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Object[]) {
			return (Object[]) inputElement;
		} else {
			return new Object[0];
		}
	}
}