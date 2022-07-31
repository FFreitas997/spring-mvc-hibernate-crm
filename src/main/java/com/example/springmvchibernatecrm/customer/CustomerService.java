package com.example.springmvchibernatecrm.customer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    @Transactional
    public void createCustomer(Customer customer) {
        repository.save(customer);
    }
}
