package com.model;

import java.util.Date;

public class TicketsInfo {
	int tixNum;
	String category, emp, status, approver, submitted, purchased;
	public String getEmp() {
		return emp;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	double amt;
	
	public TicketsInfo(Integer tixNum, String submitted, String purchased, String category, Double amt, String emp, String status, String approver) {
		super();
		this.tixNum = tixNum;
		this.submitted = submitted;
		this.purchased = purchased;
		this.category = category;
		this.amt = amt;
		this.emp = emp;
		this.status = status;
		this.approver = approver;
	}
	
//	public TicketsInfo(Integer tixNum, String submitted, String purchased, String category, Double amt, String employee, String status) {
//		super();
//		this.tixNum = tixNum;
//		this.submitted = submitted;
//		this.purchased = purchased;
//		this.category = category;
//		this.amt = amt;
//		this.employee = employee;
//		this.status = status;
//	}



	public int getTixNum() {
		return tixNum;
	}

	public void setTixNum(int tixNum) {
		this.tixNum = tixNum;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getPurchased() {
		return purchased;
	}

	public void setPurchased(String purchased) {
		this.purchased = purchased;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	@Override
	public String toString() {
		if(status.equals("PENDING")) {
			return "\nTicket [#" + tixNum + ", submitted=" + submitted + ", purchased=" + purchased + ", category="
					+ category + ", amount=$" + amt + ", employee=" + emp + ", status=" + status + "]";
		}else {
		return "\nTicket [#" + tixNum + ", submitted=" + submitted + ", purchased=" + purchased + ", category="
				+ category + ", amount=$" + amt + ", employee=" + emp + ", status=" + status + ", approver=" + approver + "]";
		}
	}


	
	
	

}
