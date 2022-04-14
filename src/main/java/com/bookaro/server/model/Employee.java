package com.bookaro.server.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@DiscriminatorValue( value="EM" )
public class Employee extends User {

	private String role;
	private double salary;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor
	 * @param id
	 * @param username
	 * @param password
	 * @param type
	 * @param name
	 * @param surname
	 * @param dni
	 * @param address
	 * @param age
	 * @param role
	 * @param salary
	 */
	public Employee(Long id, String username, String password, String email, 
			String firstName, String lastName, String role, Double salary) {
		super(id, username, password, email, firstName, lastName, email);
		this.role = role;
		this.salary = salary;
	}
	
	// Getter/Setter
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}	
	
		
}
