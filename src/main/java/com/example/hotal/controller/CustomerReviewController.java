package com.example.hotal.controller;

import com.example.hotal.entity.CustomResultObject;
import com.example.hotal.entity.CustomerReview;
import com.example.hotal.service.CustomerReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/review")
public class CustomerReviewController {

    @Autowired
    private CustomerReviewService customerReviewService;

    @GetMapping("")
    public String getReview(Model model, HttpSession session) {
        List<CustomResultObject> reviews = customerReviewService.getReview();

        if (reviews != null && !reviews.isEmpty()) {
            model.addAttribute("reviews", reviews); // 将整个列表传递给视图
        } else {
            model.addAttribute("error", "Review not found");
        }
        Boolean loginSuccess = (Boolean) session.getAttribute("loginSuccess");
        model.addAttribute("loginSuccess", loginSuccess != null ? loginSuccess : false); // Retrieve loginSuccess from session

        return "review";
    }

    @PostMapping("/addreview")
    public String insertReview(@RequestParam Integer customer_id, @RequestParam String review_content, Model model, HttpSession session) {
        // 使用敏感词服务替换评论内容中的敏感词
        String processedContent = customerReviewService.replaceSensitiveWords(review_content);

        LocalDateTime date = LocalDateTime.now(); // 获取当前时间
        CustomerReview customerReview = new CustomerReview(customer_id, processedContent, date);
        customerReviewService.insertReview(customerReview);
        model.addAttribute("customerReview", customerReview);
        Boolean loginSuccess = (Boolean) session.getAttribute("loginSuccess");
        model.addAttribute("loginSuccess", loginSuccess != null ? loginSuccess : false); // Retrieve loginSuccess from session
        return "redirect:/review";
    }
}