package com.spring.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.spring.model.Tutorials;


public class ExcelExport {
	
	
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<Tutorials> litutorials;
	CellStyle style = workbook.createCellStyle();
	XSSFFont font = workbook.createFont();

	public ExcelExport(List<Tutorials> litutorials) {
		super();
		this.litutorials = litutorials;
		workbook = new XSSFWorkbook();
	}
	
	private void createCell(Row row, int colCount,Object value, CellStyle style) {
		sheet.autoSizeColumn(colCount);
		Cell cell = row.createCell(colCount);
		if(value instanceof Long) {
			cell.setCellValue((Long) value);
		}else if(value instanceof Integer) {
			cell.setCellValue((Integer) value);
		}else if(value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else {
			cell.setCellValue((String)value); 
		}
		cell.setCellStyle(style);
	}
	
	public void writeHeading() {
		sheet=workbook.createSheet("Tutorials");
		Row row= sheet.createRow(0);
		
		font.setBold(true);
		font.setStrikeout(true);
		font.setFontHeight(22);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		createCell(row, 0, "Tutorials Details....", style);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		font.setFontHeightInPoints((short) 14);
		font.setItalic(true);
				
		row = sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(22);		
		style.setFont(font);
//		style.setAlignment(HorizontalAlignment.LEFT);
		createCell(row, 0, "Tutorial Id", style);
		createCell(row, 1, "Tutorial Discription", style);
		createCell(row, 2, "Tutorial Title", style);
		createCell(row, 3, "Tutorial Published", style);

	}
	
	private void writeData() {
		int rowCount = 2;	
		font.setFontHeight(10);
		font.setItalic(true);
		style.setFont(font);
		
		for (Tutorials tut:litutorials) {
			Row row = sheet.createRow(rowCount++);
			int colCount = 0;
			createCell(row, colCount++, tut.getId(), style);
			createCell(row, colCount++, tut.getDiscription(), style);
			createCell(row, colCount++, tut.getTitle(), style);
			createCell(row, colCount++, tut.isPublished(), style);

		}	
	}
	
	public void export(HttpServletResponse response) throws IOException {
		writeHeading();
		writeData();
		ServletOutputStream outs = response.getOutputStream();
		workbook.write(outs);
		workbook.close();
		outs.close();
	}
}