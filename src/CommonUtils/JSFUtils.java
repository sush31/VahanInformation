package CommonUtils;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItems;
import javax.faces.component.UISelectOne;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

public class JSFUtils {

	public static final int VM_MODELS_INDEX_OF_MAKER_ID = 2;
	private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
			.getSession(false);
	public static final String ERROR = "ERROR";
	public static final String INFO = "INFO";
	public static final String WARN = "WARN";
	public static final String FATAL = "FATAL";
	//private static final Logger LOGGER = Logger.getLogger(JSFUtils.class);
	private static SimpleDateFormat sdf;
	public static final String MD5 = "MD5";

	public static boolean validateDate(String date) {

		Pattern pattern;
		Matcher matcher;

		String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
		pattern = Pattern.compile(DATE_PATTERN);
		matcher = pattern.matcher(date);
		if (matcher.matches()) {

			matcher.reset();

			if (matcher.find()) {

				String day = matcher.group(1);
				String month = matcher.group(2);
				int year = Integer.parseInt(matcher.group(3));

				if (day.equals("31") && (month.equals("4") || month.equals("6") || month.equals("9")
						|| month.equals("11") || month.equals("04") || month.equals("06") || month.equals("09"))) {
					return false; // only 1,3,5,7,8,10,12 has 31 days
				} else if (month.equals("2") || month.equals("02")) {
					// leap year
					if (year % 4 == 0) {
						if (day.equals("30") || day.equals("31")) {
							return false;
						} else {
							return true;
						}
					} else {
						if (day.equals("29") || day.equals("30") || day.equals("31")) {
							return false;
						} else {
							return true;
						}
					}
				} else {
					return true;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Get the Selected key's Value Part from the Select One Menu
	 *
	 * @param som
	 * @return
	 */
	public static String getSelectedValue(HtmlSelectOneMenu som) {
		List<UIComponent> childComps = som.getChildren();
		UIComponent comp = null;
		ArrayList list;
		SelectItem item = null;
		String selectesKey = (String) som.getValue();
		String selectedVal = null;
		for (int i = 0; i < childComps.size(); i++) {
			comp = childComps.get(i);
			if (comp instanceof UISelectItems) {
				break;
			}
		}
		if (comp != null) {
			list = (ArrayList) ((UISelectItems) comp).getValue();
			for (int i = 0; i < list.size(); i++) {
				item = (SelectItem) list.get(i);
				if (((String) item.getValue()).equalsIgnoreCase(selectesKey)) {
					selectedVal = item.getLabel();
					return selectedVal;
				}
			}
		}
		return selectedVal;
	}

	/**
	 * Get the selected key from the Select One Menu
	 *
	 * @param som
	 * @return
	 */
	public static String getSelectedKey(HtmlSelectOneMenu som) {
		return (String) som.getValue();
	}

	public static String getDateInDD_MMM_YYYY(String strDate) throws ParseException {
		Date dt = nic.java.util.DateUtils.parseDate(strDate);
		SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy");
		return sd.format(dt);
	}

	/**
	 * Dump the given data in the console
	 *
	 * @param data
	 *            Given two dimensional table (array)
	 */
	public static void dump(String[][] data) {
		// Check input
		if (data == null) {
			return;
		}

		// Print the data
		for (int i = 0; i < data.length; i++) {
			//LOGGER.info(data[i][0] + "\t: " + data[i][1]);
		}
	}

	/**
	 * Check if there are values given in the "series-part" and "number-part"
	 * vehicle number text fields.
	 *
	 * @param tfSeriesPart
	 *            Vehicle Number - Series Part
	 * @param tfNumberPart
	 *            Vehicle Number - Number Part
	 *
	 * @return false if any of the textfields are empty else true
	 */
	/**
	 * Fill the Select One Menu
	 *
	 * @param data
	 * @return
	 */
	public static ArrayList fillSelectOne(String[] data) {
		ArrayList somList = new ArrayList();
		int dataLength = data.length;
		somList.add(new SelectItem("-1"));
		for (int i = 0; i < dataLength; i++) {
			somList.add(new SelectItem(data[i]));
		}
		return somList;
	}

	/**
	 * Fill the Select One Menu
	 *
	 * @param data
	 * @return
	 */
	public static ArrayList fillSelectOne(String[][] data) {
		int dataLength = data.length;
		ArrayList somList = new ArrayList();
		somList.add(new SelectItem("-1", ""));
		for (int i = 0; i < dataLength; i++) {
			somList.add(new SelectItem(data[i][0], data[i][1]));
		}
		return somList;
	}

	/**
	 * Get the Select One Menu component by ID and then set the value of the
	 * component
	 *
	 * @param evt
	 * @param uiSelectOneMenu
	 * @param value
	 */
	public static void setUISelectOne(ValueChangeEvent evt, String uiSelectOneMenu, String value) {
		UISelectOne temp = (UISelectOne) (evt.getComponent().findComponent(uiSelectOneMenu));
		temp.setValue(value);
	}

	/**
	 * Get the Select One Menu Component by ID
	 *
	 * @param evt
	 * @param uiSelectOneMenu
	 * @return
	 */
	public static String getUISelectOne(ValueChangeEvent evt, String uiSelectOneMenu) {
		UISelectOne temp = (UISelectOne) (evt.getComponent().findComponent(uiSelectOneMenu));
		return (String) temp.getValue();
	}

	/**
	 * Get the Input component by ID and then set the value of the component
	 *
	 * @param evt
	 * @param uiInput
	 * @param value
	 */
	public static void setUIInput(ValueChangeEvent evt, String uiInput, String value) {
		UIInput temp = (UIInput) (evt.getComponent().findComponent(uiInput));
		temp.setValue(value);
	}

	/**
	 * Get the Input component by ID
	 *
	 * @param evt
	 * @param uiInput
	 * @return
	 */
	public static String getUIInput(ValueChangeEvent evt, String uiInput) {
		UIInput temp = (UIInput) (evt.getComponent().findComponent(uiInput));
		return (String) temp.getValue();
	}

	/**
	 * Make a HashMap from an Array
	 *
	 * @param data
	 * @return
	 */
	public static HashMap arrayToHash(String[][] data) {
		HashMap tempMap = new HashMap();
		for (int i = 0; i < data.length; i++) {
			tempMap.put(data[i][0], data[i][1]);
		}
		return tempMap;
	}

	/**
	 * Method to check if the given string is a number or not.
	 *
	 * @param numToCheck
	 *            number to check
	 *
	 * @return "true" if the string is a number else "false".
	 */
	public static boolean isNumeric(String numToCheck) {
		boolean retValue = false;

		// Check if has some value
		if (numToCheck != null && numToCheck.matches("(-){0,1}[0-9]+")) {
			try {
				retValue = true;
			} catch (NumberFormatException ne) {
				//LOGGER.error(ne);
				// Do nothing in the catch as retValue is already false.
			}
		}

		return retValue;
	}
	// Added by Aftab @ 12-07-2016

	public static boolean isFloat(String numToCheck) {
		boolean retValue = false;

		// Check if has some value
		if (numToCheck != null && numToCheck.matches("[0-9.]+")) {
			try {
				retValue = true;
			} catch (NumberFormatException ne) {
				//LOGGER.error(ne);
				// Do nothing in the catch as retValue is already false.
			}
		}

		return retValue;
	}

	/**
	 * Returns boolean value, showing whether Input string is AlphaNumeric or
	 * not.
	 *
	 * @param str
	 *            String
	 *
	 * @return true if alphanumeric else false
	 */
	public static boolean isAlphaNumeric(String str) {
		boolean result = true;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			if (!(Character.isLetterOrDigit(str.charAt(i)))) {
				result = false;
				break;
			}
		}
		return result;
	}

	public static boolean isAlphaNumericWithSpace(String str) {
		boolean result = true;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			if (!(Character.isLetterOrDigit(str.charAt(i)) || Character.isSpaceChar(str.charAt(i)))) {
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * Returns boolean value, showing whether Input string is Alphabetic or not.
	 *
	 * @param str
	 *            String
	 *
	 * @return true if Alphabetic, else false
	 */
	public static boolean isAlphabet(String str) {
		boolean result = true;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			if (!(Character.isLetter(str.charAt(i)))) {
				result = false;
				break;
			}
		}
		return result;
	}

	public static boolean isAlphabetWithSpace(String str) {
		boolean result = true;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			if (!(Character.isLetter(str.charAt(i)) || Character.isSpaceChar(str.charAt(i)))) {
				result = false;
				break;
			}
		}
		return result;
	}

	/*
	 * address validator
	 */
	public static boolean isAddressValid(String compValue) {

		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[a-zA-Z0-9/.,&\\- ]*");
		}
		return false;
	}

	/*
	 * Financer Name validator
	 */
	public static boolean isFncrNameValid(String compValue) {
		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[a-zA-Z0-9/.,*&()\\-~ '`^ #@! % \\\\ : ]*");
		}
		return false;
	}

	/*
	 * Encdata validator
	 */
	public static boolean isEncdataValid(String compValue) {

		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[a-zA-Z0-9_&=?\\s]*");
		}
		return false;
	}

	/*
	 * Double Varification data validator
	 */
	public static boolean isDblVarificationEncdataValid(String compValue) {

		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[a-zA-Z0-9_&=?\\s]*");
		}
		return false;
	}

