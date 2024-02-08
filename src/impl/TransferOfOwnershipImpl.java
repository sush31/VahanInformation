package impl;

import java.sql.PreparedStatement;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import CommonUtils.FillMapUtility;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import dobj.TransferOfOwnershipDobj;

public class TransferOfOwnershipImpl {

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	int purCd = Integer.parseInt((String) session.getAttribute("purcd"));
	String stateCd = (String) session.getAttribute("state");

	public TransferOfOwnershipDobj getTOAttributes(TransferOfOwnershipDobj transferOwnershipDobj) {
		boolean toRetention = false;
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select to_retention_for_all_regn from  " + TableList.TM_CONFIGURATION + " where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch TO retention");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {

				toRetention = (rs.getBoolean("to_retention_for_all_regn"));
				transferOwnershipDobj.setToRetention(toRetention);

			}
			getTONOCDetails(transferOwnershipDobj);

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
		return transferOwnershipDobj;

	}

	public TransferOfOwnershipDobj getTONOCDetails(TransferOfOwnershipDobj transferOwnershipDobj) {

		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select check_for_to_with_noc,check_for_to_without_noc from tm_configuration_appl_inward_anywhere_in_state where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch TO retention");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {

				transferOwnershipDobj.setTransferOfOwnershipWithtNoc(rs.getBoolean("check_for_to_with_noc"));
				transferOwnershipDobj.setTransferOfOwnershipWithoutNoc(
						FillMapUtility.interpretExpression(rs.getString("check_for_to_without_noc")));

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
		return transferOwnershipDobj;

	}

}
