package com.neurogine.models;

public class ReportDocumentField {
	private String name;
	private String type;
	private String label;

	public ReportDocumentField(String name, String type, String label) {
		this.name = name;
		this.type = type;
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
