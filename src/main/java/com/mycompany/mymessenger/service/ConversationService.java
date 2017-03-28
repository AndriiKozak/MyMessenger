package com.mycompany.mymessenger.service;

import com.mycompany.mymessenger.domain.Message;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService

public interface ConversationService {

    @WebMethod(operationName = "conversation")

    List<Message> conversation(Long interlocutorId1, Long interlocutorId2);

}
