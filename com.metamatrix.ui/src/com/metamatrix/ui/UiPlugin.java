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
package com.metamatrix.ui;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.osgi.framework.BundleContext;
import com.metamatrix.core.PluginUtil;
import com.metamatrix.core.util.PluginUtilImpl;
import com.metamatrix.ui.actions.AbstractActionService;
import com.metamatrix.ui.actions.ActionService;
import com.metamatrix.ui.graphics.GlobalUiColorManager;
import com.metamatrix.ui.graphics.GlobalUiFontManager;
import com.metamatrix.ui.internal.InternalUiConstants;

/**
 * The main plugin class to be used in the desktop.
 * 
 * @since 4.0
 */
public final class UiPlugin extends AbstractUiPlugin implements InternalUiConstants, UiConstants.EclipsePluginIds {

    // The shared instance.
    private static UiPlugin plugin;

    /**
     * Returns the shared instance.
     * 
     * @since 4.0
     */
    public static UiPlugin getDefault() {
        return UiPlugin.plugin;
    }

    /**
     * The constructor.
     * 
     * @since 4.0
     */
    public UiPlugin() {
        UiPlugin.plugin = this;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start( BundleContext context ) throws Exception {
        super.start(context);

        // Initialize logging/i18n utility
        ((PluginUtilImpl)Util).initializePlatformLogger(this);

        // Load Eclipse UI images into registry
        final Runnable op = new Runnable() {
            public void run() {
                registerPluginImage(UI_IDE, Images.CHECKED_CHECKBOX);
                registerPluginImage(UI_IDE, Images.UNCHECKED_CHECKBOX);
                registerPluginImage(UI_IDE, Images.REFRESH);
                registerPluginImage(UI, Images.TASK_ERROR);
                registerPluginImage(UI, Images.TASK_WARNING);
                registerPluginImage(UI, Images.TASK_INFO);
            }
        };
        if (Display.getCurrent() == null) {
            Display.getDefault().asyncExec(op);
        } else {
            op.run();
        }

    }

    /**
     * @see com.metamatrix.ui.AbstractUiPlugin#createActionService(org.eclipse.ui.IWorkbenchPage)
     * @since 4.0
     */
    @Override
    protected ActionService createActionService( IWorkbenchPage page ) {
        return new UiActionService(page);
    }

    /**
     * @see com.metamatrix.ui.AbstractUiPlugin#getPluginUtil()
     * @since 4.0
     */
    @Override
    public PluginUtil getPluginUtil() {
        return Util;
    }

    /**
     * @since 4.0
     */
    private class UiActionService extends AbstractActionService {

        UiActionService( IWorkbenchPage page ) {
            super(getDefault(), page);
        }

        /**
         * @see com.metamatrix.ui.actions.AbstractActionService#getDefaultAction(java.lang.String)
         */
        public IAction getDefaultAction( String theActionId ) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.metamatrix.ui.AbstractUiPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop( BundleContext context ) throws Exception {
        // This plugin contains the ModelerUiColorManager so it needs to be told to dispose of all it's cached colors
        GlobalUiColorManager.dispose();
        GlobalUiFontManager.dispose();
        super.stop(context);
    }
}
