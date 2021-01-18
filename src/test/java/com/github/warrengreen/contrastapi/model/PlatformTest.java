package com.github.warrengreen.contrastapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import org.junit.jupiter.api.Test;

public class PlatformTest {

    Platform platform;

    @BeforeEach
    public void setUp() {
        platform = new Platform();
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void getAndSetId() {
        int id = 123;
        platform.setId(id);
        assertEquals(id, platform.getId());
    }

    @Test
    public void getAndSetName() {
        String name = "testplatform";
        platform.setName(name);
        assertEquals( platform.getName(),name);
    }


}
