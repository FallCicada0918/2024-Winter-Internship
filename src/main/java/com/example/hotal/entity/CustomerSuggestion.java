package com.example.hotal.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSuggestion {
    private Integer suggestion_id;
    private Integer order_id;
    private String feedback_content;
    private LocalDateTime date;
}
