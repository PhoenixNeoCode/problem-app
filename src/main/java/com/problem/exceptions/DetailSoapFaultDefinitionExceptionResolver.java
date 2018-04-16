package com.problem.exceptions;

import com.problem.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

@Slf4j
public class DetailSoapFaultDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver {

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
        log.error("SOAP Exception={}", ex.getMessage());
        MyException myException=new MyException();
        myException.setMessage(ex.getMessage());
    }


}