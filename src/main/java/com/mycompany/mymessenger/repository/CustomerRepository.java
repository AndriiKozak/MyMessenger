package com.mycompany.mymessenger.repository;

import com.mycompany.mymessenger.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Override
    @RestResource(exported = false)
    public void deleteAll();

    @Override
    @RestResource(exported = false)
    public void delete(Iterable<? extends Customer> itrbl);

    @Override
    @RestResource(exported = false)
    public void delete(Customer t);

    @Override
    @RestResource(exported = false)
    public void delete(Long id);

    @Override
    @RestResource(exported = false)
    public <S extends Customer> Iterable<S> save(Iterable<S> itrbl);

    @Override
    @RestResource(exported = false)
    public <S extends Customer> S save(S s);

}
