package com.mycompany.mymessenger;

import com.mycompany.mymessenger.service.ConversationServiceImpl;
import javax.xml.ws.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mycompany.mymessenger.service.ConversationService;

@SpringBootApplication
public class MyMessengerApplication  implements CommandLineRunner {
    @Autowired
    ConversationService service;
 
    @Value("${service.port}")
    private String servicePort;

    @Override
    public void run(String... args) throws Exception {
        Endpoint.publish("http://localhost:" + servicePort
                + "/service/hello-world", service);
    }            
    
 

    public static void main(String[] args) {
        SpringApplication.run(MyMessengerApplication.class, args);
    }
}
