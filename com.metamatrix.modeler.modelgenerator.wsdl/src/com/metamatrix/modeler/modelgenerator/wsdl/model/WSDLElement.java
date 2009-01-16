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

package com.metamatrix.modeler.modelgenerator.wsdl.model;


/**
 * 
 * This class is an abstract representation of the elements
 * that appear in a WSDL
 * 
 * @author JChoate
 *
 */
public interface WSDLElement {
	
	/**
	 * 
	 * @return the name of the element
	 */
	public String getName();
	
	/**
	 * 
	 * @param name the name of the element
	 */
	public void setName(String name);
	
	/**
	 * 
	 * @return the id of the element
	 */
	public String getId();
	
	/**
	 * 
	 * @param id the id of the element
	 */
	public void setId(String id);
	
	/**
	 * This method is used for making defensive copies of WSDLElements
	 * 
	 * @return a copy of the element
	 */
	public WSDLElement copy();
	
}
