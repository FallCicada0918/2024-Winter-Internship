package com.example.hotal.service;

import com.example.hotal.entity.CustomResultObject;
import com.example.hotal.entity.CustomerReview;

import java.util.List;

public interface CustomerReviewService {
    List<CustomResultObject> getReview();
    void insertReview(CustomerReview review);
    void deleteReview(Integer customer_id,Integer review_id);
    List<CustomResultObject> myReview(Integer customer_id);

    String replaceSensitiveWords(String content);
}