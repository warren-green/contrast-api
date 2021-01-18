package com.github.warrengreen.contrastapi.controller;

import com.github.warrengreen.contrastapi.model.Application;
import com.github.warrengreen.contrastapi.repo.ApplicationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizations/{orgId}/applications")
public class ApplicationRestController {

    @Autowired
    private ApplicationRepo appdb;
    
    @RequestMapping( method = RequestMethod.GET)
	public Page<Application> listApplicationsByOrgId(@PathVariable int orgId,Pageable pageable) {
		return appdb.findAllByOrganizationId(orgId, pageable);
    }

    @RequestMapping( method = RequestMethod.GET,params="search")
	public Page<Application> searchApplicationsByName(@RequestParam(value = "search") String search,Pageable pageable){
		return appdb.findByNameContaining(search,pageable);
	}
}
