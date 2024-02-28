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
import bean.LoginBean;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;

public class NottobeTransactedImpl {
	static Map<String, String> complaintDescrMap = new LinkedHashMap<String, String>();
	static Map<Integer, Map<String, String>> subcomplaintDescrMap = new LinkedHashMap<Integer, Map<String, String>>();

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

	public static Map<Integer, Map<String, String>> fillComplainDescr() {

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

	public void fillSubcomplainDescr(Map<Integer, Map<String, String>> subcomplaintDescrMap, int code) {

		String sql = "select subcode,descr from " + TableList.VM_BLACKLIST_NOTTRANSACTED_OTHERS + " where code=?";
		TransactionManagerReadOnly tmgr = null;
		try {
			tmgr = new TransactionManagerReadOnly("sub complaint description");
			PreparedStatement prstmt = tmgr.prepareStatement(sql);
			prstmt.setInt(1, code);
			RowSet rs = tmgr.fetchDetachedRowSet();
			Map<String, String> subcomplaint = new LinkedHashMap<>();
			while (rs.next()) {
				subcomplaint.put(rs.getString("subcode"), rs.getString("descr"));
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
		StringBuffer result = new StringBuffer();
		String index = "";
		String replacement="";
		Map<Integer, Map<String, String>> subcomplaintDescrMap = new LinkedHashMap<Integer, Map<String, String>>();
		Map<String, Map<String, String>> codeMeanings = new HashMap<>();
		Map<String, String> context = new HashMap<>();
		Map<?, String> meanings = new HashMap<>();
		context.put("<61>", "PURPOSE");
		context.put("<COMPLAIN_TYPE>", "COMPLAIN TYPE");
		context.put("SUB_COMPLAIN_TYPE", "SUB COMPLAIN TYPE");
		subcomplaintDescrMap = fillComplainDescr();
		codeMeanings.put("<61>", FillMapUtility.fillPurposeCodeMap());
		codeMeanings.put("<COMPLAIN_TYPE>", complaintDescrMap);
		//String patternString = "<(.*?)> in\\((.*?)\\)";
		//String patternString ="<(\\w+)>\\s+in\\((\\d+)\\)";
		String patternString="<(\\w+)>|'(\\w)'|(\\d+)";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(expression);
		while (matcher.find()) {
		
			String code = matcher.group();
			String val=matcher.group(1);
			String key = matcher.group(2);

			if (code.startsWith("<")) {
				String contextreplacement = context.getOrDefault(code, code);
				if (code.equals("<61>") || code.equals("<COMPLAIN_TYPE>")) {
					meanings = codeMeanings.get(code);
					
				}
				if (code.equals("<SUB_COMPLAIN_TYPE>")) {
					meanings = subcomplaintDescrMap.get(Integer.parseInt(index));

				}

				matcher.appendReplacement(result, Matcher.quoteReplacement(contextreplacement));

			}

			else {
				
				if (meanings != null) {
					index=code;
					replacement = (String) meanings.getOrDefault(code, code);
				} else {
					replacement = code;
				}
				matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));

			}
			
			System.out.println(result);

		}
		matcher.appendTail(result);
		return result.toString();
	}

	

	private static String fetchSubComplainTypeMeaning(String complainType, String subComplainType) {
		return (subcomplaintDescrMap.get(complainType)).get(subComplainType);
	}

}
