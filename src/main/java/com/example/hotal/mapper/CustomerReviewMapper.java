package com.example.hotal.mapper;

import com.example.hotal.entity.CustomResultObject;
import com.example.hotal.entity.Customer;
import com.example.hotal.entity.CustomerReview;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerReviewMapper {
        @Select("SELECT cr.review_id, cr.review_content, c.customer_account " +
                "FROM CustomerReview cr " +
                "JOIN Customer c ON cr.customer_id = c.customer_id " )
        @Results(id = "customerReviewMap", value = {
                @Result(property = "review_id", column = "review_id"),
                @Result(property = "review_content", column = "review_content"),
                @Result(property = "customer_account", column = "customer_account")
        })
        List<CustomResultObject> getCustomerReview();
    @Delete("DELETE FROM CustomerReview WHERE customer_id = #{customer_id} and  review_id = #{review_id}")
    void deleteCommentsByCustomerId(@Param("customer_id") Integer customer_id,@Param("review_id") Integer review_id);
    @Select("SELECT review_id,review_content FROM CustomerReview WHERE customer_id = #{customer_id}")
    List<CustomResultObject> MyReview(@Param("customer_id") Integer customer_id);


    @Insert("INSERT INTO CustomerReview (review_id, customer_id ,review_content,date) " +
            "VALUES (#{review_id}, #{customer_id},#{review_content}, #{date})")
    void insertCustomerReview(CustomerReview review);
}
