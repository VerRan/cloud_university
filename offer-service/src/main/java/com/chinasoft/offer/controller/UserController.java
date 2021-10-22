package com.chinasoft.offer.controller;

import com.chinasoft.offer.common.ServerResponse;
import com.chinasoft.offer.data.entity.Offer;
import com.chinasoft.offer.data.entity.User;
import com.chinasoft.offer.data.model.OfferQo;
import com.chinasoft.offer.data.model.UserQo;
import com.chinasoft.offer.data.repository.UserRepository;
import com.chinasoft.offer.service.OfferService;
import com.chinasoft.offer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Honkey on 2017/11/16 11:48.
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping({"/add","/update"})
    public ServerResponse<User> addOrUpdate(@RequestBody User user){
        return userService.addOrUpdate(user);
    }

    @PostMapping("/findAllOrLike")
    public ServerResponse<Page<User>> findAllOrLike(@RequestBody UserQo userQo){
        return userService.findAllOrLike(userQo);
    }

    @PostMapping("/delete")
    public ServerResponse<Map<String,Integer>> delete(@RequestBody OfferQo offerQo){
        return  userService.delete(offerQo.getId());
    }
}

