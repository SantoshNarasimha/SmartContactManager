package com.api.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.demo.db.UserRepository;
import com.api.demo.entities.User;
import com.api.demo.entities.login_validation;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contact")
@EnableMethodSecurity
public class myController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/home")
	public String ok() {
		return "home";
	}
	

	@GetMapping("/signup")
	public String SignUp(Model m) {
		m.addAttribute("title", "Registration - Smart Contact Manager");
		m.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/do_register")
	public String Sign(
	        @Valid @ModelAttribute("user") User user,
	        BindingResult result,
	        Model model)
	{
		
		if (result.hasErrors()) {
		    return "signup";
		}
		
		boolean emailexists = userRepository.existsByEmail(user.getEmail());

		if (emailexists) {
			model.addAttribute("errorMessage", "Email is already registered. Please Login with your details	");
			return "signup";
		} else {

			model.addAttribute("successMessage", " You have Signed Up successfully, using " + user.getEmail());

			user.setRole("ROLE_USER");
			
			user.setEnabled("True");
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			this.userRepository.save(user);

			System.out.println();

			return "home";
		}
	}

	@GetMapping("/login")
	public String login_page(Model m) {
		m.addAttribute("login", new login_validation());
		return "signin";
	}

	@PostMapping("/home")
	public String logi(@ModelAttribute("login") login_validation login, Model m) {
		if (userRepository.existsByEmail(login.getEmail())) {
			m.addAttribute("loginn", "You have loggedin successfully");
			return "home";
		}
		System.out.println("message from home post mapping");
		return "home";
	}
		
	@GetMapping("/signin")
	public String login_validation() {
		return "signin";
	}
	
	
	@GetMapping("/about")
	public String about_contact() {
		return "about";
	}

}