package com.bhel.project.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhel.project.entityImpl.BhelVendorBbuData;

public interface BhelWamDataRepository extends JpaRepository<BhelVendorBbuData, Integer> {
  

}
