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

package com.metamatrix.modeler.core.compare;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import com.metamatrix.metamodels.core.CorePackage;

/**
 * CoreMatcherFactory
 */
public class CoreMatcherFactory implements EObjectMatcherFactory {

    /**
     * Construct an instance of CoreMatcherFactory.
     * 
     */
    public CoreMatcherFactory() {
        super();
    }

    /**
     * @see com.metamatrix.modeler.core.compare.EObjectMatcherFactory#createEObjectMatchersForRoots()
     */
    public List createEObjectMatchersForRoots() {
        // Create the appropriate matchers ...
        final List results = new LinkedList();
        results.add( new CoreRootObjectMatcher() );
        return results;
    }

    /**
     * @see com.metamatrix.modeler.core.compare.EObjectMatcherFactory#createEObjectMatchers(org.eclipse.emf.ecore.EReference)
     */
    public List createEObjectMatchers(final EReference reference) {
        // Make sure the reference is in the Core metamodel ...
        final EClass containingClass = reference.getEContainingClass();
        final EPackage metamodel = containingClass.getEPackage();
        if ( !CorePackage.eINSTANCE.equals(metamodel) ) {
            return Collections.EMPTY_LIST;
        }
        
        // Create the appropriate matchers ...
        final List results = new LinkedList();
        final int featureId = reference.getFeatureID();
        switch( featureId ) {
            case CorePackage.ANNOTATION_CONTAINER__ANNOTATIONS:
                results.add( new CoreAnnotationMatcher() );
                break;
            case CorePackage.MODEL_ANNOTATION__MODEL_IMPORTS:
                results.add( new CoreModelImportMatcher() );
        }
        
        return results;
    }

}
