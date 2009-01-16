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

package com.metamatrix.modeler.internal.ui.viewsupport;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;

import com.metamatrix.modeler.ui.UiConstants;
import com.metamatrix.modeler.ui.actions.IModelObjectEditHelper;

/**
 * @author BLaFond
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class ModelObjectEditHelperManager {
	
	/** Array of all extensions to the DiagramHelper extension point */
	private static HashMap editHelpers;
	private static boolean helpersLoaded = false;

	public static boolean canRename(Object obj) {
		if( !helpersLoaded )
            loadHelperExtensions();
			
		boolean oK = true;
		Iterator iter = editHelpers.values().iterator();
		IModelObjectEditHelper helper = null;
		while( iter.hasNext() && oK) {
			helper = (IModelObjectEditHelper)iter.next();
			oK = helper.canRename(obj);
		}
		
		return oK;
	}

	public static boolean canCopy(Object obj) {
		if( !helpersLoaded )
            loadHelperExtensions();
			
		boolean oK = true;
		Iterator iter = editHelpers.values().iterator();
		IModelObjectEditHelper helper = null;
		while( iter.hasNext() && oK) {
			helper = (IModelObjectEditHelper)iter.next();
			oK = helper.canCopy(obj);
		}
		
		return oK;
	}

	public static boolean canCut(Object obj) {
		if( !helpersLoaded )
            loadHelperExtensions();
			
		boolean oK = true;
		Iterator iter = editHelpers.values().iterator();
		IModelObjectEditHelper helper = null;
		while( iter.hasNext() && oK) {
			helper = (IModelObjectEditHelper)iter.next();
			oK = helper.canCut(obj);
		}
		
		return oK;
	}

    public static boolean canUndoCut(Object obj) {
        /*
         * Defect 17739: disallow this operation if the selection includes tables 
         *               "with a dependent FK/PK relationships"
         *               Should 'obj' be an array of tables for this to make sense?
         *               Yes!
         */
        if( !helpersLoaded )
            loadHelperExtensions();
            
        boolean oK = true;
        Iterator iter = editHelpers.values().iterator();
        IModelObjectEditHelper helper = null;
        while( iter.hasNext() && oK) {
            helper = (IModelObjectEditHelper)iter.next();            
            
            oK = helper.canUndoCut(obj);
        }
        
        return oK;
    }

	public static boolean canDelete(Object obj) {
		if( !helpersLoaded )
            loadHelperExtensions();
			
		boolean oK = true;
		Iterator iter = editHelpers.values().iterator();
		IModelObjectEditHelper helper = null;
		while( iter.hasNext() && oK) {
			helper = (IModelObjectEditHelper)iter.next();
			oK = helper.canDelete(obj);
		}
		
		return oK;
	}

    public static boolean canUndoDelete(Object obj) {
        if( !helpersLoaded )
            loadHelperExtensions();
            
        boolean oK = true;
        Iterator iter = editHelpers.values().iterator();
        IModelObjectEditHelper helper = null;
        while( iter.hasNext() && oK) {
            helper = (IModelObjectEditHelper)iter.next();
            oK = helper.canUndoDelete(obj);
        }
        
        return oK;
    }
    
	public static boolean canClone(Object obj) {
		if( !helpersLoaded )
            loadHelperExtensions();
			
		boolean oK = true;
		Iterator iter = editHelpers.values().iterator();
		IModelObjectEditHelper helper = null;
		while( iter.hasNext() && oK) {
			helper = (IModelObjectEditHelper)iter.next();
			oK = helper.canClone(obj);
		}
		
		return oK;
	}
	
    
	public static boolean canPaste(Object obj, Object pasteParent) {
		if( !helpersLoaded )
            loadHelperExtensions();
			
		boolean oK = true;
		Iterator iter = editHelpers.values().iterator();
		IModelObjectEditHelper helper = null;
		while( iter.hasNext() && oK) {
			helper = (IModelObjectEditHelper)iter.next();
			oK = helper.canPaste(obj, pasteParent);
		}
		
		return oK;
	}

	private static void loadHelperExtensions() {
		editHelpers = new HashMap();
		helpersLoaded = true;
		
		// get the extension point from the plugin class
		String id = UiConstants.ExtensionPoints.ModelObjectEditHelperExtension.ID;
		String classTag = UiConstants.ExtensionPoints.ModelObjectEditHelperExtension.CLASS;
		String className = UiConstants.ExtensionPoints.ModelObjectEditHelperExtension.CLASSNAME;
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(id);
		
		// get the all extensions to the NewChildAction extension point
		IExtension[] extensions = extensionPoint.getExtensions();
		
		// walk through the extensions and find all INewChildAction implementations
		for ( int i=0 ; i<extensions.length ; ++i ) {
			IConfigurationElement[] elements = extensions[i].getConfigurationElements();
			try {

				// first, find the content provider instance and add it to the instance list
				for ( int j=0 ; j<elements.length ; ++j ) {
					if ( elements[j].getName().equals(classTag)) {
						Object helper = elements[j].createExecutableExtension(className);
						if ( helper instanceof IModelObjectEditHelper ) {
                            
                            String sThisClassName = elements[j].getAttribute( className ); 
							editHelpers.put( sThisClassName, helper );                            
						}
					}
				}
            
			} catch (Exception e) {
				// catch any Exception that occurred obtaining the configuration and log it
				String message = UiConstants.Util.getString("ModelerActionService.configurationErrorMessage", //$NON-NLS-1$
							extensions[i].getUniqueIdentifier()); 
				UiConstants.Util.log(IStatus.ERROR, e, message);
			}
		}
	}



}
