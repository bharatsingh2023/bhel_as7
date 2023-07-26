package com.bhel.project.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhel.project.dtoImpl.Bhel_Customer_Vendor_MasterDto;
import com.bhel.project.entityImpl.BHEL_PACKAGES;
import com.bhel.project.entityImpl.CustomerVendorData;
import com.bhel.project.entityImpl.ProjectVendorData;
import com.bhel.project.jpa.repo.BHEL_CUSTOMER_MASTER_REPO;
import com.bhel.project.jpa.repo.BHEL_PACKAGES_REPO;
import com.bhel.project.jpa.repo.BhelProjectVendorRepo;
import com.bhel.project.jpa.repo.Bhel_Customer_Vendor_MasterRepo;
import com.bhel.project.repo.BhelPkgRepo;

@Service
public class Bhel_Customer_Vendor_Master_Service {
    static final Logger logger = Logger.getLogger(Bhel_Customer_Vendor_Master_Service.class);

    @Autowired
    private Bhel_Customer_Vendor_MasterRepo bhelCustomerVendorMasterRepo;
    @Autowired
    private BhelProjectVendorRepo bhelProjectVendorRepo;
    @Autowired
    private BHEL_CUSTOMER_MASTER_REPO bhelCustomerMasterRepo;
    @Autowired
    private BHEL_PACKAGES_REPO bhelPackages;
    @Autowired
    private BhelPkgRepo bhelPkgRepo;
    @Autowired
    private SessionFactory pkgSessionFactory;

    public CustomerVendorData saveCustomerVendorData(Bhel_Customer_Vendor_MasterDto bhelCustomerVendorMasterDto) {
        CustomerVendorData customerVendorDataSave = null;
        ProjectVendorData projectVendorData = null;
        try (Session session = pkgSessionFactory.openSession()) {
            List<String> getpKg = bhelCustomerVendorMasterDto.getpKg();
            for (String e : getpKg) {
                session.beginTransaction();

                List<BHEL_PACKAGES> vendorNameList = bhelPkgRepo.getVendor(session, e);

                if (vendorNameList.size() > 0) {
                    for (BHEL_PACKAGES vendorName : vendorNameList) {
                        if (vendorName.getVENDOR_NAME() != null) {
                            logger.info("saveCustomerVendorData service---->" + vendorName);
                            projectVendorData = new ProjectVendorData();
                            projectVendorData.setpKg(vendorName.getPACKAGE_NAME());
                            projectVendorData.setProjectCode(bhelCustomerVendorMasterDto.getProjectCode());
                            projectVendorData.setVendorId(e);
                            projectVendorData.setVendorName(vendorName.getVENDOR_NAME());
                        } else {
                            logger.info("saveCustomerVendorData service---->" + vendorNameList.size());
                            projectVendorData = new ProjectVendorData();
                            projectVendorData.setpKg(vendorName.getPACKAGE_NAME());
                            projectVendorData.setProjectCode(bhelCustomerVendorMasterDto.getProjectCode());
                        }
                    }
                }
                bhelProjectVendorRepo.save(projectVendorData);
            }
            CustomerVendorData customerVendorData = new CustomerVendorData();
            customerVendorData.setProjectCode(bhelCustomerVendorMasterDto.getProjectCode().trim());
            customerVendorData.setProjectName(bhelCustomerVendorMasterDto.getProjectName());
            // customerVendorData.setcCn(bhelCustomerVendorMasterDto.getcCn().trim());
            customerVendorData.setTotalProjValue(Double.parseDouble(bhelCustomerVendorMasterDto.getTotalProjValue().trim()));
            customerVendorData.setCustomer_name(bhelCustomerMasterRepo.getCustomerName(bhelCustomerVendorMasterDto.getProjectCode().trim()));
            customerVendorDataSave = bhelCustomerVendorMasterRepo.save(customerVendorData);
            return customerVendorDataSave;
        } catch (Exception e) {
            logger.error("error in saveCustomerVendorData ", e);
            return null;
        }
    }
}

