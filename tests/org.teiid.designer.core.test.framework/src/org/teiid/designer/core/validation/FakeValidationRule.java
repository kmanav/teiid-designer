/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.validation;

import org.eclipse.emf.ecore.EObject;
import org.teiid.designer.core.util.DateUtil;


/**
 * FakeValidationRule
 */
public class FakeValidationRule implements ObjectValidationRule {
    public int code;
    public int severity;
    public String message;
    public boolean isFatal;
    public boolean addResult;
    
    public FakeValidationRule(int code, int severity, String message, boolean isFatal, boolean addResult) {
        this.code      = code;
        this.severity  = severity;
        this.message   = message;
        this.addResult = addResult;
        this.isFatal = isFatal;
    }

    @Override
	public void validate(EObject eObject, ValidationContext context) {
        if (addResult) {
            final String timeStampedMessage   = message + " " + DateUtil.getCurrentDateAsString(); //$NON-NLS-1$
            final ValidationProblem problem   = new ValidationProblemImpl(code,severity,timeStampedMessage);
            final ValidationResultImpl result = new ValidationResultImpl(eObject);
            result.setFatalResource(isFatal);
            
            result.addProblem(problem);
            context.addResult(result);
        }
    }

}
