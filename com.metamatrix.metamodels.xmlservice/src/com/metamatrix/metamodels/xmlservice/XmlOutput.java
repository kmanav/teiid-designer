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

package com.metamatrix.metamodels.xmlservice;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Xml Output</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.metamatrix.metamodels.xmlservice.XmlOutput#getOperation <em>Operation</em>}</li>
 *   <li>{@link com.metamatrix.metamodels.xmlservice.XmlOutput#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.metamatrix.metamodels.xmlservice.XmlServicePackage#getXmlOutput()
 * @model
 * @generated
 */
public interface XmlOutput extends XmlMessage, XmlServiceComponent{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2000-2006 MetaMatrix Corporation. All rights reserved."; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Operation</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link com.metamatrix.metamodels.xmlservice.XmlOperation#getOutput <em>Output</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation</em>' container reference.
     * @see #setOperation(XmlOperation)
     * @see com.metamatrix.metamodels.xmlservice.XmlServicePackage#getXmlOutput_Operation()
     * @see com.metamatrix.metamodels.xmlservice.XmlOperation#getOutput
     * @model opposite="output" required="true"
     * @generated
     */
    XmlOperation getOperation();

    /**
     * Sets the value of the '{@link com.metamatrix.metamodels.xmlservice.XmlOutput#getOperation <em>Operation</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operation</em>' container reference.
     * @see #getOperation()
     * @generated
     */
    void setOperation(XmlOperation value);

    /**
     * Returns the value of the '<em><b>Result</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link com.metamatrix.metamodels.xmlservice.XmlResult#getOutput <em>Output</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Result</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Result</em>' containment reference.
     * @see #setResult(XmlResult)
     * @see com.metamatrix.metamodels.xmlservice.XmlServicePackage#getXmlOutput_Result()
     * @see com.metamatrix.metamodels.xmlservice.XmlResult#getOutput
     * @model opposite="output" containment="true"
     * @generated
     */
    XmlResult getResult();

    /**
     * Sets the value of the '{@link com.metamatrix.metamodels.xmlservice.XmlOutput#getResult <em>Result</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Result</em>' containment reference.
     * @see #getResult()
     * @generated
     */
    void setResult(XmlResult value);

} // XmlOutput
