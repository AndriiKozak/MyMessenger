package com.mycompany.mymessenger.repository;

import com.mycompany.mymessanger.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MessageRepository extends CrudRepository<Message, Long> {

}
