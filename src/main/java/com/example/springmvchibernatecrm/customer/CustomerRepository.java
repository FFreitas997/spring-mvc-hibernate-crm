package com.example.springmvchibernatecrm.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByFirstNameContainsIgnoreCase(@NonNull String firstName);

    List<Customer> findByLastNameLike(String lastName);
}
