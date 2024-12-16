package com.example.hotal.service.impl;

import com.example.hotal.entity.CustomerSuggestion;
import com.example.hotal.mapper.CustomerSuggestionMapper;
import com.example.hotal.mapper.OrderMapper;
import com.example.hotal.service.CustomerSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerSuggestionServicelmpl implements CustomerSuggestionService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CustomerSuggestionMapper customerSuggestionMapper;

    public Integer getFirstOrderIdByCustomerId(Integer customerId) {
        return orderMapper.getFirstOrderIdByCustomerId(customerId);
    }

    public void submitSuggestion(CustomerSuggestion suggestion) {
        customerSuggestionMapper.insertCustomerSuggestion(suggestion);
    }


    @Override
    public CustomerSuggestion getCustomerSuggestionById(Integer suggestionId) {
        return customerSuggestionMapper.getCustomerSuggestionById(suggestionId);
    }

    @Override
    public List<CustomerSuggestion> getAllCustomerSuggestions() {
        return customerSuggestionMapper.getAllCustomerSuggestions();
    }

    @Override
    public void updateCustomerSuggestion(CustomerSuggestion suggestion) {
        customerSuggestionMapper.updateCustomerSuggestion(suggestion);
    }

    @Override
    public void deleteCustomerSuggestion(Integer suggestionId) {
        customerSuggestionMapper.deleteCustomerSuggestion(suggestionId);
    }
}
