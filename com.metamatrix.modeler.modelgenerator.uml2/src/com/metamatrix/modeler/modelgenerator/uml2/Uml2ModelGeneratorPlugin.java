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

package com.metamatrix.modeler.modelgenerator.uml2;

import java.util.ResourceBundle;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import com.metamatrix.core.PluginUtil;
import com.metamatrix.core.util.PluginUtilImpl;
import com.metamatrix.modeler.modelgenerator.uml2.processor.Uml2RelationalGeneratorImpl;

/**
 * The main plugin class to be used in the desktop.
 */
public class Uml2ModelGeneratorPlugin extends Plugin {
    /**
     * The plug-in identifier of this plugin
     * (value <code>"com.metamatrix.modeler.modelgenerator.uml2"</code>).
     */
    public static final String PLUGIN_ID = "com.metamatrix.modeler.modelgenerator.uml2" ; //$NON-NLS-1$

    /**
     * Provides access to the plugin's log and to it's resources.
     */
    private static final String BUNDLE_NAME = PLUGIN_ID + ".i18n"; //$NON-NLS-1$
    public static final PluginUtil Util = new PluginUtilImpl(PLUGIN_ID,BUNDLE_NAME,ResourceBundle.getBundle(BUNDLE_NAME));

    public static boolean DEBUG = false;


	//The shared instance.
	private static Uml2ModelGeneratorPlugin plugin;

	/**
	 * The constructor.
	 */
	public Uml2ModelGeneratorPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	@Override
    public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	@Override
    public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 */
	public static Uml2ModelGeneratorPlugin getDefault() {
		return plugin;
	}

    /**
     * Create a new {@link Uml2RelationalGenerator} instance.
     * @return
     */
    public static Uml2RelationalGenerator createUml2RelationalGenerator() {
        return new Uml2RelationalGeneratorImpl();
    }

}
