//package com.bhel.project.jpa.repo;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.bhel.project.entityImpl.CCN_Master;
//
//public interface CCNRepository extends JpaRepository<CCN_Master, String>{
//	@Query(value = "SELECT CCN,CPC,PROJECTIDENTIFIER,PI_DESCRIPTION FROM BHEL_CUST_CONTRACT_NO  WHERE CPC=:cpc", nativeQuery=true)
//	public List<CCN_Master> getCCN(@Param("cpc") String cpc);
//
//	   
//	   @Query(value = " select distinct bcm.customer_project_name,bcc.CPC,BCC.CCN,BCC.PROJECTIDENTIFIER,bcc.PI_DESCRIPTION from "
//	   		+ "bhel_cpc_master bcm INNER JOIN bhel_cust_contract_no bcc ON bcm.customer_project_code = bcc.cpc and  "
//	   		+ "bcc.cpc = :cpcx and bcc.ccn = :ccnx", nativeQuery=true)
//	   public List<Object> getproject(@Param("cpcx") String cpcx, @Param("ccnx") String ccnx);
//	   
//	   
//}
