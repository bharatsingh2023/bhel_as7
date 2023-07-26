package com.bhel.project.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bhel.project.dtoImpl.BBUWAMDto;
import com.bhel.project.jpa.repo.BhelBbuDataRepo;
import com.bhel.project.jpa.repo.BhelWamDataRepo;
import com.bhel.project.util.BhelProcedures;
import com.bhel.project.util.TextChange;

@Service
public class FileUploadService {
	static final Logger logger = Logger.getLogger(FileUploadService.class);

	@Autowired
	private BhelBbuDataRepo bhelBbuDataRepo;
	@Autowired
	private BhelWamDataRepo bhelWamDataRepo;
	String desktopPath1 = "";
	String desktopPath2 = "";

	@Autowired
	private BhelProcedures bhelProcedures;

	public Map<String, Object> saveAddProject(BBUWAMDto bBUWAMDto) throws ParseException {

		String bbuDate = bBUWAMDto.getBbuDate();
		String custBbuDate = bBUWAMDto.getCusBbuDate();

		String pattern = "yyyy-MM-dd";
		// List<String> list = new ArrayList<>();
		// boolean procedureSuccess = false;
		Map<String, Object> procedureResultMap = null;
		String message = null;
		int scuuessVendorBbu = 0;
		int scuuessCustomerBbu = 0;

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date bbuDt = null;
		Date wamDt = null;
		try {
			if (bBUWAMDto.getCustomerBBU() != null && !bBUWAMDto.getCustomerBBU().isEmpty()) {
				String cusBBuCsv = this.convertXlsxToCsv(bBUWAMDto.getCustomerBBU());
				procedureResultMap = bhelProcedures.procedure_BHEL_INSERT_FRM_EXCEL_CUST_BBU(cusBBuCsv,
						bBUWAMDto.getCPC(), bBUWAMDto.getCUSTOMER_CONTACT_NO(), bBUWAMDto.getPROJECT_NAME(),
						dateFormat.parse(custBbuDate));

				logger.info("procedureResult Map ::" + procedureResultMap);

			}

			if (bBUWAMDto.getVendorBBU() != null && !bBUWAMDto.getVendorBBU().isEmpty()) {

				logger.info("get PKG_ID() ::" + bBUWAMDto.getPKG_ID());
				logger.info("get CUSTOMER_CONTACT_NO() ::" + bBUWAMDto.getCUSTOMER_CONTACT_NO());
				logger.info("get vendor_name() ::" + bBUWAMDto.getVendorName());
				String vendBBuCv = this.convertXlsxToCsv(bBUWAMDto.getVendorBBU());

				bbuDt = dateFormat.parse(bbuDate);

				procedureResultMap = bhelProcedures.procedure_BHEL_INSERT_FRM_EXCEL_VEND_BBU(vendBBuCv,
						bBUWAMDto.getCPC(), bBUWAMDto.getCUSTOMER_CONTACT_NO(), bBUWAMDto.getPROJECT_NAME(),
						bBUWAMDto.getPKG_ID(), bBUWAMDto.getWORK_AREA(), bBUWAMDto.getVendorName(), bbuDt, wamDt);
				logger.info("procedureResult Map ::" + procedureResultMap);
			}
			if (procedureResultMap != null && procedureResultMap.get("exception").toString().equalsIgnoreCase("na")) {

				message = procedureResultMap.get("P_OUT").toString();
				if (message.equalsIgnoreCase("sucess")) {
					logger.info("message_2 ::" + procedureResultMap.get("P_OUT"));
					if (procedureResultMap.get("userType").equals("vendor")) {
						scuuessVendorBbu = bhelBbuDataRepo.updateVendorData(bBUWAMDto.getCPC());
					} else if (procedureResultMap.get("userType").equals("customer")) {
						scuuessCustomerBbu = bhelBbuDataRepo.updateCustomerData(bBUWAMDto.getCPC());
					}
					if (scuuessCustomerBbu > 0 || scuuessVendorBbu > 0) {
						procedureResultMap.put("procedureSuccess", true);

					} else {
						procedureResultMap.put("procedureSuccess", false);

					}
				} else {
					procedureResultMap.put("procedureSuccess", false);
				}
			} else if (procedureResultMap != null
					&& !procedureResultMap.get("exception").toString().equalsIgnoreCase("na")) {
				procedureResultMap.put("procedureSuccess", false);
			}
		} catch (IOException e) {
			logger.error("error while saving data project bbu/wam", e);

		}
		return procedureResultMap;

	}

