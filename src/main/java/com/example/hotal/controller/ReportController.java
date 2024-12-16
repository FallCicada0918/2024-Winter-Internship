package com.example.hotal.controller;

import com.example.hotal.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/sales")
    public String getSales(Model model) {
        String salesFilePath = "D:\\2exec\\hotal\\src\\main\\resources\\static\\orders.csv"; // Update with your actual file path
        String roomFilePath = "D:\\2exec\\hotal\\src\\main\\resources\\room_selection_random.csv"; // Update with your actual file path

        Map<String, Double> dailySales = reportService.getDailySales(salesFilePath);
        Map<String, Double> monthlySales = reportService.getMonthlySales(salesFilePath);
        Map<String, Double> lastMonthDailySales = reportService.getLastMonthDailySales(salesFilePath);
        Map<String, Integer> roomSelection = reportService.getRoomSelection(roomFilePath);

        model.addAttribute("dailySales", dailySales);
        model.addAttribute("monthlySales", monthlySales);
        model.addAttribute("lastMonthDailySales", lastMonthDailySales);
        model.addAttribute("roomSelection", roomSelection);
        return "sales";
    }
}