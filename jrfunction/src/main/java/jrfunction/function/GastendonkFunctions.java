package jrfunction.function;

import java.text.SimpleDateFormat;

import net.sf.jasperreports.functions.AbstractFunctionSupport;
import net.sf.jasperreports.functions.annotations.Function;
import net.sf.jasperreports.functions.annotations.FunctionCategories;
import net.sf.jasperreports.functions.annotations.FunctionParameter;
import net.sf.jasperreports.functions.annotations.FunctionParameters;

@FunctionCategories({ GastendonkCategory.class })
public class GastendonkFunctions extends AbstractFunctionSupport {

	@Function("GDATEFORMAT")
	@FunctionParameters({ @FunctionParameter("date"), @FunctionParameter("pattern") })
	public String GDATEFORMAT(java.util.Date date, String pattern) {
		if (date == null) {
			return null;
		}
		if ("M-".equals(pattern)) {
			// TODO if Locale is Germany
			pattern = "dd.MM.yyyy";
		}
		return new SimpleDateFormat(pattern).format(date);
	}
}
