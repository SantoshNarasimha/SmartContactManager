package com.api.demo.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.demo.entities.User;


public interface UserRepository extends CrudRepository<User, Integer> {
	
	
	@Query("select u from User u where u.email = :email")
	public User getUserByUserName(@Param("email") String email);

	public boolean existsByEmail(String email);


}
