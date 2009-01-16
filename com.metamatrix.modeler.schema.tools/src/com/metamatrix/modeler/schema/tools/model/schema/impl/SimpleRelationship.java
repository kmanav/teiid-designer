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

package com.metamatrix.modeler.schema.tools.model.schema.impl;

import com.metamatrix.modeler.schema.tools.model.schema.SchemaObject;

public class SimpleRelationship extends BaseRelationship {
    private SchemaObject parent; // a parent of null means the child can be
    // global

    private SchemaObject child;

    // Relates to the cardinality that the child appears with inside the parent
    private int minOccurs;

    private int maxOccurs;

    public SimpleRelationship( SchemaObject parent,
                               SchemaObject child,
                               int minOccurs,
                               int maxOccurs ) {
        this.parent = parent;
        this.child = child;
        this.minOccurs = minOccurs;
        this.maxOccurs = maxOccurs;
    }

    public String getParentRelativeXpath() {
        return ".."; //$NON-NLS-1$
    }

    public String getChildRelativeXpath() {
        String xpath = child.getRelativeXpath();
        return xpath;
    }

    public SchemaObject getParent() {
        return parent;
    }

    public SchemaObject getChild() {
        return child;
    }

    public int getMinOccurs() {
        return minOccurs;
    }

    public int getMaxOccurs() {
        return maxOccurs;
    }

    public void setMinOccurs( int minOccurs ) {
        this.minOccurs = minOccurs;
    }

    public void setMaxOccurs( int maxOccurs ) {
        this.maxOccurs = maxOccurs;
    }

    @Override
    public String toString() {
        String childName = getChild().getSimpleName();
        String parentName = getParent().getSimpleName();
        return parentName + "_" + childName; //$NON-NLS-1$
    }

    public Object copy( SchemaModelCopyTraversalContext ctx ) {
        SchemaObject parentCopy = ctx.getElement(parent);
        SchemaObject childCopy = ctx.getElement(child);
        return new SimpleRelationship(parentCopy, childCopy, minOccurs, maxOccurs);
    }

    public void printDebug() {
        StringBuffer buff = new StringBuffer();
        buff.append("\t \t SimpleRelationship:"); //$NON-NLS-1$
        buff.append("ParentSimpleName = " + parent.getSimpleName()); //$NON-NLS-1$
        buff.append(" "); //$NON-NLS-1$
        buff.append("ChildSimpleName = " + child.getSimpleName()); //$NON-NLS-1$
        buff.append(" "); //$NON-NLS-1$
        buff.append("MinOccurs = " + minOccurs); //$NON-NLS-1$
        buff.append(" "); //$NON-NLS-1$
        buff.append("MaxOccurs = " + maxOccurs); //$NON-NLS-1$
        buff.append(" "); //$NON-NLS-1$
        buff.append("Type = " + getType()); //$NON-NLS-1$
        System.out.println(buff.toString());
    }

    @Override
    public void setType( int relationshipType ) {
        super.setType(relationshipType);
    }

    @Override
    public int getType() {
        return super.getType();
    }
}
