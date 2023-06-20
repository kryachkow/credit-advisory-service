package com.my.creditadvisoryservice.repository;

import com.my.creditadvisoryservice.entity.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {

}
