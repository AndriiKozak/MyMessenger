package com.mycompany.mymessenger.repository;

import com.mycompany.mymessenger.domain.Customer;
import com.mycompany.mymessenger.domain.Message;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findBySenderAndReciever(Customer sender, Customer reciever);
}
