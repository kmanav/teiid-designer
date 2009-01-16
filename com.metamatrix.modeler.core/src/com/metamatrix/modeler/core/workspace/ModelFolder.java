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

package com.metamatrix.modeler.core.workspace;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

/**
 * A ModelFolder encapsulates {@link org.eclipse.core.resources.IFolder Eclipse folder} instances under
 * a {@link org.eclipse.core.resources.IProject Eclipse project} with the 
 * {@link ModelerCore#NATURE_ID com.metamatrix.modeler.core.modelnature}
 * nature.
 * <p>
 * Model folder items need to be opened before they can be navigated or manipulated.
 * The children are of type {@link ModelFolder} and {@link ModelResource}.
 * The children are listed in no particular order.
 * </p>
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 */
public interface ModelFolder extends ModelWorkspaceItem, Openable {

    /**
     * Return the {@link IFolder Eclipse folder} that this object
     * represents and upon which this item was created.
     * @return the Eclipse folder
     */
    IFolder getFolder();
    
    /**
     * Returns an array of non-modeling resources directly contained in this project.
     * It does not transitively answer non-Java resources contained in folders;
     * these would have to be explicitly iterated over.
     * <p>
     * Non-model resources includes other files and folders located in the
     * project not accounted for by any of it model package fragment
     * roots.
     * </p>
     * 
     * @return an array of non-Modeling resources directly contained in this project
     * @exception ModelWorkspaceException if this element does not exist or if an
     *      exception occurs while accessing its corresponding resource
     */
    Object[] getNonModelingResources() throws ModelWorkspaceException;

    /**
     * Return the {@link ModelWorkspaceItem model workspace item} for the specified resource and
     * contained by this project.
     * <p>
     * This method returns an object that is in the {@link #getChildren() children}.
     * </p>
     * @return the {@link ModelWorkspaceItem} instances contained by this project item that represents
     * the suppplied resource; may be null if the supplied resource doesn't represent a model or a folder.
     * @throws ModelWorkspaceException
     */
    ModelWorkspaceItem getModelWorkspaceItem( IResource resource ) throws ModelWorkspaceException;
}
    
