package com.sp.app.expense.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExpenseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "item")
	private String item;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "consumer")
	private String consumer;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "amount")
	private Number amount;
	
	@Column(name = "date")
	private LocalDate date;
	
	public ExpenseEntity() {}
	public ExpenseEntity(String item, String category, String consumer, String description, Number amount, LocalDate date) {
		this.item = item;
		this.category = category;
		this.consumer = consumer;
		this.description = description;
		this.amount = amount;
		this.date = date;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getConsumer() {
		return consumer;
	}

	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Number getAmount() {
		return amount;
	}

	public void setAmount(Number amount) {
		this.amount = amount;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
