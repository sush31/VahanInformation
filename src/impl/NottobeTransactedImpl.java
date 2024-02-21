package impl;

import java.sql.PreparedStatement;

import javax.sql.RowSet;

import CommonUtils.FillMapUtility;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;

public class NottobeTransactedImpl {

	
	public String getAllowedConditionFormulaDescrForAction(String stateCd)
	{
		String descr="";
		String sql = "SELECT allowed_action_cd_condition_formula FROM  " + TableList.TM_CONFIGURATION_BLACKLIST+" where state_cd=?";
		TransactionManagerReadOnly tmgr = null;
		try {
			tmgr = new TransactionManagerReadOnly("blacklist configuration");
			PreparedStatement prstmt = tmgr.prepareStatement(sql);
			prstmt.setString(1, stateCd);
			RowSet rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				descr=FillMapUtility.interpretExpression(rs.getString("descr"));
				
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
	
	public String getAllowedConditionFormulaDescrForPurpose(String stateCd)
	{
		String descr="";
		String sql = "SELECT allowed_pur_cd_condition_formula FROM  " + TableList.TM_CONFIGURATION_BLACKLIST+" where state_cd=?";
		TransactionManagerReadOnly tmgr = null;
		try {
			tmgr = new TransactionManagerReadOnly("blacklist configuration");
			PreparedStatement prstmt = tmgr.prepareStatement(sql);
			prstmt.setString(1, stateCd);
			RowSet rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				descr=FillMapUtility.interpretExpression(rs.getString("descr"));
				
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
}
