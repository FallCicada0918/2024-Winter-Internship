package com.example.hotal.controller;

import com.example.hotal.entity.CustomResultObject;
import com.example.hotal.entity.Customer;
import com.example.hotal.service.CustomerReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MyReviewController {

    @Autowired
    private CustomerReviewService customerReviewService;

    @RequestMapping("/review/myreview")
    public String myReview(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("user");
        Integer customerId = customer.getCustomer_id();
        List<CustomResultObject> reviews = customerReviewService.myReview(customerId);
        model.addAttribute("reviews", reviews);
        return "myreview";
    }

    @RequestMapping("/deleteReview")
    public String deleteReview(HttpSession session, @RequestParam("review_id") Integer reviewId) {
        Customer customer = (Customer) session.getAttribute("user");
        Integer customerId = customer.getCustomer_id();
        customerReviewService.deleteReview(customerId, reviewId);
        return "redirect:/review/myreview";
    }
}