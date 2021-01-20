package com.github.warrengreen.contrastapi.service;

import com.github.warrengreen.contrastapi.model.Application;
import com.github.warrengreen.contrastapi.repo.ApplicationRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ApplicationService {
    
    private final ApplicationRepo appRepo;

	public Page<Application> findAllByOrganizationId(int orgId, Pageable pageable) {
		return appRepo.findAllByOrganizationId(orgId, pageable);
	}

	public Page<Application> findByOrganizationIdAndNameContaining(int orgId, String query, Pageable pageable) {
		return appRepo.findByOrganizationIdAndNameContaining(orgId,query,pageable);
    }
    
    




}
