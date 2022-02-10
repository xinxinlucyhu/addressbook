package com.tts.adressbook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

@Entity
public class Entry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty(message = "First name cannot be empty.")
	private String firstName;
	@NotEmpty(message = "Last name cannot be empty.")
	private String lastName;
	@NotEmpty(message = "Phone number cannot be empty.")
	private String phoneNumber;
	@Column(unique=true )
	@NotEmpty(message = "Email cannot be empty.")
	private String email;
	
	public Entry() {
		
	}
	
	public Entry(String firstName, String lastName, String phoneNumber, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Entry [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", Email="
				+ email + "]";
	}
}
