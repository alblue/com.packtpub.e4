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

import java.time.Instant;
import java.time.ZoneId;

import org.eclipse.swt.SWT;

public class TimeZoneSummerTimeColumn extends TimeZoneColumn {
	public String getText(Object element) {
		if (element instanceof ZoneId) {
			return String.valueOf(!((ZoneId) element).getRules().getDaylightSavings(Instant.now()).isZero());
		} else {
			return "";
		}
	}

	public String getTitle() {
		return "Summer Time";
	}

	public int getWidth() {
		return 100;
	}

	public int getAlignment() {
		return SWT.RIGHT;
	}
}