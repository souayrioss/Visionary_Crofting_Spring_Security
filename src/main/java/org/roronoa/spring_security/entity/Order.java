package org.roronoa.spring_security.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty @NotBlank
    private UUID uuid;
    private LocalDateTime createdAt;

    @NotEmpty @NotNull
    private Double totalPrice;

    @ManyToOne
    @Valid
    @NotNull @NotEmpty
    private UserApp user;

    @NotNull @NotEmpty @Valid
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();


}
