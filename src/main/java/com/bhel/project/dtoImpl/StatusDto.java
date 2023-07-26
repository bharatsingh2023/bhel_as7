package com.bhel.project.dtoImpl;

import java.util.List;
import java.util.Map;

public class StatusDto {
	private boolean saveStatus;
	private String saveMessage;
	private boolean readStatus;
	private int pkgCount;
	private int ProjectVendorData;
	private Exception exp;
	private Map<String, List<ParentDto>> parentMap;
	// private String Message;
	private List<Map<String, Map<String, String>>> bbuWamMapingData;

	public boolean isSaveStatus() {
		return saveStatus;
	}

	public void setSaveStatus(boolean saveStatus) {
		this.saveStatus = saveStatus;
	}

	public String getSaveMessage() {
		return saveMessage;
	}

	public void setSaveMessage(String saveMessage) {
		this.saveMessage = saveMessage;
	}

	public List<Map<String, Map<String, String>>> getBbuWamMapingData() {
		return bbuWamMapingData;
	}

	public void setBbuWamMapingData(List<Map<String, Map<String, String>>> bbuWamMapingData) {
		this.bbuWamMapingData = bbuWamMapingData;
	}

	public boolean isReadStatus() {
		return readStatus;
	}

	public void setReadStatus(boolean readStatus) {
		this.readStatus = readStatus;
	}

	public Map<String, List<ParentDto>> getParentMap() {
		return parentMap;
	}

	public void setParentMap(Map<String, List<ParentDto>> parentMap) {
		this.parentMap = parentMap;
	}

	public int getPkgCount() {
		return pkgCount;
	}

	public void setPkgCount(int pkgCount) {
		this.pkgCount = pkgCount;
	}

	public int getProjectVendorData() {
		return ProjectVendorData;
	}

	public void setProjectVendorData(int projectVendorData) {
		ProjectVendorData = projectVendorData;
	}

	public Exception getExp() {
		return exp;
	}

	public void setExp(Exception exp) {
		this.exp = exp;
	}
	

}
