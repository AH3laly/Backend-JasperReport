package com.neurogine.beans;

public class PdfReportRow {

	private Integer id;
	private String date;
	private String referenceno;
	private String amount;
	private String status;
	private String remark;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReferenceno() {
		return this.referenceno;
	}
	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
