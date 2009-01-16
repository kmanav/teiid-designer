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

package com.metamatrix.modeler.mapping.ui.actions;

import java.util.List;

import com.metamatrix.modeler.mapping.ui.UiConstants;


/** 
 * @since 5.0
 */
public class SetIncludeInDocumentSpecialAction extends SetExcludeFromDocumentSpecialAction {
    
    //============================================================================================================================
    // Constants

    private static String INCLUDE_TITLE 
        = UiConstants.Util.getString( "SetIncludeExcludeAction.Include.title" );  //$NON-NLS-1$
    private static String INCLUDE_TOOLTIP 
        = UiConstants.Util.getString( "SetIncludeExcludeAction.Include.tooltip" );  //$NON-NLS-1$
    
    /** 
     * 
     * @since 5.0
     */
    public SetIncludeInDocumentSpecialAction() {
        super();
        this.setText( INCLUDE_TITLE );
        this.setToolTipText( INCLUDE_TOOLTIP );
        
        // What distinguishes this 'set included' action is that it sets the 'excluded state'
        //  of the selelcted xml document nodes to 'false'.  (Not Excluded = Included.)
        setDefaultExcludeState( false );
    }
    
    // This method is called by determineEnablement().
    @Override
    protected boolean isSelectionAppropriate( List lstSelectedEObjects ) {
        int iSelectionType = getXmlDocNodeSelectionType( lstSelectedEObjects );
        
        if ( iSelectionType == this.ALL_EXCLUDED 
          || iSelectionType == this.BOTH_INCLUDED_AND_EXCLUDED ) {
            return true;
        }
        return false;
    }
}
