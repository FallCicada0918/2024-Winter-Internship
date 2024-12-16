package com.example.hotal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer order_id;
    private Integer customer_id;
    private Integer room_id;
    private LocalDateTime check_in_time;
    private LocalDateTime check_out_time;
    private String order_status;
    private String room_type;    // 新增字段
    private Integer room_capacity; // 新增字段
    public Date getCheckInTimeAsDate() {
        return Date.from(check_in_time.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getCheckOutTimeAsDate() {
        return Date.from(check_out_time.atZone(ZoneId.systemDefault()).toInstant());
    }
    public Order(Integer order_id,Integer customer_id,Integer room_id,LocalDateTime check_in_time,LocalDateTime check_out_time,String order_status){
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.room_id = room_id;
        this.check_in_time = check_in_time;
        this.check_out_time = check_out_time;
        this.order_status = order_status;
    }
}
