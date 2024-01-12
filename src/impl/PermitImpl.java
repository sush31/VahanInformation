package impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.sql.RowSet;

import org.apache.poi.util.SystemOutLogger;

import CommonUtils.FillMapUtility;
import CommonUtils.FormulaUtils;
import CommonUtils.VehicleParameters;
import databaseconnection.TableConstants;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import dobj.PermitDobj;
import dobj.RtoServiceFlowDobj;
import dobj.CitizenServiceFlowDobj;

public class PermitImpl {

	boolean isServiceRto = false;
	boolean isServiceCitizen = false;
	static public Map<String, String> TagFieldMap = null;

	public PermitDobj getPermitServiceAttributes(String stateCd, int purCd, PermitDobj permitdobj) {

		ArrayList<Object> flow;
		String upload_doc_condition = null;
		String enab_faceless = null;
		String aadharAuthentication = null;
		boolean uploadDocument;
		String uploadDoc;
		String noFeeCitizen;
		String noFeeRto;
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select via_registered_mobile, is_fee_required,upload_doc,upload_doc_condition, no_fee_purpose,enab_faceless_serv from "
				+ TableList.ONLINE_PERMIT_STATE_CONFIGURE + " where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch permit attributes");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			VehicleParameters parameter = new VehicleParameters();
			parameter.setPUR_CD(purCd);
			if (rs.next()) {
				permitdobj.setVia_registered_mobile(rs.getBoolean("via_registered_mobile"));
				if (rs.getString("enab_faceless_serv") != null) {
					enab_faceless = rs.getString("enab_faceless_serv");
					if (enab_faceless.equalsIgnoreCase("false")) {
						aadharAuthentication = "false";
					} else {

						boolean aadharAuth = FormulaUtils
								.isCondition(FormulaUtils.replaceTagPermitValues(enab_faceless, parameter));
						if (containsOnly61(enab_faceless) == true) {

							aadharAuthentication = aadharAuth ? "true" : "false";

						} else {
							if (aadharAuth == true)
								aadharAuthentication = "true";
							else {
								aadharAuthentication = interpretExpression(enab_faceless);
							}
						}

					}
					boolean uploaddoc = rs.getBoolean("upload_doc");
					if (uploaddoc) {
						uploadDoc = rs.getString("upload_doc_condition");
						uploadDocument = FormulaUtils.isCondition(
								FormulaUtils.replaceTagPermitValues(rs.getString("upload_doc_condition"), parameter));
						if (containsOnly61(uploadDoc) == true) {
							uploadDoc = uploadDocument ? "true" : "false";

						} else {
							if (uploadDocument == true)
								uploadDoc = "true";
							else {
								uploadDoc = interpretExpression(uploadDoc);
							}
						}
					} else {
						uploadDoc = "false";
					}
					permitdobj.setUploadDocument(uploadDoc);
					permitdobj.setAadharAuthentication(aadharAuthentication);
					permitdobj.setServiceCitizen(isServiceRto(stateCd, purCd));
					permitdobj.setServiceRto(isServiceCitizen(stateCd, purCd));
					String noFeePurpose = rs.getString("no_fee_purpose");
					if (noFeePurpose.equalsIgnoreCase("false")) {
						noFeeCitizen = "false";
					} else {
						boolean noFee = FormulaUtils.isCondition(
								FormulaUtils.replaceTagPermitValues(rs.getString("no_fee_purpose"), parameter));

						if (containsOnly61(rs.getString("no_fee_purpose"))) {

							noFeeCitizen = noFee ? "true" : "false";
						} else {
							if (noFee) {
								noFeeCitizen = "true";
							} else {
								noFeeCitizen = interpretExpression(rs.getString("no_fee_purpose"));
							}
						}
						permitdobj.setCitizenFeeExempt(noFeeCitizen);

					}

				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			{
				try {
					if (tmgr != null) {
						tmgr.release();
					}
				} catch (Exception ee) {

				}
			}
		}
		return permitdobj;

	}

	

	public String interpretExpression(String expression) {
		Map<String, String> vmtaxslabfieldsmap = new LinkedHashMap<String, String>();
		vmtaxslabfieldsmap = FillMapUtility.getCodeDescr();
		Map<String, String> codeMeanings = new LinkedHashMap<String, String>();
		StringBuffer result = new StringBuffer();
		expression = expression.trim();
		Map<String, Map<String, String>> contextAwareCodeMeanings = fetchContextAwareCodeMeaningsFromDatabase();
		String patternString = "<(\\d+)>|(\\b\\d+\\b)";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(expression);
		while (matcher.find()) {
			String code = matcher.group(); // Check if the code inside angle
											// brackets is present
			if (code.startsWith("<")) {

				codeMeanings = contextAwareCodeMeanings.get(code);
				String contextreplacement = (String) vmtaxslabfieldsmap.getOrDefault(code,code);
				matcher.appendReplacement(result, Matcher.quoteReplacement(contextreplacement));

			} else {

				String replacement = (String) codeMeanings.getOrDefault(code, code);
				matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
				
			}
		}
		matcher.appendTail(result);
		System.out.println(result);
		return result.toString();
		

	}

	private Map<String, Map<String, String>> fetchContextAwareCodeMeaningsFromDatabase() {
		Map<String, Map<String, String>> contextAwareCodeMeanings = new HashMap<>();
		contextAwareCodeMeanings.put("<61>", FillMapUtility.fillPurposeCodeMap());
		contextAwareCodeMeanings.put("<25>", FillMapUtility.fillPmtTypeMap());
		contextAwareCodeMeanings.put("<33>", FillMapUtility.fillVehicleClassMap());
		

		return contextAwareCodeMeanings;
	}

