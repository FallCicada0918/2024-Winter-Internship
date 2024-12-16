package com.example.hotal.service;

import com.example.hotal.entity.EsportsEquipment;
import com.example.hotal.entity.HotelRoom;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface HotelRoomService {
    //房间分类页面Service,用于找到用户选择房型的空闲房表
    List<HotelRoom> findFreeRoom(String roomType,
                                 Integer roomCapacity,
                                 LocalDateTime checkInTime,
                                 LocalDateTime checkOutTime);


    HotelRoom getHotelRoomById(Integer roomId);
    List<HotelRoom> getAllHotelRooms();
    void updateHotelRoom(HotelRoom hotelRoom);

    List<EsportsEquipment> groupRoomEquipmentByType(String equipment_type, Integer room_id);
    String getRoomNumber(Integer room_id);
}
