package com.example.hotal.mapper;

import com.example.hotal.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO `Order` (customer_id, room_id, check_in_time, check_out_time, order_status) " +
            "VALUES (#{customer_id}, #{room_id}, #{check_in_time}, #{check_out_time}, #{order_status})")
    void insertOrder(Order order);

    @Select("SELECT * FROM `Order` WHERE order_id = #{orderId}")
    Order getOrderById(@Param("orderId") Integer orderId);

    @Select("SELECT * FROM `Order`")
    List<Order> getAllOrders();

    @Select("SELECT o.order_id, o.customer_id, o.room_id, o.check_in_time, o.check_out_time, o.order_status, " +
            "r.room_type, r.room_capacity " +
            "FROM `Order` o " +
            "JOIN HotelRoom r ON o.room_id = r.room_id " +
            "WHERE o.customer_id = #{customerId}")
    List<Order> getOrdersByCustomerId(@Param("customerId") Integer customerId);

    @Select("SELECT order_id FROM `Order` WHERE customer_id = #{customerId} LIMIT 1")
    Integer getFirstOrderIdByCustomerId(@Param("customerId") Integer customerId);

    @Update("UPDATE `Order` SET order_status = 'finish' WHERE order_id = #{orderId}")
    void completeOrder(@Param("orderId") Integer orderId);

    @Update("UPDATE `Order` SET customer_id = #{customer_id}, room_id = #{room_id}, check_in_time = #{check_in_time}, " +
            "check_out_time = #{check_out_time}, order_status = #{order_status} WHERE order_id = #{order_id}")
    void updateOrder(Order order);
    @Update("UPDATE `Order` SET order_status = #{order_status} WHERE order_id = #{order_id}")
    void updateOrderStatus(@Param("order_id") Integer orderId, @Param("order_status") String orderStatus);

    @Delete("DELETE FROM `Order` WHERE order_id = #{orderId}")
    void deleteOrder(@Param("orderId") Integer orderId);



}