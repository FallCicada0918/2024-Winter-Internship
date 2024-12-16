package com.example.hotal.controller;

import com.example.hotal.entity.EsportsEquipment;
import com.example.hotal.service.EsportsEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/esports-equipment")
public class EsportsEquipmentController {

    @Autowired
    private EsportsEquipmentService esportsEquipmentService;

    @PostMapping("/add")
    public String addEsportsEquipment(@RequestParam String equipmentName, @RequestParam String equipmentType, @RequestParam String parameterInfo) {
        EsportsEquipment equipment = new EsportsEquipment();
        equipment.setEquipment_name(equipmentName);
        equipment.setEquipment_type(equipmentType);
        equipment.setParameter_info(parameterInfo);

        esportsEquipmentService.addEsportsEquipment(equipment);
        return "redirect:/esports-equipment/";
    }

    @GetMapping  ("/select")
    public String getEsportsEquipmentById(@RequestParam Integer equipmentId, Model model) {
        EsportsEquipment esportsEquipment = esportsEquipmentService.getEsportsEquipmentById(equipmentId);
        model.addAttribute("esportsEquipment", esportsEquipment);
        return "equipmanage";
    }

    @GetMapping("/")
    public String getAllEsportsEquipment(Model model) {
        List<EsportsEquipment> allEsportsEquipment = esportsEquipmentService.getAllEsportsEquipment();
        model.addAttribute("allEsportsEquipment", allEsportsEquipment);
        return "equipmanage";
    }

    @RequestMapping("/update")
    public String updateEsportsEquipment(@RequestParam Integer updateId, @RequestParam String updateName, @RequestParam String updateType, @RequestParam String updateParameter) {
        EsportsEquipment equipment = new EsportsEquipment();
        equipment.setEquipment_id(updateId);
        equipment.setEquipment_name(updateName);
        equipment.setEquipment_type(updateType);
        equipment.setParameter_info(updateParameter);
        esportsEquipmentService.updateEsportsEquipment(equipment);
        return "redirect:/esports-equipment/";
    }

    @RequestMapping("/delete")
    public String deleteEsportsEquipment(@RequestParam Integer deleteId) {
        esportsEquipmentService.deleteEsportsEquipment(deleteId);
        return "redirect:/esports-equipment/";
    }

    @GetMapping("/init")
    public String init() {
        return "equipmanage";
    }
}