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

import java.util.Map;
import java.util.TimeZone;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * Provides a means to convert an object into text. Think of it as an
 * externalised toString representation for an object that is passed in as the
 * argument.
 */
public class TimeZoneLabelProvider extends LabelProvider implements
		IStyledLabelProvider, IFontProvider {
	private final FontRegistry fr;
	private final ImageRegistry ir;

	public TimeZoneLabelProvider(ImageRegistry ir, FontRegistry fr) {
		this.ir = ir;
		this.fr = fr;
	}

	@SuppressWarnings("rawtypes")
	public String getText(Object element) {
		if (element instanceof Map) {
			return "Time Zones";
		} else if (element instanceof Map.Entry) {
			return ((Map.Entry) element).getKey().toString();
		} else if (element instanceof TimeZone) {
			return ((TimeZone) element).getID().split("/")[1];
		} else {
			return "Unknown type: " + element.getClass();
		}
	}

	public Image getImage(Object element) {
		if (element instanceof Map.Entry) {
			return ir.get("sample");
		} else if (element instanceof TimeZone) {
			return ir.get("sample");
		} else {
			return super.getImage(element);
		}
	}

	public StyledString getStyledText(Object element) {
		String text = getText(element);
		StyledString ss = new StyledString(text);
		if (element instanceof TimeZone) {
			int offset = -((TimeZone) element).getOffset(0);
			ss.append(" (" + offset / 3600000 + "h)",
					StyledString.DECORATIONS_STYLER);
		}
		return ss;
	}

	public Font getFont(Object element) {
		Font italic = fr.getItalic(JFaceResources.DEFAULT_FONT);
		return italic;
	}
}
