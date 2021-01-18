package com.github.warrengreen.contrastapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.github.warrengreen.contrastapi.model.Application;
import com.github.warrengreen.contrastapi.model.Organization;
import com.github.warrengreen.contrastapi.repo.ApplicationRepo;
import com.github.warrengreen.contrastapi.repo.OrganizationRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationRestControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private ApplicationRepo appRepo;

    @Autowired
    private OrganizationRepo orgRepo;

    String baseURI;

    @BeforeEach
    void setup() {
        appRepo.deleteAll();
        orgRepo.deleteAll();
        baseURI = "http://localhost:" + port;
    }

    @Test
    public void getAllByOrgIDReturnsAllApps() {
        Application app1 = new Application();
        Application app2 = new Application();
        Application app3 = new Application();
        Organization org1 = new Organization();
        Organization org2 = new Organization();

        app1.setName("app1");
        app2.setName("app2");
        app2.setName("app3");

        app1.setOrganization(org1);
        app2.setOrganization(org1);
        app3.setOrganization(org2);

        org1 = orgRepo.save(org1);
        orgRepo.save(org2);
        appRepo.save(app1);
        appRepo.save(app2);
        appRepo.save(app3);

        ParameterizedTypeReference<CustomPageImpl<Application>> responseType = new ParameterizedTypeReference<CustomPageImpl<Application>>() {
        };
        ResponseEntity<CustomPageImpl<Application>> response = restTemplate.exchange(
                baseURI + "/organizations/" + org1.getId() + "/applications", HttpMethod.GET, null, responseType);

        List<Application> apps = response.getBody().getContent();

        assertEquals(2, apps.size());
    }

    @Test
    public void findByPartialNameReturnSingleApp() {

        Application app1 = new Application();
        Application app2 = new Application();

        Organization org1 = new Organization();
        org1 = orgRepo.save(org1);
        Organization org2 = new Organization();
        org2 = orgRepo.save(org2);

        app1.setId(1);
        app1.setName("Kubernetes");
        app1.setOrganization(org1);
        app2.setId(2);
        app2.setName("NixOS Package Collection");
        app2.setOrganization(org1);
        appRepo.save(app1);
        appRepo.save(app2);

        ParameterizedTypeReference<CustomPageImpl<Application>> responseType = new ParameterizedTypeReference<CustomPageImpl<Application>>() {
        };
        ResponseEntity<CustomPageImpl<Application>> response = restTemplate.exchange(
                baseURI + "/organizations/" + org1.getId() + "/applications?query=Kub", HttpMethod.GET, null, responseType);
        
        List<Application> apps = response.getBody().getContent();

        assertEquals(1, apps.size());
        assertEquals("Kubernetes",apps.get(0).getName() );
    }

    @Test
    public void findByPartialNameReturnForCorrectOrg() {

        Application app1 = new Application();
        Application app2 = new Application();

        Organization org1 = new Organization();
        org1 = orgRepo.save(org1);
        Organization org2 = new Organization();
        org2 = orgRepo.save(org2);

        app1.setId(1);
        app1.setName("Kubernetes");
        app1.setOrganization(org1);
        app2.setId(2);
        app2.setName("Kubernetes2");
        app2.setOrganization(org2);
        appRepo.save(app1);
        appRepo.save(app2);

        ParameterizedTypeReference<CustomPageImpl<Application>> responseType = new ParameterizedTypeReference<CustomPageImpl<Application>>() {
        };
        ResponseEntity<CustomPageImpl<Application>> response = restTemplate.exchange(
                baseURI + "/organizations/" + org1.getId() + "/applications?query=Kub", HttpMethod.GET, null, responseType);
        
        List<Application> apps = response.getBody().getContent();

        assertEquals(1, apps.size());
        assertEquals("Kubernetes",apps.get(0).getName() );
    }
}
