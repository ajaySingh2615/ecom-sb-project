package com.ecom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role {
    @Id
    private Integer roleId;
    private AppRole appRole;
}
