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

package com.metamatrix.metamodels.xml.aspects.validation;

import org.eclipse.emf.ecore.EObject;

import com.metamatrix.core.util.ArgCheck;
import com.metamatrix.core.util.StringUtil;
import com.metamatrix.metamodels.xml.XmlContainerNode;
import com.metamatrix.metamodels.xml.XmlDocumentNode;
import com.metamatrix.modeler.core.ValidationDescriptor;
import com.metamatrix.modeler.core.ValidationPreferences;
import com.metamatrix.modeler.core.metamodel.aspect.MetamodelEntity;
import com.metamatrix.modeler.core.validation.ValidationContext;


/** 
 * @since 4.2
 */
public abstract class AbstractXmlNodeAspect extends AbstractXmlAspect {

    /** 
     * @param entity
     * @since 4.2
     */
    public AbstractXmlNodeAspect(MetamodelEntity entity) {
        super(entity);
    }

    /** 
     * @see com.metamatrix.modeler.core.metamodel.aspect.ValidationAspect#shouldValidate(org.eclipse.emf.ecore.EObject)
     * @since 4.2
     */
    @Override
    public boolean shouldValidate(EObject eObject, final ValidationContext context) {
        ArgCheck.isNotNull(eObject);
        String validationPref = context.getPreferenceValue(ValidationPreferences.XML_ELEMENT_VALIDATE_EXCLUDED);
        
        // If we are not validating excluded elements then return false for all EObjects
        // that are marked as excluded in the document
        if (StringUtil.isEmpty(validationPref) || validationPref.equals(ValidationDescriptor.FALSE)) {
	        if(!context.shouldIgnore(eObject)) {
	            // check if the eObject is excluded
	            if(isExcluded(eObject, context)) {
	                return false;
	            }
	            // check if any of the parents of the EObject are excluded
	            EObject container = eObject.eContainer();
	            while(container != null) {
	                if(isExcluded(container, context)) {
	                    return false;
	                }
	                container = container.eContainer();
	            }
	            // neither Eobject nor its parents are excluded
	            // so validate
		        return true;
	        }
	        // already ignored do not validate
	        return false;	        
        }
        // preferences say validate
        return true;
    }

    /**
     * Check if the documentNode is excluded and add itseld and its children to the context to
     * be ignored during validation.
     * @since 4.2
     */
    private boolean isExcluded(final EObject eObject, final ValidationContext context) {
        if(eObject != null) {
            boolean excluded = false;
	        if(eObject instanceof XmlDocumentNode) {
	            excluded = ((XmlDocumentNode)eObject).isExcludeFromDocument();
	        } else if(eObject instanceof XmlContainerNode) {
	            excluded = ((XmlContainerNode)eObject).isExcludeFromDocument();
	        }
	        if(excluded) {
	            context.addObjectToIgnore(eObject, true);
	            return true;   
	        }
    	}
        return false;
    }    
}
