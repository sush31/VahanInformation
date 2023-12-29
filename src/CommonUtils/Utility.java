///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package CommonUtils;
//
//import java.awt.print.PrinterException;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.Serializable;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.faces.context.FacesContext;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.design.JRDesignStyle;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import nic.rto.vahan.common.VahanException;
//import nic.vahan.common.jsf.utils.validators.POSValidator;
//
////import org.apache.log4j.Logger;
//
///**
// *
// * @author vinay
// */
//public class Utility implements Serializable{
//
//	//private static final Logger LOGGER = Logger.getLogger(Utility.class);
//	String string;
//	String st1[] = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", };
//	String st2[] = { "hundred", "thousand", "lakh", "crore" };
//	String st3[] = { "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
//			"ninteen", };
//	String st4[] = { "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninty" };
//	static String receiptNo = null;
//
//	public static String checkDataIsNULL(Object obj) {
//		if (obj != null) {
//			return obj.toString();
//
//		} else {
//			return "";
//		}
//	}
//
//	public static java.sql.Timestamp convertStringToTimestamp(String date) {
//		Timestamp fromTS1 = null;
//		try {
//			SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
//			Date lFromDate1 = datetimeFormatter1.parse(date);
//			fromTS1 = new Timestamp(lFromDate1.getTime());
//
//		} catch (Exception e) {
//			LOGGER.error(e);
//		}
//		return fromTS1;
//	}
//
//	public static String convertdateFormatString(String dateString) {
//		String returnDateFormat = "";
//		try {
//			SimpleDateFormat datetimeFormatRead = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//			Date date = datetimeFormatRead.parse(dateString);
//			SimpleDateFormat convertDateformat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
//			returnDateFormat = convertDateformat.format(date);
//		} catch (Exception e) {
//			LOGGER.error(e);
//		}
//		return returnDateFormat;
//	}
//
//	public static boolean isNullOrBlank(String content) {
//		/// this will return false if conetnt is NULL or Blank, so it will easy
//		/// when we use this.
//		/// e.g do the action if field is Not Null and blank, in this case it
//		/// will return true so that you can process your work.
//
//		return content == null || content.equalsIgnoreCase("") ? true : false;
//	}
//
//	public static boolean isZeroOrNot(String content) {
//		/// this will return false if conetnt is zero, so it will easy when we
//		/// use this.
//		/// e.g do the action if field is Not zero, in this case it will return
//		/// true so that you can process your work.
//
//		return content.equalsIgnoreCase("0") ? true : false;
//	}
//
//	public String ConvertNumberToWords(int number) {
//
//		int n = 1;
//		int word;
//		string = "";
//		while (number != 0) {
//			switch (n) {
//			case 1:
//				word = number % 100;
//				pass(word);
//				if (number > 100 && number % 100 != 0) {
//					show("and ");
//				}
//				number /= 100;
//				break;
//
//			case 2:
//				word = number % 10;
//				if (word != 0) {
//					show(" ");
//					show(st2[0]);
//					show(" ");
//					pass(word);
//				}
//				number /= 10;
//				break;
//
//			case 3:
//				word = number % 100;
//				if (word != 0) {
//					show(" ");
//					show(st2[1]);
//					show(" ");
//					pass(word);
//				}
//				number /= 100;
//				break;
//
//			case 4:
//				word = number % 100;
//				if (word != 0) {
//					show(" ");
//					show(st2[2]);
//					show(" ");
//					pass(word);
//				}
//				number /= 100;
//				break;
//
//			case 5:
//				word = number % 100;
//				if (word != 0) {
//					show(" ");
//					show(st2[3]);
//					show(" ");
//					pass(word);
//				}
//				number /= 100;
//				break;
//
//			}
//			n++;
//		}
//		return string.toUpperCase() + " ONLY";
//	}
//
//	public void pass(int number) {
//		int word, q;
//		if (number < 10) {
//			show(st1[number]);
//		}
//		if (number > 9 && number < 20) {
//			show(st3[number - 10]);
//		}
//		if (number > 19) {
//			word = number % 10;
//			if (word == 0) {
//				q = number / 10;
//				show(st4[q - 2]);
//			} else {
//				q = number / 10;
//				show(st1[word]);
//				show(" ");
//				show(st4[q - 2]);
//			}
//		}
//	}
//
//	public void show(String s) {
//		String st;
//		st = string;
//		string = s;
//		string += st;
//	}
//
//	/**
//	 * To validate illegal characters
//	 *
//	 * @author varun
//	 * @paramtext , regex
//	 * @return boolean
//	 */
//	public static String validateTextForIllegalCharacters(String text, String regex) {
//		String allowed = regex;
//		if (text != null && !text.equalsIgnoreCase("")) {
//			boolean flag = text.matches(allowed);
//			if (!flag) {
//				// htmlinputtext.setValue("");
//				String notAllowed = text.replaceAll(allowed, "");
//				return notAllowed;
//			}
//		}
//
//		return null;
//
//	}
//
//	public static void print(List dataBeanList)
//			throws FileNotFoundException, IOException, PrinterException, JRException {
//		try {
//			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
//					.getResponse();
//			byte[] pdfByteArray = null;
//			JasperPrint jasperPrint = null;
//			ServletContext sercontext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
//					.getContext();
//			String urlPath = sercontext.getRealPath(File.separator);
//			final String masterReportFileName = urlPath + "ui" + File.separator + "printReceipt" + File.separator
//					+ "rptRecieptPrint.jrxml";
//			final String subReportFileName = urlPath + "ui" + File.separator + "printReceipt" + File.separator
//					+ "prntRecieptSubList.jrxml";
//			JasperDesign masterJasperDesign = null;
//			JasperDesign particularJasperDesign = null;
//			if (POSValidator.validate(masterReportFileName, "FILEPATH")) {
//				masterJasperDesign = JRXmlLoader.load(new FileInputStream(new File(masterReportFileName)));
//			}
//			if (POSValidator.validate(subReportFileName, "FILEPATH")) {
//				particularJasperDesign = JRXmlLoader.load(new FileInputStream(new File(subReportFileName)));
//			}
//
//			JasperReport jasperMasterReport = JasperCompileManager.compileReport(masterJasperDesign);
//			JasperReport jasperSubReport = JasperCompileManager.compileReport(particularJasperDesign);
//			Map<String, Object> parameters = new HashMap<String, Object>();
//			parameters.put("subreportParameter", jasperSubReport);
//			parameters.put("SUBREPORT_DIR", urlPath + "ui" + File.separator + "printReceipt" + File.separator);
//			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
//			jasperPrint = JasperFillManager.fillReport(jasperMasterReport, parameters, beanColDataSource);
//			JRDesignStyle normalStyle = new JRDesignStyle();
//			normalStyle.setName("Sans_Normal");
//			normalStyle.setDefault(true);
//			normalStyle.setFontName("DejaVu Sans");
//			normalStyle.setFontSize(8);
//			normalStyle.setPdfFontName("Helvetica");
//			normalStyle.setPdfEncoding("Cp1252");
//			normalStyle.setPdfEmbedded(false);
//			jasperPrint.addStyle(normalStyle);
//			JRDesignStyle boldStyle = new JRDesignStyle();
//			boldStyle.setName("Sans_Bold");
//			boldStyle.setFontName("DejaVu Sans");
//			boldStyle.setFontSize(8);
//			boldStyle.setBold(true);
//			boldStyle.setPdfFontName("Helvetica-Bold");
//			boldStyle.setPdfEncoding("Cp1252");
//			boldStyle.setPdfEmbedded(false);
//			jasperPrint.addStyle(boldStyle);
//			JRDesignStyle italicStyle = new JRDesignStyle();
//			italicStyle.setName("Sans_Italic");
//			italicStyle.setFontName("DejaVu Sans");
//			italicStyle.setFontSize(8);
//			italicStyle.setItalic(true);
//			italicStyle.setPdfFontName("Helvetica-Oblique");
//			italicStyle.setPdfEncoding("Cp1252");
//			italicStyle.setPdfEmbedded(false);
//			jasperPrint.addStyle(italicStyle);
//			pdfByteArray = JasperExportManager.exportReportToPdf(jasperPrint);
//			if (pdfByteArray != null) {
//				ServletOutputStream servletOutputStream = response.getOutputStream();
//				response.setContentType("application/pdf");
//				response.addHeader("Content-Disposition",
//						"attachment;zoom=1000=OpenActions;filename=" + receiptNo + ".pdf");
//				response.setContentLength(pdfByteArray.length);
//				servletOutputStream.write(pdfByteArray, 0, pdfByteArray.length);
//				FacesContext context = FacesContext.getCurrentInstance();
//				context.responseComplete();
//			}
//		} catch (JRException e) {
//			LOGGER.error(e);
//		} catch (VahanException e) {
//			LOGGER.error(e);
//		}
//	}
//}
