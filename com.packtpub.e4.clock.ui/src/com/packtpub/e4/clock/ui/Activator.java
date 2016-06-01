/*
 * Copyright (c) 2016, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2016, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.service.prefs.Preferences;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
	private TrayItem trayItem;
	private Image image;
	// The plug-in ID
	public static final String PLUGIN_ID = "com.packtpub.e4.clock.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		final Display display = Display.getDefault();
		display.asyncExec(() -> {
			image = new Image(display, Activator.class.getResourceAsStream("/icons/sample.gif"));
			Tray tray = display.getSystemTray();
			if (tray != null && image != null) {
				trayItem = new TrayItem(tray, SWT.NONE);
				trayItem.setToolTipText("Hello World");
				trayItem.setVisible(true);
				trayItem.setText("Hello World");
				trayItem.setImage(image);
				trayItem.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						Shell shell = new Shell(display, SWT.ON_TOP | SWT.NO_TRIM);
						shell.setAlpha(128);
						final Region region = new Region();
						region.add(circle(25, 25, 25));
						shell.setRegion(region);
						shell.setLayout(new FillLayout());
						new ClockWidget(shell, SWT.NONE, new RGB(255, 0, 255));
						shell.pack();
						shell.open();
						shell.addDisposeListener(de -> region.dispose());
					}
				});
			}
		});
		// import org.osgi.service.prefs.Preferences;
		// import org.eclipse.core.runtime.preferences.InstanceScope;
		// ^ add these if necessary to top of the class
		Preferences preferences = InstanceScope.INSTANCE.getNode("com.packtpub.e4.clock.ui");
		int launchCount = preferences.getInt("launchCount", 0) + 1;
		preferences.putInt("launchCount", launchCount);
		System.out.println("I have been launched " + launchCount + " times");
		preferences.sync();
	}

	private static int[] circle(int r, int offsetX, int offsetY) {
		int[] polygon = new int[8 * r + 4];
		// x^2 + y^2 = r^2
		for (int i = 0; i < 2 * r + 1; i++) {
			int x = i - r;
			int y = (int) Math.sqrt(r * r - x * x);
			polygon[2 * i] = offsetX + x;
			polygon[2 * i + 1] = offsetY + y;
			polygon[8 * r - 2 * i - 2] = offsetX + x;
			polygon[8 * r - 2 * i - 1] = offsetY - y;
		}
		return polygon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		if (trayItem != null) {
			Display.getDefault().asyncExec(trayItem::dispose);
		}
		if (image != null) {
			Display.getDefault().asyncExec(image::dispose);
		}
		super.stop(context);

	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 *
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
