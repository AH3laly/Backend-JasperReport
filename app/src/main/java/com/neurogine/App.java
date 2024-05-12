package com.neurogine;

import java.util.ArrayList;
import java.util.List;

import com.neurogine.beans.PdfReportRow;
import com.neurogine.models.ReportDocument;
import com.neurogine.models.ReportDocumentField;
import com.neurogine.services.JapserReportServiceImpl;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class App {
	
    public static void main(String[] args) {
    	try {
    		
    		// Create ReportDocument
    		ReportDocument reportDocument = new ReportDocument();
        	reportDocument.setName("First Demo Report");
        	reportDocument.setHeaderFields(generateDemofields());
        	
        	// Pass ReportDocument to JapserReportService
        	JapserReportServiceImpl jasperReportService = new JapserReportServiceImpl();
        	jasperReportService.createNewReport(reportDocument, createDemoDataSource());
        	
        	// Export JRXML file
        	String jrxmlDestFileFile = "/projects/results/Output.jrxml";
        	jasperReportService.generateJrxml(jrxmlDestFileFile);
        	
        	// Export PDF file
        	String pdfDestFileFile = "/projects/results/Output.pdf";
        	jasperReportService.exportPdf(pdfDestFileFile);
        	
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    private static List<ReportDocumentField> generateDemofields(){
    	
    	// date, referenceno, amount, status, remark
    	List<ReportDocumentField> fieldsList = new ArrayList<>();
    	fieldsList.add(new ReportDocumentField("date", "java.lang.String", "Date"));
    	fieldsList.add(new ReportDocumentField("referenceno", "java.lang.String", "Reference No"));
    	fieldsList.add(new ReportDocumentField("amount", "java.lang.String", "Amount"));
    	fieldsList.add(new ReportDocumentField("status", "java.lang.String", "Status"));
    	fieldsList.add(new ReportDocumentField("remark", "java.lang.String", "Remark"));
    	
    	return fieldsList;
    }

    private static JRDataSource createDemoDataSource() throws InstantiationException, IllegalAccessException {

    	List<PdfReportRow> tableData = new ArrayList<PdfReportRow>();
        PdfReportRow row;
        for(int i =0; i < 5; i++) {
        	String indexString = String.valueOf(i + 1);
        	row = new PdfReportRow();
        	row.setAmount("10.2");
        	row.setDate("2024-11-10");
        	row.setReferenceno("54877" + indexString);
        	row.setStatus("Active");
        	row.setRemark("Remarks " + indexString);
        	tableData.add(row);
        }

		JRDataSource dataSource = new JRBeanCollectionDataSource(tableData);
		return dataSource;
    }
    
}
