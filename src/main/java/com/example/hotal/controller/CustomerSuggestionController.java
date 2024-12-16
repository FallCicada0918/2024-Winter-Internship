package com.example.hotal.controller;

import com.example.hotal.entity.Customer;
import com.example.hotal.entity.CustomerSuggestion;
import com.example.hotal.entity.EsportsEquipment;
import com.example.hotal.service.CustomerSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/suggestion")
public class CustomerSuggestionController {
    @Autowired
    private CustomerSuggestionService customerSuggestionService;


    @GetMapping("/suggestions")
    public String showSuggestionForm(Model model, HttpSession session) {
        Customer customer=(Customer)session.getAttribute("user");
        Integer customerId = customer.getCustomer_id();
        Integer orderId = customerSuggestionService.getFirstOrderIdByCustomerId(customerId);
        CustomerSuggestion suggestion = new CustomerSuggestion();
        suggestion.setOrder_id(orderId);
        model.addAttribute("suggestion", suggestion);

        return "suggestions";
    }

    @PostMapping("/send")
    public String submitSuggestion(@ModelAttribute CustomerSuggestion suggestion) {
        suggestion.setDate(LocalDateTime.now());
        customerSuggestionService.submitSuggestion(suggestion);
        return "redirect:/suggestion/suggestions";
    }

    @GetMapping("/select")
    public String getEsportsEquipmentById(@RequestParam Integer suggestionId, Model model) {
        CustomerSuggestion customerSuggestion = customerSuggestionService.getCustomerSuggestionById(suggestionId);
        model.addAttribute("customerSuggestion", customerSuggestion);
        return "suggestionmanage";
    }

    @GetMapping("/")
    public String getAllEsportsEquipment(Model model) {
        List<CustomerSuggestion> allcustomerSuggestion = customerSuggestionService.getAllCustomerSuggestions();
        model.addAttribute("allcustomerSuggestion",allcustomerSuggestion);
        return "suggestionmanage";
    }
}
