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

public class TimeZoneSummerTimeColumn extends TimeZoneColumn {
	public String getText(Object element) {
		if (element instanceof TimeZone) {
			return ((TimeZone) element).useDaylightTime() + "";
		} else {
			return "";
		}
	}

	public String getTitle() {
		return "Summer Time";
	}

	@Override
	public int getWidth() {
		return 50;
	}
}