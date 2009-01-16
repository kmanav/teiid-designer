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

package com.metamatrix.metamodels.uml2.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Package;

/**
 * AssociationFinder
 */
public class DependencyFinder extends UmlEntityFinder {

    /**
     * Construct an instance of AssociationFinder.
     */
    public DependencyFinder() {
        super();
    }

    /**
     * @see com.metamatrix.modeler.core.util.ModelVisitor#visit(org.eclipse.emf.ecore.EObject)
     */
    public boolean visit( final EObject object ) {
        if (object instanceof Dependency) {
            found(object);
            return false;
        }
        // Dependencies are contained by Packages
        if (object instanceof Package) {
            return true;
        }
        return false;
    }

}
