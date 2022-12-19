package org.roronoa.spring_security.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Entity
@Data
@NoArgsConstructor

@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @NotEmpty  @NotBlank
    private String reference;

    @NotEmpty  @NotBlank
    private String title;

    @NotEmpty  @NotBlank
    private String description;

    @Min(0)
    private float initialPrice ;

    @Min(1)
    private int quantity;


    public Product(String reference, String title, String description, float initialPrice, int quantity) {
        this.reference = reference;
        this.title = title;
        this.description = description;
        this.initialPrice = initialPrice;
        this.quantity = quantity;
    }
}
