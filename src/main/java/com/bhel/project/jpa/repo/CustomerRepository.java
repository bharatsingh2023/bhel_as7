package com.bhel.project.jpa.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bhel.project.dtoImpl.CustomerVendorData;
import com.bhel.project.entityImpl.CPC_Master;

@Repository
public interface CustomerRepository extends JpaRepository<CPC_Master, String> {

	//countQuery = "SELECT COUNT(*) FROM BHEL_CPC_Master  WHERE ( CUSTOMER_PROJECT_CODE LIKE %:search% or CUSTOMER_PROJECT_Name like %:search%)",
	@Query(value = "SELECT CUSTOMER_PROJECT_CODE,CUSTOMER_PROJECT_NAME FROM BHEL_CPC_Master  "
			+ "WHERE ( CUSTOMER_PROJECT_CODE LIKE %:search% or UPPER(CUSTOMER_PROJECT_Name) like %:search%)",nativeQuery = true)
	Page<CPC_Master> searchCPC(@Param("search") String search, Pageable pageable);

	//List<CustomerVendorData> getCustomerdata();
}
