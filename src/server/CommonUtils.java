///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package nic.vahan.server;
//
//import java.io.Serializable;
//import java.security.SecureRandom;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpSession;
//import javax.sql.RowSet;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//import org.codehaus.jackson.map.ObjectMapper;
//
//import nic.rto.vahan.common.VahanException;
//import nic.transport.homologation.web.service.InsuranceInfoDobj;
//import nic.vahan.CommonUtils.FormulaUtils;
//import nic.vahan.CommonUtils.VehicleParameters;
//import nic.vahan.common.jsf.utils.JSFUtils;
//import nic.vahan.common.jsf.utils.validators.POSValidator;
//import nic.vahan.db.TableConstants;
//import nic.vahan.db.TableList;
//import nic.vahan.db.connection.TransactionManagerInterface;
//import nic.vahan.db.connection.TransactionManagerReadOnly;
//import nic.vahan.form.dobj.TmConfigurationDobj;
//import nic.vahan.form.dobj.UserValid_ServiceBasedDobj;
//import nic.vahan.form.dobj.common.DMSResponseMaterDO;
//import nic.vahan.utils.DateUtil;
//import resources.ApplicationConfig;
//
///**
// *
// * @author sushil
// */
//public class CommonUtils implements Serializable {
//
//	//private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CommonUtils.class);
//	private static Pattern pattern;
//	private static Matcher matcher;
//	private static final String PWD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
//	public static final String MD5 = "MD5";
//	//private static TmConfigurationDobj tmConfigurationDobj = null;
//
//	// added by Nitin : 16 jan 2014 for generating random throught the
//	// application
//	public static synchronized String getRanomNumber() {
//
//		String randonNumber = "";
//		SecureRandom ran = new SecureRandom();
//		int num = ran.nextInt();
//		num = num + 1000;
//		if (num < 0) {
//			num = num * (-1);
//		}
//
//		randonNumber = String.valueOf(num);
//		return randonNumber;
//	}
//
//	/**
//	 * Checks whether the string is blank or 'null'.
//	 *
//	 * @return true if string is null or blank else false
//	 * @param strCheck string to be verified
//	 */
//	public static boolean isNullOrBlank(String strCheck) {
//		if ((null == strCheck) || ("null".equalsIgnoreCase(strCheck))
//				|| (TableConstants.BLANK_STRING.equalsIgnoreCase(strCheck)) || (strCheck.trim().length() <= 0)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public static boolean isMapNullOrEmpty(Map strCheck) {
//		if ((null == strCheck) || (strCheck.isEmpty())) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public static boolean isListNullOrEmpty(List strCheck) {
//		if ((null == strCheck) || (strCheck.isEmpty())) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * Checks whether the string is blank or 'null' or "-1"
//	 *
//	 * @return true if string is null or blank or "-1" else false
//	 * @param strCheck string to be verified
//	 */
//	public static boolean isNullOrBlank_Negative(String strCheck) {
//		if ((strCheck == null) || ("null".equalsIgnoreCase(strCheck)) || ("".equalsIgnoreCase(strCheck))
//				|| (strCheck.trim().length() <= 0) || ("-1".equalsIgnoreCase(strCheck))) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public static String vahanEncryption(String strVal) {
//		StringBuffer encVal = new StringBuffer();
//		char ch[] = strVal.toCharArray();
//		for (char c : ch) {
//			encVal.append(Integer.toHexString((byte) c));
//		}
//		return encVal.toString();
//	}
//
//	public static String vahanDecryption(String strVal) {
//
//		StringBuilder sb = new StringBuilder();
//		try {
//			for (int i = 0; i < strVal.length() - 1; i += 2) {
//				String output = strVal.substring(i, (i + 2));
//				int decimal = Integer.parseInt(output, 16);
//				sb.append((char) decimal);
//			}
//		} catch (NumberFormatException e) {
//			LOGGER.error(e.getMessage());
//		}
//		return sb.toString();
//
//	}
//
//	// public static int generateHashCode(String str) throws
//	// NoSuchAlgorithmException, UnsupportedEncodingException {
//	// int hashCode = 0;
//	// if (str != null && str.length() > 0) {
//	// String md5String = MD5(str);
//	// long asciiSum = getAsciiSum(md5String);
//	// hashCode = (int) (asciiSum % 10);
//	// }
//	// return hashCode;
//	// }
//
//	public static long getAsciiSum(String str) {
//		long asciiSum = 0;
//		if (str != null && str.length() > 0) {
//			for (int i = 0; i < str.length(); i++) {
//				asciiSum = asciiSum + str.charAt(i);
//			}
//		}
//		return asciiSum;
//	}
//
//	// public static String MD5(String text) throws NoSuchAlgorithmException,
//	// UnsupportedEncodingException {
//	// MessageDigest md;
//	// byte[] md5hash;
//	// md = MessageDigest.getInstance(MD5);
//	// if (md != null) {
//	// md5hash = new byte[32];
//	// md.update(text.getBytes("iso-8859-1"), 0, text.length());
//	// }
//	// md5hash = md.digest();
//	// return convertToHex(md5hash);
//	// }
//
//	private static String convertToHex(byte[] data) {
//		StringBuffer buf = new StringBuffer();
//		for (int i = 0; i < data.length; i++) {
//			int halfbyte = (data[i] >>> 4) & 0x0F;
//			int two_halfs = 0;
//			do {
//				if ((0 <= halfbyte) && (halfbyte <= 9)) {
//					buf.append((char) ('0' + halfbyte));
//				} else {
//					buf.append((char) ('a' + (halfbyte - 10)));
//				}
//				halfbyte = data[i] & 0x0F;
//			} while (two_halfs++ < 1);
//		}
//		return buf.toString();
//	}
//
//	public static boolean validatePassword(String password) {
//		pattern = Pattern.compile(PWD_PATTERN);
//		matcher = pattern.matcher(password);
//		return matcher.matches();
//	}
//
//	public static String getPreviousWebSiteURLasPerState(String stateCD, boolean isTax, boolean isFee,
//			boolean isPermit) {
//		stateCD = "NA";
//		String url = null;
//		if (isTax) {
//			switch (stateCD) {
//			case "UK":
//				url = "https://vahan.uk.gov.in/uk";
//				break;
//			case "JH":
//				url = "https://vahan.jhr.nic.in/jh";
//				break;
//
//			case "UP":
//				url = "https://vahan.up.nic.in/up";
//				break;
//			case "HR":
//				url = "https://haryanatransport.gov.in/srservices";
//				break;
//			case "DL":
//				url = "http://vahan.dl.nic.in/dl";
//				break;
//			default:
//				url = " the previous website.";
//
//			}
//		}
//		if (isFee) {
//			switch (stateCD) {
//			case "UK":
//				url = "https://vahan.uk.gov.in/uk";
//				break;
//			case "JH":
//				url = "https://vahan.jhr.nic.in/jh";
//				break;
//
//			case "UP":
//				url = "https://vahan.up.nic.in/up";
//				break;
//			case "HR":
//				url = "https://haryanatransport.gov.in/srservices";
//				break;
//			case "DL":
//				url = "http://vahan.dl.nic.in/dl";
//				break;
//			default:
//				url = " the previous website.";
//
//			}
//		}
//		return url;
//	}
//
//	public static void invalidateJSFSession(HttpSession session) {
//		try {
//			if (session != null && !session.isNew()) {
//				session.invalidate();
//			}
//		} catch (IllegalStateException e) {
//			LOGGER.error(e.getMessage());
//		}
//	}
//
//	public static String getUserMobileNo(String regnNo) throws VahanException {
//		String mobileNo = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		RowSet rs = null;
//		String whereiam = "getUserMobileNo";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			String sql = "select mobile_no from " + TableList.VT_OWNER_IDENTIFICATION + " where regn_no=? ";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				mobileNo = rs.getString("mobile_no");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return mobileNo;
//	}
//
//	// public static long getUserServiceCharge(String desp) {
//	//
//	// long userServiceCharge = 0;
//	// if (!desp.contains("<slno")) {
//	// return userServiceCharge;
//	// }
//	// int index1 = desp.indexOf("USER_CHARGE");
//	// int index2 = desp.lastIndexOf("USER_CHARGE");
//	// int lastInd = desp.lastIndexOf(">><");
//	// if (index1 == index2) {
//	// // //System.out.println("index1 = : " + index1+" index2 = : " + index2+"
//	// Last index>>< = : " + lastInd);
//	// String serStr = desp.substring(index1, lastInd);
//	// int indxEqul = serStr.indexOf("=");
//	// String usrChrge = serStr.substring(indxEqul + 1);
//	// if (!CommonUtils.isNullOrBlank(usrChrge)) {
//	//// userServiceCharge = Double.valueOf(usrChrge).longValue();
//	// userServiceCharge =Long.valueOf(usrChrge);
//	// }
//	// //LOGGER.info("serCharge = " + userServiceCharge);
//	// }
//	// return userServiceCharge;
//	// }
//
//	public static long getUserServiceCharge(String desp) {
//
//		long userServiceCharge = 0;
//		// change by vivek
//		if (!desp.contains("<slno")) {
//			return userServiceCharge;
//		} else if (desp.contains("###")) {
//			desp = desp.substring(0, desp.indexOf("###"));
//		}
//		// change by vivek
//		int index1 = desp.indexOf("USER_CHARGE");
//		int index2 = desp.lastIndexOf("USER_CHARGE");
//		int lastInd = desp.lastIndexOf(">><");
//		if (index1 == index2) {
//			// //System.out.println("index1 = : " + index1+" index2 = : " +
//			// index2+" Last index>>< = : " + lastInd);
//			String serStr = desp.substring(index1, lastInd);
//			int indxEqul = serStr.indexOf("=");
//			String usrChrge = serStr.substring(indxEqul + 1);
//			if (!CommonUtils.isNullOrBlank(usrChrge)) {
//				// userServiceCharge = Double.valueOf(usrChrge).longValue();
//				userServiceCharge = Long.valueOf(usrChrge);
//			}
//			// LOGGER.info("serCharge = " + userServiceCharge);
//		}
//		return userServiceCharge;
//	}
//
//	public static long getUserServiceChargeOnlyForNoFeesFlow(String desp) {
//
//		long userServiceCharge = 0;
//
//		if (!desp.contains("USER_CHARGE")) {
//			return userServiceCharge;
//		}
//		int index1 = desp.indexOf("USER_CHARGE");
//		int index2 = desp.lastIndexOf("USER_CHARGE");
//		int lastInd = desp.lastIndexOf(">>");
//
//		if (index1 == index2) {
//			// //System.out.println("index1 = : " + index1+" index2 = : " +
//			// index2+" Last index>>< = : " + lastInd);
//			String serStr = desp.substring(index1, lastInd);
//			int indxEqul = serStr.indexOf("=");
//			String usrChrge = serStr.substring(indxEqul + 1);
//			if (!CommonUtils.isNullOrBlank(usrChrge)) {
//				// userServiceCharge = Double.valueOf(usrChrge).longValue();
//				userServiceCharge = Long.valueOf(usrChrge);
//			}
//			// LOGGER.info("serCharge = " + userServiceCharge);
//		}
//		return userServiceCharge;
//	}
//
//	public static long getTransactionFee(String desp) {
//
//		long transFee = 0;
//		if (!desp.contains("<slno")) {
//			return transFee;
//		}
//		if (!desp.contains("TRANS_FEE")) {
//			return transFee;
//		}
//		int index1 = desp.indexOf("TRANS_FEE");
//		int index2 = desp.lastIndexOf("TRANS_FEE");
//		int lastInd = desp.lastIndexOf(">><<USER_CHARGE");
//		if (index1 == index2) {
//			// //System.out.println("index1 = : " + index1+" index2 = : " +
//			// index2+" Last index>>< = : " + lastInd);
//			String serStr = desp.substring(index1, lastInd);
//			int indxEqul = serStr.indexOf("=");
//			String transFee1 = serStr.substring(indxEqul + 1);
//			if (!CommonUtils.isNullOrBlank(transFee1)) {
//				// transFee = Double.valueOf(transFee1).longValue();
//				transFee = Long.valueOf(transFee1);
//			}
//			// LOGGER.info("serCharge = " + transFee);
//		}
//		return transFee;
//	}
//
//	public static long getTransactionFeeOnlyForNoFeesFlow(String desp) {
//
//		long userServiceCharge = 0;
//
//		if (!desp.contains("TRANS_FEE")) {
//			return userServiceCharge;
//		}
//
//		int index1 = desp.indexOf("TRANS_FEE");
//		int index2 = desp.lastIndexOf("TRANS_FEE");
//		int lastInd = desp.indexOf(">><<USER_CHARGE");
//
//		if (index1 == index2) {
//			// //System.out.println("index1 = : " + index1+" index2 = : " +
//			// index2+" Last index>>< = : " + lastInd);
//			String serStr = desp.substring(index1, lastInd);
//			int indxEqul = serStr.indexOf("=");
//			String usrChrge = serStr.substring(indxEqul + 1);
//			if (!CommonUtils.isNullOrBlank(usrChrge)) {
//				// userServiceCharge = Double.valueOf(usrChrge).longValue();
//				userServiceCharge = Long.valueOf(usrChrge);
//			}
//			// LOGGER.info("serCharge = " + userServiceCharge);
//		}
//		return userServiceCharge;
//	}
//
//	public static boolean checkNumber(String number) {
//		boolean flag = true;
//		if (!number.matches("[a-zA-Z]")) {
//			flag = false;
//		}
//		return flag;
//	}
//
//	public static boolean checkNumberForRegion(String number) {
//		return number.matches("[0-9]+(\\.[0-9][0-9]?)?");
//	}
//
//	public static DMSResponseMaterDO getDMSResponse(String applNo, int pur_CdDMS, String stateCD, String pmt_type,
//			String trans_type, String authMode, int veh_class) throws VahanException {
//		DMSResponseMaterDO dmsResponse = null;
//		String url = "";
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!CommonUtils.isNullOrBlank(pmt_type) && CommonUtils.checkNumberForRegion(pmt_type)) {
//				if (Integer.valueOf(pmt_type) < 1) {
//					pmt_type = "0";
//				}
//
//			} else {
//				pmt_type = "0";
//			}
//			if (!isTestingEnviroment) {
//				url = "http://vahan.parivahan.gov.in/dms-app/dms-vahan-citizen-conf?applno=" + applNo + "&state="
//						+ stateCD + "&purpose=" + pur_CdDMS + "&permit=" + pmt_type + "&transType=" + trans_type
//						+ "&authMode=" + authMode + "&prj_name=" + TableConstants.PROJECT_NAME + "&vehClass=" + veh_class
//						+ "&j_key=vVl/Az1yGsjOAG18WDeScg==&j_securityKey=6D6C069D681B40DBF95CAD7B3ED71BE1A46F0A7036BC711860B00BAAE50FE8A4!TZFIMZbTiUtqXMfARJ1DGgyNicWFwYkwTA0ip/Q8Wns=";
//				/*
//				 * url = "http://vahan.parivahan.gov.in/dms-app/doc-upload-conf?applno=" +
//				 * applNo + "&state=" + stateCD + "&purpose=" + pur_CdDMS +"&permit="+pmt_type +
//				 * "&transType="+trans_type +
//				 * "&j_key=vVl/Az1yGsjOAG18WDeScg==&j_securityKey=6D6C069D681B40DBF95CAD7B3ED71BE1A46F0A7036BC711860B00BAAE50FE8A4!TZFIMZbTiUtqXMfARJ1DGgyNicWFwYkwTA0ip/Q8Wns=";
//				 */
//
//			} else {
//				// url =
//				// "https://staging.parivahan.gov.in/dms-app/doc-upload-conf?applno="
//				// + applNo + "&state=" + stateCD + "&purpose="
//				// + pur_CdDMS + "&permit=" + pmt_type + "&transType=" +
//				// trans_type + "&authMode=" + authMode
//				// +
//				// "&j_key=vVl/Az1yGsjOAG18WDeScg==&j_securityKey=6D6C069D681B40DBF95CAD7B3ED71BE1A46F0A7036BC711860B00BAAE50FE8A4!TZFIMZbTiUtqXMfARJ1DGgyNicWFwYkwTA0ip/Q8Wns=";
//				url = "http://vahan.parivahan.gov.in/dms-app/dms-vahan-citizen-conf?applno=" + applNo + "&state="
//						+ stateCD + "&purpose=" + pur_CdDMS + "&permit=" + pmt_type + "&transType=" + trans_type
//						+ "&authMode=" + authMode + "&prj_name=" + TableConstants.PROJECT_NAME + "&vehClass=" + veh_class
//						+ "&j_key=vVl/Az1yGsjOAG18WDeScg==&j_securityKey=6D6C069D681B40DBF95CAD7B3ED71BE1A46F0A7036BC711860B00BAAE50FE8A4!TZFIMZbTiUtqXMfARJ1DGgyNicWFwYkwTA0ip/Q8Wns=";
//
//			}
//
//			DefaultHttpClient client = new DefaultHttpClient();
//			HttpGet request = new HttpGet(url);
//			// System.out.println("\n chkUploadDocsByDMSResponse url : " + url);
//			LOGGER.info("URL : " + url);
//			String encResponse = "";
//			// //System.out.println("\n encResponse : " + encResponse);
//			HttpResponse httpresponse = client.execute(request);
//			HttpEntity entity = httpresponse.getEntity();
//			String response = EntityUtils.toString(entity);
//			LOGGER.info("responsethroughDMS " + response);
//			ObjectMapper mapper = new ObjectMapper();
//			dmsResponse = mapper.readValue(response, DMSResponseMaterDO.class);
//			LOGGER.info("responsethroughDMS " + dmsResponse);
//			LOGGER.info("responsethroughDMS dmsResponse.getNonUploadedList : " + dmsResponse.getMandatoryList());
//			// System.out.println("\n dmsResponse : " + dmsResponse);
//
//		} catch (Exception e) {
//			LOGGER.error("DMS issue " + e.getMessage());
//			throw new VahanException("Unable to get Details from DMS Service...");
//		}
//		return dmsResponse;
//
//	}
//
//	public boolean checkforPGIrcpt_dt(String state_cd) {
//		String pgistate[] = { "OR" };
//		for (String state : pgistate) {
//			if (state_cd.equalsIgnoreCase(state)) {
//				return true;
//			}
//
//		}
//		return false;
//	}
//
//	public static LinkedList<UserValid_ServiceBasedDobj> getValidateUserDtls(String state_cd, String table_name) {
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String Query;
//		UserValid_ServiceBasedDobj obj = null;
//		LinkedList<UserValid_ServiceBasedDobj> ll = null;
//		RowSet rs;
//		try {
//			tmgr = new TransactionManagerReadOnly("getValidateUserDtls");
//			Query = "SELECT ins_lgn_chk,fit_lgn_chk,tax_lgn_chk,skip_ins_lgn_chk,skip_tax_lgn_chk,skip_fit_lgn_chk FROM "
//					+ table_name + " where state_cd = ?";
//			ps = tmgr.prepareStatement(Query);
//			ps.setString(1, state_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			ll = new LinkedList<UserValid_ServiceBasedDobj>();
//			if (rs.next()) {
//				obj = new UserValid_ServiceBasedDobj();
//				obj.setFit_lgn_chk(rs.getString("fit_lgn_chk"));
//				obj.setIns_lgn_chk(rs.getString("ins_lgn_chk"));
//				obj.setTax_lgn_chk(rs.getString("tax_lgn_chk"));
//				obj.setSkip_ins_lgn_chk(rs.getString("skip_ins_lgn_chk"));
//				obj.setSkip_fit_lgn_chk(rs.getString("skip_fit_lgn_chk"));
//				obj.setSkip_tax_lgn_chk(rs.getString("skip_tax_lgn_chk"));
//				obj.setState_cd(state_cd);
//			}
//			if (obj != null) {
//				ll.add(obj);
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return ll;
//	}
//
//	public static String validateUser(String regn_no, String chasis_no, UserValid_ServiceBasedDobj dobj,
//			InsuranceInfoDobj insInfoDobj) throws VahanException {
//		String msg = "";
//		boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//				.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//
//		String sql = "select * from onlinepermit.validate_user(?,?,?) ";
//
//		TransactionManagerReadOnly tmgr = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("validateUserfunction");
//			PreparedStatement ps = tmgr.prepareStatement(sql);
//			boolean befrexpiryfit_check = !CommonUtils.isNullOrBlank(dobj.getFit_lgn_chk());
//			boolean befrexpiryIns_check = !CommonUtils.isNullOrBlank(dobj.getIns_lgn_chk());
//			boolean befrexpirytax_check = !CommonUtils.isNullOrBlank(dobj.getTax_lgn_chk());
//			boolean Skip_fit_lgn_chk = !CommonUtils.isNullOrBlank(dobj.getSkip_fit_lgn_chk())
//					&& dobj.getSkip_fit_lgn_chk().equalsIgnoreCase("TRUE");
//			boolean Skip_tax_lgn_chk = !CommonUtils.isNullOrBlank(dobj.getSkip_tax_lgn_chk())
//					&& dobj.getSkip_tax_lgn_chk().equalsIgnoreCase("TRUE");
//			boolean Skip_ins_lgn_chk = !CommonUtils.isNullOrBlank(dobj.getSkip_ins_lgn_chk())
//					&& dobj.getSkip_ins_lgn_chk().equalsIgnoreCase("TRUE");
//			if (!CommonUtils.isNullOrBlank(dobj.getSkip_fit_lgn_chk())
//					&& (dobj.getSkip_fit_lgn_chk().contains("<") && dobj.getSkip_fit_lgn_chk().contains(">"))) {
//				Skip_fit_lgn_chk = FormulaUtils.isCondition(
//						FormulaUtils.replaceTagPermitValues(dobj.getSkip_fit_lgn_chk(), dobj.getParameter()),
//						"Validation of fitness check skipped or not");
//			}
//
//			if (!CommonUtils.isNullOrBlank(dobj.getSkip_ins_lgn_chk())
//					&& (dobj.getSkip_ins_lgn_chk().contains("<") && dobj.getSkip_ins_lgn_chk().contains(">"))) {
//				Skip_ins_lgn_chk = FormulaUtils.isCondition(
//						FormulaUtils.replaceTagPermitValues(dobj.getSkip_ins_lgn_chk(), dobj.getParameter()),
//						"Validation of insurance check skipped or not");
//
//			}
//			if (!CommonUtils.isNullOrBlank(dobj.getSkip_tax_lgn_chk())
//					&& (dobj.getSkip_tax_lgn_chk().contains("<") && dobj.getSkip_tax_lgn_chk().contains(">"))) {
//				Skip_tax_lgn_chk = FormulaUtils.isCondition(
//						FormulaUtils.replaceTagPermitValues(dobj.getSkip_tax_lgn_chk(), dobj.getParameter()),
//						"Validation of tax check skipped or not");
//			}
//
//			if (befrexpiryfit_check && (dobj.getFit_lgn_chk().contains("<") && dobj.getFit_lgn_chk().contains(">"))) {
//				dobj.setFit_lgn_chk(FormulaUtils.getReturnValue(
//						FormulaUtils.replaceTagPermitValues(dobj.getFit_lgn_chk(), dobj.getParameter())));
//
//			}
//			if (befrexpirytax_check && (dobj.getTax_lgn_chk().contains("<") && dobj.getTax_lgn_chk().contains(">"))) {
//				dobj.setTax_lgn_chk(FormulaUtils.getReturnValue(
//						FormulaUtils.replaceTagPermitValues(dobj.getTax_lgn_chk(), dobj.getParameter())));
//			}
//			if (befrexpiryIns_check && (dobj.getIns_lgn_chk().contains("<") && dobj.getIns_lgn_chk().contains(">"))) {
//				dobj.setIns_lgn_chk(FormulaUtils.getReturnValue(
//						FormulaUtils.replaceTagPermitValues(dobj.getIns_lgn_chk(), dobj.getParameter())));
//
//			}
//			// for msg either one month or normal
//			befrexpirytax_check = befrexpirytax_check && Integer.parseInt(dobj.getTax_lgn_chk()) != 0;
//			befrexpiryfit_check = befrexpiryfit_check && Integer.parseInt(dobj.getFit_lgn_chk()) != 0;
//			befrexpiryIns_check = befrexpiryIns_check && Integer.parseInt(dobj.getIns_lgn_chk()) != 0;
//
//			if (POSValidator.validate(regn_no, "AN") && POSValidator.validate(chasis_no, "ANWS")) {
//				ps.setString(1, regn_no);
//			}
//			ps.setString(2, chasis_no);
//			ps.setString(3, dobj.getState_cd());
//			ResultSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				tmConfigurationDobj = ServerUtil.getTmConfigurationParameters(rs.getString("state_cd"));
//				Date currentDate = rs.getDate("my_current_date");
//				dobj.setState_cd(rs.getString("state_cd"));
//				dobj.setOff_cd(rs.getInt("off_cd"));
//				dobj.setRegn_no(regn_no);
//				dobj.setMobile_no(rs.getLong("contact_no"));
//				if (!CommonUtils.isNullOrBlank(dobj.getTax_lgn_chk()) && !Skip_tax_lgn_chk) {
//					if (rs.getDate("vt_tax_taxupto") != null)
//						rs.updateDate("vt_tax_taxupto",
//								new java.sql.Date(DateUtil
//										.dateRange(rs.getDate("vt_tax_taxupto"), 0, 0,
//												Integer.parseInt(FormulaUtils.getReturnValue(dobj.getTax_lgn_chk())))
//										.getTime()));
//					if (rs.getDate("vt_taxclear_taxupto") != null)
//						rs.updateDate("vt_taxclear_taxupto",
//								new java.sql.Date(DateUtil
//										.dateRange(rs.getDate("vt_taxclear_taxupto"), 0, 0,
//												Integer.parseInt(FormulaUtils.getReturnValue(dobj.getTax_lgn_chk())))
//										.getTime()));
//					if (rs.getDate("vt_taxexem_taxupto") != null)
//						rs.updateDate("vt_taxexem_taxupto",
//								new java.sql.Date(DateUtil
//										.dateRange(rs.getDate("vt_taxexem_taxupto"), 0, 0,
//												Integer.parseInt(FormulaUtils.getReturnValue(dobj.getTax_lgn_chk())))
//										.getTime()));
//				}
//				if (!CommonUtils.isNullOrBlank(dobj.getFit_lgn_chk()) && !Skip_fit_lgn_chk) {
//					if (rs.getDate("fit_upto") != null)
//						rs.updateDate("fit_upto",
//								new java.sql.Date(DateUtil
//										.dateRange(rs.getDate("fit_upto"), 0, 0,
//												Integer.parseInt(FormulaUtils.getReturnValue(dobj.getFit_lgn_chk())))
//										.getTime()));
//					if (rs.getDate("fitness_table_fit_upto") != null)
//						rs.updateDate("fitness_table_fit_upto",
//								new java.sql.Date(DateUtil
//										.dateRange(rs.getDate("fitness_table_fit_upto"), 0, 0,
//												Integer.parseInt(FormulaUtils.getReturnValue(dobj.getFit_lgn_chk())))
//										.getTime()));
//
//				}
//
//				if (rs.getInt("vehicle_class_type") != TableConstants.VM_VEHTYPE_TRANSPORT) {
//					msg += ("* Vehicle is Non-Transport Type.");
//				}
//				if (!Skip_fit_lgn_chk
//						&& ((rs.getDate("fitness_exempt_from") == null && rs.getDate("fitness_exempt_upto") == null)
//								|| !(rs.getDate("fitness_exempt_from").compareTo(currentDate) <= 0
//										&& rs.getDate("fitness_exempt_upto").compareTo(currentDate) >= 0))) {
//					if ((rs.getDate("fit_upto") == null || rs.getDate("fit_upto").compareTo(currentDate) < 0)
//							&& (rs.getDate("fitness_table_fit_upto") == null
//									|| rs.getDate("fitness_table_fit_upto").compareTo(currentDate) < 0)) {
//						msg += (befrexpiryfit_check ? " Fitness of Vehicle is expired within one month, "
//								: "* Fitness of Vehicle is expired, ");
//					}
//				}
//
//				if (!Skip_ins_lgn_chk && (insInfoDobj == null
//						|| JSFUtils.getStringToDateyyyyMMdd(insInfoDobj.getInsUpto()).compareTo(currentDate) < 0)) {
//					msg += (befrexpiryIns_check ? " Insurance of Vehicle is expired within one month, "
//							: " Insurance of Vehicle is expired, ");
//				}
//
//				if (!ServerUtil.isAllowBlackListedVehicle(rs.getString("state_cd"))
//						&& !CommonUtils.isNullOrBlank(rs.getString("blacklist_descr"))) {
//					msg = "* Vehicle is Black-Listed because of " + rs.getString("blacklist_descr");
//				} else {
//					VehicleParameters parameter = new VehicleParameters();
//					parameter.setREGN_NO(regn_no);
//					parameter.setOFF_CD(rs.getInt("off_cd"));
//					parameter.setSTATE_CD(rs.getString("state_cd"));
//					parameter.setVH_CLASS(rs.getInt("vch_class"));
//					ServerUtil.getOwnerDetailsInVehicleParameters(parameter);
//					boolean tax_exempt_condition = FormulaUtils.isCondition(
//							FormulaUtils.replaceTagValues(tmConfigurationDobj.getTax_exemption(), parameter));
//					int vt_tax_taxupto = rs.getDate("vt_tax_taxupto") == null ? -1
//							: rs.getDate("vt_tax_taxupto").compareTo(currentDate);
//					int vt_taxclear_taxupto = rs.getDate("vt_taxclear_taxupto") == null ? -1
//							: rs.getDate("vt_taxclear_taxupto").compareTo(currentDate);
//					int vt_taxexem_taxupto = rs.getDate("vt_taxexem_taxupto") == null ? -1
//							: rs.getDate("vt_taxexem_taxupto").compareTo(currentDate);
//					if (!(",KL,PY,").contains("," + dobj.getState_cd() + ",") && !Skip_tax_lgn_chk) {
//						if (!tax_exempt_condition) {
//							if (vt_tax_taxupto < 0 && vt_taxclear_taxupto < 0 && vt_taxexem_taxupto < 0) {
//								if (!isTestingEnviroment) {
//									msg += (befrexpirytax_check ? "Your Tax is expired within one month, "
//											: "*Your Tax is not paid. Please Pay Tax From <a href='https://parivahan.gov.in/vahanservice/vahan/ui/statevalidation/homepage.xhtml'>Pay Your Road Tax</a> , ");
//								} else {
//									msg += (befrexpirytax_check ? "Your Tax is expired within one month, "
//											: "*Your Tax is not paid. Please Pay Tax From <a href='http://164.100.78.110/vahanservice/vahan/ui/statevalidation/homepage.xhtml'>Pay Your Road Tax</a> , ");
//								}
//							} else {
//								int rcptMinclearOpt = rs.getTimestamp("vt_tax_rcpt_dt")
//										.compareTo(rs.getTimestamp("vt_taxclear_op_dt"));
//								int rcptMinExemOpt = rs.getTimestamp("vt_tax_rcpt_dt")
//										.compareTo(rs.getTimestamp("vt_taxexem_op_dt"));
//								int clearOptMinExemOpt = rs.getTimestamp("vt_taxclear_op_dt")
//										.compareTo(rs.getTimestamp("vt_taxexem_op_dt"));
//								if (rcptMinclearOpt >= 0 && rcptMinExemOpt >= 0) {
//									if (vt_tax_taxupto < 0) {
//										if (!isTestingEnviroment) {
//											msg += (befrexpirytax_check ? "Your Tax is expired within one month, "
//													: "*Your Tax is not paid. Please Pay Tax From <a href='https://parivahan.gov.in/vahanservice/vahan/ui/statevalidation/homepage.xhtml'>Pay Your Road Tax</a> , ");
//										} else {
//											msg += (befrexpirytax_check ? "Your Tax is expired within one month, "
//													: "*Your Tax is not paid. Please Pay Tax From <a href='http://164.100.78.110/vahanservice/vahan/ui/statevalidation/homepage.xhtml'>Pay Your Road Tax</a> , ");
//										}
//									}
//								} else if (clearOptMinExemOpt >= 0 && rcptMinclearOpt <= 0) {
//									if (vt_taxclear_taxupto < 0) {
//										if (!isTestingEnviroment) {
//											msg += (befrexpirytax_check ? "Your Tax is expired within one month, "
//													: "*Your Tax is not paid. Please Pay Tax From <a href='https://parivahan.gov.in/vahanservice/vahan/ui/statevalidation/homepage.xhtml'>Pay Your Road Tax</a> , ");
//										} else {
//											msg += (befrexpirytax_check ? "Your Tax is expired within one month , "
//													: "*Your Tax is not paid. Please Pay Tax From <a href='http://164.100.78.110/vahanservice/vahan/ui/statevalidation/homepage.xhtml'>Pay Your Road Tax</a> , ");
//										}
//									}
//								} else if (rcptMinExemOpt <= 0 && clearOptMinExemOpt <= 0) {
//									if (vt_taxexem_taxupto < 0) {
//										if (!isTestingEnviroment) {
//											msg += (befrexpirytax_check ? "Your Tax is expired within one month, "
//													: "*Your Tax is not paid. Please Pay Tax From <a href='https://parivahan.gov.in/vahanservice/vahan/ui/statevalidation/homepage.xhtml'>Pay Your Road Tax</a> , ");
//										} else {
//											msg += (befrexpirytax_check ? "Your Tax is expired within one month, "
//													: "*Your Tax is not paid. Please Pay Tax From <a href='http://164.100.78.110/vahanservice/vahan/ui/statevalidation/homepage.xhtml'>Pay Your Road Tax</a> , ");
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//
//		catch (VahanException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//		return msg;
//
//	}
//
//	/**
//	 * @return the tmConfigurationDobj
//	 */
//	public static TmConfigurationDobj getTmConfigurationDobj() {
//		return tmConfigurationDobj;
//	}
//
//	/**
//	 * @param tmConfigurationDobj the tmConfigurationDobj to set
//	 */
//	public static void setTmConfigurationDobj(TmConfigurationDobj tmConfigurationDobj) {
//		CommonUtils.tmConfigurationDobj = tmConfigurationDobj;
//	}
//}
