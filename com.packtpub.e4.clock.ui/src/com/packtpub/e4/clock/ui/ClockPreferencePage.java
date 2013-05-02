/*
 * Copyright (c) 2013, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2013, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ClockPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {
	protected void createFieldEditors() {
		addField(new IntegerFieldEditor("launchCount",
				"Number of times it has been launched", getFieldEditorParent()));
		IntegerFieldEditor offset = new IntegerFieldEditor("offset",
				"Current offset from GMT", getFieldEditorParent());
		offset.setValidRange(-14, +12);
		addField(offset);
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
}