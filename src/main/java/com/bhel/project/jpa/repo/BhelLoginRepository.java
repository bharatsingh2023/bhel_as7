
package com.bhel.project.jpa.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bhel.project.entityImpl.BhelLogin;

@Repository
public interface BhelLoginRepository extends JpaRepository<BhelLogin, Integer> {

	@Query("select bl from BhelLogin as bl where bl.email_id = :email")
	BhelLogin checkEmailCount(@Param("email") String email);
	
	
	@Transactional
	 @Modifying
	 @Query(value =	 "UPDATE BHEL_USER SET bpassword = :password WHERE buser = :id", nativeQuery = true)
	 public void update(@Param("id") String id, @Param("password") String password);
			 

}
