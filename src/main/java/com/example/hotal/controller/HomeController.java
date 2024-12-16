package com.example.hotal.controller;
import com.example.hotal.entity.Admin;
import com.example.hotal.entity.Customer;
import com.example.hotal.entity.CustomerSuggestion;
import com.example.hotal.service.CustomerSuggestionService;
import com.example.hotal.service.EmailService;
import com.example.hotal.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;
    @Autowired
    private CustomerSuggestionService customerSuggestionService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/games")
    public String games() {
        return "games";
    }

    @GetMapping("/review")
    public String comments() {
        return "review";
    }

    @GetMapping("/rooms")
    public String booking(@SessionAttribute(name = "user", required = false) Customer user, Model model) {
        if (user == null) {
            return "home";
        }
        return "redirect:/rooms/";
    }

    @GetMapping("/suggestions")
    public String suggestions(@SessionAttribute(name = "user", required = false) Customer user, Model model,HttpSession session) {
        if (user == null) {
            return "home";
        }
        Customer customer=(Customer)session.getAttribute("user");
        Integer customerId = customer.getCustomer_id();
        Integer orderId = customerSuggestionService.getFirstOrderIdByCustomerId(customerId);
        CustomerSuggestion suggestion = new CustomerSuggestion();
        suggestion.setOrder_id(orderId);
        model.addAttribute("suggestion", suggestion);
        return "suggestions";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) String userType, HttpSession session, Model model) {
        boolean loginSuccess = false;

        if ("user".equals(userType)) {
            Customer user = homeService.authenticateUser(username, password, userType);
            if (user != null) {
                session.setAttribute("user", user);
                model.addAttribute("user", user);
                loginSuccess = true;
                session.setAttribute("loginSuccess", loginSuccess); // Store loginSuccess in session
                model.addAttribute("loginSuccess", loginSuccess);
                return "home";
            }
        } else if ("manager".equals(userType)) {
            Admin admin = homeService.authenticateAdmin(username, password, userType);
            if (admin != null) {
                session.setAttribute("admin", admin);
                loginSuccess = true;
                session.setAttribute("loginSuccess", loginSuccess); // Store loginSuccess in session
                model.addAttribute("loginSuccess", loginSuccess);
                return "equipmanage";
            }
        }
        return "home";
    }

    @PostMapping("/sendVerificationCode")
    @ResponseBody
    public String sendVerificationCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        System.out.println("Received email: " + email); // 打印 email

        try {
            emailService.sendVerificationCode(email);
            System.out.println("发送验证码到: " + email); // 打印中文信息
            return "验证码已发送";
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常堆栈信息
            return "发送验证码失败";
        }
    }
    @PostMapping("/register")
    public String register(@RequestParam String newUsername, @RequestParam String newPassword, @RequestParam String idCardNumber, @RequestParam String name, @RequestParam String phoneNumber, @RequestParam String email, @RequestParam String verificationCode, Model model) {
        if (!emailService.verifyCode(email, verificationCode)) {
            System.out.println(email);
            model.addAttribute("error", "验证码无效");
            return "register";
        }
        Customer customer = new Customer(newUsername, newPassword ,idCardNumber,name,phoneNumber);
        homeService.addUser(customer);
        // 注册逻辑
        return "home";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user"); // 清除用户登录状态
        session.removeAttribute("admin"); // 清除管理员登录状态
        session.removeAttribute("loginSuccess");
        return "home"; // 重定向到主页
    }
}