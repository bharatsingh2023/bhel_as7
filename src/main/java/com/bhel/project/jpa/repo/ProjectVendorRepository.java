package com.bhel.project.jpa.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bhel.project.entityImpl.BHEL_PACKAGES;
import com.bhel.project.entityImpl.CustomerVendorData;
import com.bhel.project.entityImpl.ProjectVendorData;

public interface ProjectVendorRepository extends JpaRepository<ProjectVendorData, Integer> {

//	@Query("select pvd from ProjectVendorData as pvd where pvd.projectCode = :project_code and pvd.workArea is null")
//	List<ProjectVendorData> findAllByProject_code(@Param("project_code") String project_code);
	
	@Query("select pvd from ProjectVendorData as pvd where pvd.projectCode = :project_code")
	List<ProjectVendorData> findAllByProject_code(@Param("project_code") String project_code);

	@Transactional
	@Modifying
	@Query("update ProjectVendorData as pVData set workArea =:workArea where pVData.pKg =:projectPackage and pVData.projectCode =:projectCode")
	int updateWorkArea(@Param("projectPackage") String projectPackage, @Param("projectCode") String projectCode,
			@Param("workArea") String workArea);

	@Query("select pVData.workArea from ProjectVendorData as pVData where pVData.projectCode=:projectCode and pVData.workArea is not null")
	List<String> getByProjectId(@Param("projectCode") String projectCode);

	@Query("select pvd from BHEL_PACKAGES as pvd where pvd.PACKAGE_NAME = :pkgName")
	BHEL_PACKAGES getPackageByPkgName(@Param("pkgName") String pkgName);

	@Query("select pvd from CustomerVendorData as pvd where pvd.projectCode = :project_NAME")
	CustomerVendorData getCcn(@Param("project_NAME") String project_NAME);

}
