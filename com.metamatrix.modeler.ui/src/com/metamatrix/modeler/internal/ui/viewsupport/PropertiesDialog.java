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

package com.metamatrix.modeler.internal.ui.viewsupport;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.views.properties.PropertySheetPage;

import org.eclipse.emf.ecore.EObject;

import com.metamatrix.modeler.internal.ui.properties.ModelObjectPropertySheetPage;
import com.metamatrix.modeler.ui.UiConstants;

/**
 * PropertiesDialog is a dialog that displays the ModelObjectPropertySheet for an EObject
 */
public class PropertiesDialog extends SelectionDialog {

    private static final String TITLE = UiConstants.Util.getString("PropertiesDialog.title"); //$NON-NLS-1$
    private static final String MSG_PREFIX = UiConstants.Util.getString("PropertiesDialog.message") +  ' '; //$NON-NLS-1$

    private EObject propertiedObject;

    /**
     * Construct an instance of PropertyDialog.
     * @param propertiedObject the EObject to display in this 
     * @param title
     */
    public PropertiesDialog(EObject propertiedObject, Shell parent) {
        super(parent);
        this.propertiedObject = propertiedObject;
        setTitle(TITLE);
        String name = ModelUtilities.getEMFItemDelegator().getText(propertiedObject);
        setMessage(MSG_PREFIX + propertiedObject.eClass().getName() + ' ' + name);
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        super.createButton(
                parent,
                IDialogConstants.OK_ID,
                IDialogConstants.OK_LABEL,
                true);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite container) {
        Composite parent = (Composite) super.createDialogArea(container);
        createMessageArea(parent);

        PropertySheetPage page = new ModelObjectPropertySheetPage();
        page.setPropertySourceProvider(ModelUtilities.getPropertySourceProvider());
        page.createControl(parent);
        page.selectionChanged(null, new StructuredSelection(propertiedObject));
        Control result = page.getControl();
        GridData gd= new GridData(GridData.FILL_BOTH);
        gd.heightHint=400;
        gd.widthHint=600;
        result.setLayoutData(gd);
        return parent;
    }

    /* (non-Javadoc)
     * Overridden to make the shell resizable.
     * @see org.eclipse.jface.window.Window#create()
     */
    @Override
    public void create() {
        setShellStyle(getShellStyle() | SWT.RESIZE);
        super.create();
    }

}
