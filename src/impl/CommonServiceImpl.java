package impl;

import java.sql.PreparedStatement;

import javax.sql.RowSet;

import databaseconnection.TableConstants;
import databaseconnection.TransactionManagerReadOnly;

public class CommonServiceImpl {

	
	
	public static boolean getHypthVerifyBank(String stateCd, int purCd) {
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		boolean hypth = false;
		if(purCd==TableConstants.VM_TRANSACTION_MAST_ADD_HYPO)
		{
		sql = "select hpa as hypth from tm_configuration_hypth_vrfy_bank where state_cd=?";
		}
		else if(purCd==TableConstants.VM_TRANSACTION_MAST_REM_HYPO)
		{
			sql = "select hpt as hypth from tm_configuration_hypth_vrfy_bank where state_cd=?";
		}
		else if((purCd==TableConstants.VM_TRANSACTION_MAST_HPC))
		{
			sql = "select hpc as hypth from tm_configuration_hypth_vrfy_bank where state_cd=?";
		}
		try {
			tmgr = new TransactionManagerReadOnly("fetch hypth details ");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				hypth=rs.getBoolean("hypth");
				
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

				}
			}

		}
		return hypth;

}
	
}
