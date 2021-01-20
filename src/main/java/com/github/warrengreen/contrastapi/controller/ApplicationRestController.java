package com.github.warrengreen.contrastapi.controller;

import com.github.warrengreen.contrastapi.model.Application;
import com.github.warrengreen.contrastapi.service.ApplicationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/organizations/{orgId}/applications")
@AllArgsConstructor
public class ApplicationRestController {

    
    private ApplicationService appService;
    
    @RequestMapping( method = RequestMethod.GET)
	public Page<Application> listApplicationsByOrgId(@PathVariable int orgId,Pageable pageable) {
		return appService.findAllByOrganizationId(orgId, pageable);
    }

    @RequestMapping( method = RequestMethod.GET,params="query")
	public Page<Application> searchApplicationsByName(@PathVariable int orgId,@RequestParam(value = "query") String query,Pageable pageable){
		return appService.findByOrganizationIdAndNameContaining(orgId,query,pageable);
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
        
        return appService.findByOrganizationIdAndNameContaining(orgId,query,sorted);
	}
}
