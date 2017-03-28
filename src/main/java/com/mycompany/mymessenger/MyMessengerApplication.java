package com.mycompany.mymessenger;

import javax.xml.ws.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mycompany.mymessenger.service.ConversationService;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MyMessengerApplication implements CommandLineRunner {    

    @Autowired
    ConversationService service;

    @Value("${service.port}")
    private String servicePort;
    
     @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
    
    @Override
    public void run(String... args) throws Exception {
        Endpoint.publish("http://localhost:" + servicePort
                + "/service/conversation", service);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyMessengerApplication.class, args);
    }
}
