/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui.views;

import java.time.ZoneId;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.packtpub.e4.clock.ui.internal.TimeZoneIDColumn;

public class TimeZoneTableView {
	private TableViewer tableViewer;

	@PostConstruct
	public void create(Composite parent) {
		tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		new TimeZoneIDColumn().addColumnTo(tableViewer);
		tableViewer.setInput(ZoneId.getAvailableZoneIds().stream().map(ZoneId::of).toArray());
	}

	@Focus
	public void focus() {
		tableViewer.getControl().setFocus();
	}
}