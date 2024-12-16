package com.example.hotal.controller;

import com.example.hotal.entity.Customer;
import com.example.hotal.entity.Order;
import com.example.hotal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/order-management")
public class OrderController {
    @Autowired
    private OrderService orderService;

//    @PostMapping("/add")
//    public String addOrder(@RequestParam String equipmentName, @RequestParam String equipmentType, @RequestParam String parameterInfo) {
//        Order order = new Order();
//        order.setOrder_id(equipmentName);
//        equipment.setEquipment_type(equipmentType);
//        equipment.setParameter_info(parameterInfo);
//
//        esportsEquipmentService.addEsportsEquipment(equipment);
//        return "index";
//    }

    @GetMapping("/orders")
    public String getOrders(HttpSession session, Model model) {
        Customer customer=(Customer)session.getAttribute("user");
        if (customer == null) {
            return "home";
        }
        Integer customerId = customer.getCustomer_id();
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/orders/cancel")
    public String cancelOrder(@RequestParam("orderId") Integer orderId,HttpSession session,Model model) {
        boolean loginSuccess = false;
        orderService.deleteOrder(orderId);
        loginSuccess = true;
        session.setAttribute("loginSuccess", loginSuccess); // Store loginSuccess in session
        model.addAttribute("loginSuccess", loginSuccess);
        Customer customer=(Customer)session.getAttribute("user");
        Integer customerId = customer.getCustomer_id();
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/orders/complete")
    public String completeOrder(@RequestParam("orderId") Integer orderId,HttpSession session,Model model) {
        boolean loginSuccess = false;
        orderService.completeOrder(orderId);
        loginSuccess = true;
        session.setAttribute("loginSuccess", loginSuccess); // Store loginSuccess in session
        model.addAttribute("loginSuccess", loginSuccess);
        Customer customer=(Customer)session.getAttribute("user");
        Integer customerId = customer.getCustomer_id();
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        model.addAttribute("orders", orders);
        return "orders";
    }


    @GetMapping("/select")
    public String getOrderById(@RequestParam Integer orderId, Model model) {
        Order order =orderService.getOrderById(orderId);
        model.addAttribute("order", order);
        return "ordermanage";
    }

    @GetMapping("/")
    public String getAllOrder(Model model) {
        List<Order> allOrder = orderService.getAllOrder();
        model.addAttribute("allOrder", allOrder);
        return "ordermanage";
    }

    @RequestMapping("/update")
    public String updateOrder(
            @RequestParam Integer updateId,
            @RequestParam Integer customerId,
            @RequestParam Integer roomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkInTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkOutTime,
            @RequestParam String orderStatus) {

        Order order = new Order();
        order.setOrder_id(updateId); // 确保设置订单 ID
        order.setCustomer_id(customerId);
        order.setRoom_id(roomId);
        order.setCheck_in_time(checkInTime);
        order.setCheck_out_time(checkOutTime);
        order.setOrder_status(orderStatus);
        orderService.updateOrder(order);
        return "redirect:/order-management/";
    }

    @RequestMapping("/delete")
    public String deleteOrder(@RequestParam Integer deleteId) {
        orderService.deleteOrder(deleteId);
        return "redirect:/order-management/";
    }
    @RequestMapping("/updateStatus")
    public String updateOrderStatus(@RequestParam Integer orderId, @RequestParam String newStatus) {
        orderService.updateOrderStatus(orderId, newStatus);
        return "redirect:/order-management/";
    }
}
