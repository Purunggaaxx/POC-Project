package com.poc.dto;

import java.util.List;

import com.poc.entity.ProductResponse;

public class ProductResponseDTO {
	private Long productId;

	private String productBrand;

	private String productName;

	private float productPrice;

	private int productQty;

	public ProductResponseDTO(Long productId, String productBrand, String productName, float productPrice, int productQty) {
		super();
		this.productId = productId;
		this.productBrand = productBrand;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQty = productQty;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public ProductResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
