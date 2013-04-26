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
import org.eclipse.jface.viewers.ViewerComparator;

public class TimeZoneViewerComparator extends ViewerComparator {
	@Override
	public int compare(Viewer viewer, Object o1, Object o2) {
		int compare;
		if (o1 instanceof TimeZone && o2 instanceof TimeZone) {
			compare = ((TimeZone) o2).getOffset(System.currentTimeMillis())
					- ((TimeZone) o1).getOffset(System.currentTimeMillis());
		} else {
			compare = o1.toString().compareTo(o2.toString());
		}
		boolean reverse = Boolean.parseBoolean(String.valueOf(viewer
				.getData("REVERSE")));
		return reverse ? -compare : compare;
	}
}
