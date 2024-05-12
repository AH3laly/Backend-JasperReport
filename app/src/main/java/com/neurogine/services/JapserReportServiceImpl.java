package com.neurogine.services;

import com.neurogine.models.ReportDocument;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

public class JapserReportServiceImpl implements JapserReportService {
	
	public JasperDesign jasperDesign;
	public JRDesignStyle jasperDesignStyle;
	public ReportDocument reportDocument;
	public JRDataSource dataSource;
	
	// Plan
	// Create New Report
	// define Report Design
	// Define Report Styles
	// Build Report
	// Define Report Fields
	// Create Document Field
	// Style Report Field
	// Build Header Band
	// Build Body Band
	// Generate JRXML
	// Export PDF
}
