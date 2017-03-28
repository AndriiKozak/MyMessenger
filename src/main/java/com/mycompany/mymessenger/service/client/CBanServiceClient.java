package com.mycompany.mymessenger.service.client;

import java.util.HashSet;
import java.util.Set;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

@Component
public class CBanServiceClient {

    public Set<Long> getBanned(Long id) throws SOAPException {
        SOAPConnectionFactory myFct = SOAPConnectionFactory.newInstance();
        SOAPConnection myCon = myFct.createConnection();
        MessageFactory myMsgFct = MessageFactory.newInstance();
        SOAPMessage message = myMsgFct.createMessage();
        SOAPPart mySPart = message.getSOAPPart();
        SOAPEnvelope myEnvp = mySPart.getEnvelope();
        SOAPBody body = myEnvp.getBody();
        Name bodyName = myEnvp.createName("baned", "ser", "http://service.mymessenger.mycompany.com/");
        SOAPBodyElement gltp = body.addBodyElement(bodyName);
        Name myContent = myEnvp.createName("arg0");
        SOAPElement mySymbol = gltp.addChildElement(myContent);
        mySymbol.addTextNode(String.valueOf(id));

        SOAPMessage answer = myCon.call(message, "http://localhost:8088/mockbanServiceImplPortBinding");

        Set<Long> result = new HashSet<>();
        Document document = answer.getSOAPBody().extractContentAsDocument();
        NodeList returns = document.getElementsByTagName("return");
        for (int i = 0; i < returns.getLength(); i++) {
            result.add(Long.parseLong(returns.item(i).getChildNodes().item(0).getNodeValue()));

        }
        return result;
    }
}
