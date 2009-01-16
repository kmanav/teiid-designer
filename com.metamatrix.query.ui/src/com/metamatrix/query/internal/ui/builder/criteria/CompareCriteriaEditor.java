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
package com.metamatrix.query.internal.ui.builder.criteria;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import com.metamatrix.core.util.ArgCheck;
import com.metamatrix.core.util.I18nUtil;
import com.metamatrix.query.internal.ui.builder.model.CompareCriteriaEditorModel;
import com.metamatrix.query.internal.ui.builder.model.ILanguageObjectEditorModelListener;
import com.metamatrix.query.internal.ui.builder.model.LanguageObjectEditorModelEvent;
import com.metamatrix.query.internal.ui.builder.util.BuilderUtils;
import com.metamatrix.query.sql.LanguageObject;
import com.metamatrix.query.sql.lang.CompareCriteria;
import com.metamatrix.query.sql.symbol.Expression;

/**
 * CompareCriteriaEditor
 */
public class CompareCriteriaEditor extends AbstractPredicateCriteriaTypeEditor {

    private final static String PREFIX = I18nUtil.getPropertyPrefix(CompareCriteriaEditor.class);

    private CriteriaExpressionEditor leftEditor;
    private CriteriaExpressionEditor rightEditor;
    private Control rightComponent;
    private Control leftComponent;
    private ViewController viewController;
    CompareCriteriaEditorModel theModel;

    public CompareCriteriaEditor( Composite parent,
                                  CompareCriteriaEditorModel model ) {
        super(parent, CompareCriteria.class, model);
        if (BuilderUtils.isDebugLogging() || BuilderUtils.isTraceLogging()) {
            Util.print(this, "constructor"); //$NON-NLS-1$
        }
        this.theModel = model;
        viewController = new ViewController();
        model.addModelListener(viewController);
        viewController.initialize();
    }

    @Override
    public String getToolTipText() {
        String toolTip = ""; //$NON-NLS-1$
        String curOperator = theModel.getCurrentOperator();
        if (curOperator.equals(CompareCriteriaEditorModel.EQ)) {
            toolTip = Util.getString(PREFIX + "equalsToolTipText"); //$NON-NLS-1$
        } else if (curOperator.equals(CompareCriteriaEditorModel.NE)) {
            toolTip = Util.getString(PREFIX + "notEqualToolTipText"); //$NON-NLS-1$
        } else if (curOperator.equals(CompareCriteriaEditorModel.GT)) {
            toolTip = Util.getString(PREFIX + "greaterThanToolTipText"); //$NON-NLS-1$
        } else if (curOperator.equals(CompareCriteriaEditorModel.LT)) {
            toolTip = Util.getString(PREFIX + "lessThanToolTipText"); //$NON-NLS-1$
        } else if (curOperator.equals(CompareCriteriaEditorModel.GE)) {
            toolTip = Util.getString(PREFIX + "greaterEqualToolTipText"); //$NON-NLS-1$
        } else if (curOperator.equals(CompareCriteriaEditorModel.LE)) {
            toolTip = Util.getString(PREFIX + "lessEqualToolTipText"); //$NON-NLS-1$
        }
        return toolTip;
    }

    @Override
    public String getTitle() {
        String title = Util.getString(PREFIX + "title"); //$NON-NLS-1$
        return title;
    }

    public Control createLeftComponent( Composite parent ) {
        if (BuilderUtils.isTraceLogging() || BuilderUtils.isDebugLogging()) {
            Util.print(this, " in createLeftComponent()"); //$NON-NLS-1$
        }
        leftEditor = new CriteriaExpressionEditor(parent, theModel.getLeftExpressionModel());
        leftComponent = leftEditor.getUi();
        return leftComponent;
    }

    public Control createRightComponent( Composite parent ) {
        if (BuilderUtils.isTraceLogging() || BuilderUtils.isDebugLogging()) {
            Util.print(this, " in createRightComponent()"); //$NON-NLS-1$
        }
        rightEditor = new CriteriaExpressionEditor(parent, theModel.getRightExpressionModel());
        rightComponent = rightEditor.getUi();
        return rightComponent;
    }

    public Expression getLeftExpression() {
        Expression leftExpression = theModel.getLeftExpression();
        return leftExpression;
    }

    public Expression getRightExpression() {
        Expression rightExpression = theModel.getRightExpression();
        return rightExpression;
    }

    @Override
    public void setLanguageObject( LanguageObject obj ) {
        ArgCheck.isInstanceOf(CompareCriteria.class, obj);
        theModel.setLanguageObject(obj);
    }

    public String[] getOperators() {
        return theModel.getOperators();
    }

    @Override
    public void acceptFocus() {
        leftEditor.acceptFocus();
    }

    public void setOperator( String op ) {
        theModel.setCurrentOperator(op);
    }

    public String getCurrentOperator() {
        return theModel.getCurrentOperator();
    }

    void displayLanguageObjectChange() {
        displayLeftExpression();
        displayRightExpression();
    }

    void displayLeftExpression() {
        leftEditor.setLanguageObject(theModel.getLeftExpression());
    }

    void displayRightExpression() {
        rightEditor.setLanguageObject(theModel.getRightExpression());
    }

    /**
     * The <code>ViewController</code> class is a view controller for the <code>FunctionEditor</code>.
     */
    private class ViewController implements ILanguageObjectEditorModelListener {
        public ViewController() {
            super();
        }

        public void initialize() {
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    modelChanged(new LanguageObjectEditorModelEvent(theModel, LanguageObjectEditorModelEvent.SAVED));
                }
            });
        }

        /**
         * @see com.metamatrix.query.internal.ui.builder.model.ILanguageObjectEditorModelListener#modelChanged(com.metamatrix.query.internal.ui.builder.model.LanguageObjectEditorModelEvent)
         */
        public void modelChanged( LanguageObjectEditorModelEvent theEvent ) {
            String type = theEvent.getType();
            if (BuilderUtils.isEventLogging() || BuilderUtils.isTraceLogging()) {
                Util.print(this, " CompareCriteriaEditor ViewController modelChanged(), event type of " + type); //$NON-NLS-1$
            }
            if (type.equals(CompareCriteriaEditorModel.LEFT_EXPRESSION)) {
                displayLeftExpression();
            } else if (type.equals(CompareCriteriaEditorModel.RIGHT_EXPRESSION)) {
                displayRightExpression();
            } else if (type.equals(LanguageObjectEditorModelEvent.SAVED)) {
                displayLanguageObjectChange();
            }
        }
    }

    @Override
    public boolean hasChanged() {
        boolean changed = super.hasChanged();
        if (BuilderUtils.isDebugLogging() || BuilderUtils.isTraceLogging()) {
            Util.print(this, "hasChanged() returning " + changed); //$NON-NLS-1$
        }
        return changed;
    }
}
