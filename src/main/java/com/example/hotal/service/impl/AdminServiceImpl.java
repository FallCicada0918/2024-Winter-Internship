package com.example.hotal.service.impl;

import com.example.hotal.mapper.AdminMapper;
import com.example.hotal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


}