package com.bhel.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bhel.project.dtoImpl.AddProjectCcnDto;
import com.bhel.project.dtoImpl.CPC_MasterDto;
import com.bhel.project.entityImpl.BHEL_PACKAGES;
import com.bhel.project.entityImpl.BhelWorkArea;
import com.bhel.project.entityImpl.CustomerVendorData;
import com.bhel.project.entityImpl.ProjectVendorData;
import com.bhel.project.jpa.repo.BhelProjectVendorRepo;
import com.bhel.project.jpa.repo.Bhel_Customer_Vendor_MasterRepo;

@Service
public class ViewEditPageService {
	static final Logger logger = Logger.getLogger(ViewEditPageService.class);

	@Autowired
	private Bhel_Customer_Vendor_MasterRepo BhelCustomerVendorMasterRepo;
	@Autowired
	private BhelProjectVendorRepo bhelProjectVendorRepo;

	public AddProjectCcnDto getCcnOfCpc(String search) {
		AddProjectCcnDto dto = null;
		try {
			CustomerVendorData ccn = BhelCustomerVendorMasterRepo.getCcn(search);
			dto = new AddProjectCcnDto();
			dto.setCUSTOMER_CONTACT_NO(ccn.getcCn());
			dto.setCUSTOMET_NAME(ccn.getCustomer_name());
		} catch (Exception e) {
			logger.error("error in getCcnOfCpc", e);
		}
		return dto;
	}

	public List<CPC_MasterDto> getCpc(String search, int pageNo, int pageSize) {

		List<CPC_MasterDto> cpc = new ArrayList<>();
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			List<CustomerVendorData> content = BhelCustomerVendorMasterRepo.searchCPC(search, pageable).getContent();
			for (CustomerVendorData e : content) {
				CPC_MasterDto cpc_MasterDto = new CPC_MasterDto();
				cpc_MasterDto.setCUSTOMER_PROJECT_CODE(e.getProjectCode());
				cpc_MasterDto.setCUSTOMER_PROJECT_NAME(e.getProjectName());
				cpc.add(cpc_MasterDto);

			}

			System.out.println(cpc);
		} catch (Exception e) {

			logger.error("error in getCpc ", e);
		}

		return cpc;
	}

	public List<BHEL_PACKAGES> GetAllPackages(String search) {

		List<BHEL_PACKAGES> listPackages = new ArrayList<>();
		try {

			List<ProjectVendorData> getAllPackages = bhelProjectVendorRepo.GetAllPackages(search); // BY PROJECT CODE
			System.out.println("----------->"+getAllPackages);
			for (ProjectVendorData e : getAllPackages) {
				BHEL_PACKAGES bhelPackages = new BHEL_PACKAGES();
				bhelPackages.setPACKAGE_NAME(e.getpKg());
				if (e.getVendorName() != null) {
					bhelPackages.setVENDOR_NAME(e.getVendorName());
					//bhelPackages.setPKG_ID(Integer.parseInt(e.getVendorId()));
				}
				listPackages.add(bhelPackages);
			}

		} catch (Exception e) {
			logger.error("error in GetAllPackages", e);
		}
		return listPackages;
	}

	public List<BhelWorkArea> GetAllWorkArea(String search) {

		List<BhelWorkArea> listWorkArea = new ArrayList<>();
		try {

			List<ProjectVendorData> workArea = bhelProjectVendorRepo.GetAllWorkArea(search);

			for (ProjectVendorData e : workArea) {
				BhelWorkArea bhelWorkArea = new BhelWorkArea();
				bhelWorkArea.setWORK_AREA_NAME(e.getWorkArea());
				listWorkArea.add(bhelWorkArea);
			}
		} catch (Exception e) {
			logger.error("error in GetAllWorkArea", e);
		}
		return listWorkArea;
	}

}
