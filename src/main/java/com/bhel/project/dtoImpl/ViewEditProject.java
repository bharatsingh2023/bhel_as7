package com.bhel.project.dtoImpl;

public class ViewEditProject {

	private String project_Code;
	private String project_Name;
	private String customer_Name;
	private String customer_CCN;
	private String packages;
	private String sub_packages;
	private String work_area;
//	private List<Map<String, Map<String, String>>> pathMapList;
	private String pathMapList;

	public String getProject_Name() {
		return project_Name;
	}

	public void setProject_Name(String project_Name) {
		this.project_Name = project_Name;
	}

	public String getCustomer_Name() {
		return customer_Name;
	}

	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public String getSub_packages() {
		return sub_packages;
	}

	public void setSub_packages(String sub_packages) {
		this.sub_packages = sub_packages;
	}

	public String getWork_area() {
		return work_area;
	}

	public void setWork_area(String work_area) {
		this.work_area = work_area;
	}

	public ViewEditProject() {
		super();
	}

	public String getProject_Code() {
		return project_Code;
	}

	public void setProject_Code(String project_Code) {
		this.project_Code = project_Code;
	}

	public String getCustomer_CCN() {
		return customer_CCN;
	}

	public void setCustomer_CCN(String customer_CCN) {
		this.customer_CCN = customer_CCN;
	}

	public String getPathMapList() {
		return pathMapList;
	}

	public void setPathMapList(String pathMapList) {
		this.pathMapList = pathMapList;
	}

	@Override
	public String toString() {
		return "ViewEditProject [project_Code=" + project_Code + ", project_Name=" + project_Name + ", customer_Name="
				+ customer_Name + ", customer_CCN=" + customer_CCN + ", packages=" + packages + ", sub_packages="
				+ sub_packages + ", work_area=" + work_area + ", pathMapList=" + pathMapList + "]";
	}

	/*
	 * public List<Map<String, Map<String, String>>> getPathMapList() { return
	 * pathMapList; }
	 * 
	 * public void setPathMapList(List<Map<String, Map<String, String>>>
	 * pathMapList) { this.pathMapList = pathMapList; }
	 */
	

}
