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

package com.metamatrix.modeler.jdbc.metadata.impl;

import com.metamatrix.modeler.jdbc.metadata.DatabaseInfo;

/**
 * DatabaseInfoImpl
 */
public class DatabaseInfoImpl implements DatabaseInfo {
    
    private String productName;
    private String productVersion;
    private int driverMajorVersion;
    private int driverMinorVersion;
    private String driverName;
    private String driverVersion;
    private String databaseURL;
    private String userName;
    private boolean readOnly;

    /**
     * Construct an instance of DatabaseInfoImpl.
     * 
     */
    public DatabaseInfoImpl() {
        super();
    }

    /* (non-Javadoc)
     * @see com.metamatrix.modeler.jdbc.DatabaseInfo#getProductName()
     */
    public String getProductName() {
        return this.productName;
    }

    /* (non-Javadoc)
     * @see com.metamatrix.modeler.jdbc.DatabaseInfo#getProductVersion()
     */
    public String getProductVersion() {
        return this.productVersion;
    }

    /* (non-Javadoc)
     * @see com.metamatrix.modeler.jdbc.DatabaseInfo#getMajorVersion()
     */
    public int getDriverMajorVersion() {
        return this.driverMajorVersion;
    }

    /* (non-Javadoc)
     * @see com.metamatrix.modeler.jdbc.DatabaseInfo#getMinorVersion()
     */
    public int getDriverMinorVersion() {
        return this.driverMinorVersion;
    }

    /* (non-Javadoc)
     * @see com.metamatrix.modeler.jdbc.DatabaseInfo#getDriverName()
     */
    public String getDriverName() {
        return this.driverName;
    }

    /* (non-Javadoc)
     * @see com.metamatrix.modeler.jdbc.DatabaseInfo#getDriverVersion()
     */
    public String getDriverVersion() {
        return this.driverVersion;
    }

    /* (non-Javadoc)
     * @see com.metamatrix.modeler.jdbc.DatabaseInfo#getDatabaseURL()
     */
    public String getDatabaseURL() {
        return this.databaseURL;
    }

    /* (non-Javadoc)
     * @see com.metamatrix.modeler.jdbc.DatabaseInfo#getUserName()
     */
    public String getUserName() {
        return this.userName;
    }

    /* (non-Javadoc)
     * @see com.metamatrix.modeler.jdbc.DatabaseInfo#isReadOnly()
     */
    public boolean isReadOnly() {
        return this.readOnly;
    }

    /**
     * @param string
     */
    public void setDatabaseURL(String url) {
        this.databaseURL = url;
    }

    /**
     * @param string
     */
    public void setDriverName(String name) {
        this.driverName = name;
    }

    /**
     * @param string
     */
    public void setDriverVersion(String version) {
        this.driverVersion = version;
    }

    /**
     * @param string
     */
    public void setDriverMajorVersion(int majorVersion) {
        this.driverMajorVersion = majorVersion;
    }

    /**
     * @param string
     */
    public void setDriverMinorVersion(int minorVersion) {
        this.driverMinorVersion = minorVersion;
    }

    /**
     * @param string
     */
    public void setProductName(String name) {
        this.productName = name;
    }

    /**
     * @param string
     */
    public void setProductVersion(String version) {
        this.productVersion = version;
    }

    /**
     * @param b
     */
    public void setReadOnly(boolean readonly) {
        this.readOnly = readonly;
    }

    /**
     * @param string
     */
    public void setUserName(String username) {
        this.userName = username;
    }

}
