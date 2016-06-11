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

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.packtpub.e4.timezones.TimeZonesService;

public class TimeZonesActivator implements BundleActivator {
	public void start(BundleContext context) throws Exception {
		context.registerService(TimeZonesService.class, new TimeZonesProvider(), null);
	}

	public void stop(BundleContext context) throws Exception {
	}
}
