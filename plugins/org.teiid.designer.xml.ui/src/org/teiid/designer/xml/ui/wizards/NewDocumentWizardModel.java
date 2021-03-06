/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.xml.ui.wizards;

import static org.teiid.designer.xml.ui.ModelerXmlUiConstants.PLUGIN_ID;

import java.util.Collection;
import java.util.HashSet;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.swt.widgets.Composite;
import org.teiid.designer.core.ModelerCore;
import org.teiid.designer.core.workspace.ModelResource;
import org.teiid.designer.mapping.factory.MappingClassBuilderStrategy;
import org.teiid.designer.metamodels.xml.XmlFragment;
import org.teiid.designer.xml.IVirtualDocumentFragmentSource;
import org.teiid.designer.xml.ModelerXmlPlugin;
import org.teiid.designer.xml.PluginConstants;



/**
 * @author PForhan
 *
 * @since 8.0
 */
public class NewDocumentWizardModel {
    private Composite                      wizHolder;
    private IVirtualDocumentFragmentSource source;
    private XmlFragment[]                  fragments;
    private int                            selectedFragmentCount;
    private boolean                        fragmentsNeedUpdating = true;
    private boolean                        buildEntireDocuments;
    private boolean                        buildMappingClasses;
    private int                            estimatedNodeCount;
    private boolean                        useSchemaTypes = true;
    private boolean                        buildGlobalOnly; // Note: not currently read by anyone!
    private Collection                     unhandledModelImports;
    private MappingClassBuilderStrategy    strategy;
//  may not be needed:
//  public IDocumentsAndFragmentsPopulator populator;
    
    public NewDocumentWizardModel() {
        ModelerXmlPlugin plugin = ModelerXmlPlugin.getDefault();

        if (plugin != null) {
            IEclipsePreferences defaultPrefs = ModelerCore.getDefaultPreferences(PLUGIN_ID);
            defaultPrefs.putBoolean(PluginConstants.PreferenceKeys.MAPPING_TYPE_FROM_XSD, true);
        }
    }

    //
    // Data methods:
    //
    public XmlFragment[] getFragments(ModelResource modelResource, IProgressMonitor monitor) {
        if (fragmentsNeedUpdating
         || fragments == null) {
            fragments = source.getFragments(modelResource, monitor);
            fragmentsNeedUpdating = false;
        } // endif
        
        return fragments;
    }
    public void setBuildEntireDocuments(boolean buildEntireDocuments) {
        // only operate if this is a change:
        if (this.buildEntireDocuments != buildEntireDocuments) {
            fragmentsNeedUpdating = true; // indicate that the fragments have changed
            this.buildEntireDocuments = buildEntireDocuments;
        } // endif
    }
    public boolean getBuildEntireDocuments() {
        return buildEntireDocuments;
    }
    public void setSource(IVirtualDocumentFragmentSource source) {
        if (this.source != source) {
            fragmentsNeedUpdating = true; // indicate that the fragments have changed
            this.source = source;
        } // endif
    }
    public IVirtualDocumentFragmentSource getSource() {
        return source;
    }
    public void setSelectedFragmentCount(int selectedFragmentCount) {
        // only operate if this is a change:
        if (this.selectedFragmentCount != selectedFragmentCount) {
            fragmentsNeedUpdating = true; // indicate that the fragments have changed
            this.selectedFragmentCount = selectedFragmentCount;
        } // endif
    }
    public int getSelectedFragmentCount() {
        return selectedFragmentCount;
    }
    public void setBuildMappingClasses(boolean buildMappingClasses) {
        // has no effect upon fragmentsNeedsUpdating
        this.buildMappingClasses = buildMappingClasses;
    }
    public boolean getBuildMappingClasses() {
        return buildMappingClasses;
    }
    public void setEstimatedNodeCount(int estimatedNodeCount) {
        // has no effect upon fragmentsNeedsUpdating
        this.estimatedNodeCount = estimatedNodeCount;
    }
    public int getEstimatedNodeCount() {
        return estimatedNodeCount;
    }
    public void setUseSchemaTypes(boolean useSchemaTypes) {
        // only operate if this is a change:
        if (this.useSchemaTypes != useSchemaTypes) {
            fragmentsNeedUpdating = true; // indicate that the fragments have changed
            this.useSchemaTypes = useSchemaTypes;
        } // endif
    }
    public boolean getUseSchemaTypes() {
        return useSchemaTypes;
    }
    public void setBuildGlobalOnly(boolean buildGlobalOnly) {
        // only operate if this is a change:
        if (this.buildGlobalOnly != buildGlobalOnly) {
            fragmentsNeedUpdating = true; // indicate that the fragments have changed
            this.buildGlobalOnly = buildGlobalOnly;
        } // endif
    }
    public boolean getBuildGlobalOnly() {
        return buildGlobalOnly;
    }
    
    /** Allows access to the container holding wizard page controls */
    public Composite getWizHolder() {
        return wizHolder;
    }
    public void setWizHolder(Composite wizHolder) {
        this.wizHolder = wizHolder;
    }
    
    public void setReferencedResources(final Collection refs) {
        this.unhandledModelImports = refs;
    }
    
    public Collection getUnhandledModelImports() {
        if(unhandledModelImports == null) {
            unhandledModelImports = new HashSet();
        }
        return unhandledModelImports;
    }
    
    public void setMappingClassBuilderStrategy(MappingClassBuilderStrategy strategy) {
        this.strategy = strategy;
    }
    
    public MappingClassBuilderStrategy getMappingClassBuilderStrategy( ) {
        return this.strategy;
    }
}
