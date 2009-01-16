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

package com.metamatrix.metamodels.xml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Choice Error Mode</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.metamatrix.metamodels.xml.XmlDocumentPackage#getChoiceErrorMode()
 * @model
 * @generated
 */
public final class ChoiceErrorMode extends AbstractEnumerator {
    /**
     * The '<em><b>THROW</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #THROW_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int THROW = 0;

    /**
     * The '<em><b>RECORD</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RECORD_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int RECORD = 1;

    /**
     * The '<em><b>DISCARD</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DISCARD_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int DISCARD = 2;

    /**
     * The '<em><b>THROW</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>THROW</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #THROW
     * @generated
     * @ordered
     */
    public static final ChoiceErrorMode THROW_LITERAL = new ChoiceErrorMode(THROW, "THROW"); //$NON-NLS-1$

    /**
     * The '<em><b>RECORD</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>RECORD</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #RECORD
     * @generated
     * @ordered
     */
    public static final ChoiceErrorMode RECORD_LITERAL = new ChoiceErrorMode(RECORD, "RECORD"); //$NON-NLS-1$

    /**
     * The '<em><b>DISCARD</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DISCARD</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DISCARD
     * @generated
     * @ordered
     */
    public static final ChoiceErrorMode DISCARD_LITERAL = new ChoiceErrorMode(DISCARD, "DISCARD"); //$NON-NLS-1$

    /**
     * An array of all the '<em><b>Choice Error Mode</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ChoiceErrorMode[] VALUES_ARRAY =
        new ChoiceErrorMode[] {
            THROW_LITERAL,
            RECORD_LITERAL,
            DISCARD_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Choice Error Mode</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Choice Error Mode</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ChoiceErrorMode get(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ChoiceErrorMode result = VALUES_ARRAY[i];
            if (result.toString().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Choice Error Mode</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ChoiceErrorMode get(int value) {
        switch (value) {
            case THROW: return THROW_LITERAL;
            case RECORD: return RECORD_LITERAL;
            case DISCARD: return DISCARD_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private ChoiceErrorMode(int value, String name) {
        super(value, name);
    }

} //ChoiceErrorMode
