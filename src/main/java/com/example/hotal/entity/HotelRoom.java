package com.example.hotal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoom {
    private Integer room_id;
    private String room_number;
    private String room_type;
    private Integer room_capacity;
    private String room_status;
    private String bed_size;
    private String other_info;
    private Double price;
    private byte[] img1;
    private byte[] img2;
    private byte[] img3;
    private String img1Base64;
    private String img2Base64;
    private String img3Base64;
}
