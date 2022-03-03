package com.poc;

import java.util.Scanner;

import com.poc.AdminApplication;
import com.poc.StoreApplication;

public class ClientApplication {

	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("POC Microservice Application");
		System.out.println();
		System.out.println("[1]Store\n[2]Admin Login\n[3]Exit");
		System.out.println(":");
		int menu = scanner.nextInt();
		switch (menu) {
		case 1:
			StoreApplication.main(args);
			break;
		case 2:
			AdminApplication.main(args);
			break;
			
		case 3:
			
			break;
		default:
			break;
		}
	}
}