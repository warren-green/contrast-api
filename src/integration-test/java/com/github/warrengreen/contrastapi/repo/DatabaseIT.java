package com.github.warrengreen.contrastapi.repo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;


import com.github.warrengreen.contrastapi.model.Application;
import com.github.warrengreen.contrastapi.model.Organization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@SpringBootTest
public class DatabaseIT {

    @Autowired
    private ApplicationRepo appRepo;
    
    @Autowired
    private OrganizationRepo orgRepo;

    @BeforeEach
    void setup() {
        appRepo.deleteAll();
    }
    
    @Test
    public void dbIsEmpty() {
        assertEquals(0,appRepo.findAll(PageRequest.of(0,20)).getContent().size()); 
    }

    @Test
    public void saveOneApplication() {
        Application app = new Application();  
        appRepo.save(app);
        Page<Application> apps = appRepo.findAll(PageRequest.of(0,20));
        assertEquals(apps.getContent().size(),1); 
        assertThat(apps.getContent().get(0).getId(), greaterThan(0));
    }

    @Test
    public void findByOrgIdReturnsCorrectApp() {

        Application app1 = new Application();
        Application app2 = new Application(); 
        Organization org1 = new Organization();
        
        app1.setId(1);

        app1.setOrganization(org1); 

        org1 = orgRepo.save(org1);
        appRepo.save(app1);
        appRepo.save(app2);

        Page<Application> apps = appRepo.findAllByOrganizationId(org1.getId(), PageRequest.of(0,20));
        assertEquals(apps.getContent().size(),1);
    }


    @Test
    public void findByNameFullName() {

        Application app1 = new Application();
        Application app2 = new Application(); 
        
        app1.setName("Kubernetes");
        app2.setName("NixOS Package Collection");
        appRepo.save(app1);
        appRepo.save(app2);

        Page<Application> apps = appRepo.findByNameContaining("Kubernetes", PageRequest.of(0,20));
        assertEquals(apps.getContent().size(),1);
        assertEquals(apps.getContent().get(0).getName(),"Kubernetes");
    }

    @Test
    public void findByPartialNameReturnsCorrectApp() {

        Application app1 = new Application();
        Application app2 = new Application(); 
        
        app1.setName("Kubernetes");
        app2.setName("NixOS Package Collection");
        appRepo.save(app1);
        appRepo.save(app2);

        Page<Application> apps = appRepo.findByNameContaining("Package", PageRequest.of(0,20));
        assertEquals(apps.getContent().size(),1);
        assertEquals(apps.getContent().get(0).getName(),"NixOS Package Collection");
    }

    @Test
    public void findByPartialNameReturnsAllMatches() {

        Application app1 = new Application();
        Application app2 = new Application();
        Application app3 = new Application(); 
        
        app1.setName("Microsoft .NET CoreFX");
        app2.setName("Microsoft .NET Roslyn");
        app3.setName("TensorFlow");
        appRepo.save(app1);
        appRepo.save(app2);
        appRepo.save(app3);

        Page<Application> apps = appRepo.findByNameContaining(".NET", PageRequest.of(0,20));
        assertEquals(apps.getContent().size(),2);
        assertEquals(apps.getContent().get(0).getName(),"Microsoft .NET CoreFX");
        assertEquals(apps.getContent().get(1).getName(),"Microsoft .NET Roslyn");
    }
    


}
