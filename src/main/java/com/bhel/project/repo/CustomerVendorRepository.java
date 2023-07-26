package com.bhel.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhel.project.entityImpl.CustomerVendorData;

public interface CustomerVendorRepository extends JpaRepository<CustomerVendorData, Integer> {

}
