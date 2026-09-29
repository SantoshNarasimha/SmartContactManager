package com.api.demo.Controller;


import com.api.demo.db.ContactRepository;
import com.api.demo.db.UserRepository;
import com.api.demo.entities.Contacts;
import com.api.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ContactRepository contactrepo;

    @Autowired
    private UserRepository userrepo;

    @GetMapping("/{query}")
    public ResponseEntity<?> search_info(@PathVariable("query") String name, Principal principal){

        System.out.println(name);

        User logged_user = this.userrepo.getUserByUserName(principal.getName());

        List<Contacts> names = this.contactrepo.getUserByNameContainingAndUser(name, logged_user);

        return ResponseEntity.ok(names);


    }

}
