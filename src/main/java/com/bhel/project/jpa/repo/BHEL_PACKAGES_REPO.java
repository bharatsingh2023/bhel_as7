package com.bhel.project.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhel.project.entityImpl.BHEL_PACKAGES;

@Repository
public interface BHEL_PACKAGES_REPO extends JpaRepository<BHEL_PACKAGES, Integer> {

}