	/*
	 * URL validator
	 */
	public static boolean isUrlValid(String compValue) {
		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[\\w:./\\\\ -?=]*");
		}
		return false;
	}
	/*
	 * Email Validator
	 */

	public static boolean isEmailValid(String compValue) {
		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[\\w\\._@]*");
		}
		return false;
	}
	/*
	 * Date Validator
	 */

	public static boolean isDateValid(String compValue) {
		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[A-Za-z0-9-:./ ]*");
		}
		return false;
	}
	/*
	 * DATETIME validator
	 */

	public static boolean isDateTimeValid(String compValue) {

		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[A-Za-z0-9-:./ ]*");
		}
		return false;
	}

	/*
	 * IP address validator
	 */
	public static boolean isIPValid(String compValue) {

		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[0-9:.]*");
		}
		return false;
	}

	/**
	 * Get a HashMap Object from String, Here it splits the String with special
	 * symbols '|' and '='
	 *
	 * @param str
	 * @return
	 */
	public static HashMap getHashMapFromString(String str) {
		HashMap map = new HashMap();
		String tokens[] = str.split("\\|");
		for (int i = 0; i < tokens.length; i++) {
			String subTokens[] = tokens[i].split("=");
			if (subTokens.length > 1) {
				map.put(subTokens[0], subTokens[1]);
			}
		}
		return map;
	}

	// public static String md5_hex(String passwd) {
	// String rtnMd5 = "";
	// if (passwd != null && !passwd.equalsIgnoreCase("")) {
	// try {
	// rtnMd5 = MD5(passwd);
	// } catch (Exception ex) {
	// }
	// }
	// return rtnMd5;
	// }
	// add spl

	// public static int generateHashCode(String str) throws
	// NoSuchAlgorithmException, UnsupportedEncodingException {
	// int hashCode = 0;
	// if (str != null && str.length() > 0) {
	// String md5String = MD5(str);
	// long asciiSum = getAsciiSum(md5String);
	// hashCode = (int) (asciiSum % 10);
	// }
	// return hashCode;
	// }
	// end spl

	// public static String MD5(String text) throws NoSuchAlgorithmException,
	// UnsupportedEncodingException {
	// MessageDigest md;
	// md = MessageDigest.getInstance(MD5);
	// byte[] md5hash = null;
	// md.update(text.getBytes("iso-8859-1"), 0, text.length());
	// md5hash = md.digest();
	// return convertToHex(md5hash);
	// }

	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9)) {
					buf.append((char) ('0' + halfbyte));
				} else {
					buf.append((char) ('a' + (halfbyte - 10)));
				}
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}
	// add spl

	public static long getAsciiSum(String str) {
		long asciiSum = 0;
		if (str != null && str.length() > 0) {
			for (int i = 0; i < str.length(); i++) {
				asciiSum = asciiSum + str.charAt(i);

			}
		}
		return asciiSum;
	}
	// end spl

	public static String convertSanitizedStr(String str) {
		return (str.replaceAll("[\'\"([%])<>?:=&#$*]", ""));
	}

	public static String convertAddressSanitizedStr(String str) {
		return (str.replaceAll("[\'\"([%])<>?:=&#$*]", ""));
	}

	public static String convertSanitizedStr(Object obj) {
		String str = "";
		if (obj == null) {
			str = "";
		} else {
			str = obj.toString();
		}
		return (str.replaceAll("[\'\"([%])<>?:=&#$*]", ""));
	}

	public static String trim(String str) {
		String trim = "";
		if (str == null || str.equalsIgnoreCase("")) {
			trim = "";
		} else {
			trim = str.trim();
		}
		return trim;
	}

	public static Date getLastDateOfTheMonth(Date dt) {
		Date rtnDate = null;
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setLenient(false);
		if (dt != null) {
			cal.setTime(dt);
			int lastDate = cal.getActualMaximum(Calendar.DATE);
			cal.set(Calendar.DATE, lastDate);
			rtnDate = cal.getTime();
		}
		return rtnDate;
	}

	public static java.sql.Date convertStringToSQLDate(String date) {
		java.sql.Date dt = null;

		if (date != null && date.length() > 0) {
			java.util.Date utilDate = getStringToDate(date);
			if (utilDate != null) {
				long t = utilDate.getTime();
				dt = new java.sql.Date(t);
			}
		}
		return dt;
	}

	public static Date getStringToDate(String strDt) {
		// return variable
		Date dt = null;
		// Constructs a SimpleDateFormat using the given pattern
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			// Parses the given string to a date
			dt = sdf.parse(strDt);
		} catch (ParseException pex) {
			//LOGGER.error(pex);
		}
		return dt;
	}

	public static Date getStringToDateyyyyMMdd(String strDt) {
		// return variable
		Date dt = null;
		// Constructs a SimpleDateFormat using the given pattern
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// Parses the given string to a date
			dt = sdf.parse(strDt);
		} catch (ParseException pex) {
			//LOGGER.error(pex);
		}
		return dt;
	}

	/**
	 * Method to convert date into 'dd-MMM-yyyy' format
	 *
	 * @param dt
	 * @return
	 */
	public static String convertToStandardDateFormat(Date dt) {
		String rtnDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

		if (dt != null) {
			rtnDate = sdf.format(dt);
		}
		return rtnDate;
	}
	/*
	 * Methods Copied from the National Permit Software
	 *
	 * Ambrish 27 May 2011
	 */
	// ambrish start

	public static String makeCapital(String str) {
		String rtnStr = "";
		if (str != null && str.length() > 0) {
			rtnStr = str.toUpperCase();
		}
		return rtnStr;
	}

	public static boolean validateRegNo(String regNo) {
		return regNo.matches("[a-yA-Y][a-zA-Z0-9 ]*");
	}

	public static boolean validateTransId(String regNo) {
		return regNo.matches("[0-9]+");
	}

	public static void showMessage(String msg) {
		FacesMessage fm = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage(msg, fm);
	}

	public static boolean checkFullName(String strVal) {
		return strVal.matches("[a-zA-Z ]*");

	}

	public static boolean validateAddress(String address) {
		return address.matches("`");
	}

	public static boolean checkPinCode(String strVal) {
		return strVal.matches("[0-9]{6,7}");

	}

	public static boolean validatePhone(String phone) {
		return phone.matches("[0-9]*");
	}

	public static boolean checkMobileNumber(String strVal) {
		return strVal.matches("[0-9]{10}");

	}

	public static boolean checkStringDigit(String strVal) {
		return strVal.matches("[a-zA-Z0-9 ]*");

	}

	public static boolean checkUserName(String strVal) {
		return strVal.matches("[a-zA-Z0-9.]*");

	}

	public static String convertMessage(String strVal) {

		return ((strVal.substring(strVal.indexOf("Detail:"))).replace("Detail: Key ", ""));

	}

	public static boolean validateUsrRole(String strVal) {
		boolean flag = false;
		if (strVal.equalsIgnoreCase("RTO")) {
			flag = true;
		} else if (strVal.equalsIgnoreCase("POLICE")) {
			flag = true;
		} else if (strVal.equalsIgnoreCase("ADMINISTRATOR")) {
			flag = true;
		}
		return flag;
	}

	public static void setFacesMessage(String messgaeSummary, String componentID, String severityCode) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(getMessageSeverity(severityCode), messgaeSummary, null);
		context.addMessage(componentID, message);

	}

	private static FacesMessage.Severity getMessageSeverity(String severityCode) {
		if (JSFUtils.ERROR.equalsIgnoreCase(severityCode)) {
			return FacesMessage.SEVERITY_ERROR;
		} else if (JSFUtils.INFO.equalsIgnoreCase(severityCode)) {
			return FacesMessage.SEVERITY_INFO;
		} else if (JSFUtils.WARN.equalsIgnoreCase(severityCode)) {
			return FacesMessage.SEVERITY_WARN;
		} else if (JSFUtils.FATAL.equalsIgnoreCase(severityCode)) {
			return FacesMessage.SEVERITY_FATAL;
		} else {
			return FacesMessage.SEVERITY_ERROR;
		}
	}

	public static ArrayList<SelectItem> fillSelectOne(String[][] data, String defaultCaseValue) {
		int dataLength = data.length;
		ArrayList<SelectItem> somList = new ArrayList<>();
		somList.add(new SelectItem("-1", defaultCaseValue));
		for (int i = 0; i < dataLength; i++) {
			somList.add(new SelectItem(data[i][0], data[i][1]));
		}
		return somList;
	}

	/**
	 *
	 * @param summary
	 *            is a String messages and this message will be on the Header of
	 *            the Dialog
	 * @param details
	 *            is a String Messages and this message will be in the container
	 *            of the Dialog
	 * @param severity
	 *            is used for displaying type of messages like info,warn,fatal
	 *            etc.Example FacesMessage.SEVERITY_WARN
	 *
	 */
	public static void showMessagesInDialog(String summary, String details, Severity severity) {
		RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(severity, summary, details));
	}// end of showMessagesInDialog

	/**
	 *
	 * @param key
	 *            It is used for getting key from properties file.
	 * @param var
	 *            It is used to get particular variable for particular property
	 *            file.
	 * @return It Will return value against the key
	 *
	 */
	public static String getResource(String key, String var) {

		String result = null;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle bundle = context.getApplication().getResourceBundle(context, var);
			result = bundle.getString(key);
		} catch (MissingResourceException e) {
			//LOGGER.error(e);
			result = "???" + key + "??? not found";
		}
		return result;
	}// end of getResource

	public static String getIpPath() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String contextPath = request.getContextPath();
		StringBuffer requestURL = request.getRequestURL();
		String ipPath = requestURL.substring(0, requestURL.indexOf(contextPath)) + contextPath;
		return ipPath;
	}

	public static String generateRandomString(int length) {
		SecureRandom rand = new SecureRandom();
		StringBuffer out = new StringBuffer();
		String CharSet = "abcdefghijklmnopqrstuvwxyz0123456789";
		for (int i = 0; i < length; i++) {
			double val = rand.nextDouble() * CharSet.length();
			out.append(CharSet.charAt((int) val));
		}

		return out.toString();
	}

	/*
	 * Added by Aftab @ 12-07-2016 Financer Name validator
	 */
	public static boolean isUserNameValid(String compValue) {

		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[a-zA-Z0-9/.,&\\- ]*");
		}
		return false;
	}

	/*
	 * chasis no, engine no and username validator
	 */
	public static boolean isAlphaNumWithSpecialChar(String compValue) {
		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[a-zA-Z0-9/.,~`!+\\*&@#\\-_| \\[\\]()\\\\]*");
			// [a-zA-Z0-9.,~`!\\/+&@#\\\\\\-_|\\[\\])( ]
		}
		return false;
	}

	// For Audit
	public static synchronized Date parse1(String strDate) throws ParseException {
		sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.parse(strDate);

	}

	public static Date parse2(String strDate) throws ParseException {
		synchronized (sdf) {
			sdf = new SimpleDateFormat("dd-MM-yyyy");
			return sdf.parse(strDate);
		}
	}

	public static boolean isDespStr(String compValue) {
		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[a-zA-Z0-9/:.,;+&?@#<>=\\-_| ()]*");
		}
		return false;
	}

	// filePath
	public static boolean isFilePathValid(String compValue) {
		if (compValue != null && !compValue.equalsIgnoreCase("")) {
			return compValue.matches("[a-zA-Z0-9/.:;,+&@#\\\\-_| \\\\[\\\\]()]*");
		}
		return false;
	}

	public static Date getStringToDateddMMMyyyy(String strDt) {
		// return variable
		Date dt = null;
		// Constructs a SimpleDateFormat using the given pattern
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			// Parses the given string to a date
			dt = sdf.parse(strDt);
		} catch (ParseException pex) {
		}
		return dt;
	}

	public static String getRelevantLangMsg(String proerty_key) {

		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

		if (locale != null) {
			ResourceBundle labels = ResourceBundle.getBundle("resources.Language", locale);
			if (labels != null) {

				return labels.getString(proerty_key);
			}
		}
		return proerty_key;
	}

}
