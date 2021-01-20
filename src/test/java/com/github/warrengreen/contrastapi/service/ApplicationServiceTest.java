package com.github.warrengreen.contrastapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.ArrayList;
import java.util.List;

import com.github.warrengreen.contrastapi.model.Application;
import com.github.warrengreen.contrastapi.repo.ApplicationRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {
    
    @Mock
    private ApplicationRepo appRepo;

    private ApplicationService appService;

    private Pageable pageable;

    @BeforeEach
    public void setup() {
        appService = new ApplicationService(appRepo);
        pageable = Pageable.unpaged();
    }

    @Test
    public void findAllWhenOrgIdDoesntExist(){
        int orgId = 12;
        
        List<Application> apps = new ArrayList<>();
        Page<Application> page = new PageImpl<>(apps);

        doReturn(page).when(appRepo).findAllByOrganizationId(orgId, pageable);
        Page<Application> appsReturned = appService.findAllByOrganizationId(orgId, pageable);

        assertEquals(appsReturned.getSize(),apps.size());

        verify(appRepo,times(1)).findAllByOrganizationId(orgId,pageable);
        verifyNoMoreInteractions(appRepo);
    }

    @Test
    public void findMultipleByOrgId(){
        int orgId = 12;
        
        List<Application> apps = new ArrayList<>();
        
        Application app1 = new Application();
        app1.setName("app1"); 

        Application app2 = new Application();
        app1.setName("app2");
        apps.add(app1);
        apps.add(app2);

        Page<Application> page = new PageImpl<>(apps);
        
        doReturn(page).when(appRepo).findAllByOrganizationId(orgId, pageable);
        Page<Application> appsReturned = appService.findAllByOrganizationId(orgId, pageable);

        assertEquals(appsReturned.getSize(),apps.size());

        verify(appRepo,times(1)).findAllByOrganizationId(orgId,pageable);
        verifyNoMoreInteractions(appRepo);
    }
}
