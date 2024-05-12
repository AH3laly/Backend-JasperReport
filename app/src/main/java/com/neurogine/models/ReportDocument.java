package com.neurogine.models;

import java.util.List;

public class ReportDocument {
	private String name;
	private List<ReportDocumentField> headerFields;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ReportDocumentField> getHeaderFields() {
		return headerFields;
	}
	public void setHeaderFields(List<ReportDocumentField> headerFields) {
		this.headerFields = headerFields;
	}
}
