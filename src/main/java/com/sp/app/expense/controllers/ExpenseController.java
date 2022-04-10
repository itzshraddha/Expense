package com.sp.app.expense.controllers;

import java.io.ByteArrayInputStream;
import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sp.app.expense.entity.ExpenseEntity;
import com.sp.app.expense.repositories.ExpenseRepository;
import com.sp.app.expense.services.ExpenseExcelExporter;
import com.sp.app.expense.services.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private ExpenseExcelExporter expenseExcelReporter;

	@GetMapping("/fetch")
	public ResponseEntity<List<ExpenseEntity>> getAllExpenses() {
		try {
			List<ExpenseEntity> expense = new ArrayList<>();
			expense = expenseRepository.findAll();
			if (expense.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(expense, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<String> saveUserDetails(@RequestBody ExpenseEntity expense) {
		try {
			if (expense.getAmount() == null || expense.getAmount().intValue() == 0) {
				return new ResponseEntity<>("Amount can not be null or 0. Please enter valid amount.",
						HttpStatus.BAD_REQUEST);
			} else {
				expenseService.addExpense(expense);
				return new ResponseEntity<>("Expense added successfully !", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Unable to add expense. Please try again later...",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//C:\Users\This PC\AppData\Local\Postman\app-7.36.7
	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> exportReport() {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String filename = "UserExpense-"+dtf.format(now)+".xlsx";
			List<ExpenseEntity> expenseList = expenseRepository.findAll();
			InputStreamResource file = new InputStreamResource(expenseExcelReporter.export(expenseList));
			//ByteArrayInputStream byteArrayInputStream = expenseExcelReporter.export(expenseList);
			 return ResponseEntity.ok()
				        .header("Content-Disposition", "attachment; filename=" + filename)
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		} catch (Exception e) {
			System.out.println("----------------"+ e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
