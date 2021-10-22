package com.chinasoft.offer.controller;

import com.chinasoft.offer.common.ServerResponse;
import com.chinasoft.offer.data.entity.Offer;
import com.chinasoft.offer.data.entity.Order;
import com.chinasoft.offer.data.model.OfferQo;
import com.chinasoft.offer.data.repository.OrderReponsitory;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderReponsitory orderReponsitory;
    @PostMapping("/findAllOrLike")
    public List<Order> findByUserId(String userId){
        return orderReponsitory.findByUserId(userId);
    }
}
