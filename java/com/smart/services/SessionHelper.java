package com.smart.services;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Component
public class SessionHelper {
	
	public void removeMessageFromSession() {
		
		
		try {
			
			
			System.out.println("Removing Message from Session");
			
			HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
			
			session.removeAttribute("message");
			
		}catch(Exception e) {
		
		
		
		}
		
		
	}

}
