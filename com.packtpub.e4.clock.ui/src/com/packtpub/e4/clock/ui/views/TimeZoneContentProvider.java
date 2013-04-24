/*
 * Copyright (c) 2013, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2013, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui.views;

import java.util.Collection;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Provides a tree view of TimeZones.
 */
public class TimeZoneContentProvider implements ITreeContentProvider {
	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Object[]) {
			return (Object[]) inputElement;
		} else {
			return new Object[0];
		}
	}

	@SuppressWarnings("rawtypes")
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Map) {
			return ((Map) parentElement).entrySet().toArray();
		} else if (parentElement instanceof Map.Entry) {
			return getChildren(((Map.Entry) parentElement).getValue());
		} else if (parentElement instanceof Collection) {
			return ((Collection) parentElement).toArray();
		} else {
			return new Object[0];
		}
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	public boolean hasChildren(Object element) {
		if (element instanceof Map) {
			return !((Map) element).isEmpty();
		} else if (element instanceof Map.Entry) {
			return hasChildren(((Map.Entry) element).getValue());
		} else if (element instanceof Collection) {
			return !((Collection) element).isEmpty();
		} else {
			return false;
		}
	}

}
