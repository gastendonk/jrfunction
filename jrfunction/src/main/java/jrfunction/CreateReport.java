package jrfunction;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CreateReport {

	public static void main(String[] args) {
		Map<String, Object> parameters = new HashMap<>();
		exportToPdf(Paths.get("reports/demo.jrxml"), parameters, getPersons(), Paths.get("reports/demo.pdf"));
		System.out.println("finished");
	}

	private static List<Person> getPersons() {
		List<Person> persons = new ArrayList<>();
		Person adam = new Person();
		adam.setName("Adam");
		adam.setBirthdate(13, 12, 2001);
		persons.add(adam);
		Person eva = new Person();
		eva.setName("Eva");
		eva.setBirthdate(1, 1, 2002);
		persons.add(eva);
		return persons;
	}
	
	private static void exportToPdf(Path jrxmlFile, Map<String, Object> parameters, Collection<?> collection, Path pdfFile) {
        try {
        	SimpleJasperReportsContext jasperReportsContext = new SimpleJasperReportsContext();
			JasperReport report = JasperCompileManager.compileReport(jrxmlFile.toString().replace("\\", "/"));
            JRDataSource datasource = new JRBeanCollectionDataSource(collection);
            JasperPrint printer = JasperFillManager.getInstance(jasperReportsContext)
            		.fill(report, parameters, datasource);
            JasperExportManager.exportReportToPdfFile(printer, pdfFile.toString().replace("\\", "/"));
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
	}
}
