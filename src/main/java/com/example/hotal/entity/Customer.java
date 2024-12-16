package com.example.hotal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer customer_id;
    private String customer_account;
    private String customer_password;
    private String id_card_number;
    private String name;
    private String phone_number;
    public Customer(String customer_account, String customer_password, String id_card_number, String name, String phone_number) {
        this.customer_account = customer_account;
        this.customer_password = customer_password;
        this.id_card_number = id_card_number;
        this.name = name;
        this.phone_number = phone_number;
    }
    public Object getPassword() {
        return customer_password;
    }
    public Object getUsername() {
        return customer_id;
    }
}
