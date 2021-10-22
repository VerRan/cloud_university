package com.chinasoftinc.offer;

import com.chinasoft.offer.data.entity.Offer;
import com.chinasoft.offer.data.repository.OfferRepository;
import com.netflix.discovery.converters.Auto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes=OfferServiceTest.class)
public class OfferServiceTest {

    @Autowired
    OfferRepository offerRepository;
//    @Autowired


    @Test
    public void queryOrderOfferings(){
        Offer offer =new Offer();
        offer.setOfferName("IphoneX");
        offer.setImageUrl("sss");
        offer.setRemarks("xxxxxxx");
        Offer offerTemp = offerRepository.save(offer);
        Assert.assertEquals(offer.getOfferName(),offerTemp.getOfferName());
    }
}
