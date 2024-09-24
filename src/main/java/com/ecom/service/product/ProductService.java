package com.ecom.service.product;

import com.ecom.model.Product;
import com.ecom.payload.product.ProductDTO;
import com.ecom.payload.product.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts();
}
