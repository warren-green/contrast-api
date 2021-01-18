package com.github.warrengreen.contrastapi.repo;

import com.github.warrengreen.contrastapi.model.Application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApplicationRepo extends JpaRepository<Application, Integer>, JpaSpecificationExecutor<Application>
{
    Page<Application> findAllByOrganizationId(int orgId,Pageable pageable);

    Page<Application> findByNameContaining(String search, Pageable pageable);

}
