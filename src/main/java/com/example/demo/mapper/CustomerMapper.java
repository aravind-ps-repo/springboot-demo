package com.example.demo.mapper;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.model.Customer;

public class CustomerMapper {
    public static CustomerDTO toDto(Customer c) {
        if (c == null) return null;
        CustomerDTO dto = new CustomerDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setEmail(c.getEmail());
        dto.setAge(c.getAge());
        return dto;
    }

    public static Customer toEntity(CustomerDTO dto) {
        if (dto == null) return null;
        Customer c = new Customer();
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setAge(dto.getAge());
        return c;
    }

    public static void updateEntity(Customer c, CustomerDTO dto) {
        if (dto.getName() != null) c.setName(dto.getName());
        if (dto.getEmail() != null) c.setEmail(dto.getEmail());
        if (dto.getAge() != null) c.setAge(dto.getAge());
    }
}
