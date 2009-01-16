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

package com.metamatrix.modeler.internal.core.workspace;

import java.util.List;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import com.metamatrix.metamodels.transformation.MappingClassSet;
import com.metamatrix.modeler.core.ModelerCore;
import com.metamatrix.modeler.core.util.ModelResourceContainerFactory;
import com.metamatrix.modeler.core.workspace.ModelMappingClassSets;
import com.metamatrix.modeler.core.workspace.ModelResource;
import com.metamatrix.modeler.core.workspace.ModelWorkspaceException;
import com.metamatrix.modeler.core.workspace.ModelWorkspaceItem;

/**
 * ModelMappingClassSetsImpl
 */
public class ModelMappingClassSetsImpl extends NonOpenableModelWorkspaceItemImpl implements ModelMappingClassSets {

    protected static final String DEFAULT_NAME = ModelerCore.Util.getString("ModelMappingClassSetsImpl.defaultName"); //$NON-NLS-1$

    /**
     * Constructor needed for test cases.
     */
    ModelMappingClassSetsImpl() {
        super(MAPPING_CLASS_SETS, null, DEFAULT_NAME);
    }

    /**
     * Construct an instance of ModelProjectImpl.
     * 
     * @since 4.0
     */
    public ModelMappingClassSetsImpl( final ModelWorkspaceItem parent ) {
        super(MAPPING_CLASS_SETS, parent, DEFAULT_NAME);
    }

    public ModelResource getModelResource() {
        return (ModelResource)this.getParent();
    }

    /**
     * @see com.metamatrix.modeler.core.workspace.ModelWorkspaceItem#getResource()
     */
    public IResource getResource() {
        return this.getModelResource().getResource();
    }

    /**
     * @see com.metamatrix.modeler.core.workspace.ModelWorkspaceItem#getUnderlyingResource()
     */
    public IResource getUnderlyingResource() {
        return getResource();
    }

    /**
     * @see com.metamatrix.modeler.core.workspace.ModelWorkspaceItem#getPath()
     */
    public IPath getPath() {
        return this.getModelResource().getPath();
    }

    /**
     * @see com.metamatrix.modeler.core.workspace.ModelWorkspaceItem#hasChildren()
     */
    @Override
    public boolean hasChildren() {
        return false;
    }

    protected ModelBufferImpl getModelBuffer() throws ModelWorkspaceException {
        final ModelResourceImpl modelResourceImpl = (ModelResourceImpl)this.getModelResource();
        return (ModelBufferImpl)modelResourceImpl.getBuffer(); // loads if req'd
    }

    /**
     * @see com.metamatrix.modeler.core.workspace.ModelMappingClassSets#createNewMappingClassSet(org.eclipse.emf.ecore.EObject)
     */
    public MappingClassSet createNewMappingClassSet( EObject target ) throws ModelWorkspaceException {
        return ModelResourceContainerFactory.createNewMappingClassSet(target, this.getModelResource().getEmfResource());
    }

    /**
     * @see com.metamatrix.modeler.core.workspace.ModelMappingClassSets#getMappingClassSets(org.eclipse.emf.ecore.EObject)
     */
    public List getMappingClassSets( EObject target ) throws ModelWorkspaceException {
        return getModelBuffer().getModelContents().getMappingClassSets(target);
    }

    /**
     * @see com.metamatrix.modeler.core.workspace.ModelMappingClassSets#getMappingClassSets()
     */
    public List getMappingClassSets() throws ModelWorkspaceException {
        return getModelBuffer().getModelContents().getMappingClassSets();
    }

    /**
     * @see com.metamatrix.modeler.core.workspace.ModelMappingClassSets#delete(com.metamatrix.metamodels.transformation.MappingClassSet)
     */
    public boolean delete( MappingClassSet mappingClassSet ) {
        return ModelResourceContainerFactory.deleteMappingClassSet(mappingClassSet);
    }

}
