/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.timezones.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer implements Runnable {
	private ServerSocket socket;
	private boolean running = true;
	private Thread thread;

	public EchoServer(int port) throws IOException {
		this.socket = new ServerSocket(port);
		this.thread = new Thread(this);
		this.thread.setDaemon(true);
		this.thread.start();
	}

	public void run() {
		try {
			byte[] buffer = new byte[1024];
			while (running) {
				Socket client = null;
				try {
					client = socket.accept();
					InputStream in = client.getInputStream();
					OutputStream out = client.getOutputStream();
					int read;
					while (running && (read = in.read(buffer)) > 0) {
						out.write(buffer, 0, read);
						out.flush();
					}
				} catch (InterruptedIOException e) {
					running = false;
				} catch (Exception e) {
				} finally {
					safeClose(client);
				}
			}
		} finally {
			safeClose(socket);
		}
	}

	public void safeClose(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
		}
	}

	public void stop() {
		running = false;
		this.thread.interrupt();
	}
}