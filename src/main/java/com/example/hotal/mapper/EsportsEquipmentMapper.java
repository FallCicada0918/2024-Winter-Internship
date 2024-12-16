package com.example.hotal.mapper;

import org.apache.ibatis.annotations.*;
import com.example.hotal.entity.EsportsEquipment;

import java.util.List;

@Mapper
public interface EsportsEquipmentMapper {

    @Insert("INSERT INTO EsportsEquipment (equipment_name, equipment_type, parameter_info) " +
            "VALUES (#{equipment_name}, #{equipment_type}, #{parameter_info})")
    void insertEsportsEquipment(EsportsEquipment equipment);

    @Select("SELECT * FROM EsportsEquipment WHERE equipment_id = #{equipmentId}")
    EsportsEquipment getEsportsEquipmentById(@Param("equipmentId") Integer equipmentId);

    @Select("SELECT * FROM esportsequipment")
    List<EsportsEquipment> getAllEsportsEquipment();

    @Update("UPDATE EsportsEquipment SET equipment_name = #{equipment_name}, equipment_type = #{equipment_type}, " +
            "parameter_info = #{parameter_info} WHERE equipment_id = #{equipment_id}")
    void updateEsportsEquipment(EsportsEquipment equipment);

    @Delete("DELETE FROM EsportsEquipment WHERE equipment_id = #{equipmentId}")
    void deleteEsportsEquipment(@Param("equipmentId") Integer equipmentId);

    @Select("SELECT * FROM esportsequipment " +
            "WHERE equipment_id IN " +
            "(SELECt equipment_id FROM equipmentownership WHERE room_id = #{room_id})")
    List<EsportsEquipment> findEquipmentByRoomId(@Param("room_id") Integer room_id);
}