	// conversion logic
	public String convertXlsxToCsv(MultipartFile excelFile) throws IOException {
		Workbook workbook = null;
		String csvFilePath = null;
		final int PERCENTAGE_FORMAT = BuiltinFormats.getBuiltinFormat("0%");
		Sheet sheet = null;
		if (excelFile.getSize() > 0) {
			workbook = WorkbookFactory.create(excelFile.getInputStream());
			sheet = workbook.getSheetAt(0);
		}
		// File file = new File(excelFile.getOriginalFilename());
		// if(file.exists()) {

		String fileNameWithOutExt = FilenameUtils.removeExtension(excelFile.getOriginalFilename());
		logger.info("fileNameWithOutExt :" + fileNameWithOutExt);
		String csvFileName = fileNameWithOutExt + ".csv";
		// csvFilePath = "D:\\BhelUpload\\BBU\\" +csvFileName;
		String destinationFolderPath = "\\\\Beassrv01\\BHEL\\" + csvFileName;
		// }

		// FileOutputStream fileOutputStream = new FileOutputStream(csvFilePath);
		FileOutputStream fileOutputStream = new FileOutputStream(destinationFolderPath);
		StringBuilder data = new StringBuilder();

		for (Row row : sheet) {
			for (Cell cell : row) {

				switch (cell.getCellType()) {
					case BOOLEAN:
						data.append(cell.getBooleanCellValue() + "|");
						break;

					case NUMERIC:
						if (cell.getCellStyle().getDataFormat() == PERCENTAGE_FORMAT) {

						}
						DecimalFormat df = new DecimalFormat("#.###");
						df.setRoundingMode(RoundingMode.CEILING);
						data.append(df.format(cell.getNumericCellValue()) + "|");

						break;

					case STRING:
						logger.info("data :: " + cell.getStringCellValue().replace("\n", " "));

						TextChange tc = new TextChange();
						String changedText = tc.textChange(cell.getStringCellValue());
						logger.info("changedText :: " + changedText);
						// data.append(cell.getStringCellValue().replace("\n", "
						// ").strip().toLowerCase() + "|");
						data.append(changedText.replace("\n", " ") + "|");

						break;

					case BLANK:

						data.append("" + "|");

						break;

					default:

						data.append(cell + "|");

				}
			}
			data.append("\n");
		}

		fileOutputStream.write(data.toString().getBytes());
		fileOutputStream.close();
		workbook.close();

		// logger.info(fileOutputStream.);
		return csvFileName;
	}

	public Map<String, Object> addDataToProject(BBUWAMDto bBUWAMDto) throws ParseException {

		String wamDate = bBUWAMDto.getWamDate();
		String ecDate = bBUWAMDto.getEcDate();
		String pattern = "yyyy-MM-dd";
		
		Map<String, Object> procedureResultMap = null;
		String message = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		// Date periodFrom = null;
		Date wamDt = null;
		try {
			if (bBUWAMDto.getCustomerEc() != null && !bBUWAMDto.getCustomerEc().isEmpty()) {
				logger.info("ec date :: " + ecDate);
				String cusEcCsv = this.convertXlsxToCsv(bBUWAMDto.getCustomerEc());
				procedureResultMap = bhelProcedures.procedure_BHEL_INSERT_FROM_EXCEL_EC_DATA(cusEcCsv,
						bBUWAMDto.getCPC(), bBUWAMDto.getCUSTOMER_CONTACT_NO(), bBUWAMDto.getPROJECT_NAME(),
						dateFormat.parse(ecDate));

				logger.info("procedureResult Map ::" + procedureResultMap);

			}

			if (bBUWAMDto.getVendorWam() != null && !bBUWAMDto.getVendorWam().isEmpty()) {

				String vendBBuCv = this.convertXlsxToCsv(bBUWAMDto.getVendorWam());

				wamDt = dateFormat.parse(wamDate);

				procedureResultMap = bhelProcedures.procedure_BHEL_INSERT_FROM_EXCEL(vendBBuCv,
						bBUWAMDto.getPROJECT_NAME(), bBUWAMDto.getCUSTOMER_CONTACT_NO(), bBUWAMDto.getPROJECT_NAME(),
						bBUWAMDto.getPKG_ID(), bBUWAMDto.getWORK_AREA(), wamDt);

			}
			if (procedureResultMap != null && procedureResultMap.get("exception").toString().equalsIgnoreCase("na")) {
				message = procedureResultMap.get("P_OUT").toString();

				if (message.equalsIgnoreCase("sucess")) {
					logger.info("message_2 ::" + message);

					procedureResultMap.put("dataProcedureSuccess", true);
				} else {
					procedureResultMap.put("dataProcedureSuccess", false);
					procedureResultMap.put("exception", procedureResultMap.get("P_MSG"));
				}
				logger.info("procedureResult Map ::" + procedureResultMap);

			} else if (procedureResultMap != null
					&& !procedureResultMap.get("exception").toString().equalsIgnoreCase("na")) {
				procedureResultMap.put("dataProcedureSuccess", false);
			}
			logger.info("procedureResult Map ::" + procedureResultMap);
		} catch (IOException e) {
			logger.error("error while saving data project bbu/wam", e);
			procedureResultMap.put("dataProcedureSuccess", false);
		}
		return procedureResultMap;
	}

}
