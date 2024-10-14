package com.ecom.service.cart;

import com.ecom.payload.cart.CartDTO;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);
}
