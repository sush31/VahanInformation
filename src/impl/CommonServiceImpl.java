package impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.sql.RowSet;

import CommonUtils.FillMapUtility;
import databaseconnection.TableConstants;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import dobj.ConversionOfVehicleDobj;
import dobj.ConvertibleClasses;

public class CommonServiceImpl {

	
	
	public  boolean getHypthVerifyBank(String stateCd, int purCd) {
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
	
	public  ArrayList<ConvertibleClasses> getConvertibleClasses()
	{
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		ArrayList<ConvertibleClasses> list=new ArrayList<>();
		
		sql="select vh_class,convertible_classes from " + TableList.VM_VH_CLASS+" order by 1";
		try {
			tmgr = new TransactionManagerReadOnly("fetch convertible classes details ");
			ps = tmgr.prepareStatement(sql);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				StringBuilder output = new StringBuilder();
				ConvertibleClasses dobj=new ConvertibleClasses();
				dobj.setVehClass(FillMapUtility.getVehClassDecr(rs.getInt("vh_class")));
				String classes=rs.getString("convertible_classes");
				String[] classesList=classes.split(",");
				for(String vehclass: classesList)
				{
					String vehClassDescr= FillMapUtility.getVehClassDecr(Integer.parseInt(vehclass));
					 output.append(vehClassDescr).append(", ");
				}
				dobj.setConvertibleClasses(output);
				list.add(dobj);
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
		return list;
		
	}
	
	public  boolean getReassignNumberInConversion(String stateCd)
	{
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		boolean reassignOrReattain=false;
		
		sql="select reassign_retained_no_with_conv from " + TableList.TM_CONFIGURATION+" where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch convertible classes details ");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				reassignOrReattain=rs.getBoolean("reassign_retained_no_with_conv");
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
		return reassignOrReattain;
		
	}
	public  boolean nocRequiredForSameState(String stateCd)
	{
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		boolean noc=false;
		
		sql="select allow_for_same_state from " + TableList.TM_CONFIGURATION_NOC+" where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch convertible classes details ");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				noc=rs.getBoolean("allow_for_same_state");
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
		return noc;
		
	}
	
	public  boolean getHsrp(String stateCd)
	{
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		boolean hsrp=false;
		
		sql="select distinct hsrp from vm_smart_card_hsrp where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch hsrp ");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				hsrp=hsrp || rs.getBoolean("hsrp");
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
		return hsrp;
		
	}
	public  boolean getOldHsrp(String stateCd)
	{
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		boolean hsrp=false;
		
		sql="select distinct old_veh_hsrp from vm_smart_card_hsrp where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch hsrp ");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				hsrp=hsrp || rs.getBoolean("old_veh_hsrp");
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
		return hsrp;
		
	}
	
	public  String getTaxInstallmentCondition(String stateCd)
	{
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		String taxInstallment="";
		sql="select tax_installment from "+ TableList.TM_CONFIGURATION +" where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch tax installment condition ");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				taxInstallment=rs.getString("tax_installment");
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
		return taxInstallment;
		
	}

	
	
	
}
