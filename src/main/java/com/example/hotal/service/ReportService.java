package com.example.hotal.service;

import java.util.Map;

public interface ReportService {
    Map<String, Double> getDailySales(String filePath);
    Map<String, Double> getMonthlySales(String filePath);
    Map<String, Double> getLastMonthDailySales(String filePath);
    Map<String, Integer> getRoomSelection(String filePath); // 新增方法
}