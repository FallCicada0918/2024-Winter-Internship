package com.example.hotal.service.impl;

import com.example.hotal.entity.EsportsEquipment;
import com.example.hotal.mapper.EsportsEquipmentMapper;
import com.example.hotal.service.EsportsEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsportsEquipmentServiceImpl implements EsportsEquipmentService {
    @Autowired
    private EsportsEquipmentMapper esportsEquipmentMapper;

    @Override
    public void addEsportsEquipment(EsportsEquipment equipment) {
        esportsEquipmentMapper.insertEsportsEquipment(equipment);
    }

    @Override
    public EsportsEquipment getEsportsEquipmentById(Integer equipmentId) {
        return esportsEquipmentMapper.getEsportsEquipmentById(equipmentId);
    }

    @Override
    public List<EsportsEquipment> getAllEsportsEquipment() {
        return esportsEquipmentMapper.getAllEsportsEquipment();
    }

    @Override
    public void updateEsportsEquipment(EsportsEquipment equipment) {
        esportsEquipmentMapper.updateEsportsEquipment(equipment);
    }

    @Override
    public void deleteEsportsEquipment(Integer equipmentId) {
        esportsEquipmentMapper.deleteEsportsEquipment(equipmentId);
    }
}
