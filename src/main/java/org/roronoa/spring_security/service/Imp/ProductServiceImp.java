package org.roronoa.spring_security.service.Imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.roronoa.spring_security.entity.Product;
import org.roronoa.spring_security.repository.ProductRepository;
import org.roronoa.spring_security.service.IProductService;
import org.roronoa.spring_security.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImp implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public Product getProduct(String reference) {
        Optional<Product> product = productRepository.findByReference(reference);
        return product.orElse(null);
    }

    @Override
    public List<Product> getListProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        product.setReference(EntityUtils.generateReference());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        productRepository.save(product);
        return getProduct(product.getReference());
    }
}
