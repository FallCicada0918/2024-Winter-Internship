package com.example.hotal.service.impl;
import com.example.hotal.mapper.CustomerMapper;
import com.example.hotal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public boolean authenticateCustomer(String customer_id, String customer_password) {
        return customerMapper.findByCustomernameAndPassword(customer_id, customer_password) != null;
    }
}
