/*
 * Copyright (c) 2013, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2013, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */package com.packtpub.e4.clock.ui.handlers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class ShowTheTime extends AbstractHandler {
	public Object execute(ExecutionEvent event) {
		ISelection sel = HandlerUtil.getActiveWorkbenchWindow(event)
				.getSelectionService().getSelection();
		if (sel instanceof IStructuredSelection && !sel.isEmpty()) {
			Object value = ((IStructuredSelection) sel).getFirstElement();
			if (value instanceof TimeZone) {
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.setTimeZone((TimeZone) value);
				MessageDialog.openInformation(null, "The time is",
						sdf.format(new Date()));
			}
		}
		return null;
	}
}