package impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

public class PermitImpl {

	boolean isServiceRto=false;
	boolean isServiceCitizen=false;
	static public Map<String, String> TagFieldMap = null;

	public PermitDobj getPermitServiceAttributes(String stateCd, int purCd) {

		ArrayList<Object> flow;
		PermitDobj permitdobj = null;
		String upload_doc_condition = null;
		String enab_faceless = null;
		boolean aadharAuthentication;
		boolean uploadDocument;
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
			if (rs.next()) {
				permitdobj = new PermitDobj();
				permitdobj.setFeeRequired(rs.getBoolean("is_fee_required"));
				permitdobj.setVia_registered_mobile(rs.getBoolean("via_registered_mobile"));
				if (rs.getString("enab_faceless_serv") != null) {
					enab_faceless = getConditionDescription(rs.getString("enab_faceless_serv"));
					aadharAuthentication = true;
				} else

				{
					aadharAuthentication = false;
				}
				upload_doc_condition = getConditionDescription(rs.getString("upload_doc_condition"));
				uploadDocument = rs.getBoolean("upload_doc");
				permitdobj.setUploadDocument(uploadDocument);
				permitdobj.setUploadDocumentCondition(upload_doc_condition);
				permitdobj.setAadharAuthentication(aadharAuthentication);
				permitdobj.setFaceless(enab_faceless);
				permitdobj.setServiceCitizen(isServiceCitizen);
				permitdobj.setServiceRto(isServiceRto);
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
					System.out.println(ee);
				}
			}
		}
		return permitdobj;

	}

	// public StringBuilder getConditionDescription(String input) {
	//
	// // <61> IN (26,36,34,27,41,28,46,29,33,32) OR (<61>=35 AND
	// <transport_catg> in ('P','G'))
	// // (25,26,39,36,34,27,35,28,29,41)
	// // [<61>, IN, (25,26,39,36,34,27,35,28,29,41), OR, (<61>=38, and, <25>,
	// NOT, IN, (105,106))]
	// TagFieldMap= FillMapUtility.fillMap();
	//// String[] tokens = input.split("\\s+");
	// StringBuilder result = new StringBuilder();
	//// for (String token : tokens) {
	//// if (token.matches("<\\d+>")) {
	//// result.append(getCodeDescr(token));
	//// }
	//// else if (token.startsWith(("("))){
	//// result.append("("+' ');
	//// String[] subtoken = token.substring(1,token.length()).split(",");
	//// for (String t : subtoken) {
	//// if(t.matches("\\d+"))
	//// {
	//// result.append(TagFieldMap.get(Integer.parseInt(t))).append(",");
	//// }
	//// else if(t.matches("<\\d+>"))
	//// {
	//// result.append(getCodeDescr(t));
	//// }
	//// else
	//// {
	//// result.append(t+" ");
	//// }
	//// }
	//// result.append(")");
	////
	//// }
	//// else
	//// {
	//// result.append(' '+token+' ');
	//// }
	////
	////
	//// }
	////
	////
	// return result;
	//
	//
	// }

	public String getConditionDescription(String input) {
		// input="<61> IN (26,36,34) OR (<61>=35 AND <transport_catg> in
		// ('P','G'))";
		String input1 = input.trim();
		TagFieldMap = FillMapUtility.fillMap();
		// TagFieldMap = new LinkedHashMap<>();
		// TagFieldMap.put("26","fresh permit");
		// TagFieldMap.put("36","especial permit");
		// TagFieldMap.put("34","duplicate permit");
		// TagFieldMap.put("<61>","purpose code");
		// TagFieldMap.put("35","temporary permit");
		//
		//
		Pattern pattern = Pattern.compile("<\\d+>|\\b\\d+\\b");
		Matcher matcher = pattern.matcher(input1);
		StringBuffer result = new StringBuffer();
		while (matcher.find()) {
			String value = matcher.group();
			String replacement = (String) TagFieldMap.getOrDefault(value, value);
			matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
		}
		matcher.appendTail(result);

		System.out.println(result.toString());

		return result.toString();
	}

	public boolean isServiceRto(String stateCd,int purCd)	
	
	{
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select * from "+ TableList.TM_PURPOSE_ACTION_FLOW+" where state_cd=? and pur_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch flow from RTO");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
     		ps.setInt(1, purCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
	
				isServiceRto=true;
				
           }
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			{
				try {
					if (tmgr != null) {
						tmgr.release();
					}
				} catch (Exception ee) {
					System.out.println(ee);
				}
			}
		}
		return isServiceRto;
		
	}
	
public boolean isServiceCitizen(String stateCd,int purCd)	
	
	{
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select * from "+ TableList.PERMIT_PURPOSE_ACTION_FLOW +" where state_cd=? and pur_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch flow from RTO");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
     		ps.setInt(1, purCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
	
				isServiceCitizen=true;
				
           }
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			{
				try {
					if (tmgr != null) {
						tmgr.release();
					}
				} catch (Exception ee) {
					System.out.println(ee);
				}
			}
		}
		return isServiceCitizen;
	}
}
