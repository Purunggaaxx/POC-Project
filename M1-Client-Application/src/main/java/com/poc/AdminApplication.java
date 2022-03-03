package com.poc;

import java.util.List;
import java.util.Scanner;

import org.springframework.web.client.RestTemplate;

import com.poc.dto.ProductResponseDTO;
import com.poc.response.ProductResponse;

public class AdminApplication {

	private static Scanner scanner = new Scanner(System.in);
	private static final String productURL = "http://localhost:8181/ecz/api/";
	private static RestTemplate adminRestTemplate = new RestTemplate();
	
	
	public static void main(String[] args) {
	
		System.out.println("---Admin Page---");
		System.out.println();
		System.out.println("[1]View Products\n[2]Search Product\n[3]Add Products");
		System.out.println(":");
		int adminMenu = scanner.nextInt();
		switch (adminMenu) {
		case 1:
			adminDIsplay();
		
			break;
		case 2:
			adminSearchProducts();
			
			break;
		case 3:
			adminAddProducts();
			break;
		default:
			break;
		}
		
	}
	
	public static void adminDIsplay() {
		System.out.println("---Products List---");
		ProductResponse productResponseList = adminRestTemplate.getForObject(productURL+"productsResponse", ProductResponse.class);
		List<ProductResponseDTO> productsList = productResponseList.getListDTO();
		for(ProductResponseDTO dto : productsList) {
			System.out.println("Product ID: "+dto.getProductId());
			System.out.println("Product Brand: "+dto.getProductBrand());
			System.out.println("Product Name: "+dto.getProductName());
			System.out.println("Product Price: "+dto.getProductPrice());
			System.out.println("Product Quantity: "+dto.getProductQty());
			System.out.println();
		}
		
		System.out.println("Press n to go back...");
		String displayBack = scanner.next();
		if(displayBack.equalsIgnoreCase("n")) {
			main(null);
		}else {
			System.out.println("Invalid Input");
			adminDIsplay();
		}
	}
	
	public static void adminSearchProducts() {
		ProductResponse productResponse;
		ProductResponseDTO productSearch;
		System.out.println("---Search Products By ID---");
		System.out.println("Enter ID: ");
		int searchInt = scanner.nextInt();
		
		productResponse = adminRestTemplate.getForObject(productURL+"products/"+searchInt, ProductResponse.class);
		productSearch = productResponse.getProductResponseDTO();
		
		adminDisplaySearched(productSearch);
	
	}
	
	public static void adminDisplaySearched(ProductResponseDTO productSearch) {
		
		System.out.println();
		System.out.println("Product ID: "+productSearch.getProductId());
		System.out.println("Product Brand: "+productSearch.getProductBrand());
		System.out.println("Product Name: "+productSearch.getProductName());
		System.out.println("Product Price: "+productSearch.getProductPrice());
		System.out.println("Product Quantity: "+productSearch.getProductQty());
		System.out.println();
		System.out.println("[1]Update Product\n[2]Delete Product\n[3]Back");
		System.out.println(":");
		int searchMenu = scanner.nextInt();
		switch (searchMenu) {
		case 1:
			adminUpdateProduct(productSearch);
			break;

		case 2:
			adminDeleteProduct(productSearch);
			
			break;
		case 3:
			adminSearchProducts();
			
			break;
		default:
			break;
		}
		
		
	}
	
	public static void adminUpdateProduct(ProductResponseDTO productSearch) {
		System.out.println("---Update Product---");
		System.out.println("Enter new Brand Name: ");
		String newBrand = scanner.nextLine();
		scanner.nextLine();
		System.out.println("Enter new Product Name: ");
		String newName = scanner.nextLine();
		System.out.println("Enter new Price: ");
		Float newPrice = scanner.nextFloat();
		System.out.println("Enter new Quantity: ");
		int newQty = scanner.nextInt();
		
		productSearch.setProductBrand(newBrand);
		productSearch.setProductName(newName);
		productSearch.setProductPrice(newPrice);
		productSearch.setProductQty(newQty);
		
		adminUpdateConfirmation(productSearch);
		
	}
	
	public static void adminUpdateConfirmation(ProductResponseDTO productSearch) {
		
		
	}
	
	public static void adminDeleteProduct(ProductResponseDTO productSearch) {
		System.out.println("Are you sure you want to delete "+productSearch.getProductBrand()+" ?");
		System.out.println("y/n:");
		String deleteChoice = scanner.next();
		switch (deleteChoice.toUpperCase()) {
		case "Y":
			adminRestTemplate.delete(productURL+"delete/"+productSearch.getProductId(), ProductResponse.class);
			System.out.println("Record Deleted!");
			adminSearchProducts();
			break;
		case "N":
			adminSearchProducts();
			break;
		default:
			break;
		}
		
	
		
	}
	
	public static void adminAddProducts() {
//		String addBrand; String addName;
		System.out.println("---Add Products---");
		System.out.println("Enter product Brand: ");
		String addBrand = scanner.nextLine();
		scanner.nextLine();
		System.out.println("Enter product Name: ");
		String addName = scanner.nextLine();
		System.out.println("Enter product Pice: ");
		Float addPrice = scanner.nextFloat();
		System.out.println("Enter product Quantity: ");
		int addQty = scanner.nextInt();
		
		ProductResponseDTO addProduct = new ProductResponseDTO();
		
		addProduct.setProductBrand(addBrand);
		addProduct.setProductName(addName);
		addProduct.setProductPrice(addPrice);
		addProduct.setProductQty(addQty);
		
		adminAddConfirmation(addProduct);
	}
	public static void adminAddConfirmation(ProductResponseDTO addProduct) {
		
	
		System.out.println();
		System.out.println("Product Brand: "+addProduct.getProductBrand());
		System.out.println("Product Name: "+addProduct.getProductName());
		System.out.println("Product Price: "+addProduct.getProductPrice());
		System.out.println("Product Quantity: "+addProduct.getProductQty());
		System.out.println("\nAdd Products? y/n");
		String addConfirm = scanner.next();
		switch (addConfirm.toUpperCase()) {
		case "Y":
			adminRestTemplate.postForObject(productURL+"add", addProduct, ProductResponse.class);
			System.out.println("Product Added!");
			main(null);
			break;
		case "N":
			adminAddProducts();
			break;
		default:
			System.out.println("Invalid Input");
			adminAddConfirmation(addProduct);
			break;
		}
		
	}
}







