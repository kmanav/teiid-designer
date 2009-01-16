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

package com.metamatrix.metamodels.relational;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.metamatrix.metamodels.relational.RelationalPackage
 * @generated
 */
public interface RelationalFactory extends EFactory{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    RelationalFactory eINSTANCE = new com.metamatrix.metamodels.relational.impl.RelationalFactoryImpl();

    /**
     * Returns a new object of class '<em>Column</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Column</em>'.
     * @generated
     */
    Column createColumn();

    /**
     * Returns a new object of class '<em>Schema</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Schema</em>'.
     * @generated
     */
    Schema createSchema();

    /**
     * Returns a new object of class '<em>Primary Key</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Primary Key</em>'.
     * @generated
     */
    PrimaryKey createPrimaryKey();

    /**
     * Returns a new object of class '<em>Foreign Key</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Foreign Key</em>'.
     * @generated
     */
    ForeignKey createForeignKey();

    /**
     * Returns a new object of class '<em>View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>View</em>'.
     * @generated
     */
    View createView();

    /**
     * Returns a new object of class '<em>Catalog</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Catalog</em>'.
     * @generated
     */
    Catalog createCatalog();

    /**
     * Returns a new object of class '<em>Procedure</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Procedure</em>'.
     * @generated
     */
    Procedure createProcedure();

    /**
     * Returns a new object of class '<em>Index</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Index</em>'.
     * @generated
     */
    Index createIndex();

    /**
     * Returns a new object of class '<em>Procedure Parameter</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Procedure Parameter</em>'.
     * @generated
     */
    ProcedureParameter createProcedureParameter();

    /**
     * Returns a new object of class '<em>Unique Constraint</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Unique Constraint</em>'.
     * @generated
     */
    UniqueConstraint createUniqueConstraint();

    /**
     * Returns a new object of class '<em>Access Pattern</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Access Pattern</em>'.
     * @generated
     */
    AccessPattern createAccessPattern();

    /**
     * Returns a new object of class '<em>Logical Relationship</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Logical Relationship</em>'.
     * @generated
     */
    LogicalRelationship createLogicalRelationship();

    /**
     * Returns a new object of class '<em>Logical Relationship End</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Logical Relationship End</em>'.
     * @generated
     */
    LogicalRelationshipEnd createLogicalRelationshipEnd();

    /**
     * Returns a new object of class '<em>Base Table</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Base Table</em>'.
     * @generated
     */
    BaseTable createBaseTable();

    /**
     * Returns a new object of class '<em>Procedure Result</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Procedure Result</em>'.
     * @generated
     */
    ProcedureResult createProcedureResult();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    RelationalPackage getRelationalPackage();

} //RelationalFactory
