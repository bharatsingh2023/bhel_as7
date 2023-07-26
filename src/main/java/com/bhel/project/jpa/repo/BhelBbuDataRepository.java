package com.bhel.project.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhel.project.entityImpl.BhelBbuData;

@Repository
public interface BhelBbuDataRepository extends JpaRepository<BhelBbuData, Integer> {



}
