package com.my.creditadvisoryservice.controller;

import com.my.creditadvisoryservice.entity.Application;
import com.my.creditadvisoryservice.exception.AssignationException;
import com.my.creditadvisoryservice.service.AdvisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/advisor")
public class AdvisorController {

    private final AdvisorService advisorService;

    @PatchMapping("/assignation/{id}")
    public Application assignToAdvisor(@PathVariable(name = "id") Long advisorId) throws AssignationException {
        return advisorService.assignApplicationToAdvisor(advisorId);

    }
}
