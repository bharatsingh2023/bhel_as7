package com.bhel.project.dtoImpl;

public class UserInfoDTO {
	private String CUSTOMER_PROJECT_CODE;
	private String CCN;
	private String PROJECTIDENTIFIER;

	public String getCUSTOMER_PROJECT_CODE() {
		return CUSTOMER_PROJECT_CODE;
	}

	public void setCUSTOMER_PROJECT_CODE(String cUSTOMER_PROJECT_CODE) {
		CUSTOMER_PROJECT_CODE = cUSTOMER_PROJECT_CODE;
	}

	public String getCCN() {
		return CCN;
	}

	public void setCCN(String cCN) {
		CCN = cCN;
	}

	public String getPROJECTIDENTIFIER() {
		return PROJECTIDENTIFIER;
	}

	public void setPROJECTIDENTIFIER(String pROJECTIDENTIFIER) {
		PROJECTIDENTIFIER = pROJECTIDENTIFIER;
	}

	@Override
	public String toString() {
		return "UserInfoDTO [CUSTOMER_PROJECT_CODE=" + CUSTOMER_PROJECT_CODE + ", CCN=" + CCN + ", PROJECTIDENTIFIER="
				+ PROJECTIDENTIFIER + "]";
	}
}
