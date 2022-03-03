package com.poc.controller;

import java.util.List;
import java.util.Optional;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.ProducerState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.converter.EntityConverter;
import com.poc.dto.ProductResponseDTO;
import com.poc.entity.ProductResponse;
import com.poc.entity.Products;
import com.poc.repository.ProductsRepository;

@RestController
@RequestMapping("/api")
public class ProductsController {
	
	private List<ProductResponseDTO> responseList;

	@Autowired
	ProductsRepository productsRepository;
	
	@Autowired
	EntityConverter entityConverter;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	

	ProductResponseDTO productResponseDTO;

	@Bean
	public NewTopic createNewTopic() {
		return TopicBuilder.name("Product_Logger").build();
	}

	@GetMapping("/welcome")
	protected String welcome() {
		System.out.println("Welcome to the Gulag");
		return "welcome";
	}
	
//	@GetMapping("/products")
//	public List<Products> getAllProducts(){
//		try {
//			List<Products> productsList = productsRepository.findAll();
//			return productsList;
//		}catch(Exception e){
//			System.out.println("Error" + e);
//			return null;
//		}
//	}
	@GetMapping("/lists")
	public List<Products> getAllProducts() {
		List<Products> productsList = productsRepository.findAll();
		return productsList;
	}

	@PostMapping("/add")
	public Products createProducts(@RequestBody ProductResponseDTO request) {
		Products product = new Products();
		product = entityConverter.convertToProducts(request);
		productsRepository.save(product);
		kafkaTemplate.send("Product_Logger", "PRODUCT ADDED: "+product);
		return product;
	}

	@PutMapping("/update/{productId}")
	public Products updateProducts(@PathVariable(value = "productId") Long productId, @RequestBody Products productsFrom) {
		Products exisitingProducts = productsRepository.findById(productId).get();
		
//		exisitingProducts.setProductId(productsFrom.getProductId());
		exisitingProducts.setProductBrand(productsFrom.getProductBrand());
		exisitingProducts.setProductName(productsFrom.getProductName());
		exisitingProducts.setProductPrice(productsFrom.getProductPrice());
		exisitingProducts.setProductQty(productsFrom.getProductQty());
		Products updatedProducts = productsRepository.save(exisitingProducts);
		kafkaTemplate.send("Product_Logger", "PRODUCT UPDATE: " +updatedProducts);
		return updatedProducts;
	}
	
	@DeleteMapping("/delete/{id}")
	public List<Products> deleteProduct(@PathVariable("id")Long productId) {
		productsRepository.deleteById(productId);
		kafkaTemplate.send("Product_Logger", "PRODUCT DELETE: Product ID: "+productId);
		return productsRepository.findAll();
	}

	@GetMapping("/productsResponse")
	public ProductResponse getAllProductResponse() {
		List<Products> productsList = productsRepository.findAll();
		responseList = entityConverter.getAllProductsToDTO(productsList);
		ProductResponse productResponse = new ProductResponse();
		productResponse.setListDTO(responseList);
		return productResponse;
	}
	
	@GetMapping("/products/{productId}")
	public ProductResponse getProductResponse(@PathVariable(value = "productId")Long productId) {
	Products products = productsRepository.findById(productId).orElseThrow();	
	productResponseDTO = entityConverter.convertFromProducts(products);
	ProductResponse productResponse = new ProductResponse();
	productResponse.setProductResponseDTO(productResponseDTO);
	return productResponse;
	}
}
