package com.problem.config.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.soap.server.SoapEndpointInterceptor;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

@Component
public class SoapInterceptor implements SoapEndpointInterceptor {

    private static final String PREFERRED_PREFIX = "soap";
    private static final String SOAP_ENV_NAMESPACE = "http://schemas.xmlsoap.org/soap/envelope/";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean understands(final SoapHeaderElement header) {
        return false;
    }

    @Override
    public boolean handleRequest(final MessageContext messageContext, final Object endpoint) throws Exception {
        return true;
    }

    @Override
    public boolean handleResponse(final MessageContext messageContext, final Object endpoint) throws Exception {
        SaajSoapMessage response = (SaajSoapMessage) messageContext.getResponse();
        alterSoapEnvelope(response);
        return true;
    }

    @Override
    public boolean handleFault(final MessageContext messageContext, final Object endpoint) throws Exception {
        return true;
    }

    @Override
    public void afterCompletion(final MessageContext messageContext, final Object endpoint,
                                final Exception ex) throws Exception {
        // Do nothing because everything is fine
    }

    private void alterSoapEnvelope(SaajSoapMessage soapResponse) {
        try {
            SOAPMessage soapMessage = soapResponse.getSaajMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            SOAPHeader header = soapMessage.getSOAPHeader();
            SOAPBody body = soapMessage.getSOAPBody();
            SOAPFault fault = body.getFault();
            envelope.removeNamespaceDeclaration(envelope.getPrefix());
            envelope.addNamespaceDeclaration(PREFERRED_PREFIX, SOAP_ENV_NAMESPACE);
            envelope.setPrefix(PREFERRED_PREFIX);
            header.setPrefix(PREFERRED_PREFIX);
            body.setPrefix(PREFERRED_PREFIX);
            if (fault != null) {
                fault.setPrefix(PREFERRED_PREFIX);
            }
        } catch (SOAPException e) {
            logger.error("Altering Soap Envelope caused an exception.", e);
        }
    }
}
