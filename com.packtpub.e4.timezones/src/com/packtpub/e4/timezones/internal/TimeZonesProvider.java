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
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.log.LogService;

import com.packtpub.e4.timezones.TimeZonesService;

@Component(name = "TimeZonesProvider2", service = { TimeZonesService.class }, property = { "service.ranking:Integer=1" })
public class TimeZonesProvider implements TimeZonesService {
	public Map<String, Set<ZoneId>> getTimeZones() {
		Supplier<Set<ZoneId>> sortedZones = () -> new TreeSet<>(new TimeZoneComparator());
		Map<String, Set<ZoneId>> timeZones = ZoneId.getAvailableZoneIds().stream() // stream
				.filter(s -> s.contains("/")) // with / in them
				.limit(max) // return this many only
				.map(ZoneId::of) // convert to ZoneId
				.collect(Collectors.groupingBy( // and group by
						z -> z.getId().split("/")[0], // first part
						TreeMap::new, Collectors.toCollection(sortedZones)));
		if (logService != null) {
			logService.log(LogService.LOG_INFO, "Time zones loaded with " + timeZones.size());
		}
		return timeZones;
	}

	private LogService logService;

	public void setLog(LogService logService) {
		this.logService = logService;
	}

	public void unsetLog(LogService logService) {
		this.logService = null;
	}

	private long max = Long.MAX_VALUE;

	public void configure(Map<String, Object> properties) {
		max = Long.MAX_VALUE;
		if (properties != null) {
			String maxStr = (String) properties.get("max");
			if (maxStr != null) {
				max = Long.parseLong(maxStr);
			}
		}
	}
}