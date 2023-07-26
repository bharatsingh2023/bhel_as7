package com.bhel.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhel.project.dtoImpl.AddProjectCcnDto;
import com.bhel.project.dtoImpl.Bhel_Customer_Vendor_MasterDto;
import com.bhel.project.dtoImpl.CPC_MasterDto;
import com.bhel.project.dtoImpl.MasterAddPackage_Dto;
import com.bhel.project.dtoImpl.MasterAddSubPackageDto;
import com.bhel.project.dtoImpl.MasterAddWorkAreaDto;
import com.bhel.project.dtoImpl.ViewEditProject;
import com.bhel.project.entityImpl.BHEL_CUSTOMER_MASTER;
import com.bhel.project.entityImpl.BHEL_PACKAGES;
import com.bhel.project.entityImpl.BhelLogin;
import com.bhel.project.entityImpl.BhelVendorMaster;
import com.bhel.project.entityImpl.BhelWorkArea;
import com.bhel.project.entityImpl.CustomerVendorData;
import com.bhel.project.jpa.repo.BHEL_CUSTOMER_MASTER_REPO;
import com.bhel.project.jpa.repo.BHEL_PACKAGES_REPO;
import com.bhel.project.service.BhelService;
import com.bhel.project.service.impl.BhelLoginService;
import com.bhel.project.service.impl.BhelMasterService;
import com.bhel.project.service.impl.Bhel_Customer_Vendor_Master_Service;
import com.bhel.project.service.impl.CustomerService;
import com.bhel.project.service.impl.EmailService;
import com.bhel.project.service.impl.FileUploadService;
import com.bhel.project.service.impl.ViewEditPageService;
import com.bhel.project.util.Message;

@Controller
public class BhelController {
	static final Logger logger = Logger.getLogger(BhelController.class);

	@Autowired
	BhelService bhelService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BhelMasterService bhelMasterService;
	@Autowired
	private BHEL_CUSTOMER_MASTER_REPO bHEL_CUSTOMER_MASTER_REPO;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private BHEL_PACKAGES_REPO BhelPackagesRepo;
	@Autowired
	private Bhel_Customer_Vendor_Master_Service bhelCustomerMasterService;
	@Autowired
	private ViewEditPageService viewEditPageService;
	@Autowired
	private BhelLoginService bhelLoginService;
	@Autowired
	private EmailService emailService;

	List<String> collect = new ArrayList<>();

	// 1st page
	// created by me
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String userLogin(Model model) {

		// SecurityContextHolder.getContext().getAuthentication().
		return "bhelLogin";

	}

	// Second page
	@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
	public String clientProjctDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		} else {

			return "dashboard";
		}
	}

	@GetMapping("/home")
	public String getHome() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}
		return "dashboard";
	}

	@GetMapping("/customer")
	public String getCustomer(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}
		BHEL_CUSTOMER_MASTER bHEL_CUSTOMER_MASTER = new BHEL_CUSTOMER_MASTER();
		model.addAttribute("bHEL_CUSTOMER_MASTER", bHEL_CUSTOMER_MASTER);
		return "customer";
	}

	@PostMapping("/saveMaster")
	public String saveMaster(@Valid @ModelAttribute("bHEL_CUSTOMER_MASTER") BHEL_CUSTOMER_MASTER bHEL_CUSTOMER_MASTER,
			BindingResult result, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}
		BHEL_CUSTOMER_MASTER saveCustomer = null;
		if (result.hasErrors()) {
			return "customer";
		}

		try {
			// if (contactNumberExists(bHEL_CUSTOMER_MASTER.getCpc()) == false) {
			saveCustomer = bhelMasterService.saveBHEL_CUSTOMER_MASTER(bHEL_CUSTOMER_MASTER);
			if (saveCustomer.getID() > 0)
				session.setAttribute("message", new Message("Successfully Saved!!.. ", "success"));
			else
				session.setAttribute("message", new Message("Some eror occured. Can not add new customer ", "danger"));
		} catch (Exception e) {
			session.setAttribute("message", new Message("Something went wrong..try Again!! ", "danger"));
			logger.error("saveMaster has some error in bhel_controller", e);

			return "customer";
		}
		return "redirect:/customer";
	}

