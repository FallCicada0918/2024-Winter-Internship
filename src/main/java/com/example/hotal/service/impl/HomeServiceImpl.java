package com.example.hotal.service.impl;

import com.example.hotal.entity.Admin;
import com.example.hotal.entity.Customer;
import com.example.hotal.mapper.AdminMapper;
import com.example.hotal.mapper.CustomerMapper;
import com.example.hotal.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class HomeServiceImpl implements HomeService{


        @Autowired
        private CustomerMapper customerMapper;

        @Autowired
        private AdminMapper adminMapper;
        @Override
        public Customer authenticateUser(String username, String password, String userType){
                Customer user = customerMapper.findByCustomernameAndPassword(username, password);
                if(user != null){
                    return user;
                }else{
                    return null;
            }
            }
            @Override
            public Admin authenticateAdmin(String username, String password, String userType){
                Admin manager = adminMapper.findByAdminnameAndPassword(username, password);
                if(manager != null){
                    return manager;
                }else{

                    return null;
                }
            }
    @Override
    public void addUser(Customer customer){
            customerMapper.insertCustomer(customer);
    }
}
