package com.bhel.project.entityImpl;

import com.bhel.project.entity.ProjectNameList;

public class ProjectNameListImpl implements ProjectNameList{
	public String customer_project_code;
	public String customer_project_name;

	public String getCustomer_project_code() {
		return customer_project_code;
	}

	public void setCustomer_project_code(String customer_project_code) {
		this.customer_project_code = customer_project_code;
	}

	public String getCustomer_project_name() {
		return customer_project_name;
	}

	public void setCustomer_project_name(String customer_project_name) {
		this.customer_project_name = customer_project_name;
	}

}
