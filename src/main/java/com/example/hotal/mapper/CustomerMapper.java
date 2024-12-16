package com.example.hotal.mapper;

import com.example.hotal.entity.Customer;
import com.example.hotal.entity.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {
    @Select("select * from Customer where customer_account=#{customer_account} and customer_password=#{customer_password}")
    Customer findByCustomernameAndPassword(@Param("customer_account") String username, @Param("customer_password") String password);

    @Insert("INSERT INTO Customer (customer_account, customer_password ,id_card_number,name,phone_number) " +
            "VALUES (#{customer_account}, #{customer_password},#{id_card_number}, #{name},#{phone_number})")
    void insertCustomer(Customer customer);
}
