package com.main;

import java.io.IOException;
import java.util.Scanner;

import com.employeeregistration.*;
import com.validation.*;

/*
 * --------------------------------Main Class------------------------------------
 * 
 * Entry point of Use Case 1.
 * 
 * Execution Flow:
 * 	1. Take input from user
 * 	2. Validate user input
 * 	3. Create objects
 * 	4. Persist data
 * 	5. Display Confirmation
 * 
 * @author Developer
 * @version 1.0
 */

public class EmployeePayrollApp {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("======USE CASE 1 : EMPLOYEE REGISTRATION=======");

		try {
			System.out.println("Enter name: ");
			String name = scanner.nextLine();
			System.out.println("Enter phone: ");
			String phone = scanner.nextLine();
			System.out.println("Enter email: ");
			String email = scanner.nextLine();
			System.out.println("Enter your password: ");
			String password = scanner.nextLine();
			Validator.validatePhone(phone);
			Validator.validateEmail(email);
			UserAccount userAccount = new UserAccount(email, password);
			Employee employee = new Employee(name, email, phone, userAccount);
			employee.persist();
		} catch(ValidationException e) {
			System.out.println("\nValidation Failed: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("\nError saving employee data!");
		}
		
		scanner.close();
	}
}