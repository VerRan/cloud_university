package com.chinasoft.offer.data.model;

import java.util.Date;

/**
 * Created by Honkey on 2017/11/16 11:52.
 */
public class OfferQo extends PageQo{

    private String id;

    private String offerName;

    private String imageUrl;



    public OfferQo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
