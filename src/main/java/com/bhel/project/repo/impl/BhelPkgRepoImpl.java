package com.bhel.project.repo.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bhel.project.entityImpl.BHEL_PACKAGES;
import com.bhel.project.entityImpl.BhelVendorBbuData;
import com.bhel.project.repo.BhelPkgRepo;

@Repository
public class BhelPkgRepoImpl implements BhelPkgRepo {
	static final Logger logger = Logger.getLogger(BhelPkgRepoImpl.class);

	@SuppressWarnings("unchecked")
	public List<BHEL_PACKAGES> getVendor(Session session, String pkgName) {
		Query qry = null;
		List<BHEL_PACKAGES> returnVlues = null;

		try {
			String parentQuery = "select bp from BHEL_PACKAGES as bp where bp.PKG_ID =:pkg";
			qry = session.createQuery(parentQuery);
			qry.setParameter("pkg", Integer.parseInt(pkgName));

			returnVlues = (List<BHEL_PACKAGES>) qry.getResultList();
			logger.info("returnVlues size --->" + returnVlues);
		} catch (Exception e) {
			logger.error("getWamParentDataByStNo error", e);
		}
		return returnVlues;
	}

}
