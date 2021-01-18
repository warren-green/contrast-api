package com.github.warrengreen.contrastapi.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
public class OrganizationRestControllerIT {

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
    public void getAllReturnsAllOrganizations() {
        Organization org1 = new Organization(1, "Microsoft", null);
        Organization org2 = new Organization(2, "Google", null);
        Organization org3 = new Organization(3, "Facebook", null);

        orgRepo.save(org1);
        orgRepo.save(org2);
        orgRepo.save(org3);

        ParameterizedTypeReference<CustomPageImpl<Organization>> responseType = new ParameterizedTypeReference<CustomPageImpl<Organization>>() {
        };
        ResponseEntity<CustomPageImpl<Organization>> response = restTemplate.exchange(baseURI + "/organizations",
                HttpMethod.GET, null, responseType);

        List<Organization> orgs = response.getBody().getContent();

        assertEquals(3, orgs.size());
    }

    @Test
    public void findByIdReturnsCorrectOrganizations() {
        Organization org1 = new Organization(1, "Microsoft", null);
        Organization org2 = new Organization(2, "Google", null);
        Organization org3 = new Organization(3, "Facebook", null);

        org1 = orgRepo.save(org1);
        orgRepo.save(org2);
        orgRepo.save(org3);

        Organization result = restTemplate.getForObject(baseURI + "/organizations/{orgId}", Organization.class,
                org1.getId());

        assertEquals("Microsoft", result.getName());
    }

}
