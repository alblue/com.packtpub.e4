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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

public class MinimarkVisitor implements IResourceProxyVisitor, IResourceDeltaVisitor {
	public boolean visit(IResourceDelta delta) throws CoreException {
		boolean deleted = (IResourceDelta.REMOVED & delta.getKind()) != 0;
		IResource resource = delta.getResource();
		String name = resource.getName();
		if (name.endsWith(".minimark")) {
			if (deleted) {
				String htmlName = name.replace(".minimark", ".html");
				IFile htmlFile = resource.getParent().getFile(new Path(htmlName));
				if (htmlFile.exists()) {
					htmlFile.delete(true, null);
				}
			} else {
				processResource(resource);
			}
		} else if (name.endsWith(".html")) {
			String minimarkName = name.replace(".html", ".minimark");
			IFile minimarkFile = resource.getParent().getFile(new Path(minimarkName));
			if (minimarkFile.exists()) {
				processResource(minimarkFile);
			}
		}
		return true;
	}

	public boolean visit(IResourceProxy proxy) throws CoreException {
		String name = proxy.getName();
		if (name != null && name.endsWith(".minimark")) {
			// System.out.println("Processing " + name);
			processResource(proxy.requestResource());
		}
		return true;
	}

	private void processResource(IResource resource) throws CoreException {
		if (resource instanceof IFile && resource.exists()) {
			try {
				resource.deleteMarkers("com.packtpub.e4.minimark.ui.MinimarkMarker", true, IResource.DEPTH_INFINITE);
				IFile file = (IFile) resource;
				InputStream in = file.getContents();
				String htmlName = file.getName().replace(".minimark", ".html");
				IContainer container = file.getParent();
				IFile htmlFile = container.getFile(new Path(htmlName));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				MinimarkTranslator.convert(new InputStreamReader(in), new OutputStreamWriter(baos));
				ByteArrayInputStream contents = new ByteArrayInputStream(baos.toByteArray());
				if (baos.size() < 100) {
					// System.out.println("Minimark file is empty");
					// IMarker marker = resource.createMarker(IMarker.PROBLEM);
					IMarker marker = resource.createMarker("com.packtpub.e4.minimark.ui.MinimarkMarker");
					marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
					marker.setAttribute(IMarker.MESSAGE, "Minimark file is empty");
					marker.setAttribute(IMarker.LINE_NUMBER, 1);
					marker.setAttribute(IMarker.CHAR_START, 0);
					marker.setAttribute(IMarker.CHAR_END, 0);
				}
				if (htmlFile.exists()) {
					htmlFile.setContents(contents, true, false, null);
				} else {
					htmlFile.create(contents, true, null);
				}
				htmlFile.setDerived(true, null);
			} catch (IOException e) {
				throw new CoreException(
						new Status(Status.ERROR, Activator.PLUGIN_ID, "Failed to generate resource", e));
			}
		}
	}
}
