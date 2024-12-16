package com.example.hotal.service.impl;

import com.example.hotal.entity.Order;
import com.example.hotal.mapper.OrderMapper;
import com.example.hotal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    public void addOrder(Order order) {
        orderMapper.insertOrder(order);
    }
    public void deleteOrder(Integer orderId) {
        orderMapper.deleteOrder(orderId);
    }
    public Order getOrderById(Integer orderId) {
        return orderMapper.getOrderById(orderId);
    }
    public List<Order> getAllOrder() {
        return orderMapper.getAllOrders();
    }
    public void updateOrder(Order order)
    {
        orderMapper.updateOrder(order);
    }

    public void updateOrderStatus(Integer orderId, String newStatus) {
        Order order = orderMapper.getOrderById(orderId);
        if (order != null) {
            order.setOrder_status(newStatus);
            orderMapper.updateOrder(order);
        }
    }
    public List<Order> getOrdersByCustomerId(Integer customerId) {
        return orderMapper.getOrdersByCustomerId(customerId);
    }


    public void completeOrder( Integer orderId) {
        orderMapper.completeOrder(orderId);
    }
    public void bookRoom(Integer customer_id,
                            Integer room_id,
                            LocalDateTime check_in_time,
                            LocalDateTime check_out_time){
        Order order=new Order(1,customer_id,room_id,check_in_time,check_out_time,"未开始");
        orderMapper.insertOrder(order);
    }
}
