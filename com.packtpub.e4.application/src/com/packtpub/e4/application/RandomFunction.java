/*
 * Copyright (c) 2013, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2013, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.application;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;

@SuppressWarnings("restriction")
public final class RandomFunction extends ContextFunction {
	@Override
	// For Eclipse 4.3 onwards, this should be compute(IEclipseContext,String)
	public Object compute(final IEclipseContext context) {
		return Math.random();
	}
}