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

import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.properties.AbstractCSSPropertySWTHandler;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;
import org.w3c.dom.css.CSSValue;

import com.packtpub.e4.clock.ui.ClockWidget;

@SuppressWarnings("restriction")
public class CSSPropertyClockHandler extends AbstractCSSPropertySWTHandler {

	@Override
	protected void applyCSSProperty(Control control, String property, CSSValue value, String pseudo, CSSEngine engine)
			throws Exception {
		if (control instanceof ClockWidget) {
			ClockWidget clock = (ClockWidget) control;
			switch (property) {
			case "clock-hand-color":
				Color handColor = (Color) engine.convert(value, Color.class, control.getDisplay());
				clock.setHandColor(handColor);
				break;
			}
		}
	}

	@Override
	protected String retrieveCSSProperty(Control control, String property, String pseudo, CSSEngine engine)
			throws Exception {
		if (control instanceof ClockWidget) {
			ClockWidget clock = (ClockWidget) control;
			switch (property) {
			case "clock-hand-color":
				return engine.convert(clock.getHandColor(), Color.class, null);
			}
		}
		return null;
	}

}
