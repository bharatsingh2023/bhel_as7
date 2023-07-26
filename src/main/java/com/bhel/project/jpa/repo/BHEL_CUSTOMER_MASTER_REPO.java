package com.bhel.project.jpa.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bhel.project.entityImpl.BHEL_CUSTOMER_MASTER;

@Repository
public interface BHEL_CUSTOMER_MASTER_REPO extends JpaRepository<BHEL_CUSTOMER_MASTER, Integer> {
	@Query(value = "SELECT * FROM BHEL_CUSTOMER_MASTER WHERE CUSTOMER_CONTACT_NO = :contact", nativeQuery = true)
	public List<BHEL_CUSTOMER_MASTER> getContact(@Param("contact") String contact);

	@Query(value = "SELECT bcm.CUSTOMET_NAME FROM BHEL_CUSTOMER_MASTER as bcm WHERE bcm.cpc = :cpc")
	public String getCustomerName(@Param("cpc") String cpc);

	@Query(value = "SELECT * FROM BHEL_CUSTOMER_MASTER WHERE(UPPER(CUSTOMET_NAME) LIKE %:search% or CUSTOMER_CONTACT_NO LIKE %:search%)", nativeQuery = true)
	public Page<BHEL_CUSTOMER_MASTER> getCCN(@Param("search") String search, Pageable pageable);

}