	public String getConditionFormulaDescription(String input) {

		String input1 = input.trim();
		TagFieldMap = FillMapUtility.fillMap();
		Pattern pattern = Pattern.compile("<\\d+>|\\b\\d+\\b");
		Matcher matcher = pattern.matcher(input1);
		StringBuffer result = new StringBuffer();
		while (matcher.find()) {
			String value = matcher.group();
			String replacement = (String) TagFieldMap.getOrDefault(value, value);
			matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
		}
		matcher.appendTail(result);

		return result.toString();
	}

	public boolean containsOnly61(String input) {
		// Define a regular expression pattern to match <61>
		// String patternString =
		// "<61>\\s+IN\\s*\\(\\d+(,\\s*\\d+)*\\)\\s*OR\\s*\\(.*\\)";
		String patternString = "<33>|<25>|<20>";
		Pattern pattern = Pattern.compile(patternString);

		// Create a matcher with the input string
		Matcher matcher = pattern.matcher(input);

		// Check if the pattern is found in the input
		if (matcher.find()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isServiceRto(String stateCd, int purCd)

	{
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select * from " + TableList.TM_PURPOSE_ACTION_FLOW + " where state_cd=? and pur_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch flow from RTO");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			ps.setInt(2, purCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {

				isServiceRto = true;

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			{
				try {
					if (tmgr != null) {
						tmgr.release();
					}
				} catch (Exception ee) {

				}
			}
		}
		return isServiceRto;

	}

	public boolean isServiceCitizen(String stateCd, int purCd)

	{
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select * from " + TableList.PERMIT_PURPOSE_ACTION_FLOW + " where state_cd=? and pur_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch flow from RTO");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			ps.setInt(2, purCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {

				isServiceCitizen = true;

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			{
				try {
					if (tmgr != null) {
						tmgr.release();
					}
				} catch (Exception ee) {

				}
			}
		}
		return isServiceCitizen;
	}

	public ArrayList<CitizenServiceFlowDobj> getCitizenServiceFlow(String stateCd, int purCd,
			CitizenServiceFlowDobj citizenFlow) {

		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql1 = null;
		ArrayList<CitizenServiceFlowDobj> flowCitizen = new ArrayList<>();
		sql1 = "select flow_srno,action_cd,condition_formula,move_to_vahan4,isbackward,action_descr from "
				+ TableList.PERMIT_PURPOSE_ACTION_FLOW + " where state_cd=? and pur_cd=? order by flow_srno";
		try {
			tmgr = new TransactionManagerReadOnly("fetch flow from Citizen");
			ps = tmgr.prepareStatement(sql1);
			ps.setString(1, stateCd);
			ps.setInt(2, purCd);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				CitizenServiceFlowDobj dobj = new CitizenServiceFlowDobj();
				dobj.setFlow_srno(rs.getInt("flow_srno"));
				dobj.setAction_cd(rs.getInt("action_cd"));
				if (rs.getString("condition_formula").equalsIgnoreCase("true")) {
					dobj.setCondition_formula(rs.getString("condition_formula"));
				} else {
					dobj.setCondition_formula(getConditionFormulaDescription(rs.getString("condition_formula")));
				}
				dobj.setMove_to_vahan4(rs.getBoolean("move_to_vahan4"));
				dobj.setAction_descr(rs.getString("action_descr"));
				dobj.setBackward(rs.getString("isbackward"));
				flowCitizen.add(dobj);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			{
				try {
					if (tmgr != null) {
						tmgr.release();
					}
				} catch (Exception ee) {

				}

			}
		}
		return flowCitizen;
	}

	public ArrayList<RtoServiceFlowDobj> getRtoServiceFlow(String stateCd, int purCd, RtoServiceFlowDobj rtoFlow) {

		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql1 = null;
		ArrayList<RtoServiceFlowDobj> flowRto = new ArrayList<>();
		sql1 = "select flow_srno,action_cd,isbackward,condition_formula from " + TableList.TM_PURPOSE_ACTION_FLOW
				+ " where state_cd=? and pur_cd=? order by flow_srno";
		try {
			tmgr = new TransactionManagerReadOnly("fetch flow from RTO");
			ps = tmgr.prepareStatement(sql1);
			ps.setString(1, stateCd);
			ps.setInt(2, purCd);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				RtoServiceFlowDobj dobjRto = new RtoServiceFlowDobj();
				dobjRto.setFlow_srno(rs.getInt("flow_srno"));
				dobjRto.setAction_cd(rs.getInt("action_cd"));
				dobjRto.setBackward(rs.getString("isbackward"));
				if (rs.getString("condition_formula").equalsIgnoreCase("true")) {
					dobjRto.setCondition_formula(rs.getString("condition_formula"));
				} else {
					dobjRto.setCondition_formula(getConditionFormulaDescription(rs.getString("condition_formula")));
				}
				dobjRto.setAction_descr(FillMapUtility.getActionDescr(rs.getInt("action_cd")));
				flowRto.add(dobjRto);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			{
				try {
					if (tmgr != null) {
						tmgr.release();
					}
				} catch (Exception ee) {

				}
			}
		}
		return flowRto;
	}

}
