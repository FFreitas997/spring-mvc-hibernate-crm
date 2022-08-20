package com.example.springmvchibernatecrm.customer;

import com.example.springmvchibernatecrm.exceptions.BadRequestException;
import com.example.springmvchibernatecrm.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Value("${customer.exception.empty-list}")
    private String messageErrorEmptyListCustomer;
    @Value("${customer.exception.invalid-parameter}")
    private String messageErrorCreateCustomer;

    public CustomerController(CustomerService service) {
        this.service = service;
    }


    @GetMapping("/list")
    public String getListOfCustomers(Model model) throws ResourceNotFoundException {
        logger.info("GET /customers/list requested");
        List<Customer> customers = service.getCustomersSorted("asc", "firstName");
        if (customers.isEmpty())
            throw new ResourceNotFoundException(messageErrorEmptyListCustomer);
        customers.forEach((Customer customer) -> logger.info(customer.toString()));
        model.addAttribute("customers", customers);
        return "customers/list-customers";
    }

    @GetMapping("/showForm")
    public String getShowForm(Model model) {
        CustomerDTO customerDTO = new CustomerDTO();
        model.addAttribute("customer", customerDTO);
        return "customers/form-customer";
    }

    @PostMapping("/create")
    public String createCustomer(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult bindingResult) throws BadRequestException {
        logger.info(customerDTO.toString());
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(messageErrorCreateCustomer, bindingResult.getFieldErrors());
        }
        this.service.createCustomer(customerDTO.toCustomer());
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormUpdate")
    public String getShowFormUpdateCustomer(@RequestParam("customerID") Integer id, Model model) {
        Customer customer = service.getCustomerByID(id);
        model.addAttribute("customer", customer);
        return "customers/form-customer";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerID") Integer id) {
        service.deleteCustomerByID(id);
        return "redirect:/customer/list";
    }
}
