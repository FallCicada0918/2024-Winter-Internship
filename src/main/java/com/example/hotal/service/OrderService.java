package com.example.hotal.service;

import com.example.hotal.entity.EsportsEquipment;
import com.example.hotal.entity.Order;

import java.util.List;


import java.time.LocalDateTime;

public interface OrderService {
    void addOrder(Order order);

    Order getOrderById(Integer orderId);

    List<Order> getAllOrder();

    void updateOrder(Order order);

    void deleteOrder(Integer orderId);

    List<Order> getOrdersByCustomerId(Integer customerId);

    void completeOrder(Integer orderId);

    void bookRoom(Integer customer_id,
                  Integer room_id,
                  LocalDateTime check_in_time,
                  LocalDateTime check_out_time);
    void updateOrderStatus(Integer orderId, String newStatus);
}
