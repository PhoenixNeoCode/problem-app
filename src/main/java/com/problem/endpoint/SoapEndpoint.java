package com.problem.endpoint;

import com.problem.IsActiveRequest;
import com.problem.Problem;
import com.problem.ReadSomethingRequest;
import com.problem.ReadSomethingResponse;
import com.problem.config.Roles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
@Slf4j
public class SoapEndpoint implements Problem {
    private static final String TARGET_NAMESPACE = "http://problem.com";

    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = "isCLIRActiveRequest")
    @ResponsePayload
    @Secured(Roles.PERMISSION)
    public boolean isActive(@RequestPayload IsActiveRequest request) {
        return true;
    }

    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = "readSomethingRequest")
    @ResponsePayload
    @Secured(Roles.PERMISSION)
    public ReadSomethingResponse readSomething(@RequestPayload ReadSomethingRequest request) {
        ReadSomethingResponse response = new ReadSomethingResponse();
        response.setSomething("something");
        return response;
    }
}


