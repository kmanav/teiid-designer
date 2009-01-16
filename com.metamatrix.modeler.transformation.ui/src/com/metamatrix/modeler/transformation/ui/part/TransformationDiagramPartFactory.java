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

package com.metamatrix.modeler.transformation.ui.part;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.gef.EditPart;

import com.metamatrix.metamodels.diagram.Diagram;
import com.metamatrix.modeler.core.ModelerCore;
import com.metamatrix.modeler.diagram.ui.DiagramUiConstants;
import com.metamatrix.modeler.diagram.ui.DiagramUiPlugin;
import com.metamatrix.modeler.diagram.ui.connection.AnchorManager;
import com.metamatrix.modeler.diagram.ui.connection.ChopBoxAnchorManager;
import com.metamatrix.modeler.diagram.ui.connection.NodeConnectionEditPart;
import com.metamatrix.modeler.diagram.ui.drawing.DrawingPartFactory;
import com.metamatrix.modeler.diagram.ui.drawing.model.DrawingModelNode;
import com.metamatrix.modeler.diagram.ui.figure.DiagramFigureFactory;
import com.metamatrix.modeler.diagram.ui.model.DiagramModelNode;
import com.metamatrix.modeler.diagram.ui.model.LabelModelNode;
import com.metamatrix.modeler.diagram.ui.notation.NotationPartGenerator;
import com.metamatrix.modeler.diagram.ui.notation.uml.model.UmlClassifierNode;
import com.metamatrix.modeler.diagram.ui.notation.uml.part.UmlClassifierEditPart;
import com.metamatrix.modeler.diagram.ui.part.AbstractDiagramEditPart;
import com.metamatrix.modeler.diagram.ui.part.AbstractDiagramEditPartFactory;
import com.metamatrix.modeler.diagram.ui.part.DiagramEditPart;
import com.metamatrix.modeler.diagram.ui.part.LabelEditPart;
import com.metamatrix.modeler.diagram.ui.util.DiagramUiUtilities;
import com.metamatrix.modeler.internal.transformation.util.TransformationHelper;
import com.metamatrix.modeler.transformation.ui.DebugConstants;
import com.metamatrix.modeler.transformation.ui.PluginConstants;
import com.metamatrix.modeler.transformation.ui.UiConstants;
import com.metamatrix.modeler.transformation.ui.actions.TransformationSourceManager;
import com.metamatrix.modeler.transformation.ui.connection.TransformationAnchorManager;
import com.metamatrix.modeler.transformation.ui.connection.TransformationLink;
import com.metamatrix.modeler.transformation.ui.figure.TransformationDiagramFigureFactory;
import com.metamatrix.modeler.transformation.ui.model.TransformationDiagramNode;
import com.metamatrix.modeler.transformation.ui.model.TransformationNode;

/**
 * TransformationPartFactory
 */
