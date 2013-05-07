/*
 * Copyright (c) 2013, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2013, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.application.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.packtpub.e4.application.IStringService;

@SuppressWarnings("restriction")
public class Rainbow {
	private static final Object[] rainbow = { "Red", "Orange", "Yellow",
			"Green", "Blue", "Indigo", "Violet" };
	@Inject
	private ESelectionService selectionService;
	@Inject
	private IEventBroker broker;
	@Inject
	private IStringService stringService;
	
	@PostConstruct
	private void create(Composite parent) {
		ListViewer lv = new ListViewer(parent, SWT.NONE);
		lv.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// selectionService.setSelection(event.getSelection());
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				Object colour = sel.getFirstElement();
				broker.post("rainbow/colour",stringService.process(colour.toString()));
			}
		});
		lv.setContentProvider(new ArrayContentProvider());
		lv.setInput(rainbow);
	}
}
