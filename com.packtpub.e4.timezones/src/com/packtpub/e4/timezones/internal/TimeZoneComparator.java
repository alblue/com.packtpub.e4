/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.timezones.internal;

import java.time.ZoneId;
import java.util.Comparator;

public class TimeZoneComparator implements Comparator<ZoneId> {
	public int compare(ZoneId o1, ZoneId o2) {
		return o1.getId().compareTo(o2.getId());
	}
}