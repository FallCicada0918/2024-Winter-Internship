package com.example.hotal.mapper;

import com.example.hotal.entity.CustomerSuggestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerSuggestionMapper {

    @Insert("INSERT INTO CustomerSuggestion (order_id, feedback_content, date) VALUES (#{order_id}, #{feedback_content}, #{date})")
    void insertCustomerSuggestion(CustomerSuggestion suggestion);

    @Select("SELECT * FROM CustomerSuggestion WHERE suggestion_id = #{suggestionId}")
    CustomerSuggestion getCustomerSuggestionById(@Param("suggestionId") Integer suggestionId);

    @Select("SELECT * FROM CustomerSuggestion")
    List<CustomerSuggestion> getAllCustomerSuggestions();

    @Update("UPDATE CustomerSuggestion SET order_id = #{orderId}, feedback_content = #{feedbackContent}, " +
            "date = #{date} WHERE suggestion_id = #{suggestionId}")
    void updateCustomerSuggestion(CustomerSuggestion suggestion);

    @Delete("DELETE FROM CustomerSuggestion WHERE suggestion_id = #{suggestionId}")
    void deleteCustomerSuggestion(@Param("suggestionId") Integer suggestionId);

}