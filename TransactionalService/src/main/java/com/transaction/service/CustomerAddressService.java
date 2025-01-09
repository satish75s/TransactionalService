package com.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.entity.Address;
import com.transaction.entity.Customer;
import com.transaction.repository.AddressRepository;
import com.transaction.repository.CustomerRepository;

@Service
public class CustomerAddressService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional  // Ensure that both entities are saved in one transaction
    public Customer addCustomerAndAddress(Customer customer, Address address) {
        // Save the address first, to avoid issues with uninitialized customer in address
    	Address address1 = addressRepository.save(address);

        // Set the relationship from address to customer
        customer.setAddress(address1);

        // Save the customer, which will automatically save the associated address due to cascade
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
