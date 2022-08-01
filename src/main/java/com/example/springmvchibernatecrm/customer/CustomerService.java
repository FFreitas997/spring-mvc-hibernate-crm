package com.example.springmvchibernatecrm.customer;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

    @Transactional(readOnly = true)
    public List<Customer> getCustomersSorted(String direction, String fieldName) {
        Sort.Direction direct = Objects.equals(direction, "desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direct, fieldName);
        return repository.findAll(sort);
    }

    @Transactional
    public void createCustomer(Customer customer) {
        repository.save(customer);
    }
}
