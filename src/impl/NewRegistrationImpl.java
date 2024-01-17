package impl;

import java.sql.PreparedStatement;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import CommonUtils.FillMapUtility;
import CommonUtils.FormulaUtils;
import CommonUtils.VehicleParameters;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import dobj.NewRegistrationDobj;

public class NewRegistrationImpl {

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	int purCd = (Integer) session.getAttribute("purcd");

	public NewRegistrationDobj getNewRegistrationAttributes(String stateCd, NewRegistrationDobj newRegndobj) {

		String zeroFees;
		boolean uploadDocument;
		String uploadDoc;
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
				newRegndobj.setUploadDocument(getDocumentUpload(stateCd));
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
		sql = "select is_doc_upload,pur_cd  " + TableList.TM_CONFIGURATION_DMS + " where state_cd=?";
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

}
