package com.example.hotal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomResultObject {
    private Integer review_id;
    private String review_content;
    private String customer_account;
    private Integer customer_id;

    // Getters and Setters
}