package com.api.demo.Controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.api.demo.db.ContactRepository;
import com.api.demo.db.UserRepository;
import com.api.demo.entities.Contacts;
import com.api.demo.entities.User;
import java.nio.file.Files;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userrepo;

	@Autowired
	private ContactRepository contactrepo;


	@ModelAttribute 
	public void addCommonData(Model m, Principal p) { 
		
		String name = p.getName();
		
		User user = userrepo.getUserByUserName(name);
		
	  Iterable<Contacts> c = this.contactrepo.findAll(); 
	  
	  m.addAttribute("user", user);
	  
	}


	@GetMapping("/home")
	public String home() {
		return "USER/home";
	}

	@GetMapping("/add_contacts")
	public String adding_contacts(Model m) {
		m.addAttribute("contact", new Contacts());
		return "USER/add_contacts";
	}

	@PostMapping("/do_register")
	public String Sign(@ModelAttribute("contact") Contacts contact,@RequestParam("ProfileImage") MultipartFile file ,Model model, Principal p) throws IOException {
		
		if(file.isEmpty()) {
			
			contact.setImage("register.png");
			
		}
		
		else {
			
			contact.setImage(file.getOriginalFilename());
			
			File saveFile = new ClassPathResource("/static/image").getFile();
			
			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
			
			Files.copy(file.getInputStream() , path  , StandardCopyOption.REPLACE_EXISTING);
			
			System.out.print("Image is Uploaded");
			
		}
				
		User user = userrepo.getUserByUserName(p.getName());
		
		user.getContacts().add(contact);
		
		contact.setUser(user);
		
		this.userrepo.save(user);
				
		return "USER/home";
		
	}
	
	
	// current page
	// contact per page
	
	@GetMapping("/show_contacts/{page}")
	public String show(@PathVariable("page") Integer page,Principal p, Model m) {
		
		String username = p.getName();
		
		User u = this.userrepo.getUserByUserName(username);
		
		System.out.println(u);
		
		Pageable perpage =PageRequest.of(page, 5);
		
		System.out.println(u.getId());
		
		// Use u.getId() instead of 2
		Page<Contacts> contact = this.contactrepo.getContactsByUser_id(u.getId(), perpage);
		 
		m.addAttribute("contact", contact);
		m.addAttribute("current_page", page);
		m.addAttribute("total_pages", contact.getTotalPages());
		
		System.out.println(contact.getTotalElements());
			
		return "USER/show_contacts";

	} 
	
	@GetMapping("/show_details/{id}")
	public String details(@PathVariable("id") int id, Principal p,Model m) {
		
		String name = p.getName();
		
		User u = this.userrepo.getUserByUserName(name);
		
		System.out.println("This page is running");
		
		Contacts c = this.contactrepo.getContactByCid(id);
				
		if(u.getId() == c.getUser().getId()) {
			
			m.addAttribute("contact", c);
			
			return "USER/show_details";
			
		}
		
		else {
			
			m.addAttribute("error", "You dont permission to access other user saved info details");
			
			return "USER/show_details";
			
		}
		
		
	}
	

	@GetMapping("/deleteAll")
	public String delete(Principal p) {

		// this.contactrepo.deleteByUser_Id(0);

		this.contactrepo.deleteAll();
 
		return "USER/deleteAll";

	}
	
	@GetMapping("/delete/{id}")
	public String deleteId(@PathVariable("id") Integer id, Model m) {
		
		this.contactrepo.deleteById(id);
		
		// String success = "Contact has successfully deleted";
		
		m.addAttribute("success", "Contact has successfully deleted");
		
		return "USER/home";
				
	}

}