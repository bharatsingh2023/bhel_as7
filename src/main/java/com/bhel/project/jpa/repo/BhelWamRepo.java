package com.bhel.project.jpa.repo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bhel.project.dtoImpl.ParentChildDto;
import com.bhel.project.entityImpl.BhelVendorBbuData;

public interface BhelWamRepo extends JpaRepository<BhelVendorBbuData, Integer> {
	
	@Query(value = "Select new com.bhel.project.dtoImpl.ParentChildDto(bwds.ST_NO1 as parentId, bwds.ST_NO2 as childId, "
			+ "bwds.ST_NO3 as grandchildId, bwds.ST_NO4 as grandgrandchildId) from BhelVendorBbuData as bwds where bwds.ST_NO1"
			+ " in (:allRightParentList) and ST_NO2 is not null")
	public LinkedList<ParentChildDto> getAllRightChildsByParent(
			@Param("allRightParentList") List<String> allRightParentList);

	@Query("select count(bwds) from BhelVendorBbuData as bwds where bwds.ST_NO2 =:childId and bwds.ST_NO1 = :parentId and bwds.ST_NO3 is not null")
	public int getGrandChildCount(@Param("childId") String nxtChildId, @Param("parentId") String nxtParentid);

//	@Query("select distinct bwds.TYPE from BhelVendorBbuData as bwds where bwds.ST_NO2 = :childId and bwds.ST_NO1=:parentId")
//	public List<String> getType(@Param("parentId") String nxtParentid, @Param("childId") String nxtChildId);

	@Query("select distinct bwd from BhelVendorBbuData as bwd where bwd.ST_NO1 = :parentId and bwd.ST_NO2 is null and bwd.quentity != 0")
	public BhelVendorBbuData getWamParentDataByStNo(@Param("parentId") String nxtParentid);

	@Query("select distinct bwd from BhelVendorBbuData as bwd where bwd.ST_NO1 = :parentId and bwd.ST_NO2 = :childId and bwd.ST_NO3 is null order by bwd.ID")
	public BhelVendorBbuData getWamChildDataByStNo(@Param("childId") String nxtChildId,
			@Param("parentId") String nxtParentid);

	@Query("select distinct bwd from BhelVendorBbuData as bwd where bwd.ST_NO2 = :childId and bwd.ST_NO3 = :grandChildId and bwd.ST_NO4 is null order by bwd.ID")
	public BhelVendorBbuData getGrandChildDataByStNo(@Param("grandChildId") String nxtGrandChildId,
			@Param("childId") String nxtChildId);

}
