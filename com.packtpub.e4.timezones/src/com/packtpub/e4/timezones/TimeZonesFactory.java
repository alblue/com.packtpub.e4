/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.timezones;

import java.util.function.Consumer;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class TimeZonesFactory {
	private static final Bundle bundle = FrameworkUtil.getBundle(TimeZonesService.class);
	private static final BundleContext context = bundle.getBundleContext();
	private static final ServiceReference<TimeZonesService> sr = context.getServiceReference(TimeZonesService.class);

	public static void use(Consumer<TimeZonesService> consumer) {
		TimeZonesService service = context.getService(sr);
		try {
			consumer.accept(service);
		} finally {
			context.ungetService(sr);
		}
	}
}