package com.bhel.project.entityImpl;

import java.util.List;

public class Parent {
	public String name;
	public String value;
	public String description;
	public List<Children> childList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Children> getChildList() {
		return childList;
	}

	public void setChildList(List<Children> childList) {
		this.childList = childList;
	}

//	public Map<String, List<Children>> getChildList() {
//		return childList;
//	}
//
//	public void setChildList(Map<String, List<Children>> childList) {
//		this.childList = childList;
//	}

}
