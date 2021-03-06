/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.schema.tools.processing.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.teiid.designer.schema.tools.model.schema.Column;
import org.teiid.designer.schema.tools.model.schema.Relationship;
import org.teiid.designer.schema.tools.model.schema.SchemaModel;
import org.teiid.designer.schema.tools.model.schema.SchemaObject;
import org.teiid.designer.schema.tools.model.schema.impl.SchemaModelImpl;

/**
 * @since 8.0
 */
public class RequestProcessorImpl extends BaseRelationshipProcessor {

    public boolean DEBUG_SOUT_REPRESENTATIONS = true;

    private Map tableRelationships; // key: Relationship, value Integer

    @Override
	public void calculateRelationshipTypes( SchemaModel model ) {
        setSechemaModel(model);
        List elements = model.getElements();

        for (Iterator iter = elements.iterator(); iter.hasNext();) {
            SchemaObject element = (SchemaObject)iter.next();

            int P_value; // the number of types of parent
            int C_value; // maxOccurs of the child within its parent
            // for maxOccurs, -2 means "mixed", -3 means "not set yet"
            // as as usual, -1 means "unbounded"
            boolean canBeRoot; // whether the table can be a document root
            int F_value;

            List parents = element.getParents();
            List columns = element.getAttributes();
            P_value = parents.size();
            F_value = columns.size();
            canBeRoot = element.isUseAsRoot();

            C_value = calculateCValue(parents);

            int representation = rules.calculateRelationship(P_value, C_value, canBeRoot, F_value);

            if (representation != Relationship.MERGE_IN_PARENT_MULTIPLE || representation != Relationship.MERGE_IN_PARENT_SINGLE) {
                element.setRepresentAsTable(true);
            }
            element.setAllParentRepresentations(representation, this);
        }

        removeRecursiveMerges(elements);

        qualifyDuplicateMergedTableNames();
        mergeRelationships();
        removeFullyMergedTables();
        qualifyDuplicateNonMergedTableNames();
        ((SchemaModelImpl)schemaModel).setTableRelationships(tableRelationships);

    }

    @Override
	public int C_threshold() {
        return Integer.MAX_VALUE;
    }

    @Override
	public int P_threshold() {
        return Integer.MAX_VALUE;
    }

    @Override
	public int F_threshold() {
        return Integer.MAX_VALUE;
    }

    /**
     * Merges the columns and TableRelationships of a child into its parent(s).
     * 
     * @param parent The table to merge into.
     * @param tableRelationship The Relationship to the child being merged.
     */
    @Override
    protected void mergeChild( SchemaObject parent,
                               Relationship tableRelationship ) {
        SchemaObject child = tableRelationship.getChild();
        child.setWithinSelectedHierarchy(false);
        for (Iterator iter = child.getAttributes().iterator(); iter.hasNext();) {
            Object o = iter.next();
            Column col = (Column)o;
            col.mergeIntoParent(tableRelationship, -1);
        }
        pullUpGrandChildRelationships(child.getParents(), child.getChildren());
    }
}
