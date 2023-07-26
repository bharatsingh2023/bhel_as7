package com.bhel.project.entityImpl;

import java.util.List;

public class Children {
	
	public String name;
	public String value;
	public String description;
	public String parent;
	public List<GrandChildren> grandChild;
	
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
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public List<GrandChildren> getGrandChild() {
		return grandChild;
	}
	public void setGrandChild(List<GrandChildren> grandChild) {
		this.grandChild = grandChild;
	}
	
	
	

}
