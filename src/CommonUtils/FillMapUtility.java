package CommonUtils;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import oracle.net.aso.e;

public class FillMapUtility {

	static public Map<String, String> TagFieldMap = null;

	public static Map<String, String> fillPurposeCodeMap() {

		final Map<String, String> purposecodemap = new LinkedHashMap<String, String>();
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql, sql1 = null;
		String descr = "";
		sql = "select pur_cd,descr from " + TableList.TM_PURPOSE_MAST + " order by pur_cd";

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

	public static Map<String, String> fillPmtTypeMap() {
		final Map<String, String> permitTypeMap = new LinkedHashMap<String, String>();
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

	public static Map<String, String> fillOwnerType() {
		final Map<String, String> ownerTypeMap = new LinkedHashMap<String, String>();
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql, sql1 = null;
		String descr = "";
		sql = "select ow_code,descr from " + TableList.VM_OWCODE;

		try {
			tmgr = new TransactionManagerReadOnly("get code description");
			ps = tmgr.prepareStatement(sql);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				ownerTypeMap.put(String.valueOf(rs.getInt("ow_code")), rs.getString("descr"));
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
		return ownerTypeMap;

	}

	public static Map<String, String> fillRegnType() {
		final Map<String, String> regnTypeMap = new LinkedHashMap<String, String>();
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql, sql1 = null;
		String descr = "";
		sql = "select regn_typecode,descr from " + TableList.VM_REGN_TYPE;

		try {
			tmgr = new TransactionManagerReadOnly("get registration type description");
			ps = tmgr.prepareStatement(sql);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				regnTypeMap.put(String.valueOf(rs.getString("regn_typecode")), rs.getString("descr"));
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
		return regnTypeMap;

	}

	public static Map<String, String> fillVehicleClassMap() {
		final Map<String, String> vehClassMap = new LinkedHashMap<String, String>();
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

	public static Map<String, String> getCodeDescr() {
		TransactionManagerReadOnly tmgr = null;
		Map<String, String> vmtaxslabfieldsmap = new LinkedHashMap<String, String>();
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		String descr = "";
		sql = "select code,descr from " + TableList.VM_TAX_SLAB_FIELDS;
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

	public static Map<String, String> getOfficeDescr() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		TransactionManagerReadOnly tmgr = null;
		Map<String, String> officedescr = new LinkedHashMap<String, String>();
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		String descr = "";
		sql = "select off_cd,off_name from " + TableList.TM_OFFICE + " where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("get office description");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, (String) session.getAttribute("state"));
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				officedescr.put(rs.getString("off_cd"), rs.getString("off_name"));
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
		return officedescr;

	}

	public static Map<String, Map<String, String>> fetchContextAwareCodeMeaningsFromDatabase() {
		Map<String, Map<String, String>> contextAwareCodeMeanings = new HashMap<>();
		contextAwareCodeMeanings.put("<61>", FillMapUtility.fillPurposeCodeMap());
		contextAwareCodeMeanings.put("<25>", FillMapUtility.fillPmtTypeMap());
		contextAwareCodeMeanings.put("<33>", FillMapUtility.fillVehicleClassMap());
		contextAwareCodeMeanings.put("<94>", FillMapUtility.getOfficeDescr());
		contextAwareCodeMeanings.put("<20>", FillMapUtility.fillOwnerType());
		contextAwareCodeMeanings.put("<42>", FillMapUtility.fillRegnType());
		contextAwareCodeMeanings.put("<22>",FillMapUtility.getFuelDescr());
		Map<String, String> vehTypeMap = new LinkedHashMap<String, String>();
		Map<String, String> vehPurchasedAs = new LinkedHashMap<String, String>();
		vehTypeMap.put("1", "Transport");
		vehTypeMap.put("2", "Non Transport");
		vehPurchasedAs.put("B", "fully built");
		vehPurchasedAs.put("C", "chasis");
		contextAwareCodeMeanings.put("<46>", vehTypeMap);
		contextAwareCodeMeanings.put("<26>", vehPurchasedAs);
	    return contextAwareCodeMeanings;
	}

	public static String getActionDescr(int actionCode) {

		String actionDescr = null;
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
	// fuel
	
	public static Map<String, String>  getFuelDescr()
	{

	    Map<String, String> fuelDescr = new LinkedHashMap<String, String>();
		String sqlTmActionSQL = "SELECT code,descr FROM  " + TableList.VM_FUEL;
		TransactionManagerReadOnly tmgr = null;
		try {
			tmgr = new TransactionManagerReadOnly("fuel description");
			PreparedStatement prstmt = tmgr.prepareStatement(sqlTmActionSQL);
			RowSet rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				fuelDescr.put(String.valueOf(rs.getInt("code")), rs.getString("descr"));
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

		return fuelDescr;

	}


	public static String getPurposeDescr(int purCd) {
		String purposeDescr = null;
		String sqlTmActionSQL = "SELECT descr FROM " + TableList.TM_PURPOSE_MAST + "  WHERE pur_cd = ?";
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
	
	public static boolean containsOnly61(String input) {
		// Define a regular expression pattern to match <61>
		// String patternString =
		// "<61>\\s+IN\\s*\\(\\d+(,\\s*\\d+)*\\)\\s*OR\\s*\\(.*\\)";
		String patternString = "<33>|<25>|<20>|<94>|<46>|<regn_no>|<22>|<42>|<46>|<26>|<28>";
		Pattern pattern = Pattern.compile(patternString);

		// Create a matcher with the input string
		Matcher matcher = pattern.matcher(input);

		// Check if the pattern is found in the input
		if (matcher.find()) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public static String interpretExpression(String expression) {
		Map<String, String> vmtaxslabfieldsmap = new LinkedHashMap<String, String>();
		vmtaxslabfieldsmap = FillMapUtility.getCodeDescr();
		Map<String, String> codeMeanings = new LinkedHashMap<String, String>();
		StringBuffer result = new StringBuffer();
		expression = expression.trim();
		Map<String, Map<String, String>> contextAwareCodeMeanings = FillMapUtility.fetchContextAwareCodeMeaningsFromDatabase();
		String patternString = "<(\\d+)>|(\\b\\d+\\b)";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(expression);
		while (matcher.find()) {
			String code = matcher.group(); // Check if the code inside angle
											// brackets is present
			if (code.startsWith("<")) {

				codeMeanings = contextAwareCodeMeanings.get(code);
				String contextreplacement = (String) vmtaxslabfieldsmap.getOrDefault(code,code);
				matcher.appendReplacement(result, Matcher.quoteReplacement(contextreplacement));

			} else {

				String replacement = (String) codeMeanings.getOrDefault(code, code);
				matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
				
			}
		}
		matcher.appendTail(result);
		System.out.println(result);
		return result.toString();
		

	}

}
