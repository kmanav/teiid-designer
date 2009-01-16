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

package com.metamatrix.metamodels.relational.aspects.validation.rules;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.metamatrix.metamodels.relational.AccessPattern;
import com.metamatrix.metamodels.relational.Catalog;
import com.metamatrix.metamodels.relational.Column;
import com.metamatrix.metamodels.relational.ForeignKey;
import com.metamatrix.metamodels.relational.Index;
import com.metamatrix.metamodels.relational.LogicalRelationship;
import com.metamatrix.metamodels.relational.Procedure;
import com.metamatrix.metamodels.relational.ProcedureParameter;
import com.metamatrix.metamodels.relational.Schema;
import com.metamatrix.metamodels.relational.Table;
import com.metamatrix.metamodels.relational.UniqueKey;
import com.metamatrix.metamodels.relational.util.RelationalUtil;
import com.metamatrix.modeler.core.util.ModelVisitorProcessor;
import com.metamatrix.modeler.core.validation.rules.StringNameRule;

/**
 * RelationalStringNameRule
 */
public class RelationalStringNameRule extends StringNameRule {

    /**
     * Construct an instance of RelationalStringNameRule.
     * @param invalidChars
     * @param featureID
     */
    public RelationalStringNameRule(char[] invalidChars, int featureID) {
        super(invalidChars, featureID);
    }

    /**
     * Construct an instance of RelationalStringNameRule.
     * @param featureID
     */
    public RelationalStringNameRule(int featureID) {
        super(featureID);
    }
    
    /**
     * This method groups siblings into the following domains, and chooses only those siblings that are in
     * the same domain as the supplied object.
     * <ul>
     *  <li>{@link Catalog} and {@link Schema} instances</li>
     *  <li>{@link BaseTable} and {@link View} instances</li>
     *  <li>{@link Procedure} instances</li>
     *  <li>{@link ProcedureColumn} instances</li>
     *  <li>{@link AccessPattern} instances</li>
     *  <li>{@link UniqueKey}, {@link ForeignKey} and {@link Index} instances</li>
     *  <li></li>
     *  <li></li>
     * </ul>
     * @see com.metamatrix.modeler.core.validation.rules.StringNameRule#getSiblingsForUniquenessCheck(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public List getSiblingsForUniquenessCheck(final EObject eObject) {
        Object parent = eObject.eContainer();
        if ( parent == null ) {
            parent = eObject.eResource();
        }
        if ( eObject instanceof Table ) {
            return RelationalUtil.findTables(parent, ModelVisitorProcessor.DEPTH_ONE);
        } else if ( eObject instanceof Procedure ) {
            return RelationalUtil.findProcedures(parent, ModelVisitorProcessor.DEPTH_ONE);
        } else if ( eObject instanceof ProcedureParameter ) {
            return RelationalUtil.findProcedureParameters(parent, ModelVisitorProcessor.DEPTH_ONE);
        } else if ( eObject instanceof Index ) {
            return RelationalUtil.findIndexes(parent, ModelVisitorProcessor.DEPTH_ONE);
        } else if ( eObject instanceof ForeignKey || eObject instanceof UniqueKey ) {
            return RelationalUtil.findKeys(parent, ModelVisitorProcessor.DEPTH_ONE);
        } else if ( eObject instanceof Column ) {
            return RelationalUtil.findColumns(parent, ModelVisitorProcessor.DEPTH_ONE);
        } else if ( eObject instanceof AccessPattern ) {
            if ( parent instanceof Table ) {
                return ((Table)parent).getAccessPatterns();
            }
            return new LinkedList();
        } else if ( eObject instanceof LogicalRelationship ) {
            return RelationalUtil.findLogicalRelationships(parent, ModelVisitorProcessor.DEPTH_ONE);
        } else if( eObject instanceof Schema ) {
            return RelationalUtil.findSchemas(parent, ModelVisitorProcessor.DEPTH_ONE);
        } else if( eObject instanceof Catalog ) {
            return RelationalUtil.findCatalogs(parent, ModelVisitorProcessor.DEPTH_ONE);
        }
        return super.getSiblingsForUniquenessCheck(eObject);
    }

}
