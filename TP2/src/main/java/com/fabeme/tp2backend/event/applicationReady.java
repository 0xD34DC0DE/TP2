package com.fabeme.tp2backend.event;

import com.fabeme.tp2backend.message.request.SignUpForm;
import com.fabeme.tp2backend.model.Admin;
import com.fabeme.tp2backend.model.Role;
import com.fabeme.tp2backend.model.RoleName;
import com.fabeme.tp2backend.repository.RoleRepository;
import com.fabeme.tp2backend.resource.AuthRestAPIs;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
        Admin defaultAdmin = null;
        try {
            defaultAdmin = objectMapper.readValue(jsonFileURL, Admin.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(defaultAdmin != null){
            //read JSON like DOM Parser
            JsonNode rootNode = null;

            try {
                rootNode = objectMapper.readTree(jsonFileURL);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Maybe that instead of using this ugly map I could just do a roleRepository.findByName ?
            JsonNode roleNode = null;
            if (rootNode != null) {
                roleNode = rootNode.path("roles");

                if(roleNode != null)
                {
                    Iterator<JsonNode> elements = roleNode.elements();
                    Set<String> adminRoleSet = new HashSet<>();

                    // Search every string in the string array of roles inside the json
                    while(elements.hasNext()){
                        JsonNode roleNodeElement = elements.next();
                        adminRoleSet.add(roleNodeElement.toString().replace("\"", ""));
                    }

                    authRestAPIs.registerUser(new SignUpForm(
                            defaultAdmin.getFirstName(),
                            defaultAdmin.getLastName(),
                            defaultAdmin.getEmail(),
                            defaultAdmin.getPhone(),
                            adminRoleSet,
                            defaultAdmin.getPassword(),
                            defaultAdmin.getAddress()));
                }
            }
        } else {
            throw new RuntimeException("Admin is null");
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
