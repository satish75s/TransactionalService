package com.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.dto.CustomerAddressRequest;
import com.transaction.entity.Address;
import com.transaction.entity.Customer;
import com.transaction.service.CustomerAddressService;

@RestController
@RequestMapping("/api/customers")
public class CustomerAddressController {

    @Autowired
    private CustomerAddressService customerAddressService;

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomerWithAddress(@RequestBody CustomerAddressRequest request) {
        // Create Customer and Address entities from the request
        Customer customer = new Customer();
        customer.setName(request.getCustomerName());

        Address address = new Address();
        address.setStreet(request.getStreet());

        // Save the customer and address in one transaction
        Customer savedCustomer = customerAddressService.addCustomerAndAddress(customer, address);

        return ResponseEntity.ok(savedCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = customerAddressService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }
}
