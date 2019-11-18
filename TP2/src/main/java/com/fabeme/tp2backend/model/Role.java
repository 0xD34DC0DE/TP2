package com.fabeme.tp2backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public Role() {}

    @JsonCreator
    public Role(@JsonProperty("roles") String role) {

        if(role.equals(RoleName.ROLE_TRADER.toString())) {
            this.name = RoleName.ROLE_TRADER;
        } else if(role.equals(RoleName.ROLE_ADMIN.toString())) {
            this.name = RoleName.ROLE_ADMIN;
        }

    }

    public Role(RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}