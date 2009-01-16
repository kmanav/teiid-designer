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

package com.metamatrix.query.internal.ui.sqleditor.component;

import java.util.ArrayList;

import com.metamatrix.query.sql.ReservedWords;
import com.metamatrix.query.sql.lang.NotCriteria;

/**
 * The <code>NotCriteriaDisplayNode</code> class is used to represent a Not Criteria.
 */
public class NotCriteriaDisplayNode extends AtomicCriteriaDisplayNode {

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////

    /**
     *   NotCriteriaDisplayNode constructor
     *  @param parentNode the parent DisplayNode of this.
     *  @param criteria the query language object used to construct this display node.
     */
    public NotCriteriaDisplayNode(DisplayNode parentNode, NotCriteria criteria) {
        this.parentNode = parentNode;
        this.languageObject = criteria;
        createChildNodes();
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public boolean supportsCriteria() {
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////
    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////

    /**
     *   Create the child nodes for this type of DisplayNode.
     */
    private void createChildNodes() {
        childNodeList = new ArrayList();
        //int indent = this.getIndentLevel();

        NotCriteria notCriteria = (NotCriteria)this.getLanguageObject();

        // Single Child - Criteria
        if(notCriteria!=null) {
            childNodeList.add(DisplayNodeFactory.createDisplayNode(this,notCriteria.getCriteria()));
        } else {
            childNodeList.add(DisplayNodeFactory.createDisplayNode(this,"NULL")); //$NON-NLS-1$
        }

        // Build the Display Node List
        createDisplayNodeList();
    }

    /**
     *   Create the DisplayNode list for this type of DisplayNode.  This is a list of
     *  all the lowest level nodes for this DisplayNode.
     */
    private void createDisplayNodeList() {
        displayNodeList = new ArrayList();
        //int indent = this.getIndentLevel();

        displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,ReservedWords.NOT));
        displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,SPACE+LTPAREN));

        if(childNodeList.size()>0) {
            DisplayNode child = (DisplayNode)childNodeList.get(0);
            //indent = child.getIndentLevel();
            if( child.hasDisplayNodes() ) {
                displayNodeList.addAll(child.getDisplayNodeList());
            } else {
                displayNodeList.add(child);
            }
        }

        displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,RTPAREN));
	}

}

