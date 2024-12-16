package com.example.hotal.service.impl;

import com.example.hotal.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private ConcurrentHashMap<String, String> verificationCodes = new ConcurrentHashMap<>();

    @Override
    public void sendVerificationCode(String email) {
        String code = generateVerificationCode();
        verificationCodes.put(email, code);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("3111291511@qq.com"); // 确保此地址与授权用户相同
        message.setSubject("邮箱验证码");
        message.setText("您的验证码是: " + code);

        mailSender.send(message);
    }

    @Override
    public boolean verifyCode(String email, String code) {
        return code.equals(verificationCodes.get(email));
    }

    private String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 9000) + 1000); // 4位验证码
    }
}