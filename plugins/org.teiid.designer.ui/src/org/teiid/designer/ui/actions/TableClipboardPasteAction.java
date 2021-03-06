/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.ui.actions;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.teiid.designer.ui.UiPlugin;
import org.teiid.designer.ui.editors.ModelEditor;
import org.teiid.designer.ui.table.ModelTableEditor;


/**
 * TableClipboardPasteAction
 *
 * @since 8.0
 */
public class TableClipboardPasteAction extends AbstractModelerAction {

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public TableClipboardPasteAction() {
        super(UiPlugin.getDefault());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /* (non-Javadoc)
     * see org.teiid.designer.ui.common.actions.AbstractAction#doRun()
     */
    @Override
    protected void doRun() {
        getTableEditor().pasteClipboardContents();
    }

    private ModelTableEditor getTableEditor() {
        ModelTableEditor result = null;
        IEditorPart editor = getActiveEditor();

        if (editor instanceof ModelEditor) {
            ModelEditor modelEditor = (ModelEditor)editor;
            IEditorPart subEditor = modelEditor.getActiveEditor();
        
            if (subEditor instanceof ModelTableEditor) {
                result = (ModelTableEditor)subEditor;
            }
        }
        
        return result;
    }

    /* (non-Javadoc)
     * see org.teiid.designer.ui.common.actions.AbstractAction#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(IWorkbenchPart thePart, ISelection theSelection) {
        super.selectionChanged(thePart, theSelection);
        setEnabledState();
    }
    
    /* (non-Javadoc)
     * see org.teiid.designer.ui.common.actions.AbstractAction#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    @Override
    public void selectionChanged(SelectionChangedEvent theEvent) {
        super.selectionChanged(theEvent);
        setEnabledState();
    }
    
    /**
     * Sets the enabled state of the action.
     */
    private void setEnabledState() {
        boolean enable = false;
        
        if (getTableEditor() != null) {
            enable = getTableEditor().canPaste();
        }

        setEnabled(enable);
    }

}
