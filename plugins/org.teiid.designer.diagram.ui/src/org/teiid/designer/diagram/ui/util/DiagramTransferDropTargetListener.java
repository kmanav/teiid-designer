/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.diagram.ui.util;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;

/**
 *  DiagramTransferDropTargetListener
 *
 * @since 8.0
 */
public class DiagramTransferDropTargetListener extends AbstractTransferDropTargetListener {

    public DiagramTransferDropTargetListener(EditPartViewer viewer) {
        super(viewer);
    }

    @Override
    protected Request createTargetRequest() {
        return new NativeDropRequest();
    }

    protected NativeDropRequest getNativeDropRequest() {
        return (NativeDropRequest)getTargetRequest();
    }

    @Override
    protected void updateTargetRequest() {
        getNativeDropRequest().setData(getCurrentEvent().data);
    }

}
