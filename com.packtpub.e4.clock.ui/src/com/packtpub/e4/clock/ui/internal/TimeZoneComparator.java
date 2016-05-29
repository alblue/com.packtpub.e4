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
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TimeZoneComparator implements Comparator<ZoneId> {
	public int compare(ZoneId o1, ZoneId o2) {
		return o1.getId().compareTo(o2.getId());
	}

	public static Map<String, Set<ZoneId>> getTimeZones() {
		Supplier<Set<ZoneId>> sortedZones = () -> new TreeSet<>(new TimeZoneComparator());
		return ZoneId.getAvailableZoneIds().stream() // stream
				.filter(s -> s.contains("/")) // with / in them
				.map(ZoneId::of) // convert to ZoneId
				.collect(Collectors.groupingBy( // and group by
						z -> z.getId().split("/")[0], // first part
						TreeMap::new, Collectors.toCollection(sortedZones)));
	}
}