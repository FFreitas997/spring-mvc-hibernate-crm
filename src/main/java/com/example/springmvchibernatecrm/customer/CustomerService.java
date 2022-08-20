package com.example.springmvchibernatecrm.customer;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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
        if (fieldName == null)
            return Collections.emptyList();
        Sort.Direction direct = direction != null && direction.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC;
        return repository.findAll(Sort.by(direct, fieldName));
    }

    @Transactional
    public void createCustomer(Customer customer) {
        if (customer == null)
            throw new NullPointerException("Customer param in createCustomer is null");
        repository.save(customer);
    }

    public Customer getCustomerByID(Integer id) {
        if (id == null)
            throw new NullPointerException("Customer ID is null in getCustomerByID method");
        return repository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no such customer by id " + id));
    }

    public void deleteCustomerByID(Integer id){
        if (id == null)
            throw new NullPointerException("Customer ID is null in deleteCustomerByID method");
        repository.deleteById(id);
    }
}
