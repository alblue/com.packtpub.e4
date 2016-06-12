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

import java.util.Dictionary;
import java.util.Map;
import java.util.TreeMap;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;

public class EchoServiceFactory implements ManagedServiceFactory {
	private Map<String, EchoServer> echoServers = new TreeMap<String, EchoServer>();

	public String getName() {
		return "Echo service factory";
	}

	public void updated(String pid, Dictionary<String, ?> properties) throws ConfigurationException {
		if (properties != null) {
			String portString = properties.get("port").toString();
			try {
				int port = Integer.parseInt(portString);
				System.out.println("Creating echo server on port " + port);
				echoServers.put(pid, new EchoServer(port));
			} catch (Exception e) {
				throw new ConfigurationException("port", "Cannot create a server on port " + portString, e);
			}
		} else if (echoServers.containsKey(pid)) {
			deleted(pid);
		}
	}

	public void deleted(String pid) {
		System.out.println("Removing echo server with pid " + pid);
		EchoServer removed = echoServers.remove(pid);
		if (removed != null) {
			removed.stop();
		}
	}
}