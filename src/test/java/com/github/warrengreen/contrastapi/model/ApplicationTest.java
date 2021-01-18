package com.github.warrengreen.contrastapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import org.junit.jupiter.api.Test;

public class ApplicationTest {

    Application app;

    @BeforeEach
    public void setUp() {
        app = new Application();
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void getAndSetId() {
        int id = 123;
        app.setId(id);
        assertEquals(id, app.getId());
    }

    @Test
    public void getAndSetName() {
        String name = "testApp";
        app.setName(name);
        assertEquals( app.getName(),name);
    }

    @Test
    public void getAndSetPlatform() {
        String platformName = "testplatform";
        Platform platform = new Platform();
        platform.setName(platformName);
        
        app.setPlatform(platform);
        assertEquals( app.getPlatform().getName(),platformName);
    }

}
