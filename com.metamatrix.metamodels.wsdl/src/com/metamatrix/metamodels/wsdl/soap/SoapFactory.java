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

package com.metamatrix.metamodels.wsdl.soap;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.metamatrix.metamodels.wsdl.soap.SoapPackage
 * @generated
 */
public interface SoapFactory extends EFactory{
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = "Copyright � 2000-2005 MetaMatrix, Inc.  All rights reserved."; //$NON-NLS-1$

    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	SoapFactory eINSTANCE = new com.metamatrix.metamodels.wsdl.soap.impl.SoapFactoryImpl();

    /**
     * Returns a new object of class '<em>Address</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Address</em>'.
     * @generated
     */
	SoapAddress createSoapAddress();

    /**
     * Returns a new object of class '<em>Header Fault</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Header Fault</em>'.
     * @generated
     */
	SoapHeaderFault createSoapHeaderFault();

    /**
     * Returns a new object of class '<em>Fault</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Fault</em>'.
     * @generated
     */
	SoapFault createSoapFault();

    /**
     * Returns a new object of class '<em>Header</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Header</em>'.
     * @generated
     */
	SoapHeader createSoapHeader();

    /**
     * Returns a new object of class '<em>Body</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Body</em>'.
     * @generated
     */
	SoapBody createSoapBody();

    /**
     * Returns a new object of class '<em>Operation</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Operation</em>'.
     * @generated
     */
	SoapOperation createSoapOperation();

    /**
     * Returns a new object of class '<em>Binding</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Binding</em>'.
     * @generated
     */
	SoapBinding createSoapBinding();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	SoapPackage getSoapPackage();

} //SoapFactory
