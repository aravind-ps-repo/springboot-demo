package com.example.demo.service.impl;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {
        if (repo.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Customer with email already exists");
        }
        Customer entity = CustomerMapper.toEntity(dto);
        Customer saved = repo.save(entity);
        return CustomerMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO getCustomerById(Long id) {
        Customer c = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return CustomerMapper.toDto(c);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers() {
        return repo.findAll().stream().map(CustomerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
        Customer c = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        CustomerMapper.updateEntity(c, dto);
        Customer updated = repo.save(c);
        return CustomerMapper.toDto(updated);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
        repo.deleteById(id);
    }
}
