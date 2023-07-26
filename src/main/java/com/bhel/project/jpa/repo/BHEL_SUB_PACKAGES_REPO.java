package com.bhel.project.jpa.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bhel.project.entityImpl.BHEL_SUB_PACKAGES;
@Repository
public interface BHEL_SUB_PACKAGES_REPO extends JpaRepository<BHEL_SUB_PACKAGES, Integer> {

	@Query(value = "SELECT SUB_PACKAGES_NAME,SUB_PACKAGES_ID FROM BHEL_SUB_PACKAGES  WHERE( UPPER(SUB_PACKAGES_NAME) like %:search%) ",nativeQuery = true)
 public	Page<BHEL_SUB_PACKAGES> search_SUB_PACKAGES(@Param("search") String search, Pageable pageable);

}
