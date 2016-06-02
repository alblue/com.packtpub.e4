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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class MinimarkNature implements IProjectNature {
	public static final String ID = "com.packtpub.e4.minimark.ui.MinimarkNature";
	private IProject project;

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	public void configure() throws CoreException {
		IProjectDescription desc = project.getDescription();
		List<ICommand> commands = new ArrayList<ICommand>(Arrays.asList(desc.getBuildSpec()));
		Iterator<ICommand> iterator = commands.iterator();
		while (iterator.hasNext()) {
			ICommand command = iterator.next();
			if (MinimarkBuilder.ID.equals(command.getBuilderName())) {
				return;
			}
		}
		ICommand newCommand = desc.newCommand();
		newCommand.setBuilderName(MinimarkBuilder.ID);
		commands.add(newCommand);
		desc.setBuildSpec(commands.toArray(new ICommand[0]));
		project.setDescription(desc, null);
	}

	public void deconfigure() throws CoreException {
		IProjectDescription desc = project.getDescription();
		List<ICommand> commands = new ArrayList<ICommand>(Arrays.asList(desc.getBuildSpec()));
		Iterator<ICommand> iterator = commands.iterator();
		while (iterator.hasNext()) {
			ICommand command = iterator.next();
			if (MinimarkBuilder.ID.equals(command.getBuilderName())) {
				iterator.remove();
			}
		}
		desc.setBuildSpec(commands.toArray(new ICommand[0]));
		project.setDescription(desc, null);
	}
}