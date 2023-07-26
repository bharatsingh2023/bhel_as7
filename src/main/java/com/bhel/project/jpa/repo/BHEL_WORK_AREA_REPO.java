package com.bhel.project.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bhel.project.entityImpl.BhelWorkArea;

public interface BHEL_WORK_AREA_REPO extends JpaRepository<BhelWorkArea, Integer> {

	// @Query(value = "SELECT WORK_AREA_ID,WORK_AREA_NAME FROM BHEL_WORK_AREA WHERE(
	// UPPER(WORK_AREA_NAME) like %:search%) ", nativeQuery = true)
	// public Page<BHEL_WORK_AREA> search_Work_Area(@Param("search") String search,
	// Pageable pageable);

	@Query(value = "SELECT bwa FROM BhelWorkArea as bwa")
	public List<BhelWorkArea> search_Work_Area();

}
