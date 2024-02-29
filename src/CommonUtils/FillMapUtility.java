package CommonUtils;

import java.io.Reader;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import bean.LoginBean;
import databaseconnection.TableConstants;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import dobj.CitizenServiceFlowDobj;
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

	public static String getVehClassDecr(int vehClass) {
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql, sql1 = null;
		String descr = "";
		sql = "select descr from " + TableList.VM_VH_CLASS + " where vh_class=?";

		try {
			tmgr = new TransactionManagerReadOnly("get vehicle class description");
			ps = tmgr.prepareStatement(sql);
			ps.setInt(1, vehClass);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				descr = rs.getString("descr");
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
		return descr;

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
		contextAwareCodeMeanings.put("<15>", FillMapUtility.fillPurposeCodeMap());
		contextAwareCodeMeanings.put("<25>", FillMapUtility.fillPmtTypeMap());
		contextAwareCodeMeanings.put("<33>", FillMapUtility.fillVehicleClassMap());
		contextAwareCodeMeanings.put("<94>", FillMapUtility.getOfficeDescr());
		contextAwareCodeMeanings.put("<20>", FillMapUtility.fillOwnerType());
		contextAwareCodeMeanings.put("<42>", FillMapUtility.fillRegnType());
		contextAwareCodeMeanings.put("<22>", FillMapUtility.getFuelDescr());
		contextAwareCodeMeanings.put("<28>", FillMapUtility.fillPmtSubCatgMap());
		contextAwareCodeMeanings.put("<38>", FillMapUtility.getVehicleCatgDescr());
		contextAwareCodeMeanings.put("<action_cd>", FillMapUtility.fillActionCdDescr());
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

	public static Map<String, String> fillActionCdDescr() {

		Map<String, String> actionDescrMap = new LinkedHashMap<String, String>();
		String sql = "SELECT action_cd,action_descr FROM  " + TableList.TM_ACTION;
		TransactionManagerReadOnly tmgr = null;
		try {
			tmgr = new TransactionManagerReadOnly("action code description");
			PreparedStatement prstmt = tmgr.prepareStatement(sql);
			RowSet rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				actionDescrMap.put(String.valueOf(rs.getInt("action_cd")), rs.getString("action_descr"));
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

		return actionDescrMap;

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

		Map<String, String> vmTaxSlabFields = getCodeDescr();
		vmTaxSlabFields.remove("<61");
		String patternString = vmTaxSlabFields.keySet().stream().map(Object::toString).collect(Collectors.joining("|"));

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

		Map<String, String> codeMeanings = new LinkedHashMap<String, String>();
		
		StringBuffer result = new StringBuffer();
		expression = expression.trim();
     	//String patternString = "<(\\d+)>|'([^']+)'|(\\b\\d+\\b)";
		String patternString ="<([^>]+)>|'([^']+)'|(\\b\\d+\\b)";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(expression);
		while (matcher.find()) {
			String code = matcher.group(); // Check if the code inside angle
											// brackets is present
			if (code.startsWith("<")) {
			
				codeMeanings = LoginBean.contextAwareCodeMeanings.get(code);
				String contextreplacement = (String) LoginBean.vmtaxslabfieldsmap.getOrDefault(code, code);
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
		return result.toString()+")";

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

				String purposes = rs.getString("pur_cd");
				String[] purposeArray = purposes.split(",");
				for (String purpose : purposeArray) {
					if (Integer.parseInt(purpose) == purCd) {
						uploadDocument = rs.getBoolean("is_doc_upload");
						break;
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

				String purposes = rs.getString("pur_cd");
				String[] purposeArray = purposes.split(",");
				for (String purpose : purposeArray) {
					if (Integer.parseInt(purpose) == purCd) {
						uploadDocument = rs.getBoolean("is_doc_upload");
						break;
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
		return uploadDocument;

	}

	// mobile authentication for citizen end
	public static boolean[] getAdhaarAndMobAuthentication(String stateCd, int purCd) {
		boolean auth[] = new boolean[2];
		// auth[0]=aadhar authentication, auth[1]=mobile authentication
		String service_auth_mode;
		boolean aadharauth = false;
		boolean mobauth = false;
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
					aadharauth = aadharString.equals(String.valueOf(0));
					if (!aadharauth) {
						String[] purposeArray = aadharString.split(",");
						for (String purpose : purposeArray) {
							if (Integer.parseInt(purpose) == purCd) { // Check
																		// if
																		// purCd
																		// matches
																		// any
								aadharauth = true; // value
								break; // Exit loop if found
							}
						}

					}
				}

				if (matchermobile.find()) {
					// Extract the numbers after 'M'
					String mobileString = matchermobile.group(1);
					mobauth = mobileString.equals(String.valueOf(0));
					if (!mobauth) {
						String[] purposeArray = mobileString.split(",");
						for (String purpose : purposeArray) {
							if (Integer.parseInt(purpose) == purCd) { // Check
																		// if
																		// purCd
																		// matches
																		// any
								mobauth = true; // value
								break; // Exit loop if found
							}
						}

					}

				}
			}

			auth[0] = aadharauth;
			auth[1] = mobauth;

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
		if (purCd == TableConstants.VM_TRANSACTION_MAST_NEW_VEHICLE
				|| purCd == TableConstants.VM_TRANSACTION_MAST_DEALER_NEW_VEHICLE)

		{
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='NEW'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_REN_REG) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='REN'";
		}

		else if (purCd == TableConstants.VM_TRANSACTION_MAST_DEALER_NEW_TEMP_VEHICLE
				|| purCd == TableConstants.VM_TRANSACTION_MAST_TEMP_REG) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='TMP'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_DUP_RC) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='DUP'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_DUP_FC) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='DFC'";
		}

		else if (purCd == TableConstants.VM_TRANSACTION_MAST_TO) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='TO'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_CHG_ADD) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='CA'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_VEH_CONVERSION) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='CON'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_VEH_ALTER) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='ALT'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_ADD_HYPO) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='HPA'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_REM_HYPO) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='HPT'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_HPC) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='HPC'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_NOC) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='NOC'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_FIT_CERT) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='FIT'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_NEW_VEHICLE_FITNESS) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='NVF'";

		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_FRESH_RC) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='FRC'";
		} else if (purCd == TableConstants.VM_MAST_RC_CANCELLATION) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='CRC'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_NOC) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='NOC'";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_NOC_CANCEL) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='CNO'";
		} else if (purCd == TableConstants.VM_TRANSACTION_CONVERSION_PAPER_RC_TO_SMARTCARD) {
			sql = "select pur_cd,condition_formula from  " + TableList.VC_ACTION_PURPOSE_MAP
					+ " where state_cd=? and action='CRS'";
		}

		try {
			tmgr = new TransactionManagerReadOnly("fetch fees");
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
				String purposes = rs.getString("pur_cd");
				String[] purposeArray = purposes.split(",");
				for (String purpose : purposeArray) {
					if (Integer.parseInt(purpose) == purCd) {
						val = true;
						break;
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
		return val;

	}

	public static String getFeesExempt(String stateCd, int purCd) {
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		String zeroFees = "";
		sql = "select fee_amt_zero from  " + TableList.TM_CONFIGURATION + " where state_cd=? ";
		try {
			tmgr = new TransactionManagerReadOnly("fetch new registartion attributes");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
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

	public String getTaxExempt(String stateCd, int purCd) {

		String taxExempt = "";
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select tax_exemption from  " + TableList.TM_CONFIGURATION + " where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch new registartion attributes");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			VehicleParameters parameter = new VehicleParameters();
			parameter.setPUR_CD(1);
			if (rs.next()) {

				taxExempt = rs.getString("tax_exemption");
				boolean taxExemption = FormulaUtils
						.isCondition(FormulaUtils.replaceTagPermitValues(taxExempt, parameter));
				if (FillMapUtility.containsOnly61(taxExempt)) {
					taxExempt = taxExemption ? "true" : "false";
				} else {
					if (!taxExempt.contains("NOT")) {
						if (taxExemption == true) {
							taxExempt = "true";
						}
					} else {
						taxExempt = interpretExpression(taxExempt);
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
		return taxExempt;

	}

	public static boolean isServiceRto(String stateCd, int purCd)

	{
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		boolean isServiceRto = false;
		sql = "select * from " + TableList.TM_PURPOSE_ACTION_FLOW + " where state_cd=? and pur_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch flow from RTO");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			ps.setInt(2, purCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {

				isServiceRto = true;

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
		return isServiceRto;

	}

	public static boolean isServiceCitizen(String stateCd, int purCd)

	{
		boolean isServiceCitizen = false;
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select pur_cd from " + TableList.VM_STATE_RUNNING_SERVICES + " where state_cd=?";
		try {
			tmgr = new TransactionManagerReadOnly("fetch whether service runs on citizen");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {

				String purposes = rs.getString("pur_cd");
				String[] purposeArray = purposes.split(",");
				for (String purpose : purposeArray) {
					if (Integer.parseInt(purpose) == purCd) {
						isServiceCitizen = true;
						break;
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
		return isServiceCitizen;
	}

	public static ArrayList<CitizenServiceFlowDobj> getCitizenServiceFlow(String stateCd, int purCd,
			CitizenServiceFlowDobj citizenFlow) {

		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql1 = null;
		ArrayList<CitizenServiceFlowDobj> flowCitizen = new ArrayList<>();
		sql1 = "select flow_srno,action_cd,condition_formula,isbackward,auto_approval_allowed from "
				+ TableList.TM_PURPOSE_ACTION_FLOW_CITIZEN + " where state_cd=? and pur_cd=? order by flow_srno";
		try {
			tmgr = new TransactionManagerReadOnly("fetch flow from Citizen");
			ps = tmgr.prepareStatement(sql1);
			ps.setString(1, stateCd);
			ps.setInt(2, purCd);
			rs = tmgr.fetchDetachedRowSet();
			while (rs.next()) {
				CitizenServiceFlowDobj dobj = new CitizenServiceFlowDobj();
				dobj.setFlow_srno(rs.getInt("flow_srno"));
				dobj.setAction_cd((rs.getInt("action_cd")));
				dobj.setAction_descr(getActionDescr(rs.getInt("action_cd")));
				if (rs.getString("condition_formula").equalsIgnoreCase("true")) {
					dobj.setCondition_formula(rs.getString("condition_formula"));
				} else {
					dobj.setCondition_formula(FillMapUtility.interpretExpression(rs.getString("condition_formula")));
				}
				dobj.setBackward(rs.getString("isbackward"));
				dobj.setAutoapproval(rs.getBoolean("auto_approval_allowed"));
				flowCitizen.add(dobj);
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
		return flowCitizen;
	}
	// for blacklist

	
}
