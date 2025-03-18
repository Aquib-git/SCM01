package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	  @RequestMapping("/")
	  public String home(Model model) {
		  
		  model.addAttribute("title", "Home-SmartContactManager");
		  
		  return "home";
		  
		  	
	  }
	  
	  
	  
	  
	  
	  @RequestMapping("/about")
	  public String about(Model model) {
		  
		  model.addAttribute("title", "About-SmartContactManager");
		  
		  return "about";
		  
		  
	  }
	  
	  @RequestMapping("/signup")
	  public String signup(Model model) {
		  
		  model.addAttribute("title", "Register-SmartContactManager");
		  
		  model.addAttribute("user", new User());
		  
		  return "signup";
		  
		  
	  }
	  
	  
	  @RequestMapping(value="/do_register", method=RequestMethod.POST)
	  public String registerUser(@Valid @ModelAttribute ("user") User user, BindingResult result1, @RequestParam(value="agreement" ,defaultValue="false") boolean agreement, 
			  Model model,HttpSession session   ) {
		  
		  
		try {
			
			  if (!agreement) {
				  
				  
				  System.out.println("you have not agreed the terms and condition ");
				  
				  throw new Exception("you have not agreed the terms and condition ");
			  }
			  
			  if(result1.hasErrors()) {
				  
				  System.out.println("Errors"+result1.toString());
				  
				  model.addAttribute("user",user);
				  
				  return "signup";
			  }
			  
			  user.setRole("ROLE_USER");
			  
			  user.setEnabled(true);
			  
			  user.setImageUrl("Default.png");	
			  
		  user.setPassword(passwordEncoder.encode(user.getPassword()));
			  
			  
			  System.out.println("Agrement :" +agreement);
			  
			  System.out.println("USER: "+user);
			  
			  User result=this.userRepository.save(user);
			  
			  model.addAttribute("user", new User());
			  
			  model.addAttribute("session", "session");
			  
			session.setAttribute("message", new Message("Successfully Registered!!","alert-success"));
				 
				
				return "signup";
			  
			  
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			model.addAttribute("user", user);
			
			session.setAttribute("message", new Message("Somethingh went wrong !!"+ e.getMessage(),"alert-danger"));
			 
			
			return "signup";
			
		}
		 
	  }
	  
	  
	  @GetMapping("/signin")
	  public String CustomLogin(Model model) {
		  
		  
		  model.addAttribute("title", "Custom Login ");
		  
		 return "customsignin"; 
		  
		  
	  }
	  
	  
}
