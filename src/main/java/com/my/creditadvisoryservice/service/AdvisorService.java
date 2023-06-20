package com.my.creditadvisoryservice.service;

import com.my.creditadvisoryservice.entity.Application;
import com.my.creditadvisoryservice.exception.AssignationException;

public interface AdvisorService {
    Application assignApplicationToAdvisor(Long advisorId) throws AssignationException;
}
