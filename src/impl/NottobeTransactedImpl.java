package impl;

import java.io.Reader;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.RowSet;

import CommonUtils.FillMapUtility;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;

public class NottobeTransactedImpl {
	static Map<String, String> complaintDescrMap = new LinkedHashMap<String, String>();
	static Map<Integer, Map<Reader, String>> subcomplaintDescrMap = new LinkedHashMap<Integer, Map<Reader, String>>();

	public String getAllowedConditionFormulaDescrForAction(String stateCd) {
		String descr = "";
		String sql = "SELECT allowed_action_cd_condition_formula FROM  " + TableList.TM_CONFIGURATION_BLACKLIST
				+ " where state_cd=?";
		TransactionManagerReadOnly tmgr = null;
		try {
			tmgr = new TransactionManagerReadOnly("blacklist configuration");
			PreparedStatement prstmt = tmgr.prepareStatement(sql);
			prstmt.setString(1, stateCd);
			RowSet rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				descr = FillMapUtility.interpretExpression(rs.getString("allowed_action_cd_condition_formula"));

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (tmgr != null) {
					tmgr.release();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return descr;

	}

	public String getAllowedConditionFormulaDescrForPurpose(String stateCd) {
		String descr = "";
		String sql = "SELECT allowed_pur_cd_condition_formula FROM  " + TableList.TM_CONFIGURATION_BLACKLIST
				+ " where state_cd=?";
		TransactionManagerReadOnly tmgr = null;
		try {
			tmgr = new TransactionManagerReadOnly("blacklist configuration");
			PreparedStatement prstmt = tmgr.prepareStatement(sql);
			prstmt.setString(1, stateCd);
			RowSet rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				descr = interpretNottobetransactedExpression(rs.getString("allowed_pur_cd_condition_formula"));

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (tmgr != null) {
					tmgr.release();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return descr;

	}

	public static Map<Integer, Map<Reader, String>> fillComplainDescr() {

		String sql = "SELECT code,descr FROM  " + TableList.VM_BLACKLIST;
		TransactionManagerReadOnly tmgr = null;
		try {
			tmgr = new TransactionManagerReadOnly("complaint description");
			PreparedStatement prstmt = tmgr.prepareStatement(sql);
			RowSet rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {

				complaintDescrMap.put(String.valueOf(rs.getInt("code")), rs.getString("descr"));
				new NottobeTransactedImpl().fillSubcomplainDescr(subcomplaintDescrMap, rs.getInt("code"));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (tmgr != null) {
					tmgr.release();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return subcomplaintDescrMap;

	}

	public void fillSubcomplainDescr(Map<Integer, Map<Reader, String>> subcomplaintDescrMap, int code) {

		String sql = "select subcode,descr from " + TableList.VM_BLACKLIST_NOTTRANSACTED_OTHERS + " where code=?";
		TransactionManagerReadOnly tmgr = null;
		try {
			tmgr = new TransactionManagerReadOnly("sub complaint description");
			PreparedStatement prstmt = tmgr.prepareStatement(sql);
			prstmt.setInt(1, code);
			RowSet rs = tmgr.fetchDetachedRowSet();
			Map<Reader, String> subcomplaint = new LinkedHashMap<>();
			while (rs.next()) {
				subcomplaint.put(rs.getCharacterStream("subcode"), rs.getString("descr"));
			}
			subcomplaintDescrMap.put(code, subcomplaint);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (tmgr != null) {
					tmgr.release();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public static String interpretNottobetransactedExpression(String expression) {
		StringBuffer translatedExpression = new StringBuffer();
		String replacement = "";
		Map<Integer, Map<Reader, String>> subcomplaintDescrMap = new LinkedHashMap<Integer, Map<Reader, String>>();
		Map<String, Map<String, String>> codeMeanings = new HashMap<>();
		subcomplaintDescrMap = fillComplainDescr();
		codeMeanings.put("<61>", FillMapUtility.fillPurposeCodeMap());
		codeMeanings.put("<COMPLAIN_TYPE>", complaintDescrMap);
		subcomplaintDescrMap = fillComplainDescr();
		String patternString = "<(.*?)> in\\((.*?)\\)";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(expression);
		while (matcher.find()) {

			String code = matcher.group(1);
			String values = matcher.group(2);
			// Retrieve relevant from the map
			if (code.equalsIgnoreCase("COMPLAIN_TYPE")) {
				replacement = "COMPLAIN TYPE";
			} else if (code.equalsIgnoreCase("SUB_COMPLAIN_TYPE")) {
				replacement = "SUB COMPLAIN TYPE";
			} else if (code.equals("61")) {
				replacement = "PURPOSE";
			}
			matcher.appendReplacement(translatedExpression, Matcher.quoteReplacement(replacement));
			Map<String, String> meanings = codeMeanings.get("<" + code + ">");
			if (meanings != null) {
				String[] valueArray = values.split(",");
				for (String value : valueArray) {
					String meaning = meanings.get(value.trim());
					if (meaning != null) {
						// If the code is <SUB_COMPLAIN_TYPE>, dynamically fetch
						// its meaning based on <COMPLAIN_TYPE>
						if ("<SUB_COMPLAIN_TYPE>".equals("<" + code + ">")) {
							String complainType = getCodeValue(expression, "<COMPLAIN_TYPE>");
							if (complainType != null) {
								// Simulate dynamic fetching of sub complain
								// type meaning based on complain type
								meaning = fetchSubComplainTypeMeaning(complainType, value.trim());
							}
						}
						// Replace code with its meaning
						matcher.appendReplacement(translatedExpression, meaning);
					}
				}
			}

			matcher.appendTail(translatedExpression);
		}
		return translatedExpression.toString();

	}

	private static String getCodeValue(String expression, String code) {
		Pattern pattern = Pattern.compile(code + " in\\((.*?)\\)");
		Matcher matcher = pattern.matcher(expression);
		if (matcher.find()) {
			return matcher.group(1).trim();
		}
		return null;
	}

	private static String fetchSubComplainTypeMeaning(String complainType, String subComplainType) {
		return (subcomplaintDescrMap.get(complainType)).get(subComplainType);
	}

}
