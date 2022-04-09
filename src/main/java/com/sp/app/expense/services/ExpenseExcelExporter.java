package com.sp.app.expense.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.sp.app.expense.entity.ExpenseEntity;

@Service
public class ExpenseExcelExporter {

	public ByteArrayInputStream export(List<ExpenseEntity> expensesList) throws IOException {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Expenses");
			
			Row row = sheet.createRow(0);
			
			 XSSFFont defaultFont= (XSSFFont) workbook.createFont();
			 defaultFont.setFontHeightInPoints((short)10);
			 defaultFont.setFontName("Georgia");
			 defaultFont.setColor(IndexedColors.WHITE.getIndex());
			 defaultFont.setBold(true);
			 defaultFont.setItalic(false);
			 
			// Define header cell style
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.PLUM.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        headerCellStyle.setFont(defaultFont);
	        
	        // Creating header cells 
	        Cell cell = row.createCell(0);
	        cell.setCellValue("ID");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Item");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(2);
	        cell.setCellValue("Category");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(3);
	        cell.setCellValue("Consumer");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(4);
	        cell.setCellValue("Description");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(5);
	        cell.setCellValue("Amount");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(6);
	        cell.setCellValue("Date");
	        cell.setCellStyle(headerCellStyle);
	        
	        // Creating data rows for each contact
	        for(int i = 0; i < expensesList.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(expensesList.get(i).getId());
	        	dataRow.createCell(1).setCellValue(expensesList.get(i).getItem());
	        	dataRow.createCell(2).setCellValue(expensesList.get(i).getCategory());
	        	dataRow.createCell(3).setCellValue(expensesList.get(i).getConsumer());
	        	dataRow.createCell(4).setCellValue(expensesList.get(i).getDescription());
	        	dataRow.createCell(5).setCellValue(expensesList.get(i).getAmount().toString());
	        	dataRow.createCell(6).setCellValue(expensesList.get(i).getDate().toString());
	        }
	        
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        sheet.autoSizeColumn(5);
	        sheet.autoSizeColumn(6);
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (Exception e) {
			throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
		}
	}
}
