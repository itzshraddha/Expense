package com.sp.app.expense.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.app.expense.entity.ExpenseEntity;
import com.sp.app.expense.repositories.ExpenseRepository;

@Service
public class ExpenseService {
	@Autowired
	private ExpenseRepository expenseRepository;

	public ExpenseEntity addExpense(ExpenseEntity expense) {
		// To Do. Add additional validation before saving in db.
		return expenseRepository.save(expense);
	}
}
