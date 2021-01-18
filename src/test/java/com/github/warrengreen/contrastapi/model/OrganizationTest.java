package com.github.warrengreen.contrastapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import org.junit.jupiter.api.Test;

public class OrganizationTest {

    Organization org;

    @BeforeEach
    public void setUp() {
        org = new Organization();
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void getAndSetId() {
        int id = 123;
        org.setId(id);
        assertEquals(id, org.getId());
    }

    @Test
    public void getAndSetName() {
        String name = "testorg";
        org.setName(name);
        assertEquals(org.getName(), name);
    }

    @Test
    public void addSingleApp() {
        String appName = "testapp";
        Application application = new Application();
        application.setName(appName);

        org.getApplications().add(application);
        assertEquals(1, org.getApplications().size());
        assertEquals(appName, org.getApplications().get(0).getName());
    }

}
