package impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.sql.RowSet;

import CommonUtils.FillMapUtility;
import databaseconnection.TableConstants;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import dobj.FitnessDobj;
import dobj.FitnessValidityDobj;

public class FitnessImpl {

	public void getFitnessAttributes(String stateCd, FitnessDobj fitnessDobj) {
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		String vltdCheck = "";

		sql = "select is_document_upload,check_vltd from tm_configuration_fitness where state_cd=?";

		try {
			tmgr = new TransactionManagerReadOnly("fetch fitness details ");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				vltdCheck = rs.getString("check_vltd");
				if (!vltdCheck.equalsIgnoreCase("true") && !vltdCheck.equalsIgnoreCase("false"))
					vltdCheck = FillMapUtility.interpretExpression(rs.getString("check_vltd"));
				fitnessDobj.setVltdCheck(vltdCheck);
				fitnessDobj.setDocUpload(rs.getBoolean("is_document_upload"));
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

	}

	public ArrayList<FitnessValidityDobj> getFitnessValidityList(String stateCd) {
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		String vltdCheck = "";
		ArrayList<FitnessValidityDobj> list=new ArrayList<>();

		sql = "select condition_formula,new_value,re_new_value from " + TableList.VM_VALIDITY_MAST
				+ "  where state_cd=?";

		try {
			tmgr = new TransactionManagerReadOnly("fetch fitness details ");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				FitnessValidityDobj dobj=new FitnessValidityDobj();
				dobj.setCondition(FillMapUtility.interpretExpression(rs.getString("condition_formula")));
				dobj.setNewVal(rs.getInt("new_value"));
				dobj.setRenewVal(rs.getInt("re_new_value"));
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

}
