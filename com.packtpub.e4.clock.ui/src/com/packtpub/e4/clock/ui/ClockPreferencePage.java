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

import java.util.Arrays;
import java.util.TimeZone;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.PathEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.ScaleFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ClockPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {
	public ClockPreferencePage() {
		super(GRID);
	}

	protected void createFieldEditors() {
		addField(new IntegerFieldEditor("launchCount",
				"Number of times it has been launched", getFieldEditorParent()));
		IntegerFieldEditor offset = new IntegerFieldEditor("offset",
				"Current offset from GMT", getFieldEditorParent());
		offset.setValidRange(-14, +12);
		addField(offset);
		String[][] data;
		String[] ids = TimeZone.getAvailableIDs();
		Arrays.sort(ids);
		data = new String[ids.length][];
		for (int i = 0; i < ids.length; i++) {
			data[i] = new String[] { ids[i], ids[i] };
		}
		addField(new ComboFieldEditor("favourite", "Favourite time zone", data,
				getFieldEditorParent()));
		addField(new BooleanFieldEditor("tick", "Boolean value",
				getFieldEditorParent()));
		addField(new ColorFieldEditor("colour", "Favourite colour",
				getFieldEditorParent()));
		addField(new ScaleFieldEditor("scale", "Scale", getFieldEditorParent(),
				0, 360, 10, 90));
		addField(new FileFieldEditor("file", "Pick a file",
				getFieldEditorParent()));
		addField(new DirectoryFieldEditor("dir", "Pick a directory",
				getFieldEditorParent()));
		addField(new PathEditor("path", "Path", "Directory",
				getFieldEditorParent()));
		addField(new RadioGroupFieldEditor("group", "Radio choices", 3, data,
				getFieldEditorParent(), true));
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
}