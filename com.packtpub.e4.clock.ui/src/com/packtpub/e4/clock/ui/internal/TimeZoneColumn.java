/*
 * Copyright (c) 2013, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2013, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.clock.ui.internal;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;

public abstract class TimeZoneColumn extends ColumnLabelProvider {
	public abstract String getText(Object element);

	public abstract String getTitle();

	public int getWidth() {
		return 250;
	}

	public TableViewerColumn addColumnTo(TableViewer viewer) {
		TableViewerColumn tableViewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		TableColumn column = tableViewerColumn.getColumn();
		column.setMoveable(true);
		column.setResizable(true);
		column.setText(getTitle());
		column.setWidth(getWidth());
		tableViewerColumn.setLabelProvider(this);
		return tableViewerColumn;
	}
}