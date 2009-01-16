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

package com.metamatrix.modeler.dqp.ui.connection;

import com.metamatrix.vdb.edit.VdbContextEditor;
import com.metamatrix.vdb.edit.VdbEditingContext;

/**
 * @since 5.0
 */
public interface IVdbConnectionMgr {

    /**
     * Closes all existing VDB connections.
     * 
     * @since 5.0
     */
    void closeAllConnections();

    /**
     * Closes the connection for the specified <code>VdbEditingContext</code>.
     * 
     * @param theVdbContext the context whose connection is being closed
     * @return <code>true</code> if connection successfully closed or if no connection was open; <code>false</code> otherwise.
     * @since 5.0
     */
    boolean closeConnection( VdbEditingContext theVdbContext );

    /**
     * Closes the connection for the specified <code>VdbContextEditor</code>.
     * 
     * @param theVdbContext the context whose connection is being closed
     * @return <code>true</code> if connection successfully closed or if no connection was open; <code>false</code> otherwise.
     * @since 5.0
     */
    boolean closeConnection( VdbContextEditor theVdbContext );

    /**
     * Obtains the name of the specified connection.
     * 
     * @param theConnection the connection whose name is being requested
     * @return the name (never <code>null</code>)
     * @since 5.0.1
     */
    String getConnectionName( Object theConnection );

    /**
     * Obtains the <code>VdbEditingContext</code> for the specified connection.
     * 
     * @param theConnection the connection whose VDB context is being requested
     * @return the context or <code>null</code> if not found
     * @since 5.0.1
     */
    VdbEditingContext getVdbEditingContext( Object theConnection );

    /**
     * Obtains the <code>VdbContextEditor</code> for the specified connection.
     * 
     * @param theConnection the connection whose VDB context is being requested
     * @return the context or <code>null</code> if not found
     * @since 5.0.1
     */
    VdbContextEditor getVdbContextEditor( Object theConnection );

    /**
     * Indicates if the specified connection is open.
     * 
     * @param theConnection the connection being checked
     * @return <code>true</code> if open; <code>false</code> otherwise.
     * @since 5.0
     */
    boolean isConnectionOpen( Object theConnection );

    /**
     * Indicates if the specified connection is stale.
     * 
     * @param theConnection the connection being checked
     * @return <code>true</code> if the connection is stale; <code>false</code> if no connection exists or connection is
     *         up-to-date.
     * @since 5.0.1
     */
    boolean isConnectionStale( Object theConnection );

    /**
     * Indicates if a connection for the specified <code>VdbEditingContext</code> is open.
     * 
     * @param theVdbContext the context being checked
     * @return <code>true</code> if a connection is open; <code>false</code> otherwise.
     * @since 5.0
     * @deprecated use method that takes VdbContextEditor as the argument
     */
    @Deprecated
    boolean isVdbConnectionOpen( VdbEditingContext theVdbContext );

    /**
     * Indicates if a connection for the specified <code>VdbContextEditor</code> is open.
     * 
     * @param theVdbContext the context being checked
     * @return <code>true</code> if a connection is open; <code>false</code> otherwise.
     * @since 5.0
     */
    boolean isVdbConnectionOpen( VdbContextEditor theVdbContext );

    /**
     * Indicates if the current connection for the specified <code>VdbEditingContext</code> does not represent the latest VDB
     * state.
     * 
     * @param theVdbContext the context whose connection is being checked
     * @return <code>true</code> if the connection is stale; <code>false</code> if no connection exists or connection is
     *         up-to-date.
     * @since 5.0
     * @deprecated use method that takes VdbContextEditor as the argument
     */
    @Deprecated
    boolean isVdbConnectionStale( VdbEditingContext theVdbContext );

    /**
     * Indicates if the current connection for the specified <code>VdbContextEditor</code> does not represent the latest VDB
     * state.
     * 
     * @param theVdbContext the context whose connection is being checked
     * @return <code>true</code> if the connection is stale; <code>false</code> if no connection exists or connection is
     *         up-to-date.
     * @since 5.0
     */
    boolean isVdbConnectionStale( VdbContextEditor theVdbContext );

    /**
     * Indicates if any of the extension module is stale for the specified connection.
     * 
     * @param theVdbContext the context being checked
     * @return <code>true</code> if a connection is open; <code>false</code> otherwise.
     * @since 5.0
     */
    boolean isExtensionModuleStale( Object theConnection );

}
