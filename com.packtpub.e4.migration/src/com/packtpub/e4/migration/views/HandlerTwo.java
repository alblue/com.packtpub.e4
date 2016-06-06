/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.migration.views;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.action.Action;

public final class HandlerTwo extends Action {
	@Execute
	public void run() {
		SampleView.showMessage("Action 2 executed");
	}
}