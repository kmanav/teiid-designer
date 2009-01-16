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

package com.metamatrix.metamodels.function.aspects.validation;

import org.eclipse.emf.ecore.EClassifier;

import com.metamatrix.metamodels.function.FunctionPackage;
import com.metamatrix.metamodels.function.FunctionPlugin;
import com.metamatrix.modeler.core.metamodel.aspect.MetamodelAspect;
import com.metamatrix.modeler.core.metamodel.aspect.MetamodelAspectFactory;
import com.metamatrix.modeler.core.metamodel.aspect.MetamodelEntity;

/**
 * FunctionValidationAspectFactoryImpl
 */
public class FunctionValidationAspectFactoryImpl implements MetamodelAspectFactory {

    /*
     * @see com.metamatrix.modeler.core.metamodel.aspect.MetamodelAspectFactory#create(org.eclipse.emf.ecore.EClassifier, com.metamatrix.modeler.core.metamodel.aspect.MetamodelEntity)
     */
    public MetamodelAspect create(EClassifier classifier, MetamodelEntity entity) {
        switch (classifier.getClassifierID()) {
            case FunctionPackage.FUNCTION:
            case FunctionPackage.SCALAR_FUNCTION:       return new ScalarFunctionAspect(entity);
            case FunctionPackage.FUNCTION_PARAMETER:    return new FunctionParameterAspect(entity);
            case FunctionPackage.RETURN_PARAMETER:      return new ReturnParameterAspect(entity);
            default:
                throw new IllegalArgumentException(FunctionPlugin.Util.getString("FunctionValidationAspectFactoryImpl.Invalid_classifer_ID,_for_creating_validation_aspect__1")+classifier); //$NON-NLS-1$
        }
    }

}
