package com.example.hotal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReview {
    private Integer review_id;
    private Integer customer_id;
    private String review_content;
    private LocalDateTime date;

    public CustomerReview(Integer customerId, String reviewContent, LocalDateTime date) {
        this.customer_id = customerId;
        this.review_content = reviewContent;
        this.date = date;
    }
}