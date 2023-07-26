package com.bhel.project.repo.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bhel.project.entityImpl.BhelVendorBbuData;
import com.bhel.project.repo.GetWamData;

@Repository
public class GetWamDataImpl implements GetWamData {

	static final Logger logger = Logger.getLogger(GetWamDataImpl.class);

	@Override
	public BhelVendorBbuData getWamParentDataByStNo(Session session, String nxtParentid) {
		Query qry = null;
		BhelVendorBbuData returnParentVlues = null;
		//logger.info("next Parent id :: " + nxtParentid);
		//logger.info("session :: " + session.isConnected());
		try {
			String parentQuery = "select bvd from BhelVendorBbuData as bvd where bvd.ST_NO1 = :parentId and bvd.ST_NO2 is null and bvd.ST_NO3 is null";
			qry = session.createQuery(parentQuery);
			qry.setParameter("parentId", nxtParentid);
			// qry.setParameter("quantity", 0);

			returnParentVlues = (BhelVendorBbuData) qry.getSingleResult();
			//logger.info("return Parent Vlues :: " + returnParentVlues);
		} catch (Exception e) {
			logger.error("getWamParentDataByStNo error", e);
		}
		return returnParentVlues;
	}

	@Override
	public BhelVendorBbuData getWamChildDataByStNo(Session session, String nxtChildId, String nxtParentid) {

		Query qry = null;
		BhelVendorBbuData returnChildVlues = null;
		try {
			String childQuery = "select distinct bwd from BhelVendorBbuData as bwd where bwd.ST_NO1 = :parentId and "
					+ "bwd.ST_NO2 = :childId and bwd.ST_NO3 is null";
			qry = session.createQuery(childQuery);
			qry.setParameter("parentId", nxtParentid);
			qry.setParameter("childId", nxtChildId);
			returnChildVlues = (BhelVendorBbuData) qry.getSingleResult();
		} catch (Exception e) {

		}
		return returnChildVlues;
	}

	@Override
	public BhelVendorBbuData getGrandChildDataByStNo(Session session, String nxtGrandChildId, String nxtChildId) {
		Query qry = null;
		BhelVendorBbuData returnGChildVlues = null;
		try {
			String gChildQuery = "select distinct bwd from BhelVendorBbuData as bwd where bwd.ST_NO2 = :childId "
					+ "and bwd.ST_NO3 = :gchildId and bwd.ST_NO4 is null";
			qry = session.createQuery(gChildQuery);
			qry.setParameter("childId", nxtChildId);
			qry.setParameter("gchildId", nxtGrandChildId);
			returnGChildVlues = (BhelVendorBbuData) qry.getSingleResult();
		} catch (Exception e) {

		}
		return returnGChildVlues;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllRightParents(Session session, String cpc, String pkgs, String workArea) {
		Query qry = null;
		List<String> returnParentVlues = null;
		try {
			logger.info("pkgs :: " + pkgs);
			logger.info("cpc :: " + cpc);
			logger.info("workArea :: " + workArea);
			String rigthParentQuery = "select bwds.ST_NO1 from BhelVendorBbuData as bwds where bwds.Unit is null and (bwds.quentity is null or bwds.quentity = 0) and bwds.CPC=:cpc";
			if (pkgs != null || pkgs != "") {
				rigthParentQuery += " and bwds.PKG_Name = :pkgs";
			}
			if (workArea != null || pkgs != "") {
				rigthParentQuery += " and bwds.WORK_AREA = :workArea";
			}
			rigthParentQuery +=" ORDER BY(bwds.ID)";
			//logger.info("rigth Parent Query :: " + rigthParentQuery);
			qry = session.createQuery(rigthParentQuery);
			qry.setParameter("cpc", cpc);
			qry.setParameter("pkgs", pkgs);
			qry.setParameter("workArea", workArea);
			returnParentVlues = qry.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return returnParentVlues;
	}

}
