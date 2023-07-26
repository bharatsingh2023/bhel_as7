package com.bhel.project.dtoImpl;

import java.util.List;

public class GrandChildrenDto {

	public String name;
	public String value;
	public String description;
	public String child;
	public double rate;
	public double quantity;
	public Double ammount;
	public List<GreatGrandChildrenDto> gGrandchild;

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

	public List<GreatGrandChildrenDto> getgGrandchild() {
		return gGrandchild;
	}

	public void setgGrandchild(List<GreatGrandChildrenDto> gGrandchild) {
		this.gGrandchild = gGrandchild;
	}

	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Double getAmmount() {
		return ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

}
