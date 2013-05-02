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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;

public class EclipsePreferencesScope implements IScopeContext {
	private IEclipsePreferences preferences;

	public EclipsePreferencesScope(IEclipsePreferences preferences) {
		this.preferences = preferences;
	}

	public String getName() {
		return "";
	}

	public IPath getLocation() {
		return new Path(preferences.absolutePath());
	}

	public IEclipsePreferences getNode(String qualifier) {
		return preferences;
	}
}