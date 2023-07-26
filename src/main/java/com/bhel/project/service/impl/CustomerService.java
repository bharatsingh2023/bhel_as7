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
import com.bhel.project.entityImpl.BHEL_CUSTOMER_MASTER;
import com.bhel.project.entityImpl.BHEL_PACKAGES;
import com.bhel.project.entityImpl.CPC_Master;
import com.bhel.project.entityImpl.CustomerVendorData;
import com.bhel.project.entityImpl.ProjectVendorData;
import com.bhel.project.jpa.repo.BHEL_CUSTOMER_MASTER_REPO;
import com.bhel.project.jpa.repo.CustomerRepository;
import com.bhel.project.jpa.repo.ProjectVendorRepository;
import com.bhel.project.repo.CustomerVendorRepository;

@Service
public class CustomerService {
	static final Logger logger = Logger.getLogger(CustomerService.class);
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerVendorRepository customerVendorRepository;
	@Autowired
	private ProjectVendorRepository projectVendorRepository;
//	@Autowired
//	private CCNRepository ccnRepository;
	@Autowired
	private BHEL_CUSTOMER_MASTER_REPO bhelCustomerMasterRepo;

	public List<CPC_MasterDto> getCPC_Master(final String search, final int pageNo, final int pageSize) {

		List<CPC_MasterDto> listCpc_MasterDto = new ArrayList<>();
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			List<CPC_Master> content = customerRepository.searchCPC(search, pageable).getContent();
			for (CPC_Master e : content) {
				CPC_MasterDto cpc_MasterDto = new CPC_MasterDto();
				cpc_MasterDto.setCUSTOMER_PROJECT_CODE(e.getCUSTOMER_PROJECT_CODE());
				cpc_MasterDto.setCUSTOMER_PROJECT_NAME(e.getCUSTOMER_PROJECT_NAME());
				listCpc_MasterDto.add(cpc_MasterDto);
			}
		} catch (Exception e) {
			logger.error("Error in pagenation " + e);
		}
		return listCpc_MasterDto;
	}

//	public List<String> getccn(String cpc) {
//		List<String> collect = null;
//		try {
//			List<CCN_Master> ccnList = ccnRepository.getCCN(cpc);
//			collect = ccnList.stream().map(e -> e.getCCN()).collect(Collectors.toList());
//		} catch (Exception e) {
//			logger.error("error in getccn", e);
//		}
//		return collect;
//	}

	public List<AddProjectCcnDto> getccn(String search, int pageNo, int pageSize) {
		List<AddProjectCcnDto> listCcn = new ArrayList<>();
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			List<BHEL_CUSTOMER_MASTER> content = bhelCustomerMasterRepo.getCCN(search, pageable).getContent();
			for (BHEL_CUSTOMER_MASTER e : content) {
				AddProjectCcnDto CcnDto = new AddProjectCcnDto();
				CcnDto.setCUSTOMER_CONTACT_NO(e.getCpc());
				CcnDto.setCUSTOMET_NAME(e.getCUSTOMET_NAME());
				listCcn.add(CcnDto);
			}
		} catch (Exception e) {
			logger.error("Error in pagenation " + e);
		}
		return listCcn;
	}

	/*
	 * public List<Object> getproject(String cpc, String ccn) {
	 * 
	 * List<Object> cpcCcn = new ArrayList<>();
	 * 
	 * try { cpcCcn = ccnRepository.getproject(cpc, ccn); } catch (Exception e) {
	 * logger.error("error", e); } return cpcCcn; }
	 */

	public List<CustomerVendorData> getcustomerVendorData() {
		List<CustomerVendorData> customerVndorData = null;
		try {
			customerVndorData = customerVendorRepository.findAll();
		} catch (Exception e) {
			logger.error("getcustomerVendorData error", e);
		}
		return customerVndorData;
	}

	public List<ProjectVendorData> getProjectWisePackage(String project_code) {
		try {
			return projectVendorRepository.findAllByProject_code(project_code);
		} catch (Exception e) {
			logger.error("getProjectWisePackage error", e);
		}
		return null;
	}

	public BHEL_PACKAGES getNameWisePackage(String pkgName) {
		try {
			logger.info("pkgName :: " + pkgName);
			return projectVendorRepository.getPackageByPkgName(pkgName);
		} catch (Exception e) {
			logger.error("getNameWisePackage error", e);
		}
		return null;
	}

	public CustomerVendorData getCcnByProjectCode(String project_NAME) {
		try {
			logger.info("pkgName :: " + project_NAME);
			return projectVendorRepository.getCcn(project_NAME);
		} catch (Exception e) {
			logger.error("getNameWisePackage error", e);
		}
		return null;
	}

}