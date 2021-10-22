package com.chinasoft.offer.service;

import com.chinasoft.offer.common.ServerResponse;
import com.chinasoft.offer.config.MessageConfig;
import com.chinasoft.offer.data.entity.Offer;
import com.chinasoft.offer.data.model.OfferQo;
import com.chinasoft.offer.data.repository.OfferRepository;
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
public class OfferService {

    Logger logger= LoggerFactory.getLogger(OfferService.class);

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private MessageConfig messageConfig;

    private Pageable builderPage(int pageNum,int pageSize){
        return new PageRequest(pageNum,pageSize,new Sort(Sort.Direction.DESC,"createDate"));
    }

    public ServerResponse<Offer> addOrUpdate(Offer offer){
        StringBuilder str=new StringBuilder();
        Boolean flag=true;
        if(offer.getOfferName()==null){
            str.append("商品姓名 ");
            flag=false;
        }
        if(offer.getImageUrl()==null){
            str.append("商品图片 ");
            flag=false;
        }
        if(flag){
            try {
                if(offer.getId()==null){
                    /**新增商品信息*/
                    Offer offerTemp = offerRepository.save(offer);
                    if(offerTemp!=null){
                        return ServerResponse.createBySuccess(messageConfig.getAddTeaSuccess(),offerTemp);
                    }
                    return ServerResponse.createByErrorMessage(messageConfig.getAddTeaFail());
                }
                /**修改商品信息*/
                if(offerRepository.findOne(offer.getId())==null){
                    return ServerResponse.createByErrorMessage(messageConfig.getTeaIdNoExist());
                }
                Offer offerTemp = offerRepository.saveAndFlush(offer);
                if(offerTemp!=null){
                    return ServerResponse.createBySuccess(messageConfig.getUpdateTeaSuccess(),offerTemp);
                }
                return ServerResponse.createByErrorMessage(messageConfig.getUpdateTeaFail());
            } catch (Exception e) {
                logger.debug(e.getMessage());
                return ServerResponse.createByErrorMessage(e.getMessage());
            }

        }
        return ServerResponse.createByErrorMessage(str+messageConfig.getNotNull());
    }

    public ServerResponse<Page<Offer>> findAllOrLike(OfferQo offerQo){
        if(offerQo ==null){
            return ServerResponse.createByErrorMessage(messageConfig.getNotNull());
        }
        try {
            Page<Offer> pageList= offerRepository.findAllOrKeywordsLike(offerQo.getOfferName()==null ? "%" : "%"+ offerQo.getOfferName()+"%",
                                                                            builderPage(offerQo.getPageNum(), offerQo.getPageSize()));
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
                offerRepository.delete(id);
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
