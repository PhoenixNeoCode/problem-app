package com.problem.endpoint;

import com.problem.config.WebServiceConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ControllerTestBeans.class, WebServiceConfig.class})
@WithMockUser
public class SoapEndpointTests {

    private final static String VALID_ROLE = "PERMISSION";

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @Before
    public void setup() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    @WithMockUser(roles = VALID_ROLE)
    public void readSomethingSoap_Success() throws Exception {

        Source requestPayload = new StringSource(SoapTestUtility.getSomethingRequestXml());
        Source responsePayload = new StringSource(SoapTestUtility.getSomethingResponseXml());

        mockClient
            .sendRequest(withPayload(requestPayload))
            .andExpect(noFault())
            .andExpect(payload(responsePayload));
    }

    @Ignore
    @Test
    @WithMockUser(roles = VALID_ROLE)
    public void isActivSoap_Success() throws Exception {

        Source requestPayload = new StringSource(SoapTestUtility.getActiveRequestXml());
        Source responsePayload = new StringSource(SoapTestUtility.getActiveResponseXml());

        mockClient
            .sendRequest(withPayload(requestPayload))
            .andExpect(noFault())
            .andExpect(payload(responsePayload));
    }
}
