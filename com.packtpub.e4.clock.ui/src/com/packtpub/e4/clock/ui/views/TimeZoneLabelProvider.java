/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui.views;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;

public class TimeZoneLabelProvider extends LabelProvider implements IStyledLabelProvider, IFontProvider {
	private final ISharedImages images;
	private final ImageRegistry ir;
	private final FontRegistry fr;

	public TimeZoneLabelProvider(ISharedImages images, ImageRegistry ir, FontRegistry fr) {
		this.images = images;
		this.ir = ir;
		this.fr = fr;
	}

	@SuppressWarnings("rawtypes")
	public String getText(Object element) {
		if (element instanceof Map) {
			return "Time Zones";
		} else if (element instanceof Map.Entry) {
			return ((Map.Entry) element).getKey().toString();
		} else if (element instanceof ZoneId) {
			return ((ZoneId) element).getId().split("/")[1];
		} else {
			return "Unknown type: " + element.getClass();
		}
	}

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Object[]) {
			return (Object[]) inputElement;
		} else {
			return new Object[0];
		}
	}

	public Image getImage(Object element) {
		if (element instanceof Map.Entry) {
			return images.getImage(ISharedImages.IMG_OBJ_FOLDER);
		} else if (element instanceof ZoneId) {
			return ir.get("sample");
		} else {
			return super.getImage(element);
		}
	}

	public StyledString getStyledText(Object element) {
		String text = getText(element);
		StyledString styledString = new StyledString(text);
		if (element instanceof ZoneId) {
			ZoneId zone = (ZoneId) element;
			ZoneOffset offset = ZonedDateTime.now(zone).getOffset();
			styledString.append(" (" + offset + ")", StyledString.DECORATIONS_STYLER);
		}
		return styledString;
	}

	public Font getFont(Object element) {
		Font italic = fr.getItalic(JFaceResources.DEFAULT_FONT);
		return italic;
	}
}