// to check Existing user
	public boolean contactNumberExists(String contact) {
		if (bHEL_CUSTOMER_MASTER_REPO.getContact(contact).size() > 0) {
			return true;
		}
		return false;
	}

	@GetMapping("/add-project")
	public String getProject(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}
		return "add-project";
	}

	@GetMapping("/viewproject")
	public String viewAllProjects(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}
		List<CustomerVendorData> cvdata = customerService.getcustomerVendorData();
		model.addAttribute("projectData", cvdata);
		return "viewProject";
	}

	@GetMapping("/MasterAddproject")
	public String addProjectForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}
		model.addAttribute("bhelCustomerVendor", new Bhel_Customer_Vendor_MasterDto());
		List<BHEL_PACKAGES> collect = BhelPackagesRepo.findAll();

//		for (BHEL_PACKAGES pkgs : findAllPackages) {
//			logger.info("VENDOR_NAME --->" + pkgs.getVENDOR_NAME());
//			if (pkgs.getVENDOR_NAME() != null) {
//				collect.add(pkgs.getPACKAGE_NAME().concat(":").concat(pkgs.getVENDOR_NAME()));
//			} else {
//				collect.add(pkgs.getPACKAGE_NAME().concat(":").concat("NA"));
//
//			}
//		}

		model.addAttribute("AllPackages", collect);
		return "MasterAddProject";
	}

	@PostMapping("/saveMasterAddproject")
	public String saveMasterAddproject(
			@Valid @ModelAttribute("bhelCustomerVendor") Bhel_Customer_Vendor_MasterDto bhelCustomerVendor,
			BindingResult result, HttpSession session, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}
		List<BHEL_PACKAGES> collect1 = null;
		CustomerVendorData returnStatus = null;
		if (result.hasErrors()) {
			collect1 = BhelPackagesRepo.findAll();
			// List<String> collect1 = new ArrayList<>();
//			for (BHEL_PACKAGES pkgs : findAllPackages) {
//				if (pkgs.getVENDOR_NAME() != null) {
//					collect1.add(pkgs.getPACKAGE_NAME().concat(":").concat(pkgs.getVENDOR_NAME()));
//					collect1.add(pkgs.getPKG_ID());
//					
//				} else {
//					collect1.add(pkgs.getPACKAGE_NAME().concat(":").concat("NA"));
//
//				}
//			}
			model.addAttribute("AllPackages", collect1);
			return "MasterAddProject";
			// addProjectForm(model);
		}

		try {

			returnStatus = bhelCustomerMasterService.saveCustomerVendorData(bhelCustomerVendor);
			if (returnStatus.getcvm_id() > 0) {
				session.setAttribute("message", new Message("Project Saved Successfully!!", "success"));
				System.out.println(returnStatus);

			} else {
				session.setAttribute("message", new Message("Project Can not be saved!!", "danger"));
			}

		} catch (Exception e) {
			model.addAttribute("AllPackages", collect1);
			session.setAttribute("message", new Message("Something went wrong..try Again!! ", "danger"));
			logger.error("saveMasterAddproject has some error in bhel_controller", e);
			return "MasterAddProject";
		}
		return "redirect:/MasterAddproject";
	}

	@GetMapping("/MasterAddPackage")
	public String addPackageForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}

		model.addAttribute("masterAddPackage_Dto", new MasterAddPackage_Dto());
		return "MasterAddPackage";
	}

	@PostMapping("/saveMasterAddPackage")
	public String saveMasterAddPackage(
			@Valid @ModelAttribute("masterAddPackage_Dto") MasterAddPackage_Dto masterAddPackage_Dto,
			BindingResult result, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}

		if (result.hasErrors()) {
			return "MasterAddPackage";
		}
		try {
			bhelMasterService.saveMasterAddPackage(masterAddPackage_Dto);
			session.setAttribute("message", new Message("Package Saved Successfully!!", "success"));
		} catch (Exception e) {
			session.setAttribute("message", new Message("Something went wrong..try Again!! ", "danger"));
			logger.error("saveMasterAddPackage has some error in bhel_controller", e);
			return "MasterAddPackage";
		}
		return "redirect:/MasterAddPackage";
	}

	@GetMapping("/MasterAddSubPackage")
	public String addSubPackageForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}

		model.addAttribute("masterAddSubPackageDto", new MasterAddSubPackageDto());
		return "MasterAddSubPackage";
	}

	@PostMapping("/saveMasterAddSubPackage")
	public String saveMasterAddSubPackage(
			@Valid @ModelAttribute("masterAddSubPackageDto") MasterAddSubPackageDto masterAddSubPackageDto,
			BindingResult result, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}

		if (result.hasErrors()) {
			return "MasterAddSubPackage";
		}
		try {
			bhelMasterService.saveMasterSubPackage(masterAddSubPackageDto);
			session.setAttribute("message", new Message("Sub-Package Saved Successfully!!", "success"));
		} catch (Exception e) {
			session.setAttribute("message", new Message("Something went wrong..try Again!! ", "danger"));
			logger.error("saveMasterAddSubPackage has some error in bhel_controller", e);
			return "MasterAddSubPackage";
		}
		return "redirect:/MasterAddSubPackage";
	}

	@GetMapping("/MasterAddWorkArea")
	public String addWorkArea(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}
		model.addAttribute("masterAddWorkAreaDto", new MasterAddWorkAreaDto());
		return "MasterAddWorkArea";
	}

	@PostMapping("/saveMasterAddWorkArea")
	public String saveMasterAddWorkArea(
			@Valid @ModelAttribute("masterAddWorkAreaDto") MasterAddWorkAreaDto masterAddWorkAreaDto,
			BindingResult result, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}

		if (result.hasErrors()) {
			return "MasterAddWorkArea";
		}

		try {
			bhelMasterService.saveMasterAddWorkArea(masterAddWorkAreaDto);
			session.setAttribute("message", new Message("Work Area Saved Successfully!!", "success"));

		} catch (Exception e) {
			session.setAttribute("message", new Message("Something went wrong..try Again!! ", "danger"));
			logger.error("saveMasterAddWorkArea has some error in bhel_controller", e);
			return "MasterAddWorkArea";
		}
		return "redirect:/MasterAddWorkArea";
	}

	@GetMapping("/viewPage")
	public String getViewPage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		}
		model.addAttribute("viewEditProject", new ViewEditProject());
		return "viewPage";
	}

	// Create by shinjan
	@PostMapping("/workspace")
	public String workPage(@ModelAttribute("viewEditProject") ViewEditProject viewEditProject, Model model) {
		
		//System.out.println("returning from viewNdEdit"+viewEditProject);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		} else {
			logger.info("Project code :: " + viewEditProject.getProject_Code());
			logger.info("Project pkgs :: " + viewEditProject.getPackages());
			logger.info("Project ccn :: " + viewEditProject.getCustomer_CCN());
			int bbuToWamData = bhelService.getBbuToWamDataCountByCpcCcn(viewEditProject.getProject_Code(),
					viewEditProject.getCustomer_CCN());
			System.out.println("bbuToWamData---------->"+bbuToWamData);
			CustomerVendorData customerVendorData = bhelService.getProjectData(viewEditProject.getProject_Code());
			//System.out.println("customerVendorData---------->"+customerVendorData.getTotalProjValue());
			model.addAttribute("cpc", viewEditProject.getProject_Code());
			model.addAttribute("ccn", viewEditProject.getCustomer_CCN());
			model.addAttribute("pkgs", viewEditProject.getPackages());
			model.addAttribute("workarea", viewEditProject.getWork_area());
			model.addAttribute("totalvalue", customerVendorData.getTotalProjValue());
			
			System.out.println("WOrk Area "+viewEditProject.getWork_area());

			if (bbuToWamData > 0) {
				model.addAttribute("mode", "viewupdate");
				logger.info("viewupdate");
			} else {
				logger.info("create");
				model.addAttribute("mode", "create");
			}
		}

		return "work";
	}

	@GetMapping("/viewworkspace")
	public String viewWorkPage(@ModelAttribute("viewEditProject") ViewEditProject viewEditProject, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";

		} else {
			model.addAttribute("mode", "viewupdate");
			model.addAttribute("cpc", viewEditProject.getProject_Code());
			model.addAttribute("ccn", viewEditProject.getCustomer_CCN());
			model.addAttribute("pkgs", viewEditProject.getPackages());
			model.addAttribute("workarea", viewEditProject.getWork_area());

		}
		return "work";

	}

	@RequestMapping("/sendToMail")
	public String sendToMail(Model model, @RequestParam("email") String email) {
		 BhelLogin bhelLogin = null;
		    boolean flag = false;
		    try {
		        if (email != null) {
		            bhelLogin = bhelLoginService.checkUserExist(email)
	;
		            if (bhelLogin != null) {
		                String timestamp = Long.toString(System.currentTimeMillis());
		                String link = "http://182.73.216.93:8091/bhel/resetPassword/" + email + "/" + bhelLogin.getId() + "/" + timestamp;

		                // Store the link and its creation time in your database or cache for future validation

		                // Sending email
		                String message = "<h3>Dear Sir/Madam</h3><br><p>Please update your password from the link below.</p>"
		                        + "<a href='" + link + "'>" + link + "</a>";
		                String subject = "BHEL: reset your password";
		                flag = this.emailService.sendEmail(subject, message, email.trim());
		                if (flag) {
		                    model.addAttribute("message", "Password reset mail has been sent successfully");
		                }
		            } else {
		                model.addAttribute("message", "User does not exist.");
		            }
		        }
		    } catch (Exception e) {
		        logger.error("sendToMail error ", e);
		    }

		    return "bhelLogin";
	}

	@GetMapping("/getCPC_Master")
	public @ResponseBody ResponseEntity<List<CPC_MasterDto>> getCPC_Master(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "page", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize) {
		List<CPC_MasterDto> cpc_Master = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return new ResponseEntity<>(cpc_Master, HttpStatus.UNAUTHORIZED);

		}

		cpc_Master = customerService.getCPC_Master(search.toUpperCase(), pageNo, pageSize);

		return new ResponseEntity<>(cpc_Master, HttpStatus.OK);
	}

	@GetMapping("/getccn")
	public @ResponseBody List<AddProjectCcnDto> getCcn(@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "page", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		List<AddProjectCcnDto> collectCcn = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

				return collectCcn;

			}
			collectCcn = customerService.getccn(search.toUpperCase(), pageNo, pageSize);
		} catch (Exception e) {
			logger.error("error in getCcn " + e);
		}
		return collectCcn;
	}

	@GetMapping("/getCpc")
	@ResponseBody
	public List<CPC_MasterDto> getCpcForViewEdit(@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "page", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize) {

		List<CPC_MasterDto> cpc = null;
		if (search != null) {
			try {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

					return cpc;

				}
				cpc = viewEditPageService.getCpc(search.toUpperCase(), pageNo, pageSize);
			} catch (Exception e) {
				logger.error("getCpcForViewEdit", e);
			}
		} else {
			return null;
		}
		return cpc;
	}

	@GetMapping("/getCcnByCpc")
	@ResponseBody
	public AddProjectCcnDto getCcnOfCpc(@RequestParam(value = "search", required = true) String search) {

		AddProjectCcnDto ccnOfCpc = null;
		if (search != null) {
			try {
				System.out.println("running...." + search);
				ccnOfCpc = viewEditPageService.getCcnOfCpc(search.trim());
			} catch (Exception e) {
				logger.error("error in get getCcnOfCpc", e);
			}
		} else {
			logger.error("search value is null while fetching ccnOfCpc");
			return null;
		}

		return ccnOfCpc;
	}

	@GetMapping("/fetchPackages")
	@ResponseBody
	public List<BHEL_PACKAGES> getPackages(@RequestParam("search") String search) {
		System.out.println("search "+search);
		List<BHEL_PACKAGES> getAllPackages = null;
		if (search != null) {
			try {
				getAllPackages = viewEditPageService.GetAllPackages(search);
			} catch (Exception e) {
				logger.error("error in getPackages", e);
			}
		} else {
			return null;
		}
		return getAllPackages;
	}

	@GetMapping("/fetchWorkArea")
	@ResponseBody
	public List<BhelWorkArea> getWorkAreaByPkgOrCpc(@RequestParam("search") String search) {
		System.out.println("--------->"+search);
		List<BhelWorkArea> getAllWorkArea = null;
		if (search != null) {
			System.err.println(search.toUpperCase());
			try {
				getAllWorkArea = viewEditPageService.GetAllWorkArea(search.toUpperCase());
			} catch (Exception e) {
				logger.error("error in getWorkArea", e);
			}
		} else {
			return null;
		}

		return getAllWorkArea;
	}

