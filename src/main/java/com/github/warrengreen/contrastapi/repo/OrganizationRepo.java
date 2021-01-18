package com.github.warrengreen.contrastapi.repo;

import com.github.warrengreen.contrastapi.model.Organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrganizationRepo extends JpaRepository<Organization, Integer>, JpaSpecificationExecutor<Organization>
{


}
