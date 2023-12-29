/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonUtils;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.sql.RowSet;

import databaseconnection.TransactionManager;
import databaseconnection.TransactionManagerInterface;
import databaseconnection.TransactionManagerReadOnly;


public class FormulaUtils {

	//private static final Logger LOGGER = Logger.getLogger(FormulaUtils.class);
	public static Map TagFieldsMap = null;
	public static Map TagFieldsMapVerify = null;
	public static Map TagDescrDisplay = null;
	

	static {
		getTagFields();
	}

	private static void getTagFields() {
		if (TagFieldsMap == null) {
			TagFieldsMap = new LinkedHashMap<>();
			if (TagFieldsMapVerify == null) {
				TagFieldsMapVerify = new LinkedHashMap<>();
			}
			if (TagDescrDisplay == null) {
				TagDescrDisplay = new LinkedHashMap<>();
			}

			String sql = "SELECT regexp_replace(code, '[^0-9]', '', 'g')::numeric as sr_no, code, descr, value_field, field_type FROM vm_tax_slab_fields order by 1";
			TransactionManagerInterface tmgr = null;
			try {
				tmgr = new TransactionManagerReadOnly("getTagFields");
				tmgr.prepareStatement(sql);
				RowSet rs = tmgr.fetchDetachedRowSet();
				while (rs.next()) {
					TagFieldsMap.put(rs.getString("code"), rs.getString("value_field"));
					TagFieldsMapVerify.put(rs.getString("code"), rs.getString("field_type"));
					TagDescrDisplay.put(rs.getString("code"), rs.getString("descr"));
				}
				// TagFieldsMap.put("<pDay>", "PMT_DAYS");
				// TagFieldsMap.put("<pMonth>", "PMT_MONTHS");
				// TagFieldsMap.put("<pCMonth>", "PMT_CEL_MONTH");
				// TagFieldsMap.put("<pYear>", "PMT_YEAR");
				// TagFieldsMap.put("<state_cd>", "STATE_CD");
				// TagFieldsMap.put("<regn_no>", "REGN_NO");
				// TagFieldsMap.put("<per_route>", "ROUTE_COUNT");
				// TagFieldsMap.put("<per_region>", "REGION_COUNT");
				// TagFieldsMap.put("<noc_ret>", "NOC_RETENTION");
				// TagFieldsMap.put("<tmp_purpose>", "TMP_PURPOSE");
				// TagFieldsMap.put("<exem_amount>", "EXEM_AMOUNT");
				// TagFieldsMap.put("<fine_to_be_taken>", "FINE_TO_BE_TAKEN");
				// TagFieldsMap.put("<special_tax_amt>", "SPECIAL_TAX_AMT");
				////////////////////////
				TagFieldsMap.put("<pDay>", "PMT_DAYS");
				TagFieldsMap.put("<pMonth>", "PMT_MONTHS");
				TagFieldsMap.put("<pCMonth>", "PMT_CEL_MONTH");
				TagFieldsMap.put("<pYear>", "PMT_YEAR");
				TagFieldsMap.put("<state_cd>", "STATE_CD");
				TagFieldsMap.put("<regn_no>", "REGN_NO");
				TagFieldsMap.put("<per_route>", "ROUTE_COUNT");
				TagFieldsMap.put("<per_region>", "REGION_COUNT");
				TagFieldsMap.put("<noc_ret>", "NOC_RETENTION");
				TagFieldsMap.put("<tmp_purpose>", "TMP_PURPOSE");
				TagFieldsMap.put("<exem_amount>", "EXEM_AMOUNT");
				TagFieldsMap.put("<fine_to_be_taken>", "FINE_TO_BE_TAKEN");
				TagFieldsMap.put("<multi_region>", "MULTI_REGION");
				TagFieldsMap.put("<multi_doc>", "MULTI_DOC");
				TagFieldsMap.put("<vehicle_hypth>", "VEHICLE_HYPTH");
				TagFieldsMap.put("<FIT_UPTO>", "FIT_UPTO");
				TagFieldsMap.put("<own_catg>", "OWN_CATG");
				TagFieldsMap.put("<OWNER_CATG>", "OWNER_CATG");
				TagFieldsMap.put("<to_reason>", "TO_REASON");
				TagFieldsMap.put("<fit_status>", "FIT_STATUS");
				TagFieldsMap.put("<pAmount>", "PMT_AMOUNT");
				TagFieldsMap.put("<excem_flag>", "EXCEM_FLAG");
				TagFieldsMap.put("<pOffer_Letter>", "OFFER_LETTER");
				TagFieldsMap.put("<pdup_reason>", "DUP_PMT_REASON");
				TagFieldsMap.put("<online_permit>", "ONLINE_PERMIT");
				TagFieldsMap.put("<offline_pay>", "OFFLINEPAYMENT");
				TagFieldsMap.put("<authYear>", "AUTHYEAR");
				TagFieldsMap.put("<daysWithinState>", "DAYSWITHINSTATE");
				TagFieldsMap.put("<transport_catg>", "TRANSPORT_CATG");
				TagFieldsMap.put("<inter_state>", "INTER_STATE");
				TagFieldsMap.put("<outside_route_cd>", "OUTSIDE_ROUTE_CD");
				TagFieldsMap.put("<pFineDays>", "P_FINE_DAYS");
				TagFieldsMap.put("<region_state>", "INTER_STATE");
				TagFieldsMap.put("<regn_state>", "INTRA_STATE");
				TagFieldsMap.put("<is_aadhar>", "IS_AADHAR");
				TagFieldsMap.put("<per_state>", "STATE_COUNT");
				TagFieldsMap.put("<aadhar_login>", "AADHAR_LOGIN");
				TagFieldsMap.put("<count_splpmt>", "COUNT_SPLPMT");
				TagFieldsMap.put("<splpmt_max_period>", "SPLPMT_MAX_PERIOD");
				TagFieldsMap.put("<same_month_spl_pmt>", "SAME_MONTH_SPL_PMT");
				TagFieldsMap.put("<SPLPMTDAYSWITHMONTH>", "SPLPMTDAYSWITHMONTH");
				TagFieldsMap.put("<AITP_HOMEAUTH_FEE>", "AITP_HOMEAUTH_FEE");
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (tmgr != null) {
						tmgr.release();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

		}
	}

	public static String getReturnValue(String inputString) throws Exception {
		String result = "";
		TransactionManagerInterface tmgr = null;
		try {
			if (inputString != null && inputString.equalsIgnoreCase("ONLINE")) {
				inputString = "'ONLINE'";
			}
			if (inputString != null && inputString.equalsIgnoreCase("OFFLINE")) {
				inputString = "'OFFLINE'";
			}
			if (inputString != null && inputString.equalsIgnoreCase("ALL")) {
				inputString = "'ALL'";
			}

			String sql = "select (" + inputString + ")::text as ret";
			tmgr = new TransactionManagerReadOnly("getTagFields");
			tmgr.prepareStatement(sql);
			RowSet rs = tmgr.fetchDetachedRowSet();

			if (rs.next()) {
				result = rs.getString("ret");
			}
		} finally {
			try {
				if (tmgr != null) {
					tmgr.release();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return result;
	}

	public static String replaceTagValues(String inputString, VehicleParameters dobj) {
		String retString = inputString;
		if (TagFieldsMap == null) {
			getTagFields();
		}
		Set entries = TagFieldsMap.entrySet();
		Iterator entryIter = entries.iterator();
		while (entryIter.hasNext()) {
			Map.Entry entry = (Map.Entry) entryIter.next();
			Object key = entry.getKey(); // Get the key from the entry.
			Object value = entry.getValue(); // Get the value.

			try {
				Method method = findMethod(dobj.getClass(),
						"get" + value.toString().substring(0, 1).toUpperCase() + value.toString().substring(1));
				if (method != null) {
					Class ab = method.getReturnType();

					Object retObj = method.invoke(dobj, null);
					if (retObj != null) {
						if (ab.isInstance(new String())) {
							retString = retString.replace(key.toString(), "'" + retObj.toString().toUpperCase() + "'");

						} else {
							retString = retString.replace(key.toString(), retObj.toString().toUpperCase());
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());			}

		}
		return retString;
	}

	public static String replaceTagValues(String inputString) {
		String retString = inputString;
		String replaceChar = "";
		if (TagFieldsMapVerify == null) {
			getTagFields();
		}
		Set entries = TagFieldsMapVerify.entrySet();
		Iterator entryIter = entries.iterator();
		while (entryIter.hasNext()) {
			Map.Entry entry = (Map.Entry) entryIter.next();
			Object key = entry.getKey(); // Get the key from the entry.
			Object value = entry.getValue(); // Get the value.
			if (value.toString().equalsIgnoreCase("N")) {
				replaceChar = "1";
			} else if (value.toString().equalsIgnoreCase("C")) {
				replaceChar = "'Y'";
			} else {
				replaceChar = "";
			}

			try {
				retString = retString.replace(key.toString(), replaceChar);
			} catch (Exception e) {
				System.out.println(e.getMessage());			}

		}
		return retString;
	}

	public static String makeIfCondition(String type, String condString, String rateString) {
		String retCondString = condString;
		String retRateString = rateString;
		String retType = type;
		String retString = "";

		if (TagFieldsMap == null) {
			getTagFields();
		}
		if (type.equalsIgnoreCase("T")) {
			retType = "Amount";
		} else if (type.equalsIgnoreCase("R")) {
			retType = "Rebate Amount";
		} else if (type.equalsIgnoreCase("S")) {
			retType = "Surcharge Amount";
		} else if (type.equalsIgnoreCase("M")) {
			retType = "Minimum Amount";
		} else if (type.equalsIgnoreCase("X")) {
			retType = "Maximum Amount";
		}
		Set entries = TagFieldsMap.entrySet();
		Iterator entryIter = entries.iterator();
		while (entryIter.hasNext()) {
			Map.Entry entry = (Map.Entry) entryIter.next();
			Object key = entry.getKey(); // Get the key from the entry.
			Object value = entry.getValue(); // Get the value.
			try {
				retCondString = retCondString.replace(key.toString(), "{" + value.toString() + "}");
				retRateString = retRateString.replace(key.toString(), "{" + value.toString() + "}");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (retCondString.length() > 0) {
			retString = "<font color=\"blue\">If</font> (" + retCondString
					+ ")<br/> <font color=\"blue\">then</font> <br/>";
		}
		retString = retString + retType + " <font color=\"blue\">=</font> (" + retRateString + ")";

		return retString;
	}

	private static Method findMethod(Class<?> clazz, String methodName) {
		for (Method method : clazz.getMethods()) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}

		// throw new NoSuchMethodException();
		return null;
	}

	public static boolean isCondition(String inputString) {
		boolean status = false;
		TransactionManagerInterface tmgr = null;
		try {
			String sql = "select (" + inputString + ")::boolean as ret";
			tmgr = new TransactionManagerReadOnly("getTagFields");
			tmgr.prepareStatement(sql);
			RowSet rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				status = rs.getBoolean("ret");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = false;
		} finally {
			try {
				if (tmgr != null) {
					tmgr.release();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());			}
		}
		return status;
	}

	public static boolean isCondition(String inputString, String pathExceptionDtls) {
		boolean status = false;
		TransactionManagerInterface tmgr = null;
		try {
			String sql = "select (" + inputString + ")::boolean as ret";
			tmgr = new TransactionManagerReadOnly("getTagFields");
			tmgr.prepareStatement(sql);
			RowSet rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				status = rs.getBoolean("ret");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = false;
		} finally {
			try {
				if (tmgr != null) {
					tmgr.release();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return status;
	}

	public static boolean isVerifyFormula(String inputString) {
		boolean status = false;
		TransactionManagerInterface tmgr = null;

		try {
			String sql = "select (" + inputString + ")::boolean as ret";
			tmgr = new TransactionManagerReadOnly("getTagFields");
			tmgr.prepareStatement(sql);
			RowSet rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				status = rs.getBoolean("ret");
			}
			status = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = false;
		} finally {
			try {
				if (tmgr != null) {
					tmgr.release();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return status;
	}

	public static double getFormulaValue(String inputString) throws Exception {
		double dblAmt = 0;
		TransactionManagerInterface tmgr = null;
		try {
			String sql = "select (" + inputString + ")::numeric as ret";
			tmgr = new TransactionManagerReadOnly("getTagFields");
			tmgr.prepareStatement(sql);
			RowSet rs = tmgr.fetchDetachedRowSet();

			if (rs.next()) {
				dblAmt = rs.getDouble("ret");
			}
		} finally {
			try {
				if (tmgr != null) {
					tmgr.release();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return dblAmt;
	}

//	public static VehicleParameters fillVehicleParametersFromDobj(OwnerDetailsDobj ownerDobj) {
//
//		VehicleParameters taxParameters = new VehicleParameters();
//		try {
//
//			taxParameters.setAC_FITTED(ownerDobj.getAc_fitted());
//			// taxParameters.setCALENDARMONTH(ownerDobj.getc);
//			taxParameters.setCC(ownerDobj.getCubic_cap());// float type
//			// taxParameters.setDELAYDAYS(ownerDo);
//			taxParameters.setFLOOR_AREA(ownerDobj.getFloor_area());
//			taxParameters.setFUEL(ownerDobj.getFuel());
//			taxParameters.setHP(ownerDobj.getHp());
//			taxParameters.setLD_WT(ownerDobj.getLd_wt());
//			taxParameters.setOWNER_CD(ownerDobj.getOwner_cd());
//			taxParameters.setREGN_TYPE(ownerDobj.getRegn_type());
//			taxParameters.setSALE_AMT(ownerDobj.getSale_amt());
//			taxParameters.setSEAT_CAP(ownerDobj.getSeat_cap());
//			taxParameters.setSLEEPAR_CAP(ownerDobj.getSleeper_cap());
//			taxParameters.setSTAND_CAP(ownerDobj.getStand_cap());
//			taxParameters.setSTATE_CD(ownerDobj.getState_cd());
//			taxParameters.setUNLD_WT(ownerDobj.getUnld_wt());
//			taxParameters.setVH_CLASS(ownerDobj.getVh_class());
//			taxParameters.setTAX_MODE(ownerDobj.getTax_mode());
//			taxParameters.setREGN_DATE(ownerDobj.getRegn_dt());// DateUtils.parseDate(
//			taxParameters.setTAX_DUE_FROM_DATE(ownerDobj.getRegn_dt());// DateUtils.parseDate(
//			taxParameters.setVCH_CATG(ownerDobj.getVch_catg() != null ? ownerDobj.getVch_catg().trim() : null);
//			taxParameters.setVH_CLASS(ownerDobj.getVh_class());
//			taxParameters.setSEAT_CAP(ownerDobj.getSeat_cap());
//
//			// 12-jan-216
//			// 12-01-2016
//			taxParameters.setVCH_AGE((int) Math.ceil(
//					DateUtils.getDate1MinusDate2_Months(DateUtils.parseDate(ownerDobj.getPurchase_dt()), new Date())
//							/ 12.0));
//
//			taxParameters.setVCH_TYPE(ServerUtil.VehicleClassType(ownerDobj.getVh_class()));
//		} catch (Exception e) {
//			LOGGER.error("FormulaUtils method : fillVehicleParametersFromDobj() " + e.getMessage());
//		}
//
//		return taxParameters;
//	}
//
//	public static VahanTaxParameters fillTaxParametersFromDobj(Owner_dobj ownerDobj,
//			PassengerPermitDetailDobj permitDobj) {
//
//		VahanTaxParameters taxParameters = new VahanTaxParameters();
//		try {
//			taxParameters.setAC_FITTED(ownerDobj.getAc_fitted());
//			taxParameters.setCC((float) ownerDobj.getCubic_cap());// float type
//			taxParameters.setFLOOR_AREA((float) ownerDobj.getFloor_area());
//			taxParameters.setFUEL(ownerDobj.getFuel());
//			taxParameters.setHP((float) ownerDobj.getHp());
//			taxParameters.setLD_WT(ownerDobj.getLd_wt());
//			taxParameters.setOWNER_CD(ownerDobj.getOwner_cd());
//			taxParameters.setREGN_TYPE(ownerDobj.getRegn_type());
//			taxParameters.setSALE_AMT(ownerDobj.getSale_amt());
//			taxParameters.setSEAT_CAP(ownerDobj.getSeat_cap());
//			taxParameters.setSLEEPAR_CAP(ownerDobj.getSleeper_cap());
//			taxParameters.setSTAND_CAP(ownerDobj.getStand_cap());
//			taxParameters.setSTATE_CD(ownerDobj.getState_cd());
//			taxParameters.setUNLD_WT(ownerDobj.getUnld_wt());
//			taxParameters.setVH_CLASS(ownerDobj.getVh_class());
//			taxParameters.setTAX_MODE(ownerDobj.getTax_mode());
//			taxParameters.setREGN_DATE(DateUtils.parseDate(ownerDobj.getRegn_dt()));
//			taxParameters.setTAX_DUE_FROM_DATE(DateUtils.parseDate(ownerDobj.getRegn_dt()));
//			taxParameters.setVCH_IMPORTED(ownerDobj.getImported_vch());
//			taxParameters.setVCH_CATG(ownerDobj.getVch_catg());
//			taxParameters.setOTHER_CRITERIA(ownerDobj.getOther_criteria());
//			taxParameters.setVEH_PURCHASE_AS(ownerDobj.getVch_purchase_as());
//			taxParameters.setVCH_TYPE(ServerUtil.VehicleClassType(ownerDobj.getVh_class()));
//			// taxParameters.setVCHAGE((int)
//			// Math.ceil(DateUtils.getDate1MinusDate2_Months(ownerDobj.getPurchase_dt(),
//			// new Date()) / 12.0));
//			if (permitDobj != null) {
//				if (permitDobj.getPmt_type_code() != null && !permitDobj.getPmt_type_code().equals("")) {
//					taxParameters.setPERMIT_TYPE(Integer.parseInt(permitDobj.getPmt_type_code()));
//				}
//				if (permitDobj.getPmtCatg() != null && !permitDobj.getPmtCatg().equals("")) {
//					taxParameters.setPERMIT_SUB_CATG(Integer.parseInt(permitDobj.getPmtCatg()));
//				}
//				if (permitDobj.getServices_TYPE() != null && !permitDobj.getServices_TYPE().equals("")) {
//					taxParameters.setSERVICE_TYPE(Integer.parseInt(permitDobj.getServices_TYPE()));
//				}
//				if (permitDobj.getDomain_CODE() != null && !permitDobj.getDomain_CODE().equals("")) {
//					taxParameters.setDOMAIN_CD(Integer.parseInt(permitDobj.getDomain_CODE()));
//				}
//				if (permitDobj.getNumberOfTrips() != null && !permitDobj.getNumberOfTrips().equals("")) {
//					taxParameters.setNO_OF_ROUTES(Integer.parseInt(permitDobj.getNumberOfTrips()));
//				}
//				if (permitDobj.getRout_length() != null && !permitDobj.getRout_length().equals("")) {
//					taxParameters.setROUTE_LENGTH(Double.parseDouble(permitDobj.getRout_length()));
//				}
//			}
//
//		} catch (Exception e) {
//			LOGGER.error("FormulaUtils method : fillTaxParametersFromDobj() " + e.getMessage());
//		}
//		return taxParameters;
//	}
//
//	// public static VehicleParameters fillPermitParametersFromDobj(Owner_dobj
//	// ownerDobj,
//	// PassengerPermitDetailDobj pmtDobj, int pDay, int pMonth, int pCMonth, int
//	// pYear, int exem_amount,
//	// int fine_to_be_taken, float special_tax) {
//	// VehicleParameters pmtParameters = new VehicleParameters();
//	// try {
//	// if (ownerDobj != null) {
//	// if (ownerDobj.getVh_class() != 0) {
//	// pmtParameters.setVH_CLASS(ownerDobj.getVh_class());
//	// }
//	// if (!CommonUtils.isNullOrBlank(ownerDobj.getVch_catg())) {
//	// pmtParameters.setVCH_CATG(ownerDobj.getVch_catg());
//	// }
//	// if (!CommonUtils.isNullOrBlank(ownerDobj.getState_cd())) {
//	// pmtParameters.setSTATE_CD(ownerDobj.getState_cd());
//	// }
//	// if (!CommonUtils.isNullOrBlank(ownerDobj.getRegn_no())) {
//	// pmtParameters.setREGN_NO(ownerDobj.getRegn_no());
//	// }
//	// if (ownerDobj.getFuel() > 0) {
//	// pmtParameters.setFUEL(ownerDobj.getFuel());
//	// }
//	// if (ownerDobj.getLd_wt() > 0) {
//	// pmtParameters.setLD_WT(ownerDobj.getLd_wt());
//	// }
//	// pmtParameters.setSEAT_CAP(ownerDobj.getSeat_cap());
//	// }
//	// if (pmtDobj != null) {
//	// if (!CommonUtils.isNullOrBlank(pmtDobj.getPmt_type_code())) {
//	// pmtParameters.setPERMIT_TYPE(Integer.parseInt(pmtDobj.getPmt_type_code().trim()));
//	// }
//	// if (!CommonUtils.isNullOrBlank(pmtDobj.getPmtCatg())) {
//	// pmtParameters.setPERMIT_SUB_CATG(Integer.parseInt(pmtDobj.getPmtCatg().trim()));
//	// }
//	//
//	// if (!CommonUtils.isNullOrBlank(pmtDobj.getRout_length())) {
//	// pmtParameters.setROUTE_COUNT(Integer.parseInt(pmtDobj.getRout_length().trim()));
//	// }
//	//
//	// if (!CommonUtils.isNullOrBlank(pmtDobj.getRegion_covered())) {
//	// pmtParameters.setREGION_COUNT(Integer.parseInt(pmtDobj.getRegion_covered().trim()));
//	// }
//	//
//	// }
//	// if (pDay != 0) {
//	// pmtParameters.setPMT_DAYS(pDay);
//	// }
//	// if (pMonth != 0) {
//	// pmtParameters.setPMT_MONTHS(pMonth);
//	// }
//	// if (pCMonth != 0) {
//	// pmtParameters.setPMT_CEL_MONTH(pCMonth);
//	// }
//	// if (pYear != 0) {
//	// pmtParameters.setPMT_YEAR(pYear);
//	// }
//	// if (exem_amount != 0) {
//	// pmtParameters.setEXEM_AMOUNT(exem_amount);
//	// } else {
//	// pmtParameters.setEXEM_AMOUNT(0);
//	// }
//	// if (fine_to_be_taken != 0) {
//	// pmtParameters.setFINE_TO_BE_TAKEN(fine_to_be_taken);
//	// } else {
//	// pmtParameters.setFINE_TO_BE_TAKEN(0);
//	// }
//	// if (special_tax != 0.0) {
//	// pmtParameters.setSPECIAL_TAX_AMT(special_tax);
//	// } else {
//	// pmtParameters.setSPECIAL_TAX_AMT(0.0F);
//	// }
//	//
//	// } catch (Exception e) {
//	// LOGGER.error(e);
//	// }
//	//
//	// return pmtParameters;
//	// }
//
	public static String replaceTagPermitValues(String inputString, VehicleParameters dobj) {
		String retString = inputString;
		if (TagFieldsMap == null) {
			getTagFields();
		}
		Set entries = TagFieldsMap.entrySet();
		Iterator entryIter = entries.iterator();
		while (entryIter.hasNext()) {
			Map.Entry entry = (Map.Entry) entryIter.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			try {
				Method method = findMethod(dobj.getClass(),
						"get" + value.toString().substring(0, 1).toUpperCase() + value.toString().substring(1));
				Class ab = method.getReturnType();
				Object retObj = method.invoke(dobj, null);
				if (ab.isInstance(new String())) {
					retString = retString.replace(key.toString(), "'" + retObj.toString().toUpperCase() + "'");
				} else {
					retString = retString.replace(key.toString(), retObj.toString().toUpperCase());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return retString;
	}}

