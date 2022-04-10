package com.sp.app.expense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "contactNumber")
	private Number contactNumber;
	@Column(name = "email")
	private String email;
	
	public UserEntity() {}
	public UserEntity(String name, Number contactNumber, String email) {
		this.name = name;
		this.contactNumber = contactNumber;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Number getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Number contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", contactNumber=" + contactNumber + ", email=" + email + "]";
	}
}
