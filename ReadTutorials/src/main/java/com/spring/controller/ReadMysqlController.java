package com.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.model.Tutorials;
import com.spring.repository.TutorialsRepository;
import com.spring.service.ExcelExport;

@Controller
public class ReadMysqlController {
	@Autowired
	private TutorialsRepository tutorialsRepository;
	
	@RequestMapping("/excel/tutorials")
	@ResponseBody
	public String exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		String headrerValue="attachment; filename=Tutorials.xlsx";
		response.setHeader(headerKey, headrerValue);
		
		List<Tutorials> listTuts = tutorialsRepository.findAll();
		ExcelExport exp =new ExcelExport(listTuts);
		exp.export(response);
		return "Export Success";
	}

}
