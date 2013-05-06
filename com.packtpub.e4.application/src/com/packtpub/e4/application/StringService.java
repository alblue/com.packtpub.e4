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

import org.eclipse.e4.core.di.annotations.Creatable;

@SuppressWarnings("restriction")
@Creatable
public class StringService {
	public String process(String string) {
		return string.toUpperCase();
	}
}