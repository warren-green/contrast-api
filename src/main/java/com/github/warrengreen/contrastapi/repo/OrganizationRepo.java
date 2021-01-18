package com.github.warrengreen.contrastapi.repo;

import com.github.warrengreen.contrastapi.model.Organization;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrganizationRepo extends PagingAndSortingRepository<Organization, Integer>
{
    Page<Organization> findByNameContaining(String search, Pageable pageable);

}
