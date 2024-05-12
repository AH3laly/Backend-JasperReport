package com.neurogine;

import java.util.ArrayList;

import com.neurogine.models.ReportDocument;
import com.neurogine.models.ReportDocumentField;
import com.neurogine.services.JapserReportServiceImpl;

import net.sf.jasperreports.engine.JRDataSource;

public class App {
	
    public static void main(String[] args) {
    	try {
    		
    		ReportDocument reportDocument = new ReportDocument();
        	reportDocument.setName("First Demo Report");
        	reportDocument.setHeaderFields(generateDemofields());
        	
        	JapserReportServiceImpl jasperReportService = new JapserReportServiceImpl();
        	jasperReportService.createNewReport(reportDocument, createDemoDataSource());
        	
        	// Export jrxml file
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
    
    private static ArrayList<ReportDocumentField> generateDemofields(){
    	return new ArrayList<ReportDocumentField>();
    }

    private static JRDataSource createDemoDataSource() throws InstantiationException, IllegalAccessException {
    	return JRDataSource.class.newInstance();
		
    }
    
}
