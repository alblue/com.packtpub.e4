/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.junit.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MathUtilTest {
	@Test
	public void testOdd() {
		assertTrue(MathUtil.isOdd(3));
		assertFalse(MathUtil.isOdd(4));
	}
}
