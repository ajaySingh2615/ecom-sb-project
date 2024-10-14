package com.ecom.payload.cart;

import com.ecom.payload.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long cardId;
    private Double totalPrice=0.0;
    private List<ProductDTO> products = new ArrayList<>();
}
