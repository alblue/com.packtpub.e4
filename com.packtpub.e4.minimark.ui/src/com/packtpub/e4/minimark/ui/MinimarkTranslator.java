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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class MinimarkTranslator {
	// For testing the convert() method as a standalone Java app
	// public static void main(String[] args) throws IOException {
	// convert(new FileReader("in.txt"), new FileWriter("out.txt"));
	// }

	public static void convert(Reader reader, Writer writer) throws IOException {
		BufferedReader lines = new BufferedReader(reader);
		String line;
		String title = String.valueOf(lines.readLine());
		writer.write("<html><head><title>");
		writer.write(title);
		writer.write("</title></head><body><h1>");
		writer.write("</h1><p>");
		while (null != (line = lines.readLine())) {
			if ("".equals(line)) {
				writer.write("</p><p>");
			} else {
				writer.write(line);
				writer.write('\n');
			}
		}
		writer.write("</p></body></html>");
		writer.flush();
	}
}