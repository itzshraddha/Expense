package com.sp.app.expense.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sp.app.expense.entity.ExpenseEntity;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Integer>{

}
