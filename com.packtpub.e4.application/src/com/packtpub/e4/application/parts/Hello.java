/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.application.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.osgi.service.log.LogService;

public class Hello {
	private Label label;
	@Inject
	private LogService logService;
	@Inject
	private MWindow window;
	@Inject
	@Named("math.random")
	private Object random;
	private Button button;
	@Inject
	private UISynchronize ui;

	@PostConstruct
	public void create(Composite parent, EMenuService menu) {
		if (!menu.registerContextMenu(parent, "com.packtpub.e4.application.popupmenu.hello")) {
			logService.log(LogService.LOG_ERROR, "Failed to register pop-up menu");
		}
		button = new Button(parent, SWT.PUSH);
		button.setText("Do not push");
		button.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				button.setEnabled(false);
				new Job("Button Pusher") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						ui.asyncExec(() -> button.setEnabled(true));
						return Status.OK_STATUS;
					}
				}.schedule(1000);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		label = new Label(parent, SWT.NONE);
		label.setText(window.getLabel() + " " + random);
		logService.log(LogService.LOG_ERROR, "Hello");
		Label label = new Label(parent, SWT.NONE);
		label.setText("Danger Will Robinson!");
		label.setData("org.eclipse.e4.ui.css.id", "DireWarningMessage");
	}

	@Focus
	public void onFocus() {
		label.setFocus();
	}

	@Inject
	@Optional
	public void setSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object selection) {
		if (selection != null) {
			label.setText(selection.toString());
		}
	}

	@Inject
	@Optional
	public void receiveEvent(@UIEventTopic("rainbow/colour") String data) {
		label.setText(data);
	}
}