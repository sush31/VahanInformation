package CommonUtils;

import java.sql.PreparedStatement;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.RowSet;

import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import oracle.net.aso.e;

public class FillMapUtility {

	static public Map<String,String> TagFieldMap = null;

	public static Map<String,String> fillPurposeCodeMap() {
		
	    final Map<String,String> purposecodemap = new LinkedHashMap <String,String>();
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql, sql1 = null;
		String descr = "";
		sql = "select pur_cd,descr from " + TableList.TM_PURPOSE_MAST+" order by pur_cd";

		try {
			tmgr = new TransactionManagerReadOnly("get code description");
			ps = tmgr.prepareStatement(sql);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				purposecodemap.put(String.valueOf(rs.getInt("pur_cd")), rs.getString("descr"));
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
		return purposecodemap;

	}

	public static Map<String,String> fillPmtTypeMap() {
		final Map<String,String> permitTypeMap = new LinkedHashMap <String,String>();
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql, sql1 = null;
		String descr = "";
		sql = "select code,descr from " + TableList.VM_PERMIT_TYPE;

		try {
			tmgr = new TransactionManagerReadOnly("get code description");
			ps = tmgr.prepareStatement(sql);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				permitTypeMap.put(String.valueOf(rs.getInt("code")), rs.getString("descr"));
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
		return permitTypeMap;

	}
	
	public static Map<String,String> fillVehicleClassMap() {
		final Map<String,String> vehClassMap = new LinkedHashMap <String,String>();
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql, sql1 = null;
		String descr = "";
		sql = "select vh_class,descr from " + TableList.VM_VH_CLASS;

		try {
			tmgr = new TransactionManagerReadOnly("get vehicle class description");
			ps = tmgr.prepareStatement(sql);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				vehClassMap.put(String.valueOf(rs.getInt("vh_class")), rs.getString("descr"));
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
		return vehClassMap;

	}
	
	public static Map<String,String> getCodeDescr() {
		TransactionManagerReadOnly tmgr = null;
		Map<String,String> vmtaxslabfieldsmap=new LinkedHashMap <String,String>();
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		String descr = "";
		sql = "select code,descr from " + TableList.VM_TAX_SLAB_FIELDS ;
		try {
			tmgr = new TransactionManagerReadOnly("get code description");
			ps = tmgr.prepareStatement(sql);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				vmtaxslabfieldsmap.put(rs.getString("code"), rs.getString("descr"));
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
		return vmtaxslabfieldsmap;

	}


	public static Map<String,String>fillMap() {
		TagFieldMap = new LinkedHashMap<>();
		getCodeDescr();
		fillPmtTypeMap();
		return TagFieldMap;

	}
	public static Map<String,String>fillMap1() {
		TagFieldMap = new LinkedHashMap<>();
		getCodeDescr();
		fillPmtTypeMap();
		fillPurposeCodeMap();
		return TagFieldMap;

	}
	
	public static String getActionDescr(int actionCode) {
		
		String actionDescr=null;
		String sqlTmActionSQL = "SELECT action_descr FROM TM_ACTION WHERE ACTION_CD = ?";
		TransactionManagerReadOnly tmgr = null;
		try {
			tmgr = new TransactionManagerReadOnly("action_description");
			PreparedStatement prstmt = tmgr.prepareStatement(sqlTmActionSQL);
			prstmt.setInt(1, actionCode);
			RowSet rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				actionDescr = rs.getString("action_descr");
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

		return actionDescr;

	}
	
	public static String getPurposeDescr(int purCd)
	{
		String purposeDescr=null;
		String sqlTmActionSQL = "SELECT descr FROM "+ TableList.TM_PURPOSE_MAST+"  WHERE pur_cd = ?";
		TransactionManagerReadOnly tmgr = null;
		try {
			tmgr = new TransactionManagerReadOnly("action_description");
			PreparedStatement prstmt = tmgr.prepareStatement(sqlTmActionSQL);
			prstmt.setInt(1, purCd);
			RowSet rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				purposeDescr = rs.getString("descr");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (tmgr != null) {
					tmgr.release();
				}
			} catch (Exception e) {
				
			}
		}

		return purposeDescr;

	}


}
