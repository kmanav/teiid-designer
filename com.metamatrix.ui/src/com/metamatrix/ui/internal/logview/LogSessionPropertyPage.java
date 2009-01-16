/* ================================================================================== 
 * JBoss, Home of Professional Open Source. 
 * 
 * Copyright (c) 2000, 2009 MetaMatrix, Inc. and Red Hat, Inc. 
 * 
 * Some portions of this file may be copyrighted by other 
 * contributors and licensed to Red Hat, Inc. under one or more 
 * contributor license agreements. See the copyright.txt file in the 
 * distribution for a full listing of individual contributors. 
 * 
 * This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html 
 * ================================================================================== */ 

package com.metamatrix.ui.internal.logview;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.dialogs.PropertyPage;

import com.metamatrix.ui.UiConstants;

public class LogSessionPropertyPage extends PropertyPage {
    public static final String SESSION_TXT = UiConstants.Util.getString("LogView.propertyPage.session"); //$NON-NLS-1$

    public LogSessionPropertyPage() {
        noDefaultAndApplyButton();
    }
    @Override
    protected Control createContents(Composite parent) {
        LogEntry entry = (LogEntry) getElement();
        LogSession session = entry.getSession();
        
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        //layout.numColumns = 2;
        container.setLayout(layout);
        Label label = new Label(container, SWT.NULL);
        label.setText(SESSION_TXT);
        label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
        Text text =
            new Text(
                container,
                SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
        text.setEditable(false);
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.widthHint = 300;
        gd.heightHint = 300;
        // defect 17008
        if (session != null && session.getSessionData() != null)
            text.setText(session.getSessionData());
        text.setLayoutData(gd);
        return container;
    }
}
