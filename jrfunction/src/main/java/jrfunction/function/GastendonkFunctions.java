package jrfunction.function;

import java.text.SimpleDateFormat;
import java.util.Locale;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.functions.AbstractFunctionSupport;
import net.sf.jasperreports.functions.FunctionContext;
import net.sf.jasperreports.functions.annotations.Function;
import net.sf.jasperreports.functions.annotations.FunctionCategories;
import net.sf.jasperreports.functions.annotations.FunctionParameter;
import net.sf.jasperreports.functions.annotations.FunctionParameters;

@FunctionCategories({ GastendonkCategory.class })
public class GastendonkFunctions extends AbstractFunctionSupport {

	@Function("GDATEFORMAT")
	@FunctionParameters({ @FunctionParameter("date"), @FunctionParameter("pattern") })
	public String GDATEFORMAT(java.util.Date date, String pattern) {
		if ("M-".equals(pattern)) {
			FunctionContext context = getContext();
			Locale locale = (Locale) context.getParameterValue(JRParameter.REPORT_LOCALE);
			if (Locale.GERMANY.equals(locale)) {
				pattern = "dd.MM.yyyy";
			} else {
				pattern = "yyyy-MM-dd";
			}
		}
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(pattern).format(date);
	}
}
