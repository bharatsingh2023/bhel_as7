package com.bhel.project.repo;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bhel.project.entityImpl.BhelVendorBbuData;


public interface GetWamData {

	BhelVendorBbuData getWamParentDataByStNo(Session session, String nxtParentid);

	BhelVendorBbuData getWamChildDataByStNo(Session session, String nxtChildId, String nxtParentid);

	BhelVendorBbuData getGrandChildDataByStNo(Session session, String nxtGrandChildId, String nxtChildId);
	
	public List<String> getAllRightParents(Session session, String cpc, String pkgs, String workArea);

}
