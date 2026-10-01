package com.staj.faturalama.service;

import com.staj.faturalama.entity.Customer;
import com.staj.faturalama.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Müşteri bulunamadı. ID: " + id));
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = getCustomerById(id);
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setSurname(updatedCustomer.getSurname());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setNationalId(updatedCustomer.getNationalId());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long id){
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }
}
