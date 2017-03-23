package com.mycompany.mymessenger.controller;

import com.mycompany.mymessanger.domain.Message;
import com.mycompany.mymessanger.repository.MessageRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages/xml")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getMessageXML(@PathVariable Long id) throws Exception {
        Message message = messageRepository.findOne(id);
        return ResponseEntity.ok(message);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Iterable<Message>> getAllMessagesXML() throws URISyntaxException {
        Iterable<Message> messages = messageRepository.findAll();
        return ResponseEntity.ok(messages);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Long> count() throws URISyntaxException {
        Long count = messageRepository.count();
        return ResponseEntity.ok(count);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> postMessageXML(@RequestBody Message message) throws URISyntaxException {
        messageRepository.save(message);
        return ResponseEntity.created(new URI("/messages/xml/" + message.getId())).build();
    }

    @RequestMapping(value = "/multiple", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> postMultipleMessagesXML(@RequestBody List<Message> messages) throws URISyntaxException {
        messageRepository.save(messages);
        return ResponseEntity.created(new URI("/messages/xml/")).build();
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> deleteMessageXML(@RequestBody Message message) throws URISyntaxException {
        messageRepository.delete(message);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/multiple", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> deleteMultipleMessagesXML(@RequestBody List<Message> messages) throws URISyntaxException {
        messageRepository.delete(messages);
        return ResponseEntity.noContent().build();
    }

}
