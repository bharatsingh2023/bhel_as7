package com.bhel.project.jpa.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bhel.project.entityImpl.BhelBbuData;

public interface BhelBbuDataRepo extends JpaRepository<BhelBbuData, Integer> {

	@Transactional
	@Modifying
	@Query("update CustomerVendorData as cvd set cvd.custBbuPresent = 1 where cvd.projectCode =:cpc")
	int updateCustomerData(@Param("cpc") String cpc);

	@Transactional
	@Modifying
	@Query("update CustomerVendorData as cvd set cvd.vendorBbuPresent = 1 where cvd.projectCode =:cpc")
	int updateVendorData(@Param("cpc") String cpc);

}
