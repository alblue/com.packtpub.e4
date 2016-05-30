/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui.internal;

import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

public class TimeZoneDisplayNameColumn extends TimeZoneColumn {
	public String getText(Object element) {
		if (element instanceof ZoneId) {
			return ((ZoneId) element).getDisplayName(TextStyle.FULL, Locale.getDefault());
		} else {
			return "";
		}
	}

	public String getTitle() {
		return "Display Name";
	}
}