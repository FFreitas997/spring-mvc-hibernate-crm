package com.example.springmvchibernatecrm.customer;

import com.example.springmvchibernatecrm.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Value("${customer.exception.empty-list}")
    private String emptyCustomerListMessage;

    public CustomerController(CustomerService service) {
        this.service = service;
    }


    @GetMapping("/list")
    public String getListOfCustomers(Model model) throws ResourceNotFoundException {
        logger.info("GET /customers/list requested");
        List<Customer> customers = service.getCustomers();
        customers.forEach((Customer customer) -> logger.info(customer.toString()));
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException(emptyCustomerListMessage);
        }
        model.addAttribute("customers", customers);
        return "customers/list-customers";
    }

    @PostMapping("/processForm")
    public String processForm() {

    }

  /*  @PostMapping("/create")
    public String createCustomer(@Valid @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) throws BadRequestException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Please create a valid customer", bindingResult);
        }
        this.service.createCustomer(customerDTO.toCustomer());
        return "customers/create-customer";
    }*/
}
