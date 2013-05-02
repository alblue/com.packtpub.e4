/*
 * Copyright (c) 2013, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2013, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.progress.IProgressConstants2;
import org.eclipse.ui.statushandlers.StatusManager;

import com.packtpub.e4.clock.ui.Activator;

public class HelloHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) {
		Job job = new Job("About to say hello") {
			protected IStatus run(IProgressMonitor monitor) {
				try {
					SubMonitor subMonitor = SubMonitor.convert(monitor,
							"Preparing", 5000);
					// how to trigger the null pointer exception 'bug'
					// subMonitor = null; // the bug
					subMonitor.beginTask("Preparing", 5000);
					for (int i = 0; i < 50 && !subMonitor.isCanceled(); i++) {
						if (i == 10) {
							subMonitor.subTask("Doing something");
						} else if (i == 12) {
							checkDozen(new SubProgressMonitor(subMonitor, 100));
						} else if (i == 25) {
							subMonitor.subTask("Doing something else");
						} else if (i == 40) {
							subMonitor.subTask("Nearly there");
						}
						Thread.sleep(100);
						subMonitor.worked(100);
					}
				} catch (InterruptedException e) {
				} catch (NullPointerException e) {
					// Returning a Status lets Eclipse report the error
					// return new Status(IStatus.ERROR, Activator.PLUGIN_ID,
					// "Programming bug?", e);
					// Alternatively the error can be reported/shown manually
					StatusManager statusManager = StatusManager.getManager();
					Status status = new Status(IStatus.ERROR,
							Activator.PLUGIN_ID, "Programming bug?", e);
					statusManager.handle(status, StatusManager.LOG
							| StatusManager.SHOW);

				} finally {
					monitor.done();
				}
				if (!monitor.isCanceled()) {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							MessageDialog.openInformation(null, "Hello",
									"World");
						}
					});
				}
				return Status.OK_STATUS;
			}

			private void checkDozen(IProgressMonitor monitor) {
				try {
					if (monitor == null)
						monitor = new NullProgressMonitor();
					monitor.beginTask("Checking a dozen", 12);
					for (int i = 0; i < 12; i++) {
						Thread.sleep(10);
						monitor.worked(1);
					}
				} catch (InterruptedException e) {
				} finally {
					monitor.done();
				}
			}
		};
		ICommandService service = (ICommandService) PlatformUI.getWorkbench()
				.getService(ICommandService.class);
		Command command = service == null ? null : service
				.getCommand("com.packtpub.e4.clock.ui.command.hello");
		if (command != null) {
			job.setProperty(IProgressConstants2.COMMAND_PROPERTY, command);
			job.setProperty(IProgressConstants2.COMMAND_PROPERTY,
					ParameterizedCommand.generateCommand(command, null));
		}
		job.setProperty(IProgressConstants2.ICON_PROPERTY, ImageDescriptor
				.createFromURL(HelloHandler.class
						.getResource("/icons/sample.gif")));
		job.schedule();
		return null;
	}
}