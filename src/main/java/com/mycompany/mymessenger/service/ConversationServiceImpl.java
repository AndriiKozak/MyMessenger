package com.mycompany.mymessenger.service;

import com.mycompany.mymessenger.domain.Customer;
import com.mycompany.mymessenger.domain.Message;
import com.mycompany.mymessenger.repository.MessageRepository;
import com.mycompany.mymessenger.service.client.CBanServiceClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.soap.SOAPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@WebService(endpointInterface = "com.mycompany.mymessenger.service.ConversationService")
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    CBanServiceClient client;

    @Override
    public List<Message> conversation(Long interlocutorId1, Long interlocutorId2) {
        List<Message> result = new ArrayList<>();
        try {
            Customer interlocutor1 = new Customer();
            Customer interlocutor2 = new Customer();
            interlocutor1.setId(interlocutorId1);
            interlocutor2.setId(interlocutorId2);

            result.addAll(messageRepository.findBySenderAndReciever(interlocutor1, interlocutor2));
            Set<Long> banList = client.getBanned(interlocutorId1);
            if (!banList.contains(interlocutorId2)) {
                result.addAll(messageRepository.findBySenderAndReciever(interlocutor2, interlocutor1));
            }

        } catch (SOAPException ex) {
            Logger.getLogger(ConversationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
