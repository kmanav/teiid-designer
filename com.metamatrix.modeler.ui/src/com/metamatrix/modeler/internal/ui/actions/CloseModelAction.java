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

package com.metamatrix.modeler.internal.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.ActionDelegate;

import com.metamatrix.modeler.core.workspace.ModelResource;
import com.metamatrix.modeler.core.workspace.ModelWorkspaceException;
import com.metamatrix.modeler.internal.ui.viewsupport.ModelUtilities;
import com.metamatrix.modeler.ui.UiConstants;
import com.metamatrix.modeler.ui.UiPlugin;
import com.metamatrix.modeler.ui.editors.ModelEditorManager;
import com.metamatrix.modeler.ui.event.ModelResourceEvent;
import com.metamatrix.ui.internal.eventsupport.SelectionUtilities;

/**
 * CloseModelAction
 */
public class CloseModelAction extends ActionDelegate implements IWorkbenchWindowActionDelegate,
                                                                IViewActionDelegate {

    private Collection selectedModelResources;

    /**
     * Construct an instance of RebuildImportsAction.
     */
    public CloseModelAction() {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {
        if (selectedModelResources != null) {
            try {
                
                for ( Iterator iter = selectedModelResources.iterator() ; iter.hasNext() ; ) {
                    
                    ModelResource modelResource = (ModelResource) iter.next();
                    IFile modelFile = (IFile) modelResource.getResource(); 
                    
                    // defect 11675 - make sure user didn't cancel closing unsaved changes
                    boolean doClose = true;
                    if (ModelEditorManager.isOpen(modelFile)) {
                        // make sure the user didn't cancel the close:
                        doClose = ModelEditorManager.close(modelFile, true);
                    } // endif -- open in editor
                    
                    if (doClose) {
                        ModelResourceEvent event = new ModelResourceEvent(modelResource,
                                ModelResourceEvent.CLOSING, this);
                        UiPlugin.getDefault().getEventBroker().processEvent(event);
                        modelResource.close();
                        event = new ModelResourceEvent(modelResource, ModelResourceEvent.CLOSED,
                                this);
                        UiPlugin.getDefault().getEventBroker().processEvent(event);
                    } // endif
                }

            } catch (ModelWorkspaceException e) {
                UiConstants.Util.log(IStatus.ERROR, e, e.getMessage());
            }
        }
        
        // Defect 11675 - leave the action enabled until the selection changes
        // action.setEnabled(false);

    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        selectedModelResources = Collections.EMPTY_LIST;
        boolean enable = SelectionUtilities.isAllIResourceObjects(selection);
        if ( enable ) {
            // can only enable if all selected objects have open ModelResources
            Collection selectedObjects = SelectionUtilities.getSelectedObjects(selection);
            if ( selectedObjects != null && ! selectedObjects.isEmpty() ) {
                selectedModelResources = new ArrayList(selectedObjects.size());
                for ( Iterator iter = selectedObjects.iterator() ; iter.hasNext() && enable ; ) {
                    Object obj = iter.next();
                    
                    // must be a model
                    if ( obj instanceof IResource && ModelUtilities.isModelFile((IResource) obj)) {
                        try {
                            ModelResource modelResource = ModelUtilities.getModelResource((IFile) obj, false);
                            // defect 11675 -  ModelResource does not need to be open, just present
                            if ( modelResource!=null ) {
                                selectedModelResources.add(modelResource);
                            } else {
                                enable = false;
                            }
                        } catch (Exception e) {
                            UiConstants.Util.log(IStatus.ERROR, e, e.getMessage());
                            enable = false;
                        }
                    } else {
                        enable = false;
                    }
                }
            }
        }
        action.setEnabled(enable);
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
     */
    public void init(IWorkbenchWindow window) {

    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
     */
    public void init(IViewPart view) {
    }

}
