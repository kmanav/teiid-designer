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

package com.metamatrix.modeler.core.index;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;

import com.metamatrix.core.index.IDocument;

/**
 * VDBDocument contains references to a collection of resources that need to be indexed
 * It contains path to a dummy resource file for which these indexes built. We need a dummy
 * resource because JDT index framework needs an index to be associated with an resource file. 
 */
public interface VDBDocument extends IDocument {

    /**
     * Get the collection of {@link org.eclipse.emf.ecore.resource.Resource} 
     * instance that this document contains
     * @return collection of Resources for this document
     */
    Collection getResources();

    /**
     * Return the name of the file this document is indexed to
     * @return The name of the file to index this document
     */
    String getIndexName();
    
    /**
     * Return the relative path in the vdb to the {@link org.eclipse.emf.ecore.resource.Resource} 
     * instance with the specified URI
     * @param resourceURI the resource uri
     * @return The relative path in the vdb
     */
    String getModelPath(URI resourceURI);    
}
