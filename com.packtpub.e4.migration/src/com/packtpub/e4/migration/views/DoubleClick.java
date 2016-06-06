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

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;

public final class DoubleClick extends Action {
	@Execute
	public void run(@Named("selection") IStructuredSelection selection) {
		Object obj = ((IStructuredSelection) selection).getFirstElement();
		SampleView.showMessage("Double-click detected on " + obj.toString());
	}
}