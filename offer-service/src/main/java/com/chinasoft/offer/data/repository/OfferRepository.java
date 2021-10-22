package com.chinasoft.offer.data.repository;

import com.chinasoft.offer.data.entity.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Honkey on 2017/11/16 11:51.
 */
@Repository
public interface OfferRepository extends JpaRepository<Offer,String> {

    @Query("select t from Offer t where t.offerName like :keyWords")
    Page<Offer> findAllOrKeywordsLike(@Param("keyWords") String keyWords, Pageable pageRequest);
}
