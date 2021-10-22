package com.chinasoft.offer.controller;

import com.chinasoft.offer.common.ServerResponse;
import com.chinasoft.offer.data.entity.Offer;
import com.chinasoft.offer.data.model.OfferQo;
import com.chinasoft.offer.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Honkey on 2017/11/16 11:48.
 */
@RestController
@CrossOrigin
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping({"/add","/update"})
    public ServerResponse<Offer> addOrUpdate(@RequestBody Offer offer){
        return offerService.addOrUpdate(offer);
    }

    @PostMapping("/findAllOrLike")
    public ServerResponse<Page<Offer>> findAllOrLike(@RequestBody OfferQo offerQo){
        return offerService.findAllOrLike(offerQo);
    }

    @PostMapping("/delete")
    public ServerResponse<Map<String,Integer>> delete(@RequestBody OfferQo offerQo){
        return  offerService.delete(offerQo.getId());
    }
}

