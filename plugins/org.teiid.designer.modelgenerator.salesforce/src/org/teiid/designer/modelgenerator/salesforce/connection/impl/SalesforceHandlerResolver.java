/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.modelgenerator.salesforce.connection.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import com.sforce.soap.partner.SessionHeader;

/* Handler which adds the SessionId info to the SOAP Header
 * 
 */
class SalesforceHandlerResolver implements HandlerResolver {

    private SalesforceHeaderHandler headerHandler = null;

    public SalesforceHandlerResolver( SessionHeader sh ) {
        this.headerHandler = new SalesforceHeaderHandler(sh);
    }

    @Override
	public List<Handler> getHandlerChain( PortInfo portInfo ) {
        List<Handler> handlerChain = new ArrayList<Handler>();

        handlerChain.add(this.headerHandler);

        return handlerChain;
    }

    class SalesforceHeaderHandler implements SOAPHandler<SOAPMessageContext> {

        SessionHeader sh = null;

        public SalesforceHeaderHandler( SessionHeader sh ) {
            this.sh = sh;
        }

        @Override
		public boolean handleMessage( SOAPMessageContext smc ) {
            QName sessionHeader = new QName("urn:partner.soap.sforce.com", "SessionHeader"); //$NON-NLS-1$ //$NON-NLS-2$
            QName sessionId = new QName("urn:partner.soap.sforce.com", "sessionId"); //$NON-NLS-1$ //$NON-NLS-2$
            try {
                // If the SessionHeader is null, or the session id is null - do nothing.
                if (sh != null && sh.getSessionId() != null) {
                    SOAPMessage message = smc.getMessage();
                    SOAPPart part = message.getSOAPPart();
                    SOAPEnvelope envelope = part.getEnvelope();
                    SOAPHeader header = envelope.getHeader();
                    if (header == null) header = envelope.addHeader();
                    SOAPHeaderElement sessionHeaderElement = header.addHeaderElement(sessionHeader);
                    SOAPElement sessionIdElement = sessionHeaderElement.addChildElement(sessionId);
                    sessionIdElement.addTextNode(sh.getSessionId());
                }

            } catch (SOAPException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
		public Set getHeaders() {
            return null;
        }

        @Override
		public boolean handleFault( SOAPMessageContext context ) {
            return true;
        }

        @Override
		public void close( MessageContext context ) {
        }
    }
}
