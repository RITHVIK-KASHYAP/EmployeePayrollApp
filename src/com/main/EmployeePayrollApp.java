package com.main;

import java.io.IOException;
import java.util.Scanner;

import com.employeeauthentication.AuthenticationService;
import com.employeeauthentication.Session;
import com.employeeregistration.Employee;
import com.employeeregistration.UserAccount;
import com.validation.ValidationException;
import com.validation.Validator;

/*
 * --------------------------------Main Class------------------------------------
 * 
 * Entry point of Use Case 2- Employee Authentication.
 * 
 * Execution Flow:
 * 	1. Trigger login
 * 	2. Receive session
 * 	3. Validate session state
 *  
 * @author Rithvik
 * @version 2.0
 */



public class EmployeePayrollApp {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("======USE CASE 2 : EMPLOYEE AUTHENTICATION=======");

		try {
			System.out.println("Enter name: ");
			String name = scanner.nextLine();
			System.out.println("Enter phone: ");
			String phone = scanner.nextLine();
			System.out.println("Enter email: ");
			String email = scanner.nextLine();
			System.out.println("Enter your password: ");
			String password = scanner.nextLine();
			Validator.validateEmail(email);
			Validator.validatePhone(phone);
			UserAccount userAccount = new UserAccount(email, password);
			Employee employee = new Employee(name, email, phone, userAccount);
			employee.persist();
		} catch(ValidationException e) {
			System.out.println("\nValidation Failed: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("\nError saving employee data!");
		}
		
		 AuthenticationService auth = new AuthenticationService();
	        Session session = auth.login();

	        if (session != null) {
	            System.out.println("\n" + session);
	            if (!session.isExpired()) {
	                System.out.println("Session active and valid.");
	            } else {
	                System.out.println("Session expired.");
	            }
	        }
		
		scanner.close();
	}
}