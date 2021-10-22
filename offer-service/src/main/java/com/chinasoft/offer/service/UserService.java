package com.chinasoft.offer.service;

import com.chinasoft.offer.common.ServerResponse;
import com.chinasoft.offer.config.MessageConfig;
import com.chinasoft.offer.data.entity.Offer;
import com.chinasoft.offer.data.entity.User;
import com.chinasoft.offer.data.model.OfferQo;
import com.chinasoft.offer.data.model.UserQo;
import com.chinasoft.offer.data.repository.OfferRepository;
import com.chinasoft.offer.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Honkey on 2017/11/16 14:54.
 */
@Service
public class UserService {

    Logger logger= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageConfig messageConfig;

    private Pageable builderPage(int pageNum,int pageSize){
        return new PageRequest(pageNum,pageSize,new Sort(Sort.Direction.DESC,"createDate"));
    }

    public ServerResponse<User> addOrUpdate(User user){
        StringBuilder str=new StringBuilder();
        Boolean flag=true;
        if(user.getUserName()==null){
            str.append("用户姓名 ");
            flag=false;
        }
        if(user.getGender()==null){
            str.append("用户性别 ");
            flag=false;
        }
        if(flag){
            try {
                if(user.getId()==null){
                    /**新增用户信息*/
                    User userTemp = userRepository.save(user);
                    if(userTemp!=null){
                        return ServerResponse.createBySuccess(messageConfig.getAddTeaSuccess(),userTemp);
                    }
                    return ServerResponse.createByErrorMessage(messageConfig.getAddTeaFail());
                }
                /**修改用户信息*/
                if(userRepository.findOne(user.getId())==null){
                    return ServerResponse.createByErrorMessage(messageConfig.getTeaIdNoExist());
                }
                User userTemp = userRepository.saveAndFlush(user);
                if(userTemp!=null){
                    return ServerResponse.createBySuccess(messageConfig.getUpdateTeaSuccess(),userTemp);
                }
                return ServerResponse.createByErrorMessage(messageConfig.getUpdateTeaFail());
            } catch (Exception e) {
                logger.debug(e.getMessage());
                return ServerResponse.createByErrorMessage(e.getMessage());
            }

        }
        return ServerResponse.createByErrorMessage(str+messageConfig.getNotNull());
    }

    public ServerResponse<Page<User>> findAllOrLike(UserQo userQo){
        if(userQo ==null){
            return ServerResponse.createByErrorMessage(messageConfig.getNotNull());
        }
        try {
            Page<User> pageList= userRepository.findAllOrKeywordsLike(userQo.getUserName()==null ? "%" : "%"+ userQo.getUserName()+"%",
                                                                            builderPage(userQo.getPageNum(), userQo.getPageSize()));
            if(pageList.getNumberOfElements()==0){
                return ServerResponse.createByErrorMessage("第"+(pageList.getNumber()+1)+messageConfig.getFindTeaFail());
            }
            return ServerResponse.createBySuccess("第"+(pageList.getNumber()+1)+messageConfig.getFindTeaSuccess(),pageList);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
    }

    public ServerResponse<Map<String,Integer>> delete(String idStr){
        if(idStr==null){
            return ServerResponse.createByErrorMessage(messageConfig.getNotNull());
        }
        try {
            String[] idArr=idStr.split(",");
            int count=0;
            for (String id : idArr) {
                userRepository.delete(id);
                count++;
            }
            Map<String,Integer> map=new HashMap<>();
            map.put("count",count);
            return ServerResponse.createBySuccess(messageConfig.getDeleteTeaSuccess(),map);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
    }

}
