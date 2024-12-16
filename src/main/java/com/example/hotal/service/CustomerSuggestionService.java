package com.example.hotal.service;

import com.example.hotal.entity.CustomerSuggestion;

import java.util.List;

public interface CustomerSuggestionService {


    CustomerSuggestion getCustomerSuggestionById(Integer suggestionId);

    List<CustomerSuggestion> getAllCustomerSuggestions();

    void updateCustomerSuggestion(CustomerSuggestion suggestion);

    void deleteCustomerSuggestion(Integer suggestionId);

    Integer getFirstOrderIdByCustomerId(Integer customerId);
    void submitSuggestion(CustomerSuggestion suggestion);

}