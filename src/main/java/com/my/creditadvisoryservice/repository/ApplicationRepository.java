package com.my.creditadvisoryservice.repository;

import com.my.creditadvisoryservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("select a from Application a  where a.status = :status order by a.createdOn asc")
    List<Application> findAllApplicationsByStatusFromOldest(@Param("status") Application.Status status);

}
