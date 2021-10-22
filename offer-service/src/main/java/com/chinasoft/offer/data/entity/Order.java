package com.chinasoft.offer.data.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
/**
 *
 * 订单实体类，用于存储订单数据
 *
 * **/
@Entity
@Table(name = "order")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Order {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    private String offerId;
    private String userId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
