package com.fabeme.tp2backend.event;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.fabeme.tp2backend.message.request.SignUpForm;
import com.fabeme.tp2backend.model.Role;
import com.fabeme.tp2backend.model.RoleName;
import com.fabeme.tp2backend.repository.RoleRepository;
import com.fabeme.tp2backend.resource.AuthRestAPIs;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class applicationReady {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthRestAPIs authRestAPIs;

    @EventListener(ContextRefreshedEvent.class)
    public void populateDB() {
        System.out.println("READY!...Populating database...");

        System.out.println("Populating RoleRepository");

        // This set is used as argument to roleRepository because converting a HashMap to a Set is not straight forward
        Set<Role> roleSet = new HashSet<>();

        Arrays.stream(RoleName.values()).forEach((RoleName roleName) ->{
            System.out.println("Adding role: " + roleName + " to RoleRepository");
            roleSet.add(new Role(roleName));
        });
        roleRepository.saveAll(roleSet);
        //roleRepository.flush();

        // Get the default admin defined in defaultAdmin.json inside the resources directory
        System.out.println("Loading defaultAdmin.json");

        URL jsonFileURL = getResourcesURL("defaultAdmin.json");

        ObjectMapper objectMapper = new ObjectMapper();

        SignUpForm adminSignUpForm = null;
        try {
            adminSignUpForm = objectMapper.readValue(jsonFileURL, SignUpForm.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (adminSignUpForm != null) {
            authRestAPIs.registerUser(adminSignUpForm);
        } else
        {
            throw new RuntimeException("Error while calling authRestAPI for adminSignUpForm");
        }

    }

    private URL getResourcesURL(String ressourceFileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(ressourceFileName);

        if (resource == null) {
            throw new IllegalArgumentException("Resource file not found!");
        } else {
            return resource;
        }
    }
}
