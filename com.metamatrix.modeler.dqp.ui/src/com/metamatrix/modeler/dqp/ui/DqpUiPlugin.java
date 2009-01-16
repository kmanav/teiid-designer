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

package com.metamatrix.modeler.dqp.ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.osgi.framework.BundleContext;

import com.metamatrix.core.PluginUtil;
import com.metamatrix.core.util.I18nUtil;
import com.metamatrix.core.util.PluginUtilImpl;
import com.metamatrix.modeler.dqp.ui.connection.IVdbConnectionMgr;
import com.metamatrix.modeler.dqp.ui.workspace.WorkspaceExecutor;
import com.metamatrix.modeler.internal.dqp.ui.config.ConnectorBindingsPanel;
import com.metamatrix.modeler.internal.dqp.ui.connection.SqlExplorerConnectionMgr;
import com.metamatrix.ui.AbstractUiPlugin;
import com.metamatrix.ui.actions.ActionService;


/**
 * The main plugin class to be used in the desktop.
 */
public class DqpUiPlugin extends AbstractUiPlugin
                         implements DqpUiConstants{
    
    private static final String PREFIX = I18nUtil.getPropertyPrefix(ConnectorBindingsPanel.class);
    
    private static String getString(String theKey) {
        return UTIL.getStringOrKey(PREFIX + theKey);
    }

    //The shared instance.
	private static DqpUiPlugin plugin;
	/**
	 * Returns the shared instance.
	 */
	public static DqpUiPlugin getDefault() {
		return plugin;
	}

    public static void showErrorDialog(Shell shell, Exception error) {
        MessageDialog.openError(shell, getString("errorDialogTitle"), error.getMessage()); //$NON-NLS-1$
    }
    
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
    //The Vdb Editor Util instance for this plugin
    private IVdbEditorUtil vdbEditorUtil;
    
    /**
     * The VDB connection manager. Access through the getter.
     * @since 5.0
     * @see #getVdbConnectionMgr()
     */
    private IVdbConnectionMgr connMgr;
  
	/**
	 * The constructor.
	 */
	public DqpUiPlugin() {
		plugin = this;
	}

    /**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = DqpUiPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

    @Override
    protected ActionService createActionService(IWorkbenchPage workbenchPage) {
        return null;
    }
    
    @Override
    public PluginUtil getPluginUtil() {
        return UTIL;
    }

    public Image getAnImage(String key) {
        return getOrCreateImage(key);
    }
	
    private Image getOrCreateImage(String key) {
        Image image = getImageRegistry().get(key);
        if(image == null) {
            ImageDescriptor d = getImageDescriptor(key);

            // make sure we still need to put in registry (above call
            //  seems to be registering the image):
            image = getImageRegistry().get(key);
            if (image == null) {
                image = d.createImage();
                getImageRegistry().put(key, image);
            } // endif
        }
        return image;
    }

    public IVdbEditorUtil getVdbEditorUtil() {
        if ( vdbEditorUtil == null ) {
        
            // look for any extensions to VdbEditorUtil

            IExtensionPoint extensionPoint 
                = Platform.getExtensionRegistry().getExtensionPoint(PLUGIN_ID, ExtensionPoints.VdbEditorUtil.ID);
            
            // get the all extensions to the MappingClassStrategies extension point
            IExtension[] extensions = extensionPoint.getExtensions();
            
            // if there is an extension, use it
            if ( extensions.length > 0 ) { 
                IConfigurationElement[] elements = extensions[0].getConfigurationElements();
                Object extension = null;
                for ( int j = 0; j < elements.length; j++ ) {
                    try {

                        extension = elements[ j ].createExecutableExtension( ExtensionPoints.VdbEditorUtil.CLASSNAME );

                        if (extension instanceof IVdbEditorUtil) {
                            this.vdbEditorUtil = (IVdbEditorUtil) extension;
                        }
                    } catch (Exception theException) {
                        UTIL.log(theException);
                    }
                }
            }

            // if no extension was found, implement the default
            if ( vdbEditorUtil == null ) {
                vdbEditorUtil =  new DefaultVdbEditorUtil();
            }
        
        }
        return vdbEditorUtil;
    }

    /**
     * Obtains the VDB connection manager. 
     * @return the manager
     * @since 5.0
     */
    public IVdbConnectionMgr getVdbConnectionMgr() {
        if (this.connMgr == null) {
            this.connMgr = new SqlExplorerConnectionMgr();
        }
        
        return this.connMgr;
    }
    
    /** 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     * @since 5.0
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        // Initialize logging/i18n/debugging utility
        ((PluginUtilImpl)UTIL).initializePlatformLogger(this);

            // start workspace execution
        WorkspaceExecutor.getInstance().start();
    }
    
	@Override
    public void stop(BundleContext theContext) throws Exception {
        WorkspaceExecutor.getInstance().stop();
        super.stop(theContext);
    }
}
