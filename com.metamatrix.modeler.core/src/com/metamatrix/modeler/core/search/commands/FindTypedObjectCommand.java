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

package com.metamatrix.modeler.core.search.commands;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.metamatrix.modeler.core.index.IndexSelector;

/**
 * This interface is used to find typed objects based on datatype criteria.
 */
public interface FindTypedObjectCommand	extends SearchCommand {

	/**
	 * Return a collection of record
	 * objects that are found on this command execution. 
	 * @return a collection of records
	 */
	Collection getRecordInfo();
    
    /**
     * Set the IndexSelector that will be used to obtain models that will be searched.
     * @param selector the index selector that should be used, or null if the 
     * {@link com.metamatrix.modeler.internal.core.index.ModelWorkspaceIndexSelector} should be used
     */
    void setIndexSelector( IndexSelector selector );
    
    /**
     * Set the metamodel class to use in the search. 
     * @param metaClass
     * @since 4.1
     */
    public void setMetaClass(EClass metaClass);
    
    /**
     * Set the EObject representing the simple datatype that must be matched 
     * @param datatype
     * @param includeSubtypes
     * @since 4.1
     */
    public void setDatatype( EObject datatype );
    
    /**
     * Set the EObject[] representing the all subtypes that must be matched 
     * @param subTypes
     * @param includeSubtypes
     * @since 4.1
     */
    public void setSubTypes( EObject[] subTypes );
    
    /**
     * Set the runtime type that must be matched. 
     * @param runtimeType
     * @since 4.1
     */
    public void setRuntimeType( String runtimeType );
    
}
