package com.example.hotal.mapper;

import com.example.hotal.entity.HotelRoom;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface HotelRoomMapper {

    @Select("SELECT * FROM HotelRoom WHERE room_id = #{roomId}")
    HotelRoom getHotelRoomById(@Param("roomId") Integer roomId);

    @Select("SELECT * FROM HotelRoom")
    List<HotelRoom> getAllHotelRooms();

    @Update("UPDATE HotelRoom SET room_number = #{room_number}, room_type = #{room_type}, room_capacity = #{room_capacity}, " +
            "room_status = #{room_status}, bed_size = #{bed_size}, other_info = #{other_info}, price = #{price}, " +
            "img1 = #{img1}, img2 = #{img2}, img3 = #{img3} WHERE room_id = #{room_id}")
    void updateHotelRoom(HotelRoom hotelRoom);

    @Delete("DELETE FROM HotelRoom WHERE room_id = #{roomId}")
    void deleteHotelRoom(@Param("roomId") Integer roomId);

    //测试用插入房间Mapper，可删
    @Insert("<script>" +
            "INSERT INTO hotelroom (room_number, room_type, room_capacity, room_status, bed_size, other_info, price, img1, img2, img3) " +
            "VALUES " +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(#{item.room_number}, #{item.room_type}, #{item.room_capacity}, #{item.room_status}, #{item.bed_size}, #{item.other_info}, #{item.price}, #{item.img1}, #{item.img2}, #{item.img3})" +
            "</foreach>" +
            "</script>")
    void insertHotelRooms(List<HotelRoom> hotelRooms);
    //房间分类页面根据类型，容纳人数，时间查询返回空房列表
    //链接订单表和房间表查询空房
    @Select("SELECT * FROM HotelRoom " +
            "WHERE room_type = #{roomType} " +
            "AND room_capacity = #{roomCapacity} " +
            "AND room_id NOT IN (" +
            "   SELECT room_id FROM `order` " +
            "   WHERE (check_in_time <= #{checkOutTime} AND check_out_time >= #{checkInTime}) " +
            "      OR (check_in_time <= #{checkInTime} AND check_out_time >= #{checkOutTime}) " +
            ")")
    List<HotelRoom> findRoomsByOrder(@Param("roomType") String roomType,
                                       @Param("roomCapacity") Integer roomCapacity,
                                       @Param("checkInTime") LocalDateTime checkInTime,
                                       @Param("checkOutTime") LocalDateTime checkOutTime);

}
