/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.application.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class HelloHandler {
	@Execute
	public void hello(@Named(IServiceConstants.ACTIVE_SHELL) Shell s,
			@Optional @Named("com.packtpub.e4.application.commandparameter.hello.value") String hello,
			@Named("math.random") double value) {
		MessageDialog.openInformation(s, "Hello World ", hello + value);
	}
}