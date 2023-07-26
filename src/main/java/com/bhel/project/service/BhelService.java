package com.bhel.project.service;

import java.util.List;
import java.util.Map;

import com.bhel.project.dtoImpl.ParentDto;
import com.bhel.project.dtoImpl.StatusDto;
import com.bhel.project.dtoImpl.ViewEditProject;
import com.bhel.project.entityImpl.CustomerVendorData;

public interface BhelService {
	public StatusDto insertConnections(List<Map<String, Map<String, String>>> mapList, ViewEditProject viewEditProject);

	public Map<String, List<ParentDto>> getBbuData(ViewEditProject viewEditProject);

	public List<Map<String, Map<String, String>>> getBbuToWamDataByCpcCcn(long l, long m);

	public Boolean savePackageWiseWorkArea(String projectCode, String projectPackage, String workArea,
			String vendorName, String vendorId);

	public List<String> getWorkAreaById(String projectCode);

	public int getBbuToWamDataCountByCpcCcn(String project_Code, String customer_CCN);

	public CustomerVendorData getProjectData(String project_Code);
}
