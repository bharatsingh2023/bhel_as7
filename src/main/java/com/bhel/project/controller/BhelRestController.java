package com.bhel.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhel.project.dtoImpl.BBUWAMDto;
import com.bhel.project.dtoImpl.FetchBbuToWamDto;
import com.bhel.project.dtoImpl.MasterAddPackage_Dto;
import com.bhel.project.dtoImpl.MasterAddWorkAreaDto;
import com.bhel.project.dtoImpl.ParentDto;
import com.bhel.project.dtoImpl.StatusDto;
import com.bhel.project.dtoImpl.ViewEditProject;
import com.bhel.project.entityImpl.BHEL_PACKAGES;
import com.bhel.project.entityImpl.BhelVendorMaster;
import com.bhel.project.entityImpl.CustomerVendorData;
import com.bhel.project.entityImpl.ProjectVendorData;
import com.bhel.project.service.BhelService;
import com.bhel.project.service.impl.BhelLoginService;
import com.bhel.project.service.impl.BhelMasterService;
import com.bhel.project.service.impl.CustomerService;
import com.bhel.project.service.impl.EmailService;
import com.bhel.project.service.impl.FileUploadService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class BhelRestController {

	static final Logger logger = Logger.getLogger(BhelRestController.class);

	@Autowired
	BhelService bhelService;
	@Autowired
	private BhelMasterService bhelMasterService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private FileUploadService fileUploadService;

	// @Autowired

	@RequestMapping(value = "/getBbuData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, List<ParentDto>> workPage(@ModelAttribute ViewEditProject viewEditProject) {
		System.out.println("View Edit "+viewEditProject);
		Map<String, List<ParentDto>> bbuDataMap = null;
		try {
			if (viewEditProject != null) {
				//logger.info("bbuData workspace :: " + viewEditProject.getCustomer_CCN());
				logger.info("bbuData workspace :: " + viewEditProject.getProject_Code());

				bbuDataMap = bhelService.getBbuData(viewEditProject);

				ObjectMapper mapper = new ObjectMapper();
				// Converting the Object to JSONString
				String jsonString = mapper.writeValueAsString(bbuDataMap);
				// logger.info("bbuData :: " + bbuDataMap);
				logger.info("bbuData jsonString :: " + jsonString);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bbuDataMap;
	}

	@PostMapping("/savework")
	public StatusDto insertConnection(@ModelAttribute ViewEditProject viewEditProject) {
		ObjectMapper mapper = new ObjectMapper();

		StatusDto insertConnectionStatus = null;
		logger.info("pathMaps :: " + viewEditProject.getPathMapList().getClass());
		try {

			String insertMapList = viewEditProject.getPathMapList();
			logger.info("pathMaps :: " + insertMapList);

			List<Map<String, Map<String, String>>> mapList = mapper.readValue(insertMapList,
					new TypeReference<List<Map<String, Map<String, String>>>>() {
					});

			logger.info("mapList :: " + mapList);
			insertConnectionStatus = bhelService.insertConnections(mapList, viewEditProject);

		} catch (Exception e) {
			logger.error("error in savework api", e);
		}
		return insertConnectionStatus;
	}

	@PostMapping("/getwork")
	public StatusDto getDataByCpcAndCcn(@ModelAttribute ViewEditProject viewEditProject) {
		StatusDto getDataByCpcAndCcnStatus = new StatusDto();
		logger.info("Cpc ::: " + viewEditProject.getProject_Code());
		logger.info("ccn ::: " + viewEditProject.getCustomer_CCN());
		List<Map<String, Map<String, String>>> bbuToWamData = null;
		getDataByCpcAndCcnStatus.setReadStatus(false);
		getDataByCpcAndCcnStatus.setSaveMessage("unabale to fetch saved works");
		if (viewEditProject.getProject_Code() != null && viewEditProject.getCustomer_CCN() != null) {
			bbuToWamData = bhelService.getBbuToWamDataByCpcCcn(Long.parseLong(viewEditProject.getProject_Code()),
					Long.parseLong(viewEditProject.getCustomer_CCN()));
			if (bbuToWamData != null) {
				getDataByCpcAndCcnStatus.setReadStatus(true);
				getDataByCpcAndCcnStatus.setSaveMessage("successfully fetch saved works");
				getDataByCpcAndCcnStatus.setBbuWamMapingData(bbuToWamData);
				getDataByCpcAndCcnStatus.setParentMap(workPage(viewEditProject));
			}
			logger.info("getDataByCpcAndCcnStatus ::: " + getDataByCpcAndCcnStatus);
		}
		return getDataByCpcAndCcnStatus;
	}

	@PostMapping("/getpackage")
	public List<ProjectVendorData> getProjectPackages(@RequestParam("project_code") String project_code) {
		System.out.println("PROJECTCODE :: "+project_code);
		List<ProjectVendorData> pkgData = customerService.getProjectWisePackage(project_code);
		return pkgData;
	}

	@PostMapping("/getpackageByName")
	public BHEL_PACKAGES getPackagesByName(@RequestParam String pkgName) {
		BHEL_PACKAGES pkgData = customerService.getNameWisePackage(pkgName);
		return pkgData;
	}

	@GetMapping("/workArea")
	public List<MasterAddWorkAreaDto> addWorkArea(@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "page", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize) {
		List<MasterAddWorkAreaDto> listWorkArea = new ArrayList<>();
		try {
			if (search != null) {
				listWorkArea = bhelMasterService.getWorkArea(search.toUpperCase(), pageNo, pageSize);
			} else {
				search = "bb";
				listWorkArea = bhelMasterService.getWorkArea(search, pageNo, pageSize);
			}
		} catch (Exception e) {
			logger.error("error in addWorkArea", e);
		}
		return listWorkArea;
	}

	@GetMapping("/workAreaByProject")
	public List<String> addWorkAreaByProject(@RequestParam(value = "projectCode") String projectCode) {
		List<String> listWorkArea = new ArrayList<>();
		try {
			if (projectCode != null) {
				listWorkArea = bhelService.getWorkAreaById(projectCode);
			}
		} catch (Exception e) {
			logger.error("error in addWorkArea", e);
		}
		return listWorkArea;
	}

	@PostMapping("/saveAddPackage")
	// @ResponseBody
	public StatusDto saveAddPackageForView(@RequestParam("projectCode") String projectCode,
			@RequestParam("PACKAGE_NAME") String pACKAGE_NAME, @RequestParam("VendorName") String vendorName) {

		StatusDto packageInView = null;
		try {
			MasterAddPackage_Dto masterAddPackage_Dto = new MasterAddPackage_Dto();
			masterAddPackage_Dto.setPACKAGE_NAME(pACKAGE_NAME);
			masterAddPackage_Dto.setVendorName(vendorName);
			System.out.println(masterAddPackage_Dto);
			packageInView = bhelMasterService.saveAddPackageInView(masterAddPackage_Dto, projectCode);
			return packageInView;
		} catch (Exception e) {
			packageInView = new StatusDto();
			packageInView.setExp(e);
			logger.error("error while saveAddPackageForView", e);
			return packageInView;
		}

	}

	@PostMapping("/newWorkArea")
	public StatusDto createNewPackageWiseWorkArea(@RequestParam("projectCode") String projectCode,
			@RequestParam("vendorName") String vendorName, @RequestParam("projectPackage") String projectPackage,
			@RequestParam("projectWorkArea") String workArea, @RequestParam("vendorId") String vendorId) {
		StatusDto insertWorkAreaDto = new StatusDto();
		insertWorkAreaDto.setSaveStatus(false);
		insertWorkAreaDto.setSaveMessage("Unable to save work area for package " + projectPackage);
		boolean save = false;
		try {
			logger.info("workArea names :: " + workArea);
			logger.info("project Package names :: " + projectPackage);
			logger.info("project Code names :: " + projectCode);
			logger.info("Vendor names :: " + vendorName);
			save = bhelService.savePackageWiseWorkArea(projectCode, projectPackage, workArea, vendorName,vendorId);
			if (save) {
				insertWorkAreaDto.setSaveStatus(true);
				insertWorkAreaDto.setSaveMessage("New work areas are added for package : " + projectPackage);
			}
		} catch (Exception e) {
			logger.error("createNewPackageWiseWorkArea error", e);
			insertWorkAreaDto.setSaveMessage("Unable to save work area for package " + projectPackage);
		}

		return insertWorkAreaDto;
	}

	@PostMapping("/getWorkSpaceAndPackage")
	public Map<String, Object> getWorkSpaceAndPackage(@RequestParam("projectCode") String project_code, Model model) {
		Map<String, Object> packageWorkAreaMap = new HashMap<>();

		try {
			packageWorkAreaMap.put("workArea", addWorkAreaByProject(project_code));
			packageWorkAreaMap.put("packages", getProjectPackages(project_code));
			packageWorkAreaMap.put("vendors", getVendorName(project_code));
		} catch (Exception e) {
			logger.error("getWorkSpaceAndPackage error :: ", e);
		}
		return packageWorkAreaMap;
	}

	@PostMapping(value = "/saveCustomerBbu", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public StatusDto saveCustomerBbu(@ModelAttribute BBUWAMDto bBUWAMDto) {
		logger.info("Executing saveCustomerBbu..." + bBUWAMDto);
		Map<String, Object> customerProcedureSuccess = null;
		StatusDto saveCustomerBbuData = new StatusDto();
		saveCustomerBbuData.setSaveStatus(false);
		saveCustomerBbuData.setSaveMessage("Data can not be saved");
		if (!bBUWAMDto.getCustomerBBU().isEmpty()) {
			try {
				logger.info("Executing..." + bBUWAMDto.getPKG_ID());
				customerProcedureSuccess = fileUploadService.saveAddProject(bBUWAMDto);
				if (!customerProcedureSuccess.isEmpty() && (boolean) customerProcedureSuccess.get("procedureSuccess")) {
					saveCustomerBbuData.setSaveStatus((boolean) customerProcedureSuccess.get("procedureSuccess"));
					saveCustomerBbuData.setSaveMessage(
							"Customer BBU has been uploaded successfully for project :" + bBUWAMDto.getPROJECT_NAME());
					logger.info("Customer BBU Saved Successfully...");
				} else if (!customerProcedureSuccess.isEmpty()
						&& !(boolean) customerProcedureSuccess.get("procedureSuccess")) {

					if (customerProcedureSuccess.get("p_msg") != null) {
						saveCustomerBbuData.setSaveMessage("For project <b>" + bBUWAMDto.getPROJECT_NAME()
								+ "</b> customer BBU can not be uploaded. Because "
								+ customerProcedureSuccess.get("p_msg"));
					} else {
						saveCustomerBbuData.setSaveMessage("For project <b>" + bBUWAMDto.getPROJECT_NAME()
								+ "</b> customer BBU can not be uploaded. Because "
								+ customerProcedureSuccess.get("exception"));
					}

				}

			} catch (Exception e) {
				logger.error("error while saving ..", e);
				saveCustomerBbuData.setSaveMessage("Some exception occured");
				return saveCustomerBbuData;
			}
		}
		return saveCustomerBbuData;
	}

	@PostMapping(value = "/saveCustomerEc", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public StatusDto saveCustomerEc(@ModelAttribute BBUWAMDto bBUWAMDto) {
		logger.info("Executing saveCustomerBbu..." + bBUWAMDto);
		Map<String, Object> customerProcedureSuccess = null;
		boolean dataProcedureStatus = false;
		StatusDto saveCustomerBbuData = new StatusDto();
		saveCustomerBbuData.setSaveStatus(false);
		saveCustomerBbuData.setSaveMessage("Data can not be saved");
		if (!bBUWAMDto.getCustomerEc().isEmpty()) {
			try {
				logger.info("Executing..." + bBUWAMDto);
				customerProcedureSuccess = fileUploadService.addDataToProject(bBUWAMDto);
				dataProcedureStatus = (boolean) customerProcedureSuccess.get("dataProcedureSuccess");
				logger.info("data Procedure Success..." + customerProcedureSuccess.get("dataProcedureSuccess"));

				if (customerProcedureSuccess != null && dataProcedureStatus) {
					saveCustomerBbuData.setSaveStatus(dataProcedureStatus);
					saveCustomerBbuData.setSaveMessage("Customer EC data has been uploaded successfully for project :"
							+ bBUWAMDto.getPROJECT_NAME());
					logger.info("Customer BBU Saved Successfully...");
				} else if (customerProcedureSuccess != null && !dataProcedureStatus
						&& customerProcedureSuccess.get("p_msg") != null) {

					saveCustomerBbuData.setSaveStatus((boolean) customerProcedureSuccess.get("p_out"));
					saveCustomerBbuData.setSaveMessage(
							"Customer EC data can not be uploaded for project :" + bBUWAMDto.getPROJECT_NAME()
									+ ". The following exception has occured " + customerProcedureSuccess.get("p_msg"));

				} else {
					saveCustomerBbuData.setSaveStatus(dataProcedureStatus);
					saveCustomerBbuData.setSaveMessage("Customer EC data can not be uploaded for project :"
							+ bBUWAMDto.getPROJECT_NAME() + ". The following exception has occured "
							+ customerProcedureSuccess.get("exception"));
				}

			} catch (Exception e) {
				logger.error("error while saving ..", e);
				saveCustomerBbuData.setSaveMessage("Some exception occured");
				return saveCustomerBbuData;
			}
		}
		return saveCustomerBbuData;
	}

	@PostMapping("/saveVendorBBu")
	public StatusDto saveVendorBBu(@ModelAttribute BBUWAMDto bBUWAMDto) {
		System.out.println("bb --> "+bBUWAMDto);

		Map<String, Object> vendorProcedureSuccess = null;
		StatusDto saveBbuData = new StatusDto();

		saveBbuData.setSaveStatus(false);
		saveBbuData.setSaveMessage("Data can not be saved");

		if (!bBUWAMDto.getVendorBBU().isEmpty()) {

			try {
				logger.info("Executing saveVendorBBu..." + bBUWAMDto.getPKG_ID());
				CustomerVendorData getByProjectCode = customerService.getCcnByProjectCode(bBUWAMDto.getCPC());
				bBUWAMDto.setCUSTOMER_CONTACT_NO(getByProjectCode.getcCn());

				vendorProcedureSuccess = fileUploadService.saveAddProject(bBUWAMDto);

				if (!vendorProcedureSuccess.isEmpty() && (boolean) vendorProcedureSuccess.get("procedureSuccess")) {
					saveBbuData.setSaveStatus((boolean) vendorProcedureSuccess.get("procedureSuccess"));
					saveBbuData.setSaveMessage(
							"Vendor BBU has been uploaded successfully for project :" + bBUWAMDto.getCPC());
					logger.info("Customer BBU Saved Successfully...");
				} else if (!vendorProcedureSuccess.isEmpty()
						&& !(boolean) vendorProcedureSuccess.get("procedureSuccess")
						&& vendorProcedureSuccess.get("p_msg") != null) {
					saveBbuData.setSaveMessage("For project <b>" + bBUWAMDto.getCPC()
							+ "</b> vendor BBU can not be uploaded. Because " + vendorProcedureSuccess.get("p_msg"));

				} else {
					saveBbuData.setSaveMessage("For project <b>" + bBUWAMDto.getPROJECT_NAME()
							+ "</b> vendor BBU can not be uploaded. Because follwoing error occured : "
							+ vendorProcedureSuccess.get("exception"));
				}

			} catch (Exception e) {
				logger.error("error while saving ..", e);
				saveBbuData.setSaveMessage("Some exception occured : " + e.getMessage());
				return saveBbuData;
			}

		} else {
			logger.error("empty files");
			saveBbuData.setSaveMessage("file(s) not found");
			return saveBbuData;
		}

		return saveBbuData;

	}

	@PostMapping("/saveVendorWam")
	public StatusDto saveVendorWam(@ModelAttribute BBUWAMDto bBUWAMDto) {
		System.out.println("<-- get bbuWAMDto -->"+bBUWAMDto);
		Map<String, Object> vendorProcedureSuccess = null;
		StatusDto saveVendorData = new StatusDto();
		boolean vendorDataProcedureStatus = false;
		saveVendorData.setSaveStatus(false);
		saveVendorData.setSaveMessage("Data can not be saved");

		if (!bBUWAMDto.getVendorWam().isEmpty()) {

			try {
				logger.info("Executing saveVendorBBu..." + bBUWAMDto.getPKG_ID());
				vendorProcedureSuccess = fileUploadService.addDataToProject(bBUWAMDto);
				vendorDataProcedureStatus = (boolean) vendorProcedureSuccess.get("dataProcedureSuccess");

				if (vendorProcedureSuccess != null && vendorDataProcedureStatus) {
					saveVendorData.setSaveStatus(true);
					saveVendorData.setSaveMessage(
							"Vendor WAM data has been uploaded successfully for project :" + bBUWAMDto.getCPC());
					logger.info("vendor bbu data Saved Successfully...");

				} else if (vendorProcedureSuccess != null && !vendorDataProcedureStatus
						&& vendorProcedureSuccess.get("p_msg") != null) {

					saveVendorData.setSaveStatus((boolean) vendorProcedureSuccess.get("p_out"));
					saveVendorData
							.setSaveMessage("Customer EC data can not be uploaded for project :" + bBUWAMDto.getCPC()
									+ ". The following exception has occured " + vendorProcedureSuccess.get("p_msg"));

				} else {
					saveVendorData.setSaveStatus(vendorDataProcedureStatus);
					saveVendorData.setSaveMessage("Customer EC data can not be uploaded for project :"
							+ bBUWAMDto.getCPC() + ". The following exception has occured "
							+ vendorProcedureSuccess.get("exception"));
				}

			} catch (Exception e) {
				logger.error("error while saving ..", e);
				saveVendorData.setSaveMessage("Some exception occured");
				return saveVendorData;
			}

		} else {
			logger.error("empty files");
			saveVendorData.setSaveMessage("file(s) not found");
			return saveVendorData;
		}

		return saveVendorData;

	}

	@GetMapping("/getAllVendor")
	public List<ProjectVendorData> getVendorName(String project_code) {

		return bhelMasterService.getVendorName(project_code);
	}

}