//	@GetMapping("/getproject/{cpc}/{ccn}")
//	public @ResponseBody List<Object> getproject(@PathVariable("cpc") String cpc, @PathVariable("ccn") String ccn) {
//		List<Object> projectList = null;
//
//		try {
//			projectList = customerService.getproject(cpc, ccn);
//		} catch (Exception e) {
//			logger.error("error proccesing getproject in bhel_controller ", e);
//		}
//		return projectList;
//	}

	// Kaushik's code end

	@GetMapping("/subPackages")
	@ResponseBody
	public List<MasterAddSubPackageDto> getSubPackages(@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "page", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize) {
		List<MasterAddSubPackageDto> listSubPackages = new ArrayList<>();
		try {
			listSubPackages = bhelMasterService.getSubPackages(search.toUpperCase(), pageNo, pageSize);
		} catch (Exception e) {
			logger.error("error in getSubPackages", e);
		}
		return listSubPackages;
	}

	@GetMapping("/vendor")
	public String getVendor(Model model) {
		BhelVendorMaster bHEL_VENDOR_MASTER = new BhelVendorMaster();
		model.addAttribute("bHEL_VENDOR_MASTER", bHEL_VENDOR_MASTER);
		return "vendor";
	}

	@PostMapping("/saveVendorMaster")
	public String saveVendorMaster(@Valid @ModelAttribute("bHEL_VENDOR_MASTER") BhelVendorMaster bHEL_VENDOR_MASTER,
			BindingResult result, HttpSession session) {
		BhelVendorMaster saveVendor = null;
		if (result.hasErrors()) {
			return "vendor";
		}

		try {
			if (contactNumberExists(bHEL_VENDOR_MASTER.getVENDOR_CONTACT_NO()) == false) {
				saveVendor = bhelMasterService.saveBHEL_VENDOR_MASTER(bHEL_VENDOR_MASTER);
				if (saveVendor != null && saveVendor.getID() > 0) {
					session.setAttribute("message", new Message("Successfully Saved!!.. ", "success"));

				} else {
					session.setAttribute("message", new Message("Data can not be Saved!!.. ", "danger"));
				}
			} else {
				result.rejectValue("VENDOR_CONTACT_NO", "error.bHEL_VENDOR_MASTER", "Contact already Exists!!");
				return "vendor";
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Something went wrong..try Again!! ", "danger"));
			logger.error("saveMaster has some error in bhel_controller", e);

			return "vendor";
		}
		return "redirect:/vendor";
	}

}
