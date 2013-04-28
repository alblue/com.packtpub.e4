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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

public class TimeZoneSelectionListener implements ISelectionListener {
	private Viewer viewer;
	private IWorkbenchPart part;

	public TimeZoneSelectionListener(Viewer v, IWorkbenchPart p) {
		this.viewer = v;
		this.part = p;
	}

	@Override
	public void selectionChanged(IWorkbenchPart p, ISelection sel) {
		if (p != this.part) {
			Object selected = ((IStructuredSelection) sel).getFirstElement();
			Object current = ((IStructuredSelection) viewer.getSelection())
					.getFirstElement();
			if (selected != current && selected instanceof TimeZone) {
				viewer.setSelection(sel);
				if (viewer instanceof StructuredViewer) {
					((StructuredViewer) viewer).reveal(selected);
				}
			}
		}
	}
}