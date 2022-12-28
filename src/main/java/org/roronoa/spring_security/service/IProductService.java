package org.roronoa.spring_security.service;


import org.roronoa.spring_security.entity.Product;

import java.util.List;

public interface IProductService {
    Product getProduct(String reference);
    List<Product> getListProducts();
    Product saveProduct(Product product);
    Product updateProduct(Product product);



}
