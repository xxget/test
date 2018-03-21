//package com.lding.wiqs.general.business.reports;
//
//import java.util.Map;
//
//import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
//
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//
///**
// * jasper报表 
// * @author xxg
// *
// */
//public class BaseParamJasperReportsMultiFormatView extends JasperReportsMultiFormatView {
//	
//	private JasperReport jasperReport;
//	
//	public BaseParamJasperReportsMultiFormatView() {
//		super();
//	}
//	
//	protected JasperPrint fillReport(Map<String,Object> model) throws Exception {
//		if(model.containsKey("url")) {
//			//setUrl("/WEB-INF/classes/reports/"+model.get("url")+".jasper");
//			setUrl("/report/"+model.get("url")+".jasper");
//			this.jasperReport = loadReport();
//		}
//		if(!model.containsKey("format")) {
//			model.put("format", "pdf");
//		}
//		return super.fillReport(model);
//	}
//	
//	protected JasperReport getReport() {
//		return this.jasperReport;
//	}
//}
