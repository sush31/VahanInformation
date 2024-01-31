package impl;

import java.sql.PreparedStatement;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import CommonUtils.FillMapUtility;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import dobj.RenewalOfRegistrationDobj;
import server.CommonUtils;

public class RenewalOfRegistrationImpl {

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	int purCd = Integer.parseInt((String) session.getAttribute("purcd"));
	String stateCd = (String) session.getAttribute("state");

	public RenewalOfRegistrationDobj getRenewalRegistrationAttributes(String selectedState,
			RenewalOfRegistrationDobj renewalRegDobj) {

		renewalRegDobj.setServiceRto(new PermitImpl().isServiceRto(stateCd, purCd));
		renewalRegDobj.setServiceCitizen(new PermitImpl().isServiceCitizen(stateCd, purCd));
		boolean auth[] = FillMapUtility.getAdhaarAndMobAuthentication(stateCd, purCd);
		renewalRegDobj.setAadharAuthenticationCitizen(auth[0]);
		renewalRegDobj.setMobileAuthenticationCitizen(auth[1]);
		renewalRegDobj.setAadharAuthenticationRto(false);
		renewalRegDobj.setMobileAuthenticationRto(false);
		renewalRegDobj.setUploadDocumentCitizen(FillMapUtility.getDocumentUploadCitizen(stateCd, purCd));
		renewalRegDobj.setUploadDocumentRto(FillMapUtility.getDocumentUploadRTO(stateCd, purCd));
		renewalRegDobj.setFeesApplicable(FillMapUtility.getFeesApplicableForNewRegn(stateCd, purCd));
		getRenewalRegProperties(renewalRegDobj);
		getVintageVehicleProperties(renewalRegDobj);
		renewalRegDobj.setRenewAllowAnywhere(FillMapUtility.getApplInwardOtherRto(stateCd,purCd));
		renewalRegDobj.setRenewValue(getRenewValue(stateCd));
		renewalRegDobj.setFeeExempt(FillMapUtility.getFeesExempt(stateCd, purCd));

		return renewalRegDobj;
	}

	public void getRenewalRegProperties(RenewalOfRegistrationDobj renewalRegDobj) {
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select renewal_regn_rqrd_for,ren_regn_from_date from " + TableList.TM_CONFIGURATION
				+ " where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch whether service runs on citizen");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {

				String renRegCondition = FillMapUtility.interpretExpression(rs.getString("renewal_regn_rqrd_for"));
				String renRegRequiredFrom = rs.getString("ren_regn_from_date");
				if (CommonUtils.isNullOrBlank(renRegRequiredFrom)) {
					renRegRequiredFrom = "Inspection Date";
				} else {
					renRegRequiredFrom = rs.getString("ren_regn_from_date");
				}

				renewalRegDobj.setRenewRegFromDate("renRegRequiredFrom");
				renewalRegDobj.setRenewRegFor(renRegCondition);
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

	}

	public void getVintageVehicleProperties(RenewalOfRegistrationDobj renewalRegDobj) {
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = " select vintage_allowed,vintage_owner_choice from tm_configuration_appl_inward_anywhere_in_state where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch vintage ");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {

				renewalRegDobj.setVintageAllowed(rs.getBoolean("vintage_allowed"));
				renewalRegDobj.setVintageOwnerChoice(rs.getBoolean("vintage_owner_choice"));
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

	}
	

	public int getRenewValue(String stateCd) {
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		int val=0;
		sql = " select re_new_value from "+TableList.VM_VALIDITY_MAST+" where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch renew value ");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {

				val= rs.getInt("re_new_value");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		finally {
			
				try {
					if (tmgr != null) {
						tmgr.release();
					}
				} catch (Exception ee) {

				}
			}
			return val;
		}

	}
	

