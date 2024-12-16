package com.example.hotal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Integer admin_id;
    private String admin_account;
    private String admin_password;
    private String name;
    private String phone_number;
    public Object getPassword() {
        return admin_password;
    }
    public Object getUsername() {
        return admin_id;
    }
}
