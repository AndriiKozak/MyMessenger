package com.mycompany.mymessenger.controller;

import com.mycompany.mymessenger.domain.Customer;
import com.mycompany.mymessenger.repository.CustomerRepository;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers/xml")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Customer> getCustomerXML(@PathVariable Long id) throws Exception {
        Customer customer = customerRepository.findOne(id);
        return ResponseEntity.ok(customer);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Iterable<Customer>> getAllCustomersXML() throws URISyntaxException {
        Iterable<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Long> count() throws URISyntaxException {
        Long count = customerRepository.count();
        return ResponseEntity.ok(count);
    }
}
