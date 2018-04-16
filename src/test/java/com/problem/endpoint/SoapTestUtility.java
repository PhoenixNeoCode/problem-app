package com.problem.endpoint;

public class SoapTestUtility {

    static String getSomethingRequestXml() {
        return "<ns:readSomethingRequest xmlns:ns=\"http://problem.com\"><id>1234</id></ns:readSomethingRequest>";
    }

    static String getSomethingResponseXml() {
        return "<ns3:readSomethingResponse xmlns:ns3=\"http://problem" +
            ".com\"><something>something</something></ns3:readSomethingResponse>";
    }

    static String getActiveRequestXml() {
        return "<ns:isActiveRequest xmlns:ns=\"http://problem.com\"><id>1234</id></ns:isActiveRequest>";
    }

    static String getActiveResponseXml() {
        return "<ns3:isActiveResponse xmlns:ns3=\"http://problem.com\">true</ns3:isActiveResponse>";
    }
}
