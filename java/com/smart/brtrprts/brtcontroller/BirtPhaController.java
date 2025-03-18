package com.kng.brtrprts.brtcontroller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kng.brtrprts.brtformbean.BrtFormBean;
import com.kng.brtrprts.brtservice.BirtReportService;

@Controller
public class BirtPhaController 
{
	Logger logger = LoggerFactory.getLogger(BirtPhaController.class);
	
	@Autowired
	public BrtFormBean brtFormBean;
	
	@Autowired
	public BirtReportService bs;
	
	public String sdate = "", edate = "",reportUrl=""; 
	public Integer oid;
	
	@Value("${birt.report.base.url}")
	private String reportBaseUrl;
	
	
	
	@GetMapping("/medicineStockClinicWise")	
	public void getmedicineStockClinicWiseReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("medicineStockClinicWise====" + reportBaseUrl);
	
		
		oid	=	brtFormBean.getBrtOid();
		if(brtFormBean.getBrtOid()==0)
		{					
			reportUrl= reportBaseUrl+"KngMsd_PharamacyStockByAll.rptdesign";
		}
		else 
		{
			reportUrl= reportBaseUrl+"KngMsd_PharamacyStockByClinic.rptdesign&OID="+oid;
		}
		
		response.sendRedirect(reportUrl);
	}
	
	
	//phavisitOfficerSoldierCivilianFamily
	@GetMapping("/phavisitOfficerSoldierCivilianFamily")	
	public void getphavisitOfficerSoldierCivilianFamilyReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		  System.out.println("phavisitOfficerSoldierCivilianFamily====" + reportBaseUrl);
		
		  sdate	=brtFormBean.getBrtSdate();
		  edate	=brtFormBean.getBrtEdate();
		  
		  if(brtFormBean.getBrtSdate()!=null && brtFormBean.getBrtEdate()!=null)
		  {
			  reportUrl= reportBaseUrl+"civil_med_catgory.rptdesign&SDATE="+sdate+"&EDATE="+edate;
		  }
		  response.sendRedirect(reportUrl);
		
	}
	

}
