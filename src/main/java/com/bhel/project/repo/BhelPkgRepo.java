package com.bhel.project.repo;

import java.util.List;

import org.hibernate.Session;

import com.bhel.project.entityImpl.BHEL_PACKAGES;

public interface BhelPkgRepo {

	public List<BHEL_PACKAGES> getVendor(Session session, String pkgName);

}
