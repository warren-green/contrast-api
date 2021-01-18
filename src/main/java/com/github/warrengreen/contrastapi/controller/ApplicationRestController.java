package com.github.warrengreen.contrastapi.controller;

import java.util.List;

import com.github.warrengreen.contrastapi.model.Application;
import com.github.warrengreen.contrastapi.repo.ApplicationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @RequestMapping( method = RequestMethod.GET,params="query")
	public Page<Application> searchApplicationsByName(@PathVariable int orgId,@RequestParam(value = "query") String query,Pageable pageable){
		return appdb.findByOrganizationIdAndNameContaining(orgId,query,pageable);
    }
    
    @RequestMapping( method = RequestMethod.GET,params={"query","order"})
	public Page<Application> searchApplicationsByNameAndOrder(@PathVariable int orgId,@RequestParam(value = "query") String query,@RequestParam(value = "order") String order,Pageable pageable){
        String trimmedOrder = order.replaceAll("\'","");
        String[] el = trimmedOrder.split(" ");
        String sortField = el[0];
        String sortDir = el[1];
        
        Pageable sorted = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sortDir.equals("asc") ? Sort.by(sortField).ascending()
        : Sort.by(sortField).descending()
        
        );
        
        return appdb.findByOrganizationIdAndNameContaining(orgId,query,sorted);
	}
}
