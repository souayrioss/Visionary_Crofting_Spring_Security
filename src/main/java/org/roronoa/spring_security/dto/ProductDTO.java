package org.roronoa.spring_security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductDTO {

    Long id;

    private String reference;

    @NotEmpty  @NotBlank
    private String title;

    @NotEmpty  @NotBlank
    private String description;

    @Min(0)
    @NotNull
    private float initialPrice ;

    @Min(1)
    @NotNull
    private int quantity;

}
