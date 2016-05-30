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

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;

public class ShowTheTime {
	@Execute
	public void execute(ESelectionService selectionService) {
		Object selection = selectionService.getSelection();
		if (selection instanceof ZoneId) {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
			String theTime = ZonedDateTime.now((ZoneId) selection).format(formatter);
			MessageDialog.openInformation(null, "The time is", theTime);
		}
	}
}
