package com.chinasoft.offer.data.repository;

import com.chinasoft.offer.data.entity.Offer;
import com.chinasoft.offer.data.entity.User;
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
public interface UserRepository extends JpaRepository<User,String> {

    @Query("select t from User t where t.userName like :keyWords")
    Page<User> findAllOrKeywordsLike(@Param("keyWords") String keyWords, Pageable pageRequest);
}
