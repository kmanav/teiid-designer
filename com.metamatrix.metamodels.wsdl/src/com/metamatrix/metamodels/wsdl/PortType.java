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

package com.metamatrix.metamodels.wsdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.metamatrix.metamodels.wsdl.PortType#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link com.metamatrix.metamodels.wsdl.PortType#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.metamatrix.metamodels.wsdl.WsdlPackage#getPortType()
 * @model
 * @generated
 */
public interface PortType extends WsdlNameRequiredEntity, ExtensibleAttributesDocumented{
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = "Copyright � 2000-2005 MetaMatrix, Inc.  All rights reserved."; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Definitions</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link com.metamatrix.metamodels.wsdl.Definitions#getPortTypes <em>Port Types</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definitions</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Definitions</em>' container reference.
     * @see #setDefinitions(Definitions)
     * @see com.metamatrix.metamodels.wsdl.WsdlPackage#getPortType_Definitions()
     * @see com.metamatrix.metamodels.wsdl.Definitions#getPortTypes
     * @model opposite="portTypes" required="true"
     * @generated
     */
	Definitions getDefinitions();

    /**
     * Sets the value of the '{@link com.metamatrix.metamodels.wsdl.PortType#getDefinitions <em>Definitions</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definitions</em>' container reference.
     * @see #getDefinitions()
     * @generated
     */
	void setDefinitions(Definitions value);

    /**
     * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
     * The list contents are of type {@link com.metamatrix.metamodels.wsdl.Operation}.
     * It is bidirectional and its opposite is '{@link com.metamatrix.metamodels.wsdl.Operation#getPortType <em>Port Type</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Operations</em>' containment reference list.
     * @see com.metamatrix.metamodels.wsdl.WsdlPackage#getPortType_Operations()
     * @see com.metamatrix.metamodels.wsdl.Operation#getPortType
     * @model type="com.metamatrix.metamodels.wsdl.Operation" opposite="portType" containment="true"
     * @generated
     */
	EList getOperations();

} // PortType
