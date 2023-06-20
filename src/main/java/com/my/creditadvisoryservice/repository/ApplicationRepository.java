package com.my.creditadvisoryservice.repository;

import com.my.creditadvisoryservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("select a from Application a  where a.moneyAmount >= :minValue and  a.moneyAmount <= :maxValue and a.status = 'NEW' order by a.createdOn asc LIMIT 1")
    Optional<Application> findAllNewByMoneyAmountSortedByDate(@Param("minValue") Long minValue, @Param("maxValue") Long maxValue);

}
