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
public class BirtPatController
{
	Logger logger = LoggerFactory.getLogger(BirtPatController.class);
	
	@Autowired
	public BrtFormBean brtFormBean;
	
	@Autowired
	public BirtReportService bs;
	
	public String sdate = "", edate = "",reportUrl=""; 
	public Integer oid,did,docid;
	
	
	@Value("${birt.report.base.url}")
	private String reportBaseUrl;
	
	
	@GetMapping("/patientVisitsByDoctorDepartmentReport")	
	public void getpatientVisitsByDoctorDepartmentReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("patientVisitsByDoctorDepartmentReport====" + reportBaseUrl);
		
		  sdate	=brtFormBean.getBrtSdate();
		  edate	=brtFormBean.getBrtEdate();
		  oid	=brtFormBean.getBrtOid();
		  did	=brtFormBean.getBrtDid();
		  docid	=brtFormBean.getBrtDocid();
		
		//.START
		if(brtFormBean.getBrtSdate()!=null && brtFormBean.getBrtEdate()!=null)
		{	
			if(brtFormBean.getBrtOid()==0)// user selects all clinic scenario
			{
				reportUrl= reportBaseUrl+"KngMsd_////.rptdesign&SDATE="+sdate+"&EDATE="+edate;
			}
			else // user selects specific clinic scenario
			{
				if(did==0)//specific clinic -> all department -> all doctor
				{
					reportUrl= reportBaseUrl+"KngMsd_////.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid;
				}
				else//specific clinic -> specific department scenario
				{
					if(docid==0)//specific clinic -> specific department -> all doctors
					{
						reportUrl= reportBaseUrl+"KngMsd_////.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid+"&DID="+did;
					}
					else//specific clinic -> specific department -> specific doctor
					{
						reportUrl= reportBaseUrl+"KngMsd_////.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid+"&DID="+did+"&DOCID="+docid;
					}
				}
			}
			response.sendRedirect(reportUrl);
		}
		//.STOP
		
	}
	
	@GetMapping("/patientcount")
	public void getpatientcountReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("patientcount====" + reportBaseUrl);
		
		  sdate	=brtFormBean.getBrtSdate();
		  edate	=brtFormBean.getBrtEdate();
		  
		  if(brtFormBean.getBrtSdate()!=null && brtFormBean.getBrtEdate()!=null)
		  {
			  reportUrl= reportBaseUrl+"pat_count_demogr1.rptdesign&SDATE="+sdate+"&EDATE="+edate;
		  }
		  response.sendRedirect(reportUrl);
	}
	
	
	@GetMapping("/visitcount")
	public void getvisitcountReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("visitcount====" + reportBaseUrl);
		
		  sdate	=brtFormBean.getBrtSdate();
		  edate	=brtFormBean.getBrtEdate();
		  
		  if(brtFormBean.getBrtSdate()!=null && brtFormBean.getBrtEdate()!=null)
		  {			  
			  reportUrl= reportBaseUrl+"pat_count_demogr2.rptdesign&SDATE="+sdate+"&EDATE="+edate;
		  }
		  response.sendRedirect(reportUrl);
	}
	
	
	
	@GetMapping("/patvisitOfficerSoldierCivilianFamily")
	public void getpatvisitOfficerSoldierCivilianFamilyReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("patvisitOfficerSoldierCivilianFamily====" + reportBaseUrl);
		
		  sdate	=brtFormBean.getBrtSdate();
		  edate	=brtFormBean.getBrtEdate();
		  
		  if(brtFormBean.getBrtSdate()!=null && brtFormBean.getBrtEdate()!=null)
		  {
			  reportUrl= reportBaseUrl+"speciality_stat.rptdesign&SDATE="+sdate+"&EDATE="+edate;
		  }
		  response.sendRedirect(reportUrl);
	}
	
	@GetMapping("/chronicPatientReport")
	public void getpatChronicPatientDetailedReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("patvisitOfficerSoldierCivilianFamily====" + reportBaseUrl);
		
		
		 sdate =brtFormBean.getBrtSdate();
		 edate =brtFormBean.getBrtEdate();
		 oid   =brtFormBean.getBrtOid();
		 
		 
		if(brtFormBean.getBrtSdate()!=null && brtFormBean.getBrtEdate()!=null)
		  {
				if(brtFormBean.getBrtOid()==0)// user selects all clinic scenario
				{
					reportUrl= reportBaseUrl+"KngMsd_ChronicPatient_All.rptdesign&SDATE="+sdate+"&EDATE="+edate;
				}
				else 
				{
					reportUrl= reportBaseUrl+"KngMsd_ChronicPatient_ByOffice.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid;
				}
		  }
		  response.sendRedirect(reportUrl);
	}
	
}
