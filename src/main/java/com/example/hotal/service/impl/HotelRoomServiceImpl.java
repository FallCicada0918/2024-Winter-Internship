package com.example.hotal.service.impl;

import com.example.hotal.entity.EsportsEquipment;
import com.example.hotal.entity.HotelRoom;
import com.example.hotal.mapper.EsportsEquipmentMapper;
import com.example.hotal.mapper.HotelRoomMapper;
import com.example.hotal.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelRoomServiceImpl implements HotelRoomService {
    @Autowired
    private HotelRoomMapper hotelroomMapper;
    @Autowired
    private EsportsEquipmentMapper esportsEquipmentMapper;
    public List<HotelRoom> findFreeRoom(String roomType,
                                        Integer roomCapacity,
                                        LocalDateTime checkInTime,
                                        LocalDateTime checkOutTime){
        List<HotelRoom> Room=hotelroomMapper.findRoomsByOrder(roomType,roomCapacity,checkInTime,checkOutTime);
        return Room;
    }

    @Override
    public HotelRoom getHotelRoomById(Integer roomId) {
        return hotelroomMapper.getHotelRoomById(roomId);
    }

    @Override
    public List<HotelRoom> getAllHotelRooms() {
        return hotelroomMapper.getAllHotelRooms();
    }

    @Override
    public void updateHotelRoom(HotelRoom hotelRoom) {
        hotelroomMapper.updateHotelRoom(hotelRoom);
    }
    public List<EsportsEquipment> groupRoomEquipmentByType(String equipment_type, Integer room_id)
    {
        List<EsportsEquipment> esportsEquipments = esportsEquipmentMapper.findEquipmentByRoomId(room_id);
        List<EsportsEquipment> equipments=new ArrayList<>();
        for(EsportsEquipment equipment:esportsEquipments)
        {
            if(equipment.getEquipment_type().equals(equipment_type))
            {
                equipments.add(equipment);
            }
        }
        return equipments;
    }
    public String getRoomNumber(Integer room_id){
        HotelRoom room=hotelroomMapper.getHotelRoomById(room_id);
        return room.getRoom_number();
    }

}
