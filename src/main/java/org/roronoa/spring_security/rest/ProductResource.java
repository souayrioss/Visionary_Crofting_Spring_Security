package org.roronoa.spring_security.rest;

import lombok.RequiredArgsConstructor;
import org.roronoa.spring_security.dto.ProductDTO;
import org.roronoa.spring_security.dto.ResponseDTO;
import org.roronoa.spring_security.entity.Product;
import org.roronoa.spring_security.service.IProductService;
import org.roronoa.spring_security.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.roronoa.spring_security.util.IConstantes.CODE_000;
import static org.roronoa.spring_security.util.IConstantes.CODE_001;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductResource {
    private final IProductService productService;


    @GetMapping(path = "/getProduct/{reference}")
    public ResponseEntity<ResponseDTO<ProductDTO>> getProduct(@PathVariable @NotEmpty @NotBlank String reference){
        try {
            ResponseDTO<ProductDTO> response = new ResponseDTO<>() ;
            Product product = productService.getProduct(reference);
            if (!Objects.isNull(product)){
                response.setData(EntityUtils.productToProductDTO(product));
                response.setStatus(CODE_001);
            }else {
                response.setStatus("user not exist");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ResponseDTO<ProductDTO> response = new ResponseDTO<>() ;
            response.setStatus(CODE_000);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @GetMapping(path = "/getProducts")
    public ResponseEntity<ResponseDTO<List<ProductDTO>>> getListUser(){
        try{
            List<Product> products = productService.getListProducts();
            ResponseDTO<List<ProductDTO>> response = new ResponseDTO<>() ;
            List<ProductDTO> productDTO = products.stream().map(EntityUtils::productToProductDTO).collect(Collectors.toList());
            response.setStatus(CODE_001);
            response.setData(productDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ResponseDTO<List<ProductDTO>> response = new ResponseDTO<>() ;
            response.setStatus(CODE_000);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @PostMapping(path = "/save")
    public ResponseEntity<ResponseDTO<ProductDTO>> save(@RequestBody @Valid ProductDTO productDTO){
        try{
            Product product = EntityUtils.productDTOToProduct(productDTO);
            productService.saveProduct(product);
            ResponseDTO<ProductDTO> response = new ResponseDTO<>() ;
            response.setData(EntityUtils.productToProductDTO(product));
            response.setStatus(CODE_001);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ResponseDTO<ProductDTO> response = new ResponseDTO<>() ;
            response.setStatus(CODE_000);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @PostMapping(path = "/update")
    public ResponseEntity<ResponseDTO<ProductDTO>> update(@RequestBody @Valid ProductDTO productDTO){
        try {
            Product product = EntityUtils.productDTOToProduct(productDTO);
            Product productUpdated = productService.updateProduct(product);
            ResponseDTO<ProductDTO> response = new ResponseDTO<>() ;
            response.setData(EntityUtils.productToProductDTO(productUpdated));
            response.setStatus(CODE_001);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ResponseDTO<ProductDTO> response = new ResponseDTO<>() ;
            response.setStatus(CODE_000);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
