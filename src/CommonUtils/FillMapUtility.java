package CommonUtils;

import java.sql.PreparedStatement;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.RowSet;

import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;

public class FillMapUtility {

	static public Map<String,String> TagFieldMap = null;

	public static void fillPurposeCodeMap() {
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
				TagFieldMap.put(String.valueOf(rs.getInt("pur_cd")), rs.getString("descr"));
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

	}

	public static void fillPmtTypeMap() {
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
				TagFieldMap.put(String.valueOf(rs.getInt("code")), rs.getString("descr"));
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

	}
	
	
	public static String getCodeDescr() {
		TransactionManagerReadOnly tmgr = null;
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
				TagFieldMap.put(rs.getString("code"), rs.getString("descr"));
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
		return descr;

	}


	public static Map<String,String>fillMap() {
		TagFieldMap = new LinkedHashMap<>();
		getCodeDescr();
		fillPmtTypeMap();
		fillPurposeCodeMap();
		return TagFieldMap;

	}

}
