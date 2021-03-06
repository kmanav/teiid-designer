/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.metadata.runtime;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.teiid.designer.core.index.IndexConstants;
import org.teiid.designer.core.metamodel.aspect.MetamodelEntity;
import org.teiid.designer.core.metamodel.aspect.sql.SqlModelAspect;


/**
 * FakeSqlModelAspect
 */
public class FakeSqlModelAspect implements SqlModelAspect {
    
    public String name, fullName, nameInSource, primaryMetamodelUri;
    public IPath path;
    public Object uuid, parentUuid;
    public boolean supportsOrderBy, supportsOuterJoin, supportsWhereAll, supportsDistinct, supportsJoin, isVisible;
    public int maxSetSize, modelType;
    
    @Override
	public String getPrimaryMetamodelUri(EObject eObject) { return primaryMetamodelUri; }

    @Override
	public boolean supportsOrderBy(EObject eObject) { return supportsOrderBy; }

    @Override
	public boolean supportsOuterJoin(EObject eObject) { return supportsOuterJoin; }

    @Override
	public boolean supportsWhereAll(EObject eObject) { return supportsWhereAll; }

    @Override
	public boolean supportsDistinct(EObject eObject) { return supportsDistinct; }

    @Override
	public boolean supportsJoin(EObject eObject) { return supportsJoin; }

    @Override
	public boolean isVisible(EObject eObject) { return isVisible; }
    
    @Override
	public int getModelType(EObject eObject) {return modelType;}

    @Override
	public int getMaxSetSize(EObject eObject) { return maxSetSize; }
    
    @Override
	public String getName(EObject eObject) { return name; }
    
    @Override
	public String getFullName(EObject eObject) { return fullName; }    
    
    @Override
	public String getNameInSource(EObject eObject) { return nameInSource; }
    
    @Override
	public IPath getPath(EObject eObject) { return path; }
    
    @Override
	public Object getObjectID(EObject eObject) { return uuid; }
    
    @Override
	public boolean isRecordType(char recordType) { return (recordType == IndexConstants.RECORD_TYPE.MODEL ); } 

    @Override
	public boolean isQueryable(EObject eObject) { return true; }
    
    @Override
	public String getID() { return null; }
    
    @Override
	public MetamodelEntity getMetamodelEntity() { return null; }

    @Override
	public Object getParentObjectID(EObject eObject) { return parentUuid; }


    /*
     * @See org.teiid.designer.core.metamodel.aspect.sql.SqlAspect#updateObject(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    @Override
	public void updateObject(EObject targetObject, EObject sourceObject) {

    }

}
