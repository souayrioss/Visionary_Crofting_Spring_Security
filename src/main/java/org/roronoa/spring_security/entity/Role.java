package org.roronoa.spring_security.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "role")
    private List<UserApp> usersApp;

    public Role(String nom) {
        this.nom = nom;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<UserApp> getUsersApp() {
        return usersApp;
    }

    public void setUsersApp(List<UserApp> usersApp) {
        this.usersApp = usersApp;
    }
}