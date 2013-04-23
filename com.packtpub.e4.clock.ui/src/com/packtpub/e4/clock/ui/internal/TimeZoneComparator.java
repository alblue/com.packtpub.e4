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

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;

@SuppressWarnings("rawtypes")
public class TimeZoneComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		if (o1 instanceof TimeZone && o2 instanceof TimeZone) {
			return ((TimeZone) o1).getID().compareTo(((TimeZone) o2).getID());
		} else {
			throw new IllegalArgumentException();
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Set<TimeZone>> getTimeZones() {
		String[] ids = TimeZone.getAvailableIDs();
		Map<String, Set<TimeZone>> timeZones = new TreeMap<String, Set<TimeZone>>();
		for (int i = 0; i < ids.length; i++) {
			String[] parts = ids[i].split("/");
			if (parts.length == 2) {
				String region = parts[0];
				Set<TimeZone> zones = timeZones.get(region);
				if (zones == null) {
					zones = new TreeSet<TimeZone>(new TimeZoneComparator());
					timeZones.put(region, zones);
				}
				TimeZone timeZone = TimeZone.getTimeZone(ids[i]);
				zones.add(timeZone);
			}
		}
		return timeZones;
	}
}
