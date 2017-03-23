/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mymessenger.service;

import com.mycompany.mymessenger.domain.Customer;
import com.mycompany.mymessenger.domain.Message;
import com.mycompany.mymessenger.repository.MessageRepository;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@WebService(endpointInterface = "com.mycompany.mymessenger.service.ConversationService")
public class ConversationServiceImpl implements ConversationService {
    @Autowired
    MessageRepository messageRepository;
    @Override
    public List<Message> helloWorld(Long interlocutorId1, Long interlocutorId2) {
        Customer interlocutor1 = new Customer();
        Customer interlocutor2 = new Customer();
        interlocutor1.setId(interlocutorId1);
        interlocutor2.setId(interlocutorId2);
        List<Message> result = new ArrayList<>();
        result.addAll(messageRepository.findBySenderAndReciever(interlocutor1, interlocutor2));
        result.addAll(messageRepository.findBySenderAndReciever(interlocutor2, interlocutor1));
        return result;
    }

}
