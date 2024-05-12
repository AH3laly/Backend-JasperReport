package com.neurogine.services;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.neurogine.models.ReportDocument;
import com.neurogine.models.ReportDocumentField;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
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
	
	public void createNewReport(ReportDocument reportDocument) throws JRException {
		createNewReport(reportDocument, null);
	}
	
	public void createNewReport(ReportDocument reportDocument, JRDataSource dataSource) throws JRException {

		this.reportDocument = reportDocument;
		this.dataSource = dataSource;
		
		defineReportDesign();
		
		// Starting point to build the whole report
		buildReport();
	}
	
	private void defineReportDesign() throws JRException {
		jasperDesign = new JasperDesign();
		jasperDesign.setName(reportDocument.getName());
		defineReportStyles();
	}
	
	private void defineReportStyles() throws JRException {
		jasperDesignStyle = new JRDesignStyle();
		jasperDesignStyle.setName("tableFonts");
		jasperDesignStyle.setFontSize(8);
		jasperDesign.addStyle(jasperDesignStyle);
	}

	private void buildReport() throws JRException {
		defineReportFields();
		buildHeaderBand();
		buildBodyBand();
	}
	
	private void defineReportFields() throws JRException {
		for(ReportDocumentField reportField : reportDocument.getHeaderFields()) {
        	jasperDesign.addField(createDocumentField(reportField));
        }
	}
	
	private JRDesignField createDocumentField(ReportDocumentField reportField) {
        JRDesignField field = new JRDesignField();
    	field.setName(reportField.getName());
    	field.setValueClassName(reportField.getType());
    	return field;
	}
	
	private void buildHeaderBand() throws JRException {
		
		// Define Header Band
		JRDesignBand header = new JRDesignBand();
        header.setHeight(REPORT_BAND_HEIGHT);
        
		Integer x = REPORT_START_X;
		for(ReportDocumentField reportField : reportDocument.getHeaderFields()) {
			JRDesignStaticText tableHeader = new JRDesignStaticText();
			
			// Set Content
			tableHeader.setText(reportField.getLabel());
			
			// Set Position Details
			tableHeader.setHeight(REPORT_FIELD_HEIGHTH);
			tableHeader.setWidth(REPORT_FIELD_WIDTH);
            tableHeader.setX(x);
            tableHeader.setY(0);

            // Set Styles
            styleReportField(tableHeader.getLineBox());
            tableHeader.setStyle(jasperDesignStyle);
            
            // Push element to Header band
            header.addElement(tableHeader);
            
            x += REPORT_FIELD_WIDTH;
        }
		
		// Inject Table Header to Jasper Design
		jasperDesign.setPageHeader(header);
	}

	private void buildBodyBand() {
	
		// Define Body Band
		JRDesignBand body = new JRDesignBand();
		body.setHeight(REPORT_BAND_HEIGHT);
	    
	    Integer x = REPORT_START_X;
		for(ReportDocumentField reportField : reportDocument.getHeaderFields()) {
			JRDesignTextField tableRow = new JRDesignTextField();
			
			// Set Content
	        JRDesignExpression colspanExpression = new JRDesignExpression();
	        colspanExpression.setText("$F{" + reportField.getName() + "}");
	        tableRow.setExpression(colspanExpression);
	        
			// Set Position Details
			tableRow.setHeight(REPORT_FIELD_HEIGHTH);
			tableRow.setWidth(REPORT_FIELD_WIDTH);
			tableRow.setX(x);
			tableRow.setY(0);
	        
			// Set Styles
	        styleReportField(tableRow.getLineBox());
	        tableRow.setStyle(jasperDesignStyle);
	        
	        body.addElement(tableRow);
	        
	        x += REPORT_FIELD_WIDTH;
		}
		
		// Inject Table Header to Jasper Design
	    ((JRDesignSection)jasperDesign.getDetailSection()).addBand(body);
	
	}

	private void styleReportField(JRLineBox lineBox) {
        lineBox.getPen().setLineWidth(Float.MIN_NORMAL);
        lineBox.getPen().setLineColor(Color.BLACK);
        lineBox.setPadding(5);
	}
	
	public void generateJrxml(String destFileName) throws JRException {
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperCompileManager.writeReportToXmlFile(jasperReport , destFileName);
	}

	public void exportPdf(String filePath) throws JRException {
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        
        JasperPrint jasperPrint;
        Map<String, Object> parameters = new HashMap<String, Object>();
        if(dataSource != null) {
        	jasperPrint =  JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        } else {
        	jasperPrint =  JasperFillManager.fillReport(jasperReport, parameters);
        }
        
        JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
	}
}
