package org.roronoa.spring_security.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id ;
    @Column(name = "uuid_user",length = 60,nullable = false,unique = true)
    @NotNull @NotEmpty
    private String uuid;
    @Column(nullable = false)
    @NotNull @NotEmpty
    private String name;
    @Column(nullable = false , unique = true)
    @NotNull @NotEmpty @Email
    private String email;
    @Column( nullable = false )
    @NotNull @NotEmpty
    private String password;
    @Column( nullable = false , unique = true)
    @NotNull @NotEmpty
    private String phone;
    @Enumerated(EnumType.STRING)
    private  Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;


}
