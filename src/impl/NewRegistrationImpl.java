package impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import CommonUtils.FillMapUtility;
import CommonUtils.FormulaUtils;
import CommonUtils.VehicleParameters;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import dobj.NewRegistrationDobj;
import dobj.RtoServiceFlowDobj;

public class NewRegistrationImpl {

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	int purCd = Integer.parseInt((String) session.getAttribute("purcd"));
	String stateCd= (String) session.getAttribute("state");
	
	public NewRegistrationDobj getNewRegistrationAttributes(String stateCd, NewRegistrationDobj newRegndobj) {

		String zeroFees;
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select tax_exemption,fee_amt_zero from  " + TableList.TM_CONFIGURATION + " where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch new registartion attributes");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			VehicleParameters parameter = new VehicleParameters();
			parameter.setPUR_CD(1);
			if (rs.next()) {

				String tax_exemption = FillMapUtility.interpretExpression(rs.getString("tax_exemption"));
				newRegndobj.setTaxExempt(tax_exemption);
				if (rs.getString("fee_amt_zero").equalsIgnoreCase("false")) {
					zeroFees = "false";
				}
				boolean zerofee = FormulaUtils
						.isCondition(FormulaUtils.replaceTagPermitValues(rs.getString("fee_amt_zero"), parameter));
				if (FillMapUtility.containsOnly61(rs.getString("fee_amt_zero"))) {
					zeroFees = zerofee ? "true" : "false";
				} else {
					if (zerofee) {
						zeroFees = "true";
					} else {
						zeroFees = FillMapUtility.interpretExpression(rs.getString("fee_amt_zero"));
					}
				}
				newRegndobj.setFeeExempt(zeroFees);
				newRegndobj.setTaxExempt(tax_exemption);
				newRegndobj.setUploadDocument(getDocumentUpload(stateCd));
				newRegndobj.setFeesApplicable(getFeesApplicableForNewRegn(stateCd));
				newRegndobj.setMobileAuthentication(getMobileAuthentication(stateCd));
				newRegndobj.setServiceRto(new PermitImpl().isServiceRto(stateCd, purCd));
				newRegndobj.setServiceCitizen(new PermitImpl().isServiceCitizen(stateCd, purCd));
				
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
		return newRegndobj;

	}

	public Boolean getDocumentUpload(String stateCd) {
		boolean uploadDocument = false;
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select is_doc_upload,pur_cd  from  " + TableList.TM_CONFIGURATION_DMS + " where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch dms");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			VehicleParameters parameter = new VehicleParameters();
			parameter.setPUR_CD(1);
			if (rs.next()) {

				uploadDocument = (rs.getBoolean("is_doc_upload"))
						&& (rs.getString("pur_cd").contains(String.valueOf(purCd)));

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
		return uploadDocument;

	}

	public Map<String,String> getFeesApplicableForNewRegn(String stateCd) {
		Map<String, String> feesApplicable = new LinkedHashMap<String, String>();
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		VehicleParameters parameter = new VehicleParameters();
		parameter.setPUR_CD(1);
		sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
				+ " where state_cd=? and action='NEW'";
		try {
			tmgr = new TransactionManagerReadOnly("fetch dms");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {

				String purpose= FillMapUtility.getPurposeDescr(rs.getInt("pur_cd"));
				String condition = rs.getString("condition_formula");
				if (condition.equalsIgnoreCase("true")) {
					feesApplicable.put(purpose, condition);
				} else {
					
						feesApplicable.put(purpose,
								FillMapUtility.interpretExpression(rs.getString("condition_formula")));
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
		return feesApplicable;
	}

	public String getMobileAuthentication(String stateCd) {
		String mobileauth = "false";
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select owner_mobile_verify_with_otp from  " + TableList.TM_CONFIGURATION_OTP + " where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch mobile OTP verification");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {

				if ((rs.getString("owner_mobile_verify_with_otp")).equalsIgnoreCase("true")) {
					mobileauth = "true";
				} else if (rs.getString("owner_mobile_verify_with_otp").equalsIgnoreCase("true")) {
					mobileauth = "false";
				} else {
					mobileauth = FillMapUtility.interpretExpression(rs.getString("owner_mobile_verify_with_otp"));
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
		return mobileauth;

	}

}
