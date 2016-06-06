/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui.internal.css;

import org.eclipse.e4.ui.css.core.dom.IElementProvider;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.w3c.dom.Element;

import com.packtpub.e4.clock.ui.ClockWidget;

@SuppressWarnings("restriction")
public class ClockElementProvider implements IElementProvider {
	public Element getElement(Object element, CSSEngine engine) {
		if (element instanceof ClockWidget) {
			return new ClockElement((ClockWidget) element, engine);
		}
		return null;
	}
}