package com.example.hotal.service.impl;

import com.example.hotal.entity.CustomResultObject;
import com.example.hotal.entity.CustomerReview;
import com.example.hotal.mapper.CustomerReviewMapper;
import com.example.hotal.service.CustomerReviewService;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerReviewServiceImpl implements CustomerReviewService {
    @Autowired
    private CustomerReviewMapper customerReviewMapper;
    @Override
    public List<CustomResultObject> getReview(){
        List<CustomResultObject> review = customerReviewMapper.getCustomerReview();
        return review;
    }
    public void insertReview(CustomerReview review){
        customerReviewMapper.insertCustomerReview(review);
    }
    public List<CustomResultObject> myReview(Integer customer_id){
        List<CustomResultObject> myReview = customerReviewMapper.MyReview(customer_id);
        return myReview;
    }
    public void deleteReview(Integer customer_id,Integer review_id){
        customerReviewMapper.deleteCommentsByCustomerId(customer_id,review_id);
    }
    public String replaceSensitiveWords(String content) {
        // 使用默认配置创建一个敏感词处理实例
        return SensitiveWordBs.newInstance().replace(content);
    }
}