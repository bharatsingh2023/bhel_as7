package com.bhel.project.entityImpl;

import java.util.List;

public class GrandChildren {

	public String name;
	public String value;
	public String description;
	public String child;
	public List<GreatGrandChildren> gGrandchild;

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

	public List<GreatGrandChildren> getgGrandchild() {
		return gGrandchild;
	}

	public void setgGrandchild(List<GreatGrandChildren> gGrandchild) {
		this.gGrandchild = gGrandchild;
	}

	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}

	

}
