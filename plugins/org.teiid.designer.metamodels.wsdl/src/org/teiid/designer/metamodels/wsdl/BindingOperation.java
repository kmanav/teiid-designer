/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.wsdl;

import org.eclipse.emf.common.util.EList;
import org.teiid.designer.metamodels.wsdl.http.HttpOperation;
import org.teiid.designer.metamodels.wsdl.soap.SoapOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Binding Operation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.teiid.designer.metamodels.wsdl.BindingOperation#getBinding <em>Binding</em>}</li>
 * <li>{@link org.teiid.designer.metamodels.wsdl.BindingOperation#getBindingInput <em>Binding Input</em>}</li>
 * <li>{@link org.teiid.designer.metamodels.wsdl.BindingOperation#getBindingFaults <em>Binding Faults</em>}</li>
 * <li>{@link org.teiid.designer.metamodels.wsdl.BindingOperation#getBindingOutput <em>Binding Output</em>}</li>
 * <li>{@link org.teiid.designer.metamodels.wsdl.BindingOperation#getSoapOperation <em>Soap Operation</em>}</li>
 * <li>{@link org.teiid.designer.metamodels.wsdl.BindingOperation#getHttpOperation <em>Http Operation</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.teiid.designer.metamodels.wsdl.WsdlPackage#getBindingOperation()
 * @model
 * @generated
 *
 * @since 8.0
 */
public interface BindingOperation extends ExtensibleDocumented, WsdlNameRequiredEntity {

    /**
     * Returns the value of the '<em><b>Binding</b></em>' container reference. It is bidirectional and its opposite is '
     * {@link org.teiid.designer.metamodels.wsdl.Binding#getBindingOperations <em>Binding Operations</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Binding</em>' container reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Binding</em>' container reference.
     * @see #setBinding(Binding)
     * @see org.teiid.designer.metamodels.wsdl.WsdlPackage#getBindingOperation_Binding()
     * @see org.teiid.designer.metamodels.wsdl.Binding#getBindingOperations
     * @model opposite="bindingOperations" required="true"
     * @generated
     */
    Binding getBinding();

    /**
     * Sets the value of the '{@link org.teiid.designer.metamodels.wsdl.BindingOperation#getBinding <em>Binding</em>}' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Binding</em>' container reference.
     * @see #getBinding()
     * @generated
     */
    void setBinding( Binding value );

    /**
     * Returns the value of the '<em><b>Binding Input</b></em>' containment reference. It is bidirectional and its opposite is '
     * {@link org.teiid.designer.metamodels.wsdl.BindingInput#getBindingOperation <em>Binding Operation</em>}'. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Binding Input</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Binding Input</em>' containment reference.
     * @see #setBindingInput(BindingInput)
     * @see org.teiid.designer.metamodels.wsdl.WsdlPackage#getBindingOperation_BindingInput()
     * @see org.teiid.designer.metamodels.wsdl.BindingInput#getBindingOperation
     * @model opposite="bindingOperation" containment="true"
     * @generated
     */
    BindingInput getBindingInput();

    /**
     * Sets the value of the '{@link org.teiid.designer.metamodels.wsdl.BindingOperation#getBindingInput <em>Binding Input</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Binding Input</em>' containment reference.
     * @see #getBindingInput()
     * @generated
     */
    void setBindingInput( BindingInput value );

    /**
     * Returns the value of the '<em><b>Binding Faults</b></em>' containment reference list. The list contents are of type
     * {@link org.teiid.designer.metamodels.wsdl.BindingFault}. It is bidirectional and its opposite is '
     * {@link org.teiid.designer.metamodels.wsdl.BindingFault#getBindingOperation <em>Binding Operation</em>}'. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Binding Faults</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Binding Faults</em>' containment reference list.
     * @see org.teiid.designer.metamodels.wsdl.WsdlPackage#getBindingOperation_BindingFaults()
     * @see org.teiid.designer.metamodels.wsdl.BindingFault#getBindingOperation
     * @model type="org.teiid.designer.metamodels.wsdl.BindingFault" opposite="bindingOperation" containment="true"
     * @generated
     */
    EList getBindingFaults();

    /**
     * Returns the value of the '<em><b>Binding Output</b></em>' containment reference. It is bidirectional and its opposite is '
     * {@link org.teiid.designer.metamodels.wsdl.BindingOutput#getBindingOperation <em>Binding Operation</em>}'. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Binding Output</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Binding Output</em>' containment reference.
     * @see #setBindingOutput(BindingOutput)
     * @see org.teiid.designer.metamodels.wsdl.WsdlPackage#getBindingOperation_BindingOutput()
     * @see org.teiid.designer.metamodels.wsdl.BindingOutput#getBindingOperation
     * @model opposite="bindingOperation" containment="true"
     * @generated
     */
    BindingOutput getBindingOutput();

    /**
     * Sets the value of the '{@link org.teiid.designer.metamodels.wsdl.BindingOperation#getBindingOutput <em>Binding Output</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Binding Output</em>' containment reference.
     * @see #getBindingOutput()
     * @generated
     */
    void setBindingOutput( BindingOutput value );

    /**
     * Returns the value of the '<em><b>Soap Operation</b></em>' containment reference. It is bidirectional and its opposite is '
     * {@link org.teiid.designer.metamodels.wsdl.soap.SoapOperation#getBindingOperation <em>Binding Operation</em>}'. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Soap Operation</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Soap Operation</em>' containment reference.
     * @see #setSoapOperation(SoapOperation)
     * @see org.teiid.designer.metamodels.wsdl.WsdlPackage#getBindingOperation_SoapOperation()
     * @see org.teiid.designer.metamodels.wsdl.soap.SoapOperation#getBindingOperation
     * @model opposite="bindingOperation" containment="true"
     * @generated
     */
    SoapOperation getSoapOperation();

    /**
     * Sets the value of the '{@link org.teiid.designer.metamodels.wsdl.BindingOperation#getSoapOperation <em>Soap Operation</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Soap Operation</em>' containment reference.
     * @see #getSoapOperation()
     * @generated
     */
    void setSoapOperation( SoapOperation value );

    /**
     * Returns the value of the '<em><b>Http Operation</b></em>' containment reference. It is bidirectional and its opposite is '
     * {@link org.teiid.designer.metamodels.wsdl.http.HttpOperation#getBindingOperation <em>Binding Operation</em>}'. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Http Operation</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Http Operation</em>' containment reference.
     * @see #setHttpOperation(HttpOperation)
     * @see org.teiid.designer.metamodels.wsdl.WsdlPackage#getBindingOperation_HttpOperation()
     * @see org.teiid.designer.metamodels.wsdl.http.HttpOperation#getBindingOperation
     * @model opposite="bindingOperation" containment="true"
     * @generated
     */
    HttpOperation getHttpOperation();

    /**
     * Sets the value of the '{@link org.teiid.designer.metamodels.wsdl.BindingOperation#getHttpOperation <em>Http Operation</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Http Operation</em>' containment reference.
     * @see #getHttpOperation()
     * @generated
     */
    void setHttpOperation( HttpOperation value );

} // BindingOperation
