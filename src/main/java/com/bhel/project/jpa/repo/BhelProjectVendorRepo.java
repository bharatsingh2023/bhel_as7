package com.bhel.project.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bhel.project.entityImpl.ProjectVendorData;

public interface BhelProjectVendorRepo extends JpaRepository<ProjectVendorData, Integer> {

	@Query(value = "select * from BHEL_PROJECT_VENDOR where PROJECT_CODE=:search", nativeQuery = true)
	public List<ProjectVendorData> GetAllPackages(@Param("search") String search);

	@Query(value = "select * from BHEL_PROJECT_VENDOR where (UPPER(PKG)=:search or PROJECT_CODE=:search or vendor_id=:search) and WORK_AREA is not null", nativeQuery = true)
	public List<ProjectVendorData> GetAllWorkArea(@Param("search") String packages);

	@Query("select count(pvd) from ProjectVendorData as pvd where pvd.projectCode =:cpc and pvd.pKg=:package_NAME")
	int getPackageCount(@Param("package_NAME") String package_NAME, @Param("cpc") String cpc);

	//public List<ProjectVendorData> findAllWithCpc(String project_code);
}