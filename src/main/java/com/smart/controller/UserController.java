package com.smart.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kng.brtrprts.brtformbean.BrtFormBean;
import com.kng.brtrprts.brtservice.BirtReportService;
import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	
	
	@Autowired
	public BrtFormBean brtFormBean;
	
	@Autowired
	public BirtReportService bs;
	
	
	// method for adding common data to response 
	@ModelAttribute
	public void addCommonData(Model model , Principal principal) {
		
		
	String userName = principal.getName();
		
		System.out.println("Username  "+ userName);
		
		
		
		//get the user using UserName(email)
		User user = userRepository.getUserbyUserName(userName);
		
		System.out.println("USER " +user);
		
		model.addAttribute("user", user);
			
		
		
		
		
	}
	
	//dashboard home
	@RequestMapping("/index")
	public String dashboard(Model model ,Principal principal) {
	
		model.addAttribute("title", "User Dashboard");
		
		return "normal/user_dashboard";

	}
	
	
	//open Add contact form 
	
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model) {
		
		model.addAttribute("title", "Add Contacts");
		
		model.addAttribute("contact", new Contact());
		
		return "normal/add_contact_form";
		
	}
	
	
	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute Contact contact, 
			@RequestParam("profileImage") MultipartFile file ,
			Principal principal , HttpSession session) {
		
		
		
		try {
		
		String name = principal.getName();
		
		User user= this.userRepository.getUserbyUserName(name);
		
		
		/*
		 * if(3>2) {
		 * 
		 * throw new Exception();
		 * 
		 * }
		 */
		//processing and uploading file..
		
	if(file.isEmpty()) {
			
			
			//if file is empty then try our message
			
		System.out.println("File is empty");
		
			
		}else {
			
			//upload the file to folder and update the name to contact
			
			contact.setImage(file.getOriginalFilename());
			
			File savefile=new ClassPathResource("static/img").getFile();
			
			Path path=Paths.get(savefile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			
			Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
			
			System.out.println("image is uploaded");
			
				
			
		}
	
	
		contact.setUser(user);
		
		user.getContacts().add(contact);

		this.userRepository.save(user);

		System.out.println("DATA"+contact);
		
		System.out.println("Added successfully");
		
		
		//message Success...
		
		session.setAttribute("message", new Message("Your message added !! add more..", "success"));
		
		
		}catch(Exception e) {
			
			
			
			  System.out.println("Error"+e.getMessage());
			  
			  //error message 
			  session.setAttribute("message", new Message("somethingh went wrong!! Try again", "danger"));
			 
			
		
		}
				
		return"normal/add_contact_form";
	
	}
	
	
	
	//show contact handler
	
	//per page =5[n]
	//current page = 0[page]
	
	@GetMapping("/show-contacts/{page}")
	public String showContacts(@PathVariable("page")Integer page, Model m ,Principal principal) {
		
		m.addAttribute("title", "show user contacts");
		
		
		String userName =principal.getName();
		
		User user=this.userRepository.getUserbyUserName(userName);
		
		// Pagination
		//current page - page
		//contact per page 
		
		
		Pageable pageable =PageRequest.of(page,3);
		
		Page<Contact> contacts=this.contactRepository.findContactsByUser(user.getId(),pageable);
		
		m.addAttribute("Contacts", contacts);
		
		m.addAttribute("currentPage", page);
		
		m.addAttribute("totalPages",contacts.getTotalPages());
		
		return "normal/show_contacts";
		
		
		
	}
	
	
	
	
	@GetMapping("/birtReport")
	public String getBirtReportPage(Model model)
	{
		model.addAttribute("brtFormBean", new BrtFormBean());
		model.addAttribute("officeDateFilter",bs.getOfficeDetailsFilter());
		
		
		
		return "./birtReport";
	}
	
	
	
}
