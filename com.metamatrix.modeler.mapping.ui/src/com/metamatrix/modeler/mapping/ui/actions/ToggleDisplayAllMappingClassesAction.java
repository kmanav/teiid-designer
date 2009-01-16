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

package com.metamatrix.modeler.mapping.ui.actions;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPart;

import com.metamatrix.modeler.diagram.ui.editor.DiagramEditor;
import com.metamatrix.modeler.internal.ui.viewsupport.ModelIdentifier;
import com.metamatrix.modeler.mapping.ui.UiConstants;
import com.metamatrix.modeler.mapping.ui.UiPlugin;
import com.metamatrix.modeler.mapping.ui.diagram.MappingDiagramUtil;
import com.metamatrix.modeler.mapping.ui.editor.MappingDiagramBehavior;


/**
 * ToggleDisplayAllMappingClassesAction
 */
public class ToggleDisplayAllMappingClassesAction extends MappingAction {

        
    private static String TOGGLE_DISPLAY_ALL_TOOLTIP 
        = UiConstants.Util.getString( "ToggleDisplayAllMappingClassesAction.showAll.tooltip" );  //$NON-NLS-1$
    private static String TOGGLE_DISPLAY_ALL_TEXT 
        = UiConstants.Util.getString( "ToggleDisplayAllMappingClassesAction.showAll.text" );  //$NON-NLS-1$
    private boolean logicalModel = false;
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public ToggleDisplayAllMappingClassesAction() {
        super( UiPlugin.getDefault(), SWT.TOGGLE );

        // set image and tooltip
        setImageDescriptor( UiPlugin.getDefault().getImageDescriptor( UiConstants.Images.SHOW_ALL_MAPPING_CLASSES ) );            
        setToolTipText( TOGGLE_DISPLAY_ALL_TOOLTIP );
        setText( TOGGLE_DISPLAY_ALL_TEXT );
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void setDiagramEditor( DiagramEditor editor ) {
        super.setDiagramEditor( editor );
        
        // update tooltip if logical model type
        boolean logical = ModelIdentifier.isLogicalModel(editor.getCurrentModelResource());

        if (this.logicalModel != logical) {
            this.logicalModel = logical;
            String key = (logical ? "ToggleDisplayAllMappingClassesAction.logicalModel.showAll.tooltip" //$NON-NLS-1$
                                  : TOGGLE_DISPLAY_ALL_TOOLTIP);
            setToolTipText(UiConstants.Util.getString(key));
            key = (logical ? "ToggleDisplayAllMappingClassesAction.logicalModel.showAll.text" //$NON-NLS-1$
                            : TOGGLE_DISPLAY_ALL_TEXT);
            setText(UiConstants.Util.getString(key));
        }

        // update the button state
        updateButtonState();
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.ISelectionListener#selectionChanged(IWorkbenchPart, ISelection)
     */
    @Override
    public void selectionChanged(IWorkbenchPart thePart, ISelection theSelection) {

        super.selectionChanged(thePart, theSelection);
        determineEnablement();
    }
    
    
    private MappingDiagramBehavior getBehavior() {
        return MappingDiagramUtil.getCurrentMappingDiagramBehavior();
    }
    

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IAction#run()
     */
    @Override
    protected void doRun() {
        
        // get current state
        boolean b = getBehavior().getDisplayAllMappingClasses();
        
        // reverse it
        getBehavior().setDisplayAllMappingClasses( !b );
        
        // update the button
        updateButtonState();
                
        // refresh the diagram...
        editor.doRefreshDiagram();        
    }

    protected void updateButtonState() {

        setChecked( getBehavior().getDisplayAllMappingClasses() );
    }
    
    private void determineEnablement() {

        /*
         * jhTODO A refinement would be to disable this action when no Mapping Classes are present
         */
        // always enable
        boolean enable = true;

        setEnabled(enable);
    }
    
}
