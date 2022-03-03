package com.poc.entity;

import java.util.List;

import com.poc.dto.ProductResponseDTO;

public class ProductResponse {

	private List<ProductResponseDTO> products;
	
	public List<ProductResponseDTO> getListDTO(){
		return products;
	}
	public void setListDTO(List<ProductResponseDTO> productList) {
		this.products = productList;
	}
	
	private ProductResponseDTO productResponseDTO;
	
	public ProductResponseDTO getProductResponseDTO(){
		return productResponseDTO;
	}
	public void setProductResponseDTO(ProductResponseDTO product) {
		this.productResponseDTO = product;
	}
	
}
