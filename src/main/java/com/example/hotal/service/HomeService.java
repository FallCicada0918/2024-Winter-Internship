package com.example.hotal.service;

import com.example.hotal.entity.Admin;
import com.example.hotal.entity.Customer;

public interface HomeService {
    Customer authenticateUser(String username, String password, String userType);
    Admin authenticateAdmin(String username,String password,String userType);
    void addUser(Customer customer);

}
