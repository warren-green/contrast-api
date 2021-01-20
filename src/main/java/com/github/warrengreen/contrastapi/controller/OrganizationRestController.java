package com.github.warrengreen.contrastapi.controller;

import com.github.warrengreen.contrastapi.exceptions.ResourceNotFoundException;
import com.github.warrengreen.contrastapi.model.Organization;
import com.github.warrengreen.contrastapi.repo.OrganizationRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/organizations")
@AllArgsConstructor
public class OrganizationRestController {

    
	private OrganizationRepo orgdb;
    

    @RequestMapping( method = RequestMethod.GET)
	public Page<Organization> listOrganizations(Pageable pageable) {
		return orgdb.findAll(pageable);
	}

	@RequestMapping(path = "/{orgId}", method = RequestMethod.GET)
	public Organization findById(@PathVariable int orgId) {
		return orgdb.findById(orgId)
				.orElseThrow(()->
				new ResourceNotFoundException("Unable to find organization with id :" + orgId));
	}



    
}
