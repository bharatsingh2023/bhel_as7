package com.bhel.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bhel.project.dtoImpl.MasterAddPackage_Dto;
import com.bhel.project.dtoImpl.MasterAddSubPackageDto;
import com.bhel.project.dtoImpl.MasterAddWorkAreaDto;
import com.bhel.project.dtoImpl.MasterProjectDto;
import com.bhel.project.dtoImpl.StatusDto;
import com.bhel.project.entityImpl.BHEL_CUSTOMER_MASTER;
import com.bhel.project.entityImpl.BHEL_PACKAGES;
import com.bhel.project.entityImpl.BHEL_SUB_PACKAGES;
import com.bhel.project.entityImpl.BhelVendorMaster;
import com.bhel.project.entityImpl.BhelWorkArea;
import com.bhel.project.entityImpl.CPC_Master;
import com.bhel.project.entityImpl.ProjectVendorData;
import com.bhel.project.jpa.repo.BHEL_CUSTOMER_MASTER_REPO;
import com.bhel.project.jpa.repo.BHEL_PACKAGES_REPO;
import com.bhel.project.jpa.repo.BHEL_SUB_PACKAGES_REPO;
import com.bhel.project.jpa.repo.BHEL_WORK_AREA_REPO;
import com.bhel.project.jpa.repo.BhelProjectVendorRepo;
import com.bhel.project.jpa.repo.BhelVemdorMasterRepo;
import com.bhel.project.jpa.repo.CustomerRepository;

@Service
public class BhelMasterService {

	static final Logger logger = Logger.getLogger(BhelMasterService.class);
	@Autowired
	private BHEL_CUSTOMER_MASTER_REPO bhel_CUSTOMER_MASTER_REPO;
	@Autowired
	private BHEL_PACKAGES_REPO bHEL_PACKAGES_REPO;
	@Autowired
	private BHEL_SUB_PACKAGES_REPO bHEL_SUB_PACKAGES_REPO;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private BHEL_WORK_AREA_REPO bHEL_Work_AREA_REPO;
	@Autowired
	private BhelVemdorMasterRepo bhel_VENDOR_MASTER_REPO;
	@Autowired
	private BhelProjectVendorRepo bhelProjectVendorRepo;
	

	public BHEL_CUSTOMER_MASTER saveBHEL_CUSTOMER_MASTER(BHEL_CUSTOMER_MASTER bhel_CUSTOMER_MASTER) {
		try {
			return bhel_CUSTOMER_MASTER_REPO.save(bhel_CUSTOMER_MASTER);
		
		} catch (Exception e) {
			logger.error("error in BHEL_CUSTOMER_MASTER in BHEL_MASTER_SERVICE", e);
		}
		return null;
	}

	public void saveMasterAddproject(MasterProjectDto masterProjectInfo) {
		try {
			CPC_Master cpc_Master = new CPC_Master();
			cpc_Master.setCUSTOMER_PROJECT_CODE(masterProjectInfo.getCUSTOMER_PROJECT_CODE());
			cpc_Master.setCUSTOMER_PROJECT_NAME(masterProjectInfo.getCUSTOMER_PROJECT_NAME());
			logger.info(cpc_Master);
			customerRepository.save(cpc_Master);
		} catch (Exception e) {
			logger.error("error in saveMasterAddproject in BHEL_MASTER_SERVICE", e);
		}
	}

	public void saveMasterAddPackage(MasterAddPackage_Dto masterAddPackage_Dto) {
		try {
			BHEL_PACKAGES bhel_PACKAGES = new BHEL_PACKAGES();
			bhel_PACKAGES.setPACKAGE_NAME(masterAddPackage_Dto.getPACKAGE_NAME());
			bhel_PACKAGES.setVENDOR_NAME(masterAddPackage_Dto.getVendorName());
			bHEL_PACKAGES_REPO.save(bhel_PACKAGES);
		} catch (Exception e) {
			logger.error("error in saveMasterAddPackage in BHEL_MASTER_SERVICE", e);
		}
	}

	public void saveMasterSubPackage(MasterAddSubPackageDto masterAddSubPackageDto) {
		try {
			BHEL_SUB_PACKAGES bhel_SUB_PACKAGES = new BHEL_SUB_PACKAGES();
			bhel_SUB_PACKAGES.setBHEL_SUB_PACKAGE_NAME(masterAddSubPackageDto.getSUB_PACKAGE_NAME());
			bHEL_SUB_PACKAGES_REPO.save(bhel_SUB_PACKAGES);

		} catch (Exception e) {
			logger.error("error in saveMasterSubPackage in BHEL_MASTER_SERVICE", e);
		}
	}

	public void saveMasterAddWorkArea(MasterAddWorkAreaDto masterAddWorkAreaDto) {
		try {
			BhelWorkArea bhel_WORK_AREA = new BhelWorkArea();
			bhel_WORK_AREA.setWORK_AREA_NAME(masterAddWorkAreaDto.getWORK_AREA_NAME().trim());
			bHEL_Work_AREA_REPO.save(bhel_WORK_AREA);
		} catch (Exception e) {
			logger.error("error in saveMasterAddWorkArea in BHEL_MASTER_SERVICE", e);
		}
	}

