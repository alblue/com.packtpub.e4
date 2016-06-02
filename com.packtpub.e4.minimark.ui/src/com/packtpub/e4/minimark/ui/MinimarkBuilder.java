/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.minimark.ui;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class MinimarkBuilder extends IncrementalProjectBuilder {
	public static final String ID = "com.packtpub.e4.minimark.ui.MinimarkBuilder";

	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(getProject(), monitor);
		} else {
			incrementalBuild(getProject(), monitor, getDelta(getProject()));
		}
		return null;
	}

	private void incrementalBuild(IProject project, IProgressMonitor monitor, IResourceDelta delta)
			throws CoreException {
		if (delta == null) {
			fullBuild(project, monitor);
		} else {
			delta.accept(new MinimarkVisitor());
		}
	}

	private void fullBuild(IProject project, IProgressMonitor monitor) throws CoreException {
		project.accept(new MinimarkVisitor(), IResource.NONE);
	}
}