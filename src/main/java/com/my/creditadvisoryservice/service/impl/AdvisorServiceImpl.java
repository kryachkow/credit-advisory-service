package com.my.creditadvisoryservice.service.impl;

import com.my.creditadvisoryservice.entity.Advisor;
import com.my.creditadvisoryservice.entity.Application;
import com.my.creditadvisoryservice.exception.AssignationException;
import com.my.creditadvisoryservice.repository.AdvisorRepository;
import com.my.creditadvisoryservice.repository.ApplicationRepository;
import com.my.creditadvisoryservice.service.AdvisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdvisorServiceImpl implements AdvisorService {

    private final AdvisorRepository advisorRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public Application assignApplicationToAdvisor(Long advisorId) throws AssignationException {
        Advisor advisor = getAssignableAdvisor(advisorId);
        Application application = getOldestApplicationForAdvisor(advisor);
        application.assignToAdvisor(advisor);
        return application;
    }

    private Advisor getAssignableAdvisor(Long advisorId) throws AssignationException {
        Advisor advisor = advisorRepository
                .findById(advisorId)
                .orElseThrow(() -> new AssignationException("Advisor with id " + advisorId + " isn`t fount"));
        if(!advisor.applicationCouldBeAssigned()) {
            throw new AssignationException("Advisor with id " + advisorId + " already has assigned application");
        }
        return advisor;
    }

    private Application getOldestApplicationForAdvisor(Advisor advisor) throws AssignationException {
        return applicationRepository
                .findAllApplicationsByStatusFromOldest(
                        Application.Status.NEW
                )
                .stream()
                .filter(application -> application.getMoneyAmount() >= advisor.getMinCreditAmount()
                        && application.getMoneyAmount() <= advisor.getMaxCreditAmount())
                .findFirst()
                .orElseThrow(() ->
                        new AssignationException("There is no NEW applications with proper parameters for advisor with id " + advisor.getId()));
    }
}