	public List<MasterAddSubPackageDto> getSubPackages(String search, int pageNo, int pageSize) {
		List<MasterAddSubPackageDto> listSubPackages = new ArrayList<>();
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			List<BHEL_SUB_PACKAGES> content = bHEL_SUB_PACKAGES_REPO.search_SUB_PACKAGES(search, pageable).getContent();
			for (BHEL_SUB_PACKAGES e : content) {
				MasterAddSubPackageDto masterAddSubPackageDto = new MasterAddSubPackageDto();
				masterAddSubPackageDto.setSUB_PACKAGE_NAME(e.getBHEL_SUB_PACKAGE_NAME());
				listSubPackages.add(masterAddSubPackageDto);
			}
		} catch (Exception e) {
			logger.error("Error in pagenation of subPackages ", e);
		}

		return listSubPackages;
	}

	public List<MasterAddWorkAreaDto> getWorkArea(String search, int pageNo, int pageSize) {
		List<MasterAddWorkAreaDto> listWorkArea = new ArrayList<>();
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			List<BhelWorkArea> content = bHEL_Work_AREA_REPO.findAll();
			logger.info("content :: " + content.size());
			logger.info("search :: " + search);

			for (BhelWorkArea e : content) {
				MasterAddWorkAreaDto masterAddWorkAreaDto = new MasterAddWorkAreaDto();
				masterAddWorkAreaDto.setWORK_AREA_NAME(e.getWORK_AREA_NAME());
				listWorkArea.add(masterAddWorkAreaDto);
			}

		} catch (Exception e) {
			logger.error("Error in pagenation of getWorkArea ", e);
		}

		return listWorkArea;
	}

	public BhelVendorMaster saveBHEL_VENDOR_MASTER(BhelVendorMaster bhel_VENDOR_MASTER) {
		try {
			return bhel_VENDOR_MASTER_REPO.save(bhel_VENDOR_MASTER);
		} catch (Exception e) {
			logger.error("error in BHEL_VENDOR_MASTER in BHEL_MASTER_SERVICE", e);
		}
		return bhel_VENDOR_MASTER;
	}

	public List<ProjectVendorData> getVendorName(String project_code) {
		//List<BhelVendorMaster> vendorList = null;

		List<ProjectVendorData> vendorList=null;
		try {
			//vendorList = bhel_VENDOR_MASTER_REPO.findAll();
			vendorList=bhelProjectVendorRepo.findAll();

			//vendorList=bhelProjectVendorRepo.findAllWithCpc(project_code);
			
		} catch (Exception e) {
			logger.error("error in getVendorName in BHEL_MASTER_SERVICE", e);
		}

		return vendorList;
	}

	public StatusDto saveAddPackageInView(MasterAddPackage_Dto masterAddPackage_Dto, String projectCode) {

		BHEL_PACKAGES packages = null;
		StatusDto packageSaveStatus = new StatusDto();
		ProjectVendorData savedPkg = null;
		int pkgCount = 0;

		try {
			if (masterAddPackage_Dto.getPACKAGE_NAME().length() != 0) {
				// checking for duplicate packages
				pkgCount = bhelProjectVendorRepo.getPackageCount(masterAddPackage_Dto.getPACKAGE_NAME(),
						projectCode);
				System.out.println("count is---->"+pkgCount);
				if (pkgCount == 0) {
					BHEL_PACKAGES bhel_PACKAGES = new BHEL_PACKAGES();
					bhel_PACKAGES.setPACKAGE_NAME(masterAddPackage_Dto.getPACKAGE_NAME());
					bhel_PACKAGES.setVENDOR_NAME(masterAddPackage_Dto.getVendorName());
					packages = bHEL_PACKAGES_REPO.save(bhel_PACKAGES);

					ProjectVendorData obj = new ProjectVendorData(projectCode, masterAddPackage_Dto.getPACKAGE_NAME(),
							masterAddPackage_Dto.getVendorName());

					savedPkg = bhelProjectVendorRepo.save(obj);
					if(packages != null && savedPkg!= null) {
						packageSaveStatus.setPkgCount(pkgCount);
						packageSaveStatus.setSaveStatus(true);
					}else {
						packageSaveStatus.setPkgCount(pkgCount);
						packageSaveStatus.setSaveStatus(false);
					}
				} else {
					packageSaveStatus.setPkgCount(pkgCount);
					packageSaveStatus.setSaveStatus(false);
				}
			}

		} catch (Exception e) {
			logger.error("error in saveAddPackageInView ", e);
			packageSaveStatus.setPkgCount(pkgCount);
			packageSaveStatus.setExp(e);
			packageSaveStatus.setSaveStatus(false);
		}
		return packageSaveStatus;

	}

}
