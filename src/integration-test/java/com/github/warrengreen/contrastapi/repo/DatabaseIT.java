package com.github.warrengreen.contrastapi.repo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;

import java.util.List;

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
        appRepo.flush();
        appRepo.deleteAllInBatch();
        appRepo.flush();
    }
    
    @Test
    public void dbIsEmpty() {
        assertEquals(0,appRepo.findAll().size()); 
    }

    @Test
    public void saveOneApplication() {
        Application app = new Application();  
        appRepo.save(app);
        List<Application> apps = appRepo.findAll();
        assertEquals(apps.size(),1); 
        assertThat(apps.get(0).getId(), greaterThan(0));
    }



    @Test
    public void findByOrgIdReturnsCorrectApp() {

        Application app1 = new Application();
        Application app2 = new Application(); 
        Organization org1 = new Organization();
        
        app1.setId(1);
        org1.setId(1);

        app1.setOrganization(org1); 

        orgRepo.save(org1);
        appRepo.save(app1);
        appRepo.save(app2);

        Page<Application> apps = appRepo.findAllByOrganizationId(org1.getId(), PageRequest.of(0,20));
        assertEquals(apps.getContent().size(),1);
    }


    


}
