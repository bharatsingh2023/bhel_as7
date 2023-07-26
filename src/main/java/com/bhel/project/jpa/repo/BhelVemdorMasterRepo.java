package com.bhel.project.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bhel.project.entityImpl.BhelVendorMaster;


@Repository
public interface BhelVemdorMasterRepo extends JpaRepository<BhelVendorMaster, Integer> {

	@Query(value="SELECT * FROM BhelVendorMaster WHERE VENDOR_CONTACT_NO = :contact", nativeQuery = true)
	public List<BhelVendorMaster> getContact(@Param("contact") String contact);
	
}
