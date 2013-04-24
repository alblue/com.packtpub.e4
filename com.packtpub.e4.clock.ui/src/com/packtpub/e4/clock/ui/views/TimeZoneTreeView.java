/*
 * Copyright (c) 2013, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2013, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.packtpub.e4.clock.ui.internal.TimeZoneComparator;

public class TimeZoneTreeView extends ViewPart {
	private TreeViewer treeViewer;

	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		treeViewer.setLabelProvider(new TimeZoneLabelProvider());
		treeViewer.setContentProvider(new TimeZoneContentProvider());
		treeViewer.setInput(new Object[] { TimeZoneComparator.getTimeZones() });
	}

	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

}
