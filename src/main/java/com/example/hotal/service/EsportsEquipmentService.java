package com.example.hotal.service;

import com.example.hotal.entity.EsportsEquipment;

import java.util.List;

public interface EsportsEquipmentService {

    void addEsportsEquipment(EsportsEquipment equipment);

    EsportsEquipment getEsportsEquipmentById(Integer equipmentId);

    List<EsportsEquipment> getAllEsportsEquipment();

    void updateEsportsEquipment(EsportsEquipment equipment);

    void deleteEsportsEquipment(Integer equipmentId);

}