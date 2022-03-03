package com.poc.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.poc.dto.ProductResponseDTO;
import com.poc.entity.ProductResponse;
import com.poc.entity.Products;

@Component
public class EntityConverter {

	public Products convertToProducts(ProductResponseDTO request) {
		Products products = new Products();
		
		products.setProductId(request.getProductId());
		products.setProductBrand(request.getProductBrand());
		products.setProductName(request.getProductName());
		products.setProductPrice(request.getProductPrice());
		products.setProductQty(request.getProductQty());
		
		return products;
	}
	
	public ProductResponseDTO convertFromProducts(Products products) {
		ProductResponseDTO response = new ProductResponseDTO();
		
		response.setProductId(products.getProductId());
		response.setProductBrand(products.getProductBrand());
		response.setProductName(products.getProductName());
		response.setProductPrice(products.getProductPrice());
		response.setProductQty(products.getProductQty());
		
		return response;
	}
	
	public List<ProductResponseDTO> getAllProductsToDTO(List<Products> products){
		List<ProductResponse> responseList = new ArrayList<>();
		products.stream().map(this::convertFromProducts).collect(Collectors.toList());
		return products.stream().map(this::convertFromProducts).collect(Collectors.toList());
	}
}
