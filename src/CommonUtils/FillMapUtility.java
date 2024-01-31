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

import bean.LoginBean;
import databaseconnection.TableConstants;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import dobj.RenewalOfRegistrationDobj;
import impl.PermitImpl;
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

	public static Map<String, String> fillPmtSubCatgMap() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		final Map<String, String> permitSubcatgMap = new LinkedHashMap<String, String>();
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql;
		String descr = "";
		sql = "select code,descr from " + TableList.VM_PERMIT_CATG + " where state_cd=? order by 2";

		try {
			tmgr = new TransactionManagerReadOnly("get permit subcategory description");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, (String) session.getAttribute("state"));
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				permitSubcatgMap.put(String.valueOf(rs.getInt("code")), rs.getString("descr"));
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
		return permitSubcatgMap;

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
				regnTypeMap.put("'" + String.valueOf(rs.getString("regn_typecode") + "'"), rs.getString("descr"));
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
		contextAwareCodeMeanings.put("<22>", FillMapUtility.getFuelDescr());
		contextAwareCodeMeanings.put("<28>", FillMapUtility.fillPmtSubCatgMap());
		contextAwareCodeMeanings.put("<38>", FillMapUtility.getVehicleCatgDescr());
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

	public static Map<String, String> getFuelDescr() {

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

	public static Map<String, String> getVehicleCatgDescr() {
		Map<String, String> vehCatgdescr = new LinkedHashMap<String, String>();
		String sqlTmActionSQL = "SELECT catg, catg_desc FROM " + TableList.VM_VCH_CATG;
		TransactionManagerReadOnly tmgr = null;
		try {
			tmgr = new TransactionManagerReadOnly("vehicle_category_description");
			PreparedStatement prstmt = tmgr.prepareStatement(sqlTmActionSQL);
			RowSet rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				vehCatgdescr.put(rs.getString("catg"), rs.getString("catg_desc"));
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

		return vehCatgdescr;

	}

	public static boolean containsOnly61(String input) {
		// Define a regular expression pattern to match <61>
		// String patternString =
		// "<61>\\s+IN\\s*\\(\\d+(,\\s*\\d+)*\\)\\s*OR\\s*\\(.*\\)";
		String patternString = "<33>|<25>|<20>|<94>|<46>|<regn_no>|<22>|<42>|<46>|<26>|<28><29>|<38>|<2>|<0>|<81>|<82>|<35>|<60>";
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
		vmtaxslabfieldsmap.remove("<46>");
		vmtaxslabfieldsmap.put("<46>", "transport type");
		Map<String, String> codeMeanings = new LinkedHashMap<String, String>();
		StringBuffer result = new StringBuffer();
		expression = expression.trim();
		Map<String, Map<String, String>> contextAwareCodeMeanings = FillMapUtility
				.fetchContextAwareCodeMeaningsFromDatabase();
		String patternString = "<(\\d+)>|'([^']+)'|(\\b\\d+\\b)";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(expression);
		while (matcher.find()) {
			String code = matcher.group(); // Check if the code inside angle
											// brackets is present
			if (code.startsWith("<")) {

				codeMeanings = contextAwareCodeMeanings.get(code);
				String contextreplacement = (String) vmtaxslabfieldsmap.getOrDefault(code, code);
				matcher.appendReplacement(result, Matcher.quoteReplacement(contextreplacement));

			}

			else {
				String replacement;
				if (codeMeanings != null) {
					replacement = (String) codeMeanings.getOrDefault(code, code);
				} else {
					replacement = code;
				}
				matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));

			}
		}
		matcher.appendTail(result);
		System.out.println(result);
		return result.toString();

	}

	// upload doc at RTO end
	public static Boolean getDocumentUploadRTO(String stateCd, int purCd) {
		boolean uploadDocument = false;
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select is_doc_upload,pur_cd  from  " + TableList.TM_CONFIGURATION_DMS + " where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch dms");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
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

	// upload document at citizen end

	public static Boolean getDocumentUploadCitizen(String stateCd, int purCd) {
		boolean uploadDocument = false;
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select upload_doc,pur_cd from  " + TableList.VM_ONLINE_CONFIGURATION + " where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch doc upload");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
                
				boolean contains= rs.getString("pur_cd").contains(String.valueOf(purCd));
				uploadDocument = (rs.getBoolean("upload_doc"))
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

	// mobile authentication for citizen end
	public static boolean[] getAdhaarAndMobAuthentication(String stateCd, int purCd) {
		boolean auth[] = new boolean[2];
		// auth[0]=aadhar authentication, auth[1]=mobile authentication
		String service_auth_mode;
		boolean aadharauth=false;
		boolean mobauth=false;
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select service_auth_mode from   " + TableList.VM_ONLINE_SERVICECHECKS + " where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch doc upload");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			VehicleParameters parameter = new VehicleParameters();
			parameter.setPUR_CD(purCd);
			if (rs.next()) {
				service_auth_mode = rs.getString("service_auth_mode");
				String patternStringAadhar = "<A,\\(([^)]+)\\)>";
				String patternStringMobile = "<M,\\(([^)]+)\\)>";
				Pattern patternaadhar = Pattern.compile(patternStringAadhar);
				Pattern patternmobile = Pattern.compile(patternStringMobile);
				Matcher matcheraadhar = patternaadhar.matcher(service_auth_mode);
				Matcher matchermobile = patternmobile.matcher(service_auth_mode);
				if (matcheraadhar.find()) {
					// Extract the numbers after 'A'
					String aadharString = matcheraadhar.group(1);
					if(aadharString.contains(String.valueOf(purCd))|| aadharString.equals(String.valueOf(0)))
					 {
						
						aadharauth=true;
				     }
				  if (matchermobile.find()) {
					// Extract the numbers after 'M'
					String mobileString = matchermobile.group(1);
					   if(mobileString.contains(String.valueOf(purCd))|| mobileString.equals(String.valueOf(0)))
					   {
						   mobauth=true;
					   }

					

					}
				}
				

			}
			auth[0]=aadharauth;
			auth[1]=mobauth;

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
		return auth;

	}

	// fees applicable for registration related purposes
	public static Map<String, String> getFeesApplicableForNewRegn(String stateCd, int purCd) {
		Map<String, String> feesApplicable = new LinkedHashMap<String, String>();
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		VehicleParameters parameter = new VehicleParameters();
		parameter.setPUR_CD(purCd);
		if (purCd == TableConstants.VM_TRANSACTION_MAST_NEW_VEHICLE)

		{
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='NEW'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_REN_REG) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='REN'";
		}
		try {
			tmgr = new TransactionManagerReadOnly("fetch dms");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {

				String purpose = FillMapUtility.getPurposeDescr(rs.getInt("pur_cd"));
				String condition = rs.getString("condition_formula");
				if (condition.equalsIgnoreCase("true")) {
					feesApplicable.put(purpose, condition);
				} else {

					feesApplicable.put(purpose, FillMapUtility.interpretExpression(rs.getString("condition_formula")));
				}

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
		return feesApplicable;
	}

	public static boolean getApplInwardOtherRto(String stateCd, int purCd) {
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		boolean val = false;
		sql = "select pur_code from tm_configuration_appl_inward_anywhere_in_state where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch application inward other state ");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {

				if (rs.getString("pur_code").contains(String.valueOf(purCd))) {
					val = true;
				}

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
		return val;

	}

	public static String getFeesExempt(String stateCd, int purCd) {
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		String zeroFees="";
		sql = "select fee_amt_zero from  " + TableList.TM_CONFIGURATION + " where state_cd=? and pur_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch new registartion attributes");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			ps.setInt(2, purCd);
			rs = tmgr.fetchDetachedRowSet();
			VehicleParameters parameter = new VehicleParameters();
			parameter.setPUR_CD(purCd);
			if (rs.next()) {

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
		return zeroFees;

	}

}
