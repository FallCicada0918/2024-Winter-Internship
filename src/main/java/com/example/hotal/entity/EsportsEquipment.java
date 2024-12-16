package com.example.hotal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsportsEquipment {
    private Integer equipment_id;
    private String equipment_name;
    private String equipment_type;
    private String parameter_info;
}
