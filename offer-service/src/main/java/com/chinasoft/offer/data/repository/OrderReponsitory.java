package com.chinasoft.offer.data.repository;

import com.chinasoft.offer.data.entity.Offer;
import com.chinasoft.offer.data.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Honkey on 2017/11/16 11:51.
 */
@Repository
public interface OrderReponsitory extends JpaRepository<Order,String> {
    public List<Order> findByUserId(String userId);
}
