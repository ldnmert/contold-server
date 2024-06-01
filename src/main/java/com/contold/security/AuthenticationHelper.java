package com.contold.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.contold.entity.User;
import com.contold.repository.UserRepository;

//@Component
//public class AuthenticationHelper {
//
//	private static UserRepository userRepository;
//	
//	
//	AuthenticationHelper(UserRepository userRepository){
//		AuthenticationHelper.userRepository = userRepository;
//	}
//	
//	
//	
//	public static String getCurrentUsername() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null || !authentication.isAuthenticated()) {
//			return null;
//		}
//		return authentication.getName();
//	}
//	
//	public static Long getUser() {
//		
//		User user = userRepository.
//		
//	}
//
//}
