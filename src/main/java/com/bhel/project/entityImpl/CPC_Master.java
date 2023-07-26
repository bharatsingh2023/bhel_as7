package com.bhel.project.entityImpl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BHEL_CPC_Master")
public class CPC_Master {
	@Id
	@Size(min = 9)
	@NotNull(message = "Minimum length is 9 !!")
	private String CUSTOMER_PROJECT_CODE;
	@NotEmpty(message = "Customer name can not be empty!! ")
	private String CUSTOMER_PROJECT_NAME;

	@Override
	public String toString() {
		return "CPC_Master [CUSTOMER_PROJECT_CODE=" + CUSTOMER_PROJECT_CODE + ", CUSTOMER_PROJECT_NAME="
				+ CUSTOMER_PROJECT_NAME + "]";
	}

	public String getCUSTOMER_PROJECT_CODE() {
		return CUSTOMER_PROJECT_CODE;
	}

	public void setCUSTOMER_PROJECT_CODE(String cUSTOMER_PROJECT_CODE) {
		CUSTOMER_PROJECT_CODE = cUSTOMER_PROJECT_CODE;
	}

	public String getCUSTOMER_PROJECT_NAME() {
		return CUSTOMER_PROJECT_NAME;
	}

	public void setCUSTOMER_PROJECT_NAME(String cUSTOMER_PROJECT_NAME) {
		CUSTOMER_PROJECT_NAME = cUSTOMER_PROJECT_NAME;
	}

}
