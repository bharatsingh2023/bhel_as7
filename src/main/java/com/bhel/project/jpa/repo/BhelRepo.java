package com.bhel.project.jpa.repo;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bhel.project.dtoImpl.ParentChildDto;
import com.bhel.project.entityImpl.BbuToWam;
import com.bhel.project.entityImpl.BhelBbuData;
import com.bhel.project.entityImpl.CustomerVendorData;

public interface BhelRepo extends JpaRepository<BbuToWam, Integer> {

	@Query("select bbm.ST_NO_1 from BhelBbuData as bbm where bbm.ST_NO_2 IS NULL and bbm.CPC =:cpc  ORDER BY (bbm.BBU_ID)")
	public List<String> getAllStNoWithOutDot(@Param("cpc") String cpc);

	@Query(value = "Select new com.bhel.project.dtoImpl.ParentChildDto(bbm.ST_NO_1 as parentId, bbm.ST_NO_2 as childId, "
			+ "bbm.ST_NO_3 as grandchildId, bbm.ST_NO_4 as grandgrandchildId) from BhelBbuData as bbm where bbm.ST_NO_1 in (:stNo) and ST_NO_2 is not null and bbm.CPC =:cpc")

	public LinkedList<ParentChildDto> getAllStNoWithDotByParent(@Param("stNo") List<String> allStnoWithOutDot,
			@Param("cpc") String cpc);

	@Query("select distinct bbm from BhelBbuData as bbm where bbm.ST_NO_3 = :grandChild and bbm.ST_NO_2 = :childId and ST_NO_4 is null and  bbm.CPC =:cpc ")
	public BhelBbuData getOtherDataByStNo(@Param("grandChild") String grandChild, @Param("childId") String childId,
			@Param("cpc") String cpc);

	@Query("select bbm from BhelBbuData as bbm where bbm.ST_NO_4 = :grandgrandchild and bbm.ST_NO_3 = :grandchild")
	public BhelBbuData getGreatGrandChildrenDataByStNo(@Param("grandgrandchild") String grandgrandchild,
			@Param("grandchild") String grandchild);

	@Query("select bbm from BhelBbuData as bbm where bbm.ST_NO_1 = :parentId and bbm.ST_NO_2 is null")
	public BhelBbuData getParentDataByStNo(@Param("parentId") String parentId);

	@Query("select bbm from BhelBbuData as bbm where bbm.ST_NO_2 =:childId and bbm.ST_NO_1 = :parentId and ST_NO_3 is null")
	public BhelBbuData getChildDataByStNo(@Param("childId") String child, @Param("parentId") String parent);

	@Query("select bbm from BhelBbuData as bbm where bbm.ST_NO_3 =:grandchildId and bbm.ST_NO_2 = :childId and ST_NO_4 is null")
	public BhelBbuData getGrandChildDataByStNo(@Param("grandchildId") String nxtGrandChildId,
			@Param("childId") String nxtChildId);

	@Query("select count(bbm) from BhelBbuData as bbm where bbm.ST_NO_3 =:grandchildId and bbm.ST_NO_2 = :nxtChildId and ST_NO_4 is not null")
	public int getGreatGrandcChildCount(@Param("grandchildId") String nxtGrandChildId,
			@Param("nxtChildId") String nxtChildId);

	@Query("select count(bbm) from BhelBbuData as bbm where bbm.ST_NO_2 =:childId and bbm.ST_NO_1 = :parentId and ST_NO_3 is not null")
	public int getGrandChildCount(@Param("childId") String nxtChildId, @Param("parentId") String nxtParentid);

	@Query("select distinct btw from BbuToWam as btw where btw.cpc = :cpc and btw.ccn = :ccn")
	public List<BbuToWam> getDataByCpcAndCcn(@Param("cpc") long cpc, @Param("ccn") long ccn);

	@Query("select count(btw) from BbuToWam as btw where btw.cpc = :cpc and btw.ccn = :ccn")
	public int isDataExists(@Param("cpc") long cpc, @Param("ccn") long ccn);

	@Transactional
	@Modifying
	@Query("delete from BbuToWam as btw where btw.cpc = :cpc and btw.ccn = :ccn")
	public void deletePrevData(@Param("cpc") long cpc, @Param("ccn") long ccn);

	/*
	 * @Query("select count(btw) from BbuToWam as btw where btw.cpc = :cpc and btw.ccn = :ccn"
	 * ) public int getDataCountByCpcAndCcn(@Param("cpc") long
	 * project_Code, @Param("ccn") long customer_CCN);
	 */

	 @Query("select count(btw) from BbuToWam as btw where btw.cpc = :cpc"
			 ) public int getDataCountByCpcAndCcn(@Param("cpc") long
			  project_Code);
	@Query("select cvd from CustomerVendorData as cvd where cvd.projectCode = :cpc")
	public CustomerVendorData getByProjectCode(@Param("cpc") String project_Code);

}
