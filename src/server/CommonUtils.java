/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.Serializable;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import databaseconnection.TableConstants;


public class CommonUtils implements Serializable {

	
	private static Pattern pattern;
	private static Matcher matcher;
	private static final String PWD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	public static final String MD5 = "MD5";
	
	public static synchronized String getRanomNumber() {

		String randonNumber = "";
		SecureRandom ran = new SecureRandom();
		int num = ran.nextInt();
		num = num + 1000;
		if (num < 0) {
			num = num * (-1);
		}

		randonNumber = String.valueOf(num);
		return randonNumber;
	}

	/**
	 * Checks whether the string is blank or 'null'.
	 *
	 * @return true if string is null or blank else false
	 * @param strCheck string to be verified
	 */
	public static boolean isNullOrBlank(String strCheck) {
		if ((null == strCheck) || ("null".equalsIgnoreCase(strCheck))
				|| (TableConstants.BLANK_STRING.equalsIgnoreCase(strCheck)) || (strCheck.trim().length() <= 0)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isMapNullOrEmpty(Map strCheck) {
		if ((null == strCheck) || (strCheck.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isListNullOrEmpty(List strCheck) {
		if ((null == strCheck) || (strCheck.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks whether the string is blank or 'null' or "-1"
	 *
	 * @return true if string is null or blank or "-1" else false
	 * @param strCheck string to be verified
	 */
	public static boolean isNullOrBlank_Negative(String strCheck) {
		if ((strCheck == null) || ("null".equalsIgnoreCase(strCheck)) || ("".equalsIgnoreCase(strCheck))
				|| (strCheck.trim().length() <= 0) || ("-1".equalsIgnoreCase(strCheck))) {
			return true;
		} else {
			return false;
		}
	}

	


	public static long getAsciiSum(String str) {
		long asciiSum = 0;
		if (str != null && str.length() > 0) {
			for (int i = 0; i < str.length(); i++) {
				asciiSum = asciiSum + str.charAt(i);
			}
		}
		return asciiSum;
	}

	

	public static void invalidateJSFSession(HttpSession session) {
		try {
			if (session != null && !session.isNew()) {
				session.invalidate();
			}
		} catch (IllegalStateException e) {
			
		}
	}

	

	public static long getUserServiceCharge(String desp) {

		long userServiceCharge = 0;
		// change by vivek
		if (!desp.contains("<slno")) {
			return userServiceCharge;
		} else if (desp.contains("###")) {
			desp = desp.substring(0, desp.indexOf("###"));
		}
		// change by vivek
		int index1 = desp.indexOf("USER_CHARGE");
		int index2 = desp.lastIndexOf("USER_CHARGE");
		int lastInd = desp.lastIndexOf(">><");
		if (index1 == index2) {
			// //System.out.println("index1 = : " + index1+" index2 = : " +
			// index2+" Last index>>< = : " + lastInd);
			String serStr = desp.substring(index1, lastInd);
			int indxEqul = serStr.indexOf("=");
			String usrChrge = serStr.substring(indxEqul + 1);
			if (!CommonUtils.isNullOrBlank(usrChrge)) {
				// userServiceCharge = Double.valueOf(usrChrge).longValue();
				userServiceCharge = Long.valueOf(usrChrge);
			}
			// LOGGER.info("serCharge = " + userServiceCharge);
		}
		return userServiceCharge;
	}

	public static long getUserServiceChargeOnlyForNoFeesFlow(String desp) {

		long userServiceCharge = 0;

		if (!desp.contains("USER_CHARGE")) {
			return userServiceCharge;
		}
		int index1 = desp.indexOf("USER_CHARGE");
		int index2 = desp.lastIndexOf("USER_CHARGE");
		int lastInd = desp.lastIndexOf(">>");

		if (index1 == index2) {
			// //System.out.println("index1 = : " + index1+" index2 = : " +
			// index2+" Last index>>< = : " + lastInd);
			String serStr = desp.substring(index1, lastInd);
			int indxEqul = serStr.indexOf("=");
			String usrChrge = serStr.substring(indxEqul + 1);
			if (!CommonUtils.isNullOrBlank(usrChrge)) {
				// userServiceCharge = Double.valueOf(usrChrge).longValue();
				userServiceCharge = Long.valueOf(usrChrge);
			}
			// LOGGER.info("serCharge = " + userServiceCharge);
		}
		return userServiceCharge;
	}

	public static long getTransactionFee(String desp) {

		long transFee = 0;
		if (!desp.contains("<slno")) {
			return transFee;
		}
		if (!desp.contains("TRANS_FEE")) {
			return transFee;
		}
		int index1 = desp.indexOf("TRANS_FEE");
		int index2 = desp.lastIndexOf("TRANS_FEE");
		int lastInd = desp.lastIndexOf(">><<USER_CHARGE");
		if (index1 == index2) {
			// //System.out.println("index1 = : " + index1+" index2 = : " +
			// index2+" Last index>>< = : " + lastInd);
			String serStr = desp.substring(index1, lastInd);
			int indxEqul = serStr.indexOf("=");
			String transFee1 = serStr.substring(indxEqul + 1);
			if (!CommonUtils.isNullOrBlank(transFee1)) {
				// transFee = Double.valueOf(transFee1).longValue();
				transFee = Long.valueOf(transFee1);
			}
			// LOGGER.info("serCharge = " + transFee);
		}
		return transFee;
	}

	
	public static boolean checkNumber(String number) {
		boolean flag = true;
		if (!number.matches("[a-zA-Z]")) {
			flag = false;
		}
		return flag;
	}

	public static boolean checkNumberForRegion(String number) {
		return number.matches("[0-9]+(\\.[0-9][0-9]?)?");
	}
}

	