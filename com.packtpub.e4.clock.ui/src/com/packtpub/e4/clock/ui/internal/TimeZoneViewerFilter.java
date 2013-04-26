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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class TimeZoneViewerFilter extends ViewerFilter {
	private String pattern;

	public TimeZoneViewerFilter(String pattern) {
		this.pattern = pattern;
	}

	public boolean select(Viewer v, Object parent, Object element) {
		if (element instanceof TimeZone) {
			TimeZone zone = (TimeZone) element;
			return zone.getDisplayName().contains(pattern);
		} else {
			return true;
		}
	}
}