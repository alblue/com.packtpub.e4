/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui.handlers;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.jface.dialogs.MessageDialog;

public class HelloHandler {
	@Execute
	public void execute(final UISynchronize display) {
		Job job = new Job("About to say hello") {
			protected IStatus run(IProgressMonitor monitor) {
				try {
					monitor.beginTask("Preparing", 5000);
					for (int i = 0; i < 50; i++) {
						Thread.sleep(100);
						monitor.worked(100);
					}
				} catch (InterruptedException e) {
				} finally {
					monitor.done();
				}
				display.asyncExec(() -> {
					MessageDialog.openInformation(null, "Hello", "World");
				});
				return Status.OK_STATUS;
			}
		};
		job.schedule();
		return;
	}
}