package com.github.warrengreen.contrastapi.repo;

import com.github.warrengreen.contrastapi.model.Application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationRepo extends PagingAndSortingRepository<Application, Integer>
{
    Page<Application> findAllByOrganizationId(int orgId,Pageable pageable);

    Page<Application> findByNameContaining(String search, Pageable pageable);

	Page<Application> findByOrganizationIdAndNameContaining(int orgId, String search, Pageable pageable);

}
