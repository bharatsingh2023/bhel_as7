package com.bhel.project.util;

import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class BhelProcedures {
	static final Logger logger = Logger.getLogger(BhelProcedures.class);

	@Autowired
	private DataSource dataSource;

	// procedure for VENDOR BBU
	public Map<String, Object> procedure_BHEL_INSERT_FRM_EXCEL_VEND_BBU(String vendBBuCv, String CPC,
			String customer_CONTACT_NO, String project_NAME2, String pkg_ID, String work_AREA, String vendorName,
			Date PERIOD_FROM, Date PERIOD_TO) {
		Map<String, Object> outMap = null;

		try {

			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
					.withProcedureName("BHEL_INSERT_FRM_EXCEL_VEND_BBU");
			logger.info(vendBBuCv);
			logger.info("pkg_ID :: " + customer_CONTACT_NO);
			logger.info("p_vendor_name :: " + vendorName);
			SqlParameterSource in = new MapSqlParameterSource().addValue("p_file_path", vendBBuCv)
					.addValue("p_project_code", CPC).addValue("p_ccn", customer_CONTACT_NO).addValue("p_pkg_id", pkg_ID)
					.addValue("P_WORK_AREA", work_AREA).addValue("p_vendor_name", vendorName)
					.addValue("p_period_from", PERIOD_FROM).addValue("P_PERIOD_TO", PERIOD_TO);

			outMap = jdbcCall.execute(in);
			outMap.put("exception", "NA");
			outMap.put("userType", "vendor");
			logger.info("vendor procedure :: " + outMap);

		} catch (Exception e) {
			outMap = new HashedMap<>();
			logger.error("error in procedure_BHEL_INSERT_FRM_EXCEL_VEND_BBU ", e);
			outMap.put("exception", e.getLocalizedMessage());
		}
		return outMap;
	}

	/// procedure for customer BBU
	public Map<String, Object> procedure_BHEL_INSERT_FRM_EXCEL_CUST_BBU(String cusBBuCsv, String cpc, String ccn,
			String project_name, Date bbucreateDate) {
		
		//System.out.println("checking..------>"+cpc+" "+project_name+" "+bbucreateDate);
		// String procedureOutputCustomerBbu = null;
		Map<String, Object> outMap = null;
		try {

			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
					.withProcedureName("BHEL_INSERT_FRM_EXCEL_CUST_BBU");
			logger.info(cusBBuCsv);
			SqlParameterSource in = new MapSqlParameterSource().addValue("p_file_path", cusBBuCsv)
					.addValue("p_cpc", cpc).addValue("p_ccn", ccn).addValue("p_project_name", project_name)
					.addValue("p_bbu_create", bbucreateDate).addValue("P_EC_UPDATE", null);

			outMap = jdbcCall.execute(in);
			outMap.put("exception", "NA");
			outMap.put("userType", "customer");
			logger.info("customer procedure :: " + outMap.keySet());

		} catch (Exception e) {
			outMap = new HashedMap<>();
			logger.error("error in procedure_BHEL_INSERT_FRM_EXCEL_CUST_BBU ", e);
			outMap.put("exception", e.getMessage());
		}
		return outMap;
	}

	/// procedure for customer EC
	public Map<String, Object> procedure_BHEL_INSERT_FROM_EXCEL_EC_DATA(String cusBBuEc, String cpc, String ccn,
			String project_name, Date ecUpdateDate) {
		// String procedureOutputCustomerBbu = null;
		Map<String, Object> outMap = null;
		try {

			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
					.withProcedureName("BHEL_INSERT_FROM_EXCEL_EC_DATA");
			logger.info(cusBBuEc);
			SqlParameterSource in = new MapSqlParameterSource().addValue("p_file_path", cusBBuEc)
					.addValue("P_PROJECT_CD", cpc.toString()).addValue("p_ccn", ccn)
					.addValue("P_PROJECT_NM", project_name.toString()).addValue("p_ec_update", ecUpdateDate);
			outMap = jdbcCall.execute(in);
			outMap.put("exception", "NA");
			outMap.put("userDataType", "EC");
			logger.info("customer procedure :: " + outMap.keySet());

		} catch (Exception e) {
			outMap = new HashedMap<>();
			logger.error("error in procedure_BHEL_INSERT_FRM_EXCEL_CUST_BBU ", e);
			outMap.put("exception", e.getMessage());
		}
		return outMap;
	}

	/// procedure for Vendor WAM

	public Map<String, Object> procedure_BHEL_INSERT_FROM_EXCEL(String vendWamCv, String project_NAME,
			String customer_CONTACT_NO, String project_NAME2, String pkg_ID, String work_AREA, Date PERIOD_TO) {
		Map<String, Object> outMap = null;
		try {

			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("BHEL_INSERT_FROM_EXCEL");
			logger.info(vendWamCv);
			SqlParameterSource in = new MapSqlParameterSource().addValue("p_file_path", vendWamCv)
					.addValue("P_PROJECT_CODE", project_NAME).addValue("p_ccn", customer_CONTACT_NO)
					.addValue("P_PACKAGE_NAME", pkg_ID).addValue("p_work_area", work_AREA)
					.addValue("p_period_to", PERIOD_TO);
			;
			outMap = jdbcCall.execute(in);
			outMap.put("exception", "NA");
			outMap.put("userDataType", "WAM");
			logger.info("vendor procedure :: " + outMap);

		} catch (Exception e) {
			outMap = new HashedMap<>();
			logger.error("error in procedure_BHEL_INSERT_FRM_EXCEL_VEND_BBU ", e);
			outMap.put("exception", e.getMessage());
		}
		return outMap;
	}

}
