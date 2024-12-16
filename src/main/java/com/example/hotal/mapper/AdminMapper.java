package com.example.hotal.mapper;

import com.example.hotal.entity.Admin;
import com.example.hotal.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select * from Admin where admin_account=#{admin_account} and admin_password=#{admin_password}")
    Admin findByAdminnameAndPassword(@Param("admin_account") String username, @Param("admin_password") String password);
}