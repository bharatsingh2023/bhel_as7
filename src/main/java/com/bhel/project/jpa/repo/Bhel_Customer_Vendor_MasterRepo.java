package com.bhel.project.jpa.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bhel.project.entityImpl.CustomerVendorData;

public interface Bhel_Customer_Vendor_MasterRepo extends JpaRepository< CustomerVendorData, Integer> {


	@Query(value = "SELECT * FROM BHEL_CUSTOMER_PROJECT_MASTER  "
			+ "WHERE ( PROJECT_CODE LIKE %:search% or UPPER(PROJECT_NAME) like %:search%)",nativeQuery = true)
	public Page<CustomerVendorData> searchCPC(@Param("search") String search, Pageable pageable);
	

	@Query(value = "select * from BHEL_CUSTOMER_PROJECT_MASTER bcpm WHERE bcpm.PROJECT_CODE= :search" ,nativeQuery = true)
	public CustomerVendorData getCcn(@Param("search") String serach);



}