public class TransformationDiagramPartFactory extends AbstractDiagramEditPartFactory implements UiConstants {
    private DrawingPartFactory drawingPartFactory;
    private DiagramFigureFactory figureFactory;
    private static final String THIS_CLASS = "TransformationDiagramPartFactory"; //$NON-NLS-1$
    private static final String diagramTypeId = PluginConstants.TRANSFORMATION_DIAGRAM_TYPE_ID;
    /**
     * @see org.eclipse.gef.EditPartFactory#createEditPart(EditPart, Object)
    **/
    @Override
    public EditPart createEditPart(EditPart iContext, Object iModel) {
        EditPart editPart = null;

        if( drawingPartFactory == null )
            drawingPartFactory = new DrawingPartFactory();
        
        if( figureFactory == null )
            figureFactory = new TransformationDiagramFigureFactory();

        if( iModel instanceof DrawingModelNode ) {
            editPart = drawingPartFactory.createEditPart(iContext, iModel);
        } else if( iModel instanceof TransformationDiagramNode ) {
            editPart = new TransformationDiagramEditPart();
            ((AbstractDiagramEditPart)editPart).setFigureFactory(figureFactory);
            Object transform = getTransformation(iModel);
            if( transform != null ) {
                ((TransformationDiagramEditPart)editPart).setDropHelper(new TransformationDropEditPartHelper(transform));
            }
        } else if( iModel instanceof TransformationNode ) {
            editPart = new TransformationEditPart(diagramTypeId);
            ((AbstractDiagramEditPart)editPart).setFigureFactory(figureFactory);
            ((DiagramEditPart)editPart).setResizable(false);
            Object transform = getTransformation(iModel);
            if( transform != null ) {
                ((TransformationEditPart)editPart).setDropHelper(new TransformationDropEditPartHelper(transform));
            }
        } else if( iModel instanceof LabelModelNode ) {
            editPart = new LabelEditPart();
            ((DiagramEditPart)editPart).setResizable(false);
        } else  if( iModel instanceof TransformationLink ) {
            editPart = getConnectionEditPart(iModel);
        } else {
            // Here's where we get the notation manager and tell it to create an EditPart
            // for this modelObject.  So it'll come back in whatever "Notation" it desires.
            NotationPartGenerator generator = DiagramUiPlugin.getDiagramNotationManager().getEditPartGenerator(getNotationId());
            if( generator != null ) {
                editPart = generator.createEditPart(iContext, iModel, diagramTypeId);
            } else {
                ModelerCore.Util.log( 
                    IStatus.ERROR, 
                    Util.getString(DiagramUiConstants.Errors.PART_GENERATOR_FAILURE) + " Model Object = " + iModel); //$NON-NLS-1$
            }
        }
        
        if (editPart != null ) {
            if( editPart instanceof UmlClassifierEditPart ) {
                Object transform = getTransformation(iModel);
                if( transform != null ) {
                    ((UmlClassifierEditPart)editPart).setDropHelper(new TransformationDropEditPartHelper(transform));
                }
            }
            if( editPart instanceof NodeConnectionEditPart ) {
                editPart.setModel(iModel);
                ((NodeConnectionEditPart)editPart).setSourceAndTarget(iContext);
            } else if( editPart instanceof DiagramEditPart ){
                editPart.setModel(iModel);
                ((DiagramEditPart)editPart).setNotationId( getNotationId());
                ((DiagramEditPart)editPart).setSelectionHandler(getSelectionHandler());
                ((DiagramEditPart)editPart).setDiagramTypeId(diagramTypeId);
            }
            
            if ( Util.isDebugEnabled(DebugConstants.TX_DIAGRAM_EDIT_PARTS) &&  Util.isDebugEnabled(com.metamatrix.modeler.internal.ui.DebugConstants.TRACE) ) { 
                String message = ".createEditPart() = " + editPart; //$NON-NLS-1$
                Util.debug(DebugConstants.TX_DIAGRAM_EDIT_PARTS, THIS_CLASS + message);
            }
        } else {
            ModelerCore.Util.log( 
                IStatus.ERROR, 
                Util.getString(DiagramUiConstants.Errors.EDIT_PART_FAILURE) + " Model Object = " + iModel); //$NON-NLS-1$);
        }
        
        if( editPart instanceof DiagramEditPart ) {
            ((DiagramEditPart)editPart).setUnderConstruction(true);
        }
        return editPart;
    }
    /* (non-Javadoc)
     * @see com.metamatrix.modeler.diagram.ui.part.DiagramEditPartFactory#getConnectionEditPart()
     */
    public NodeConnectionEditPart getConnectionEditPart(Object iModel) {
        return new TransformationLinkEditPart();
    }
    
    /* (non-Javadoc)
     * @see com.metamatrix.modeler.diagram.ui.part.DiagramEditPartFactory#getAnchorManager(com.metamatrix.modeler.diagram.ui.part.DiagramEditPart)
     */
    public AnchorManager getAnchorManager(DiagramEditPart editPart) {
        if( editPart instanceof TransformationEditPart ) {
            return new ChopBoxAnchorManager(editPart);
        }
        return new TransformationAnchorManager(editPart);
    }
    
    private Object getTransformation(Object iModel) {
        if( iModel instanceof TransformationDiagramNode ) {
            Diagram diagram = (Diagram)((TransformationDiagramNode)iModel).getModelObject();
            if( diagram != null )
                return TransformationSourceManager.getTransformationFromDiagram(diagram);
        } else if( iModel instanceof TransformationNode ) {
            return ((TransformationNode)iModel).getModelObject();
        } else if( iModel instanceof UmlClassifierNode ) {
            // check to see if it's the "Target"
            DiagramModelNode parentClassifierNode = DiagramUiUtilities.getClassifierParentNode((DiagramModelNode)iModel);
            if( parentClassifierNode != null ) {
                Object thisTransform = null;
                if(TransformationHelper.isValidSqlTransformationTarget(parentClassifierNode.getModelObject())) {
                    thisTransform = TransformationHelper.getTransformationMappingRoot(parentClassifierNode.getModelObject());
                    if( thisTransform != null ) {
                        // Now we get the parent (DiagramEditPart)
                        TransformationDiagramNode tdep = (TransformationDiagramNode)parentClassifierNode.getParent();
                        Diagram diagram = (Diagram)tdep.getModelObject();
                        if( diagram != null ) {
                            Object diagramTransform = TransformationSourceManager.getTransformationFromDiagram(diagram);
                            if( diagramTransform != null && diagramTransform.equals(thisTransform))
                                return thisTransform;
                        }
                    }
                }
                // Now we check for is Virtual?
            }
        }
        
        return null;
    }
}
