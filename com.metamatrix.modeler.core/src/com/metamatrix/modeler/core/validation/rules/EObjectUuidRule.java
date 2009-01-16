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

package com.metamatrix.modeler.core.validation.rules;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import com.metamatrix.core.util.ArgCheck;
import com.metamatrix.metamodels.core.ModelAnnotation;
import com.metamatrix.modeler.core.ModelEditor;
import com.metamatrix.modeler.core.ModelerCore;
import com.metamatrix.modeler.core.ValidationPreferences;
import com.metamatrix.modeler.core.validation.ObjectValidationRule;
import com.metamatrix.modeler.core.validation.ValidationContext;
import com.metamatrix.modeler.core.validation.ValidationProblem;
import com.metamatrix.modeler.core.validation.ValidationResult;
import com.metamatrix.modeler.internal.core.validation.ValidationProblemImpl;
import com.metamatrix.modeler.internal.core.validation.ValidationResultImpl;


/**
 * @since 4.2
 */
public class EObjectUuidRule implements ObjectValidationRule {

    /**
     * @see com.metamatrix.modeler.core.validation.ObjectValidationRule#validate(org.eclipse.emf.ecore.EObject, com.metamatrix.modeler.core.validation.ValidationContext)
     * @since 4.2
     */
    public void validate(final EObject eObject, final ValidationContext context) {
        ArgCheck.isNotNull(context);
        ArgCheck.isNotNull(eObject);

        // get uniqueness preference status
        final int status = context.getPreferenceStatus(ValidationPreferences.EOBJECT_UUID_UNIQUENESS, IStatus.ERROR);
	    if (status == IStatus.OK) {
	        return;
	    }

        // if the eObject is a model annotation then
        // is already being validation by ModelAnnoattionUuidRule
        if(eObject instanceof ModelAnnotation) {
            return;
        }

        final ModelEditor editor = ModelerCore.getModelEditor();
        final String uuidString = ModelerCore.getObjectIdString(eObject);
		if (uuidString == null) {
            final String pathInModel = editor.getModelRelativePathIncludingModel(eObject).toString();
			final ValidationResult result = new ValidationResultImpl(eObject);
			// create validation problem and add it to the result
			final ValidationProblem problem  = new ValidationProblemImpl(0, IStatus.ERROR, ModelerCore.Util.getString("EObjectUuidRule.0", pathInModel)); //$NON-NLS-1$
			problem.setHasPreference(context.hasPreferences());
			result.addProblem(problem);
			context.addResult(result);
        } else {
	        boolean isDuplicate = context.containsUuid(uuidString);
	        if(isDuplicate) {
	            final String pathInModel = editor.getModelRelativePathIncludingModel(eObject).toString();
				final ValidationResult result = new ValidationResultImpl(eObject);
				String modelName = editor.getModelName(eObject);
				// create validation problem and addit to the result
				final ValidationProblem problem  = new ValidationProblemImpl(0, status, ModelerCore.Util.getString("EObjectUuidRule.1", pathInModel, uuidString, modelName)); //$NON-NLS-1$
				problem.setHasPreference(context.hasPreferences());
				result.addProblem(problem);
				context.addResult(result);
	        } else {
	            context.addUuidToContext(uuidString);
	        }
        }
    }
}
