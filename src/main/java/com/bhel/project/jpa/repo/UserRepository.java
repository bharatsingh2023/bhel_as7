package com.bhel.project.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bhel.project.entityImpl.BhelLogin;


public interface UserRepository extends JpaRepository<BhelLogin, Integer>{

	@Query("select bl from BhelLogin as bl where bl.email_id = :s")
	public BhelLogin findById(@Param("s") String s);

	
	
	/* Boolean existsByEmail(String email); */
	
	//Optional<User> findById(String id);
}
