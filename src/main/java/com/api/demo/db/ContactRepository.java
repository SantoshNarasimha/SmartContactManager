package com.api.demo.db;

import com.api.demo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.api.demo.entities.Contacts;

import java.util.List;


public interface ContactRepository extends CrudRepository<Contacts, Integer> {
		
	
		public void deleteByUser_Id(int i);
		
		
		public Contacts getContactByCid(int id);
		
		@Query("from Contacts c where c.user.id = :userId")
		public Page<Contacts> getContactsByUser_id(@Param("userId") int user_id, Pageable perpage );


		public List<Contacts> getUserByNameContainingAndUser(String name, User user);



}
