package com.kng.brtrprts.brtcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kng.brtrprts.brtformbean.BrtFormBean;
import com.kng.brtrprts.brtservice.BirtReportService;

@Controller
public class BirtMainController 
{
	
		Logger logger = LoggerFactory.getLogger(BirtMainController.class);
	
	@Autowired
	public BrtFormBean brtFormBean;
	
	@Autowired
	public BirtReportService bs;
	
	
	
	@GetMapping("/birtReport")
	public String getBirtReportPage(Model model)
	{
		model.addAttribute("brtFormBean", new BrtFormBean());
		model.addAttribute("officeDateFilter",bs.getOfficeDetailsFilter());
		return "./birtReport";
	}
	
	@GetMapping("/birtReportOld")
	public String getBirtReportPageOld()
	{
		return "./birtReportOld";
	}
	
	
	
	

}
