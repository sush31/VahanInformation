//package server;
//
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author tranC074
// */
//import java.io.ByteArrayOutputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Serializable;
//import java.io.StringReader;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.TimeZone;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.faces.model.SelectItem;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.sql.RowSet;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.transform.stream.StreamSource;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//import org.codehaus.jackson.map.DeserializationConfig;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
//
//import nic.java.util.DateUtils;
//import nic.java.util.DateUtilsException;
//import nic.rto.vahan.common.MsgProperties;
//import nic.rto.vahan.common.VahanException;
//import nic.rto.vahan.common.VahanMessageException;
//import nic.transport.homologation.web.service.DataportwsProxy;
//import nic.transport.homologation.web.service.InsuranceInfoDobj;
//import nic.vahan.CommonUtils.FormulaUtils;
//import nic.vahan.CommonUtils.VehicleParameters;
//import nic.vahan.ICICIServicesNew.TaxResponseEntity;
//import nic.vahan.Login.LoginImpl;
//import nic.vahan.common.jsf.utils.JSFUtils;
//import nic.vahan.common.jsf.utils.validators.POSValidator;
//import nic.vahan.common.permit.fee.dobj.PermitFeeRecieptDobj;
//import nic.vahan.common.tax.VahanTaxClient;
//import nic.vahan.db.DocumentType;
//import nic.vahan.db.MasterTableFiller;
//import nic.vahan.db.TableConstants;
//import nic.vahan.db.TableList;
//import nic.vahan.db.connection.TransactionManager;
//import nic.vahan.db.connection.TransactionManagerInterface;
//import nic.vahan.db.connection.TransactionManagerReadOnly;
//import nic.vahan.db.user_mgmt.dobj.UserAuthorityDobj;
//import nic.vahan.dl.serviceXml.ResponceList;
//import nic.vahan.form.dobj.AdjustmentTaxDobj;
//import nic.vahan.form.dobj.BlackListedVehicleDobj;
//import nic.vahan.form.dobj.ChallanApidobj;
//import nic.vahan.form.dobj.DOTaxDetail;
//import nic.vahan.form.dobj.DOVP_Details;
//import nic.vahan.form.dobj.InsDobj;
//import nic.vahan.form.dobj.OwnerDetailsDobj;
//import nic.vahan.form.dobj.OwnerIdentificationDobj;
//import nic.vahan.form.dobj.Owner_dobj;
//import nic.vahan.form.dobj.PermitApprovalPrintDobj;
//import nic.vahan.form.dobj.PuccDobj;
//import nic.vahan.form.dobj.QrCodeDobj;
//import nic.vahan.form.dobj.RcCancelDobj;
//import nic.vahan.form.dobj.Status_dobj;
//import nic.vahan.form.dobj.Tax_Pay_Dobj;
//import nic.vahan.form.dobj.TmConfigurationDobj;
//import nic.vahan.form.dobj.VmSmartCardHsrpDobj;
//import nic.vahan.form.dobj.VmonlineservicechecksDobj;
//import nic.vahan.form.dobj.common.DMSResponseMaterDO;
//import nic.vahan.form.dobj.common.DOBankTransaction;
//import nic.vahan.form.dobj.common.OtherNecessaryDetailsDOBJ;
//import nic.vahan.form.dobj.common.TokenData;
//import nic.vahan.form.dobj.permit.AITPCertificateDobj;
//import nic.vahan.form.dobj.permit.NewPermitDetailsDobj;
//import nic.vahan.form.dobj.permit.NewPermitSpecialTemporaryDetailsDobj;
//import nic.vahan.form.dobj.permit.PassengerPermitDetailDobj;
//import nic.vahan.form.dobj.permit.PermitCheckDetailsDobj;
//import nic.vahan.form.dobj.permit.PermitDetailDobj;
//import nic.vahan.form.dobj.permit.PermitHomeAuthDobj;
//import nic.vahan.form.dobj.permit.PermitOwnerDetailDobj;
//import nic.vahan.form.dobj.permit.SurrenderPermitDobj;
//import nic.vahan.form.dobj.permit.TemporaryPermitDobj;
//import nic.vahan.form.dobj.permit.VehicleTrackingDetailsDobj;
//import nic.vahan.form.dobj.reports.CashRecieptSubListDobj;
//import nic.vahan.form.impl.CollectionOfRoadTaxImpl;
//import nic.vahan.form.impl.FitnessImpl;
//import nic.vahan.form.impl.Util;
//import nic.vahan.form.impl.permit.CommonPermitPrintImpl;
//import nic.vahan.usermgmt.UserRegistrationImpl;
//import nic.vahan.utils.DateUtil;
//import resources.ApplicationConfig;
//import tax.web.service.VahanTaxParameters;
//
//public class ServerUtil implements Serializable {
//
//	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ServerUtil.class);
//	private static boolean fit_fine_due_nid = false; // to get due date for
//														// calculation of
//														// fitness fine
//	private static final String MD5_Value = "MD5";
//	public static List<SelectItem> state_list = new ArrayList<SelectItem>();
//	public static Map<String, List<SelectItem>> offMap = getStates2();
//	private static final String characterEncoding = "UTF-8";
//	private static final String cipherTransformation = "AES/CBC/PKCS5Padding";
//	private static final String aesEncryptionAlgorithm = "AES";
//
//	/**
//	 * This method is used to generate unique receipt number for the successful
//	 * transaction.
//	 *
//	 * @param state_cd
//	 * @param off_cd
//	 * @param TransactionManager
//	 * @return Unique Reciept Number which is generated after successful online
//	 *         payment.
//	 * @throws VahanException
//	 */
//
//	
//
//	public static String getFreshPurCodeForFlow(String state_cd) {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		TmConfigurationDobj dobj = null;
//		int pur_cd = 0, action_cd = 0;
//		try {
//			tmgr = new TransactionManagerReadOnly("getFreshPurCodeForFlow");
//			String query = "select distinct pur_cd, action_cd from tm_purpose_action_flow where state_cd = ? and pur_cd in (?,?) and flow_srno = 1  order by pur_cd";
//			ps = tmgr.prepareStatement(query);
//			ps.setString(1, state_cd);
//			ps.setInt(2, TableConstants.VM_PMT_APPLICATION_PUR_CD);
//			ps.setInt(3, TableConstants.VM_PMT_FRESH_PUR_CD);
//			ResultSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				if (rs.isFirst() && TableConstants.TM_ROLE_PMT_APPLICATION_ENTRY == rs.getInt("action_cd")) {
//					action_cd = TableConstants.TM_ROLE_PMT_APPLICATION_ENTRY;
//					if (TableConstants.VM_PMT_APPLICATION_PUR_CD == rs.getInt("pur_cd")) {
//						pur_cd = TableConstants.VM_PMT_APPLICATION_PUR_CD;
//					} else if (rs.isLast() && TableConstants.VM_PMT_FRESH_PUR_CD == rs.getInt("pur_cd")) {
//						pur_cd = TableConstants.VM_PMT_FRESH_PUR_CD;
//					}
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return (pur_cd + "~" + action_cd);
//	}
//
//	public static int rollCd(int actionCode) {
//		int rollCd = 0;
//		String sqlTmActionSQL = "SELECT role_cd FROM TM_ACTION WHERE ACTION_CD = ?";
//		TransactionManagerReadOnly tmgr = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("roleCd");
//			PreparedStatement prstmt = tmgr.prepareStatement(sqlTmActionSQL);
//			prstmt.setInt(1, actionCode);
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				rollCd = rs.getInt("role_cd");
//			}
//		} catch (SQLException ex) {
//			LOGGER.error(ex.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return rollCd;
//
//	}
//
//	public static String printStatementDesc(String stateCD, int off_cd, String appl_no) throws VahanException {
//		String descr = null;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtil.printStatementDesc";
//		String sqlPgiVPDtls = "SELECT flow.navigation_descr FROM " + TableList.VA_STATUS_APPL + " status inner join "
//				+ TableList.PERMIT_PURPOSE_ACTION_FLOW
//				+ " flow on flow.state_cd = status.state_cd and flow.pur_cd = status.pur_cd and flow.flow_srno = status.flow_slno and flow.action_cd = status.action_cd WHERE status.state_cd = ? and status.off_cd = ? and status.appl_no = ?";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlPgiVPDtls);
//			if (POSValidator.validate(stateCD, "A")) {
//				pstm.setString(1, stateCD);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				pstm.setInt(2, off_cd);
//			}
//			if (POSValidator.validate(appl_no, "AN")) {
//				pstm.setString(3, appl_no);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				descr = rs.getString("navigation_descr");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return descr;
//	}
//
//	
//	public static Map<Integer, List<SelectItem>> getModelFromHomologation() throws VahanException {
//		String descr = null;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "getModelFromHomologation";
//		Map<Integer, List<SelectItem>> map = null;
//		String modelHomologation = "select maker_code, model_name, unique_model_ref_no from vm_model_homologation order by 1,2";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(modelHomologation);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				map = new HashMap<>();
//				rs.previous();
//			}
//			while (rs.next()) {
//				SelectItem si = new SelectItem(rs.getString("unique_model_ref_no"), rs.getString("model_name"));
//				if (map.containsKey(rs.getInt("maker_code"))) {
//					map.get(rs.getInt("maker_code")).add(si);
//				} else {
//					List<SelectItem> modelList = new ArrayList<>();
//					modelList.add(si);
//					map.put(rs.getInt("maker_code"), modelList);
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Something went wrong while getting data of models");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return map;
//	}
//	
//	public static int[] appEntryFlow(TransactionManager tmgr, boolean atAppentry, boolean atPayment, String state_cd,
//			int pur_cd, int flow_srno) throws VahanException {
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		int action_cd = 0;
//		int a[] = new int[4];
//		try {
//			if (atAppentry) {
//				tmgr = new TransactionManager("appEntryFlow");
//			}
//			String query = "SELECT state_cd, pur_cd, flow_srno, action_cd, isbackward, condition_formula, move_to_vahan4"
//					+ " FROM onlinepermit.va_permit_purpose_action_flow"
//					+ " WHERE state_cd = ? and pur_cd = ? and  flow_srno = ? ";
//			ps = tmgr.prepareStatement(query);
//			ps.setString(1, state_cd);
//			ps.setInt(2, pur_cd);
//			ps.setInt(3, flow_srno);
//			// ps.setBoolean(4, atPayment);
//			if (atAppentry) {
//				rs = tmgr.fetchDetachedRowSet();
//			} else {
//				rs = tmgr.fetchDetachedRowSet_No_release();
//			}
//			if (rs.next()) {
//				a[0] = rs.getInt("flow_srno");
//				a[1] = rs.getInt("action_cd");
//				a[2] = rs.getBoolean("move_to_vahan4") ? 1 : 0;
//				if (rs.getBoolean("move_to_vahan4")) {
//					query = "SELECT paf.state_cd, paf.pur_cd, ppaf.flow_srno as flo,paf.flow_srno, paf.action_cd, paf.isbackward, paf.condition_formula, move_to_vahan4"
//							+ " FROM " + TableList.PERMIT_PURPOSE_ACTION_FLOW + " ppaf" + " INNER JOIN "
//							+ TableList.TM_PURPOSE_ACTION_FLOW
//							+ " paf ON paf.state_cd = ppaf.state_cd and ppaf.pur_cd = paf.pur_cd and ppaf.action_cd = paf.action_cd"
//							+ " WHERE paf.state_cd = ? and paf.pur_cd = ? and  paf.action_cd = ? and move_to_vahan4 = true";
//					ps = tmgr.prepareStatement(query);
//					ps.setString(1, state_cd);
//					ps.setInt(2, pur_cd);
//					ps.setInt(3, a[1]);
//					rs = tmgr.fetchDetachedRowSet_No_release();
//					if (rs.next()) {
//						a[0] = rs.getInt("flow_srno");
//						a[1] = rs.getInt("action_cd");
//						a[3] = rs.getInt("flo");
//					}
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Error while getting flow of your purpose");
//			// TODO: handle exception
//		} finally {
//			if (atAppentry && tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage() + " At appentry");
//				}
//			}
//		}
//		return a;
//	}
//
//	// mukul app entry flow conditionally //
//	public static int[] appFlow(TransactionManager tmgr, boolean atAppentry, boolean atPayment, String state_cd,
//			int pur_cd, int flow_srno, VehicleParameters parameter) throws VahanException {
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		int action_cd = 0;
//		int a[] = new int[4];
//		try {
//			if (atAppentry) {
//				tmgr = new TransactionManager("appEntryFlow");
//			}
//			String query = "SELECT state_cd, pur_cd, flow_srno, action_cd, isbackward, condition_formula, move_to_vahan4"
//					+ " FROM onlinepermit.va_permit_purpose_action_flow"
//					+ " WHERE state_cd = ? and pur_cd = ? and  flow_srno >= ? order by 1,2,3 ";
//			ps = tmgr.prepareStatement(query);
//			if (POSValidator.validate(state_cd, "AN")) {
//				ps.setString(1, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//				ps.setInt(2, pur_cd);
//			}
//			if (POSValidator.validate(String.valueOf(flow_srno), "N")) {
//				ps.setInt(3, flow_srno);
//			}
//			
//			if (atAppentry) {
//				rs = tmgr.fetchDetachedRowSet();
//			} else {
//				rs = tmgr.fetchDetachedRowSet_No_release();
//			}
//			while (rs.next()) {
//				if (FormulaUtils.isCondition(
//						FormulaUtils.replaceTagPermitValues(rs.getString("condition_formula"), parameter), "appFlow")) {
//					a[0] = rs.getInt("flow_srno");
//					a[1] = rs.getInt("action_cd");
//					a[2] = rs.getBoolean("move_to_vahan4") ? 1 : 0;
//					if (rs.getBoolean("move_to_vahan4") && !atAppentry) {
//						query = "SELECT paf.state_cd, paf.pur_cd, ppaf.flow_srno as flo,paf.flow_srno, paf.action_cd, paf.isbackward, paf.condition_formula, move_to_vahan4"
//								+ " FROM " + TableList.PERMIT_PURPOSE_ACTION_FLOW + " ppaf" + " INNER JOIN "
//								+ TableList.TM_PURPOSE_ACTION_FLOW
//								+ " paf ON paf.state_cd = ppaf.state_cd and ppaf.pur_cd = paf.pur_cd and ppaf.action_cd = paf.action_cd"
//								+ " WHERE paf.state_cd = ? and paf.pur_cd = ? and  paf.action_cd = ? and move_to_vahan4 = true order by 1,2,3,4 ";
//						ps = tmgr.prepareStatement(query);
//						if (POSValidator.validate(state_cd, "A")) {
//							ps.setString(1, state_cd);
//						}
//						if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//							ps.setInt(2, pur_cd);
//						}
//						ps.setInt(3, a[1]);
//						rs = tmgr.fetchDetachedRowSet_No_release();
//						while (rs.next()) {
//							if (FormulaUtils.isCondition(
//									FormulaUtils.replaceTagPermitValues(rs.getString("condition_formula"), parameter),
//									"appFlow")) {
//								a[0] = rs.getInt("flow_srno");
//								a[1] = rs.getInt("action_cd");
//								a[3] = rs.getInt("flo");
//								break;
//							}
//						}
//					}
//					break;
//				}
//			}
//			if (a[0] == 0) {
//				LOGGER.error("while generating flow, first element of array not found against state_cd " + state_cd
//						+ ", pur_cd " + pur_cd + ", flow " + flow_srno + ", action_cd " + action_cd);
//				throw new VahanException(
//						"Something went wrong while getting the flow for application, Please try after some time.");
//			}
//			if (a[1] == 0) {
//				LOGGER.error("while generating flow, second element of array not found against state_cd " + state_cd
//						+ ", pur_cd " + pur_cd + ", flow " + flow_srno + ", action_cd " + action_cd);
//				throw new VahanException(
//						"Something went wrong while getting the flow for application, Please try after some time.");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage() + state_cd + ", pur_cd " + pur_cd + ", flow " + flow_srno + ", action_cd "
//					+ action_cd);
//			throw new VahanException(
//					"Something went wrong while getting the flow for application, Please try after some time.");
//			// TODO: handle exception
//		} finally {
//			if (atAppentry && tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage() + " At appentry");
//				}
//			}
//		}
//		return a;
//	}
//
//	public static VehicleParameters fillVehicleParameter(Object ob) throws VahanException {
//		VehicleParameters parameter = null;
//		if (ob instanceof PassengerPermitDetailDobj) {
//			PassengerPermitDetailDobj obj = (PassengerPermitDetailDobj) ob;
//			parameter = new VehicleParameters();
//			parameter.setREGN_NO(obj.getRegnNo());
//			if (CommonUtils.isNullOrBlank(obj.getPmt_type_code())) {
//				parameter.setPERMIT_TYPE(0);
//			} else {
//				parameter.setPERMIT_TYPE(Integer.getInteger(obj.getPmt_type_code()));
//			}
//		} else if (ob instanceof OwnerDetailsDobj) {
//			OwnerDetailsDobj obj = (OwnerDetailsDobj) ob;
//			parameter = new VehicleParameters();
//			parameter.setREGN_NO(obj.getRegn_no());
//			parameter.setFUEL(obj.getFuel());
//			parameter.setSEAT_CAP(obj.getSeat_cap());
//			parameter.setSLEEPAR_CAP(obj.getSleeper_cap());
//			parameter.setSTAND_CAP(obj.getStand_cap());
//		} else if (ob instanceof DOVP_Details) {
//			DOVP_Details obj = (DOVP_Details) ob;
//			parameter = new VehicleParameters();
//			parameter.setSTATE_CD(obj.getSTATE_CD());
//			parameter.setAADHAR_LOGIN(obj.getAADHAR_LOGIN());
//			parameter.setREGN_NO(obj.getREGN_NO());
//			parameter.setOFFLINEPAYMENT(obj.getOFFLINEPAYMENT());
//			parameter.setPERMIT_TYPE(obj.getPmt_type());
//			parameter.setPERMIT_SUB_CATG(obj.getPmt_catg());
//			if (obj.getREGN_NO().equalsIgnoreCase("NEW")) {
//				getNewOwnerDetailsInVehicleParameters(parameter, obj.getRCPT_NO());
//			} else {
//				getOwnerDetailsInVehicleParameters(parameter);
//			}
//
//			if (obj.getSTATE_CD().equalsIgnoreCase("MP") && obj.getPUR_CD() == TableConstants.VM_PMT_TEMP_PUR_CD) {
//				String applNo = ServerUtil.formatTransID(obj.getRCPT_NO(), 'P');
//				String tempReason = ServerUtil.getVaTempPermitReason(applNo, obj.getSTATE_CD(), obj.getOFF_CD());
//				if (obj.getSTATE_CD().equals("MP") && tempReason.equals("C")) {
//					parameter.setEXCEM_FLAG(tempReason);
//				}
//			}
//		} else if (ob instanceof SurrenderPermitDobj) {
//			SurrenderPermitDobj obj = (SurrenderPermitDobj) ob;
//			parameter = new VehicleParameters();
//			parameter.setREGN_NO(obj.getRegn_no());
//			parameter.setPERMIT_TYPE(obj.getPmtType());
//			parameter.setPUR_CD(obj.getPur_cd());
//			parameter = new VehicleParameters();
//			parameter.setREGN_NO(obj.getRegn_no());
//			parameter.setPERMIT_TYPE(obj.getPmtType());
//			parameter.setPUR_CD(obj.getPur_cd());
//		} else if (ob instanceof TemporaryPermitDobj) {
//			TemporaryPermitDobj obj = (TemporaryPermitDobj) ob;
//			parameter = new VehicleParameters();
//			parameter.setREGN_NO(obj.getRegn_no());
//			if (CommonUtils.isNullOrBlank(obj.getPmt_type())) {
//				parameter.setPERMIT_TYPE(0);
//			} else {
//				parameter.setPERMIT_TYPE(Integer.getInteger(obj.getPmt_type()));
//			}
//			parameter.setPUR_CD(obj.getPur_cd());
//		} else if (ob instanceof NewPermitDetailsDobj) {
//			NewPermitDetailsDobj obj = (NewPermitDetailsDobj) ob;
//			parameter = new VehicleParameters();
//			parameter.setSTATE_CD(obj.getState_cd());
//			parameter.setREGN_NO(obj.getRegn_no());
//			parameter.setPERMIT_TYPE(obj.getPmt_type());
//			parameter.setPERMIT_SUB_CATG(obj.getPmt_catg());
//			parameter.setSERVICE_TYPE(obj.getService_type());
//			parameter.setPUR_CD(obj.getPur_cd());
//			if (parameter.getREGN_NO().equalsIgnoreCase("NEW")) {
//				getNewOwnerDetailsInVehicleParameters(parameter, obj.getAppl_no());
//			} else {
//				getOwnerDetailsInVehicleParameters(parameter);
//			}
//		} else if (ob instanceof PermitApprovalPrintDobj) {
//			PermitApprovalPrintDobj obj = (PermitApprovalPrintDobj) ob;
//			parameter = new VehicleParameters();
//			parameter.setSTATE_CD(obj.getStateCd());
//			parameter.setREGN_NO(obj.getRegn_no());
//			parameter.setPERMIT_TYPE(obj.getPmt_type());
//			parameter.setPUR_CD(obj.getPur_cd());
//			if (parameter.getREGN_NO().equalsIgnoreCase("NEW")) {
//				getNewOwnerDetailsInVehicleParameters(parameter, obj.getAppl_no());
//			} else {
//				getOwnerDetailsInVehicleParameters(parameter);
//			}
//		}
//		return parameter;
//	}
//	// end of flow changed
//
//	public static UserAuthorityDobj genAuthority(String state_cd, int off_cd, String regn_no, String vch_class)
//			throws NumberFormatException, Exception {
//		UserAuthorityDobj dobj = null;
//		TransactionManagerReadOnly tmgr = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getAuthority");
//			String query = "select current_date,a.state_cd, a.off_cd, vch_class, a.pmt_type, pmt_catg, permit_print_config,fresh_permit, temporary_pemit, special_pemit, home_auth, renew_pemit, endoresement_permit,"
//					+ " freshwithoutvehicle, duplicate_permit, count_sign_after_auth, surrender_permit , counter_sign_without_reccm_ltr,permit_print_purcd, vti.ins_upto, vti.ins_type,otm.env_green_tax_pur_cd ,vti.policy_no, otm.skip_pending_pur "
//					+ " ,route_disabled_via_region,a.insurance_check_pur_cd,a.paymorthfee,a.permit_print_allowed,a.application_print_allowed,a.editable_insurance,otm.payment_mode,otm.reprintpermit_periods, otm.noofdays_withinregion_sppermit,otm.upload_doc,otm.upload_doc_condition,otm.fitness_pending_skip,otm.skip_pending_pur "
//					+ " ,otm.rta_selection,b.permit_rto_cd,c.alloted_pmt_rto_list,c.pur_cd,unnest(string_to_array(c.pmt_type,',')) as alloted_pmt_type,otm.previous_permitted_office,a.renewal_temporary_permit,otm.insurance_exempt_condition,otm.enable_dl_service,otm.enable_state_or_region_selec,otm.enb_route_time_chart,a.allowed_vch_class_noresct,count_sign_reccm_ltr "
//					+ " ,otm.ins_lgn_chk,otm.skip_ins_lgn_chk,otm.spl_pmt_count_restrictions from onlinepermit.tm_off_configuration a"
//					+ " inner join onlinepermit.vm_off_allotment b on a.state_cd = b.state_cd and a.off_cd = b.off_cd"
//					+ " inner join onlinepermit.vm_off_allot_services c on b.uid = c.uid"
//					+ " left outer  join vt_insurance vti on a.state_cd=vti.state_cd and vti.regn_no = ?"
//					+ " left outer join onlinepermit.tm_online_state_configuration otm on otm.state_cd = a.state_cd"
//					+ " where a.state_cd = ? and a.off_cd=? order by b.permit_rto_cd,c.pur_cd";
//			ps = tmgr.prepareStatement(query);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(2, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				ps.setInt(3, off_cd);
//			}
//
//			
//			rs = tmgr.fetchDetachedRowSet();
//
//			if (!rs.next()) {
//				throw new VahanException("Online permit service related to your vehicle is not activated");
//			} else {
//				// rs.beforeFirst();
//				dobj = new UserAuthorityDobj();
//				dobj.setInsuranceInfoDobj(getDataFromInsuranceServiceNew(regn_no));
//
//				String[] codeInString = null;
//				if (!CommonUtils.isNullOrBlank(rs.getString("pmt_type"))) {
//					codeInString = rs.getString("pmt_type").split(",");
//					for (int i = 0; i < codeInString.length; i++) {
//						dobj.getPermitType().add(codeInString[i]);
//					}
//				}
//				if (!CommonUtils.isNullOrBlank(rs.getString("pmt_catg"))) {
//					codeInString = rs.getString("pmt_catg").split(",");
//					for (int i = 0; i < codeInString.length; i++) {
//						dobj.getSelectedPermitCatg().add(codeInString[i]);
//					}
//				}
//				if (regn_no.equalsIgnoreCase("NEW") || (!CommonUtils.isNullOrBlank(rs.getString("vch_class"))
//						&& (rs.getString("vch_class").equalsIgnoreCase("ANY")
//								|| ("," + rs.getString("vch_class") + ",").contains("," + vch_class + ",")))) {
//					codeInString = rs.getString("vch_class").split(",");
//					for (int i = 0; i < codeInString.length; i++) {
//						if (!dobj.getSelectedVehClass().contains("ANY"))
//							dobj.getSelectedVehClass().add(codeInString[i]);
//					}
//				} else {
//					throw new VahanException("Permit related service is not available for this vehicle");
//				} // permit_print_purcd
//				if (!CommonUtils.isNullOrBlank(rs.getString("permit_print_config"))) {
//					dobj.setPmt_pur_print(rs.getString("permit_print_config"));
//				}
//				// permitPrintPur_cd permit_print_purcd
//				if (!CommonUtils.isNullOrBlank(rs.getString("permit_print_purcd"))) {
//					dobj.setPermitPrintPur_cd(rs.getString("permit_print_purcd"));
//				}
//
//				// dobj.setCondition_formula(rs.getString("condition_formula"));
//				dobj.setFresh_permit(rs.getBoolean("fresh_permit"));
//				dobj.setFreshwithoutvehicle(rs.getBoolean("freshwithoutvehicle"));
//				dobj.setRenew_pemit(rs.getBoolean("renew_pemit"));
//				dobj.setEndoresement_permit(rs.getBoolean("endoresement_permit"));
//				dobj.setSurrender_permit(rs.getBoolean("surrender_permit"));
//				dobj.setSpecial_pemit(rs.getBoolean("special_pemit"));
//				dobj.setTemporary_pemit(rs.getBoolean("temporary_pemit"));
//				dobj.setHome_auth(rs.getBoolean("home_auth"));
//				dobj.setDuplicate_permit(rs.getBoolean("duplicate_permit"));
//				dobj.setCount_sign_after_auth(rs.getBoolean("count_sign_after_auth"));
//				dobj.setCounter_sign_without_reccm_ltr(rs.getBoolean("counter_sign_without_reccm_ltr"));
//				dobj.setRoute_disabled_via_region(rs.getString("route_disabled_via_region"));
//				dobj.setInsurance_check_purCd(rs.getString("insurance_check_pur_cd"));
//				dobj.setPaymorthfee(rs.getBoolean("paymorthfee"));
//				dobj.setPermit_print_allowed(rs.getBoolean("permit_print_allowed"));
//				dobj.setApplication_print_allowed(rs.getBoolean("application_print_allowed"));
//				dobj.setPayment_mode(rs.getString("payment_mode"));
//				dobj.setRta_selection(rs.getBoolean("rta_selection"));
//				dobj.setReprintPermit_periods(rs.getInt("reprintpermit_periods"));
//				dobj.setInsConditionFormula(rs.getString("editable_insurance"));
//				dobj.setNoofdays_withinregion_sppermit(rs.getString("noofdays_withinregion_sppermit"));
//				dobj.setPrevious_permitted_office(rs.getBoolean("previous_permitted_office"));
//				dobj.setEnv_green_tax_pur_cd(rs.getString("env_green_tax_pur_cd"));
//
//				// dobj.setUpload_passenger_doc(rs.getBoolean("upload_passenger_doc"));
//				dobj.setUpload_doc(rs.getBoolean("upload_doc"));
//				dobj.setUploadPassengerCondition(rs.getString("upload_doc_condition"));
//				dobj.setFitnessPendingSkip(rs.getString("fitness_pending_skip"));
//				dobj.setRenewal_temporary_permit(rs.getBoolean("renewal_temporary_permit"));
//				dobj.setEnableStateOrRegionSelec(rs.getString("enable_state_or_region_selec"));
//				dobj.setEnableRoutetimechart(rs.getString("enb_route_time_chart"));
//				dobj.setAllowed_vch_class_noresct(rs.getString("allowed_vch_class_noresct"));
//				dobj.setCount_sign_reccm_ltr(rs.getBoolean("count_sign_reccm_ltr"));
//				dobj.setSkipPendingPurpose(rs.getString("skip_pending_pur"));
//				dobj.setSkip_ins_lgn_chk(rs.getBoolean("skip_ins_lgn_chk"));
//				VehicleParameters parameter = new VehicleParameters();
//				parameter.setREGN_NO(regn_no);
//				parameter.setSTATE_CD(state_cd);
//				parameter.setOFF_CD(off_cd);
//				ServerUtil.getOwnerDetailsInVehicleParameters(parameter);
//				dobj.setEnableDlService(rs.getString("enable_dl_service"));
//				Date currentDate = rs.getDate("current_date");
//				boolean isInsuranceExempted = FormulaUtils.isCondition(
//						FormulaUtils.replaceTagValues(rs.getString("insurance_exempt_condition"), parameter),
//					("Server Util (insurance exempt condition) for state " + state_cd));
//			
//				
//				dobj.setSpl_pmt_count_restrictions(rs.getBoolean("spl_pmt_count_restrictions"));
//				
//				if (!regn_no.equalsIgnoreCase("NEW") && !isInsuranceExempted && dobj.getInsuranceInfoDobj() != null
//						&& !CommonUtils.isNullOrBlank(dobj.getInsuranceInfoDobj().getInsUpto())) {
//					LOGGER.info("INS DATA FETCHING......");
//					if (rs.getString("ins_lgn_chk") != null) {
//						dobj.setInsurance_expired(DateUtil
//								.dateRange(JSFUtils.getStringToDateyyyyMMdd(dobj.getInsuranceInfoDobj().getInsUpto()),
//										0, 0,
//										Integer.parseInt(FormulaUtils.getReturnValue(rs.getString("ins_lgn_chk"))))
//								.compareTo(currentDate) < 0);// .compareTo(new
//																// Date()) < 0);
//					} else {
//						dobj.setInsurance_expired(
//								JSFUtils.getStringToDateyyyyMMdd(dobj.getInsuranceInfoDobj().getInsUpto())
//										.compareTo(currentDate) < 0);// .compareTo(new
//																		// Date())
//																		// < 0);
//					}
//					if (!regn_no.equalsIgnoreCase("NEW") && !isInsuranceExempted
//							&& CommonUtils.isNullOrBlank(dobj.getInsuranceInfoDobj().getPolicyNo())) {
//						throw new VahanException(
//								"Insurance data not available either from  Insurance Regulatory and Development Authority (IRDA) or from Vahan4 system. You may please enter the valid insurance details which will be verified at RTO on production of physical papers by you.");
//					}
//					dobj.setInsMessage(
//							"Note: The shown insurance details have been uploaded by Insurance Company to Vahan if any discrepancies found, the Vehicle Owner has to contact the respective Insurance Company for uploading the Latest Correct Insurance Details on Vahan.");
//					if (dobj.isInsurance_expired()) {
//						LOGGER.info("INS DATA FETCHING from service is expired......");
//						dobj.setInsMessage("");
//						String insMsg = "The Insurance validity is expired as per the details uploaded by the insurance company to Vahan (insurance company: "
//								+ dobj.getInsuranceInfoDobj().getIssuerName() + ", Policy number: "
//								+ dobj.getInsuranceInfoDobj().getPolicyNo() + ",insurance period from "
//								+ dobj.getInsuranceInfoDobj().getInsFrom() + " Upto "
//								+ dobj.getInsuranceInfoDobj().getInsUpto()
//								+ "). If any discrepancy found,the vehicle owner has to contact the respective insurance company for uploading the latest current insurance details in Vahan";
//						// dobj.setInsuranceInfoDobj(null);
//						if (!rs.getBoolean("skip_ins_lgn_chk"))
//							throw new VahanException(insMsg);
//
//					}
//				} else {
//					dobj.setInsurance_expired(!isInsuranceExempted);
//				}
//				if (!rs.getBoolean("skip_ins_lgn_chk") && !regn_no.equalsIgnoreCase("NEW") && !isInsuranceExempted
//						&& dobj.getInsConditionFormula().equalsIgnoreCase("FALSE")
//						&& dobj.getInsuranceInfoDobj() == null) {
//					throw new VahanException(
//							"Insurance data not available either from Insurance Regulatory and Development Authority (IRDA) or from Vahan4 system. If any discrepancy found,the vehicle owner has to contact the respective insurance company for uploading the latest current insurance details in Vahan");
//				}
//
//				
//				HashMap<String, Integer> mp = new HashMap<String, Integer>();
//				HashMap<String, String> mpList = new HashMap<String, String>();
//				do {
//					mp.put(rs.getString("pur_cd").concat("~").concat(rs.getString("alloted_pmt_type")),
//							rs.getInt("permit_rto_cd"));
//					mpList.put(rs.getString("pur_cd").concat("~").concat(rs.getString("alloted_pmt_type")),
//							rs.getString("alloted_pmt_rto_list"));
//					
//				} while (rs.next());
//				if (!mp.isEmpty()) {
//					dobj.setRtoMap(mp);
//					dobj.setRtoMapList(mpList);
//				}
//			}
//		} catch (
//
//		SQLException e) {
//			LOGGER.error("check  hit In genAuth" + e.getMessage());
//			throw new VahanException("Some issues occurred by getting flow at Application Entry");
//			// TODO: handle exception
//		} catch (VahanException e) {
//			dobj = null;
//			LOGGER.error("check  hit In genAuth" + e.getMessage());
//			throw new VahanException(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//		return dobj;
//	}
//
//	public static VmSmartCardHsrpDobj getVmSmartCardHsrpParameters(String state_cd, int off_cd) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String sql = null;
//		VmSmartCardHsrpDobj dobj = null;
//
//		try {
//			tmgr = new TransactionManagerReadOnly("getVmSmartCardHsrpParameters");
//			sql = "SELECT * FROM " + TableList.VM_SMART_CARD_HSRP + " WHERE state_cd = ? and off_cd=?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd, "AN")) {
//				ps.setString(1, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				ps.setInt(2, off_cd);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				dobj = new VmSmartCardHsrpDobj();
//				dobj.setState_cd(rs.getString("state_cd"));
//				dobj.setSmart_card(rs.getBoolean("smart_card"));
//				dobj.setHsrp(rs.getBoolean("hsrp"));
//				dobj.setDay_begin(rs.getDate("day_begin"));
//				dobj.setCash_counter_closed(rs.getBoolean("cash_counter_closed"));
//				dobj.setLast_working_day(rs.getDate("last_working_day"));
//				dobj.setNew_regn_not_allowed(rs.getString("new_regn_not_allowed"));
//				dobj.setNew_regn_not_allowed_msg(rs.getString("new_regn_not_allowed_msg"));
//				dobj.setOld_veh_hsrp(rs.getBoolean("old_veh_hsrp"));
//				dobj.setPaper_rc(rs.getString("paper_rc"));
//				// dobj.setAutomaticFitness(FormulaUtils.isCondition(replaceTagValues(rs.getString("condition_formula"),
//				// VehicleParameterDobj), "getVmSmartCardHsrpParameters"));
//				dobj.setAutomaticFitness(FormulaUtils.isCondition(rs.getString("automatic_fitness_formula"),
//						"getVmSmartCardHsrpParameters-setAutomaticFitness"));
//			}
//			if (dobj == null && state_cd != null && !state_cd.isEmpty() && off_cd > 0) {
//				throw new VahanException(
//						"Failed to Load Cash Counter/Smart Card/HSRP Configuration (" + state_cd + "-" + off_cd + ")");
//			}
//
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Failed to Load Cash Counter/Smart Card/HSRP Configuration (" + state_cd + "-"
//					+ off_cd + ").Please Contact to the System Administrator");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return dobj;
//	}
//
//	public static TmConfigurationDobj getTmConfigurationParameters(String state_cd) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String sql = null;
//		TmConfigurationDobj dobj = null;
//
//		try {
//			tmgr = new TransactionManagerReadOnly("getTmConfigurationParameters");
//			sql = "SELECT * FROM " + TableList.TM_CONFIGURATION + " WHERE state_cd = ? ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, state_cd);
//
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				dobj = new TmConfigurationDobj();
//				dobj.setState_cd(rs.getString("state_cd"));
//				dobj.setFinancial_year_tax(rs.getBoolean("financial_year_tax"));
//				dobj.setCalendar_month_tax(rs.getBoolean("calendar_month_tax"));
//				dobj.setService_charges_per_rcpt(rs.getBoolean("service_charges_per_rcpt"));
//				dobj.setService_charges_per_trans(rs.getBoolean("service_charges_per_trans"));
//				dobj.setRegn_gen_type(rs.getString("regn_gen_type"));
//				dobj.setRegn_gen_random_batch(rs.getInt("regn_gen_random_batch"));
//				dobj.setRcpt_heading(rs.getString("rcpt_heading"));
//				dobj.setRcpt_subheading(rs.getString("rcpt_subheading"));
//				dobj.setTax_exemption(rs.getString("tax_exemption"));
//				dobj.setEx_showroom_price_homologation(rs.getBoolean("ex_showroom_price_homologation"));
//				dobj.setConsider_holiday_fine(rs.getBoolean("consider_holiday_fine"));
//				dobj.setAdvance_regn_no(rs.getBoolean("advance_regn_no"));
//				dobj.setBiometrics(rs.getBoolean("biometrics"));
//				dobj.setPaper_rc(rs.getString("paper_rc"));
//				dobj.setProv_rc(rs.getBoolean("prov_rc"));
//				dobj.setFit_failed_grace_days(rs.getInt("fit_failed_grace_days"));
//				dobj.setAdvanceNoJump(rs.getInt("advance_jump_regn"));
//				dobj.setTax_adjustment(rs.getBoolean("tax_adjustment"));
//				dobj.setTax_adjustment_with_surcharge(rs.getInt("tax_adjustment_with_surcharge"));
//				dobj.setTo_retention(rs.getBoolean("to_retention"));
//				// dobj.setMultiple_fit_officer(rs.getBoolean("multiple_fit_officer"));
//				dobj.setFitness_rqrd_for(rs.getString("fitness_rqrd_for"));
//				dobj.setRenewal_regn_rqrd_for(rs.getString("renewal_regn_rqrd_for"));
//				dobj.setConsiderTradeCert(rs.getBoolean("consider_trade_cert"));
//				dobj.setTo_retention_for_all_regn(rs.getBoolean("to_retention_for_all_regn"));
//				dobj.setIs_rc_dispatch(rs.getBoolean("is_rc_dispatch"));
//				dobj.setNid_days(rs.getInt("nid_days"));
//				dobj.setFit_fine_due_nid(rs.getBoolean("fit_fine_due_nid"));
//				dobj.setProv_rc_rto(rs.getBoolean("prov_rc_rto"));
//				dobj.setTemp_tax_as_mvtax(rs.getBoolean("temp_tax_as_mvtax"));
//				dobj.setAuto_cash_rcpt_gen(rs.getBoolean("auto_cash_rcpt_gen"));
//				dobj.setScrap_veh_no_retain(rs.getBoolean("scrap_veh_no_retain"));
//				dobj.setFee_amt_zero(rs.getString("fee_amt_zero"));
//				dobj.setReassign_retained_no_with_to(rs.getBoolean("reassign_retained_no_with_to"));
//				dobj.setDealer_auth_for_all_office(rs.getBoolean("dealer_auth_for_all_office"));
//				dobj.setUser_signature(rs.getBoolean("user_signature"));
//				dobj.setAuto_tax_mode_filler(rs.getBoolean("auto_tax_mode_filler"));
//				dobj.setInsurance_validity(rs.getInt("insurance_validity"));
//				dobj.setRc_after_hsrp(rs.getBoolean("rc_after_hsrp"));
//				dobj.setTax_installment(rs.getString("tax_installment"));
//				dobj.setFancyFeeValidMode(rs.getString("fancy_fee_valid_mod"));
//				dobj.setFancyFeeValidPeriod(rs.getInt("fancy_fee_valid_period"));
//				dobj.setAuto_tax_no_units(rs.getBoolean("auto_tax_no_units"));
//				dobj.setTcNoForEachVehCatg(rs.getBoolean("tcno_for_each_veh_catg"));
//				dobj.setScrap_veh_type(rs.getString("scrap_veh_type"));
//				dobj.setScrap_ret_age(rs.getString("scrap_ret_age"));
//				dobj.setSmartcard_fee_at_vendor(rs.getBoolean("smartcard_fee_at_vendor"));
//				dobj.setCnginfo_from_cngmaker(rs.getBoolean("cnginfo_from_cngmaker"));
//				dobj.setPermit_exemption(rs.getString("permit_exemption"));
//				dobj.setReassign_retained_no_with_conv(rs.getBoolean("reassign_retained_no_with_conv"));
//				dobj.setOnlinePayment(rs.getBoolean("online_payment"));
//				dobj.setFine_penalty_exemtion(rs.getBoolean("fine_penalty_exemtion"));
//				dobj.setRandom_odd_even_reassign_allowed(rs.getBoolean("random_odd_even_reassign_allowed"));
//				dobj.setTempFeeInNewRegis(rs.getBoolean("temp_fee_in_new_regis"));
//				dobj.setOldFeeValidMod(rs.getString("old_fee_valid_mod"));
//				dobj.setOldFeeValidPeriod(rs.getInt("old_fee_valid_period"));
//				dobj.setRegnNoNotAssignOthState(rs.getBoolean("not_assign_regn_no_oth_state"));
//				dobj.setFancyFeeEditable(rs.getBoolean("fancy_fee_editable"));
//				dobj.setSmart_card_hpa_hpt(rs.getBoolean("smart_card_hpa_hpt"));
//				dobj.setTempRegnToNewRegnAtDealer(rs.getBoolean("temp_regn_to_new_regn_dealer"));
//				dobj.setNum_gen_allowed_dealer(rs.getBoolean("num_gen_allowed_dealer"));
//				dobj.setApplInwardExempForVehAgeExpire(rs.getString("applinwardexempforvehageexpire"));
//				dobj.setMv_tax_at_any_office(rs.getBoolean("mv_tax_at_any_office"));
//				dobj.setIs_rc_dispatch_without_postal_fee(rs.getBoolean("is_rc_dispatch_without_postal_fee"));
//				dobj.setNoOfApplsForDealerPayment(rs.getInt("no_of_appls_for_dealer_payment"));
//				dobj.setHold_regnNo_with_conversion(rs.getBoolean("hold_regnNo_with_conversion"));
//				dobj.setAllow_fitness_all_RTO(rs.getBoolean("allow_fitness_all_rto"));
//				dobj.setRegn_upto_as_fit_upto(rs.getBoolean("regn_upto_as_fit_upto"));
//				dobj.setConsiderFMSDealer(rs.getBoolean("consider_fms_dealer"));
//				dobj.setRen_regn_from_date(rs.getString("ren_regn_from_date"));
//
//			}
//			if (dobj == null) {
//				throw new VahanException("Problem in getting the Configuration Details, Please try after sometime.");
//			}
//
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return dobj;
//	}
//
//	public static Date getStringToDate2(String strDt) {
//		// return variable
//		Date dt = null;
//		// Constructs a SimpleDateFormat using the given pattern
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		try {
//			// Parses the given string to a date
//			dt = sdf.parse(strDt);
//		} catch (ParseException pex) {
//		}
//		return dt;
//	}
//
//	public static String getDateToString(Date strDt, String format) throws VahanException {
//		String dt = null;
//		if (strDt != null) {
//			SimpleDateFormat sdf = new SimpleDateFormat(format);
//			dt = sdf.format(strDt);
//
//		} else {
//			throw new VahanException("Please Provide Valid Date...");
//		}
//		return dt;
//	}
//
//	public static Date getStringToDate(String strDt, String format) throws VahanException {
//		Date dt = null;
//		try {
//
//			if (!CommonUtils.isNullOrBlank(strDt)) {
//				SimpleDateFormat sdf = new SimpleDateFormat(format);
//
//				dt = sdf.parse(strDt);
//			} else {
//				throw new VahanException("Please Provide Valid Date...");
//			}
//		} catch (ParseException e) {
//			// e.printStackTrace();
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Please Provide Valid Date...");
//		}
//		return dt;
//	}
//
//	public static int maxDiffYear(Date maxDate, Date miniDate) {
//		int difInDays = (int) (((maxDate).getTime() - (miniDate).getTime()) / ((86400000)));
//		int year = difInDays / 365;
//		year += 1;
//		return year;
//	}
//
//	
//	public static String getUniqueRcptTransactionId(String state_cd, int off_cd) throws VahanException {
//		String rcptNo = "";
//		String rcptSeries = "";
//		long rcptSequence = 0;
//		String paymentType = "I";
//		RowSet rs = null;
//		TransactionManager tmgr = null;
//		PreparedStatement psmt = null;
//		try {
//
//			if (CommonUtils.isNullOrBlank(String.valueOf(off_cd))) {
//				return null;
//			}
//			tmgr = new TransactionManager("ServerUtil.getUniqueRcptTransactionId()");
//			
//			Calendar cal = Calendar.getInstance();
//			String monthYear = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4)
//					+ String.format("%02d", cal.get(Calendar.MONTH) + 1);
//			String strSQL = "UPDATE " + TableList.vp_sequence_no
//					+ " SET RCPT_SEQUENCE_NO=RCPT_SEQUENCE_NO+1 WHERE STATE_CD=?";
//			psmt = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(state_cd, "A")) {
//				psmt.setString(1, state_cd);
//			}
//			
//			psmt.executeUpdate();
//
//			strSQL = "SELECT RCPT_PREFIX FROM " + TableList.vp_sequence_no + " WHERE STATE_CD=?";
//			psmt = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(state_cd, "A")) {
//				psmt.setString(1, state_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				if (!monthYear.equalsIgnoreCase(rs.getString("RCPT_PREFIX").trim())) {
//					strSQL = "UPDATE " + TableList.vp_sequence_no
//							+ " SET RCPT_PREFIX=?, RCPT_SEQUENCE_NO = 1, TEMP_PREFIX = ?, TEMP_SEQUENCE_NO=1 WHERE STATE_CD=?";
//					psmt = tmgr.prepareStatement(strSQL);
//					if (POSValidator.validate(monthYear, "ANWS")) {
//						psmt.setString(1, monthYear);
//						psmt.setString(2, monthYear);
//					}
//					if (POSValidator.validate(state_cd, "A")) {
//						psmt.setString(3, state_cd);
//					}
//					
//					psmt.executeUpdate();
//				}
//			}
//			rs.close();
//			strSQL = "SELECT RCPT_PREFIX,PAYMENT_TYPE, RCPT_SEQUENCE_NO FROM " + TableList.vp_sequence_no
//					+ " WHERE STATE_CD=?";
//			psmt = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(state_cd, "A")) {
//				psmt.setString(1, state_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				rcptSeries = rs.getString("RCPT_PREFIX").trim();
//				rcptSequence = rs.getLong("RCPT_SEQUENCE_NO");
//				paymentType = rs.getString("PAYMENT_TYPE");
//			}
//
//			rcptSeries = rcptSeries.concat(paymentType);
//			rcptSeries = state_cd.trim() + off_cd + rcptSeries;
//			int rcptSequenceLenght = 16 - rcptSeries.length();
//			String format = String.format("%%0%dd", rcptSequenceLenght);
//			String z = String.format(format, rcptSequence);
//			rcptNo = rcptSeries.concat(z);
//			
//			tmgr.commit();
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to generate application number.");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//				if (psmt != null) {
//					psmt.close();
//					psmt = null;
//				}
//				if (rs != null) {
//					rs.close();
//					rs = null;
//				}
//
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//				throw new VahanException("Unable to generate application number.");
//			}
//			
//		}
//		return rcptNo;
//	}
//
//	/**
//	 * This method is used to generate unique transaction number.
//	 *
//	 * @param state_cd
//	 * @param off_cd
//	 * @param tmgr
//	 * @param TransactionManager
//	 * @return Unique Transaction Number which is generated before proceeding online
//	 *         payment.
//	 * @throws VahanException
//	 */
//	
//	public static String getUniqueTempTransactionId(String state_cd, String off_cd) throws VahanException {
//		String strTempTransactionNo = "";
//		String tempSeries = "";
//		String transactionHead = null;
//		String paymentType = "T";
//		long tempSequence = 0;
//		RowSet rs = null;
//		TransactionManager tmgr = null;
//		PreparedStatement psmt = null;
//		try {
//			if (CommonUtils.isNullOrBlank(off_cd)) {
//				return null;
//			}
//			tmgr = new TransactionManager("ServerUtil.getUniqueRcptTransactionId()");
//
//			String strSQL = "UPDATE " + TableList.vp_sequence_no
//					+ " SET TEMP_SEQUENCE_NO=TEMP_SEQUENCE_NO+1 WHERE STATE_CD=?";
//			psmt = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(state_cd, "A")) {
//				psmt.setString(1, state_cd);
//			}
//			
//			psmt.executeUpdate();
//			strSQL = "SELECT RCPT_PREFIX FROM " + TableList.vp_sequence_no + " WHERE STATE_CD=?";
//			psmt = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(state_cd, "A")) {
//				psmt.setString(1, state_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			java.util.Calendar cal = java.util.Calendar.getInstance();
//			String monthYear = String.valueOf(cal.get(java.util.Calendar.YEAR)).substring(2, 4)
//					+ String.format("%02d", cal.get(java.util.Calendar.MONTH) + 1);
//			strSQL = "SELECT to_char(current_date, 'YYMM') as curr_month";
//			psmt = tmgr.prepareStatement(strSQL);
//			RowSet rs1 = tmgr.fetchDetachedRowSet_No_release();
//			if (rs1.next()) {
//				monthYear = rs1.getString("curr_month");
//			}
//			if (rs.next()) {
//				if (!monthYear.equalsIgnoreCase(rs.getString("RCPT_PREFIX").trim())) {
//					if (Integer.parseInt(monthYear) < Integer.parseInt(rs.getString("RCPT_PREFIX"))) {
//						throw new VahanException("Server Date/Time Mismatch.");
//					}
//
//					strSQL = "UPDATE " + TableList.vp_sequence_no
//							+ " SET RCPT_PREFIX=?, RCPT_SEQUENCE_NO = 1, TEMP_PREFIX = ?, TEMP_SEQUENCE_NO=1 WHERE STATE_CD=?";
//					psmt = tmgr.prepareStatement(strSQL);
//					if (POSValidator.validate(monthYear, "ANWS")) {
//						psmt.setString(1, monthYear);
//						psmt.setString(2, monthYear);
//					}
//					if (POSValidator.validate(state_cd, "A")) {
//						psmt.setString(3, state_cd);
//					}
//					
//					psmt.executeUpdate();
//
//				}
//			}
//			rs.close();
//			strSQL = "SELECT STATE_CD,PAYMENT_TYPE,TEMP_PREFIX,TEMP_SEQUENCE_NO FROM " + TableList.vp_sequence_no
//					+ " WHERE STATE_CD=?";
//			psmt = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(state_cd, "A")) {
//				psmt.setString(1, state_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				tempSeries = rs.getString("TEMP_PREFIX").trim();
//				tempSequence = rs.getLong("TEMP_SEQUENCE_NO");
//				transactionHead = rs.getString("STATE_CD");
//				
//			}
//			tempSeries = tempSeries.concat(paymentType);
//			tempSeries = transactionHead + tempSeries;
//			int rcptSequenceLenght = 16 - tempSeries.length();
//			String format = String.format("%%0%dd", rcptSequenceLenght);
//			String z = String.format(format, tempSequence);
//			strTempTransactionNo = tempSeries.concat(z);
//			tmgr.commit();
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to generate Transaction Number.");
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//				if (rs != null) {
//					rs.close();
//					rs = null;
//				}
//				if (psmt != null) {
//					psmt.close();
//					psmt = null;
//				}
//
//			} catch (SQLException e) {
//				LOGGER.error(e.getMessage());
//				throw new VahanException("Unable to generate Transaction Number.");
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//				throw new VahanException("Unable to generate Transaction Number.");
//			}
//		}
//		return strTempTransactionNo;
//	}
//
//	public static String haveOfferLetter(String state_cd) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String offerLetter = null;
//		RowSet rs = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("haveOfferLetter");
//			String strSQL = "SELECT genrate_offerletter_appl as offer_letter FROM "
//					+ TableList.ONLINE_PERMIT_STATE_CONFIGURE + " where state_cd = ?";
//			ps = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				offerLetter = rs.getString("offer_letter");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to Get Configure Details For Offer Letter.. ");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (rs != null) {
//					rs.close();
//					rs = null;
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return offerLetter;
//	}
//
//	public static boolean isTransport(int vh_class) throws VahanException {
//		Boolean isTransport = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		RowSet rs = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("isTransport");
//			String strSQL = "SELECT case when class_type = 1 then true else false end as isTransport FROM "
//					+ TableList.VM_VH_CLASS + " where vh_class = ?";
//			ps = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(String.valueOf(vh_class), "N")) {
//				ps.setInt(1, vh_class);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				isTransport = rs.getBoolean("isTransport");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (rs != null) {
//					rs.close();
//					rs = null;
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		if (isTransport == null) {
//			throw new VahanException("No Vehicle Type Found");
//		}
//		return isTransport;
//	}
//
//	public static String getRTOPermitStateConfiguration(String state_cd) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String service_charge_per_transaction = null;
//		RowSet rs = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getRTOPermitStateConfiguration");
//			String strSQL = "SELECT service_charge_per_transaction  FROM " + TableList.VM_PERMIT_STATE_CONFIGURATION
//					+ " where state_cd = ?";
//			ps = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				service_charge_per_transaction = rs.getString("service_charge_per_transaction");
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to Get RTO Side Configure Details ");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (rs != null) {
//					rs.close();
//					rs = null;
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return service_charge_per_transaction;
//	}
//
//	public static String checkAadharRecord(String regnNo) throws VahanException {
//		String aadhar = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String sql = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getRcptHeading");
//			sql = "select  aadhar_no from vt_owner_identification where  regn_no = ?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//			
//
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				aadhar = rs.getString("aadhar_no");
//			}
//
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return aadhar;
//	}
//
//	public static int VehicleClassType(int vh_class) throws VahanException {
//		int returnType = 0;
//		boolean isTranport = isTransport(vh_class);
//		if (isTranport) {
//			returnType = 1;
//		} else {
//			returnType = 2;
//		}
//		return returnType;
//	}
//
//	public static String getRcptHeading(String state_cd) throws VahanException {
//		String rcptHeading = "Not Available";
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String sql = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getRcptHeading");
//			sql = "Select rcpt_heading from " + TableList.TM_CONFIGURATION + " where state_cd=?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				rcptHeading = rs.getString("rcpt_heading");
//			}
//
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return rcptHeading;
//	}
//
//	public static String getStateOffName(String state_cd, int off_cd) throws VahanException {
//		List list = new ArrayList();
//		String state_name = "";
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String sql = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getstate_name");
//			sql = "Select a.descr,b.off_name from " + TableList.TM_STATE + " a inner join " + TableList.TM_OFFICE
//					+ " b on a.state_code=b.state_cd  where a.state_code=? and b.off_cd=?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				ps.setInt(2, off_cd);
//			}
//
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				state_name = rs.getString("off_name") + "," + rs.getString("descr");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return state_name;
//	}
//
//	public static String getTaxHead(int pur_cd) {
//		String whereiam = "Get Tax Head";
//		String taxHead = "";
//		PreparedStatement psmt = null;
//		TransactionManagerInterface tmgr = null;
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			String strSQL = "SELECT descr FROM tm_purpose_mast where pur_cd = ?;";
//			psmt = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//				psmt.setInt(1, pur_cd);
//			}
//
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				taxHead = rs.getString("descr");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return taxHead;
//	}
//
//	public static Owner_dobj getExistingOwnerDetails(String regn_no) throws VahanException {
//		
//		PreparedStatement ps = null;
//		TransactionManagerInterface iTmgr = null;
//		RowSet rs = null;
//		String sql = null;
//		Owner_dobj dobj = null;
//		try {
//			if (regn_no != null) {
//				if (POSValidator.validate(regn_no, "ANS"))
//					regn_no = regn_no.toUpperCase();
//			}
//			
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				iTmgr = new TransactionManagerReadOnly("EApplicationImpl.isValidRegistration()");
//
//			} else {
//				iTmgr = new TransactionManager("EApplicationImpl.isValidRegistration()");
//			}
//			
//			sql = "select * from " + TableList.VIEW_VV_OWNER + " where regn_no=?";
//			ps = iTmgr.prepareStatement(sql);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//
//			rs = iTmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				dobj = new Owner_dobj();
//				dobj.setRegn_no(rs.getString("regn_no"));
//				dobj.setState_cd(rs.getString("state_cd"));
//				dobj.setOff_cd(rs.getInt("off_cd"));
//				dobj.setChasi_no(rs.getString("chasi_no"));
//				dobj.setOwner_name(rs.getString("owner_name"));
//				dobj.setF_name(rs.getString("f_name"));
//				dobj.setOwner_sr(rs.getInt("owner_sr"));
//				dobj.setSale_amt(rs.getInt("sale_amt"));
//				dobj.setGarage_add(rs.getString("garage_add"));
//				dobj.setC_add1(rs.getString("c_add1"));
//				dobj.setC_add2(rs.getString("c_add2"));
//				dobj.setC_add3(rs.getString("c_add3"));
//				dobj.setC_state_name(rs.getString("c_state_name"));
//				dobj.setC_district_name(rs.getString("c_district_name"));
//				dobj.setC_pincode(rs.getInt("c_pincode"));
//				dobj.setP_add1(rs.getString("p_add1"));
//				dobj.setP_add2(rs.getString("p_add2"));
//				dobj.setP_add3(rs.getString("p_add3"));
//				dobj.setP_district_name(rs.getString("p_district_name"));
//				dobj.setP_state_name(rs.getString("p_state_name"));
//				dobj.setP_pincode(rs.getInt("p_pincode"));
//				dobj.setEng_no(rs.getString("eng_no"));
//				dobj.setLd_wt(rs.getInt("ld_wt"));
//				dobj.setUnld_wt(rs.getInt("unld_wt"));
//				dobj.setNo_cyl(rs.getInt("no_cyl"));
//				dobj.setHp(rs.getFloat("hp"));
//				dobj.setSeat_cap(rs.getInt("seat_cap"));
//				dobj.setStand_cap(rs.getInt("stand_cap"));
//				dobj.setSleeper_cap(rs.getInt("sleeper_cap"));
//				dobj.setCubic_cap(rs.getFloat("cubic_cap"));
//				dobj.setWheelbase(rs.getInt("wheelbase"));
//				dobj.setFloor_area(rs.getFloat("floor_area"));
//				dobj.setFuel_descr(rs.getString("fuel_descr"));
//				dobj.setBody_type(rs.getString("body_type"));
//				dobj.setColor(rs.getString("color"));
//				dobj.setAc_fitted(rs.getString("ac_fitted"));
//				dobj.setAudio_fitted(rs.getString("audio_fitted"));
//				dobj.setVideo_fitted(rs.getString("video_fitted"));
//				dobj.setLength(rs.getInt("length"));
//				dobj.setHeight(rs.getInt("height"));
//				dobj.setWidth(rs.getInt("width"));
//				dobj.setFit_upto(rs.getDate("fit_upto"));
//				dobj.setVh_class(rs.getInt("vh_class"));
//				dobj.setVch_catg(rs.getString("vch_catg"));
//				dobj.setSale_amt(rs.getInt("sale_amt"));
//				dobj.setOwner_cd(rs.getInt("owner_cd"));
//				dobj.setStatus(rs.getString("status"));
//				dobj.setPurchase_dt(rs.getDate("purchase_dt"));
//				dobj.setRegn_upto(rs.getDate("regn_upto"));
//				
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (rs != null) {
//					rs.close();
//					rs = null;
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return dobj;
//	}
//
//	public static String getApplNoFromRcBeToBo(String regn_no) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		String sql = null;
//		String appl_no = null;
//		try {
//			if (regn_no != null) {
//				if (POSValidator.validate(regn_no, "ANS"))
//					regn_no = regn_no.toUpperCase();
//			}
//			tmgr = new TransactionManagerReadOnly("getApplNoFromRcBeToBo");
//			sql = "SELECT rcpt_no FROM " + TableList.RC_BE_TO_BO + " WHERE vehregno=?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) // found
//			{
//				appl_no = rs.getString("rcpt_no");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return appl_no;
//	}// end of getApplNoFromRcBeToBo
//
//	public static String getApplNoFromVaRcPrint(String regn_no) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		String sql = null;
//		String appl_no = null;
//		try {
//			if (regn_no != null) {
//				if (POSValidator.validate(regn_no, "ANS"))
//					regn_no = regn_no.toUpperCase();
//			}
//			tmgr = new TransactionManagerReadOnly("getApplNoFromRcBeToBo");
//			sql = "SELECT appl_no FROM " + TableList.VA_RC_PRINT + " WHERE regn_no=?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) // found
//			{
//				appl_no = rs.getString("appl_no");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return appl_no;
//	}
//
//	
//	public static List<String> getUsersLastTransaction() throws NumberFormatException, VahanException {
//		ArrayList<String> listTransactions = new ArrayList<>();
//		TransactionManagerInterface tmgr = null;
//
//		try {
//			tmgr = new TransactionManagerReadOnly("getUsersLastTransaction");
//			String sql = "select * from " + TableList.VT_USER_LATEST_ACTIONS + " where user_cd=? order by op_dt desc ";
//			PreparedStatement ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(Util.getEmpCode(), "N")) {
//				ps.setInt(1, Integer.parseInt(Util.getEmpCode()));
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			int i = 0;
//			while (rs.next() && i < 10) {
//				listTransactions.add(rs.getString("message"));
//				i++;
//			}
//
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//		return listTransactions;
//	}
//
//	public static StringBuilder getIpPath() throws VahanException {
//		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
//				.getRequest();
//		String contextPath = request.getContextPath();
//		StringBuffer requestURL = request.getRequestURL();
//		boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//				.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//		StringBuilder ipPath = null;
//		if (isTestingEnviroment) {
//			ipPath = new StringBuilder(
//					requestURL.substring(0, requestURL.indexOf(contextPath)).replace("https:", "http:"))
//							.append(contextPath);
//		} else {
//			ipPath = new StringBuilder(
//					requestURL.substring(0, requestURL.indexOf(contextPath)).replace("http:", "https:"))
//							.append(contextPath);
//			
//		}
//		return ipPath;
//	}
//
//	public static boolean isPendingTrans(String regnNo, int purCD) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtils.isPendingTrans";
//		String sqlPgiVPDtls = "SELECT * FROM " + TableList.VT_TEMP_APPROVE + " WHERE DEAL_CD=? and PUR_CD=?";
//
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlPgiVPDtls);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				pstm.setString(1, regnNo);
//			}
//			if (POSValidator.validate(String.valueOf(purCD), "N")) {
//				pstm.setInt(2, purCD);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return false;
//	}
//
//	// check Pending Transaction For Permit
//	public static boolean isPendingPermitTrans(String regnNo) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtils.isPendingPermitTrans";
//		String sqlPendingTras = "SELECT * FROM " + TableList.VT_TEMP_APPROVE_PERMIT + " APPRV INNER JOIN "
//				+ TableList.VT_TEMP_APPL_TRANSACTION_PERMIT + " APPL "
//				+ " ON APPRV.TRANSACTION_NO=APPL.TRANSACTION_NO WHERE APPRV.DEAL_CD=? AND APPL.APPLICATION_STATUS='R'";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlPendingTras);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				pstm.setString(1, regnNo);
//			}
//			
//
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return false;
//	}
//
//	// checking for pending transaction for Permit Application
//	public static boolean checkPendingPermitAppl(String regnNo) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		// String statusMsg = "";
//		String whereiam = "ServerUtils.isPendingPermitTrans";
//		String sqlPendingPermitAppl = "SELECT * FROM " + TableList.VP_PERMIT_APPL_INWARDED_ONLINE
//				+ "  WHERE REGN_NO=? AND PUR_CD=? ";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlPendingPermitAppl);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				pstm.setString(1, regnNo);
//			}
//			pstm.setInt(2, ApplicationConfig.PUR_PERMIT_APPL);
//			
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				if (rs.getBoolean("approved")) {
//					return true;
//				} else {
//				
//					return false;
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return true;
//	}
//
//	// checking for pending transaction for Permit Application
//	public static boolean checkForPermitFee(String regnNo) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtils.checkForPermitFee";
//		String sqlPendingPermitAppl = "SELECT * FROM " + TableList.VP_PERMIT_APPL_INWARDED_ONLINE
//				+ "  WHERE REGN_NO=? AND PUR_CD=? ";
//
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlPendingPermitAppl);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				pstm.setString(1, regnNo);
//			}
//			pstm.setInt(2, ApplicationConfig.PUR_PERMIT_APPL);
//			
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				if (rs.getBoolean("approved")) {
//					return true;
//				} else {
//					return false;
//
//				}
//
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return false;
//	}
//
//	
//
//	public static boolean isOwnerTempIdDtls(String regnNo) {
//		String sql = "SELECT * FROM " + TableList.VT_TEMP_OWNER_IDENTIFICATION + " WHERE REGN_NO=?";
//		PreparedStatement pstm = null;
//		String whereiam = "ServerUtil.isOwnerIdDtls()";
//		RowSet rs = null;
//		TransactionManagerInterface iTmgr = null;
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				iTmgr = new TransactionManagerReadOnly("EApplicationImpl.isValidRegistration()");
//
//			} else {
//				iTmgr = new TransactionManager("EApplicationImpl.isValidRegistration()");
//			}
//			
//			pstm = iTmgr.prepareStatement(sql);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				pstm.setString(1, regnNo);
//			}
//			rs = iTmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return false;
//	}
//
//	public static boolean isOwnerIdDtls(String regnNo) {
//		String sql = "SELECT * FROM " + TableList.VT_OWNER_IDENTIFICATION + " WHERE REGN_NO=?";
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		String whereiam = "ServerUtil.isOwnerIdDtls()";
//		RowSet rs = null;
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				pstm.setString(1, regnNo);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return false;
//	}
//
//	public static boolean isTaxPending(OwnerDetailsDobj dobj, int purCD) throws VahanException {
//		String taxDueDate = getTaxDueFromDate(dobj, purCD);
//		if (dobj.getState_cd().equals("MH")) {
//			if (taxDueDate == null) {
//				taxDueDate = getTaxDueFromDate(dobj, ApplicationConfig.PUR_ENVIROMENTAL_TAX);
//			}
//		}
//
//		if (CommonUtils.isNullOrBlank(taxDueDate)) {
//			String taxMode = getTaxMode(dobj, purCD);
//			if (!CommonUtils.isNullOrBlank(taxMode) && (taxMode.equalsIgnoreCase(TableConstants.LIFETIME)
//					|| taxMode.equalsIgnoreCase(TableConstants.ONETIME))) {
//				return false;
//			}
//		}
//
//		if (!DateUtils.isAfter(taxDueDate, "31-03-2017") && dobj.getState_cd().equalsIgnoreCase("HR")) {
//			throw new VahanException(
//					"Either your tax is not clear upto 31st Mar, 2017 or Permit Details are not found. In order to avail the Online Services, kindly visit your concerned authority for clearing the tax upto 31st March, 2017 or Permit details updation.");
//		}
//		if (DateUtils.isAfter(taxDueDate, DateUtil.getCurrentDateDDMMYYY())) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
//	public static String getTaxMode(OwnerDetailsDobj dobj, int purCD) throws VahanException {
//		PreparedStatement ps;
//		TransactionManagerInterface tmgr = null;
//		;
//		String msg = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("permitSurrMsg");
//			String Query = "SELECT tax_mode from vt_tax where regn_no=? and off_cd=? and state_cd=? and pur_cd=?";
//			ps = tmgr.prepareStatement(Query);
//			int i = 1;
//			if (POSValidator.validate(dobj.getRegn_no(), "ANS")) {
//				ps.setString(i++, dobj.getRegn_no());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getOff_cd()), "N")) {
//				ps.setInt(i++, dobj.getOff_cd());
//			}
//			if (POSValidator.validate(dobj.getState_cd(), "A")) {
//				ps.setString(i++, dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(purCD), "N")) {
//				ps.setInt(i++, purCD);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				msg = rs.getString("tax_mode");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return msg;
//	}
//
//	/**
//	 * returns max date +1 day upto which tax is paid if else purchase date is
//	 * returned
//	 *
//	 * @param dobj
//	 * @param pur_cd
//	 * @return
//	 */
//	public static String getTaxDueFromDate(OwnerDetailsDobj dobj, int pur_cd) throws VahanException {
//		String dateDueFrom = null;
//		String dateDueFromVahan4 = null;
//		String dateDueFromSR = null;
//		Date dueDT = null;
//		RowSet rs = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface iTmgr = null;
//		
//		try {
//			
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				iTmgr = new TransactionManagerReadOnly("EApplicationImpl.isValidRegistration()");
//
//			} else {
//				iTmgr = new TransactionManager("EApplicationImpl.isValidRegistration()");
//			}
//			String sqlSR = "SELECT MAX(clear_to) tax_upto FROM " + TableList.vp_tax_clear
//					+ " WHERE REGN_NO=? AND PUR_CD=? AND STATE_CD=? AND OFF_CD=?";
//			dateDueFromVahan4 = getVahan4DueDate(dobj.getRegn_no(), pur_cd, dobj.getState_cd(), iTmgr);
//			ps = iTmgr.prepareStatement(sqlSR);
//			int i = 1;
//			if (POSValidator.validate(dobj.getRegn_no(), "ANS")) {
//				ps.setString(i++, dobj.getRegn_no());
//			}
//			if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//				ps.setInt(i++, pur_cd);
//			}
//			if (POSValidator.validate(dobj.getState_cd(), "A")) {
//				ps.setString(i++, dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getOff_cd()), "N")) {
//				ps.setInt(i++, dobj.getOff_cd());
//			}
//
//			
//			rs = iTmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				dueDT = rs.getDate("tax_upto");
//				if (dueDT != null) {
//					
//					String dt = CommonPermitPrintImpl.getDateStringDD_MMM_YYYY(DateUtil.dateRange(dueDT, 0, 0, 1));
//					
//					dateDueFromSR = DateUtil.getStringDDMMMYYYYtoStringDDMMYYYY(dt);
//				}
//			}
//			
//			if (dateDueFromVahan4 == null && dateDueFromSR == null) {
//				dateDueFrom = null;
//			} else if (dateDueFromVahan4 == null) {
//				dateDueFrom = dateDueFromSR;
//			} else {
//				dateDueFrom = dateDueFromVahan4;
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			if (iTmgr != null) {
//				try {
//					iTmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//		return dateDueFrom;
//	}
//
//	public static String getVahan4DueDate(String regnNo, int pur_cd, String stateCD, TransactionManagerInterface tmgr)
//			throws SQLException, DateUtilsException, VahanException {
//		Date dateDueFrom = null;
//		Date dateClearUpto = null;
//		Date dateTaxUpto = null;
//		Date dateExempUpto = null;
//		Date rcptDateTAX = null;
//		Date rcptDateCLEAR = null;
//		String sql = "select rcpt_dt, tax.tax_upto,tax.rcpt_no from " + TableList.VT_TAX
//				+ " tax left outer join onlineschema.vt_taxpayer_record rc on tax.rcpt_no!=rc.status where tax.regn_no=? "
//				+ " and tax.pur_cd=?  and tax.state_cd=? and tax.tax_mode !='B' order by rcpt_dt desc limit 1 ";
//
//		PreparedStatement ps = tmgr.prepareStatement(sql);
//		if (POSValidator.validate(regnNo, "ANS")) {
//			ps.setString(1, regnNo);
//		}
//		if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//			ps.setInt(2, pur_cd);
//		}
//		if (POSValidator.validate(stateCD, "A")) {
//			ps.setString(3, stateCD);
//		}
//
//		RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//		if (rs.next()) {
//			
//			Timestamp rcpt_date = rs.getTimestamp("rcpt_dt");
//			rcptDateTAX = rs.getDate("rcpt_dt");
//             String sql2 = "select tax_upto from " + TableList.VT_TAX
//					+ " where regn_no=? and pur_cd=?  and state_cd=? and rcpt_dt=? and tax_mode !='B' "
//					+ "  order by tax_upto desc limit 1";
//			ps = tmgr.prepareStatement(sql2);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//			if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//				ps.setInt(2, pur_cd);
//			}
//			if (POSValidator.validate(stateCD, "A")) {
//				ps.setString(3, stateCD);
//			}
//			if (POSValidator.validate(rcpt_date.toString(), "DATE")) {
//				ps.setTimestamp(4, rcpt_date);
//			}
//
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				dateTaxUpto = rs.getDate("tax_upto");
//			}
//			if (dateTaxUpto != null) {
//				dateTaxUpto = DateUtils.addToDate(dateTaxUpto, 1, 1);
//			}
//		}
//
//		sql = "select op_dt from " + TableList.VT_TAX_CLEAR
//				+ " where regn_no=? and pur_cd=? and  state_cd=? order by op_dt desc limit 1";
//
//		ps = tmgr.prepareStatement(sql);
//		if (POSValidator.validate(regnNo, "ANS")) {
//			ps.setString(1, regnNo);
//		}
//		if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//			ps.setInt(2, pur_cd);
//		}
//		if (POSValidator.validate(stateCD, "A")) {
//			ps.setString(3, stateCD);
//		}
//		rs = tmgr.fetchDetachedRowSet_No_release();
//		if (rs.next()) {
//			Timestamp rcpt_date = rs.getTimestamp("op_dt");
//			rcptDateCLEAR = rs.getDate("op_dt");
//
//			if (rcptDateTAX == null
//					|| (DateUtils.isAfter(DateUtils.parseDate(rcptDateCLEAR), DateUtils.parseDate(rcptDateTAX))
//							|| DateUtils.parseDate(rcptDateCLEAR).compareTo(DateUtils.parseDate(rcptDateTAX)) == 0)) {
//				sql = "select clear_to from " + TableList.VT_TAX_CLEAR
//						+ " where regn_no=? and pur_cd=? and  state_cd=? and op_dt=? order by op_dt desc limit 1";
//				ps = tmgr.prepareStatement(sql);
//				if (POSValidator.validate(regnNo, "ANS")) {
//					ps.setString(1, regnNo);
//				}
//				if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//					ps.setInt(2, pur_cd);
//				}
//				if (POSValidator.validate(stateCD, "A")) {
//					ps.setString(3, stateCD);
//				}
//				if (POSValidator.validate(rcpt_date.toString(), "DATE")) {
//					ps.setTimestamp(4, rcpt_date);
//				}
//				rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//					dateClearUpto = rs.getDate("clear_to");
//					if (dateClearUpto != null) {
//						dateClearUpto = DateUtils.addToDate(dateClearUpto, 1, 1);
//					}
//				}
//			}
//		}
//
//		if (dateTaxUpto == null) {
//			dateDueFrom = dateClearUpto;
//		} else if (dateClearUpto == null) {
//			dateDueFrom = dateTaxUpto;
//		} else {
//			if (DateUtils.parseDate(rcptDateCLEAR).compareTo(DateUtils.parseDate(rcptDateTAX)) == 0) {
//				if (DateUtils.isAfter(DateUtils.parseDate(dateTaxUpto), DateUtils.parseDate(dateClearUpto))) {
//					dateDueFrom = dateTaxUpto;
//				} else {
//					dateDueFrom = dateClearUpto;
//				}
//			} else {
//				if (DateUtils.isAfter(DateUtils.parseDate(rcptDateCLEAR), DateUtils.parseDate(rcptDateTAX))) {
//					dateDueFrom = dateClearUpto;
//				} else {
//					dateDueFrom = dateTaxUpto;
//				}
//			}
//		}
//
//		sql = "select exem_to from " + TableList.VT_TAX_EXEM
//				+ " where regn_no=?  and state_cd=?  order by op_dt desc limit 1";
//		ps = tmgr.prepareStatement(sql);
//		if (POSValidator.validate(regnNo, "ANS")) {
//			ps.setString(1, regnNo);
//		}
//
//		if (POSValidator.validate(stateCD, "A")) {
//			ps.setString(2, stateCD);
//		}
//		rs = tmgr.fetchDetachedRowSet_No_release();
//		if (rs.next()) {
//			dateExempUpto = rs.getDate("exem_to");
//			if (dateExempUpto != null) {
//				dateExempUpto = DateUtils.addToDate(dateExempUpto, 1, 1);
//			}
//		}
//
//		if (dateExempUpto != null) {
//			if (dateDueFrom == null) {
//				dateDueFrom = dateExempUpto;
//			} else {
//				if (DateUtils.isAfter(DateUtils.parseDate(dateExempUpto), DateUtils.parseDate(dateDueFrom))) {
//					dateDueFrom = dateExempUpto;
//				}
//			}
//		}
//		if (dateDueFrom == null) {
//			return null;
//		}
//
//		return DateUtil.parseDateDDMMYYYYToString(dateDueFrom);
//	}
//
//	public static OwnerDetailsDobj getRegnBasedOwnerDetails(String regnNo, String state_cd) {
//		OwnerDetailsDobj dobj = null;
//		String whereCond = TableConstants.BLANK_STRING;
//		if (!CommonUtils.isNullOrBlank(state_cd)) {
//			whereCond = " AND STATE_CD=? ";
//		}
//		
//		String sql1 = "SELECT owner.*,CASE WHEN CHAR_LENGTH(CHASI_NO)>5 THEN overlay(chasi_no placing 'XXXXX' from CHAR_LENGTH(CHASI_NO) - 4) ELSE CHASI_NO END as chasi_no_lastfive ,CASE WHEN CHAR_LENGTH(eng_no)>5 THEN  overlay(eng_no placing 'XXXXX' from CHAR_LENGTH(eng_no) - 4)  ELSE eng_no END as eng_no_lastfive,type.descr as regn_type_descr,owcode.descr as owner_cd_descr ,ctg.catg_desc"
//				+ " FROM " + TableList.VIEW_VV_OWNER + " owner "
//				+ " left join vm_regn_type type on owner.regn_type=type.regn_typecode "
//				+ " left join vm_vch_catg ctg on owner.vch_catg=ctg.catg "
//				+ " left join vm_owcode owcode on owner.owner_cd = owcode.ow_code "
//				+ " WHERE status!='N' and regn_no =? " + whereCond;
//
//		
//		PreparedStatement ps = null;
//		TransactionManagerInterface iTmgr = null;
//		RowSet rs = null;
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				iTmgr = new TransactionManagerReadOnly("ServerUtil.getRegnBasedOwnerDetails");
//
//			} else {
//				iTmgr = new TransactionManager("ServerUtil.getRegnBasedOwnerDetails");
//			}
//			
//
//			ps = iTmgr.prepareStatement(sql1);
//			if (!CommonUtils.isNullOrBlank(regnNo)) {
//				if (POSValidator.validate(regnNo, "ANS")) {
//					ps.setString(1, regnNo);
//				}
//
//			}
//
//			if (!CommonUtils.isNullOrBlank(state_cd)) {
//				if (POSValidator.validate(state_cd, "A")) {
//					ps.setString(2, state_cd);
//				}
//
//			}
//			rs = iTmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				dobj = new OwnerDetailsDobj();
//
//				dobj.setState_cd(rs.getString("state_cd"));
//				dobj.setState_name(rs.getString("state_name"));
//				dobj.setOff_cd(rs.getInt("off_cd"));
//				dobj.setOff_name(rs.getString("off_name"));
//				dobj.setRegn_no(rs.getString("regn_no"));
//				dobj.setRegn_dt(DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("regn_dt")));
//				dobj.setPurchase_dt(rs.getString("purchase_dt"));
//				dobj.setPurchase_dt(DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("purchase_dt")));
//				dobj.setFit_upto(DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("fit_upto")));
//				dobj.setOwner_sr(rs.getInt("owner_sr"));
//				dobj.setOwner_name(rs.getString("owner_name"));
//				dobj.setF_name(rs.getString("f_name"));
//				dobj.setC_add1(rs.getString("c_add1"));
//				dobj.setC_add2(rs.getString("c_add2"));
//				dobj.setC_add3(rs.getString("c_add3"));
//				dobj.setC_district(rs.getInt("c_district"));
//				dobj.setC_district_name(rs.getString("c_district_name"));
//				dobj.setC_state(rs.getString("c_state"));
//				dobj.setC_state_name(rs.getString("c_state_name"));
//				dobj.setC_pincode(rs.getInt("c_pincode"));
//				dobj.setP_add1(rs.getString("p_add1"));
//				dobj.setP_add2(rs.getString("p_add2"));
//				dobj.setP_add3(rs.getString("p_add3"));
//				dobj.setP_district(rs.getInt("p_district"));
//				dobj.setP_district_name(rs.getString("p_district_name"));
//				dobj.setP_state(rs.getString("p_state"));
//				dobj.setP_state_name(rs.getString("p_state_name"));
//				dobj.setP_pincode(rs.getInt("p_pincode"));
//				dobj.setOwner_cd(rs.getInt("owner_cd"));
//				dobj.setOwner_cd_descr(rs.getString("owner_cd_descr"));
//				dobj.setRegn_type(rs.getString("regn_type"));
//				dobj.setRegn_type_descr(rs.getString("regn_type_descr"));
//				dobj.setVh_class(rs.getInt("vh_class"));
//				dobj.setVh_class_desc(rs.getString("vh_class_desc"));
//				dobj.setChasi_no(rs.getString("chasi_no_lastfive"));
//				dobj.setEng_no(rs.getString("eng_no_lastfive"));
//				dobj.setMaker(rs.getInt("maker"));
//				dobj.setMaker_name(rs.getString("maker_name"));
//				dobj.setModel_cd(rs.getString("model_cd"));
//				dobj.setModel_name(rs.getString("model_name"));
//				dobj.setBody_type(rs.getString("body_type"));
//				dobj.setNo_cyl(rs.getInt("no_cyl"));
//				dobj.setHp(rs.getFloat("hp"));
//				dobj.setSeat_cap(rs.getInt("seat_cap"));
//				dobj.setStand_cap(rs.getInt("stand_cap"));
//				dobj.setSleeper_cap(rs.getInt("sleeper_cap"));
//				dobj.setUnld_wt(rs.getInt("unld_wt"));
//				dobj.setLd_wt(rs.getInt("ld_wt"));
//				dobj.setGcw(rs.getInt("gcw"));
//				dobj.setFuel(rs.getInt("fuel"));
//				dobj.setFuel_descr(rs.getString("fuel_descr"));
//				dobj.setColor(rs.getString("color"));
//				dobj.setManu_mon(rs.getInt("manu_mon"));
//				dobj.setManu_yr(rs.getInt("manu_yr"));
//				dobj.setNorms(rs.getInt("norms"));
//				dobj.setNorms_descr(rs.getString("norms_descr"));
//				dobj.setWheelbase(rs.getInt("wheelbase"));
//				dobj.setCubic_cap(rs.getFloat("cubic_cap"));
//				dobj.setFloor_area(rs.getFloat("floor_area"));
//				dobj.setAc_fitted(rs.getString("ac_fitted"));
//				dobj.setAudio_fitted(rs.getString("audio_fitted"));
//				dobj.setVideo_fitted(rs.getString("video_fitted"));
//				dobj.setVch_purchase_as(rs.getString("vch_purchase_as"));
//				dobj.setVch_purchase_as_code(rs.getString("vch_purchase_as"));
//				dobj.setVch_catg(rs.getString("vch_catg"));
//			    dobj.setDealer_cd(rs.getString("dealer_cd"));
//				dobj.setDlr_name(rs.getString("dlr_name"));
//				dobj.setDlr_add1(rs.getString("dlr_add1"));
//				dobj.setDlr_add2(rs.getString("dlr_add2"));
//				dobj.setDlr_add3(rs.getString("dlr_add3"));
//				dobj.setDlr_city(rs.getString("dlr_city"));
//				dobj.setDlr_district(rs.getString("dlr_district"));
//				dobj.setDlr_pincode(rs.getString("dlr_pincode"));
//				dobj.setSale_amt(rs.getInt("sale_amt"));
//				dobj.setLaser_code(rs.getString("laser_code"));
//				dobj.setGarage_add(rs.getString("garage_add"));
//				dobj.setLength(rs.getInt("length"));
//				dobj.setWidth(rs.getInt("width"));
//				dobj.setHeight(rs.getInt("height"));
//				dobj.setRegn_upto(rs.getString("regn_upto"));
//				dobj.setFit_upto(rs.getString("fit_upto"));
//				dobj.setAnnual_income(rs.getInt("annual_income"));
//				dobj.setOp_dt(rs.getString("op_dt"));
//				dobj.setImported_vch(rs.getString("imported_vch"));
//				dobj.setOther_criteria(rs.getInt("other_criteria"));
//				dobj.setStatus(rs.getString("status"));
//
//				if (ServerUtil.isTransport(dobj.getVh_class())) {
//					dobj.setVch_type("Transport");
//					// dobj.setVehTypeAsInt(1);
//				} else {
//					dobj.setVch_type("Non-Transport");
//					// dobj.setVehTypeAsInt(2);
//				}
//
//				if (dobj.getVch_purchase_as().equalsIgnoreCase("B")) {
//					dobj.setVch_purchase_as("Fully Built");
//				} else {
//					dobj.setVch_purchase_as("Drive Away Chasis");
//				}
//				ps.close();
//				rs.close();
//				// Setting Owenr Identification Details
//				String sqlOwnerId = "SELECT * FROM " + TableList.VT_OWNER_IDENTIFICATION + " WHERE regn_no =?";
//				ps = iTmgr.prepareStatement(sqlOwnerId);
//				if (POSValidator.validate(dobj.getRegn_no(), "ANS")) {
//					ps.setString(1, dobj.getRegn_no());
//				}
//				rs = iTmgr.fetchDetachedRowSet_No_release();
//				OwnerIdentificationDobj own_identity = fillOwnerIdentityDobj(rs);
//				if (own_identity != null) {
//					dobj.setOwnerIdentity(own_identity);
//				}
//
//				ps.close();
//				rs.close();
//				// Setting Transport Category
//				String sqlPrmtCatg = "SELECT * FROM " + TableList.VM_VH_CLASS + " WHERE vh_class =?";
//				ps = iTmgr.prepareStatement(sqlPrmtCatg);
//				if (POSValidator.validate(String.valueOf(dobj.getVh_class()), "N")) {
//					ps.setInt(1, dobj.getVh_class());
//				}
//				rs = iTmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//					// dobj.setTransport_catg(rs.getString("transport_catg"));
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//				if (rs != null) {
//					rs.close();
//					rs = null;
//				}
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return dobj;
//	}
//
//	public static OwnerDetailsDobj getRegnBasedOwnerDetailsWithUncommitedMobileNo(String regnNo) {
//		OwnerDetailsDobj dobj = null;
//		RowSet rs = null;
//		String sql = " SELECT owner.*,overlay(chasi_no placing 'XXXXX' from CHAR_LENGTH(CHASI_NO) - 4) as chasi_no_lastfive,overlay(eng_no placing 'XXXXX' from CHAR_LENGTH(eng_no) - 4) as eng_no_lastfive,type.descr as regn_type_descr,owcode.descr as owner_cd_descr "
//				+ " FROM " + TableList.VIEW_VV_OWNER + " owner "
//				+ " left join vm_regn_type type on owner.regn_type=type.regn_typecode "
//				+ " left join vm_owcode owcode on owner.owner_cd = owcode.ow_code " + " WHERE regn_no =? ";
//		
//		PreparedStatement ps = null;
//		TransactionManagerInterface iTmgr = null;
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				iTmgr = new TransactionManagerReadOnly("EApplicationImpl.isValidRegistration()");
//
//			} else {
//				iTmgr = new TransactionManager("EApplicationImpl.isValidRegistration()");
//			}
//			
//			ps = iTmgr.prepareStatement(sql);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//			rs = iTmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				dobj = new OwnerDetailsDobj();
//				dobj.setState_cd(rs.getString("state_cd"));
//				dobj.setState_name(rs.getString("state_name"));
//				dobj.setOff_cd(rs.getInt("off_cd"));
//				dobj.setOff_name(rs.getString("off_name"));
//				dobj.setRegn_no(rs.getString("regn_no"));
//				dobj.setRegn_dt(DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("regn_dt")));
//				dobj.setPurchase_dt(rs.getString("purchase_dt"));
//				dobj.setOwner_sr(rs.getInt("owner_sr"));
//				dobj.setOwner_name(rs.getString("owner_name"));
//				dobj.setF_name(rs.getString("f_name"));
//				dobj.setC_add1(rs.getString("c_add1"));
//				dobj.setC_add2(rs.getString("c_add2"));
//				dobj.setC_add3(rs.getString("c_add3"));
//				dobj.setC_district(rs.getInt("c_district"));
//				dobj.setC_district_name(rs.getString("c_district_name"));
//				dobj.setC_state(rs.getString("c_state"));
//				dobj.setC_state_name(rs.getString("c_state_name"));
//				dobj.setC_pincode(rs.getInt("c_pincode"));
//				dobj.setP_add1(rs.getString("p_add1"));
//				dobj.setP_add2(rs.getString("p_add2"));
//				dobj.setP_add3(rs.getString("p_add3"));
//				dobj.setP_district(rs.getInt("p_district"));
//				dobj.setP_district_name(rs.getString("p_district_name"));
//				dobj.setP_state(rs.getString("p_state"));
//				dobj.setP_state_name(rs.getString("p_state_name"));
//				dobj.setP_pincode(rs.getInt("p_pincode"));
//				dobj.setOwner_cd(rs.getInt("owner_cd"));
//				dobj.setOwner_cd_descr(rs.getString("owner_cd_descr"));
//				dobj.setRegn_type(rs.getString("regn_type"));
//				dobj.setRegn_type_descr(rs.getString("regn_type_descr"));
//				dobj.setVh_class(rs.getInt("vh_class"));
//				dobj.setVh_class_desc(rs.getString("vh_class_desc"));
//				dobj.setChasi_no(rs.getString("chasi_no_lastfive"));
//				dobj.setEng_no(rs.getString("eng_no_lastfive"));
//				dobj.setMaker(rs.getInt("maker"));
//				dobj.setMaker_name(rs.getString("maker_name"));
//				dobj.setModel_cd(rs.getString("model_cd"));
//				dobj.setModel_name(rs.getString("model_name"));
//				dobj.setBody_type(rs.getString("body_type"));
//				dobj.setNo_cyl(rs.getInt("no_cyl"));
//				dobj.setHp(rs.getFloat("hp"));
//				dobj.setSeat_cap(rs.getInt("seat_cap"));
//				dobj.setStand_cap(rs.getInt("stand_cap"));
//				dobj.setSleeper_cap(rs.getInt("sleeper_cap"));
//				dobj.setUnld_wt(rs.getInt("unld_wt"));
//				dobj.setLd_wt(rs.getInt("ld_wt"));
//				dobj.setGcw(rs.getInt("gcw"));
//				dobj.setFuel(rs.getInt("fuel"));
//				dobj.setFuel_descr(rs.getString("fuel_descr"));
//				dobj.setColor(rs.getString("color"));
//				dobj.setManu_mon(rs.getInt("manu_mon"));
//				dobj.setManu_yr(rs.getInt("manu_yr"));
//				dobj.setNorms(rs.getInt("norms"));
//				dobj.setNorms_descr(rs.getString("norms_descr"));
//				dobj.setWheelbase(rs.getInt("wheelbase"));
//				dobj.setCubic_cap(rs.getFloat("cubic_cap"));
//				dobj.setFloor_area(rs.getFloat("floor_area"));
//				dobj.setAc_fitted(rs.getString("ac_fitted"));
//				dobj.setAudio_fitted(rs.getString("audio_fitted"));
//				dobj.setVideo_fitted(rs.getString("video_fitted"));
//				dobj.setVch_purchase_as(rs.getString("vch_purchase_as"));
//				dobj.setVch_purchase_as_code(rs.getString("vch_purchase_as"));
//				dobj.setVch_catg(rs.getString("vch_catg"));
//				dobj.setDealer_cd(rs.getString("dealer_cd"));
//				dobj.setDlr_name(rs.getString("dlr_name"));
//				dobj.setDlr_add1(rs.getString("dlr_add1"));
//				dobj.setDlr_add2(rs.getString("dlr_add2"));
//				dobj.setDlr_add3(rs.getString("dlr_add3"));
//				dobj.setDlr_city(rs.getString("dlr_city"));
//				dobj.setDlr_district(rs.getString("dlr_district"));
//				dobj.setDlr_pincode(rs.getString("dlr_pincode"));
//				dobj.setSale_amt(rs.getInt("sale_amt"));
//				dobj.setLaser_code(rs.getString("laser_code"));
//				dobj.setGarage_add(rs.getString("garage_add"));
//				dobj.setLength(rs.getInt("length"));
//				dobj.setWidth(rs.getInt("width"));
//				dobj.setHeight(rs.getInt("height"));
//				dobj.setRegn_upto(rs.getString("regn_upto"));
//				dobj.setFit_upto(rs.getString("fit_upto"));
//				dobj.setAnnual_income(rs.getInt("annual_income"));
//				dobj.setOp_dt(rs.getString("op_dt"));
//				dobj.setImported_vch(rs.getString("imported_vch"));
//				dobj.setOther_criteria(rs.getInt("other_criteria"));
//				dobj.setStatus(rs.getString("status"));
//
//				if (ServerUtil.isTransport(dobj.getVh_class())) {
//					dobj.setVehTypeDescr("Transport");
//				} else {
//					dobj.setVehTypeDescr("Non-Transport");
//				}
//
//				if (dobj.getVch_purchase_as().equalsIgnoreCase("B")) {
//					dobj.setVch_purchase_as("Fully Built");
//				} else {
//					dobj.setVch_purchase_as("Drive Away Chasis");
//				}
//				ps.close();
//				rs.close();
//				// Setting Owenr Identification Details
//				String sqlOwnerId = "SELECT * FROM " + TableList.VT_TEMP_OWNER_IDENTIFICATION + " WHERE regn_no =?";
//				ps = iTmgr.prepareStatement(sqlOwnerId);
//				if (POSValidator.validate(dobj.getRegn_no(), "ANS")) {
//					ps.setString(1, dobj.getRegn_no());
//				}
//				rs = iTmgr.fetchDetachedRowSet_No_release();
//				OwnerIdentificationDobj own_identity = fillOwnerIdentityDobj(rs);
//				if (own_identity != null) {
//					dobj.setOwnerIdentity(own_identity);
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//				if (ps != null) {
//					ps.close();
//
//				}
//				if (ps != null) {
//					rs.close();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return dobj;
//	}
//
//	public static OwnerIdentificationDobj getTempOwnerIdentificationDetails(String regnNo) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		try {
//			String sqlOwnerId = "SELECT * FROM " + TableList.VT_TEMP_OWNER_IDENTIFICATION + " WHERE regn_no =?";
//			tmgr = new TransactionManagerReadOnly("getOwnerDetails");
//			ps = tmgr.prepareStatement(sqlOwnerId);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			return fillOwnerIdentityDobj(rs);
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return null;
//	}
//
//	public static OwnerIdentificationDobj fillOwnerIdentityDobj(RowSet rsOwnerId) throws SQLException {
//		OwnerIdentificationDobj dobj = null;
//		if (rsOwnerId.next()) {
//			dobj = new OwnerIdentificationDobj();
//			dobj.setRegn_no(rsOwnerId.getString("regn_no"));
//			dobj.setMobile_no(rsOwnerId.getLong("mobile_no"));
//			dobj.setEmail_id(rsOwnerId.getString("email_id"));
//			dobj.setPan_no(rsOwnerId.getString("pan_no"));
//			dobj.setAadhar_no(rsOwnerId.getString("aadhar_no"));
//			dobj.setPassport_no(rsOwnerId.getString("passport_no"));
//			dobj.setRation_card_no(rsOwnerId.getString("ration_card_no"));
//			dobj.setVoter_id(rsOwnerId.getString("voter_id"));
//			dobj.setDl_no(rsOwnerId.getString("dl_no"));
//			dobj.setVerified_on(rsOwnerId.getDate("verified_on"));
//			dobj.setOff_cd(rsOwnerId.getInt("off_cd"));
//		}
//		return dobj;
//	}
//
//	public static void insertIntoVaStatus(TransactionManager tmgr, Status_dobj dobj) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		try {
//			
//			sql = "INSERT INTO " + TableList.VA_STATUS_APPL + " (state_cd, off_cd, appl_no, pur_cd, flow_slno,"
//					+ " file_movement_slno,action_cd, seat_cd, cntr_id, status, "
//					+ " office_remark, public_remark,file_movement_type, emp_cd, op_dt,moved_from_online)"
//					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp,'N')";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(dobj.getState_cd(), "A")) {
//				ps.setString(1, dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getOff_cd()), "N")) {
//				ps.setInt(2, dobj.getOff_cd());
//			}
//			if (POSValidator.validate(dobj.getAppl_no(), "AN")) {
//				ps.setString(3, dobj.getAppl_no());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getPur_cd()), "N")) {
//				ps.setInt(4, dobj.getPur_cd());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getFlow_slno()), "N")) {
//				ps.setInt(5, dobj.getFlow_slno());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getFile_movement_slno()), "N")) {
//				ps.setInt(6, dobj.getFile_movement_slno());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getAction_cd()), "N")) {
//				ps.setInt(7, dobj.getAction_cd());
//			}
//			if (dobj.getSeat_cd() != null && POSValidator.validate(dobj.getSeat_cd(), "AN")) {
//				ps.setString(8, dobj.getSeat_cd());
//			} else {
//				ps.setString(8, "");
//			}
//			if (!CommonUtils.isNullOrBlank(dobj.getCntr_id()) && POSValidator.validate(dobj.getCntr_id(), "A")) {
//				ps.setString(9, dobj.getCntr_id());
//			} else {
//				ps.setNull(9, java.sql.Types.VARCHAR);
//			}
//			if (POSValidator.validate(dobj.getStatus(), "A")) {
//				ps.setString(10, dobj.getStatus());
//			}
//			if (!CommonUtils.isNullOrBlank(dobj.getOffice_remark())
//					&& POSValidator.validate(dobj.getOffice_remark(), "AN")) {
//				ps.setString(11, dobj.getOffice_remark());
//			} else {
//				ps.setNull(11, java.sql.Types.VARCHAR);
//			}
//			if (!CommonUtils.isNullOrBlank(dobj.getPublic_remark())
//					&& POSValidator.validate(dobj.getPublic_remark(), "AN")) {
//				ps.setString(12, dobj.getPublic_remark());
//			} else {
//				ps.setNull(12, java.sql.Types.VARCHAR);
//			}
//			if (POSValidator.validate(dobj.getFile_movement_type(), "A")) {
//				ps.setString(13, dobj.getFile_movement_type());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getEmp_cd()), "N")) {
//				ps.setLong(14, Long.valueOf(dobj.getEmp_cd()));
//			}
//			
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Saving status appl detials failed.");
//		}
//	} // end of insertIntoVaStatus
//
//	public static void insertIntoVaDetails(TransactionManager tmgr, Status_dobj dobj) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		try {
//			sql = "INSERT INTO " + TableList.VA_DETAILS_APPL
//					+ " (appl_no, pur_cd, appl_dt, regn_no, user_id, user_type, entry_ip,entry_status,"
//					+ "  confirm_ip, confirm_status, confirm_date, state_cd,off_cd) "
//					+ "    VALUES (?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(dobj.getAppl_no(), "AN")) {
//				ps.setString(1, dobj.getAppl_no());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getPur_cd()), "N")) {
//				ps.setInt(2, dobj.getPur_cd());
//			}
//			if (POSValidator.validate(dobj.getRegn_no(), "ANS")) {
//				ps.setString(3, dobj.getRegn_no());
//			}
//			if (POSValidator.validate(dobj.getUser_id(), "USERNAME")) {
//				ps.setString(4, dobj.getUser_id());
//			}
//			if (POSValidator.validate(dobj.getUser_type(), "A")) {
//				ps.setString(5, dobj.getUser_type());
//			}
//			if (!CommonUtils.isNullOrBlank(dobj.getEntry_ip()) && POSValidator.validate(dobj.getEntry_ip(), "IP")) {
//				ps.setString(6, dobj.getEntry_ip());
//			} else {
//				ps.setNull(6, java.sql.Types.VARCHAR);
//			}
//			if (POSValidator.validate(dobj.getEntry_status(), "A")) {
//				ps.setString(7, dobj.getEntry_status());
//			} else {
//				ps.setNull(7, java.sql.Types.VARCHAR);
//			}
//			if (!CommonUtils.isNullOrBlank(dobj.getConfirm_ip()) && POSValidator.validate(dobj.getConfirm_ip(), "IP")) {
//				ps.setString(8, dobj.getConfirm_ip());
//			} else {
//				ps.setNull(8, java.sql.Types.VARCHAR);
//			}
//			if (POSValidator.validate(dobj.getConfirm_status(), "A")) {
//				ps.setString(9, dobj.getConfirm_status());
//			} else {
//				ps.setNull(9, java.sql.Types.VARCHAR);
//			}
//			if (POSValidator.validate(DateUtil.getStringTimeStampFormatted(dobj.getConfirm_date()), "DATE")) {
//				ps.setDate(10, new java.sql.Date(dobj.getConfirm_date().getTime()));
//			} else {
//				ps.setNull(10, java.sql.Types.TIMESTAMP);
//			}
//			if (POSValidator.validate(dobj.getState_cd(), "A")) {
//				ps.setString(11, dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getOff_cd()), "N")) {
//				ps.setInt(12, dobj.getOff_cd());
//			}
//			
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Saving Details Appl record failed.");
//		}
//	}
//
//	public static void insertIntoVaStatus_Permit(TransactionManager tmgr, Status_dobj dobj) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		try {
//			
//			sql = "INSERT INTO va_status (state_cd, off_cd, appl_no, pur_cd, flow_slno,"
//					+ " file_movement_slno,action_cd, seat_cd, cntr_id, status, "
//					+ " office_remark, public_remark,file_movement_type, emp_cd, op_dt)"
//					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp)";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(dobj.getState_cd(), "A")) {
//				ps.setString(1, dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getOff_cd()), "N")) {
//				ps.setInt(2, dobj.getOff_cd());
//			}
//			if (POSValidator.validate(dobj.getAppl_no(), "AN")) {
//				ps.setString(3, dobj.getAppl_no());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getPur_cd()), "N")) {
//				ps.setInt(4, dobj.getPur_cd());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getFlow_slno()), "N")) {
//				ps.setInt(5, dobj.getFlow_slno());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getFile_movement_slno()), "N")) {
//				ps.setInt(6, dobj.getFile_movement_slno());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getAction_cd()), "N")) {
//				ps.setInt(7, dobj.getAction_cd());
//			}
//			if (dobj.getSeat_cd() != null && POSValidator.validate(dobj.getSeat_cd(), "AN")) {
//				ps.setString(8, dobj.getSeat_cd());
//			} else {
//				ps.setString(8, "");
//			}
//			if (!CommonUtils.isNullOrBlank(dobj.getCntr_id()) && POSValidator.validate(dobj.getCntr_id(), "A")) {
//				ps.setString(9, dobj.getCntr_id());
//			} else {
//				ps.setNull(9, java.sql.Types.VARCHAR);
//			}
//			if (POSValidator.validate(dobj.getStatus(), "A")) {
//				ps.setString(10, dobj.getStatus());
//			}
//			if (!CommonUtils.isNullOrBlank(dobj.getOffice_remark())
//					&& POSValidator.validate(dobj.getOffice_remark(), "AN")) {
//				ps.setString(11, dobj.getOffice_remark());
//			} else {
//				ps.setNull(11, java.sql.Types.VARCHAR);
//			}
//			if (!CommonUtils.isNullOrBlank(dobj.getPublic_remark())
//					&& POSValidator.validate(dobj.getPublic_remark(), "AN")) {
//				ps.setString(12, dobj.getPublic_remark());
//			} else {
//				ps.setNull(12, java.sql.Types.VARCHAR);
//			}
//			if (POSValidator.validate(dobj.getFile_movement_type(), "A")) {
//				ps.setString(13, dobj.getFile_movement_type());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getEmp_cd()), "N")) {
//				ps.setLong(14, Long.valueOf(dobj.getEmp_cd()));
//			}
//			
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to save Status Appl Data for permit.");
//		}
//	} // end of insertIntoVaStatus
//
//	public static void insertIntoVaDetails_Permit(TransactionManager tmgr, Status_dobj dobj) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		try {
//			sql = "INSERT INTO va_details (appl_no, pur_cd, appl_dt, regn_no, user_id, user_type, entry_ip,entry_status,"
//					+ "  confirm_ip, confirm_status, confirm_date, state_cd,off_cd) "
//					+ "    VALUES (?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(dobj.getAppl_no(), "AN")) {
//				ps.setString(1, dobj.getAppl_no());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getPur_cd()), "N")) {
//				ps.setInt(2, dobj.getPur_cd());
//			}
//			if (POSValidator.validate(dobj.getRegn_no(), "ANS")) {
//				ps.setString(3, dobj.getRegn_no());
//			}
//			if (POSValidator.validate(dobj.getUser_id(), "USERNAME")) {
//				ps.setString(4, dobj.getUser_id());
//			}
//			if (POSValidator.validate(dobj.getUser_type(), "A")) {
//				ps.setString(5, dobj.getUser_type());
//			}
//			if (POSValidator.validate(dobj.getEntry_ip(), "IP")) {
//				ps.setString(6, dobj.getEntry_ip());
//			} else {
//				ps.setNull(6, java.sql.Types.VARCHAR);
//			}
//			if (POSValidator.validate(dobj.getEntry_status(), "A")) {
//				ps.setString(7, dobj.getEntry_status());
//			} else {
//				ps.setNull(7, java.sql.Types.VARCHAR);
//			}
//			if (!CommonUtils.isNullOrBlank(dobj.getConfirm_ip()) && POSValidator.validate(dobj.getConfirm_ip(), "IP")) {
//				ps.setString(8, dobj.getConfirm_ip());
//			} else {
//				ps.setNull(8, java.sql.Types.VARCHAR);
//			}
//			if (POSValidator.validate(dobj.getConfirm_status(), "A")) {
//				ps.setString(9, dobj.getConfirm_status());
//			} else {
//				ps.setNull(9, java.sql.Types.VARCHAR);
//			}
//			if (POSValidator.validate(DateUtil.getStringTimeStampFormatted(dobj.getConfirm_date()), "DATE")) {
//				ps.setDate(10, new java.sql.Date(dobj.getConfirm_date().getTime()));
//			} else {
//				ps.setNull(10, java.sql.Types.TIMESTAMP);
//			}
//			if (POSValidator.validate(dobj.getState_cd(), "A")) {
//				ps.setString(11, dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getOff_cd()), "N")) {
//				ps.setInt(12, dobj.getOff_cd());
//			}
//
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to save Details appl data for permit.");
//		}
//	}
//
//	public static boolean isDuplicateTransactionForPayment(String transID, TransactionManager tmgr)
//			throws VahanException {
//		String sql = "SELECT * FROM " + TableList.vp_onlinetaxpgi + " WHERE PAYMENT_ID=?";
//		String sql2 = "SELECT * FROM " + TableList.vp_details + " WHERE transaction_no=?";
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		try {
//			ps = tmgr.prepareStatement(sql);
//
//			if (POSValidator.validate(transID, "AN")) {
//				ps.setString(1, transID);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				return true;
//			}
//			ps = tmgr.prepareStatement(sql2);
//			if (POSValidator.validate(transID, "AN")) {
//				ps.setString(1, transID);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (VahanException e) {
//			LOGGER.error(e.getMessage());
//			throw e;
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to get validation details regarding transactions or payments");
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//			} catch (SQLException e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return false;
//	}
//
//	public static boolean isDuplicateTransactionAfterPayment(String transID, TransactionManager tmgr)
//			throws VahanException {
//		String sql2 = "SELECT * FROM " + TableList.vp_details + " WHERE transaction_no=?";
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		try {
//			ps = tmgr.prepareStatement(sql2);
//			if (POSValidator.validate(transID, "AN")) {
//				ps.setString(1, transID);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//			} catch (SQLException e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return false;
//	}
//
//	public static boolean setParamForPGI(String transID, String regnNO, String ownerName, int purCD, double amount,
//			double panelty, String stateCD, String from_dt, String upto_dt, Long mobileNo, TransactionManager tmgr)
//			throws VahanException {
//		String sql = "INSERT INTO " + TableList.vp_onlinetaxpgi
//				+ " (payment_id, regn_no, pur_cd, amount, penalty, state_cd, owner_name, from_dt, upto_dt, mobile_no) VALUES (?,?,?,?,?,?,?,?,?,?)";
//		PreparedStatement ps = null;
//		// Mulitple Entry for Tax Breakup
//		java.sql.Timestamp fromDt = null;
//		java.sql.Timestamp uptoDt = null;
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		boolean executed = false;
//		try {
//			if (!CommonUtils.isNullOrBlank(from_dt) && !CommonUtils.isNullOrBlank(upto_dt)) {
//				fromDt = new java.sql.Timestamp(sdf.parse(from_dt).getTime());
//				uptoDt = new java.sql.Timestamp(sdf.parse(upto_dt).getTime());
//			}
//			
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(transID, "AN")) {
//				ps.setString(1, transID);
//			}
//			if (POSValidator.validate(regnNO, "ANS")) {
//				ps.setString(2, regnNO);
//			}
//			if (POSValidator.validate(String.valueOf(purCD), "N")) {
//				ps.setInt(3, purCD);
//			}
//			if (POSValidator.validate(String.valueOf(amount), "F")) {
//				ps.setDouble(4, amount);
//			}
//			if (POSValidator.validate(String.valueOf(panelty), "F")) {
//				ps.setDouble(5, panelty);
//			}
//			if (!CommonUtils.isNullOrBlank(stateCD) && POSValidator.validate(stateCD, "A")) {
//				ps.setString(6, stateCD);
//			} else {
//				ps.setNull(6, java.sql.Types.VARCHAR);
//			}
//			if (!CommonUtils.isNullOrBlank(ownerName) && POSValidator.validate(ownerName, "FNCR_NAME")) {
//				ps.setString(7, ownerName);
//			} else {
//				ps.setNull(7, java.sql.Types.VARCHAR);
//			}
//
//			if (!CommonUtils.isNullOrBlank(from_dt) && POSValidator.validate(from_dt, "DATE")) {
//				ps.setTimestamp(8, fromDt);
//			} else {
//				ps.setNull(8, java.sql.Types.TIMESTAMP);
//			}
//			if (!CommonUtils.isNullOrBlank(upto_dt) && POSValidator.validate(upto_dt, "DATE")) {
//				ps.setTimestamp(9, uptoDt);
//			} else {
//				ps.setNull(9, java.sql.Types.TIMESTAMP);
//			}
//			if (!CommonUtils.isNullOrBlank(String.valueOf(mobileNo))
//					&& POSValidator.validate(String.valueOf(mobileNo), "N")) {
//				ps.setLong(10, mobileNo);
//			} else {
//				ps.setNull(10, java.sql.Types.NUMERIC);
//			}
//			
//			executed = ps.executeUpdate() > 0;
//		} catch (SQLException | ParseException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to Insert Transaction Details before Payments");
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//			} catch (SQLException e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return executed;
//	}
//
//	public static void setInsTempDtls(String transID, String regn_no, InsDobj insDobj, TransactionManager tmgr)
//			throws VahanException {
//
//		PreparedStatement ps = null;
//		String sql = null;
//		sql = "INSERT INTO " + TableList.VT_TEMP_INSURANCE
//				+ " (transaction_no, regn_no, comp_cd, ins_type, ins_from, ins_upto, policy_no , idv, op_dt, state_cd ,off_cd)"
//				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?)";
//		try {
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(transID, "AN")) {
//				ps.setString(1, transID);
//			}
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(2, regn_no);
//			}
//			if (POSValidator.validate(String.valueOf(insDobj.getComp_cd()), "N")) {
//				ps.setInt(3, insDobj.getComp_cd());
//			}
//			if (POSValidator.validate(String.valueOf(insDobj.getIns_type()), "N")) {
//				ps.setInt(4, insDobj.getIns_type());
//			}
//			if (insDobj.getIns_from() != null
//					&& POSValidator.validate(DateUtil.getStringTimeStampFormatted(insDobj.getIns_from()), "DATE")) {
//				ps.setDate(5, new java.sql.Date(insDobj.getIns_from().getTime()));
//			} else {
//				ps.setNull(5, java.sql.Types.DATE);
//
//			}
//			if (insDobj.getIns_upto() != null
//					&& POSValidator.validate(DateUtil.getStringTimeStampFormatted(insDobj.getIns_upto()), "DATE")) {
//				ps.setDate(6, new java.sql.Date(insDobj.getIns_upto().getTime()));
//			} else {
//				ps.setNull(6, java.sql.Types.DATE);
//			}
//			if (POSValidator.validate(insDobj.getPolicy_no(), "AN")) {
//				ps.setString(7, insDobj.getPolicy_no());
//			}
//			ps.setLong(8, insDobj.getIdv());
//			if (POSValidator.validate(insDobj.getState_cd(), "A")) {
//				ps.setString(9, insDobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(insDobj.getOff_cd()), "N")) {
//				ps.setInt(10, insDobj.getOff_cd());
//			}
//
//			
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Saving Temp Insurance details failed.");
//		}
//	}
//
//	public static List<DOTaxDetail> callTaxService(VahanTaxParameters taxParameters, String regnNo)
//			throws VahanException {
//		List<DOTaxDetail> tempTaxList = null;
//		final String taxParametersLog = "taxParameters  : \n" + taxParameters;
//	
//		VahanTaxClient taxClient = null;
//		try {
//			taxClient = new VahanTaxClient();
//			LOGGER.info(regnNo + " :::::::Calculation Started:::: " + taxParameters.getPUR_CD() + " ::::Tax Mode::::"
//					+ taxParameters.getTAX_MODE());
//			String taxServiceResponse = taxClient.getTaxDetails(taxParameters, regnNo);
//			final String taxServiceResponseLog = "regnNo : " + regnNo + " : " + taxParameters.getPUR_CD()
//					+ " taxServiceResponse   \n:  " + taxServiceResponse + " \n " + regnNo
//					+ " ::::::Calculation Stopped::::: " + taxParameters.getPUR_CD();
//			LOGGER.info(taxServiceResponseLog);
//			tempTaxList = taxClient.parseTaxResponse(taxServiceResponse, taxParameters.getPUR_CD(),
//					taxParameters.getTAX_MODE());
//		} catch (javax.xml.ws.WebServiceException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		}
//
//		return tempTaxList;
//	}
//
//	public static VahanTaxParameters fillTaxParametersFromDobj(OwnerDetailsDobj ownerDobj,
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
//		    taxParameters.setTAX_MODE(ownerDobj.getTax_mode());
//			taxParameters.setREGN_DATE(ownerDobj.getRegn_dt());
//			taxParameters.setTAX_DUE_FROM_DATE(ownerDobj.getPurchase_dt());
//			taxParameters.setVCH_IMPORTED(ownerDobj.getImported_vch());
//			taxParameters.setVCH_CATG(ownerDobj.getVch_catg());
//			taxParameters.setOTHER_CRITERIA(ownerDobj.getOther_criteria());
//			taxParameters.setVEH_PURCHASE_AS(ownerDobj.getVch_purchase_as_code());
//			taxParameters.setVCH_TYPE(ServerUtil.VehicleClassType(ownerDobj.getVh_class()));
//			taxParameters.setNEW_VCH("N");
//			taxParameters.setOFF_CD(ownerDobj.getOff_cd());
//			
//			if (permitDobj != null) {
//				if (permitDobj.getPmt_type() != null && !permitDobj.getPmt_type().equals("")) {
//					taxParameters.setPERMIT_TYPE(Integer.parseInt(permitDobj.getPmt_type()));
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
//		} catch (VahanException e) {
//			
//			LOGGER.error(e.getMessage());
//		}
//		return taxParameters;
//	}
//
//	public static String getFeePurDesc(int purCd) {
//		TransactionManagerReadOnly tmgr = null;
//		String whereIam = "ServerUtil.getFeePurDesc";
//		PreparedStatement ps = null;
//		RowSet rsFee = null;
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			String sql = "Select * from " + TableList.TM_PURPOSE_MAST + " where pur_cd=? ";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(String.valueOf(purCd), "N")) {
//				ps.setInt(1, purCd);
//			}
//			rsFee = tmgr.fetchDetachedRowSet();
//			if (rsFee.next()) {
//				
//				return rsFee.getString("descr");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				tmgr.release();
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return null;
//	}
//
//	
//
//	public static String getLableFromSelectedListToShow(List list, String selectedValue) {
//		String lable = null;
//		for (int i = 0; i < list.size(); i++) {
//			SelectItem st = (SelectItem) list.get(i);
//			if (st.getValue().toString().equalsIgnoreCase(selectedValue)) {
//				lable = st.getLabel();
//				break;
//			}
//		}
//		return lable;
//	}
//
//	public static DOVP_Details getDoVPDetails(String transactionID, String regnNo, String statusDesc, int offCD,
//			String stateCD) throws VahanException {
//		DOVP_Details dOVP_Details = null;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtil.getDoVPDetails";
//		String sqlPgiVPDtls = "SELECT * FROM " + TableList.vp_pgi_details + " WHERE PAYMENT_ID=?";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlPgiVPDtls);
//			if (POSValidator.validate(transactionID, "AN")) {
//				pstm.setString(1, transactionID);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				dOVP_Details = new DOVP_Details();
//				dOVP_Details.setRETURN_RCPT_NO(rs.getString("return_rcpt_no"));// return_rcpt_no
//				// In case of JH Payment gateway sending C-Cancel from refund
//				// API
//				if (rs.getString("response_code").equalsIgnoreCase("C")) {
//					dOVP_Details.setRESPONSE_CODE("F");
//				} else {
//					dOVP_Details.setRESPONSE_CODE(rs.getString("response_code"));// bank
//																					// response
//																					// status
//				}
//				// commented, set "I" only for online payment
//				// dOVP_Details.setPAY_MODE(rs.getString("payment_mode"));//payment
//				// mode
//				// set M for Mobile Services
//				dOVP_Details.setPAY_MODE("I");
//				dOVP_Details.setBANK_CODE(rs.getString("bank_code"));// bank
//																		// code
//
//				// LOGGER.info("rcpDT : " + rs.getString("RCPT_DT"));
//				dOVP_Details.setRCPT_DT(rs.getString("rcpt_dt"));// bank recpt
//
//				// By Gaurav (for "OR" state)
//				if (new CommonUtils().checkforPGIrcpt_dt(stateCD)) {
//					if (CommonUtils.isNullOrBlank(rs.getString("bank_payment_dt"))) {
//						dOVP_Details.setRCPT_DT(rs.getString("rcpt_dt"));
//					} else {
//						dOVP_Details.setRCPT_DT(rs.getString("bank_payment_dt"));
//					}
//				}
//				// By Gaurav (for "OR" state) end
//
//				dOVP_Details.setRCPT_AMT(Double.valueOf(rs.getObject("rcpt_amt").toString()));
//				dOVP_Details.setREGN_NO(regnNo);
//				// dOVP_Details.setSTATUS_DESC(statusDesc); // 'A' vv_owner
//				if ("S".equalsIgnoreCase(rs.getString("response_code"))) {
//					dOVP_Details.setSTATUS_DESC("Successful Payment");
//				} else if ("F".equalsIgnoreCase(rs.getString("response_code"))) {
//					dOVP_Details.setSTATUS_DESC("Failed Payment");
//				} else if ("C".equalsIgnoreCase(rs.getString("response_code"))) {
//					dOVP_Details.setSTATUS_DESC("Failed Payment");
//				}
//				dOVP_Details.setSTATE_CD(stateCD);
//				dOVP_Details.setOFF_CD(offCD);
//				dOVP_Details.setRCPT_NO(transactionID);// TRANSACTION ID
//
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return dOVP_Details;
//	}
//
//	public static void setDOVPDtlsFromTempDtls(DOVP_Details dovp, String transID, TransactionManager tmgr)
//			throws VahanException {
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		try {
//			String sqlTmpApprove = "SELECT STATE_CD,OFF_CD,PUR_CD FROM onlineschema.VT_TEMP_APPROVE WHERE transaction_no=?";
//			ps = tmgr.prepareStatement(sqlTmpApprove);
//			if (POSValidator.validate(transID, "AN")) {
//				ps.setString(1, transID);
//			}
//			rs = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//			if (rs.next()) {
//				dovp.setPUR_CD(rs.getInt("PUR_CD"));
//				dovp.setSTATE_CD(rs.getString("STATE_CD"));
//				dovp.setOFF_CD(rs.getInt("OFF_CD"));
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//	}
//
//	public static List<Tax_Pay_Dobj> getTransactionBrkUPDtls(String transID, TransactionManagerInterface tmgr)
//			throws VahanException {
//		List<Tax_Pay_Dobj> taxbrkupDtls = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String regnNo = null;
//		String stateCD = null;
//		int offCD;
//		String despStr = null;
//		Timestamp rcpt_dt = null;
//		try {
//			String sqlTmpApprove = "SELECT * FROM onlineschema.VT_TEMP_APPROVE WHERE transaction_no=?";
//			ps = tmgr.prepareStatement(sqlTmpApprove);
//			if (POSValidator.validate(transID, "AN")) {
//				ps.setString(1, transID);
//			}
//			rs = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//			if (rs.next()) {
//				despStr = rs.getString("DESP");
//				regnNo = rs.getString("DEAL_CD");
//				stateCD = rs.getString("STATE_CD");
//				offCD = rs.getInt("OFF_CD");
//				rcpt_dt = rs.getTimestamp("OP_DT");
//				taxbrkupDtls = getTaxBrkUp(despStr, regnNo, rcpt_dt, stateCD, offCD);
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (rs != null) {
//					rs.close();
//					rs = null;
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return taxbrkupDtls;
//	}
//
//	public static List<Tax_Pay_Dobj> getTaxBrkUp(String despStr, String regnNo, Timestamp rcptDT, String stateCD,
//			int offCD) throws NumberFormatException, VahanException, Exception {
//		List<Tax_Pay_Dobj> taxBrkUp = null;
//		Map<String, String> permitMap = new HashMap<>();
//		Tax_Pay_Dobj pay_Dobj = null;
//		taxBrkUp = new ArrayList<>();
//
//		if (!despStr.contains("<slno")) {
//			return taxBrkUp;
//		}
//
//		int z = despStr.lastIndexOf(">>");
//		int y = despStr.lastIndexOf(">");
//		double total = 0.0;
//		String mainStr = despStr.substring(z + 3, y);
//		String purposeCDArray[] = mainStr.split("><");
//		for (int x = 0; x < purposeCDArray.length; x++) {
//			pay_Dobj = new Tax_Pay_Dobj();
//			String[] permitArr = purposeCDArray[x].split("\\|");
//
//			for (int i = 0; i < permitArr.length; i++) {
//
//			}
//			String[] temp = null;
//			for (int i = 0; i < permitArr.length; i++) {
//				temp = permitArr[i].split("=");
//				if (temp.length == 1) {
//					permitMap.put(temp[0], "null");
//				} else {
//					permitMap.put(temp[0], temp[1]);
//				}
//			}
//			pay_Dobj.setTaxPurcdDesc(getFeePurDesc(Integer.parseInt(permitMap.get("PUR_CD"))));
//			pay_Dobj.setRegnNo(regnNo);
//			pay_Dobj.setRcpt_dt(rcptDT);
//			pay_Dobj.setState_cd(stateCD);
//			pay_Dobj.setOff_cd(offCD);
//			if (!CommonUtils.isNullOrBlank(permitMap.get("PUR_CD"))) {
//				pay_Dobj.setPur_cd(Integer.parseInt(permitMap.get("PUR_CD")));
//			}
//
//			if (!CommonUtils.isNullOrBlank(permitMap.get("FEES"))) {
//				if (!CommonUtils.checkNumber(permitMap.get("FEES"))) {
//					pay_Dobj.setTotalPaybaleTax(Double.parseDouble(permitMap.get("FEES")));
//				}
//			}
//			if (!CommonUtils.isNullOrBlank(permitMap.get("FINE"))) {
//				if (!CommonUtils.checkNumber(permitMap.get("FINE"))) {
//					pay_Dobj.setTotalPaybalePenalty(Double.parseDouble(permitMap.get("FINE")));
//				}
//			}
//			total = pay_Dobj.getTotalPaybaleTax() + pay_Dobj.getTotalPaybalePenalty();
//			pay_Dobj.setFinalTaxAmount(total);
//			taxBrkUp.add(pay_Dobj);
//		}
//		return taxBrkUp;
//	}
//
//	public static int[] getInitialAction(TransactionManager tmgr, String state_cd, int pur_cd, int action,
//			VehicleParameters parameters) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		int action_cd = action;
//		int flowsr_no = 0;
//
//		
//		int[] retArr = null;
//		
//
//		try {
//
//			while (true) {
//				sql = "select flow_srno,action_cd,condition_formula from tm_purpose_action_flow "
//						+ " where pur_cd=? and state_cd=? and action_cd=? order by 1";
//				ps = tmgr.prepareStatement(sql);
//				if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//					ps.setInt(1, pur_cd);
//				}
//				if (POSValidator.validate(state_cd, "A")) {
//					ps.setString(2, state_cd);
//				}
//				if (POSValidator.validate(String.valueOf(action_cd), "N")) {
//					ps.setInt(3, action_cd);
//				}
//				RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//
//					if (parameters != null) {
//						if (FormulaUtils.isCondition(
//								FormulaUtils.replaceTagValues(rs.getString("condition_formula"), parameters),
//								("Server Util (Action Flow) for state/pur/action : " + state_cd + pur_cd
//										+ action_cd))) {
//							
//							flowsr_no = rs.getInt("flow_srno");
//							retArr = new int[2];
//							retArr[0] = rs.getInt("action_cd");
//							retArr[1] = flowsr_no;
//							break;
//						} else {
//							flowsr_no++;
//						}
//
//					} else {
//						
//						flowsr_no = rs.getInt("flow_srno");
//						retArr = new int[2];
//						retArr[0] = rs.getInt("action_cd");
//						retArr[1] = rs.getInt("flow_srno");
//						break;
//					}
//				} else {
//					break;
//				}
//			}
//			if (retArr == null) {
//				retArr = new int[2];
//				retArr[0] = 0;
//				retArr[1] = flowsr_no;
//				
//			}
//
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		}
//
//		return retArr;
//	} 
//
//	/**
//	 * if vehicle is hypothecated then return true else false
//	 *
//	 * @param regnNo
//	 * @return
//	 */
//
//
//	public static boolean checkForBlacklistedVehicle(String regnNo, String chasisNo) {
//		boolean regnFlag = false;
//		boolean chasisFlag = false;
//		boolean flag = false;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		String sql = "";
//		try {
//			tmgr = new TransactionManagerReadOnly("checkForBlacklistedVehicle");
//			sql = "Select regn_no from " + TableList.VT_BLACKLIST + " where regn_no = ?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				regnFlag = true;
//			}
//
//			sql = "Select chasi_no from " + TableList.VT_BLACKLIST_CHASSIS + " where chasi_no = ?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(chasisNo, "ANWS")) {
//				ps.setString(1, chasisNo);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				chasisFlag = true;
//			}
//
//			if (regnFlag || chasisFlag) {
//				flag = true;
//			}
//
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//
//		return flag;
//	}
//
//	public static BlackListedVehicleDobj getBlacklistedVehicleDetails(String regnNo, String chasisNo)
//			throws VahanException {
//
//		BlackListedVehicleDobj blackListedVehicleDobj = null;
//		PreparedStatement ps;
//		RowSet rs;
//		String sql = null;
//		TransactionManagerInterface tmgr = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("ServerUtil.getBlacklistedVehicleDetails");
//
//			sql = "select state_cd,off_cd,complain_type,complain_type_others,fir_dt,complain_dt,compounding_amt,regn_no,blackListDescr,otherComplainType,state_name,off_name from ( "
//					+ " SELECT a.state_cd, a.off_cd,a.complain_type,a.complain_type_others,a.fir_dt,a.complain_dt,a.compounding_amt,a.regn_no,COALESCE(e.descr,COALESCE(b.descr,'')) as blackListDescr,f.descr as otherComplainType ,c.descr as state_name,COALESCE(d.off_name ,'STATE ADMIN') as off_name \n"
//					+ " FROM " + TableList.VT_BLACKLIST_STATE_ADMIN + " a \n" + " LEFT JOIN " + TableList.VM_BLACKLIST
//					+ " b on a.complain_type = b.code \n" + " LEFT JOIN " + TableList.TM_STATE
//					+ " c ON c.state_code = a.state_cd \n" + " LEFT JOIN " + TableList.TM_OFFICE
//					+ " d ON d.off_cd = a.off_cd AND d.state_cd = a.state_cd \n" + " LEFT JOIN "
//					+ TableList.VM_BLACKLIST_NOT_TRANSACTED + " e on e.code = a.complain_type \n" + " LEFT JOIN "
//					+ TableList.VM_BLACKLIST_NOT_TRANSACTED_OTHRES
//					+ " f on f.code = e.code and f.subcode=a.complain_type_others \n" + " WHERE a.regn_no=?"
//					+ " UNION ALL \n"
//					+ " SELECT a.state_cd, a.off_cd,a.complain_type,a.complain_type_others,a.fir_dt,a.complain_dt,a.compounding_amt,a.regn_no,COALESCE(e.descr,COALESCE(b.descr,'')) as blackListDescr,f.descr as otherComplainType,c.descr as state_name,d.off_name \n"
//					+ " FROM " + TableList.VT_BLACKLIST + " a \n" + " LEFT JOIN " + TableList.VM_BLACKLIST
//					+ " b on a.complain_type = b.code \n" + " LEFT JOIN " + TableList.TM_STATE
//					+ " c ON c.state_code = a.state_cd \n" + " LEFT JOIN " + TableList.TM_OFFICE
//					+ " d ON d.off_cd = a.off_cd AND d.state_cd = a.state_cd \n" + " LEFT JOIN "
//					+ TableList.VM_BLACKLIST_NOT_TRANSACTED + " e on e.code = a.complain_type \n" + " LEFT JOIN "
//					+ TableList.VM_BLACKLIST_NOT_TRANSACTED_OTHRES
//					+ " f on f.code = e.code and f.subcode=a.complain_type_others \n" + " WHERE a.regn_no=? "
//					+ " ) a order by complain_dt desc limit 1 \n ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, regnNo);
//			ps.setString(2, regnNo);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				blackListedVehicleDobj = new BlackListedVehicleDobj();
//				blackListedVehicleDobj.setState_cd(rs.getString("state_cd"));
//				blackListedVehicleDobj.setStateName(rs.getString("state_name"));
//				blackListedVehicleDobj.setOff_cd(rs.getInt("off_cd"));
//				blackListedVehicleDobj.setOfficeName(rs.getString("off_name"));
//				blackListedVehicleDobj.setComplain_type(rs.getInt("complain_type"));
//				blackListedVehicleDobj.setComplainDesc(rs.getString("blackListDescr"));
//				blackListedVehicleDobj.setComplainOtherDesc(rs.getString("otherComplainType"));
//				blackListedVehicleDobj.setFirDate(rs.getDate("fir_dt"));
//				blackListedVehicleDobj.setComplain_dt(rs.getDate("complain_dt"));
//				blackListedVehicleDobj.setCompounding_amt(rs.getLong("compounding_amt"));
//				blackListedVehicleDobj.setRegn_no(rs.getString("regn_no"));
//				blackListedVehicleDobj.setComplaindt(DateUtil.parseDateToString(rs.getDate("complain_dt")));
//				blackListedVehicleDobj.setComplainTypeOtherCode(rs.getString("complain_type_others"));
//			} else {
//				sql = "select state_cd,off_cd,complain_type,complain_type_others,fir_dt,complain_dt,blackListDescr,otherComplainType,state_name,off_name  from ( "
//						+ " SELECT a.state_cd, a.off_cd, a.complain_type,a.complain_type_others, a.fir_dt,a.complain_dt,COALESCE(e.descr,COALESCE(b.descr,'')) as blackListDescr,f.descr as otherComplainType,c.descr as state_name,COALESCE(d.off_name ,'STATE ADMIN') as off_name "
//						+ " FROM " + TableList.VT_BLACKLIST_CHASSIS_STATE_ADMIN + " a " + " LEFT JOIN "
//						+ TableList.VM_BLACKLIST + " b on a.complain_type = b.code " + " LEFT JOIN "
//						+ TableList.TM_STATE + "  c ON c.state_code = a.state_cd " + " LEFT JOIN " + TableList.TM_OFFICE
//						+ " d ON d.off_cd = a.off_cd AND d.state_cd = a.state_cd " + " LEFT JOIN "
//						+ TableList.VM_BLACKLIST_NOT_TRANSACTED + " e on e.code = a.complain_type \n" + " LEFT JOIN "
//						+ TableList.VM_BLACKLIST_NOT_TRANSACTED_OTHRES
//						+ " f on f.code = e.code and f.subcode=a.complain_type_others \n" + " WHERE a.chasi_no=? "
//						+ " UNION ALL \n"
//						+ " SELECT a.state_cd, a.off_cd, a.complain_type,a.complain_type_others, a.fir_dt,a.complain_dt,COALESCE(e.descr,COALESCE(b.descr,'')) as blackListDescr,f.descr as otherComplainType,c.descr as state_name,d.off_name "
//						+ " FROM " + TableList.VT_BLACKLIST_CHASSIS + " a " + " LEFT JOIN " + TableList.VM_BLACKLIST
//						+ " b on a.complain_type = b.code " + " LEFT JOIN " + TableList.TM_STATE
//						+ "  c ON c.state_code = a.state_cd " + " LEFT JOIN " + TableList.TM_OFFICE
//						+ " d ON d.off_cd = a.off_cd AND d.state_cd = a.state_cd " + " LEFT JOIN "
//						+ TableList.VM_BLACKLIST_NOT_TRANSACTED + " e on e.code = a.complain_type \n" + " LEFT JOIN "
//						+ TableList.VM_BLACKLIST_NOT_TRANSACTED_OTHRES
//						+ " f on f.code = e.code and f.subcode=a.complain_type_others \n" + " WHERE a.chasi_no=? \n"
//						+ ") a order by complain_dt desc limit 1";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, chasisNo);
//				ps.setString(2, chasisNo);
//				rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//					blackListedVehicleDobj = new BlackListedVehicleDobj();
//					blackListedVehicleDobj.setState_cd(rs.getString("state_cd"));
//					blackListedVehicleDobj.setStateName(rs.getString("state_name"));
//					blackListedVehicleDobj.setOff_cd(rs.getInt("off_cd"));
//					blackListedVehicleDobj.setOfficeName(rs.getString("off_name"));
//					blackListedVehicleDobj.setComplain_type(rs.getInt("complain_type"));
//					blackListedVehicleDobj.setComplainDesc(rs.getString("blackListDescr"));
//					blackListedVehicleDobj.setComplainOtherDesc(rs.getString("otherComplainType"));
//					blackListedVehicleDobj.setFirDate(rs.getDate("fir_dt"));
//					blackListedVehicleDobj.setComplain_dt(rs.getDate("complain_dt"));
//					blackListedVehicleDobj.setComplaindt(DateUtil.parseDateToString(rs.getDate("complain_dt")));
//					blackListedVehicleDobj.setComplainTypeOtherCode(rs.getString("complain_type_others"));
//				}
//			}
//
//		} catch (SQLException e) {
//			LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//				}
//			}
//		}
//
//		return blackListedVehicleDobj;
//	}
//
//	public static Map<Object, Object> getOfficeListOfState(String state_cd) {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String sql = null;
//		Map<Object, Object> officeCodeList = new LinkedHashMap<Object, Object>();
//
//		try {
//			tmgr = new TransactionManagerReadOnly("getOfficeListOfState()");
//			sql = "select off_cd,off_name,state_cd from " + TableList.TM_OFFICE + " where state_cd=?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd.toUpperCase(), "A")) {
//				ps.setString(1, state_cd.toUpperCase());
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//
//			while (rs.next())// found
//			{
//				officeCodeList.put(rs.getInt("off_cd"), rs.getString("off_name"));
//			}
//
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return officeCodeList;
//	}
//
//	public static void insertIntoVhaChangedData(TransactionManager tmgr, String appl_no, String changedData)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//
//		try {
//			
//			if (changedData != null && !changedData.equals("")) {
//				sql = "INSERT INTO VHA_CHANGED_DATA (APPL_NO,EMP_CD,CHANGED_DATA,OP_DT,STATE_CD,OFF_CD) "
//						+ " VALUES(?,?,?,CURRENT_TIMESTAMP,?,?)";
//				ps = tmgr.prepareStatement(sql);
//				if (POSValidator.validate(appl_no, "AN")) {
//					ps.setString(1, appl_no);
//				}
//				if (POSValidator.validate(Util.getEmpCode(), "N")) {
//					ps.setInt(2, Integer.parseInt(Util.getEmpCode()));
//				}
//				if (POSValidator.validate(changedData, "AN")) {
//					ps.setString(3, changedData);
//				}
//				if (POSValidator.validate(Util.getUserStateCode(), "A")) {
//					ps.setString(4, Util.getUserStateCode());
//				}
//				if (POSValidator.validate(String.valueOf(Util.getUserSeatOffCode()), "AN")) {
//					ps.setInt(5, Util.getUserSeatOffCode());
//				}
//
//				ps.executeUpdate();
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		}
//
//	}
//
//	
//	public synchronized static String getUniqueTempApplNo(TransactionManager tmgr, String stateCd)
//			throws VahanException {
//		String applNo = null;
//		try {
//
//			
//			String strSQL = "select  (?||to_char(CURRENT_DATE,'YYMMDD')||'P'|| lpad( nextval('onlinepermit.temp_appl_permit_sequence')::varchar, 7, '0')) AS appl_no";
//			PreparedStatement psmt = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(stateCd, "A")) {
//				psmt.setString(1, stateCd);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				applNo = rs.getString("appl_no");
//				strSQL = "INSERT INTO onlinepermit.vt_unique_appl_nos VALUES (?, current_timestamp)";
//				psmt = tmgr.prepareStatement(strSQL);
//				psmt.setString(1, applNo);
//				psmt.executeUpdate();
//			}
//			rs.close();
//			psmt.close();
//			rs = null;
//			psmt = null;
//		} catch (Exception e) {
//			applNo = null;
//			LOGGER.info("inside method getUniqueTempApplNo");
//			throw new VahanException(
//					"There is some problem while generating application no., please try after some time.");
//		}
//		return applNo;
//	}
//
//	// online permit temp appl no generation
//	public static String getUniqueCouterSignatureAuthNo(TransactionManager tmgr, String stateCd, int off_cd)
//			throws VahanException {
//		String couterSignAuthNo = null;
//		try {
//			String strSQL = "select  (?||?||'/CAUTHNO/'||to_char(CURRENT_DATE,'YYMMDD')||'/'|| lpad( nextval('onlinepermit.count_sign_auth_no')::varchar, 4, '0')) AS count_auth_no";
//			PreparedStatement psmt = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(stateCd, "A")) {
//				psmt.setString(1, stateCd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				psmt.setString(2, String.valueOf(off_cd));
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				couterSignAuthNo = rs.getString("count_auth_no");
//			}
//		} catch (Exception e) {
//			couterSignAuthNo = null;
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Something went wrong, please try again.");
//		}
//		return couterSignAuthNo;
//	}
//
//	public static String getUniqueRCPTNo(TransactionManager tmgr, String stateCd) throws VahanException {
//		String applNo = null;
//		try {
//
//			String strSQL = "select  (?||to_char(CURRENT_DATE,'YYMMDD')||'W'|| lpad( nextval('onlinepermit.temp_appl_permit_sequence')::varchar, 7, '0')) AS appl_no";
//			PreparedStatement psmt = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(stateCd, "A")) {
//				psmt.setString(1, stateCd);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				applNo = rs.getString("appl_no");
//			}
//		} catch (Exception e) {
//			applNo = null;
//			LOGGER.error("inside method getUniqueRCPTNo " + e.toString() + " " + e.getStackTrace()[0]);
//			throw new VahanException("Something went wrong, please try again.");
//		}
//		return applNo;
//	}
//
//	
//
//	public static int generateHashCode(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		int hashCode = 0;
//		if (str != null && str.length() > 0) {
//			String md5String = MD5(str);
//			long asciiSum = getAsciiSum(md5String);
//			hashCode = (int) (asciiSum % 10);
//		}
//		return hashCode;
//	}
//
//	public static String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		MessageDigest md;
//		byte[] md5hash;
//		md = MessageDigest.getInstance(MD5_Value);
//		if (md != null) {
//			md5hash = new byte[32];
//			md.update(text.getBytes("iso-8859-1"), 0, text.length());
//		}
//		md5hash = md.digest();
//		return convertToHex(md5hash);
//	}
//
//	public static String SHA512(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		MessageDigest md;
//		md = MessageDigest.getInstance("SHA-512");
//		byte[] md5hash = md.digest(text.getBytes());
//		return convertToHex(md5hash);
//	}
//
//	public static long getAsciiSum(String str) {
//		long asciiSum = 0;
//		if (str != null && str.length() > 0) {
//			for (int i = 0; i < str.length(); i++) {
//				asciiSum = asciiSum + str.charAt(i);
//			}
//		}
//		return asciiSum;
//	}
//
//	private static String convertToHex(byte[] data) {
//		StringBuffer buf = new StringBuffer();
//		for (int i = 0; i < data.length; i++) {
//			int halfbyte = (data[i] >>> 4) & 0x0F;
//			int two_halfs = 0;
//			do {
//				if ((0 <= halfbyte) && (halfbyte <= 9)) {
//					buf.append((char) ('0' + halfbyte));
//				} else {
//					buf.append((char) ('a' + (halfbyte - 10)));
//				}
//				halfbyte = data[i] & 0x0F;
//			} while (two_halfs++ < 1);
//		}
//		return buf.toString();
//	}
//
//	public static long getOwnerFrom(TransactionManager tmgr, String regn_no, String vhTableName) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		long owner_from = 0;
//		RowSet rs = null;
//		try {
//
//			sql = "SELECT date (max(owner_upto))+ integer '1' as newOwnerFrom ," + " regn_no as new_owner_from FROM "
//					+ vhTableName + " group by regn_no having regn_no=?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//
//			if (rs.next()) { // if any record is exist
//				owner_from = rs.getDate("newOwnerFrom").getTime();
//
//			} else {
//				sql = "SELECT regn_dt FROM  " + TableList.VT_OWNER + " where regn_no=?";
//				ps = tmgr.prepareStatement(sql);
//				if (POSValidator.validate(regn_no, "ANS")) {
//					ps.setString(1, regn_no);
//				}
//				rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//					owner_from = rs.getDate("regn_dt").getTime();
//				}
//			}
//
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		}
//		return owner_from;
//	} // end of getOwnerFrom
//
//	/**
//	 * this method is used to fetch last tax paid date and tax mode
//	 *
//	 * @param regnNo  Registration Number
//	 * @param stateCD State Code
//	 * @param offCD   Office code(rto code)
//	 * @return Last paid tax date(tax_upto) and tax mode
//	 */
//	public static HashMap<String, String> getLastTaxPayedDetails(String regnNo, String stateCD) {
//
//		PreparedStatement psmt = null;
//		TransactionManagerInterface tmgr = null;
//		HashMap<String, String> hm = null;
//		try {
//
//			
//			String vtTax = "SELECT TAX_UPTO,TAX_MODE FROM VT_TAX WHERE TAX_UPTO=(SELECT TAX_UPTO  FROM VT_TAX WHERE  REGN_NO =?  order  by rcpt_dt desc limit 1) and REGN_NO =?";
//			tmgr = new TransactionManagerReadOnly("getLastTaxPayedDetails");
//			psmt = tmgr.prepareStatement(vtTax);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				psmt.setString(1, regnNo);
//			}
//			if (POSValidator.validate(regnNo, "ANS")) {
//				psmt.setString(2, regnNo);
//			}
//			
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				hm = new HashMap<String, String>();
//				hm.put("tax_upto", rs.getString("tax_upto"));
//				hm.put("tax_mode", rs.getString("tax_mode"));
//			}
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return hm;
//	}
//
//	public static double getPurposeFineAmount(String stateCd, int off_cd, int purCd, double feeAmount, Date dueDate,
//			Date paymentDate, VehicleParameters parameters) {
//
//		double fineAmt = 0;
//		double fineAmtTotal = 0;
//		int monthDiff = 0;
//		int dayDiff = 0;
//		int gracePeriod = 0;
//		int holidays = 0;
//		Date dueDateAfterGraceDays = dueDate;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		RowSet rs = null;
//		String sql = null;
//		int perUnit = 0;
//		try {
//			tmgr = new TransactionManagerReadOnly("getPurposeFineAmount");
//
//			sql = "SELECT no_of_holidays * CASE WHEN consider_holiday_fine THEN 1 ELSE 0 END as holiday " + " FROM "
//					+ TableList.THM_OFFICE_CONFIGURATION + " a, " + TableList.TM_CONFIGURATION + " b "
//					+ " WHERE a.state_cd = b.state_cd and b.state_cd = ? and a.off_cd=? "
//					+ " and a.moved_on between ?::date and (?::date + '1 day'::interval - '1 second'::interval) "
//					+ " order by a.moved_on limit 1";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(stateCd, "A")) {
//				ps.setString(1, stateCd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				ps.setInt(2, off_cd);
//			}
//			if (POSValidator.validate(DateUtil.parseDate(paymentDate), "DATE")) {
//				ps.setDate(3, new java.sql.Date(paymentDate.getTime()));
//			}
//			if (POSValidator.validate(DateUtil.parseDate(paymentDate), "DATE")) {
//				ps.setDate(4, new java.sql.Date(paymentDate.getTime()));
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				holidays = rs.getInt("holiday");
//			}
//			sql = "SELECT *,grace_days::numeric as grace_period " + " FROM " + TableList.VM_FEE_MAST_PENALTY
//					+ " WHERE state_cd = ? and pur_cd = ?"
//					+ " and (? between from_dt and upto_dt or ? between from_dt and upto_dt)" + " order by from_dt";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(stateCd, "A")) {
//				ps.setString(1, stateCd);
//			}
//			if (POSValidator.validate(String.valueOf(purCd), "N")) {
//				ps.setInt(2, purCd);
//			}
//			if (POSValidator.validate(DateUtil.parseDate(dueDate), "DATE")) {
//				ps.setDate(3, new java.sql.Date(dueDate.getTime()));
//			}
//			if (POSValidator.validate(DateUtil.parseDate(paymentDate), "DATE")) {
//				ps.setDate(4, new java.sql.Date(paymentDate.getTime()));
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				gracePeriod = 0;
//				dueDateAfterGraceDays = dueDate;
//				Date uptoDate = rs.getDate("upto_dt");
//				Date fromDate = rs.getDate("from_dt");
//				dayDiff = 0;
//				monthDiff = 0;
//				int dayFrom = rs.getInt("day_from");
//				int dayUpto = rs.getInt("day_upto");
//				if (isCondition(replaceTagValues(rs.getString("condition_formula"), parameters),
//						"getPurposeFineAmount-2")) {
//					gracePeriod = holidays + rs.getInt("grace_period");
//					if (gracePeriod > 0) {
//						dueDateAfterGraceDays = DateUtil.dateRange(dueDate, 0, 0, gracePeriod);
//					}
//					// if payment_dt!=due_date && payment_dt > due_date
//					if (DateUtils.compareDates(dueDateAfterGraceDays, paymentDate) != 0
//							&& DateUtils.compareDates(paymentDate, dueDateAfterGraceDays) == 2) {
//
//						Date maxDate = null, minDate = null;
//						// calculation of maximum date from upto_date and
//						// payment_date
//						if (DateUtils.compareDates(dueDateAfterGraceDays, fromDate) == 0) {
//							maxDate = fromDate;
//						} else if (DateUtils.compareDates(dueDateAfterGraceDays, fromDate) == 1) {
//							maxDate = fromDate;
//						} else if (DateUtils.compareDates(dueDateAfterGraceDays, fromDate) == 2) {
//							maxDate = dueDateAfterGraceDays;
//						}
//						// calculation of minimum date from upto_date and
//						// payment_date
//						if (DateUtils.compareDates(uptoDate, paymentDate) == 0) {
//							minDate = uptoDate;
//						} else if (DateUtils.compareDates(uptoDate, paymentDate) == 1) {
//							minDate = uptoDate;
//						} else if (DateUtils.compareDates(uptoDate, paymentDate) == 2) {
//							minDate = paymentDate;
//						}
//
//						if (minDate != null && maxDate != null) {
//							dayDiff = (int) DateUtils.getDate1MinusDate2_Days(maxDate, minDate);
//							dayDiff++;
//							monthDiff = DateUtils.getDate1MinusDate2_Months(maxDate, minDate);
//						}
//
//						if (monthDiff > 0 || dayDiff > 0) {
//
//							if (rs.getString("penalty_mode").equalsIgnoreCase("D")) {
//								if (dayDiff > dayUpto) {
//									dayDiff = dayUpto;
//								}
//								if (dayFrom > 1 && dayDiff > dayFrom) {
//									dayDiff = dayDiff - dayFrom;
//									dayDiff++;
//								}
//							}
//
//							if (rs.getString("penalty_mode").equalsIgnoreCase("M")) {
//								if (monthDiff > dayUpto) {
//									monthDiff = dayUpto;
//								}
//								if (dayFrom > 1 && monthDiff > dayFrom) {
//									monthDiff = monthDiff - dayFrom;
//								}
//							}
//
//							if (rs.getBoolean("is_percent_rate")) {
//								fineAmt = ((double) rs.getFloat("fine_amt") * (feeAmount / 100));
//							} else {
//								fineAmt = (double) rs.getFloat("fine_amt");
//							}
//							if (rs.getInt("per_unit") > 0) {
//								if (rs.getInt("grace_unit_days") > rs.getInt("per_unit")) {
//									if (rs.getString("penalty_mode").equalsIgnoreCase("M")) {
//										monthDiff = monthDiff - rs.getInt("grace_unit_days");
//										if (monthDiff < 0) {
//											monthDiff = 0;
//										}
//									} else if (rs.getString("penalty_mode").equalsIgnoreCase("D")) {
//										dayDiff = dayDiff - rs.getInt("grace_unit_days");
//										if (dayDiff < 0) {
//											dayDiff = 0;
//										}
//									}
//								}
//								if (rs.getString("penalty_mode").equalsIgnoreCase("M") && monthDiff > 0
//										&& monthDiff >= dayFrom && monthDiff <= dayUpto) {
//									perUnit = (int) Math.ceil((monthDiff / Float.valueOf(rs.getInt("per_unit"))));
//								} else if (rs.getString("penalty_mode").equalsIgnoreCase("D") && dayDiff > 0
//										&& dayDiff >= dayFrom && dayDiff <= dayUpto) {
//									perUnit = (int) Math.ceil((dayDiff / Float.valueOf(rs.getInt("per_unit"))));
//								}
//								fineAmt = fineAmt * perUnit;
//							}
//							if (rs.getInt("max_amount") > 0 && fineAmt > rs.getInt("max_amount")) {
//								fineAmt = rs.getInt("max_amount");
//							}
//
//							if (rs.getString("penalty_mode").equalsIgnoreCase("M") && monthDiff > 0
//									&& monthDiff >= dayFrom && monthDiff <= dayUpto) {
//								fineAmtTotal = fineAmtTotal + fineAmt;
//							} else if (rs.getString("penalty_mode").equalsIgnoreCase("D") && dayDiff > 0
//									&& dayDiff >= dayFrom && dayDiff <= dayUpto) {
//								fineAmtTotal = fineAmtTotal + fineAmt;
//							} else if (rs.getString("penalty_mode").equalsIgnoreCase("F") && dayDiff >= dayFrom
//									&& dayDiff <= dayUpto) {// fixed rate
//								fineAmtTotal = fineAmtTotal + fineAmt;
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return fineAmtTotal;
//	}
//
//	public static VehicleParameters fillVehicleParametersFromDobj(OwnerDetailsDobj ownerDobj) {
//
//		VehicleParameters taxParameters = new VehicleParameters();
//		try {
//
//			taxParameters.setAC_FITTED(ownerDobj.getAc_fitted());
//		
//			taxParameters.setCC(ownerDobj.getCubic_cap());// float type
//			
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
//			taxParameters.setREGN_DATE(ownerDobj.getRegn_dt());
//			taxParameters.setTAX_DUE_FROM_DATE(ownerDobj.getRegn_dt());
//			taxParameters.setVCH_CATG(ownerDobj != null ? ownerDobj.getVch_catg().trim() : null);
//			taxParameters.setVH_CLASS(ownerDobj.getVh_class());
//			taxParameters.setSEAT_CAP(ownerDobj.getSeat_cap());
//			taxParameters.setAUDIO_FITTED(ownerDobj.getAudio_fitted());
//			taxParameters.setVIDEO_FITTED(ownerDobj.getVideo_fitted());
//			
//			taxParameters.setVCH_AGE((int) Math.ceil(DateUtil.getDate1MinusDate2_Months(
//					DateUtil.parseDate(DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(ownerDobj.getPurchase_dt())),
//					new Date()) / 12.0));
//
//			taxParameters.setVCH_TYPE(ServerUtil.VehicleClassType(ownerDobj.getVh_class()));
//		} catch (VahanException | DateUtilsException e) {
//			
//			LOGGER.error(e.getMessage());
//		}
//
//		return taxParameters;
//	}
//
//	private static String getRandomNumber() {
//		return String.valueOf(generateRandomNumber(0, 9));
//	}
//
//	public static int generateRandomNumber(int min, int max) {
//		SecureRandom rand = new SecureRandom();
//		return ((int) (rand.nextDouble() * (max - min + 1)) + min);
//	}
//
//	private static char getRandomChar() {
//		char c = 'a';
//		if ((generateRandomNumber(1, 200) % 2) == 1) {
//			c = (char) generateRandomNumber(65, 90);
//		} else {
//			c = (char) generateRandomNumber(97, 122);
//		}
//		return c;
//	}
//
//	public static String generateRandomAlphaNumeric(int no) {
//		String rtnStr = "";
//		try {
//			for (int i = 0; i < no; i++) {
//				if ((generateRandomNumber(1, 200) % 2) == 1) {
//					rtnStr = rtnStr + getRandomNumber();
//				} else {
//					rtnStr = rtnStr + getRandomChar();
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//
//		}
//		return rtnStr;
//	}
//
//	public static Date getStringToDate(String strDt) {
//		
//		Date dt = null;
//		// Constructs a SimpleDateFormat using the given pattern
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			// Parses the given string to a date
//			dt = sdf.parse(strDt);
//		} catch (ParseException e) {
//			LOGGER.error(e.getMessage());
//		}
//		return dt;
//	}
//
//	public static Date getStringToDate1(String strDt) {
//		// return variable
//		Date dt = null;
//		// Constructs a SimpleDateFormat using the given pattern
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
//		try {
//			// Parses the given string to a date
//			dt = sdf.parse(strDt);
//		} catch (ParseException e) {
//			LOGGER.error(e.getMessage());
//		}
//		return dt;
//	}
//
//	public static String getUserCategory(String emailID) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String sql = "";
//		
//		try {
//			tmgr = new TransactionManagerReadOnly("get state list");
//			sql = "SELECT fullname, user_role FROM " + TableList.USER_DTLS + " where email_id=?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(emailID, "EMAIL")) {
//				ps.setString(1, emailID);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				UserRegistrationImpl.category = rs.getString("user_role");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//					throw new VahanException(e.getMessage());
//				}
//			}
//		}
//		return UserRegistrationImpl.category;
//	}
//
//	public static List<DropDownDobj> getAllowedTransactionData(String state_cd, int rto_cd) {
//		
//		List<DropDownDobj> list = new ArrayList<>();
//		DropDownDobj dobj = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String sql = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getUserCategory");
//
//			// changed by Aftab
//			sql = "select distinct a.state_cd , p.off_name,p.off_cd, a.istax,a.isto,a.isca,a.isduprc,a.ishpa,a.ishpt,a.isnoc\n"
//					+ ",a.isfit,a.isdoc,a.iscart,a.isrcparticular,a.ismobilereg,a.isepermit,a.isfpermit,a.isspermit,a.istpermit,a.isauthpermit from onlineschema.vp_allowed_transactions a \n"
//					+ "inner join tm_office p on a.off_cd=p.off_cd and  a.state_cd=p.state_cd\n"
//					+ " where  a.state_cd=? and a.off_cd=?";
//
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(rto_cd), "N")) {
//				ps.setInt(2, rto_cd);
//			}
//			
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				dobj = new DropDownDobj();
//				dobj.setState_cd(rs.getString("state_cd"));
//				dobj.setRto_cd(rs.getString("off_cd"));
//				dobj.setRto_descr(rs.getString("off_name"));
//				dobj.setIsto(rs.getBoolean("isto"));
//				dobj.setTax(rs.getBoolean("istax"));
//				dobj.setDuprc(rs.getBoolean("isduprc"));
//				dobj.setHpa(rs.getBoolean("ishpa"));
//				dobj.setHpt(rs.getBoolean("ishpt"));
//				dobj.setCa(rs.getBoolean("isca"));
//				dobj.setNoc(rs.getBoolean("isnoc"));
//				dobj.setFit(rs.getBoolean("isfit"));
//				dobj.setDoc(rs.getBoolean("isdoc"));
//				dobj.setVehCart(rs.getBoolean("iscart"));
//				dobj.setRcPerticuler(rs.getBoolean("isrcparticular"));
//				dobj.setMobileReg(rs.getBoolean("ismobilereg"));
//				dobj.setEpermit(rs.getBoolean("isepermit"));
//				dobj.setFpermit(rs.getBoolean("isfpermit"));
//				dobj.setSpermit(rs.getBoolean("isspermit"));
//				dobj.setTpermit(rs.getBoolean("istpermit"));
//				dobj.setAuthpermit(rs.getBoolean("isauthpermit"));
//
//				list.add(dobj);
//			}
//
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//
//				}
//			}
//		}
//		return list;
//	}
//
//	// For state//
//	public static List<DropDownDobj> getStateTransactionData(String state_cd) {
//		
//		List<DropDownDobj> list = new ArrayList<>();
//		DropDownDobj dobj = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String sql = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getUserCategory");
//
//			sql = "SELECT a.state_cd , p.off_name,p.off_cd, a.istax,a.isto,a.isca,a.isduprc,a.ishpa,a.ishpt,a.isnoc,a.isfit,a.isprmt,a.isdoc,a.iscart  "
//					+ " FROM onlineschema.vp_allowed_transactions a\n"
//					+ "  inner join tm_office p on a.off_cd=p.off_cd and a.state_cd=p.state_cd\n"
//					+ "  where a.state_cd=?; ";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			LOGGER.info("ps : " + ps);
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				dobj = new DropDownDobj();
//				dobj.setState_cd(rs.getString("state_cd"));
//				dobj.setRto_cd(rs.getString("off_cd"));
//				dobj.setRto_descr(rs.getString("off_name"));
//				dobj.setIsto(rs.getBoolean("isto"));
//				dobj.setTax(rs.getBoolean("istax"));
//				dobj.setDuprc(rs.getBoolean("isduprc"));
//				dobj.setHpa(rs.getBoolean("ishpa"));
//				dobj.setHpt(rs.getBoolean("ishpt"));
//				dobj.setCa(rs.getBoolean("isca"));
//				dobj.setNoc(rs.getBoolean("isnoc"));
//				dobj.setFit(rs.getBoolean("isfit"));
//				dobj.setDoc(rs.getBoolean("isdoc"));
//				dobj.setVehCart(rs.getBoolean("iscart"));
//				dobj.setRcPerticuler(rs.getBoolean("isrcparticular"));
//				dobj.setMobileReg(rs.getBoolean("ismobilereg"));
//				dobj.setEpermit(rs.getBoolean("isepermit"));
//				dobj.setFpermit(rs.getBoolean("isfpermit"));
//				dobj.setSpermit(rs.getBoolean("isspermit"));
//				dobj.setTpermit(rs.getBoolean("istpermit"));
//				dobj.setAuthpermit(rs.getBoolean("isauthpermit"));
//
//				list.add(dobj);
//			}
//
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//
//				}
//			}
//		}
//		return list;
//	}
//
//	// for update data
//	// for update data
//	static boolean udatevp_allowed_transactions(List<DropDownDobj> list, String check) {
//		DropDownDobj dobj = null;
//		boolean flag = false;
//		TransactionManager tmgr = null;
//		PreparedStatement ps = null;
//		try {
//			tmgr = new TransactionManager("udatevp_allowed_transactions");
//			String update_sql = "UPDATE onlineschema.vp_allowed_transactions "
//					+ " SET state_cd=?, off_cd=?, istax=?, isto=?, isca=?, isduprc=?, ishpa=?, "
//					+ " ishpt=?, isnoc=?, isfit=?, isdoc=? ,  " + " isdupfit=?,isrenreg=?, isvehconv=?, "
//					+ " isvehreasgn=?,isvehalt=?,iscart=?,isrcparticular=?,ismobilereg=? WHERE state_cd=? and off_cd=?;";
//			String update_sql1 = "UPDATE onlineschema.vp_allowed_transactions "
//					+ " SET state_cd=?, off_cd=?, isepermit=?, isfpermit=?, isspermit=?, istpermit=?, isauthpermit=? "
//					+ "WHERE state_cd=? and off_cd=?;";
//
//			Iterator itr1 = list.iterator();
//			while (itr1.hasNext()) {
//				ps = tmgr.prepareStatement(update_sql);
//				dobj = (DropDownDobj) itr1.next();
//				int i = 1;
//				if (POSValidator.validate(dobj.getState_cd(), "A")) {
//					ps.setString(i++, dobj.getState_cd());
//				}
//				if (POSValidator.validate(dobj.getRto_cd(), "N")) {
//					ps.setInt(i++, Integer.parseInt(dobj.getRto_cd()));
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isTax()), "A")) {
//					ps.setBoolean(i++, dobj.isTax());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isIsto()), "A")) {
//					ps.setBoolean(i++, dobj.isIsto());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isCa()), "A")) {
//					ps.setBoolean(i++, dobj.isCa());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isDuprc()), "A")) {
//					ps.setBoolean(i++, dobj.isDuprc());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isHpa()), "A")) {
//					ps.setBoolean(i++, dobj.isHpa());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isHpt()), "A")) {
//					ps.setBoolean(i++, dobj.isHpt());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isNoc()), "A")) {
//					ps.setBoolean(i++, dobj.isNoc());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isFit()), "A")) {
//					ps.setBoolean(i++, dobj.isFit());
//				}
//				
//				if (POSValidator.validate(String.valueOf(dobj.isDoc()), "A")) {
//					ps.setBoolean(i++, dobj.isDoc());
//				}
//
//				if (POSValidator.validate(String.valueOf(dobj.isDupFit()), "A")) {
//					ps.setBoolean(i++, dobj.isDupFit());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isRenReg()), "A")) {
//					ps.setBoolean(i++, dobj.isRenReg());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isVehConv()), "A")) {
//					ps.setBoolean(i++, dobj.isVehConv());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isVehReAssign()), "A")) {
//					ps.setBoolean(i++, dobj.isVehReAssign());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isVehAlt()), "A")) {
//					ps.setBoolean(i++, dobj.isVehAlt());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isVehCart()), "A")) {
//					ps.setBoolean(i++, dobj.isVehCart());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isRcPerticuler()), "A")) {
//					ps.setBoolean(i++, dobj.isRcPerticuler());
//				}
//
//				if (POSValidator.validate(String.valueOf(dobj.isMobileReg()), "A")) {
//					ps.setBoolean(i++, dobj.isMobileReg());
//				}
//				if (POSValidator.validate(dobj.getState_cd(), "A")) {
//					ps.setString(i++, dobj.getState_cd());
//				}
//				if (POSValidator.validate(dobj.getRto_cd(), "N")) {
//					ps.setInt(i++, Integer.parseInt(dobj.getRto_cd()));
//				}
//				
//				ps.executeUpdate();
//			}
//
//			if (check.equalsIgnoreCase("permit")) {
//				ps = tmgr.prepareStatement(update_sql1);
//				dobj = (DropDownDobj) itr1.next();
//				int i = 1;
//				if (POSValidator.validate(dobj.getState_cd(), "A")) {
//					ps.setString(i++, dobj.getState_cd());
//				}
//				if (POSValidator.validate(dobj.getRto_cd(), "N")) {
//					ps.setInt(i++, Integer.parseInt(dobj.getRto_cd()));
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isEpermit()), "A")) {
//					ps.setBoolean(i++, dobj.isEpermit());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isFpermit()), "A")) {
//					ps.setBoolean(i++, dobj.isFpermit());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isSpermit()), "A")) {
//					ps.setBoolean(i++, dobj.isSpermit());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isTpermit()), "A")) {
//					ps.setBoolean(i++, dobj.isTpermit());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.isAuthpermit()), "A")) {
//					ps.setBoolean(i++, dobj.isAuthpermit());
//				}
//				if (POSValidator.validate(dobj.getState_cd(), "A")) {
//					ps.setString(i++, dobj.getState_cd());
//				}
//				if (POSValidator.validate(dobj.getRto_cd(), "N")) {
//					ps.setInt(i++, Integer.parseInt(dobj.getRto_cd()));
//				}
//				
//				ps.executeUpdate();
//			}
//
//			tmgr.commit();
//			flag = true;
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//
//			}
//		}
//		return flag;
//	}
//
//	public static String getVahanPgiUrl(String Conn_Type) {
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String vahan_url = "";
//		String whereIam = "ServerUtils.getVahanPgiUrl()";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			sql = "select conn_dblink from tm_dblink_list where conn_type = ? ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, Conn_Type);
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				vahan_url = rs.getString("conn_dblink");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return vahan_url;
//	}
//
//	public static boolean getPaymentGatewayResponseForTransaction(String TransID) {
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		
//		String whereIam = "ServerUtils.getPaymentGatewayResponseForTransaction()";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			sql = "SELECT * FROM VAHANPGI.VP_PGI_DETAILS WHERE PAYMENT_ID=? AND RESPONSE_CODE !='P'";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(TransID, "AN")) {
//				ps.setString(1, TransID);
//			}
//
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				
//				return true;
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return false;
//	}
//
//	/**
//	 * It provides the pending transaction for schedular.
//	 */
//	public static List<DOBankTransaction> getPendingTransactionList() {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		DOBankTransaction dobjPayment;
//		long totalTransAmount = 0;
//		String despStr = null;
//		List<DOBankTransaction> listDOBankTransactions = new ArrayList<DOBankTransaction>();
//		String sql = "SELECT transaction_no,STATE_CD,OFF_CD,PUR_CD,DESP,DEAL_CD,OP_DT FROM " + TableList.VT_TEMP_APPROVE
//				+ " WHERE PUR_CD=58 ORDER BY OP_DT DESC";
//		try {
//			tmgr = new TransactionManagerReadOnly("ServerUtil.getPendingTransactionList()");
//			pstm = tmgr.prepareStatement(sql);
//
//			RowSet rs = tmgr.fetchDetachedRowSet();
//
//			if (rs.next()) {
//				dobjPayment = new DOBankTransaction();
//				dobjPayment.setTransactionID(rs.getString("transaction_no")); // TransactionID
//																				// of
//																				// the
//																				// Transaction
//				dobjPayment.setStateCode(rs.getString("STATE_CD")); // State
//																	// Code
//				dobjPayment.setRtoCode(rs.getInt("OFF_CD"));// Rto Code
//				dobjPayment.setPurCode(rs.getInt("PUR_CD")); // Purpose Code of
//																// the
//																// Transaction
//				dobjPayment.setRegnNo(rs.getString("DEAL_CD"));
//				dobjPayment.setOpDate(rs.getString("op_dt"));
//				despStr = rs.getString("DESP");
//				// fetching total transaction amount
//				List<Tax_Pay_Dobj> taxBrkup = CollectionOfRoadTaxImpl.getTaxBrkUp(despStr, null, null, null, 00);
//				for (Tax_Pay_Dobj dobj : taxBrkup) {
//					totalTransAmount += dobj.getFinalTaxAmount();
//				}
//				totalTransAmount += CommonUtils.getUserServiceCharge(despStr);
//
//				dobjPayment.setTotalTransAmount(totalTransAmount);
//
//				if (isValidPendingTrans(dobjPayment)) {
//
//					listDOBankTransactions.add(dobjPayment);
//				}
//
//				
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//				if (pstm != null) {
//					pstm.close();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return listDOBankTransactions;
//	}
//
//	public static void getICICIPendingTransaction(String regnNO, OwnerDetailsDobj owner_dobj,
//			TaxResponseEntity taxResponseEntity) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		DOBankTransaction dobjPayment;
//		long totalTransAmount = 0;
//		String despStr = null;
//		String sql = "SELECT transaction_no,STATE_CD,OFF_CD,PUR_CD,DESP,DEAL_CD,OP_DT FROM " + TableList.VT_TEMP_APPROVE
//				+ " WHERE PUR_CD=58 AND DEAL_CD=? ORDER BY OP_DT DESC";
//		try {
//			tmgr = new TransactionManagerReadOnly("ServerUtil.getPendingTransactionList()");
//			pstm = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regnNO, "ANS")) {
//				pstm.setString(1, regnNO);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				dobjPayment = new DOBankTransaction();
//				dobjPayment.setTransactionID(rs.getString("transaction_no")); // TransactionID
//																				// of
//																				// the
//																				// Transaction
//				dobjPayment.setStateCode(rs.getString("STATE_CD")); // State
//																	// Code
//				dobjPayment.setRtoCode(rs.getInt("OFF_CD"));// Rto Code
//				dobjPayment.setPurCode(rs.getInt("PUR_CD")); // Purpose Code of
//																// the
//																// Transaction
//				dobjPayment.setRegnNo(rs.getString("DEAL_CD"));
//				dobjPayment.setOpDate(rs.getString("op_dt"));
//				despStr = rs.getString("DESP");
//				// fetching total transaction amount
//				List<Tax_Pay_Dobj> taxBrkup = CollectionOfRoadTaxImpl.getTaxBrkUp(despStr, null, null, null, 00);
//				for (Tax_Pay_Dobj dobj : taxBrkup) {
//					totalTransAmount += dobj.getFinalTaxAmount();
//				}
//				dobjPayment.setTotalTransAmount(totalTransAmount);
//				taxResponseEntity.setAmount(String.valueOf(dobjPayment.getTotalTransAmount()));
//				taxResponseEntity.setTaxFrom(taxBrkup.get(0).getFinalTaxFrom());
//				taxResponseEntity.setTaxupto(taxBrkup.get(0).getFinalTaxUpto());
//				taxResponseEntity.setTransactionId(dobjPayment.getTransactionID());
//				taxResponseEntity.setOwner(owner_dobj.getOwner_name());
//				taxResponseEntity.setDtoName(owner_dobj.getOff_name());
//				taxResponseEntity.setChassisNumber(owner_dobj.getChasi_no());
//				taxResponseEntity.setEngineNumber(owner_dobj.getEng_no());
//				taxResponseEntity.setVehType(String.valueOf(owner_dobj.getVehType()));
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//	}
//
//	private static boolean isValidPendingTrans(DOBankTransaction dobj) {
//		boolean flag = true;
//		if (CommonUtils.isNullOrBlank(dobj.getTransactionID())) {
//			flag = false;
//		}
//		if (CommonUtils.isNullOrBlank(String.valueOf(dobj.getRtoCode()))) {
//			flag = false;
//		}
//		if (CommonUtils.isNullOrBlank(dobj.getStateCode())) {
//			flag = false;
//		}
//		if (CommonUtils.isNullOrBlank(dobj.getOpDate())) {
//			flag = false;
//		}
//		if (CommonUtils.isNullOrBlank(dobj.getTotalTransAmount() + "") || dobj.getTotalTransAmount() <= 0) {
//			flag = false;
//		}
//
//		return flag;
//	}
//
//	public static int total_transaction(String application_status) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String sql = "";
//		int counter = 0;
//		
//		try {
//			tmgr = new TransactionManagerReadOnly("total_transaction");
//			sql = "SELECT COUNT(application_status) as transation FROM onlineschema.vt_temp_appl_transaction where application_status='S';";
//			ps = tmgr.prepareStatement(sql);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//
//				counter = rs.getInt("transation");
//
//			}
//		} catch (SQLException e) {
//
//			LOGGER.error(e.getMessage());
//
//		} finally {
//
//			if (tmgr != null) {
//				try {
//					if (ps != null) {
//						ps.close();
//					}
//					tmgr.release();
//
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//
//				}
//			}
//		}
//		return counter;
//
//	}
//
//	public static int total_count() {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String sql = "";
//		int counter = 0;
//		
//		try {
//			tmgr = new TransactionManagerReadOnly("total_count");
//			sql = " select nextval('hit_counter') as total_count from onlineschema.hit_counter";
//			ps = tmgr.prepareStatement(sql);
//
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//
//				counter = rs.getInt("total_count");
//
//			}
//		} catch (SQLException e) {
//
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//					ps.close();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//
//				}
//			}
//		}
//		return counter;
//
//	}
//
//	public static long getUserServiceCharge(String desp) {
//		long userServiceCharge = 0;
//
//		int index1 = desp.indexOf("USER_CHARGE");
//		int index2 = desp.lastIndexOf("USER_CHARGE");
//		int lastInd = desp.indexOf(">><", index1);
//		if (index1 == index2) {
//
//			
//			String serStr = desp.substring(index1, lastInd);
//
//			int indxEqul = serStr.indexOf("=");
//
//			userServiceCharge = Long.valueOf(serStr.substring(indxEqul + 1));
//
//		}
//		return userServiceCharge;
//	}
//
//	/**
//	 * Aftab khan
//	 *
//	 * @param regn_no
//	 * @param state_cd
//	 * @return whether this regn_no is allowed to pay fitness fee or not check
//	 *         current date with (fit_upto-nid_days) TM_CONFIGURATION.NID_DAYS
//	 */
//	public static boolean getFitnessEligibility(String fit_upto, String state_cd) {
//
//		
//		boolean fitEligibility = false;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtil.getFitnessEligibility";
//		String tmConfiqsql = "";
//		int nid_days = 0;
//		String current_date = null;
//		String nid_date = null;
//
//		try {
//			current_date = DateUtil.getCurrentDateDDMMYYY();
//
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			tmConfiqsql = "SELECT nid_days,fit_fine_due_nid FROM " + TableList.TM_CONFIGURATION + " WHERE state_cd=?";
//
//			ps = tmgr.prepareStatement(tmConfiqsql);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				nid_days = rs.getInt("nid_days");
//				fit_fine_due_nid = rs.getBoolean("fit_fine_due_nid");
//
//			}
//
//			Date nidDate = DateUtil.addToDate(fit_upto, DateUtil.DAY, nid_days); // formated
//																					// date
//																					// Wed
//																					// Jan
//																					// 18
//																					// 00:00:00
//																					// IST
//																					// 2017
//			String nid_date1 = DateUtil.parseDateToString(nidDate); // 18-Jan-2017
//			// nid_date = fit_upto - nid_days
//			nid_date = DateUtil.getStringDDMMMYYYYtoStringDDMMYYYY(nid_date1); // 18-01-2017
//
//			// cur_date==>>" + current_date
//			// + " New Date after nid_days difference => " + nid_date);
//
//			if (DateUtil.isBefore(fit_upto, current_date)) { // Condition 1
//
//				// ");
//				fitEligibility = true;
//				return fitEligibility;
//			} else if (current_date.equalsIgnoreCase(fit_upto)
//					|| (DateUtil.isBefore(nid_date, current_date) || (current_date.equalsIgnoreCase(nid_date)))) {
//
//				// penalty");
//				fitEligibility = true;
//				return fitEligibility;
//			} else {
//				LOGGER.info("Fitness already valid.");
//			}
//
//		} catch (DateUtilsException | SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return fitEligibility;
//	}
//
//	/**
//	 * To check whether vehicle for this state code is allowed for Application for
//	 * getting Permit
//	 *
//	 * @param state_cd
//	 * @return
//	 */
//	public static boolean isAllowedForPermitAppl(String state_cd) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtil.isAllowedForPermitAppl";
//		String tm_purpose_action_flow_sql = "";
//
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			tm_purpose_action_flow_sql = "SELECT * FROM " + TableList.TM_PURPOSE_ACTION_FLOW
//					+ " WHERE state_cd=? AND pur_cd = ?";
//
//			ps = tmgr.prepareStatement(tm_purpose_action_flow_sql);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			ps.setInt(2, ApplicationConfig.PUR_PERMIT_APPL);
//
//			
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				return true;
//			}
//
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return false;
//	}
//
//	/**
//	 * To get due date to calculate fine for Fitness
//	 *
//	 * @param purCd
//	 * @param applNo
//	 * @param dobj
//	 * @return due Date
//	 */
//	public static Date getRegVehDueDate(int purCd, OwnerDetailsDobj dobj) {
//		Date date = new Date();
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		RowSet rs = null;
//
//		try {
//			tmgr = new TransactionManagerReadOnly("ServerUtil.getRegVehDueDate");
//			switch (purCd) {
//
//			case ApplicationConfig.PUR_FITNESS:
//				if (dobj != null && dobj.getFit_upto() != null) {
//					date = DateUtil.dateRange(ServerUtil.getStringToDate(dobj.getFit_upto()), 0, 0, 1);
//
//					
//					if (ServerUtil.fit_fine_due_nid) {
//						sql = "SELECT fit_nid FROM " + TableList.VT_FITNESS
//								+ " WHERE regn_no=? order by op_dt desc limit 1";
//						ps = tmgr.prepareStatement(sql);
//						if (POSValidator.validate(dobj.getRegn_no(), "ANS")) {
//							ps.setString(1, dobj.getRegn_no());
//						}
//						rs = tmgr.fetchDetachedRowSet();
//						if (rs.next()) {
//							if (rs.getDate("fit_nid") != null) {
//								date = rs.getDate("fit_nid");
//								date = DateUtil.dateRange(date, 0, 0, 1);
//							}
//						}
//					}
//				}
//				break;
//
//			default:
//				date = new Date();
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//
//				}
//			}
//		}
//
//		return date;
//	}
//	
//
//	public static Timestamp getSystemDateInPostgres() {
//		Date cur_date = new Date();
//		SimpleDateFormat sdf_dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String currnet_date_time = sdf_dt.format(cur_date);
//		Timestamp date_time = Timestamp.valueOf(currnet_date_time);
//		return date_time;
//	}
//
//	// To Check whether given rto_cd exists in PERMIT.VM_OFF_ALLOTMENT
//	public static int getPermitRtoCode(int off_cd, String state_cd) {
//		String off_code = "," + String.valueOf(off_cd) + ",";
//		int permitRtoCode = 0;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtil.getPermitRtoCode";
//		String sqlPermitRto = "";
//		String alloted_off_cd = "";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			sqlPermitRto = "SELECT off_cd, (','||allotted_off_cd||',') as allott_off_cd FROM "
//					+ TableList.VM_OFF_ALLOTMENT + " WHERE state_cd=?";
//
//			ps = tmgr.prepareStatement(sqlPermitRto);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				alloted_off_cd = rs.getString("allott_off_cd");
//				String db_Off_cd = String.valueOf(rs.getInt("off_cd"));
//				if (alloted_off_cd.contains(off_code)) {
//					permitRtoCode = Integer.parseInt(db_Off_cd);
//					break;
//				}
//			}
//
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//
//		return permitRtoCode;
//	}
//
//	/**
//	 * Method is used to get the parameters on which vehicle tax depends based on
//	 * state code and vh class.
//	 *
//	 * @param State   Code
//	 * @param Vehicle Class
//	 * @return parameters on which tax depends
//	 * @throws VahanException
//	 */
//	public static String[] getFieldsReqForTax(String state_cd, int vhClass) throws VahanException {
//		String[] paramList = null;
//		RowSet rs = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getTaxPerameter");
//			String Query = "SELECT string_agg(code,',') as code from get_fields_rqrd_for_tax(?,?)";
//			ps = tmgr.prepareStatement(Query);
//			int i = 1;
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(i++, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(vhClass), "N")) {
//				ps.setInt(i++, vhClass);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				String code = rs.getString("code");
//				paramList = code.split(",");
//			}
//		} catch (SQLException e) {
//			paramList = null;
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (rs != null) {
//					rs.close();
//					rs = null;
//				}
//			} catch (Exception e) {
//				paramList = null;
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return paramList;
//	}
//
//	public static List<Tax_Pay_Dobj> getPrmtDobjToTaxDobj(List<PermitDetailDobj> prmtDobjList) {
//		PermitDetailDobj prmtDobj = null;
//		List<Tax_Pay_Dobj> tax_pay_dobj_List = null;
//		Tax_Pay_Dobj pay_Dobj = null;
//		Iterator<PermitDetailDobj> itr = prmtDobjList.iterator();
//		while (itr.hasNext()) {
//			try {
//				prmtDobj = (PermitDetailDobj) itr.next();
//				pay_Dobj = new Tax_Pay_Dobj();
//				// get value from PermitDetailDobj and set to Tax_Pay_Dobj
//
//				pay_Dobj.setPur_cd(prmtDobj.getPur_cd());
//				pay_Dobj.setTaxPurcdDesc(getFeePurDesc(pay_Dobj.getPur_cd()));
//				if (pay_Dobj.getPur_cd() == ApplicationConfig.PUR_E_PERMIT) {
//					pay_Dobj.setTaxPurcdDesc("ePermit");
//				}
//				pay_Dobj.setRegnNo(prmtDobj.getRegn_no());
//				pay_Dobj.setState_cd(prmtDobj.getState_cd());
//				pay_Dobj.setOff_cd(prmtDobj.getOff_cd());
//				long fees = prmtDobj.getFees();
//				long fine = prmtDobj.getFine();
//				pay_Dobj.setTotalPaybaleTax(fees);
//				pay_Dobj.setTotalPaybalePenalty(fine);
//				long total = fees + fine;
//				pay_Dobj.setFinalTaxAmount(total);
//
//				// prmtDobj.getRcpt_dt()); //2016-04-28 11:41:48.002467
//				pay_Dobj.setRcpt_dt(new Timestamp(DateUtil.parseDate(prmtDobj.getRcpt_dt()).getTime()));
//
//				// pay_Dobj.getRcpt_dt());
//				// add pay_Dobj in List
//				tax_pay_dobj_List = new ArrayList<>();
//				tax_pay_dobj_List.add(pay_Dobj);
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//
//		}
//
//		return tax_pay_dobj_List;
//
//	}
//
//	// To check Permit Insurance and Challan validation @ 08-04-2016
//	public static PermitCheckDetailsDobj getPrmtInsChalanDtls(String regn_no) {
//		PermitCheckDetailsDobj pmtCheckDobj = new PermitCheckDetailsDobj();
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//
//		pmtCheckDobj.setInsValid(false);
//		pmtCheckDobj.setInsSaveData(true);
//		// challan
//		pmtCheckDobj.setChalPending(true);
//		try {
//			// challan query
//			String challanQuery = "SELECT chal_no, to_char(chal_date,'DD-MON-YYYY') as chal_date, chal_time, chal_place,user_name as chal_officer FROM "
//					+ TableList.VA_CHALLAN + " \n" + "inner join " + TableList.TM_USER_INFO
//					+ " on user_cd = chal_officer :: numeric\n" + "where regn_no = ?";
//			tmgr = new TransactionManagerReadOnly("Get Challan Details");
//			ps = tmgr.prepareStatement(challanQuery);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				pmtCheckDobj.setChalDate(rs.getString("chal_date"));
//				pmtCheckDobj.setChalNo(rs.getString("chal_no"));
//				pmtCheckDobj.setChalOfficer(rs.getString("chal_officer"));
//				pmtCheckDobj.setChalPlace(rs.getString("chal_place"));
//				pmtCheckDobj.setChalTime(rs.getString("chal_time"));
//				pmtCheckDobj.setChalPending(false);
//			}
//			ps.close();
//			rs.close();
//			// Insurance query
//			Date date = new Date();
//			String insrnceQuery = "SELECT cmpy_cd.descr as comp_cd,ins_cd.descr as ins_type, to_char(ins_from,'DD-MON-YYYY') as ins_from, to_char(ins_upto,'DD-MON-YYYY') as ins_upto, policy_no FROM "
//					+ TableList.VT_INSURANCE + "\n" + "inner join " + TableList.VM_ICCODE
//					+ " cmpy_cd ON ic_code = comp_cd\n" + "inner join  " + TableList.VM_INSTYP
//					+ " ins_cd ON instyp_code = ins_type\n" + "WHERE regn_no = ?;";
//			tmgr = new TransactionManager("Get Insurance Details");
//			ps = tmgr.prepareStatement(insrnceQuery);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				pmtCheckDobj.setInsFrom(rs.getString("ins_from"));
//				pmtCheckDobj.setInsPolicyNo(rs.getString("policy_no"));
//				pmtCheckDobj.setInsType(rs.getString("ins_type"));
//				pmtCheckDobj.setInsUpto(rs.getString("ins_upto"));
//				pmtCheckDobj.setInsCmpyNo(rs.getString("comp_cd"));
//				pmtCheckDobj.setInsValid(true);
//				pmtCheckDobj.setInsSaveData(false);
//
//			}
//			if (CommonUtils.isNullOrBlank(pmtCheckDobj.getInsUpto())
//					|| CommonPermitPrintImpl.getDateDD_MMM_YYYY(pmtCheckDobj.getInsUpto()).getTime() < date.getTime()) {
//				String Query = "SELECT cmpy_cd.descr as comp_cd,ins_cd.descr as ins_type, to_char(ins_from,'DD-MON-YYYY') as ins_from, to_char(ins_upto,'DD-MON-YYYY') as ins_upto, policy_no FROM "
//						+ TableList.VA_INSURANCE + "\n" + "inner join " + TableList.VM_ICCODE
//						+ " cmpy_cd ON ic_code = comp_cd " + "inner join  " + TableList.VM_INSTYP
//						+ " ins_cd ON instyp_code = ins_type " + "WHERE regn_no = ?;";
//
//				ps = tmgr.prepareStatement(Query);
//				if (POSValidator.validate(regn_no, "ANS")) {
//					ps.setString(1, regn_no);
//				}
//				rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//					pmtCheckDobj.setInsFrom(rs.getString("ins_from"));
//					pmtCheckDobj.setInsPolicyNo(rs.getString("policy_no"));
//					pmtCheckDobj.setInsType(rs.getString("ins_type"));
//					pmtCheckDobj.setInsUpto(rs.getString("ins_upto"));
//					pmtCheckDobj.setInsCmpyNo(rs.getString("comp_cd"));
//					pmtCheckDobj.setInsValid(true);
//					pmtCheckDobj.setInsSaveData(false);
//				}
//				if (CommonUtils.isNullOrBlank(pmtCheckDobj.getInsUpto()) || CommonPermitPrintImpl
//						.getDateDD_MMM_YYYY(pmtCheckDobj.getInsUpto()).getTime() < date.getTime()) {
//					pmtCheckDobj.setInsValid(false);
//					pmtCheckDobj.setInsSaveData(true);
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (rs != null) {
//					rs.close();
//
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return pmtCheckDobj;
//	}
//
//	public static boolean isValidSession(HttpSession session) {
//		if (session != null && !session.isNew()) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public static boolean isValidICICI_IsureID(String iSureID, String state_CD) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtils.isValidICICI_IsureID";
//		String sqlPgiVPDtls = "SELECT * FROM " + TableList.vp_details + " where return_rcpt_no=? and state_cd=?";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlPgiVPDtls);
//			if (POSValidator.validate(iSureID, "AN")) {
//				pstm.setString(1, iSureID);
//			}
//			if (POSValidator.validate(state_CD, "A")) {
//				pstm.setString(2, state_CD);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				return false;
//			}
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return true;
//	}
//	// addedByVinit19July16
//	// getReNewFitnessUpto
//
//	public static int getReNewFitnessUpto(OwnerDetailsDobj own_dobj) throws VahanException {
//		int fitUpto = 0;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//
//		Exception e = null;
//		VehicleParameters vehParameters = fillVehicleParametersFromDobj(own_dobj);
//
//		try {
//			String[][] data = MasterTableFiller.masterTables.VM_VH_CLASS.getData();
//			for (int i = 0; i < data.length; i++) {
//				if (data[i][0].trim().equals(String.valueOf(own_dobj.getVh_class()).trim())) {
//					if (data[i][2].equals(String.valueOf(TableConstants.VM_VEHTYPE_TRANSPORT))) {
//						vehParameters.setVCH_TYPE(TableConstants.VM_VEHTYPE_TRANSPORT);
//						break;
//					} else {
//						vehParameters.setVCH_TYPE(TableConstants.VM_VEHTYPE_NON_TRANSPORT);
//						break;
//					}
//				}
//			}
//
//			vehParameters.setVCH_CATG(own_dobj.getVch_catg());
//			vehParameters.setVH_CLASS(own_dobj.getVh_class());
//			vehParameters.setSEAT_CAP(own_dobj.getSeat_cap());
//
//			tmgr = new TransactionManagerReadOnly("Fitness_Impl");
//			ps = tmgr.prepareStatement("SELECT *  FROM vm_validity_mast where pur_cd=? and state_cd=?");
//			ps.setInt(1, TableConstants.VM_TRANSACTION_MAST_FIT_CERT);
//			if (POSValidator.validate(own_dobj.getState_cd(), "A")) {
//				ps.setString(2, own_dobj.getState_cd());
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				if (FormulaUtils.isCondition(
//						FormulaUtils.replaceTagValues(rs.getString("condition_formula"), vehParameters),
//						("Server Util vm validity mast) for state : " + own_dobj.getState_cd()))) {
//					// break;
//					fitUpto = rs.getInt("re_new_value");
//				}
//			}
//
//		} catch (SQLException sq) {
//			e = sq;
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception ex) {
//				LOGGER.error(ex.getMessage());
//			}
//		}
//
//		if (e != null) {
//			throw new VahanMessageException("Error in Getting Validity Upto Date");
//		}
//		if (fitUpto == 0) {
//			throw new VahanMessageException("Renewal of Fitness is Not Permitted.");
//		}
//		return fitUpto;
//	}
//
//	public static boolean validateFitnessApp(Date fitUpto) {
//		boolean flag = false;
//		Date today = new Date();
//		Date priorMonth = DateUtil.dateRange(today, 0, 1, 0);
//		if (fitUpto.compareTo(today) < 0) {// Expired from current date
//			flag = true;
//		} else if ((fitUpto.compareTo(today) > 0) && (fitUpto.compareTo(priorMonth) <= 0)) {// Valid
//																							// from
//																							// current
//																							// date
//																							// but
//																							// under
//																							// 1
//																							// month
//																							// of
//																							// validity
//			flag = true;
//		}
//
//		return flag;
//	}
//
//	// Aftab @ 20-07-2016 To close streams properly
//	public static void safeClose(InputStream is) {
//		if (is != null) {
//			try {
//				is.close();
//			} catch (IOException e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//	}
//
//	// Aftab @ 09-09-2016 for Pmt_no and Auth_no in Permit_Home_Auth Table
//	public static String getUniquePermitNo(TransactionManagerInterface tmgr, String stateCd, int off_cd, int permitType,
//			int PermitCatg, String flag) throws VahanException {
//		String strSQL = "";
//		String pmtNo = "";
//		String tempSeries = "";
//		PreparedStatement psmt = null;
//		RowSet rs = null;
//		boolean isPmtNoFound = false;
//		try {
//			if (stateCd == null || off_cd == 0) {
//				throw new VahanException("Something went wrong, please try again.");
//			}
//
//			strSQL = "SELECT date_part('year',CURRENT_DATE)::numeric >=2020 yr";
//			tmgr.prepareStatement(strSQL);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				if (rs.getBoolean("yr")) {
//					return getUniqueNumber(tmgr, stateCd, permitType, flag);
//				}
//			}
//			java.util.Calendar cal = java.util.Calendar.getInstance();
//			int Year = cal.get(java.util.Calendar.YEAR);
//			strSQL = "SELECT * FROM vsm_permit_no WHERE state_cd = ? AND off_cd = ? AND permit_type = ? AND permit_catg = ? AND flag = ? ";
//			psmt = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(stateCd, "A")) {
//				psmt.setString(1, stateCd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				psmt.setInt(2, off_cd);
//			}
//			if (POSValidator.validate(String.valueOf(permitType), "N")) {
//				psmt.setInt(3, permitType);
//			}
//			if (POSValidator.validate(String.valueOf(PermitCatg), "N")) {
//				psmt.setInt(4, PermitCatg);
//			}
//			if (POSValidator.validate(flag, "A")) {
//				psmt.setString(5, flag);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				isPmtNoFound = true;
//				strSQL = "UPDATE vsm_permit_no SET SEQUENCE_NO = SEQUENCE_NO + 1 WHERE state_cd = ? AND off_cd = ? AND permit_type = ? AND permit_catg = ? AND flag = ? ";
//
//				rs.close();
//				psmt.close();
//				psmt = tmgr.prepareStatement(strSQL);
//				if (POSValidator.validate(stateCd, "A")) {
//					psmt.setString(1, stateCd);
//				}
//				if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//					psmt.setInt(2, off_cd);
//				}
//				if (POSValidator.validate(String.valueOf(permitType), "N")) {
//					psmt.setInt(3, permitType);
//				}
//				if (POSValidator.validate(String.valueOf(PermitCatg), "N")) {
//					psmt.setInt(4, PermitCatg);
//				}
//				if (POSValidator.validate(flag, "A")) {
//					psmt.setString(5, flag);
//				}
//				psmt.executeUpdate();
//				psmt.close();
//				rs.close();
//				strSQL = "SELECT prefix_year FROM vsm_permit_no";
//				psmt = tmgr.prepareStatement(strSQL);
//				rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//					if (Year != rs.getInt("prefix_year")) {
//						strSQL = "UPDATE vsm_permit_no SET PREFIX_YEAR = ?, SEQUENCE_NO = 0";
//						psmt = tmgr.prepareStatement(strSQL);
//						if (POSValidator.validate(String.valueOf(Year), "N")) {
//							psmt.setInt(1, Year);
//						}
//						psmt.executeUpdate();
//
//						strSQL = "UPDATE vsm_permit_no SET SEQUENCE_NO = 1 WHERE state_cd = ? AND off_cd = ? AND permit_type = ? AND permit_catg = ? AND flag = ? ";
//						psmt = tmgr.prepareStatement(strSQL);
//						if (POSValidator.validate(stateCd, "A")) {
//							psmt.setString(1, stateCd);
//						}
//						if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//							psmt.setInt(2, off_cd);
//						}
//						if (POSValidator.validate(String.valueOf(permitType), "N")) {
//							psmt.setInt(3, permitType);
//						}
//						if (POSValidator.validate(String.valueOf(PermitCatg), "N")) {
//							psmt.setInt(4, PermitCatg);
//						}
//						if (POSValidator.validate(flag, "A")) {
//							psmt.setString(5, flag);
//						}
//						psmt.executeUpdate();
//					}
//				} else {
//					isPmtNoFound = false;
//				}
//				rs.close();
//
//				strSQL = "SELECT PREFIX, PREFIX_YEAR, SEQUENCE_NO FROM vsm_permit_no WHERE state_cd = ? AND off_cd = ? AND permit_type = ? AND permit_catg = ? AND flag = ? ";
//				psmt = tmgr.prepareStatement(strSQL);
//				if (POSValidator.validate(stateCd, "A")) {
//					psmt.setString(1, stateCd);
//				}
//				if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//					psmt.setInt(2, off_cd);
//				}
//				if (POSValidator.validate(String.valueOf(permitType), "N")) {
//					psmt.setInt(3, permitType);
//				}
//				if (POSValidator.validate(String.valueOf(PermitCatg), "N")) {
//					psmt.setInt(4, PermitCatg);
//				}
//				if (POSValidator.validate(flag, "A")) {
//					psmt.setString(5, flag);
//				}
//				rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//					tempSeries = rs.getString("PREFIX").trim() + "/" + rs.getInt("PREFIX_YEAR") + "/"
//							+ rs.getLong("SEQUENCE_NO");
//				} else {
//					isPmtNoFound = false;
//				}
//
//				pmtNo = stateCd + "/" + off_cd + "/" + tempSeries;
//			} else {
//				strSQL = "INSERT INTO vsm_permit_no(\n"
//						+ "            state_cd, off_cd, permit_type, permit_catg, flag, prefix, prefix_year, \n"
//						+ "            sequence_no)\n"
//						+ "    SELECT  state_cd, ?, permit_type, permit_catg, flag, prefix, prefix_year, \n"
//						+ "            ?\n"
//						+ "    FROM vsm_permit_no WHERE state_cd = ? AND permit_type = ? AND permit_catg = ? AND flag = ? limit 1";
//				psmt.close();
//				psmt = tmgr.prepareStatement(strSQL);
//				if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//					psmt.setInt(1, off_cd);
//				}
//				psmt.setInt(2, 0);
//				if (POSValidator.validate(stateCd, "A")) {
//					psmt.setString(3, stateCd);
//				}
//				if (POSValidator.validate(String.valueOf(permitType), "N")) {
//					psmt.setInt(4, permitType);
//				}
//				if (POSValidator.validate(String.valueOf(PermitCatg), "N")) {
//					psmt.setInt(5, PermitCatg);
//				}
//				if (POSValidator.validate(flag, "A")) {
//					psmt.setString(6, flag);
//				}
//				int value = psmt.executeUpdate();
//				if (value == 0) {
//					pmtNo = null;
//				} else {
//					pmtNo = getUniquePermitNo(tmgr, stateCd, off_cd, permitType, PermitCatg, flag);
//				}
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (psmt != null) {
//					psmt.close();
//				}
//				rs = null;
//				psmt = null;
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//				throw new VahanException(e.getMessage());
//			}
//		}
//		return pmtNo;
//	}
//
//	public String getRegnNoWithSpace(String authRegnNo) {
//		String np_regn_no = "";
//		int regn_no_length = authRegnNo.length();
//		StringBuilder s = new StringBuilder();
//		s.append(authRegnNo);
//		String digiPart = s.toString().substring(s.length() - 4);
//		String seriPart = s.toString().substring(0, s.length() - 4);
//		switch (regn_no_length) {
//		case 10:
//			np_regn_no = authRegnNo;
//			break;
//		case 9:
//			np_regn_no = seriPart + " " + digiPart;
//			break;
//		case 8:
//			np_regn_no = seriPart + " " + " " + digiPart;
//			break;
//		case 7:
//			np_regn_no = seriPart + " " + " " + " " + digiPart;
//			break;
//		case 6:
//			np_regn_no = seriPart + " " + " " + " " + " " + digiPart;
//			break;
//		}
//		return np_regn_no;
//	}
//
//	public static PermitHomeAuthDobj getPermitDetailsFromNp(String regn_no) throws VahanException {
//		PermitHomeAuthDobj dobj_auth = null;
//		PreparedStatement pstmt = null;
//		TransactionManagerInterface tmgr = null;
//		String sql = "";
//		try {
//			sql = "select * from getnationalpermitinfo (?)";
//			tmgr = new TransactionManagerReadOnly("getPermitDetailsFromNp");
//			pstmt = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				pstmt.setString(1, regn_no);
//			}
//			RowSet rowSet = tmgr.fetchDetachedRowSet();
//			if (rowSet.next()) {
//				dobj_auth = new PermitHomeAuthDobj();
//				dobj_auth.setRegnNo(rowSet.getString("regn_no"));
//				dobj_auth.setAuthFrom(rowSet.getDate("val_fr"));
//				dobj_auth.setAuthUpto(rowSet.getDate("val_to"));
//				dobj_auth.setAuthNo(rowSet.getString("pmt_no"));
//			}
//		} catch (SQLException e) {
//			throw new VahanException("There is Some Problem Fatching National Permit Data");
//		} finally {
//			try {
//				tmgr.release();
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return dobj_auth;
//	}
//
//	
//
//	public static String getPermitdetails(String regnNo) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		String flag = "Vehicle does not have permit,Can not Proceed Online.";
//		try {
//			tmgr = new TransactionManagerReadOnly("getPermitdetailsFromRegnNo");
//			String sql = " SELECT  appl_no, pmt_no,pmt_type, regn_no " + " FROM " + TableList.VT_PERMIT + " a"
//					+ " where regn_no=?";
//			String sqlTemp = " SELECT  appl_no, pmt_no,pmt_type, regn_no  FROM " + TableList.VT_TEMP_PERMIT + " a"
//					+ " where regn_no=?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//			
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				flag = "";
//				if (TableConstants.All_India_Tourist_Permit.equalsIgnoreCase(rs.getString("pmt_type"))) {
//					flag = "This vehicle already has All India Tourist Permit.";
//				}
//			} else {
//				ps = tmgr.prepareStatement(sqlTemp);
//				if (POSValidator.validate(regnNo, "ANS")) {
//					ps.setString(1, regnNo);
//				}
//				
//				rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//					flag = "";
//					if (TableConstants.All_India_Tourist_Permit.equalsIgnoreCase(rs.getString("pmt_type"))) {
//						flag = "This vehicle already has All India Tourist Permit.";
//					}
//
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return flag;
//	}
//
//	public static CashRecieptSubListDobj getPermitDtls(CashRecieptSubListDobj cashRcptdobj, String appl_no) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//			String query = "SELECT * FROM " + TableList.VA_TEMP_PERMIT_APPL + " where appl_no = ?";
//			tmgr = new TransactionManagerReadOnly("Get getPermitDtls");
//			ps = tmgr.prepareStatement(query);
//			if (POSValidator.validate(appl_no, "AN")) {
//				ps.setString(1, appl_no);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				cashRcptdobj.setDateFrom(DateUtil.getStringYYYYMMDDHHmmsstoStringDDMMYYYY(rs.getString("valid_from")));
//				cashRcptdobj.setDateUpto(DateUtil.getStringYYYYMMDDHHmmsstoStringDDMMYYYY(rs.getString("valid_upto")));
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return cashRcptdobj;
//	}
//
//	public static String hasPermit(String regn_no, String state_cd) {
//		String msg = null;
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String whereIam = "ServerUtils.hasPermit()";
//		String validUpto = null;
//		String curDate = null;
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			
//			sql = "select case when ((valid_upto::date)<(current_date::date)) then false else true end as validity,valid_upto from "
//					+ TableList.VT_PERMIT
//					+ " WHERE regn_no=? and state_cd = ? and not (state_cd = 'BR' and pmt_type in (101,102,103,104))";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(2, state_cd);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				if (rs.getBoolean("validity")) {
//					validUpto = rs.getString("valid_upto");
//					validUpto = DateUtil.convertStringYYYYMMDDToDDMMYYYY(validUpto);
//					msg = "You can't apply for Fresh Permit. Permit is already valid upto " + validUpto;
//				} else {
//					msg = "Vehicle permit has expired. Please go through Renewal of Permit.";
//				}
//			} else {
//				// check data for surrender of Permit
//				String Query = "Select CASE WHEN a.trans_pur_cd=43 and (a.pmt_no<>c.pmt_no or (c.pmt_no is null and a.pmt_no is not null)) THEN null::text ELSE 'YOUR PERMIT IS SURRENDER. PLEASE RESTORE FIRST' end as msg from "
//						+ TableList.VT_PERMIT_TRANSACTION + " a\n" + " inner join " + TableList.VH_PERMIT
//						+ " b on a.regn_no = b.regn_no AND b.pmt_status = 'SUR'\n" + " left outer join "
//						+ TableList.VT_PERMIT + " c on c.regn_no = a.regn_no "
//						+ " where a.regn_no = ?  order by b.op_dt DESC limit 1";
//				ps = tmgr.prepareStatement(Query);
//				int i = 1;
//				if (POSValidator.validate(regn_no, "ANS")) {
//					ps.setString(i++, regn_no);
//				}
//				rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//					msg = rs.getString("msg");
//				} else {
//					sql = "SELECT  distinct a.action_cd,a.appl_no,a.op_dt,b.regn_no,ta.action_abbrv ,tp.descr as pur_descr  from "
//							+ TableList.VA_STATUS + " a" + " inner join " + TableList.VA_DETAILS
//							+ " b on b.appl_no = a.appl_no" + " inner join " + TableList.TM_ACTION
//							+ " ta on ta.action_cd = a.action_cd"
//							+ " left outer join tm_purpose_mast tp on tp.pur_cd = a.pur_cd "
//							+ " WHERE  b.regn_no <> 'NEW'and a.appl_no=b.appl_no and b.regn_no=? and a.state_cd =? AND a.pur_cd <> ?"
//							+ " ORDER BY a.op_dt desc";
//					ps = tmgr.prepareStatement(sql);
//					if (POSValidator.validate(regn_no, "ANS")) {
//						ps.setString(1, regn_no.toUpperCase());
//					}
//					if (POSValidator.validate(state_cd, "A")) {
//						ps.setString(2, state_cd.toUpperCase());
//					}
//					ps.setInt(3, TableConstants.VM_TRANSACTION_MAST_NEW_VEHICLE);
//					rs = tmgr.fetchDetachedRowSet_No_release();
//					if (rs.next()) {
//						msg = "Your previous Application No <font color=\"red\">" + rs.getString("appl_no")
//								+ "</font> pending in the stage of <font color=\"red\">" + rs.getString("action_abbrv")
//								+ "</font> against  the purpose <font color=\"red\"> " + rs.getString("pur_descr")
//								+ "</font>";
//					}
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return msg;
//
//	}
//
//	public static String validToRenew(String regn_no, String state_cd) {
//		String msg = null;
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String whereIam = "ServerUtils.validToRenew()";
//		String validUpto = null;
//		String curDate = null;
//		boolean valid = false;
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			sql = "select case when ((valid_upto::date)<(current_date::date)) then false else true end as validity,to_char(valid_upto,'DD-MON-YYYY')as valid_upto ,pmt_no from "
//					+ TableList.VT_PERMIT + " WHERE regn_no=? and state_cd = ?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(2, state_cd);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				if (false && !CommonPermitPrintImpl.takeRenewalOfPermit_After(state_cd, regn_no,
//						rs.getString("pmt_no"))) {
//					msg = "Your vehicle is not able to get renewal of permit because of validity of permit is just upto "
//							+ rs.getString("valid_upto");
//				}
//
//			} else {
//				
//				String Query = "Select CASE WHEN a.trans_pur_cd=43 and c.pmt_no is not null and c.pmt_no<>a.pmt_no THEN null::text ELSE 'YOUR PERMIT IS SURRENDER. PLEASE RESTORE FIRST' END as msg"
//						+ " from " + TableList.VT_PERMIT_TRANSACTION + "  a"
//						+ " inner join permit.VH_PERMIT b on a.regn_no = b.regn_no AND b.pmt_status = 'SUR'"
//						+ " left outer join permit.VT_PERMIT c on c.regn_no = a.regn_no"
//						+ " where a.regn_no = ?  order by b.op_dt DESC limit 1";
//				ps = tmgr.prepareStatement(Query);
//				int i = 1;
//				if (POSValidator.validate(regn_no, "ANS")) {
//					ps.setString(i++, regn_no);
//				}
//				rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//					msg = rs.getString("msg");
//				} else {
//
//					sql = "SELECT  distinct a.action_cd,a.appl_no,a.op_dt,b.regn_no,ta.action_abbrv from "
//							+ TableList.VA_STATUS + " a" + " inner join " + TableList.VA_DETAILS
//							+ " b on b.appl_no = a.appl_no" + " inner join " + TableList.TM_ACTION
//							+ " ta on ta.action_cd = a.action_cd"
//							+ " WHERE  b.regn_no <> 'NEW'and a.appl_no=b.appl_no and b.regn_no=? and a.state_cd =? AND a.pur_cd <> ?"
//							+ " ORDER BY a.op_dt desc";
//					ps = tmgr.prepareStatement(sql);
//					if (POSValidator.validate(regn_no, "ANS"))
//						ps.setString(1, regn_no.toUpperCase());
//					if (POSValidator.validate(state_cd, "A"))
//						ps.setString(2, state_cd.toUpperCase());
//					ps.setInt(3, TableConstants.VM_TRANSACTION_MAST_NEW_VEHICLE);
//					rs = tmgr.fetchDetachedRowSet_No_release();
//					if (rs.next()) {
//						msg = "Your previous Application No <font color=\"red\">" + rs.getString("appl_no")
//								+ "</font> pending in the stage of <font color=\"red\">" + rs.getString("action_abbrv")
//								+ "</font>";
//					} else {
//						msg = "This vehicle has no main permit";
//					}
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return msg;
//
//	}
//
//	/**
//	 * modified by Aftab @ 03-Mar-17
//	 *
//	 * @param regn_no
//	 * @param stateCd
//	 * @param flag
//	 * @return
//	 */
//	public static boolean isValidPermit(String regn_no, String stateCd, boolean flag) {
//		String sql = null;
//		String sql2 = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		RowSet rs = null;
//		String whereIam = "ServerUtils.isValidPermit()";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			if (!flag) {
//				sql2 = "SELECT * FROM " + TableList.VM_PERMIT_STATE_CONFIGURATION
//						+ " where state_cd = ? and permanent_permit_valid = ?";
//				ps = tmgr.prepareStatement(sql2);
//				if (POSValidator.validate(stateCd, "A")) {
//					ps.setString(1, stateCd);
//				}
//				ps.setBoolean(2, true);
//				rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//					flag = true;
//				} else {
//					return true;
//				}
//			}
//			if (flag) {
//				sql = "SELECT regn_no FROM " + TableList.VT_PERMIT + " WHERE regn_no=?";
//				ps = tmgr.prepareStatement(sql);
//				if (POSValidator.validate(regn_no, "ANS")) {
//					ps.setString(1, regn_no);
//				}
//				rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//					return true;
//				} else {
//					sql = "SELECT regn_no FROM " + TableList.VT_TEMP_PERMIT + " WHERE regn_no=?";
//					ps = tmgr.prepareStatement(sql);
//					if (POSValidator.validate(regn_no, "ANS")) {
//						ps.setString(1, regn_no);
//					}
//					if (rs.next()) {
//						return true;
//					}
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return false;
//
//	}
//
//	// Added by Aftab @ 05-12-2016
//	// Gautam Sir
//	public static String isRegisteredUser(String regnNo) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String mobileNo = "";
//		String whereiam = "ServerUtil.isRegisteredUser";
//		String sqlRgstrdUsr = "SELECT * FROM " + TableList.VT_OWNER_IDENTIFICATION + " WHERE regn_no=? ";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlRgstrdUsr);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				pstm.setString(1, regnNo);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				mobileNo = rs.getString("mobile_no");
//				
//			}
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return mobileNo;
//	}
//
//	public static byte[] ImageToByte(InputStream file) throws IOException {
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		byte[] buf = new byte[1024];
//		for (int readNum; (readNum = file.read(buf)) != -1;) {
//			bos.write(buf, 0, readNum);
//		}
//		byte[] bytes = bos.toByteArray();
//		return bytes;
//	}
//
//	public static boolean isValidTaxClass(int vh_class, String state_cd) {
//		if (state_cd.equalsIgnoreCase("UP")) {
//			int[] vh_cls_tax = { 8, 9, 10, 11, 12, 17, 20, 21, 22, 24, 26, 29 };
//			for (int vh_cls : vh_cls_tax) {
//				if (vh_cls == vh_class) {
//					return true;
//				}
//			}
//		}
//		if (state_cd.equalsIgnoreCase("JH")) {
//			int[] vh_cls_tax = { 1, 2, 3, 4, 5, 6, 13, 7, 22, 21, 20, 17, 14, 12, 11, 10, 9, 8, 28 };
//			for (int vh_cls : vh_cls_tax) {
//				if (vh_cls == vh_class) {
//					return true;
//				}
//			}
//		}
//
//		if (state_cd.equalsIgnoreCase("WB")) {
//			int[] vh_cls_tax = { 7, 59, 79, 84, 87, 82, 90, 54, 63, 52, 58, 88, 89, 23, 56, 70, 71 };
//			for (int vh_cls : vh_cls_tax) {
//				if (vh_cls == vh_class) {
//					return true;
//				}
//			}
//		}
//
//		if (state_cd.equalsIgnoreCase("OR")) {
//
//			return true;
//
//		}
//		return false;
//	}
//
//	// vivek @ 16-Dec-2016
//	public static boolean isSbiEpay(String state_cd) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtil.isSbiEpay";
//		
//		String sqlRgstrdUsr = "SELECT istesting FROM " + TableList.SBI_ePAY_ONLINE
//				+ " WHERE state_cd=? and istesting=true";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlRgstrdUsr);
//			if (POSValidator.validate(state_cd, "A")) {
//				pstm.setString(1, state_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return false;
//	}
//
//	// testingAmout
//	public static int testingAmout(String state_cd) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		int total_amout = 0;
//		String whereiam = "ServerUtil.testingAmout";
//		
//		String sqlRgstrdUsr = "SELECT  pgiamount from onlineschema.vm_online_configuration  where state_cd=?";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlRgstrdUsr);
//			pstm.setString(1, state_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				total_amout = rs.getInt("pgiamount");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return total_amout;
//	}
//
//	public static int getYear1MinusYear2(Date dateStr1, Date dateStr2) throws DateUtilsException {
//
//		int yearDiff = DateUtils.getDate1MinusDate2_Months(dateStr1, dateStr2);
//		double vchAgeWithDecimal;
//		int onlyIntegerPart;
//
//		vchAgeWithDecimal = yearDiff / 12.0;// 3.6
//		onlyIntegerPart = (int) vchAgeWithDecimal;
//		if ((vchAgeWithDecimal - onlyIntegerPart) > 0) {// 3.6-3=.61
//			yearDiff = onlyIntegerPart + 1;
//		} else {
//			yearDiff = onlyIntegerPart;
//		}
//
//		return yearDiff;
//	}
//
//	public static String getLatestTaxMode(OwnerDetailsDobj ownerdobj) {
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String msg = null;
//		String whereIam = "ServerUtils.getLatestTaxMode()";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			sql = "select tax_mode from vt_tax where regn_no=? and state_cd=? and off_cd=? and tax_mode  in ('O','L') order by rcpt_dt desc limit 1";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(ownerdobj.getRegn_no(), "ANS")) {
//				ps.setString(1, ownerdobj.getRegn_no());
//			}
//			if (POSValidator.validate(ownerdobj.getState_cd(), "A")) {
//				ps.setString(2, ownerdobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(ownerdobj.getOff_cd()), "N")) {
//				ps.setInt(3, ownerdobj.getOff_cd());
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				
//				return msg;
//			} else {
//				if (ownerdobj.getSeat_cap() > 5 && ownerdobj.getSeat_cap() <= 10 && ownerdobj.getVh_class() == 7
//						&& ownerdobj.getState_cd().equalsIgnoreCase("JH")) {
//					if ((CommonUtils.isNullOrBlank(ownerdobj.getSale_amt() + TableConstants.BLANK_STRING)
//							|| ownerdobj.getSale_amt() == 0)) {
//						msg = "NOTOK";
//					} else {
//						return msg = "OK";
//					}
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return msg;
//	}
//
//	// Aftab @ 20-Dec-2016
//	public static boolean validateUserIP(String clientIpAddress, String state_cd, int off_cd) {
//		boolean response = false;
//		PreparedStatement pstmt = null;
//		TransactionManagerInterface tmgr = null;
//		RowSet rs = null;
//		try {
//			tmgr = new TransactionManagerReadOnly(" validateUser ");
//			String query = "Select * from smartcard.vm_wsdl_ip " + " where '" + clientIpAddress + "' "
//					+ " in (test_server_ip4, test_server_ip6, stage_server_ip4,stage_server_ip6 ,prod_server_ip4,prod_server_ip6) "
//					+ " and state_cd = ? and off_cd = ?";
//			pstmt = tmgr.prepareStatement(query);
//			if (POSValidator.validate(state_cd, "A")) {
//				pstmt.setString(1, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				pstmt.setInt(2, off_cd);
//			}
//			rs = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//			if (rs.next()) {
//				response = true;
//			} else {
//				response = false;
//			}
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//		return response;
//	}
//
//	public static int getTaxClassWB(int vh_class, String state_cd) {
//		int[] vh_cls_tax = { 7, 59, 79, 84, 87, 82, 90, 54, 63, 52, 58, 88, 89, 23, 56, 70, 71 };
//		for (int vh_cls : vh_cls_tax) {
//			if (vh_cls == vh_class) {
//				return vh_cls;
//			}
//		}
//		return 0;
//	}
//
//	public static String isTaxDataApprovedForWB(String regnNo, String stateCd) {
//		String msg = null;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtil.isTaxDataApprovedForWB";
//		String sqlApprvdVeh = "SELECT * FROM " + TableList.VHA_DATA_VERIFY_REQUEST + " WHERE regn_no=? ";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlApprvdVeh);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				pstm.setString(1, regnNo);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				msg = "APPROVED";
//				return msg;
//			} else {
//				String sqlAppliedVeh = "SELECT * FROM " + TableList.VA_DATA_VERIFY_REQUEST + " WHERE regn_no=? ";
//				pstm = tmgr.prepareStatement(sqlAppliedVeh);
//				if (POSValidator.validate(regnNo, "ANS")) {
//					pstm.setString(1, regnNo);
//				}
//				rs = tmgr.fetchDetachedRowSet();
//				if (rs.next()) {
//					msg = "REQUEST";
//					return msg;
//				}
//			}
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return msg;
//	}
//
//	public static void insertIntoVaDataVerifyReq(String regnNo, String mobileNo, String stateCd, int offCd)
//			throws VahanException {
//		TransactionManager tmgr = null;
//		PreparedStatement ps = null;
//		String vaDataVerfyReqsql = null;
//		try {
//			tmgr = new TransactionManager("ServerUtil.insertIntoVaDataVerifyReq");
//			vaDataVerfyReqsql = "INSERT INTO " + TableList.VA_DATA_VERIFY_REQUEST
//					+ " (state_cd, off_cd, regn_no, mobile_no, request_dt) "
//					+ " VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
//			ps = tmgr.prepareStatement(vaDataVerfyReqsql);
//			if (POSValidator.validate(stateCd, "A")) {
//				ps.setString(1, stateCd);
//			}
//			if (POSValidator.validate(String.valueOf(offCd), "N")) {
//				ps.setInt(2, offCd);
//			}
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(3, regnNo);
//			}
//			if (!CommonUtils.isNullOrBlank(mobileNo) && POSValidator.validate(mobileNo, "N")) {
//				ps.setLong(4, Long.parseLong(mobileNo));
//			} else {
//				ps.setLong(4, java.sql.Types.NUMERIC);
//			}
//
//			ps.executeUpdate();
//			tmgr.commit();
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//	}
//
//	public static String returnToHome() {
//		if (CommonUtils.isNullOrBlank(Util.getEmail_id())) {
//			return TableConstants.BACKTOHOME;
//		} else {
//			return TableConstants.BACKTOLOGGEDINHOME;
//		}
//	}
//
//	public static boolean isValidPaymentTime() {
//		String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
//
//		String[] time = currentTime.split(":");
//		int hr = Integer.parseInt(time[0]);
//		int min = Integer.parseInt(time[1]);
//
//		if ((hr == 8 && min > 29) || (hr > 8 && hr < 18)) {
//			return true;
//		}
//		return false;
//	}
//
//	public static boolean isValidFeesFlow(OwnerDetailsDobj ownerdobj, int pur_Cd) {
//		boolean flgFeesFlow = false;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtil.isValidFeesFlow";
//		String sql = "select flow_srno,action_cd,condition_formula from tm_purpose_action_flow "
//				+ " where pur_cd=? and state_cd=? and action_cd=99992";
//
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sql);
//
//			if (POSValidator.validate(String.valueOf(pur_Cd), "N")) {
//				pstm.setInt(1, pur_Cd);
//			}
//			if (POSValidator.validate(ownerdobj.getState_cd(), "A")) {
//				pstm.setString(2, ownerdobj.getState_cd());
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				VehicleParameters parameters = FormulaUtils.fillVehicleParametersFromDobj(ownerdobj);
//				if (FormulaUtils.isCondition(
//						FormulaUtils.replaceTagValues(rs.getString("condition_formula"), parameters),
//						("Server Util (tm pur action flow) for state/pur/action : " + ownerdobj.getState_cd()
//								+ pur_Cd))) {
//					flgFeesFlow = true;
//				}
//			}
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return flgFeesFlow;
//	}
//
//	
//	/**
//	 * * Use this method for setting min date or max date from current date.
//	 * Positive value for addition and negative value for subtraction.
//	 *
//	 * @param date         Date
//	 * @param year         Integer
//	 * @param month        Integer
//	 * @param day_of_month Integer.
//	 * @return Date Added by Aftab @ 01-Mar-17
//	 */
//	public static Date dateRange(Date date, int year, int month, int day_of_month) {
//		java.util.Calendar cal = new GregorianCalendar();
//		cal.setTime(date);
//		cal.add(java.util.Calendar.YEAR, year);
//		cal.add(java.util.Calendar.MONTH, month);
//		cal.add(java.util.Calendar.DAY_OF_MONTH, day_of_month);
//		return cal.getTime();
//	}
//
//	
//
//	// checkSurrenderOfPermit
//	public static String permitSurrMsg(String regn_no) throws VahanException {
//		PreparedStatement ps;
//		TransactionManagerInterface tmgr = null;
//		;
//		String msg = "";
//		try {
//			tmgr = new TransactionManagerReadOnly("permitSurrMsg");
//			
//			String Query = "Select CASE WHEN a.trans_pur_cd=43 and c.pmt_no is not null and c.pmt_no<>a.pmt_no THEN null::text ELSE 'YOUR PERMIT IS SURRENDER. PLEASE RESTORE FIRST' END as msg"
//					+ " from " + TableList.VT_PERMIT_TRANSACTION + " a"
//					+ " inner join permit.VH_PERMIT b on a.regn_no = b.regn_no AND b.pmt_status = 'SUR'"
//					+ " left outer join permit.VT_PERMIT c on c.regn_no = a.regn_no"
//					+ " where a.regn_no = ?  order by b.op_dt DESC limit 1";
//			ps = tmgr.prepareStatement(Query);
//			int i = 1;
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				msg = rs.getString("msg");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return msg;
//	}
//
//	public static PuccDobj getPuccDetails(String regnNo, String state_cd, int off_cd) {
//		PuccDobj dobj = null;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		String insQuery = "Select * from " + TableList.VT_PUCC + " where regn_no = ? and state_cd = ? and off_cd = ?";
//		try {
//			tmgr = new TransactionManagerReadOnly("getPuccDetails");
//			ps = tmgr.prepareStatement(insQuery);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(2, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				ps.setInt(3, off_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				dobj = new PuccDobj();
//				dobj.setPuccFrom(rs.getDate("pucc_from"));
//				dobj.setPuccUpto(rs.getDate("pucc_upto"));
//				dobj.setPuccCentreno(rs.getString("pucc_centreno"));
//				dobj.setPuccNo(rs.getString("pucc_no"));
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return dobj;
//	}
//
//	public static void insertPuccDeatils(String transID, String regn_no, PuccDobj Dobj, TransactionManager tmgr)
//			throws VahanException {
//
//		PreparedStatement ps = null;
//		String sql = null;
//		sql = "INSERT INTO vahan4.va_pucc(\n"
//				+ "            appl_no, regn_no, pucc_from, pucc_upto, pucc_centreno, pucc_no, \n"
//				+ "            op_dt)\n" + "    VALUES (?, ?, ?, ?, ?, ?, \n" + "            current_timestamp);";
//		try {
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(transID, "AN")) {
//				ps.setString(1, transID);
//			}
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(2, regn_no);
//			}
//			if (Dobj.getPuccFrom() != null
//					&& POSValidator.validate(DateUtil.getStringTimeStampFormatted(Dobj.getPuccFrom()), "DATE")) {
//				ps.setDate(3, new java.sql.Date(Dobj.getPuccFrom().getTime()));
//			} else {
//				ps.setNull(3, java.sql.Types.DATE);
//
//			}
//			if (Dobj.getPuccUpto() != null
//					&& POSValidator.validate(DateUtil.getStringTimeStampFormatted(Dobj.getPuccUpto()), "DATE")) {
//				ps.setDate(4, new java.sql.Date(Dobj.getPuccUpto().getTime()));
//			} else {
//				ps.setNull(4, java.sql.Types.DATE);
//			}
//
//			if (POSValidator.validate(Dobj.getPuccCentreno(), "AN")) {
//				ps.setString(5, Dobj.getPuccCentreno());
//			}
//			if (POSValidator.validate(Dobj.getPuccNo(), "AN")) {
//				ps.setString(6, Dobj.getPuccCentreno());
//			}
//
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		}
//	}
//
//	public static boolean isValidForAITP(String regnNo, String stateCd) throws DateUtilsException {
//		boolean flag = false;
//		String curDate = DateUtil.getCurrentDateDDMMYYY();
//		OwnerDetailsDobj ownerDobj = ServerUtil.getRegnBasedOwnerDetails(regnNo, stateCd);
//		String regDate = ownerDobj.getRegn_dt();
//		regDate = DateUtil.convertStringYYYYMMDDToDDMMYYYY(regDate.substring(0, 10));
//		int monthDiff = DateUtils.getDate1MinusDate2_Months(regDate, curDate);
//
//		if (monthDiff <= 36) {
//			flag = true;
//		}
//		return flag;
//	}
//
//	public static boolean getAddInsuranceDetail(String state_cd) {
//
//		PuccDobj dobj = null;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		boolean flag = false;
//		String insQuery = "select * from onlineschema.vm_online_configuration  where state_cd=? and isinsurance='true' ";
//		try {
//			tmgr = new TransactionManagerReadOnly("getAddInsuranceDetail");
//			ps = tmgr.prepareStatement(insQuery);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//
//			// ps.setInt(2, pur_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				return true;
//			}
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return flag;
//
//	}
//
//	// getOnlinevalidation
//	public static boolean getOnlinevalidation(String state_cd, int pur_cd) {
//		PuccDobj dobj = null;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		boolean flag = false;
//		String insQuery = "select ismobileregistered from onlineschema.vm_online_configuration  where state_cd=? ";
//		try {
//			tmgr = new TransactionManagerReadOnly("getOnlinevalidation");
//			ps = tmgr.prepareStatement(insQuery);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			while (rs.next()) {
//
//				String purcdlist = rs.getString("ismobileregistered");
//				if (state_cd.equalsIgnoreCase("OR")) {
//					String chkpurCd[] = purcdlist.split(",");
//					for (int i = 0; i < chkpurCd.length; i++) {
//						if (chkpurCd[i].equalsIgnoreCase(String.valueOf(pur_cd))) {
//							return false;
//						}
//
//					}
//					return true;
//				}
//			}
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return flag;
//	}
//
//	public static String getStateCd(String regn_no) {
//
//		PreparedStatement ps;
//		RowSet rs;
//		String state_cd = "";
//		TransactionManagerInterface iTmgr = null;
//		String insQuery = "select state_cd from vt_owner where regn_no=?";
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				iTmgr = new TransactionManagerReadOnly("EApplicationImpl.isValidRegistration()");
//
//			} else {
//				iTmgr = new TransactionManager("EApplicationImpl.isValidRegistration()");
//			}
//			
//			ps = iTmgr.prepareStatement(insQuery);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//			rs = iTmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//
//				state_cd = rs.getString("state_cd");
//			}
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return state_cd;
//	}
//
//	public static RcCancelDobj getSurrenderDetails(String state_cd, String regn_no) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		RcCancelDobj dobj = new RcCancelDobj();
//		String insQuery = "select * from onlineschema.va_rc_surrender_appl  where state_cd=? and regn_no=? ";
//		try {
//			tmgr = new TransactionManagerReadOnly("getSurrenderDetails");
//			ps = tmgr.prepareStatement(insQuery);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(2, regn_no);
//			}
//			
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				dobj.setRcNo(rs.getString("rc_sno"));
//				dobj.setRcStatus(rs.getString("rc"));
//				dobj.setPermitNo(rs.getString("permit_sno"));
//				dobj.setPermitStatus(rs.getString("permit"));
//				dobj.setFitnessCerNo(rs.getString("fc_sno"));
//				dobj.setFitnessCerStatus(rs.getString("fc"));
//				dobj.setTaxExampStatus(rs.getString("taxexem"));
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} catch (VahanException e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return dobj;
//
//	}
//
//	public static int getPgiAmout(String state_cd) {
//
//		PuccDobj dobj = null;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		int pgiAmout = 0;
//		boolean flag = false;
//		String insQuery = "select pgiamount from " + TableList.VM_ONLINE_CONFIGURATION + " where state_cd=?";
//		try {
//			tmgr = new TransactionManagerReadOnly("getpgiAmout");
//			ps = tmgr.prepareStatement(insQuery);
//			ps.setString(1, state_cd);
//			// ps.setInt(2, pur_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				pgiAmout = rs.getInt("pgiamount");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return pgiAmout;
//
//	}
//
//	public static boolean isPgiTestPage(String state_cd) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		String insQuery = "select ispgitestpage from " + TableList.VM_ONLINE_CONFIGURATION + " where state_cd=?";
//		try {
//			tmgr = new TransactionManagerReadOnly("getpgiAmout");
//			ps = tmgr.prepareStatement(insQuery);
//			ps.setString(1, state_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				return rs.getBoolean("ispgitestpage");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return false;
//	}
//
//	public static void update_vaStatus_VaDetails(TransactionManager tmgr, Status_dobj status_dobj, String new_appno)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		try {
//			sql = " UPDATE " + TableList.VA_STATUS_APPL
//					+ " SET state_cd=?, off_cd=?, appl_no=?, pur_cd=?, flow_slno=?, "
//					+ " file_movement_slno=?,action_cd=?, emp_cd=?, op_dt=current_timestamp, moved_from_online=? where appl_no=? ";
//
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(status_dobj.getState_cd(), "A")) {
//				ps.setString(1, status_dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(status_dobj.getOff_cd()), "N")) {
//				ps.setInt(2, status_dobj.getOff_cd());
//			}
//			if (POSValidator.validate(new_appno, "AN")) {
//				ps.setString(3, new_appno);
//			}
//			if (POSValidator.validate(String.valueOf(status_dobj.getPur_cd()), "N")) {
//				ps.setInt(4, status_dobj.getPur_cd());
//			}
//			if (POSValidator.validate(String.valueOf(status_dobj.getFlow_slno()), "N")) {
//				ps.setInt(5, status_dobj.getFlow_slno());
//			}
//			if (POSValidator.validate(String.valueOf(status_dobj.getFile_movement_slno()), "N")) {
//				ps.setInt(6, status_dobj.getFile_movement_slno());
//			}
//			if (POSValidator.validate(String.valueOf(status_dobj.getAction_cd()), "N")) {
//				ps.setInt(7, status_dobj.getAction_cd());
//			}
//			if (status_dobj.getEmp_cd() != 0) {
//				ps.setLong(8, status_dobj.getEmp_cd());
//			} else {
//				ps.setLong(8, Long.parseLong(Util.getMobile_no()));
//			}
//			if (POSValidator.validate(status_dobj.getMoved_from_online(), "AN")) {
//				ps.setString(9, status_dobj.getMoved_from_online());
//			}
//			if (POSValidator.validate(status_dobj.getAppl_no(), "AN")) {
//				ps.setString(10, status_dobj.getAppl_no());
//			}
//			int i = ps.executeUpdate();
//			if (i > 0) {
//				sql = "UPDATE " + TableList.VA_DETAILS_APPL
//						+ " SET appl_no=?, pur_cd=?, appl_dt=?, regn_no=?, user_id=?,"
//						+ " user_type=?, state_cd=?, off_cd=? , entry_status=?,  entry_ip=? where appl_no=? ";
//
//				ps = tmgr.prepareStatement(sql);
//				if (POSValidator.validate(new_appno, "AN")) {
//					ps.setString(1, new_appno);
//				} // actual
//					// application
//					// no
//				if (POSValidator.validate(String.valueOf(status_dobj.getPur_cd()), "N")) {
//					ps.setInt(2, status_dobj.getPur_cd());
//				}
//				ps.setTimestamp(3, ServerUtil.getSystemDateInPostgres());
//				if (POSValidator.validate(status_dobj.getRegn_no(), "ANS")) {
//					ps.setString(4, status_dobj.getRegn_no());
//				}
//				if (POSValidator.validate(status_dobj.getMobileNo(), "N")) {
//					ps.setString(5, status_dobj.getMobileNo());
//				}
//				ps.setString(6, ""); // blank user type.
//				if (POSValidator.validate(status_dobj.getState_cd(), "A")) {
//					ps.setString(7, status_dobj.getState_cd());
//				}
//				if (POSValidator.validate(String.valueOf(status_dobj.getOff_cd()), "N")) {
//					ps.setInt(8, status_dobj.getOff_cd());
//				}
//				if (!CommonUtils.isNullOrBlank(status_dobj.getEntry_status())) {
//					ps.setString(9, status_dobj.getEntry_status());
//				}
//				if (!CommonUtils.isNullOrBlank(Util.getClientIpAdress())) {
//					ps.setString(10, Util.getClientIpAdress());
//				}
//				if (POSValidator.validate(status_dobj.getAppl_no(), "AN")) {
//					ps.setString(11, status_dobj.getAppl_no());
//				}
//				i = ps.executeUpdate();
//			}
//			if (i <= 0) {
//				throw new VahanException("Something went wrong while updating status/details for registration no. = "
//						+ status_dobj.getRegn_no());
//			}
//			status_dobj.setAppl_no(new_appno);
//		} catch (SQLException e) {
//			throw new VahanException(
//					"Status has not been updated against registration no = " + status_dobj.getRegn_no());
//		}
//
//	}
//
//	public static long getUniqueInstrumentNo(TransactionManager tmgr) throws VahanException {
//
//		String instrumentNo = null;
//		String tempSeries = "";
//		long tempSequence = 0;
//		try {
//			java.util.Calendar cal = java.util.Calendar.getInstance();
//			String monthYear = String.valueOf(cal.get(java.util.Calendar.YEAR)).substring(2, 4)
//					+ String.format("%02d", cal.get(java.util.Calendar.MONTH) + 1);
//
//			String strSQL = "UPDATE vm_instrument_no SET SEQUENCE_NO = SEQUENCE_NO + 1";
//			PreparedStatement psmt = tmgr.prepareStatement(strSQL);
//			psmt.executeUpdate();
//
//			strSQL = "SELECT PREFIX FROM vm_instrument_no";
//			psmt = tmgr.prepareStatement(strSQL);
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//
//			if (rs.next()) {
//				if (!monthYear.equalsIgnoreCase(rs.getString("PREFIX").trim())) {
//					strSQL = "UPDATE vm_instrument_no SET PREFIX = ?, SEQUENCE_NO = 1";
//					psmt = tmgr.prepareStatement(strSQL);
//					if (POSValidator.validate(monthYear, "ANWS")) {
//						psmt.setString(1, monthYear);
//					}
//					
//					psmt.executeUpdate();
//				}
//			} else {
//				strSQL = "INSERT INTO vm_instrument_no VALUES (?, 1)";
//				psmt = tmgr.prepareStatement(strSQL);
//				if (POSValidator.validate(monthYear, "ANWS")) {
//					psmt.setString(1, monthYear);
//				}
//				
//				psmt.executeUpdate();
//			}
//			rs.close();
//
//			strSQL = "SELECT PREFIX, SEQUENCE_NO FROM vm_instrument_no";
//			psmt = tmgr.prepareStatement(strSQL);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				tempSeries = rs.getString("PREFIX").trim();
//				tempSequence = rs.getLong("SEQUENCE_NO");
//			}
//
//			String format = String.format("%%0%dd", 6);
//			String z = String.format(format, tempSequence);
//			instrumentNo = tempSeries.concat(z);
//
//		} catch (Exception e) {
//			throw new VahanException(e.getMessage());
//		}
//
//		return Long.parseLong(instrumentNo);
//	}
//
//	public static boolean isValidFee(String state_cd, int pur_cd) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		boolean flag = false;
//		String insQuery = "SELECT * from vm_feemast where state_cd=? and pur_cd=?";
//		try {
//			tmgr = new TransactionManagerReadOnly("isValidFee");
//			ps = tmgr.prepareStatement(insQuery);
//			ps.setString(1, state_cd);
//			ps.setInt(2, pur_cd);
//			rs = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//			if (rs.next()) {
//				if (rs.getLong("fees") != 0) {
//					flag = true;
//				} else {
//					insQuery = "SELECT * from vm_feemast_catg  where state_cd=? and pur_cd=?";
//					ps = tmgr.prepareStatement(insQuery);
//					ps.setString(1, state_cd);
//					ps.setInt(2, pur_cd);
//					if (rs.next()) {
//						if (rs.getLong("fees") != 0) {
//							flag = true;
//						} else {
//							flag = false;
//						}
//					}
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return flag;
//	}
//
//	public static boolean updatePolicyNumber(String regn_no, String policy_num, String state_cd) throws VahanException {
//		TransactionManager tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		boolean flag = false;
//
//		String whereClause = "";
//		if (!CommonUtils.isNullOrBlank_Negative(state_cd)) {
//			whereClause = " where state_cd=?";
//		}
//		if (!CommonUtils.isNullOrBlank(regn_no)) {
//			whereClause = " where regn_no=?";
//		}
//		String selectQ = " Select  * from " + TableList.VT_TEMP_INSURANCE + whereClause;
//		String insQuery = " update  " + TableList.VT_TEMP_INSURANCE
//				+ " set policy_no=? where  policy_no in (select policy_no from vt_insurance where policy_no=?)";
//		try {
//			tmgr = new TransactionManager("updatePolicyNumber");
//			ps = tmgr.prepareStatement(selectQ);
//			if (!CommonUtils.isNullOrBlank_Negative(state_cd)) {
//				ps.setString(1, state_cd);
//			}
//			if (!CommonUtils.isNullOrBlank(regn_no)) {
//				ps.setString(1, regn_no);
//			}
//			rs = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//			while (rs.next()) {
//				String regnNo = rs.getString("regn_no");
//				String policy = rs.getString("policy_no");
//				String newPolicyNo = "";
//				if (policy_num.length() > policy.length()) {
//					newPolicyNo = policy + policy_num;
//				} else {
//					newPolicyNo = policy.substring(0, policy.length() - policy_num.length()) + policy_num;
//				}
//
//				ps = tmgr.prepareStatement(insQuery);
//				if (!CommonUtils.isNullOrBlank(newPolicyNo)) {
//					ps.setString(1, newPolicyNo);
//				}
//				if (!CommonUtils.isNullOrBlank(policy)) {
//					ps.setString(2, policy);
//				}
//				ps.executeUpdate();
//
//			}
//			tmgr.commit();
//			flag = true;
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return flag;
//
//	}
//
//	public static boolean isValidInsurance(String regn_no) {
//
//		
//		PreparedStatement ps;
//		RowSet rs;
//		boolean ins_upto = false;
//		TransactionManagerInterface iTmgr = null;
//		String insQuery = "SELECT * from Vt_INSURANCE where regn_no=? and ins_upto>now()";
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				iTmgr = new TransactionManagerReadOnly("EApplicationImpl.isValidRegistration()");
//
//			} else {
//				iTmgr = new TransactionManager("EApplicationImpl.isValidRegistration()");
//			}
//			
//			ps = iTmgr.prepareStatement(insQuery);
//			if (POSValidator.validate(regn_no, "ANS"))
//				ps.setString(1, regn_no);
//			
//			rs = iTmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				ins_upto = true;
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return ins_upto;
//
//	}
//
//	// GET bOOKED aPPOINTMNET DTLS
//	public static Date getAppointmetDate(String applNO) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		Date apptDate = null;
//		String whereiam = "ServerUtil.getAppointmetRecord";
//		String sqlRgstrdUsr = "SELECT appointment_dt  FROM appointment.vt_appt_dtls where appl_no= ?";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlRgstrdUsr);
//			pstm.setString(1, applNO);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//
//				apptDate = (rs.getDate("appointment_dt"));
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return apptDate;
//	}
//	
//	public static void fileFlowForNewApplicationVerify(TransactionManager tmgr, Status_dobj status_dobj)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		try {
//			
//
//			sql = "update " + TableList.VA_STATUS_APPL + " set "
//					+ " flow_slno = ?, action_cd = ?, op_dt = current_timestamp " + " where appl_no = ? and pur_cd = ?";
//
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(status_dobj.getState_cd(), "A")) {
//				ps.setString(1, status_dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(status_dobj.getOff_cd()), "N")) {
//				ps.setInt(2, status_dobj.getOff_cd());
//			}
//			if (POSValidator.validate(status_dobj.getAppl_no(), "AN")) {
//				ps.setString(3, status_dobj.getAppl_no());
//			}
//			if (POSValidator.validate(String.valueOf(status_dobj.getPur_cd()), "N")) {
//				ps.setInt(4, status_dobj.getPur_cd());
//			}
//			if (status_dobj.getFlow_slno() != 0) {
//				ps.setInt(5, status_dobj.getFlow_slno());
//			}
//			if (status_dobj.getFile_movement_slno() != 0) {
//				ps.setInt(6, status_dobj.getFile_movement_slno());
//			}
//			if (status_dobj.getAction_cd() != 0) {
//				ps.setInt(7, status_dobj.getAction_cd());
//			}
//			ps.setLong(8, status_dobj.getEmp_cd());
//			
//			if (CommonUtils.isNullOrBlank(status_dobj.getMoved_from_online())) {
//				ps.setString(9, status_dobj.getMoved_from_online());
//			}
//			ps.executeUpdate();
//
//			
//
//			sql = "INSERT INTO " + TableList.VA_DETAILS_APPL
//					+ " ( appl_no,pur_cd,appl_dt,regn_no,user_id,user_type,state_cd,off_cd,entry_status,entry_ip)"
//					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(status_dobj.getAppl_no(), "AN")) {
//				ps.setString(1, status_dobj.getAppl_no()); // actual
//			} // application
//				// no
//			if (POSValidator.validate(String.valueOf(status_dobj.getPur_cd()), "N")) {
//				ps.setInt(2, status_dobj.getPur_cd());
//			}
//			ps.setTimestamp(3, ServerUtil.getSystemDateInPostgres());
//			if (POSValidator.validate(status_dobj.getRegn_no(), "ANS")) {
//				ps.setString(4, status_dobj.getRegn_no());
//			}
//			if (!CommonUtils.isNullOrBlank(status_dobj.getMobileNo())) {
//				ps.setString(5, status_dobj.getMobileNo());
//			}
//			
//			ps.setString(6, ""); // blank user type.
//			if (POSValidator.validate(status_dobj.getState_cd(), "A")) {
//				ps.setString(7, status_dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(status_dobj.getOff_cd()), "N")) {
//				ps.setInt(8, status_dobj.getOff_cd());
//			}
//			if (!CommonUtils.isNullOrBlank(status_dobj.getEntry_status())) {
//				ps.setString(9, status_dobj.getEntry_status());
//			}
//			if (!CommonUtils.isNullOrBlank(Util.getClientIpAdress())) {
//				ps.setString(10, Util.getClientIpAdress());
//			}
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new VahanException(e.getMessage());
//		}
//
//	}
//
//	public static void fileFlowForNewApplication(TransactionManager tmgr, Status_dobj status_dobj)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		try {
//			
//			sql = "insert into " + TableList.VA_STATUS_APPL
//					+ "(status,state_cd,off_cd,appl_no,pur_cd,flow_slno,file_movement_slno,action_cd,seat_cd,"
//					+ " cntr_id,office_remark,public_remark,file_movement_type,emp_cd,op_dt,moved_from_online) "
//					+ " values('N',?,?,?,?,?,?,?,'','','','','F',?,current_timestamp,?)";
//
//			ps = tmgr.prepareStatement(sql);
//			if (!CommonUtils.isNullOrBlank(status_dobj.getState_cd()))
//				ps.setString(1, status_dobj.getState_cd());
//			if (status_dobj.getOff_cd() != 0)
//				ps.setInt(2, status_dobj.getOff_cd());
//			if (!CommonUtils.isNullOrBlank(status_dobj.getAppl_no()))
//				ps.setString(3, status_dobj.getAppl_no());
//			if (status_dobj.getPur_cd() != 0)
//				ps.setInt(4, status_dobj.getPur_cd());
//			if (status_dobj.getFlow_slno() != 0)
//				ps.setInt(5, status_dobj.getFlow_slno());
//			ps.setInt(6, status_dobj.getFile_movement_slno());
//			if (status_dobj.getAction_cd() != 0)
//				ps.setInt(7, status_dobj.getAction_cd());
//			ps.setLong(8, status_dobj.getEmp_cd());
//			
//			if (!CommonUtils.isNullOrBlank(status_dobj.getMoved_from_online()))
//				ps.setString(9, status_dobj.getMoved_from_online());
//			int i = ps.executeUpdate();
//
//			
//
//			if (i > 0) {
//				sql = "INSERT INTO " + TableList.VA_DETAILS_APPL
//						+ " ( appl_no,pur_cd,appl_dt,regn_no,user_id,user_type,state_cd,off_cd,entry_status,entry_ip)"
//						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//				ps = tmgr.prepareStatement(sql);
//				if (!CommonUtils.isNullOrBlank(status_dobj.getAppl_no()))
//					ps.setString(1, status_dobj.getAppl_no()); // actual
//																// application
//				if (status_dobj.getPur_cd() != 0) // no
//					ps.setInt(2, status_dobj.getPur_cd());
//				ps.setTimestamp(3, ServerUtil.getSystemDateInPostgres());
//				if (!CommonUtils.isNullOrBlank(status_dobj.getRegn_no()))
//					ps.setString(4, status_dobj.getRegn_no());
//				if (!CommonUtils.isNullOrBlank(status_dobj.getMobileNo()))
//					ps.setString(5, status_dobj.getMobileNo());
//				
//				ps.setString(6, ""); // blank user type.
//				if (!CommonUtils.isNullOrBlank(status_dobj.getState_cd()))
//					ps.setString(7, status_dobj.getState_cd());
//				if (status_dobj.getOff_cd() != 0)
//					ps.setInt(8, status_dobj.getOff_cd());
//				ps.setString(9, status_dobj.getEntry_status());
//				if (!CommonUtils.isNullOrBlank(Util.getClientIpAdress()))
//					ps.setString(10, Util.getClientIpAdress());
//				i = ps.executeUpdate();
//			}
//			if (i <= 0) {
//				throw new VahanException("Something went wrong while submiting status.");
//			}
//		} catch (SQLException e) {
//			throw new VahanException(e.getMessage());
//		}
//
//	}
//
//	public static boolean getAppointmentStatus(String state_cd) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		boolean status = false;
//		String whereiam = "ServerUtil.getAppointmentStatus";
//		
//		String sqlRgstrdUsr = "SELECT  appt_bfr_payment from onlineschema.vm_online_configuration  where state_cd=?";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlRgstrdUsr);
//			pstm.setString(1, state_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				status = rs.getBoolean("appt_bfr_payment");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return status;
//	}
//
//	// saveForAppointment
//	// Byvinit20july
//	public static void saveApplVT_TEMP_APPL_TRANSACTION_TEMP(String transNo, String regnNo, int purCD, String state_cd,
//			int offCd) throws VahanException {
//
//		TransactionManager tmgr = null;
//		String sqlTempApplTrans = "INSERT INTO " + TableList.VT_APPOINTMENT_FLOW
//				+ " VALUES(?,?,?,?,?,CURRENT_TIMESTAMP,?)";
//		PreparedStatement ps = null;
//		try {
//			tmgr = new TransactionManager("saveApplVT_TEMP_APPL_TRANSACTION_TEMP");
//			ps = tmgr.prepareStatement(sqlTempApplTrans);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(offCd), "N")) {
//				ps.setInt(2, offCd);
//			}
//			if (POSValidator.validate(transNo, "AN")) {
//				ps.setString(3, transNo);
//			}
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(4, regnNo);
//			}
//			if (POSValidator.validate(String.valueOf(purCD), "N")) {
//				ps.setInt(5, purCD);
//			}
//			ps.setString(6, ApplicationConfig.APPL_BEFORE_PAYMENT);
//			LOGGER.info("sql serverutil:" + ps.toString());
//			ps.executeUpdate();
//			tmgr.commit();
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to save saveApplVT_TEMP_APPL_TRANSACTION_TEMP.");
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//			} catch (SQLException e) {
//				// se.printStackTrace();
//				LOGGER.error(e.getMessage());
//			}
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//	}
//
//	public static String getAlreadyBokedAppointmentData(String regn_no, String state_cd, int offCd)
//			throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String transId = "";
//
//		String whereiam = "ServerUtil.getAlreadyBokedAppointmentData";
//		String sqlRgstrdUsr = "SELECT app.appl_no, app.appointment_dt from  appointment.vt_appt_dtls app \n"
//				+ " inner join  " + TableList.VT_APPOINTMENT_FLOW
//				+ " tempapp on tempapp.transaction_no= app.appl_no and tempapp.pur_cd=app.service_id :: numeric and tempapp.state_cd=app.state_cd and \n"
//				+ "  tempapp.off_cd=app.off_cd "
//				+ " where app.regn_no=? and app.state_cd=? and app.off_cd=? and tempapp.appointment_status='BFP'";
//
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlRgstrdUsr);
//			pstm.setString(1, regn_no);
//			pstm.setString(2, state_cd);
//			pstm.setInt(3, offCd);
//			LOGGER.info("ServerUtil.getAlreadyBokedAppointmentData..." + pstm.toString());
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				transId = rs.getString("appl_no");
//			}
//
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return transId;
//
//	}
//
//	// getDataFromVT_app_flow
//	public static String getDataFromVT_app_flow(String regn_no, String state_cd, int offCd) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String transId = "";
//
//		String whereiam = "ServerUtil.getDataFromVT_app_flow";
//		String sqlRgstrdUsr = "SELECT transaction_no from onlineschema.vt_appointment_flow where transaction_no not in "
//				+ "(select appl_no from appointment.vt_appt_dtls where regn_no=? and state_cd=? and  off_cd=?) "
//				+ " and regn_no=? and  state_cd=? and  off_cd=? ";
//
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlRgstrdUsr);
//			pstm.setString(1, regn_no);
//			pstm.setString(2, state_cd);
//			pstm.setInt(3, offCd);
//			pstm.setString(4, regn_no);
//			pstm.setString(5, state_cd);
//			pstm.setInt(6, offCd);
//			LOGGER.info("ServerUtil.getDataFromVT_app_flow..." + pstm.toString());
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				transId = rs.getString("transaction_no");
//			}
//
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return transId;
//	}
//
//	public static void deleteFromVt_appt_dtls(String appl_no, String stateCd, int offCd) throws VahanException {
//		TransactionManager tmgr = null;
//		PreparedStatement pstm = null;
//		String whereiam = "ServerUtil.deleteFromVt_appt_dtls";
//		String sqlRgstrdUsr = "delete from appointment.vt_appt_dtls where appl_no=? and state_cd=? and  off_cd=?";
//		try {
//			tmgr = new TransactionManager(whereiam);
//			pstm = tmgr.prepareStatement(sqlRgstrdUsr);
//			pstm.setString(1, appl_no);
//			pstm.setString(2, stateCd);
//			pstm.setInt(3, offCd);
//			LOGGER.info("ServerUtil.deleteFromVt_appt_dtls..." + pstm.toString());
//			pstm.executeUpdate();
//
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//	}
//
//	public static String getLatestStateCD(String regnNo) throws SQLException, VahanException {
//		String state_cd = null;
//		Date dateClearUpto = null;
//		Date dateTaxUpto = null;
//		Date rcptDateTAX = null;
//		Date rcptDateCLEAR = null;
//		String state_cd_tax = null;
//		String state_cd_clear = null;
//		TransactionManagerInterface tmgr = null;
//		try {
//			String sql = "select rcpt_dt,tax.state_cd from " + TableList.VT_TAX
//					+ " tax left outer join  onlineschema.vt_taxpayer_record rc on tax.rcpt_no!=rc.status where tax.regn_no=? "
//					+ " and tax.tax_mode !='B' order by rcpt_dt desc";
//			tmgr = new TransactionManagerReadOnly("getLatestStateSc");
//			PreparedStatement ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				// String rcpt_dt = rs.getString("rcpt_dt");
//				Timestamp rcpt_date = rs.getTimestamp("rcpt_dt");
//				rcptDateTAX = rs.getDate("rcpt_dt");
//				state_cd_tax = rs.getString("state_cd");
//
//			}
//
//			sql = "select op_dt , state_cd from " + TableList.VT_TAX_CLEAR
//					+ " where regn_no=? order by op_dt desc limit 1";
//
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				Timestamp rcpt_date = rs.getTimestamp("op_dt");
//				rcptDateCLEAR = rs.getDate("op_dt");
//				state_cd_clear = rs.getString("state_cd");
//			}
//			if (rcptDateTAX == null) {
//				state_cd = state_cd_clear;
//			} else if (rcptDateCLEAR == null) {
//				state_cd = state_cd_tax;
//			} else {
//				if (rcptDateCLEAR != null && rcptDateTAX != null) {
//					if (DateUtils.parseDate(rcptDateCLEAR).compareTo(DateUtils.parseDate(rcptDateTAX)) == 0) {
//						throw new VahanException(
//								"Multiple Data found for this vehicle number,Please contact to the respective RTO office.");
//					}
//
//					if (DateUtils.isAfter(DateUtils.parseDate(rcptDateCLEAR), DateUtils.parseDate(rcptDateTAX))) {
//						state_cd = state_cd_clear;
//					} else {
//						state_cd = state_cd_tax;
//					}
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return state_cd;
//	}
//
//	public static int getActionCode(int flow_srno, int pur_cd, TransactionManager tmgr)
//			throws SQLException, VahanException {
//
//		String sql = "select action_cd from tm_purpose_action_flow where state_cd = ? and pur_cd  = ? and flow_srno = ?";
//		PreparedStatement ps = tmgr.prepareStatement(sql);
//		if (POSValidator.validate(Util.getUserStateCode(), "A")) {
//			ps.setString(1, Util.getUserStateCode());
//		}
//		if (POSValidator.validate(pur_cd + "", "N")) {
//			ps.setInt(2, pur_cd);
//		}
//		if (POSValidator.validate(flow_srno + "", "N")) {
//			ps.setInt(3, flow_srno);
//		}
//
//		RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//		if (rs.next()) {
//			return rs.getInt("action_cd");
//		} else {
//			throw new VahanException("Action not Found against given flow_srno");
//
//		}
//	}
//
//	public static boolean getMultipleStateAction(String state_cd) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		boolean status = false;
//		String insQuery = "select multiple_state_action from " + TableList.VM_ONLINE_CONFIGURATION
//				+ " where state_cd=?";
//		try {
//			tmgr = new TransactionManagerReadOnly("getpgiAmout");
//			ps = tmgr.prepareStatement(insQuery);
//			ps.setString(1, state_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				status = rs.getBoolean("multiple_state_action");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return status;
//
//	}
//
//	public static boolean getNocRetention(String state_cd) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		boolean status = false;
//		String insQuery = "select noc_ret from " + TableList.VM_ONLINE_CONFIGURATION + " where state_cd=?";
//		try {
//			tmgr = new TransactionManagerReadOnly("getNocRetention");
//			ps = tmgr.prepareStatement(insQuery);
//			ps.setString(1, state_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				status = rs.getBoolean("noc_ret");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return status;
//
//	}
//
//	public static boolean getNocRetentionData(String applNo) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		boolean status = false;
//		String insQuery = "SELECT *  FROM onlineschema.va_surrender_retention_appl where appl_no=?";
//		try {
//			tmgr = new TransactionManagerReadOnly("getNocRetentionData");
//			ps = tmgr.prepareStatement(insQuery);
//			ps.setString(1, applNo);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				status = true;
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return status;
//
//	}
//
//	public static List<SelectItem> getFitnessCenter(String state_cd) {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String sql = null;
//		List<SelectItem> centerList = new ArrayList();
//		// Map<Object, Object> officeCodeList = new LinkedHashMap<Object,
//		// Object>();
//
//		try {
//			tmgr = new TransactionManagerReadOnly("getFitnessCenter");
//			sql = "select off_cd,off_name,state_cd from " + TableList.TM_OFFICE
//					+ " where state_cd=? and off_type_cd = 5";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd.toUpperCase(), "A")) {
//				ps.setString(1, state_cd.toUpperCase());
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//
//			while (rs.next())// found
//			{
//				centerList.add(new SelectItem(rs.getInt("off_cd"), rs.getString("off_name")));
//				// officeCodeList.put(rs.getInt("off_cd"),
//				// rs.getString("off_name"));
//			}
//
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return centerList;
//	}
//
//	public static String getAutoFeeStatus(String state_cd, int fitness_office) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		String status = "";
//		String insQuery = "SELECT  automatic_fitness_formula "
//				+ " FROM vm_smart_card_hsrp where off_cd=? and state_cd=?";
//		try {
//			tmgr = new TransactionManagerReadOnly("getAutoFeeStatus");
//			ps = tmgr.prepareStatement(insQuery);
//			ps.setInt(1, fitness_office);
//			ps.setString(2, state_cd);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				status = rs.getString("automatic_fitness_formula");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} catch (VahanException e) {
//			LOGGER.error(e.getMessage());
//			throw e;
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return status;
//
//	}
//
//	public static int getFitnessCentreCode(String transaction_no, String state_cd, int off_cd) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		int code = 0;
//		String insQuery = "SELECT  fitness_center_cd FROM onlineschema.vt_book_fitness_center where appl_no=? and state_cd=? and off_cd=?";
//		try {
//			tmgr = new TransactionManagerReadOnly("getFitnessCentreCode");
//			ps = tmgr.prepareStatement(insQuery);
//			ps.setString(1, transaction_no);
//			ps.setString(2, state_cd);
//			ps.setInt(3, off_cd);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				code = rs.getInt("fitness_center_cd");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} catch (VahanException e) {
//			throw e;
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return code;
//
//	}
//
//	public static String getOffDescr(String state_cd, int off_cd) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		String dscr = "";
//		String insQuery = "SELECT  off_name FROM " + TableList.TM_OFFICE + " where state_cd=? and  off_cd=?";
//		try {
//			tmgr = new TransactionManagerReadOnly("getOffDescr");
//			ps = tmgr.prepareStatement(insQuery);
//			ps.setString(1, state_cd);
//			ps.setInt(2, off_cd);
//
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				dscr = rs.getString("off_name");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} catch (VahanException e) {
//			LOGGER.error(e.getMessage());
//			throw e;
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return dscr;
//
//	}
//
//	public static boolean isgenerateLoiAppl(String state_cd) throws VahanException {
//		boolean valid = false;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		String insQuery = "select genrate_ol_appl from permit.vm_permit_state_configuration  where state_cd = ?";
//		try {
//			tmgr = new TransactionManagerReadOnly("getOffDescr");
//			ps = tmgr.prepareStatement(insQuery);
//			ps.setString(1, state_cd);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				valid = rs.getBoolean("genrate_ol_appl");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} catch (VahanException e) {
//			LOGGER.error(e.getMessage());
//			throw e;
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return valid;
//	}
//
//	// Functions necessary to save an application
//
//	public static String applicationStatusForPermit(String regn_no, String state_cd) throws VahanException {
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String str = "";
//		if (!CommonUtils.isNullOrBlank(regn_no) && !regn_no.equalsIgnoreCase("NEW")) {
//			if (regn_no != null && state_cd != null) {
//				regn_no = regn_no.toUpperCase();
//				try {
//					tmgr = new TransactionManagerReadOnly("applicationStatus");
//
//					sql = "SELECT  distinct a.action_cd,a.appl_no,a.op_dt,b.regn_no,ta.action_abbrv from "
//							+ TableList.VA_STATUS + " a" + " inner join " + TableList.VA_DETAILS
//							+ " b on b.appl_no = a.appl_no" + " inner join " + TableList.TM_ACTION
//							+ " ta on ta.action_cd = a.action_cd"
//							+ " WHERE  b.regn_no <> 'NEW'and a.appl_no=b.appl_no and b.regn_no=? and a.state_cd =? AND a.pur_cd <> ?"
//							+ " ORDER BY a.op_dt desc";
//					ps = tmgr.prepareStatement(sql);
//					ps.setString(1, regn_no.toUpperCase());
//					ps.setString(2, state_cd.toUpperCase());
//					ps.setInt(3, TableConstants.VM_TRANSACTION_MAST_NEW_VEHICLE);
//					RowSet rs = tmgr.fetchDetachedRowSet();
//					if (rs.next()) {
//						str = "Your previous Application No <font color=\"red\">" + rs.getString("appl_no")
//								+ "</font> pending in the stage of <font color=\"red\">" + rs.getString("action_abbrv")
//								+ "</font>";
//					}
//				} catch (SQLException e) {
//					LOGGER.error(e.getMessage());
//					throw new VahanException(TableConstants.SomthingWentWrong);
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//					throw new VahanException(TableConstants.SomthingWentWrong);
//				} finally {
//					try {
//						if (tmgr != null) {
//							tmgr.release();
//						}
//					} catch (Exception e) {
//						LOGGER.error(e.getMessage());
//					}
//				}
//			}
//		}
//		return str.toUpperCase();
//	}// end
//		// of
//		// applicationStatus
//
//	
//
//	public static List<Integer> getVmvhClassAccTransportCatg(int classType, String transportCatg)
//			throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		List<Integer> vhClassList = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getVmvhClassAccTransportCatg");
//			String sql = "SELECT vh_class from vm_vh_class where class_type = ? and transport_catg = ?;";
//			ps = tmgr.prepareStatement(sql);
//			ps.setInt(1, classType);
//			ps.setString(2, transportCatg);
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			vhClassList = new ArrayList<Integer>();
//			while (rs.next()) {
//				vhClassList.add(rs.getInt("vh_class"));
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return vhClassList;
//	}
//
//	public static void fileFlow(TransactionManager tmgr, Status_dobj status_dobj) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//
//		try {
//			sql = "update " + TableList.VA_STATUS + " set " + "status = ?," + "office_remark=?," + "public_remark=? "
//			// + ",op_dt=current_timestamp "
//					+ " where appl_no=? and pur_cd=? and action_cd =?  ";
//			ps = tmgr.prepareStatement(sql);
//
//			ps.setString(1, status_dobj.getStatus());
//			ps.setString(2, status_dobj.getOffice_remark());
//			ps.setString(3, status_dobj.getPublic_remark());
//			ps.setString(4, status_dobj.getAppl_no());
//			ps.setInt(5, status_dobj.getPur_cd());
//			
//			int fileMoved = ps.executeUpdate();
//			if (fileMoved == 0) {
//				
//				throw new VahanException("File has already Moved for Appl No-" + status_dobj.getAppl_no());
//			}
//
//			if (status_dobj.getStatus().equalsIgnoreCase(TableConstants.STATUS_COMPLETE)
//					|| status_dobj.getStatus().equalsIgnoreCase(TableConstants.STATUS_REVERT)) {
//
//				if (TableConstants.isNextStageWebService) {
//
//					sql = "  INSERT INTO " + TableList.VHA_STATUS + " "
//							+ "  SELECT appl_no, appl_no_map, pur_cd, flow_slno, file_movement_slno,"
//							+ "  action_cd, seat_cd, cntr_id, status, office_remark, public_remark,"
//							+ "  emp_cd, op_dt, state_cd, rto_code, off_cd " + "  FROM " + TableList.VA_STATUS
//							+ " where appl_no=? and pur_cd=?";
//
//					ps = tmgr.prepareStatement(sql);
//					ps.setString(1, status_dobj.getAppl_no());
//					ps.setInt(2, status_dobj.getPur_cd());
//					ps.executeUpdate();
//
//					sql = "update " + TableList.VA_STATUS + " " + "set status='N',"
//							+ "file_movement_slno=file_movement_slno+1," + "office_remark=''," + "public_remark='',"
//							+ "cntr_id=?," + "emp_cd=? ," + "action_cd=?," + "rto_code=?," + "flow_slno=?,"
//							+ "op_dt=current_timestamp" + " where appl_no=? and pur_cd=?";
//
//					ps = tmgr.prepareStatement(sql);
//					ps.setString(1, status_dobj.getCntr_id());
//					// Added by Afzal on 12-Jan,2015
//					if ((String) Util.getSession().getAttribute("selected_role_cd") == null) {
//						ps.setInt(2, 0);
//					} else {
//						ps.setLong(2, status_dobj.getEmp_cd());
//					}
//					ps.setInt(3, status_dobj.getAction_cd());
//					ps.setString(4, status_dobj.getRto_code());
//					ps.setInt(5, status_dobj.getFlow_slno());
//					ps.setString(6, status_dobj.getAppl_no());
//					ps.setInt(7, status_dobj.getPur_cd());
//					ps.executeUpdate();
//
//				} else {
//					// If (forward or backward) execute this
//					sql = "INSERT INTO " + TableList.VHA_STATUS + " "
//							+ " SELECT state_cd, off_cd, appl_no, pur_cd, flow_slno, file_movement_slno, \n"
//							+ "  action_cd, seat_cd, cntr_id, status, office_remark, public_remark, \n"
//							+ "  file_movement_type, ? , op_dt, current_timestamp, ? " + "  from  "
//							+ TableList.VA_STATUS + " where appl_no=? and pur_cd=? ";
//
//					ps = tmgr.prepareStatement(sql);
//					ps.setLong(1, Long.parseLong(Util.getEmpCode()));// this
//																		// should
//																		// be as
//																		// status_dobj.getEmp_cd()
//																		// need
//																		// to be
//																		// updated
//																		// in
//																		// future...
//					ps.setString(2, Util.getClientIpAdress());
//					ps.setString(3, status_dobj.getAppl_no());
//					ps.setInt(4, status_dobj.getPur_cd());
//					int vhaStatusUpdated = ps.executeUpdate();
//					// end
//					int vaStatusUpdated = 0;
//					if ((status_dobj.getEntry_status() == null || status_dobj.getEntry_status().equals(""))
//							&& status_dobj.getAction_cd() > 0) {
//						sql = "update " + TableList.VA_STATUS + " " + "set status='N',"
//								+ "file_movement_slno=file_movement_slno+1," + "office_remark=''," + "public_remark='',"
//								+ "cntr_id=?," + "emp_cd=? ," + "action_cd=?," + "flow_slno=?,"
//								+ "op_dt=current_timestamp" + " where appl_no=? and pur_cd=?";
//
//						ps = tmgr.prepareStatement(sql);
//						ps.setString(1, status_dobj.getCntr_id());
//						ps.setLong(2, status_dobj.getEmp_cd());
//						ps.setInt(3, status_dobj.getAction_cd());
//						ps.setInt(4, status_dobj.getFlow_slno());
//						ps.setString(5, status_dobj.getAppl_no());
//						ps.setInt(6, status_dobj.getPur_cd());
//						vaStatusUpdated = ps.executeUpdate();
//
//					} else if (((status_dobj.getAction_cd() == 0
//							&& !status_dobj.getStatus().equalsIgnoreCase(TableConstants.STATUS_REVERT)) // it
//																										// must
//																										// execute
//																										// based
//																										// at
//																										// the
//																										// time
//																										// of
//																										// Approval
//							|| (status_dobj.getAction_cd() != 0
//									&& status_dobj.getStatus().equals(TableConstants.STATUS_COMPLETE)))
//							&& (status_dobj.getEntry_status() != null && status_dobj.getEntry_status()
//									.equalsIgnoreCase(TableConstants.STATUS_APPROVED))) {
//						sql = "Delete From " + TableList.VA_STATUS + " WHERE appl_no=? and pur_cd=?";
//						ps = tmgr.prepareStatement(sql);
//						ps.setString(1, status_dobj.getAppl_no());
//						ps.setInt(2, status_dobj.getPur_cd());
//						vaStatusUpdated = ps.executeUpdate();
//						
//					}
//
//					if (vhaStatusUpdated == 0 || (vaStatusUpdated == 0
//							&& (status_dobj.getPur_cd() != TableConstants.VM_TRANSACTION_MAST_HPC
//									&& status_dobj.getPur_cd() != TableConstants.VM_DUPLICATE_TO_TAX_CARD))) {
//						LOGGER.info("File-Flow-" + status_dobj.getAppl_no() + "-" + status_dobj.getPur_cd() + "-"
//								+ status_dobj.getAction_cd() + "-" + status_dobj.getStatus() + "-"
//								+ status_dobj.getEntry_status());
//						throw new VahanException("Problem in File Movement for Appl No-" + status_dobj.getAppl_no()
//								+ ",Please go to home page and try again.");
//					}
//					if (status_dobj.getAction_cd() == 0
//							&& status_dobj.getStatus().equalsIgnoreCase(TableConstants.STATUS_REVERT)) {
//						throw new VahanException(
//								"Please Press Button Only Once for Appl No-" + status_dobj.getAppl_no());
//					}
//				}
//
//			}
//		} catch (VahanException ve) {
//			throw ve;
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			LOGGER.info("File-Flow-" + status_dobj.getAppl_no() + "-" + status_dobj.getPur_cd() + "-"
//					+ status_dobj.getAction_cd() + "-" + status_dobj.getStatus() + "-" + status_dobj.getEntry_status());
//			throw new VahanException(TableConstants.SomthingWentWrong);
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(TableConstants.SomthingWentWrong);
//		}
//	}
//
//	public static void insertUsersTransactionMessage(String message, int counter, TransactionManager tmgr)
//			throws VahanException, SQLException {
//		String sql = "insert into " + TableList.VT_USER_LATEST_ACTIONS
//				+ " (user_cd, message,op_dt) values(?, ?,current_timestamp + (interval '" + counter + " second'))";
//		PreparedStatement ps = tmgr.prepareStatement(sql);
//		ps.setLong(1, Long.parseLong(Util.getEmpCode()));
//		ps.setString(2, message);
//		ps.executeUpdate();
//
//	}
//
//	// End of all functions necessary to save an application
//
//	public static boolean isTransport(int vh_class, Owner_dobj ownerDobj) throws VahanException {
//		Boolean isTransport = null;
//		PreparedStatement psmt = null;
//		TransactionManagerInterface tmgr = null;
//		try {
//
//			if (ownerDobj != null && ownerDobj.getVehType() > 0) {
//				if (ownerDobj.getVehType() == TableConstants.VM_VEHTYPE_TRANSPORT) {
//					isTransport = true;
//				} else {
//					isTransport = false;
//				}
//			} else {
//				tmgr = new TransactionManagerReadOnly("isTransport");
//				String strSQL = "SELECT case when class_type = 1 then true else false end as isTransport FROM "
//						+ TableList.VM_VH_CLASS + " where vh_class = ?";
//				psmt = tmgr.prepareStatement(strSQL);
//				psmt.setInt(1, vh_class);
//				RowSet rs = tmgr.fetchDetachedRowSet();
//				if (rs.next()) {
//					isTransport = rs.getBoolean("isTransport");
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		if (isTransport == null) {
//			throw new VahanException("No Vehicle Type Found");
//		}
//		return isTransport;
//	}
//
//	public static void saveApplStatusinVaStatusandDetail(TransactionManager tmgr, Status_dobj status_dobj)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		try {
//			
//			sql = "insert into " + TableList.VA_STATUS_APPL
//					+ "(status,state_cd,off_cd,appl_no,pur_cd,flow_slno,file_movement_slno,action_cd,seat_cd,"
//					+ " cntr_id,office_remark,public_remark,file_movement_type,emp_cd,op_dt,moved_from_online) "
//					+ " values('N',?,?,?,?,?,?,?,'','','','','F',?,current_timestamp,?)";
//
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(status_dobj.getState_cd(), "AN")) {
//				ps.setString(1, status_dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(status_dobj.getOff_cd()), "N")) {
//				ps.setInt(2, status_dobj.getOff_cd());
//			}
//			if (POSValidator.validate(status_dobj.getAppl_no(), "AN")) {
//				ps.setString(3, status_dobj.getAppl_no());
//			}
//			if (POSValidator.validate(String.valueOf(status_dobj.getPur_cd()), "N")) {
//				ps.setInt(4, status_dobj.getPur_cd());
//			}
//			if (status_dobj.getFlow_slno() != 0) {
//				ps.setInt(5, status_dobj.getFlow_slno());
//			}
//			if (status_dobj.getFlow_slno() != 0) {
//				ps.setInt(6, status_dobj.getFile_movement_slno());
//			}
//			if (status_dobj.getAction_cd() != 0) {
//				ps.setInt(7, status_dobj.getAction_cd());
//			}
//
//			ps.setLong(8, status_dobj.getEmp_cd());
//			// ps.setString(9, TableConstants.MOVED_FROM_ONLINE_APPL_STATUS_NO);
//			if (!CommonUtils.isNullOrBlank(status_dobj.getMoved_from_online())) {
//				ps.setString(9, status_dobj.getMoved_from_online());
//			}
//			ps.executeUpdate();
//
//
//			sql = "INSERT INTO " + TableList.VA_DETAILS_APPL
//					+ " ( appl_no,pur_cd,appl_dt,regn_no,user_id,user_type,state_cd,off_cd,entry_status,entry_ip)"
//					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(status_dobj.getAppl_no(), "AN")) {
//				ps.setString(1, status_dobj.getAppl_no()); // actual
//			} // application
//			if (POSValidator.validate(String.valueOf(status_dobj.getPur_cd()), "N")) { // no
//				ps.setInt(2, status_dobj.getPur_cd());
//			}
//			ps.setTimestamp(3, ServerUtil.getSystemDateInPostgres());
//			if (POSValidator.validate(status_dobj.getRegn_no(), "ANS")) {
//				ps.setString(4, status_dobj.getRegn_no());
//			}
//			if (!CommonUtils.isNullOrBlank(status_dobj.getMobileNo())) {
//				ps.setString(5, status_dobj.getMobileNo());
//			}
//			
//			ps.setString(6, ""); // blank user type.
//			if (POSValidator.validate(status_dobj.getState_cd(), "A")) {
//				ps.setString(7, status_dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(status_dobj.getOff_cd()), "N")) {
//				ps.setInt(8, status_dobj.getOff_cd());
//			}
//			if (!CommonUtils.isNullOrBlank(status_dobj.getEntry_status())) {
//				ps.setString(9, status_dobj.getEntry_status());
//			}
//			if (!CommonUtils.isNullOrBlank(Util.getClientIpAdress())) {
//				ps.setString(10, Util.getClientIpAdress());
//			}
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new VahanException(e.getMessage());
//		}
//
//	}
//
//	// checkVehiclePendingstatus
//	public static String checkVehiclePendingstatus(String regn_no, int purCd, String skipPendingPur, String state_cd)
//			throws VahanException {
//		String status = "";
//		// String state_cd = "";
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		RowSet rs = null;
//		String actiondescr = "";
//		int pur_cd = 0;
//		String returnMsg = "";
//		String pur_descr = "";
//		String applicationno = "";
//		String payment_appl_status = "";
//		String sql = null;
//		boolean check;
//
//		String whereiam = "checkVehiclePendingstatus";
//		check = skipPendingPur.contains(Integer.toString(purCd));
//
//		try {
//
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			if (check) {
//				sql = "SELECT dtls.* , sts.action_cd,sts.state_cd as state_cd, tmact.action_descr,application_status,tp.descr as pur_descr from onlineschema.VA_DETAILS_APPL dtls"
//						+ " inner join onlineschema.VA_STATUS_APPL  sts on sts.appl_no=dtls.appl_no and sts.pur_cd=dtls.pur_cd"
//						+ " left join  tm_action tmact on tmact.action_cd=sts.action_cd"
//						+ " left join tm_purpose_mast tp on tp.pur_cd = sts.pur_cd "
//						+ " left outer join onlineschema.vt_temp_appl_transaction vtt on vtt.transaction_no = replace(dtls.appl_no,right(dtls.appl_no,8),('T'||right(dtls.appl_no,7)))"
//						+ " where dtls.regn_no=?  and application_status not in ('S') and sts.state_cd= ? and (dtls.pur_cd = any(string_to_array(?,',')::numeric[])) order by  dtls.appl_dt desc limit 1";
//			} else {
//
//				sql = "SELECT dtls.* , sts.action_cd,sts.state_cd as state_cd, tmact.action_descr,application_status,tp.descr as pur_descr from onlineschema.VA_DETAILS_APPL dtls"
//						+ " inner join onlineschema.VA_STATUS_APPL  sts on sts.appl_no=dtls.appl_no and sts.pur_cd=dtls.pur_cd"
//						+ " left join  tm_action tmact on tmact.action_cd=sts.action_cd"
//						+ " left join tm_purpose_mast tp on tp.pur_cd = sts.pur_cd "
//						+ " left outer join onlineschema.vt_temp_appl_transaction vtt on vtt.transaction_no = replace(dtls.appl_no,right(dtls.appl_no,8),('T'||right(dtls.appl_no,7)))"
//						+ " where dtls.regn_no=? and sts.state_cd= ?  and NOT (dtls.pur_cd = any(string_to_array(?,',')::numeric[])) order by  dtls.appl_dt desc limit 1";
//			}
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regn_no, "ANS"))
//				ps.setString(1, regn_no);
//			ps.setString(2, state_cd);
//			if (POSValidator.validate(skipPendingPur, "ANWS"))
//				ps.setString(3, skipPendingPur);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//
//				
//				pur_cd = rs.getInt("pur_cd");
//				status = rs.getString("entry_status");
//				actiondescr = rs.getString("action_descr");
//				applicationno = rs.getString("appl_no");
//				payment_appl_status = rs.getString("application_status");
//				pur_descr = rs.getString("pur_descr");
//			}
//			if (purCd == TableConstants.VM_PMT_TEMP_PUR_CD
//					&& (state_cd.equalsIgnoreCase("UP") || state_cd.equalsIgnoreCase("CG"))) {
//				if (!(pur_cd >= 25 && pur_cd <= 50) || pur_cd == TableConstants.VM_PMT_TEMP_PUR_CD) {
//					if ((TableConstants.VERIFIED_BY_USER.equals(status)
//							&& CommonUtils.isNullOrBlank(payment_appl_status))
//							|| (TableConstants.VERIFIED_BY_USER.equals(status)
//									&& !payment_appl_status.equals(ApplicationConfig.APPL_FAILED_TRANS))) {
//						returnMsg = "Application " + applicationno + "  is pending at " + actiondescr
//								+ " stage against  the purpose  " + pur_descr + " . Please pay fees.";
//					} else if (TableConstants.NOT_VERIFIED_BY_USER.equals(status)) {
//						returnMsg = " Application No " + applicationno
//								+ " is pending at Verification stage against  the purpose  " + pur_descr + ".";
//					} else if (!CommonUtils.isNullOrBlank(payment_appl_status) && payment_appl_status.equals('R')) {
//						returnMsg = " Application " + applicationno + "  is pending at stage against  the purpose  "
//								+ pur_descr + ". Please pay fees.";
//					} else {
//						returnMsg = "";
//					}
//				}
//
//			} else {
//				if ((TableConstants.VERIFIED_BY_USER.equals(status) && CommonUtils.isNullOrBlank(payment_appl_status))
//						|| (TableConstants.VERIFIED_BY_USER.equals(status)
//								&& !payment_appl_status.equals(ApplicationConfig.APPL_FAILED_TRANS))) {
//					returnMsg = "Application " + applicationno + "  is pending at " + actiondescr
//							+ " stage against the purpose  " + pur_descr + " . Please pay fees.";
//				} else if (TableConstants.NOT_VERIFIED_BY_USER.equals(status)) {
//					returnMsg = " Application No " + applicationno
//							+ " is pending at Verification stage against the purpose  " + pur_descr + ".";
//				} else if (!CommonUtils.isNullOrBlank(payment_appl_status) && payment_appl_status.equals('R')) {
//					returnMsg = " Application " + applicationno + "  is pending at stage against the purpose  "
//							+ pur_descr + ". Please pay fees.";
//				} else {
//					returnMsg = "";
//				}
//			}
//		} catch (Exception e) {
//			throw new VahanException(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//				if (rs != null) {
//					rs = null;
//				}
//			}
//		}
//
//		return returnMsg;
//	}
//
//	public static boolean checkVehiclePermission(String regn_no, int purCd, String state_cd, int permit_rto_cd,
//			Map<String, Integer> SeatCapRangeMap) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		RowSet rs = null;
//		boolean result = false;
//		try {
//			tmgr = new TransactionManagerReadOnly("checkVehiclePermission");
//			
//			String sql = "select a.regn_no,a.vh_class,b.* from vt_owner a"
//					+ " inner join onlinepermit.tm_veh_permit_permitted b on a.fuel::text = any(String_to_array(b.fuel_type::text,',')) and"
//					+ " case when veh_class = 'ALL' then true else"
//					+ " a.vh_class ::text = any(String_to_array(b.veh_class,',')) end and"
//					+ " case when b.veh_class_not_allowed = 'ALL'  OR  a.vh_class ::text = any(String_to_array(b.veh_class_not_allowed,',')) then false else true end and"
//					+ " ((a.purchase_dt::date + (b.veh_age||' YEAR')::interval)-interval '1' DAY)::date >= current_date and"
//					+ " case when b.seating_capacity is null then case when coalesce(TRIM(seat_cap_range), '') <> '' then (a.seat_cap ::numeric) between split_part(seat_cap_range, ',', 1)::numeric and split_part(seat_cap_range, ',', 2)::numeric else true end else (a.seat_cap = b.seating_capacity or a.seat_cap between ? and ?) end and "
//					+ " case when b.veh_permitted_ownership = 'ALL' then true else a.owner_cd ::text = any(String_to_array(b.veh_permitted_ownership,',')) end"
//					+ " where a.regn_no = ? and b.state_cd = ? and b.off_cd = ? and b.pur_cd = ? and a.status in ('A','Y') order by b.pur_cd,b.veh_age";
//			ps = tmgr.prepareStatement(sql);
//			if (SeatCapRangeMap != null && !SeatCapRangeMap.isEmpty()) {
//				ps.setInt(1, (Integer) SeatCapRangeMap.get("min_range"));
//				ps.setInt(2, (Integer) SeatCapRangeMap.get("max_range"));
//			} else {
//				ps.setInt(1, 0);
//				ps.setInt(2, 1);
//			}
//
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(3, regn_no);
//			}
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(4, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(permit_rto_cd), "N")) {
//				ps.setInt(5, permit_rto_cd);
//			}
//			if (POSValidator.validate(String.valueOf(purCd), "N")) {
//				ps.setInt(6, purCd);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				result = true;
//			} else {
//
//				throw new VahanException(
//						"Permit Condition for the Vehicle are not matching with the Permit Restrictions as per directions given by State Transport department  <a href='/onlinepermit/vahan/ui/OnlinePermit/formDetailsofPermit.xhtml'>  Kindly check Permit Permissible Condition by click here </a>");
//			}
//		} catch (VahanException e) {
//			throw new VahanException(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error("error occurs from checkVehiclePermission for purpose = " + purCd + " " + e.getMessage());
//			throw new VahanException("Some problem occurred while validating your vehicle scenario.");
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(
//							"error occurs from checkVehiclePermission for purpose = " + purCd + " " + e.getMessage());
//				}
//				if (rs != null) {
//					rs = null;
//				}
//			}
//		}
//
//		return result;
//	}
//
//	public static Map<Integer, String> getPermitAllowedDtls(String state_cd, int permit_rto, int fuel, int vehClass,
//			Date purchase_dt, int seat_cap, int pur_cd, int owner_cd) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		RowSet rs = null;
//		boolean result = false;
//		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
//		try {
//			tmgr = new TransactionManagerReadOnly("getPermitAllowedDtls");
//			String sql = "select a.pur_cd,a.permit_type,string_agg(distinct a.permit_catg,',') as permit_catg, string_agg(a.permitted_vehicle_id::text,',') as vehicle_id"
//					+ " from onlinepermit.tm_permit_type_allowed a" + " inner join ("
//					+ " select b.* from  onlinepermit.tm_veh_permit_permitted b"
//					+ " where b.state_cd = ? and b.off_cd = ? and b.pur_cd = ? and ?::text = any(String_to_array(b.fuel_type::text,',')) and"
//					+ " case when veh_class = 'ALL' then true else ?::text = any(String_to_array(veh_class,',')) end and"
//					+ " ((?::date + (b.veh_age||' YEAR')::interval)-interval '1' DAY)::date >= current_date and"
//					+ " case when seating_capacity is null then true else ? = seating_capacity end   and"
//					+ " case when veh_class_not_allowed = 'ALL' OR ? ::text = any(String_to_array(veh_class_not_allowed,',')) then false else true end and"
//					+ " case when b.veh_permitted_ownership = 'ALL' then true else ? ::text = any(String_to_array(b.veh_permitted_ownership,',')) end"
//					+ " order by b.pur_cd,b.veh_age"
//					+ " )z  on z.permitted_vehicle_id = a.permitted_vehicle_id and z.pur_cd = a.pur_cd group by 1,2 order by 1,2";
//
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			ps.setString(i++, state_cd);
//			ps.setInt(i++, permit_rto);
//			ps.setInt(i++, pur_cd);
//			ps.setInt(i++, fuel);
//			ps.setInt(i++, vehClass);
//			ps.setDate(i++, new java.sql.Date(purchase_dt.getTime()));
//			ps.setInt(i++, seat_cap);
//			ps.setInt(i++, vehClass);
//			ps.setInt(i, owner_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			String pmt_type = ",";
//			while (rs.next()) {
//				pmt_type = "," + rs.getString("permit_type") + pmt_type;
//				map.put(pur_cd, pmt_type);
//				map.put(rs.getInt("permit_type"), "," + rs.getString("permit_catg") + ",");
//			}
//		} catch (Exception e) {
//			LOGGER.error("error occurs from getPermitAllowedDtls for purpose = " + pur_cd + " " + e.getMessage());
//			throw new VahanException("Some problem occurred while validating your vehicle scenario.");
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(
//							"error occurs from getPermitAllowedDtls for purpose = " + pur_cd + " " + e.getMessage());
//				}
//				if (rs != null) {
//					rs = null;
//				}
//			}
//		}
//
//		return map;
//	}
//
//	// To Check whether given rto_cd exists in PERMIT.VM_OFF_ALLOTMENT
//	public static String getAvailabilityForPermit(String regn_no, int purCd, String state_cd) {
//		String whereiam = "ServerUtil.getAvailabilityForFreshPermit";
//		PreparedStatement ps = null;
//		TransactionManager tmgr = null;
//		RowSet rs = null;
//		String msg = null;
//		try {
//			switch (purCd) {
//			case TableConstants.VM_PMT_RENEWAL_PUR_CD:
//				msg = validToRenew(regn_no, state_cd);
//				break;
//			case TableConstants.VM_PMT_FRESH_PUR_CD:
//			case TableConstants.VM_PMT_APPLICATION_PUR_CD:
//				msg = hasPermit(regn_no, state_cd);
//				break;
//			case TableConstants.VM_PMT_VARIATION_ENDORSEMENT_PUR_CD:
//				msg = applicationStatusForPermit(regn_no, state_cd);
//				break;
//			default:
//				msg = "Invalid selection of purpose for Permit";
//				break;
//			}
//			
//		} catch (VahanException e) {
//			LOGGER.error(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//
//		return msg;
//	}
//
//	public static String getAvailabilityForRenewalPermit(String regn_no, int purCd, String state_cd) {
//		String whereiam = "ServerUtil.getAvailabilityForFreshPermit";
//		PreparedStatement ps = null;
//		TransactionManager tmgr = null;
//		RowSet rs = null;
//		String msg = null;
//		try {
//			msg = validToRenew(regn_no, state_cd);
//
//		}
//		// catch (VahanException e) {
//		// LOGGER.error(e.getMessage());
//		// }
//		catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//					;
//				}
//			}
//		}
//
//		return msg;
//	}
//
//	// gettingPermitDetails
//	public static PermitDetailDobj set_vt_permit_regnNo_to_dobj(String regn_no) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		PermitDetailDobj permit_dobj = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("set_vt_permit_regnNo_to_dobj");
//			String query;
//			query = "SELECT a.*, b.descr as catg_desc, c.max_days_renew_auth, c.auth_fr_last_expiry ,c.auth_min_duration_days, c.auth_recursive_fee from "
//					+ TableList.VT_PERMIT + " a" + " left join " + TableList.VM_PERMIT_CATG
//					+ " b on b.code=a.pmt_catg and b.state_cd=a.state_cd and b.permit_type=a.pmt_type " + " inner join "
//					+ TableList.ONLINE_PERMIT_STATE_CONFIGURE + " c on c.state_cd = a.state_cd"
//					+ " where a.regn_no= ? ";
//			ps = tmgr.prepareStatement(query);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				permit_dobj = new PermitDetailDobj();
//				permit_dobj.setApplicationNo(rs.getString("appl_no"));
//				permit_dobj.setRegn_no(rs.getString("regn_no"));
//				permit_dobj.setPmt_type(rs.getInt("pmt_type"));
//				permit_dobj.setPmt_catg(rs.getInt("pmt_catg"));
//				permit_dobj.setPmt_catg_desc(rs.getString("catg_desc"));
//				permit_dobj.setState_cd(rs.getString("state_cd"));
//				permit_dobj.setOff_cd(rs.getInt("off_cd"));
//				
//				permit_dobj.setService_type(rs.getInt("service_type"));
//				
//				permit_dobj.setParking(rs.getString("parking"));
//				permit_dobj.setPur_cd(rs.getInt("pur_cd"));
//				
//				permit_dobj.setPmt_no(rs.getString("pmt_no"));
//				permit_dobj.setValid_from(rs.getDate("valid_from"));
//				permit_dobj.setValid_upto((rs.getDate("valid_upto")));
//				permit_dobj.setReplaceDt(rs.getDate("replace_date"));
//				permit_dobj.setIssue_dt(rs.getDate("issue_dt"));
//				permit_dobj.setMax_days_renew_auth(rs.getInt("max_days_renew_auth"));
//				permit_dobj.setAuth_fr_last_expiry(rs.getBoolean("auth_fr_last_expiry"));
//				permit_dobj.setAuth_recursive_fee(rs.getBoolean("auth_recursive_fee"));
//				permit_dobj.setAuth_min_duration_days(rs.getInt("auth_min_duration_days"));
//
//				if (permit_dobj.getPmt_type() == Integer.parseInt(TableConstants.NATIONAL_PERMIT)
//						|| permit_dobj.getPmt_type() == Integer.parseInt(TableConstants.All_India_Tourist_Permit)) {
//					query = "select * from " + TableList.VT_PERMIT_HOME_AUTH + "\n" + " where regn_no=?";
//					ps = tmgr.prepareStatement(query);
//					if (POSValidator.validate(regn_no, "ANS")) {
//						ps.setString(1, regn_no);
//					}
//					rs = null;
//					rs = tmgr.fetchDetachedRowSet_No_release();
//					if (rs.next()) {
//						permit_dobj.setAuthNo(rs.getString("auth_no"));
//						permit_dobj.setPre_authFrom(DateUtil.getDateInDDMMYYYY(rs.getDate("auth_fr")));
//						permit_dobj.setPre_authTo(DateUtil.getDateInDDMMYYYY(rs.getDate("auth_to")));
//
//					}
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return permit_dobj;
//	}
//
//	public static String hasGetDupPermit(String regn_no, String state_cd, int pur_cd, VehicleParameters parameter)
//			throws VahanException {
//		String msg = null;
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String whereIam = "ServerUtils.hasGetDupPermit()";
//		String validUpto = null;
//		String curDate = null;
//		String condition_formula = null;
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//
//			sql = "select case when ((valid_upto::date)<(current_date::date)) then true else false end as validity,valid_upto,c.permanent_permit_valid as spl_permanent_permit, d.permanent_permit_valid as tmp_permanent_permit, a.pmt_type from "
//					+ TableList.VT_PERMIT + " a left outer join " + TableList.VM_PERMIT_STATE_CONFIGURATION
//					+ " b on a.state_cd = b.state_cd left outer join " + TableList.ONLINE_TM_CONFIGURATION_SPL_PMT
//					+ " c on a.state_cd = c.state_cd left outer join " + TableList.ONLINE_TM_CONFIGURATION_TMP_PMT
//					+ " d on a.state_cd = d.state_cd WHERE regn_no=? and a.state_cd = ?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(2, state_cd);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				if (rs.getBoolean("validity")) {
//
//					if (pur_cd == TableConstants.VM_PMT_SPECIAL_PUR_CD) {
//						condition_formula = rs.getString("spl_permanent_permit");
//					} else if (pur_cd == TableConstants.VM_PMT_TEMP_PUR_CD) {
//						condition_formula = rs.getString("tmp_permanent_permit");
//					}
//				}
//				if (!CommonUtils.isNullOrBlank(condition_formula)) {
//
//					boolean permanent_permit_valid = FormulaUtils.isCondition(
//							FormulaUtils.replaceTagValues(condition_formula, parameter),
//							("Server Util (has Get Dup Permit) for state/regn : " + state_cd + regn_no));
//
//					validUpto = rs.getString("valid_upto");
//					validUpto = DateUtil.convertStringYYYYMMDDToDDMMYYYY(validUpto);
//					if (pur_cd == TableConstants.VM_PMT_DUPLICATE_PUR_CD) {
//						msg = "You can't apply for Duplicate Permit. Permit was valid upto " + validUpto;
//					}
//					if (pur_cd == TableConstants.VM_PMT_AUTH_COUNTER_SIGNATURE_PUR_CD) {
//						msg = "You can't apply for Counter Signature Permit. Permit was  valid upto " + validUpto;
//					}
//					if (pur_cd == TableConstants.VM_PMT_SPECIAL_PUR_CD && permanent_permit_valid) {
//						msg = "You can't apply for Special Permit. Permit was  valid upto " + validUpto;
//					}
//					if (pur_cd == TableConstants.VM_PMT_TEMP_PUR_CD && permanent_permit_valid) {
//						msg = "You can't apply for Temporary Permit. Permit was  valid upto " + validUpto;
//					}
//					if (pur_cd == TableConstants.VM_PMT_RENEWAL_HOME_AUTH_PERMIT_PUR_CD) {
//						msg = "You can't renew your home authorization. Permit was  valid upto " + validUpto
//								+ ". Please renew your permit first.";
//					}
//				} else {
//					if (pur_cd == TableConstants.VM_PMT_RENEWAL_HOME_AUTH_PERMIT_PUR_CD
//							&& (rs.getInt("pmt_type") != TableConstants.ALL_INDIA_TOURIST_PERMIT
//									&& rs.getInt("pmt_type") != TableConstants.NATIONAL_PERMIT_TYPE)) {
//						msg = "You can't apply for Renewal of Authorization. Your permit is neither National Permit nor All India Tourist Permit";
//					}
//				}
//
//			} else {
//				if (pur_cd == TableConstants.VM_PMT_COUNTER_SIGNATURE_AFTER_AUTH
//						|| pur_cd == TableConstants.VM_PMT_AUTH_COUNTER_SIGNATURE_PUR_CD) {
//					throw new VahanException("Something went wrong while validting your vehicle");
//				}
//				if (pur_cd == TableConstants.VM_PMT_TEMP_PUR_CD || pur_cd == TableConstants.VM_PMT_SPECIAL_PUR_CD) {
//					if (pur_cd == TableConstants.VM_PMT_TEMP_PUR_CD) {
//						sql = "select * from " + TableList.ONLINE_TM_CONFIGURATION_TMP_PMT + " where state_cd =? ";
//					} else if (pur_cd == TableConstants.VM_PMT_SPECIAL_PUR_CD) {
//						sql = "select * from " + TableList.ONLINE_TM_CONFIGURATION_SPL_PMT + " where state_cd =? ";
//					}
//					ps = tmgr.prepareStatement(sql);
//					if (POSValidator.validate(state_cd, "A")) {
//						ps.setString(1, state_cd);
//					}
//					rs = tmgr.fetchDetachedRowSet_No_release();
//					if (rs.next()) {
//						condition_formula = rs.getString("permanent_permit_valid");
//						if (!CommonUtils.isNullOrBlank(condition_formula)) {
//							boolean permanent_permit_valid = FormulaUtils.isCondition(
//									FormulaUtils.replaceTagValues(condition_formula, parameter),
//									("Server Util (ONLINE_TM_CONFIGURATION_TMP_PMT/ONLINE_TM_CONFIGURATION_SPL_PMT) for state : "
//											+ state_cd));
//							if (permanent_permit_valid && pur_cd == TableConstants.VM_PMT_SPECIAL_PUR_CD) {
//								msg = "You can't apply for Special Permit. First apply for main permit";
//							}
//							if (permanent_permit_valid && pur_cd == TableConstants.VM_PMT_TEMP_PUR_CD) {
//								msg = "You can't apply for Temporary Permit. First apply for main permit";
//							}
//						}
//					}
//				}
//
//				if (CommonUtils.isNullOrBlank(msg)) {
//					
//					if (pur_cd == TableConstants.VM_PMT_REPLACE_VEH_PUR_CD) {
//						String Query = "Select CASE WHEN a.trans_pur_cd=43 and (a.pmt_no<>c.pmt_no or (c.pmt_no is null and a.pmt_no is not null)) THEN null::text ELSE 'YOUR PERMIT IS SURRENDER. PLEASE RESTORE FIRST' END as msg"
//								+ " from " + TableList.VT_PERMIT_TRANSACTION + "  a"
//								+ " inner join permit.VH_PERMIT b on a.regn_no = b.regn_no AND b.pmt_status = 'SUR'"
//								+ " left outer join permit.VT_PERMIT c on c.regn_no = a.regn_no"
//								+ " where a.regn_no = ?  order by b.op_dt DESC limit 1";
//
//						ps = tmgr.prepareStatement(Query);
//						int i = 1;
//						if (POSValidator.validate(regn_no, "ANS")) {
//							ps.setString(i++, regn_no);
//						}
//						rs = tmgr.fetchDetachedRowSet_No_release();
//						if (rs.next()) {
//							msg = rs.getString("msg");
//						}
//					}
//				}
//
//				if (CommonUtils.isNullOrBlank(msg) && false) {
//
//					sql = " SELECT  distinct a.action_cd,a.appl_no,a.op_dt,b.regn_no,ta.action_abbrv ,a.pur_cd,tmp.descr as pur_descr from  "
//							+ TableList.VA_STATUS + "  a " + "  inner join  " + TableList.VA_DETAILS
//							+ "  b on b.appl_no = a.appl_no" + "  inner join  " + TableList.TM_ACTION
//							+ "  ta on ta.action_cd = a.action_cd"
//							+ " inner join tm_purpose_mast tmp on tmp.pur_cd = a.pur_cd "
//							+ "  WHERE  b.regn_no <> 'NEW' and a.appl_no=b.appl_no and b.regn_no=? and a.state_cd =? AND  a.pur_cd <> ?"
//							+ "  ORDER BY a.op_dt desc";
//					ps = tmgr.prepareStatement(sql);
//					if (POSValidator.validate(regn_no, "ANS"))
//						ps.setString(1, regn_no.toUpperCase());
//					if (POSValidator.validate(state_cd, "A"))
//						ps.setString(2, state_cd.toUpperCase());
//					ps.setInt(3, TableConstants.VM_TRANSACTION_MAST_NEW_VEHICLE);
//					
//					rs = tmgr.fetchDetachedRowSet_No_release();
//					if (rs.next()) {
//						if (pur_cd == TableConstants.VM_PMT_TEMP_PUR_CD && state_cd.equalsIgnoreCase("UP")) {
//							if (!(rs.getInt("pur_cd") >= 25 && rs.getInt("pur_cd") <= 50)
//									|| rs.getInt("pur_cd") == TableConstants.VM_PMT_TEMP_PUR_CD) {
//								msg = " Your previous Application No " + rs.getString("appl_no")
//										+ " pending in the stage of " + rs.getString("action_abbrv")
//										+ " against  the purpose  " + rs.getString("pur_descr");
//							}
//						} else {
//							msg = " Your previous Application No " + rs.getString("appl_no")
//									+ " pending in the stage of " + rs.getString("action_abbrv")
//									+ " against  the purpose  " + rs.getString("pur_descr");
//						}
//
//					}
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error("hasGetDupPermit for regn_no and pur_cd = " + regn_no + pur_cd + e.getMessage());
//			throw new VahanException("Something went wrong while validting your vehicle");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error("hasGetDupPermit for regn_no and pur_cd = " + regn_no + pur_cd + e.getMessage());
//				throw new VahanException("Something went wrong while validting your vehicle");
//			}
//		}
//		return msg;
//
//	}
//
//	public static void checkPendingStatusInRTO(String regn_no, String state_cd) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("checkPendingStatusInRTO");
//
//			String sql = " SELECT  distinct a.action_cd,a.appl_no,a.op_dt,b.regn_no,ta.action_abbrv from  "
//					+ TableList.VA_STATUS + "  a " + "  inner join  " + TableList.VA_DETAILS
//					+ "  b on b.appl_no = a.appl_no" + "  inner join  " + TableList.TM_ACTION
//					+ "  ta on ta.action_cd = a.action_cd"
//					+ "  WHERE  b.regn_no <> 'NEW' and b.state_cd=? and a.appl_no=b.appl_no and b.regn_no=? AND  a.pur_cd <> ?"
//					+ "  ORDER BY a.op_dt desc limit 1";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, state_cd);
//			if (POSValidator.validate(regn_no, "ANS"))
//				ps.setString(2, regn_no.toUpperCase());
//			ps.setInt(3, TableConstants.VM_TRANSACTION_MAST_NEW_VEHICLE);
//
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				throw new VahanException("Your previous Application No " + rs.getString("appl_no")
//						+ " pending at RTO in the stage of " + rs.getString("action_abbrv"));
//			}
//		} catch (VahanException e) {
//			throw new VahanException(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error("Problem Occured while getting pending status at RTO of vehicle : " + regn_no);
//			throw new VahanException(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error("Problem occured while releasing transaction manager " + e.getMessage());
//				throw new VahanException("Problem Occured while getting pending status at RTO of vehicle : " + regn_no);
//			}
//		}
//	}
//
//	public static String formatTransID(String transId, char changeVariable) {
//		String returnValue = "";
//		String FirstPart = transId.substring(0, 8);
//		String lastPart = transId.substring(8, transId.length());
//		returnValue = FirstPart.concat(lastPart.replace(lastPart.charAt(0), changeVariable));
//
//		return returnValue;
//	}
//
//	// forMhTemp
//	// addedByVinit22NOv
//	public static int[] getInitialAction_Temp(TransactionManager tmgr, String state_cd, int pur_cd,
//			VehicleParameters parameters, int flowSrNo) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		int action_cd = 0;
//		int flowsr_no = flowSrNo + 1;
//
//		// boolean retStatus =
//		// ServerUtil.getNocRetentionData(dobj.getRCPT_NO());
//		int[] retArr = null;
//		try {
//
//			while (true) {
//				sql = "select flow_srno,action_cd,condition_formula from onlinepermit.va_permit_purpose_action_flow  "
//						+ " where pur_cd=? and state_cd=? and flow_srno=? order by 1";
//				ps = tmgr.prepareStatement(sql);
//				if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//					ps.setInt(1, pur_cd);
//				}
//				if (POSValidator.validate(state_cd, "A")) {
//					ps.setString(2, state_cd);
//				}
//				if (POSValidator.validate(String.valueOf(flowsr_no), "N")) {
//					ps.setInt(3, flowsr_no);
//				}
//				RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//				if (rs.next()) {
//
//					if (parameters != null) {
//						if (FormulaUtils.isCondition(
//								FormulaUtils.replaceTagValues(rs.getString("condition_formula"), parameters),
//								("Server Util (va permit purpose action flow) for state/pur : " + state_cd + pur_cd))) {
//							// action_cd = rs.getInt("action_cd");
//							flowsr_no = rs.getInt("flow_srno");
//							retArr = new int[2];
//							retArr[0] = rs.getInt("action_cd");
//							retArr[1] = flowsr_no;
//							break;
//						} else {
//							flowsr_no++;
//						}
//
//					} else {
//						
//						flowsr_no = rs.getInt("flow_srno");
//						retArr = new int[2];
//						retArr[0] = rs.getInt("action_cd");
//						retArr[1] = flowsr_no;
//						break;
//					}
//				} else {
//					break;
//				}
//			}
//			if (retArr == null) {
//				retArr = new int[2];
//				retArr[0] = 0;
//				retArr[1] = flowsr_no;
//				// throw new VahanException("Action Code Not Found..");
//			}
//
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(e.getMessage());
//		}
//
//		return retArr;
//	}
//
//	// EndByVinit22NOv
//	public static void updateApprovedStatus(TransactionManager tmgr, Status_dobj dobj) throws SQLException {
//		PreparedStatement ps = null;
//		String sql = null;
//
//		sql = "UPDATE " + TableList.VA_DETAILS + "   SET  entry_status=?,confirm_ip=?,confirm_date=current_timestamp"
//				+ " WHERE  appl_no=? and pur_cd=?";
//
//		ps = tmgr.prepareStatement(sql);
//		if (!CommonUtils.isNullOrBlank(dobj.getEntry_status())) {
//			ps.setString(1, dobj.getEntry_status());
//		}
//		if (!CommonUtils.isNullOrBlank(Util.getClientIpAdress())) {
//			ps.setString(2, Util.getClientIpAdress());
//		}
//		if (!CommonUtils.isNullOrBlank(dobj.getAppl_no())) {
//			ps.setString(3, dobj.getAppl_no());
//		}
//		if (dobj.getPur_cd() != 0) {
//			ps.setInt(4, dobj.getPur_cd());
//		}
//		ps.executeUpdate();
//	} // end of updateApprovedStatus
//
//	public static int inVA_PERMIT_TRANSACTIONfromONLINE(TransactionManager tmgr, Status_dobj dobj)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		try {
//			sql = "INSERT INTO permit.va_permit_transaction("
//					+ " state_cd, off_cd, appl_no, regn_no, pmt_no, pur_cd, rcpt_no,"
//					+ " trans_pur_cd, remarks, order_no, order_dt, order_by, new_regn_no," + " user_cd, op_dt)"
//					+ " SELECT state_cd, off_cd, ?, regn_no, pmt_no, pur_cd, rcpt_no,"
//					+ " trans_pur_cd, remarks, order_no, order_dt, order_by, new_regn_no, "
//					+ " user_cd, current_timestamp" + " FROM onlinepermit.va_permit_transaction where appl_no = ?;";
//
//			ps = tmgr.prepareStatement(sql);
//			
//
//			if (POSValidator.validate(dobj.getPermanentAppno(), "AN")) {
//				ps.setString(1, dobj.getPermanentAppno());
//			}
//			if (POSValidator.validate(dobj.getAppl_no(), "AN")) {
//				ps.setString(2, dobj.getAppl_no());
//			}
//			int i = ps.executeUpdate();
//			if (i > 0) {
//				sql = "UPDATE onlinepermit.va_permit_transaction"
//						+ " SET appl_no=?, op_dt=CURRENT_TIMESTAMP WHERE appl_no=?";
//				ps = tmgr.prepareStatement(sql);
//
//				if (POSValidator.validate(dobj.getPermanentAppno(), "AN")) {
//					ps.setString(1, dobj.getPermanentAppno());
//				}
//				if (POSValidator.validate(dobj.getAppl_no(), "AN")) {
//					ps.setString(2, dobj.getAppl_no());
//				}
//				return ps.executeUpdate();
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Saving status appl detials failed.");
//		}
//		return 0;
//	}
//
//	public static int inVA_DetailfromVA_Detail_Appl(TransactionManager tmgr, Status_dobj dobj) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		try {
//			sql = "INSERT INTO vahan4.va_details(appl_no, pur_cd, appl_dt, regn_no, user_id, user_type, entry_ip, "
//					+ "  confirm_ip, confirm_status, confirm_date, state_cd, off_cd)"
//					+ " SELECT ?, pur_cd, CURRENT_TIMESTAMP, regn_no, user_id, user_type, ?, "
//					+ "  entry_ip, confirm_status, confirm_date, state_cd, off_cd "
//					+ " FROM onlineschema.va_details_appl where appl_no=?";
//
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(dobj.getPermanentAppno(), "AN")) {
//				ps.setString(1, dobj.getPermanentAppno());
//			}
//			if (POSValidator.validate(dobj.getEntry_ip(), "IP")) {
//				ps.setString(2, dobj.getEntry_ip());
//			}
//			
//			if (POSValidator.validate(dobj.getAppl_no(), "AN")) {
//				ps.setString(3, dobj.getAppl_no());
//			}
//			int i = ps.executeUpdate();
//			if (i > 0) {
//				sql = "UPDATE onlineschema.va_details_appl "
//						+ "SET entry_status=?, confirm_ip=?, appl_no = ? WHERE appl_no=?";
//				ps = tmgr.prepareStatement(sql);
//				if (POSValidator.validate(dobj.getEntry_status(), "A")) {
//					ps.setString(1, dobj.getEntry_status());
//				}
//
//				if (POSValidator.validate(dobj.getEntry_ip(), "IP")) {
//					ps.setString(2, dobj.getEntry_ip());
//				}
//				if (POSValidator.validate(dobj.getPermanentAppno(), "AN")) {
//					ps.setString(3, dobj.getPermanentAppno());
//				}
//				if (POSValidator.validate(dobj.getAppl_no(), "AN")) {
//					ps.setString(4, dobj.getAppl_no());
//				}
//				return ps.executeUpdate();
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Saving status appl detials failed.");
//		}
//		return 0;
//	}
//
//	public static void inVA_StatusfromVA_Status_Appl(TransactionManager tmgr, Status_dobj dobj) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		try {
//			sql = "INSERT INTO vahan4.va_status(" + " state_cd, off_cd, appl_no, pur_cd, flow_slno, file_movement_slno,"
//					+ " action_cd, seat_cd, cntr_id, status, office_remark, public_remark,file_movement_type, "
//					+ " emp_cd, op_dt)" + " SELECT state_cd, off_cd, ?, pur_cd, ?, 1, "
//					+ " ?, seat_cd, cntr_id, ?, office_remark, public_remark, file_movement_type, "
//					+ " emp_cd, CURRENT_TIMESTAMP FROM onlineschema.va_status_appl where appl_no=?";
//
//			ps = tmgr.prepareStatement(sql);
//
//			if (POSValidator.validate(dobj.getPermanentAppno(), "AN")) {
//				ps.setString(1, dobj.getPermanentAppno());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getFlow_slno()), "N")) {
//				ps.setInt(2, dobj.getFlow_slno());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getAction_cd()), "N")) {
//				ps.setInt(3, dobj.getAction_cd());
//			}
//			if (POSValidator.validate(dobj.getStatus(), "A")) {
//				ps.setString(4, dobj.getStatus());
//			}
//			if (POSValidator.validate(dobj.getAppl_no(), "AN")) {
//				ps.setString(5, dobj.getAppl_no());
//			}
//			int i = ps.executeUpdate();
//			if (i > 0) {
//				sql = "UPDATE onlineschema.va_status_appl"
//						+ " SET flow_slno = flow_slno+1, status=?, op_dt=CURRENT_TIMESTAMP, moved_from_online=?, appl_no=?, action_cd =?  WHERE appl_no=?";
//				ps = tmgr.prepareStatement(sql);
//
//				if (POSValidator.validate(dobj.getStatus(), "A")) {
//					ps.setString(1, dobj.getStatus());
//				}
//				if (POSValidator.validate(dobj.getMoved_from_online(), "A")) {
//					ps.setString(2, dobj.getMoved_from_online());
//				}
//				if (POSValidator.validate(dobj.getPermanentAppno(), "AN")) {
//					ps.setString(3, dobj.getPermanentAppno());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.getAction_cd()), "N")) {
//					ps.setInt(4, dobj.getAction_cd());
//				}
//				if (POSValidator.validate(dobj.getAppl_no(), "AN")) {
//					ps.setString(5, dobj.getAppl_no());
//				}
//
//				if (ps.executeUpdate() <= 0) {
//					throw new VahanException("Saving status appl detials failed.");
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Saving status appl detials failed.");
//		}
//	}
//
//	public static Date getPermitAuthValidDate(Date date[]) {
//		Date current_pointer = date[0];
//		if (null == current_pointer) {
//			for (int i = 0; i < date.length; i++) {
//				if (null != date[i]) {
//					current_pointer = date[i];
//					break;
//				}
//			}
//
//		}
//		for (int i = 0; i < date.length; i++) {
//			int bal = 0;
//			if (null != date[i]) {
//				bal = current_pointer.compareTo(date[i]);
//			}
//			
//			if (bal > 0) {
//				current_pointer = date[i];
//			}
//		}
//		return current_pointer;
//	}
//
//	public static int getVehAge(VehicleParameters vehParameters, String state_cd, int off_cd) throws VahanException {
//		boolean isVehValidityExpired = false;
//		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//		Date today = new Date();
//		Date currentDate = null;
//		int regValidityYear = 0;
//		int vehAge = 0;
//
//		try {
//			currentDate = formatter.parse(formatter.format(today));
//			regValidityYear = getRegValidityYear(vehParameters, state_cd);
//			vehAge = new FitnessImpl().getVehAgeValidity(state_cd, vehParameters.getPERMIT_TYPE(),
//					vehParameters.getPERMIT_SUB_CATG(), vehParameters.getFUEL());
//
//			
//			if (regValidityYear > 0 && state_cd.equalsIgnoreCase("MH")
//					&& (off_cd == 1 || off_cd == 2 || off_cd == 3 || off_cd == 47)) {
//				if (vehParameters.getFUEL() == 6 || vehParameters.getFUEL() == 3) {
//					regValidityYear = 15;
//				} else {
//					regValidityYear = 8;
//				}
//			}
//			if (regValidityYear == 0 || (vehAge != 0 && vehAge < regValidityYear)) {
//				regValidityYear = vehAge;
//			}
//			
//		} catch (VahanMessageException vme) {
//			LOGGER.error(vme.getMessage());
//			throw new VahanException(vme.getMessage());
//		} catch (Exception ex) {
//			// ex.printStackTrace();
//			LOGGER.error(ex.getMessage());
//			throw new VahanException(
//					"Something Went Wrong During Vehicle Age Calculation for Expirity, Please Contact to the Administrator.");
//		}
//		return regValidityYear;
//	}
//
//	public static String getChasiNo(String regn_no) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String sql = null;
//		RowSet rs = null;
//		String chasi_no = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("get full chasi no from regn no");
//			String query = "select chasi_no from vt_owner WHERE status not in ('N','C') and regn_no = ?";
//			ps = tmgr.prepareStatement(query);
//			if (POSValidator.validate(regn_no, "AN")) {
//				ps.setString(1, regn_no);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			while (rs.next()) {
//				chasi_no = rs.getString("chasi_no");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Something went wrong while fetching the chasi no");
//			// TODO: handle exception
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//			}
//		}
//
//		return chasi_no;
//	}
//
//	public static int getRegValidityYear(VehicleParameters vehParameters, String state_cd) throws VahanException {
//
//		int regUpto = 0;
//		PreparedStatement ps = null;
//		Exception e = null;
//        String sql = null;
//		TransactionManagerInterface iTmgr = null;
//		try {
//			
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				iTmgr = new TransactionManagerReadOnly("CommonServerImpl.getRegValidityYear()");
//			} else {
//				iTmgr = new TransactionManager("CommonServerImpl.getRegValidityYear()");
//			}
//			sql = "SELECT new_value,condition_formula FROM " + TableList.VM_VALIDITY_MAST
//					+ " WHERE pur_cd=? and state_cd=?";
//			ps = iTmgr.prepareStatement(sql);
//			int pur_cd = vehParameters.getPUR_CD();
//			if (pur_cd != 0)
//				ps.setInt(1, pur_cd);
//			else
//				ps.setInt(1, 0);// for all purpose
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(2, state_cd);
//			}
//			RowSet rs = iTmgr.fetchDetachedRowSet_No_release();
//
//			while (rs.next()) {
//				if (isCondition(replaceTagValues(rs.getString("condition_formula"), vehParameters),
//						"getRegValidityYear")) {
//					regUpto = rs.getInt("new_value");
//				}
//			}
//
//		} catch (SQLException sq) {
//			LOGGER.error(e);
//			e = sq;
//		} finally {
//			try {
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//			} catch (Exception ex) {
//				LOGGER.error(ex);
//			}
//		}
//
//		if (e != null) {
//			throw new VahanMessageException("Error in Getting Validity Upto Date");
//		}
//
//		return regUpto;
//	}
//
//	
//
//	public static String getPermitPrintPur_cd(String state_cd, int off_cd) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		String printPur_cd = null;
//		RowSet rs = null;
//		String sql = null;
//		sql = "select permit_print_purcd from onlinepermit.tm_off_configuration where state_cd = ? and off_cd = ? ";
//
//		try {
//			tmgr = new TransactionManagerReadOnly("getPermitPrintPur_cd");
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(i++, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				ps.setInt(i, off_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				printPur_cd = rs.getString("permit_print_purcd");
//			}
//		} catch (Exception ex) {
//			throw new VahanException("Unable to Get Permit PrintPermission..");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception ee) {
//				LOGGER.error(ee);
//			}
//		}
//
//		return printPur_cd;
//	}
//
//	private static Map<String, List<SelectItem>> getStates2() {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		Map<String, List<SelectItem>> stateData = new HashMap<>();
//
//		
//		String stateSql = "select state.state_code as state_cd, state.descr as state_name, offi.off_cd, offi.off_name"
//				+ " from onlinepermit.tm_off_configuration a" + " inner join tm_state state on state_code = a.state_cd"
//				+ " inner join  tm_office offi on a.state_cd = offi.state_cd and offi.off_cd =a.off_cd"
//				+ " where a.freshwithoutvehicle = true order by 1,4";
//
//		try {
//			tmgr = new TransactionManagerReadOnly("getStates");
//			ps = tmgr.prepareStatement(stateSql);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			String state_cd = null;
//			int maxrows = rs.getMaxRows();
//
//			while (rs.next()) {
//				state_cd = rs.getString("state_cd").trim();
//				ArrayList<SelectItem> al = new ArrayList<>();
//				do {
//					if (state_cd.equals(rs.getString("state_cd").trim())) {
//						al.add(new SelectItem(rs.getString("off_cd"), rs.getString("off_name")));
//						if (rs.isLast() && !al.isEmpty()) {
//							maxrows = rs.getRow();
//						}
//					} else {
//						break;
//					}
//				} while (rs.next());
//				
//				if (rs.getRow() - 1 < 0) {
//					
//					rs.absolute(maxrows);
//				} else {
//					rs.absolute(rs.getRow() - 1);
//				}
//				if (rs.getRow() > 0) {
//					if (!al.isEmpty()) {
//						stateData.put(rs.getString("state_cd"), al);
//					}
//					state_list.add(new SelectItem(rs.getString("state_cd"), rs.getString("state_name")));
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e);
//		} catch (Exception e) {
//			LOGGER.error(e);
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e);
//				}
//
//			}
//		}
//		return stateData;
//	}
//
//	public static int updateOnlineTempPermit(boolean withregno, Boolean permPermitexps, Boolean onlinePermitexpires,
//			String appl_no, String regn_no) throws VahanException {
//
//		TransactionManager tmgr = null;
//		PreparedStatement ps = null;
//		String printPur_cd = null;
//		RowSet rs = null;
//		String sql = null;
//		int res = 0;
//		if (withregno) {
//			sql = " update onlinepermit.va_temp_insurance set appl_no = ? where regn_no = ? and  policy_no = (select policy_no from onlinepermit.va_temp_insurance where regn_no = ? order by op_dt desc limit 1)";
//
//			try {
//				tmgr = new TransactionManager("updateOnlineTempPermit");
//				ps = tmgr.prepareStatement(sql);
//				int i = 1;
//				if (POSValidator.validate(appl_no, "AN")) {
//					ps.setString(i++, appl_no);
//				}
//				if (POSValidator.validate(regn_no, "ANS")) {
//					ps.setString(i++, regn_no);
//				}
//				if (POSValidator.validate(regn_no, "ANS")) {
//					ps.setString(i++, regn_no);
//				}
//				res = ps.executeUpdate();
//				tmgr.commit();
//
//			} catch (Exception ex) {
//				throw new VahanException("Unable to Update Permit Insurance details.");
//			} finally {
//				try {
//					if (tmgr != null) {
//						tmgr.release();
//					}
//				} catch (Exception ee) {
//					LOGGER.error(ee);
//				}
//			}
//		}
//		return res;
//	}
//
//	public static int updateOnlineTempPermitBeforePayment(boolean withregno, String appl_no, String regn_no)
//			throws VahanException {
//
//		TransactionManager tmgr = null;
//		PreparedStatement ps = null;
//		String printPur_cd = null;
//		RowSet rs = null;
//		String sql = null;
//		int res = 0;
//		if (withregno) {
//			sql = " update onlinepermit.va_temp_insurance set appl_no = ? where regn_no = ? and  policy_no = (select policy_no from onlinepermit.va_temp_insurance where regn_no = ? order by op_dt desc limit 1)";
//
//			try {
//				tmgr = new TransactionManager("updateOnlineTempPermit");
//				ps = tmgr.prepareStatement(sql);
//				int i = 1;
//				if (POSValidator.validate(appl_no, "AN")) {
//					ps.setString(i++, appl_no);
//				}
//				if (POSValidator.validate(regn_no, "ANS")) {
//					ps.setString(i++, regn_no);
//				}
//				if (POSValidator.validate(regn_no, "ANS")) {
//					ps.setString(i++, regn_no);
//				}
//				res = ps.executeUpdate();
//				tmgr.commit();
//
//			} catch (Exception ex) {
//				throw new VahanException("Unable to Update Permit Insurance details.");
//			} finally {
//				try {
//					if (tmgr != null) {
//						tmgr.release();
//
//					}
//				} catch (Exception ee) {
//					LOGGER.error(ee);
//				}
//			}
//		}
//		return res;
//	}
//
//	public static boolean checkOnlineInsinside(boolean withregno, String regn_no, String chasi_no) {
//		Boolean oncheckins = null;
//		if (withregno) {
//
//			try {
//				oncheckins = LoginImpl.checkInsuranceDetails(regn_no, chasi_no);
//				if (oncheckins == null) {
//					oncheckins = true;
//				}
//			} catch (VahanException e) {
//				// TODO Auto-generated catch block
//				LOGGER.error(e.getMessage());
//			}
//
//		} else {
//			oncheckins = false;
//		}
//		return oncheckins;
//
//	}
//
//	public static boolean checkInsInVaTemp(String regn_no, String appl_no, TransactionManager tmgr)
//			throws VahanException {
//
//		PreparedStatement ps = null;
//		String printPur_cd = null;
//		RowSet rs = null;
//		String sql = null;
//		boolean doUpdate = false;
//		int res = 0;
//
//		sql = " select * from  onlinepermit.va_temp_insurance  where appl_no = ? and regn_no =? ";
//
//		try {
//			// tmgr = new TransactionManager("checkInsInVaTemp");
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			if (POSValidator.validate(appl_no, "AN")) {
//				ps.setString(i++, appl_no);
//			}
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(i++, regn_no);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				doUpdate = true;
//			}
//
//		} catch (Exception ex) {
//			throw new VahanException("Unable to Update Permit Insurance details.");
//		}
//
//		return doUpdate;
//
//	}
//
//	public static void redirectToVahanservice() {
//		try {
//			String vahanserviceUrl = "";
//			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (isTestingEnviroment) {
//				vahanserviceUrl = "http://164.100.78.110/vahanservice/vahan/ui/usermgmt/login.xhtml";
//			} else {
//				vahanserviceUrl = ApplicationConfig.PARIVAHAN + "/vahanservice/vahan/ui/usermgmt/login.xhtml";
//			}
//			if (externalContext != null && !CommonUtils.isNullOrBlank((vahanserviceUrl))) {
//
//				externalContext.redirect(vahanserviceUrl);
//			}
//			LOGGER.info("inside redirectToVahanservice" + vahanserviceUrl);
//		} catch (IOException | VahanException ve) {
//			// TODO Auto-generated catch block
//			LOGGER.error(ve.getMessage());
//		}
//	}
//
//	public static String[][] getRouteRegionPermitAllowedDtls(String state_cd, int permit_rto, int fuel, int vehClass,
//			Date purchase_dt, int seat_cap, int pur_cd, int owner_cd) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		ResultSet rs = null;
//		boolean result = false;
//		String[][] data = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getPermitAllowedDtls");
//			String sql = "select a.permitted_vehicle_id,a.pur_cd,a.permit_type,unnest(string_to_array(a.permit_catg,',')) as permit_catg,"
//					+ " string_agg(distinct z.veh_permitted_area,',') as permitted_area, string_agg(distinct z.veh_permitted_route,',') as permitted_route"
//					+ " from onlinepermit.tm_permit_type_allowed a" + " inner join ("
//					+ " select b.* from  onlinepermit.tm_veh_permit_permitted b" + " where" + " b.state_cd = ? and"
//					+ " b.off_cd = ? and" + " b.pur_cd = ? and"
//					+ " ?::text = any(String_to_array(b.fuel_type::text,',')) and"
//					+ " case when veh_class = 'ALL' then true else ?::text = any(String_to_array(veh_class,',')) end and"
//					+ " ((?::date + (b.veh_age||' YEAR')::interval)-interval '1' DAY)::date >= current_date and"
//					+ " case when seating_capacity is null then true else ? = seating_capacity end and"
//					+ " case when veh_class_not_allowed = 'ALL' OR ? ::text = any(String_to_array(veh_class_not_allowed,',')) then false else true end and"
//					+ " case when b.veh_permitted_ownership = 'ALL' then true else ? ::text = any(String_to_array(b.veh_permitted_ownership,',')) end"
//					+ " order by b.pur_cd,b.veh_age"
//					+ " )z  on z.permitted_vehicle_id = a.permitted_vehicle_id and z.pur_cd = a.pur_cd group by 1,2,3,4 order by 1,2,3,4";
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			ps.setString(i++, state_cd);
//			ps.setInt(i++, permit_rto);
//			ps.setInt(i++, pur_cd);
//			ps.setInt(i++, fuel);
//			ps.setInt(i++, vehClass);
//			ps.setDate(i++, new java.sql.Date(purchase_dt.getTime()));
//			ps.setInt(i++, seat_cap);
//			ps.setInt(i++, vehClass);
//			ps.setInt(i, owner_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			String pmt_type = ",";
//			int totalColumn = rs.getMetaData().getColumnCount();
//			if (rs.last()) {
//				data = new String[rs.getRow()][totalColumn];
//				rs.beforeFirst();
//			}
//
//			while (rs.next()) {
//				for (int j = 1; j <= totalColumn; j++) {
//					data[rs.getRow() - 1][j - 1] = rs.getString(j);
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error("Error occurs from getPermitAllowedDtls for purpose = " + pur_cd + " " + e.getMessage());
//			data = null;
//			throw new VahanException("Some problem occurred while validating your vehicle scenario.");
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(
//							"Error occurs from getPermitAllowedDtls for purpose = " + pur_cd + " " + e.getMessage());
//				}
//				if (rs != null) {
//					rs = null;
//				}
//			}
//		}
//
//		return data;
//	}
//
//	public static byte[] getKeyBytes(String key) throws UnsupportedEncodingException {
//		byte[] keyBytes = new byte[16];
//		byte[] parameterKeyBytes = key.getBytes(characterEncoding);
//		System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
//		return keyBytes;
//	}
//
//	public static String encrypt(String plainText, String key) throws VahanException {
//		try {
//			byte[] plainTextbytes = plainText.getBytes(characterEncoding);
//			byte[] keyBytes = getKeyBytes(key);
//
//			return Base64.encodeBase64String(encrypt(plainTextbytes, keyBytes, keyBytes));
//
//			
//
//		} catch (Exception e) {
//
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Some problem occurred while encripting your object.");
//		}
//
//	}
//
//	public static byte[] encrypt(byte[] plainText, byte[] key, byte[] initialVector) throws VahanException {
//		try {
//			Cipher cipher = Cipher.getInstance(cipherTransformation);
//			SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithm);
//			IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
//			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
//			plainText = cipher.doFinal(plainText);
//			return plainText;
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Some problem occurred while encripting your object.");
//		}
//
//	}
//
//	public static void logout(String regn_no, String mobile_no, Map<String, String> eDistrictReqData)
//			throws VahanException {
//		try {
//			HttpSession session = Util.getSession();
//			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
//					.getRequest();
//
//			String ipAddress = request.getRemoteAddr();
//
//			LoginImpl.doAuditTrailDetail(regn_no, mobile_no, ipAddress,
//					MsgProperties.getKeyValue("logout.success.actiontype"),
//					MsgProperties.getKeyValue("audit.trail.status.success"), request.getSession().getId(),
//					eDistrictReqData, false);
//			new LoginImpl().setUserStatus(mobile_no, "S", regn_no);
//			if (session != null) {
//				session.invalidate();
//			}
//			// LOGGER.info("inside invalidate session at logout");
//		}
//
//		catch (VahanException ex) {
//			LOGGER.error(ex.getMessage());
//			throw ex;
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to  logout successfully ..");
//		}
//	}
//
//	
//
//	public static String getPermissionToRedirectEdistrict(String trans_id, String regn_no) throws VahanException {
//		boolean permission = false;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		String Sql = "";
//		RowSet rs;
//		String returnURL = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getPermissionToRedirect");
//			Sql = "select return_response_url from  " + TableList.VH_AUDIT_TRAIL
//					+ " where email_id = ? and return_response_url <> 'VahanService' and appl_no =? ";
//
//			ps = tmgr.prepareStatement(Sql);
//
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//
//			if (POSValidator.validate(trans_id, "AN")) {
//				ps.setString(2, trans_id);
//			}
//			
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				returnURL = rs.getString("return_response_url");
//				
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Something Went wrong during getPermissionToRedirect  Edistrict...");
//		} finally {
//			try {
//				if (ps != null || tmgr != null) {
//					ps.close();
//					ps = null;
//					tmgr.release();
//				}
//			} catch (SQLException e) {
//				LOGGER.error(e.getMessage());
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return returnURL;
//	}
//
//	public static InsuranceInfoDobj getDataFromInsuranceService(String regn_no) throws VahanException {
//		Unmarshaller unmarshaller = null;
//		JAXBContext jc = null;
//		InsuranceInfoDobj insuranceInfoRes = null;
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment && !regn_no.equalsIgnoreCase("NEW")) {
//				DataportwsProxy dataIrdaObj = new DataportwsProxy();
//				String data = dataIrdaObj.getInsuranceInfo(regn_no);
//				if (!CommonUtils.isNullOrBlank(data) && data.contains("<?xml")) {
//					jc = JAXBContext.newInstance(InsuranceInfoDobj.class);
//					unmarshaller = jc.createUnmarshaller();
//					StreamSource streamSource = new StreamSource(new StringReader(data));
//					Object response = unmarshaller.unmarshal(streamSource);
//					if (response != null && response instanceof InsuranceInfoDobj) {
//						insuranceInfoRes = (InsuranceInfoDobj) response;
//
//						LOGGER.info("insurance details from service for this regn_no " + regn_no
//								+ insuranceInfoRes.getInsFrom() + " " + insuranceInfoRes.getInsUpto() + " "
//								+ insuranceInfoRes.getInsuranceType() + " " + insuranceInfoRes.getIssuerCd() + " "
//								+ insuranceInfoRes.getPolicyNo());
//
//					}
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error("INSurance service error" + regn_no + e.getMessage());
//			throw new VahanException("Unable to contact Insurance Web Service.Please try after Sometimes... ");
//		}
//		return insuranceInfoRes;
//	}
//
//	public static Map<String, Integer> calculateAge(Date birthDate, Date uptoDate) {
//		int years = 0;
//		int months = 0;
//		int days = 0;
//		// create calendar object for birth day
//		Calendar birthDay = Calendar.getInstance();
//		birthDay.setTimeInMillis(birthDate.getTime());
//		// create calendar object for current day
//		// long currentTime = System.currentTimeMillis();
//		Calendar now = Calendar.getInstance();
//		now.setTimeInMillis(uptoDate.getTime());
//		// Get difference between years
//		years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
//		int currMonth = now.get(Calendar.MONTH) + 1;
//		int birthMonth = birthDay.get(Calendar.MONTH) + 1;
//		// Get difference between months
//		months = currMonth - birthMonth;
//		// if month difference is in negative then reduce years by one and
//		// calculate the number of months.
//		if (months < 0) {
//			years--;
//			months = 12 - birthMonth + currMonth;
//			if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
//				months--;
//		} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
//			years--;
//			months = 11;
//		}
//		// Calculate the days
//		if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
//			days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
//		else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
//			int today = now.get(Calendar.DAY_OF_MONTH);
//			now.add(Calendar.MONTH, -1);
//			days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
//		} else {
//			days = 0;
//			if (months == 12) {
//				years++;
//				months = 0;
//			}
//		}
//		HashMap<String, Integer> ageMap = new HashMap<String, Integer>();
//		ageMap.put("YEARS", years);
//		ageMap.put("MONTHS", months);
//		ageMap.put("DAYS", days);
//		return ageMap;
//		// Create new Age object
//		// return new Age(days, months, years);
//	}
//
//	public static boolean isApproved(String state_cd, String appl_no) throws VahanException {
//		TransactionManagerReadOnly tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String sql = null;
//		boolean isApproved = false;
//		sql = "select entry_status='A' as isApproved from va_details a where a.state_cd = ? and a.appl_no = ? order by entry_status";
//		try {
//			tmgr = new TransactionManagerReadOnly("isApproved");
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(i++, state_cd);
//			}
//			if (POSValidator.validate(appl_no, "ANS")) {
//				ps.setString(i++, appl_no);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				isApproved = rs.getBoolean("isApproved");
//			}
//
//		} catch (SQLException ex) {
//			throw new VahanException("Unable to get Details for Morth fee user.");
//		} catch (Exception ex) {
//			throw new VahanException("Unable to get Details for Morth fee user.");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//
//				}
//			} catch (Exception ee) {
//				LOGGER.error(ee.getMessage());
//			}
//		}
//		return isApproved;
//	}
//
//	public static boolean validToPayMorthFee(String state_cd, String regn_no, int pur_cd) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String sql = null;
//		boolean npValid = false;
//		sql = "select f.pmt_type from onlineschema.va_details_appl a "
//				+ " inner join onlineschema.va_status_appl b on a.state_cd = b.state_cd and b.appl_no = a.appl_no and a.pur_cd = b.pur_cd "
//				+ " inner join va_status c on a.state_cd = c.state_cd and c.appl_no = a.appl_no and a.pur_cd = c.pur_cd "
//				+ " inner join va_details d on a.state_cd = d.state_cd and d.appl_no = a.appl_no and a.pur_cd = d.pur_cd and a.regn_no = d.regn_no "
//				+ " inner join onlinepermit.va_permit_home_auth e on a.appl_no = e.appl_no and e.regn_no = a.regn_no "
//				+ " inner join permit.vt_permit f on f.state_cd = a.state_cd and f.regn_no = a.regn_no and e.pmt_no =  f.pmt_no "
//				+ " inner join permit.va_np_detail g on g.state_cd = a.state_cd and g.off_cd = a.off_cd and g.appl_no = a.appl_no and g.regn_no = a.regn_no and g.pmt_no = f.pmt_no and g.permit_type = f.pmt_type "
//				+ " where a.state_cd = ? and a.regn_no = ? and a.pur_cd = ? and f.pmt_type = ? order by a.appl_dt desc ";
//
//		try {
//			tmgr = new TransactionManagerReadOnly("updateOnlineTempPermit");
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(i++, state_cd);
//			}
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(i++, regn_no);
//			}
//			ps.setInt(i++, TableConstants.VM_PMT_RENEWAL_HOME_AUTH_PERMIT_PUR_CD);
//			ps.setInt(i, TableConstants.NATIONAL_PERMIT_TYPE);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			npValid = rs.next();
//			if (!npValid) {
//				sql = null;
//				ps = null;
//				rs = null;
//				sql = "select vap.pmt_type from onlineschema.va_details_appl a  inner join onlineschema.va_status_appl"
//						+ " b on a.state_cd = b.state_cd and b.appl_no = a.appl_no and a.pur_cd = b.pur_cd "
//						+ " inner join va_status c on a.state_cd = c.state_cd and c.appl_no = a.appl_no and a.pur_cd = c.pur_cd "
//						+ " inner join va_details d on a.state_cd = d.state_cd and d.appl_no = a.appl_no and a.pur_cd = d.pur_cd "
//						+ " and a.regn_no = d.regn_no   inner join onlinepermit.va_permit vap on vap.state_cd =a.state_cd and "
//						+ " vap.appl_no =a.appl_no inner join permit.va_np_detail g on g.state_cd = a.state_cd and g.off_cd = a.off_cd"
//						+ " and g.appl_no = a.appl_no and g.regn_no = a.regn_no and  g.permit_type = vap.pmt_type"
//						+ "  where a.state_cd = ? and a.regn_no = ? and a.pur_cd = ? and vap.pmt_type = ? order by a.appl_dt desc ";
//
//				ps = tmgr.prepareStatement(sql);
//				int ii = 1;
//				if (POSValidator.validate(state_cd, "A")) {
//					ps.setString(ii++, state_cd);
//				}
//				if (POSValidator.validate(regn_no, "ANS")) {
//					ps.setString(ii++, regn_no);
//				}
//				ps.setInt(ii++, pur_cd);
//				ps.setInt(ii, TableConstants.NATIONAL_PERMIT_TYPE);
//				rs = tmgr.fetchDetachedRowSet();
//				npValid = rs.next();
//
//			}
//
//		} catch (SQLException ex) {
//			throw new VahanException("Unable to get Details for Morth fee user.");
//		} catch (Exception ex) {
//			throw new VahanException("Unable to get Details for Morth fee user.");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//
//				}
//			} catch (Exception ee) {
//				LOGGER.error(ee.getMessage());
//			}
//		}
//		return npValid;
//	}
//
//	public static boolean getappoinmentEnbleCond(String state_cd, PermitFeeRecieptDobj rcpt_dobj) {
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		boolean appointment_enable_cond = false;
//		String whereIam = "ServerUtils.getappoinmentEnbleCond";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			sql = "select appointment_enable_cond from onlinepermit.tm_online_state_configuration where state_cd = ? ";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd, "AN")) {
//				ps.setString(1, state_cd);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				String app_enable_cond = rs.getString("appointment_enable_cond");
//
//				if (!CommonUtils.isNullOrBlank(app_enable_cond) && app_enable_cond.equalsIgnoreCase("TRUE")) {
//					appointment_enable_cond = true;
//				} else if (!CommonUtils.isNullOrBlank(app_enable_cond) && !app_enable_cond.equalsIgnoreCase("FALSE")) {
//					VehicleParameters vp = new VehicleParameters();
//					vp.setPUR_CD(rcpt_dobj.getPurpose());
//					vp.setREGN_NO(rcpt_dobj.getRegn_no());
//					appointment_enable_cond = FormulaUtils.isCondition(
//							FormulaUtils.replaceTagValues(app_enable_cond, vp),
//							("Server Util (appointment enable cond) for state : " + state_cd));
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return appointment_enable_cond;
//	}
//
//	public static void getOwnerDetailsInVehicleParameters(VehicleParameters parameters) throws VahanException {
//		TransactionManagerReadOnly tmgr = null;
//		PreparedStatement ps = null;
//
//		RowSet rs = null;
//		String sql = null;
//
//		sql = "select *,vvc.class_type,vvc.transport_catg from vv_owner vto "
//				+ "inner join vm_vh_class vvc on vto.vh_class = vvc.vh_class"
//				+ " where vto.state_cd = ? and vto.regn_no = ? and vvc.class_type = 1";
//
//		try {
//			tmgr = new TransactionManagerReadOnly("getOwnerDetailsInVehicleParameters");
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			if (POSValidator.validate(parameters.getSTATE_CD(), "AN")) {
//				ps.setString(i++, parameters.getSTATE_CD());
//			}
//			
//			if (POSValidator.validate(parameters.getREGN_NO(), "ANS")) {
//				ps.setString(i++, parameters.getREGN_NO());
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				parameters.setOFF_CD(rs.getInt("off_cd"));
//				parameters.setREGN_DATE(rs.getString("regn_dt"));
//				parameters.setPURCHASE_DATE(rs.getString("purchase_dt"));
//				parameters.setSEAT_CAP(rs.getInt("seat_cap"));
//				parameters.setSTAND_CAP(rs.getInt("stand_cap"));
//				parameters.setVH_CLASS(rs.getInt("vh_class"));
//				parameters.setREGN_TYPE(rs.getString("regn_type"));
//				parameters.setUNLD_WT(rs.getInt("unld_wt"));
//				parameters.setLD_WT(rs.getInt("ld_wt"));
//				parameters.setFUEL(rs.getInt("fuel"));
//				parameters.setVCH_TYPE(rs.getInt("class_type"));
//				parameters.setVCH_CATG(rs.getString("vch_catg"));
//				parameters.setTRANSPORT_CATG(rs.getString("transport_catg"));
//				parameters.setOWNER_CD(rs.getInt("owner_cd"));
//
//			}
//		} catch (SQLException ex) {
//			throw new VahanException("Unable to get vehicle Details  .");
//		} catch (Exception ex) {
//			throw new VahanException("Unable to get vehicle Details .");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//
//				}
//			} catch (Exception ee) {
//				LOGGER.error(ee);
//			}
//		}
//	}
//
//	public static void getNewOwnerDetailsInVehicleParameters(VehicleParameters parameters, String applNo)
//			throws VahanException {
//		TransactionManagerReadOnly tmgr = null;
//		PreparedStatement ps = null;
//
//		RowSet rs = null;
//		String sql = null;
//
//		sql = "select state_cd, off_cd, appl_no, regn_no, vvc.vh_class, seat_cap, unld_wt, ld_wt, vch_catg, fuel, stand_cap, owner_ctg as owner_cd"
//				+ ",vvc.class_type,coalesce(vvc.transport_catg,'') as transport_catg from "
//				+ TableList.VA_PERMIT_OWNER_ONLINE + " vto "
//				+ "inner join vm_vh_class vvc on vto.vh_class = vvc.vh_class"
//				+ " where vto.state_cd = ? and vto.regn_no = ? and (vto.appl_no = ? or vto.appl_no = ?) and vvc.class_type = 1";
//		try {
//			tmgr = new TransactionManagerReadOnly("getNewOwnerDetailsInVehicleParameters");
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			if (POSValidator.validate(parameters.getSTATE_CD(), "AN")) {
//				ps.setString(i++, parameters.getSTATE_CD());
//			}
//			
//			if (POSValidator.validate(parameters.getREGN_NO(), "ANS")) {
//				ps.setString(i++, parameters.getREGN_NO());
//			}
//			ps.setString(i++, ServerUtil.formatTransID(applNo, 'T'));
//			ps.setString(i, ServerUtil.formatTransID(applNo, 'P'));
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				parameters.setOFF_CD(rs.getInt("off_cd"));
//				parameters.setSEAT_CAP(rs.getInt("seat_cap"));
//				parameters.setSTAND_CAP(rs.getInt("stand_cap"));
//				parameters.setVH_CLASS(rs.getInt("vh_class"));
//				parameters.setREGN_TYPE("N");
//				parameters.setUNLD_WT(rs.getInt("unld_wt"));
//				parameters.setLD_WT(rs.getInt("ld_wt"));
//				parameters.setFUEL(rs.getInt("fuel"));
//				parameters.setVCH_TYPE(rs.getInt("class_type"));
//				parameters.setVCH_CATG(rs.getString("vch_catg"));
//				parameters.setTRANSPORT_CATG(rs.getString("transport_catg"));
//				parameters.setOWNER_CD(rs.getInt("owner_cd"));
//
//			}
//		} catch (SQLException ex) {
//			throw new VahanException("Unable to get vehicle Details  .");
//		} catch (Exception ex) {
//			throw new VahanException("Unable to get vehicle Details .");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//
//				}
//			} catch (Exception ee) {
//				LOGGER.error(ee);
//			}
//		}
//	}
//
//	public static void getGreenAndEnvTax(VehicleParameters greenTaxVehicle, String state_cd, int off_cd,
//			String pur_cd_to_be_check, TransactionManager tmgr) throws VahanException {
//		String condition_formula = "";
//		OtherNecessaryDetailsDOBJ otherNecessaryDetailsDOBJ = null;
//		String initial_due_date = null;
//		String mods = null;
//		RowSet rs = null;
//		PreparedStatement ps = null;
//		boolean oldTmgr = true;
//		try {
//			if (!CommonUtils.isNullOrBlank(pur_cd_to_be_check)) {
//				if (null == tmgr) {
//					tmgr = new TransactionManager("getGreenAndEnvTax");
//					oldTmgr = false;
//				}
//				String sql = "select sr_no,condition_formula,pur_cd,initial_due_date,to_char(effective_date, 'DD-Mon-YYYY') as effective_date,mods from vm_allowed_mods where state_cd = ? and  pur_cd in(?) order by  sr_no ";
//
//				ps = tmgr.prepareStatement(sql);
//				int i = 1;
//
//				if (POSValidator.validate(state_cd, "A")) {
//					ps.setString(i++, state_cd);
//				}
//				if (POSValidator.validate(pur_cd_to_be_check, "ADD")) {
//					ps.setInt(i, Integer.valueOf(pur_cd_to_be_check));
//				}
//				rs = tmgr.fetchDetachedRowSet_No_release();
//				while (rs.next()) {
//					condition_formula = rs.getString("condition_formula");
//					if (!CommonUtils.isNullOrBlank(condition_formula)) {
//
//						if (FormulaUtils.isCondition(FormulaUtils.replaceTagValues(condition_formula, greenTaxVehicle),
//								("Server Util (vm_allowed_mods) for state/pur " + state_cd + pur_cd_to_be_check))) {
//							if (!CommonUtils.isNullOrBlank(rs.getString("initial_due_date"))) {
//								
//								initial_due_date = FormulaUtils.getReturnValue(FormulaUtils
//										.replaceTagValues(rs.getString("initial_due_date"), greenTaxVehicle));
//								if (CommonUtils.isNullOrBlank(initial_due_date)) {
//									initial_due_date = rs.getString("effect" + "ive_date");
//								}
//								mods = rs.getString("mods");
//							} else if (CommonUtils.isNullOrBlank(initial_due_date)) {
//								initial_due_date = rs.getString("effective_date");
//							}
//						}
//
//					}
//				}
//				if (!CommonUtils.isNullOrBlank(initial_due_date) && DateUtils.isAfter(DateUtils.getCurrentDate(),
//						DateUtil.convertStringDDMMMYYYYToDDMMYYYY(initial_due_date))) {
//					
//
//					otherNecessaryDetailsDOBJ = getLatestTaxDetails(state_cd, greenTaxVehicle.getREGN_NO(),
//							Integer.valueOf(pur_cd_to_be_check), null, 0, tmgr);
//
//					
//					if (otherNecessaryDetailsDOBJ == null || !DateUtils.isBefore(DateUtils.getCurrentDate(),
//							otherNecessaryDetailsDOBJ.getTaxUpto())) {
//						throw new VahanException("Your " + getTaxHead(Integer.valueOf(pur_cd_to_be_check))
//								+ " Is Pending, Please pay your " + getTaxHead(Integer.valueOf(pur_cd_to_be_check))
//								+ ".");
//						
//					}
//				}
//
//			}
//
//		} catch (VahanException e) {
//			throw e;
//
//		} catch (Exception e) {
//			throw new VahanException("Unable to find out Special/GreenAndEnvTax details");
//
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//	}
//
//	public static OtherNecessaryDetailsDOBJ getLatestTaxDetails(String state_cd, String regn_no, int pur_cd,
//			String exemptedPeriodMode, int exemptedPeriod, TransactionManager tmgr) throws VahanException {
//		OtherNecessaryDetailsDOBJ otherNecessaryDetailsDOBJ = null;
//		RowSet rs = null;
//		PreparedStatement ps = null;
//		try {
//			
//
//			String sql = "select "
//					+ " to_char(tax.tax_from::date,'DD-MM-YYYY')as tax_from, to_char(tax.tax_upto::date,'DD-MM-YYYY')as tax_upto, tax.rcpt_no, tax.tax_mode,  (tax.tax_upto+ (? || ?)::interval)::date >= current_date::date as valid_vt_tax, tax.pur_cd as taxpur, ((tax.rcpt_dt + (? || ?)::interval)::timestamp > COALESCE(((taxcl.op_dt + (? || ?)::interval)::timestamp),((tax.rcpt_dt - interval '1 DAYS')::timestamp))) as vttaxgreater,"
//					+ " to_char(taxcl.clear_fr::date,'DD-MM-YYYY')as clear_fr, to_char(taxcl.clear_to::date,'DD-MM-YYYY')as clear_to, taxcl.tcr_no,  (taxcl.clear_to + (? || ?)::interval)::date >= current_date::date as valid_vt_tax_cl, taxcl.pur_cd as taxclpur, (COALESCE(((tax.rcpt_dt+ (? || ?)::interval)::timestamp),((taxcl.op_dt - interval '1 DAYS')::timestamp)) < (taxcl.op_dt + (? || ?)::interval)::timestamp) as vttaxcleargreater"
//					+ " from VT_OWNER  a" + " left join VT_TAX tax on tax.regn_no = a.regn_no and tax.pur_cd = ?"
//					+ " left join VT_TAX_CLEAR taxcl on taxcl.regn_no = a.regn_no and taxcl.pur_cd = ?"
//					+ " where a.state_cd = ?  and a.regn_no = ?"
//					+ " order by tax.rcpt_dt desc,taxcl.op_dt desc limit 1";
//
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			if (POSValidator.validate(String.valueOf(exemptedPeriod), "N")) {
//				ps.setInt(i++, exemptedPeriod);
//			}
//			if (CommonUtils.isNullOrBlank(exemptedPeriodMode)) {
//				ps.setString(i++, "DAYS");
//			} else {
//				ps.setString(i++, exemptedPeriodMode);
//			}
//			if (POSValidator.validate(String.valueOf(exemptedPeriod), "N")) {
//				ps.setInt(i++, exemptedPeriod);
//			}
//			if (CommonUtils.isNullOrBlank(exemptedPeriodMode)) {
//				ps.setString(i++, "DAYS");
//			} else {
//				ps.setString(i++, exemptedPeriodMode);
//			}
//			if (POSValidator.validate(String.valueOf(exemptedPeriod), "N")) {
//				ps.setInt(i++, exemptedPeriod);
//			}
//			if (CommonUtils.isNullOrBlank(exemptedPeriodMode)) {
//				ps.setString(i++, "DAYS");
//			} else {
//				ps.setString(i++, exemptedPeriodMode);
//			}
//
//			if (POSValidator.validate(String.valueOf(exemptedPeriod), "N")) {
//				ps.setInt(i++, exemptedPeriod);
//			}
//			if (CommonUtils.isNullOrBlank(exemptedPeriodMode)) {
//				ps.setString(i++, "DAYS");
//			} else {
//				ps.setString(i++, exemptedPeriodMode);
//			}
//			if (POSValidator.validate(String.valueOf(exemptedPeriod), "N")) {
//				ps.setInt(i++, exemptedPeriod);
//			}
//			if (CommonUtils.isNullOrBlank(exemptedPeriodMode)) {
//				ps.setString(i++, "DAYS");
//			} else {
//				ps.setString(i++, exemptedPeriodMode);
//			}
//			if (POSValidator.validate(String.valueOf(exemptedPeriod), "N")) {
//				ps.setInt(i++, exemptedPeriod);
//			}
//			if (CommonUtils.isNullOrBlank(exemptedPeriodMode)) {
//				ps.setString(i++, " DAYS");
//			} else {
//				ps.setString(i++, exemptedPeriodMode);
//			}
//			if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//				ps.setInt(i++, pur_cd);
//			}
//			if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//				ps.setInt(i++, pur_cd);
//			}
//
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(i++, state_cd);
//			}
//			if (POSValidator.validate(regn_no, "AN")) {
//				ps.setString(i, regn_no);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//
//				
//				if (rs.getBoolean("valid_vt_tax") && rs.getBoolean("vttaxgreater")) {
//					otherNecessaryDetailsDOBJ = new OtherNecessaryDetailsDOBJ();
//					otherNecessaryDetailsDOBJ.setTaxFrom(rs.getString("tax_from"));
//					otherNecessaryDetailsDOBJ.setTaxUpto(rs.getString("tax_upto"));
//					otherNecessaryDetailsDOBJ.setTaxRcptNo(rs.getString("rcpt_no"));
//					otherNecessaryDetailsDOBJ.setTaxPurDesc(rs.getString("taxpur"));
//				} else if (rs.getBoolean("valid_vt_tax_cl") && rs.getBoolean("vttaxcleargreater")) {
//					otherNecessaryDetailsDOBJ = new OtherNecessaryDetailsDOBJ();
//					otherNecessaryDetailsDOBJ.setTaxFrom(rs.getString("clear_fr"));
//					otherNecessaryDetailsDOBJ.setTaxUpto(rs.getString("clear_to"));
//					otherNecessaryDetailsDOBJ.setTaxRcptNo(rs.getString("tcr_no"));
//					otherNecessaryDetailsDOBJ.setTaxPurDesc(rs.getString("taxclpur"));
//				}
//			}
//
//		} catch (Exception e) {
//			// System.out.println(e);
//			throw new VahanException("Unable to find out Tax details");
//
//		}
//		return otherNecessaryDetailsDOBJ;
//	}
//
//	// this method for green tax please check in fee break up currently not in
//	// use
//	public static OtherNecessaryDetailsDOBJ getLatestFeeDetails(String state_cd, String regn_no, int pur_cd,
//			TransactionManager tmgr) throws VahanException {
//		RowSet rs = null;
//		PreparedStatement ps = null;
//		OtherNecessaryDetailsDOBJ otherNecessaryDetailsDOBJ = null;
//		try {
//			String sql = " SELECT regn_no, payment_mode, fees, fine, rcpt_no, to_char(rcpt_dt ,'DD-MM-YYYY') as rcpt_dt, pur_cd,"
//					+ " flag, collected_by, state_cd, off_cd "
//					+ " FROM vt_fee where state_cd =? and regn_no = ? and pur_cd = ? order by rcpt_dt desc";
//
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(i++, state_cd);
//			}
//			if (POSValidator.validate(regn_no, "AN")) {
//				ps.setString(i++, regn_no);
//			}
//			if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//				ps.setInt(i, pur_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				otherNecessaryDetailsDOBJ = new OtherNecessaryDetailsDOBJ();
//				otherNecessaryDetailsDOBJ.setTaxFrom(rs.getString("rcpt_dt"));
//				otherNecessaryDetailsDOBJ.setTaxRcptNo(rs.getString("rcpt_no"));
//				// otherNecessaryDetailsDOBJ.setTaxUpto(rs.getString("tax_upto"));
//				// otherNecessaryDetailsDOBJ.setTaxMode(rs.getString("tax_mode"));
//			}
//
//		} catch (Exception e) {
//			throw new VahanException("Unable to find out GreenAndEnvTax details");
//
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//		return otherNecessaryDetailsDOBJ;
//	}
//
//
//
//	public static UserAuthorityDobj getAuthrizationDetails(String stateCD, String appl_no, int pur_cd, String regn_no,
//			int pmt_type) throws VahanException {
//		String descr = null;
//		TransactionManagerInterface tmgr = null;
//		UserAuthorityDobj userAuth = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String sql = null;
//		String whereiam = "ServerUtil.getauthrization";
//		if (pur_cd == TableConstants.VM_PMT_TEMP_PUR_CD || pur_cd == TableConstants.VM_PMT_SPECIAL_PUR_CD
//				|| pur_cd == TableConstants.VM_PMT_RENEWAL_TEMP_PUR_CD) {
//			sql = " SELECT tmo.upload_doc,tmo.upload_doc_condition,tmo.upload_doc_iff_mandate_avail,passenger_list_temp,passenger_list_spl,vat.no_of_passenger,tmo.no_fee_purpose,count(vap.sr_no) as passenger_list  FROM "
//					+ TableList.ONLINE_PERMIT_STATE_CONFIGURE + " tmo left outer join "
//					+ TableList.VA_TEMP_PERMIT_ONLINE + " vat on tmo.state_cd = vat.state_cd left outer join "
//					+ TableList.VA_SPL_PASSWENGER_ONLINE
//					+ "  vap on vap.appl_no = vat.appl_no and vat.appl_no = vap.appl_no  " + " left outer join "
//					+ TableList.ONLINE_TM_CONFIGURATION_SPL_PMT + " spl_config on tmo.state_cd = spl_config.state_cd"
//					+ " left outer join " + TableList.ONLINE_TM_CONFIGURATION_TMP_PMT
//					+ " temp_config on tmo.state_cd = temp_config.state_cd"
//					+ " WHERE tmo.state_cd = ? and vat.appl_no =? and vat.regn_no = ? group by 1,2,3,4,5,6,7 ";
//		} else {
//			sql = " SELECT upload_doc,upload_doc_condition,upload_doc_iff_mandate_avail,no_fee_purpose FROM "
//					+ TableList.ONLINE_PERMIT_STATE_CONFIGURE + " WHERE state_cd = ? ";
//		}
//		try {
//			tmgr = new TransactionManagerReadOnly("getauthrization");
//			pstm = tmgr.prepareStatement(sql);
//			if (pur_cd == TableConstants.VM_PMT_TEMP_PUR_CD || pur_cd == TableConstants.VM_PMT_SPECIAL_PUR_CD
//					|| pur_cd == TableConstants.VM_PMT_RENEWAL_TEMP_PUR_CD) {
//
//				if (POSValidator.validate(stateCD, "A")) {
//					pstm.setString(1, stateCD);
//				}
//				if (POSValidator.validate(appl_no, "AN")) {
//					pstm.setString(2, appl_no);
//				}
//				if (POSValidator.validate(regn_no, "ANS")) {
//					pstm.setString(3, regn_no);
//				}
//
//			} else {
//				if (POSValidator.validate(stateCD, "A")) {
//					pstm.setString(1, stateCD);
//				}
//			}
//
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				userAuth = new UserAuthorityDobj();
//				userAuth.setUpload_doc(rs.getBoolean("upload_doc"));
//				VehicleParameters vp = new VehicleParameters();
//				vp.setPUR_CD(pur_cd);
//				vp.setSTATE_CD(stateCD);
//				vp.setREGN_NO(regn_no);
//				vp.setPERMIT_TYPE(pmt_type);
//				if (regn_no.equals("NEW")) {
//					ServerUtil.getNewOwnerDetailsInVehicleParameters(vp, appl_no);
//				} else {
//					ServerUtil.getOwnerDetailsInVehicleParameters(vp);
//				}
//				userAuth.setUploadPassengerCondition(rs.getString("upload_doc_condition"));
//				userAuth.setUpload_doc((userAuth.isUpload_doc() && FormulaUtils.isCondition(
//						(FormulaUtils.replaceTagValues(userAuth.getUploadPassengerCondition(), vp)),
//						("Server Util (upload doc condition) for state " + stateCD))));
//				userAuth.setUploadDocIffMandateAvail(rs.getBoolean("upload_doc_iff_mandate_avail"));
//				if (stateCD.equalsIgnoreCase("MP") && ServerUtil
//						.getVaTempPermitReason(appl_no, stateCD, Util.getUserOffCode()).equalsIgnoreCase("C")) {
//					if (userAuth.isUpload_doc() && !stateCD.equals("MH") && false) {
//						if (pur_cd == TableConstants.VM_PMT_TEMP_PUR_CD && FormulaUtils.isCondition(
//								(FormulaUtils.replaceTagValues(rs.getString("passenger_list_temp"), vp)),
//								("Server Util (passenger_list_temp) for state " + stateCD))) {
//							userAuth.setUpload_doc(
//									(rs.getInt("passenger_list") <= 0) && (rs.getInt("no_of_passenger") > 0));
//						} else if (pur_cd == TableConstants.VM_PMT_SPECIAL_PUR_CD
//								&& rs.getBoolean("passenger_list_spl")) {
//							userAuth.setUpload_doc(
//									(rs.getInt("passenger_list") <= 0) && (rs.getInt("no_of_passenger") > 0));
//						}
//					}
//				}
//				// Aarti code
//				if (!CommonUtils.isNullOrBlank(rs.getString("no_fee_purpose"))) {
//					userAuth.setNofeeConfigured(rs.getString("no_fee_purpose"));
//				} else {
//					userAuth.setNofeeConfigured("'FALSE'");
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("unable to get Authorization details..");
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("unable to get Authorization details..");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return userAuth;
//	}
//
//	public static ResponceList getDlDetails(String dob_date, String dl_no) throws VahanException {
//		HttpURLConnection conn = null;
//		Unmarshaller unmarshaller = null;
//		ResponceList response = null;
//		JAXBContext jc = null;
//		URL url = null;
//		try {
//			byte[] dl = Base64.encodeBase64(dl_no.getBytes());
//			byte[] db = Base64.encodeBase64(dob_date.getBytes());
//			String usesr = "npermit";
//			byte[] us = Base64.encodeBase64(usesr.getBytes());
//			String password = "254d2e6d55768c03a8123e49fbbe048a";
//			byte[] ps = Base64.encodeBase64(password.getBytes());
//
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				url = new URL(ApplicationConfig.UPDATED_WEB_URL_SARTHI_LOI_LIVE + new String(dl) + "/" + new String(db)
//						+ "/" + new String(us) + "/" + new String(ps) + "");
//			} else {
//				url = new URL(ApplicationConfig.WEB_URL_SARTHI_LOI_TESTING + new String(dl) + "/" + new String(db) + "/"
//						+ new String(us) + "/" + new String(ps) + "");
//			}
//
//			conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Accept", "application/xml");
//			if (conn.getResponseCode() != 201) {
//				LOGGER.error("Failed : When getting data from sarthi... HTTP error code : " + conn.getResponseCode()
//						+ " URL = " + url);
//				throw new VahanException(
//						"Failed : When getting data from sarthi... HTTP error code : " + conn.getResponseCode());
//			}
//			jc = JAXBContext.newInstance(ResponceList.class);
//			unmarshaller = jc.createUnmarshaller();
//			response = (ResponceList) unmarshaller.unmarshal(conn.getInputStream());
//			LOGGER.info("Sarthi Response..." + response);
//			conn.disconnect();
//			if (null == response) {
//				LOGGER.error(
//						"Failed : We cannot find Deatils from satrhi for this DL Number  : " + dl_no + " URL = " + url);
//				throw new VahanException("Failed : We cannot find Deatils from satrhi for this DL Number  : " + dl_no);
//			}
//
//		} catch (MalformedURLException ex) {
//			LOGGER.error(ex.getMessage() + " , URL = " + url);
//
//		} catch (IOException ex) {
//			LOGGER.error(ex.getMessage() + " , URL = " + url);
//
//		} catch (JAXBException ex) {
//			LOGGER.error(ex.getMessage() + " , URL = " + url);
//
//		} catch (Exception ex) {
//			LOGGER.error(ex.getMessage() + " , URL = " + url);
//		}
//
//		return response;
//	}
//
//	public static Map<String, Integer> checkSeatCapacityRange(String regn_no, int purCd, String state_cd,
//			int permit_rto_cd) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		RowSet rs = null;
//		Map<String, Integer> SeatCapRangeMap = new HashMap<String, Integer>(2);
//		boolean result = false;
//		try {
//			tmgr = new TransactionManagerReadOnly("checkSeatCapacityRange");
//			
//			String sql = "select seat_cap_range from vt_owner a"
//					+ " inner join onlinepermit.tm_veh_permit_permitted b on a.fuel::text = any(String_to_array(b.fuel_type::text,',')) and"
//					+ " case when veh_class = 'ALL' then true else"
//					+ " a.vh_class ::text = any(String_to_array(b.veh_class,',')) end and"
//					+ " case when b.vch_catg = 'ALL' then true else "
//					+ " a.vch_catg ::text = any(String_to_array(b.vch_catg,',')) end and "
//					+ " case when b.veh_class_not_allowed = 'ALL'  OR  a.vh_class ::text = any(String_to_array(b.veh_class_not_allowed,',')) then false else true end and"
//					+ " ((a.purchase_dt::date + (b.veh_age||' YEAR')::interval)-interval '1' DAY)::date >= current_date "
//					+ " where a.regn_no = ? and b.state_cd = ? and b.off_cd = ? and b.pur_cd = ? and a.status in ('A','Y') order by b.pur_cd,b.veh_age";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(2, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(permit_rto_cd), "N")) {
//				ps.setInt(3, permit_rto_cd);
//			}
//			if (POSValidator.validate(String.valueOf(purCd), "N")) {
//				ps.setInt(4, purCd);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				if (!CommonUtils.isNullOrBlank(rs.getString("seat_cap_range"))) {
//					String[] range = rs.getString("seat_cap_range").split(",");
//					SeatCapRangeMap.put("min_range", Integer.valueOf(range[0]));
//					SeatCapRangeMap.put("max_range", Integer.valueOf(range[1]));
//				} else {
//					SeatCapRangeMap.put("min_range", 0);
//					SeatCapRangeMap.put("max_range", 0);
//				}
//			} else {
//				throw new VahanException(
//						"Seating capacity Condition for the Vehicle are not matching with the Permit Restrictions as per directions given by State Transport department  <a href='/onlinepermit/vahan/ui/OnlinePermit/formDetailsofPermit.xhtml'>  Kindly check Permit Permissible Condition by click here </a>");
//
//			}
//		} catch (VahanException e) {
//			throw new VahanException(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error("error occurs from checkSeatCapacityRange for purpose = " + purCd + " " + e.getMessage());
//			throw new VahanException("Some problem occurred while validating your vehicle scenario.");
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(
//							"error occurs from checkSeatCapacityRange for purpose = " + purCd + " " + e.getMessage());
//				}
//				if (rs != null) {
//					rs = null;
//				}
//			}
//		}
//
//		return SeatCapRangeMap;
//	}
//
//		public static String getUniqueNumber(TransactionManagerInterface tmgr, String stateCd, int permitType, String flag)
//			throws VahanException {
//		String unqNo = null;
//		String num;
//
//		try {
//			String sql = "select  state_cd||to_char(CURRENT_DATE,'YYYY')||prefix||document_no as document_no,document_no as num"
//					+ " from TM_DOCUMENT_NO_AVAILABLE " + "  where  state_cd=?  and  permit_type=? and flag =? "
//					+ " and prefix_year=date_part('year',CURRENT_DATE) " + " order by document_no LIMIT 1 "
//					+ "  FOR UPDATE SKIP LOCKED ";
//			PreparedStatement ps = tmgr.prepareStatement(sql);
//			ps.setString(1, stateCd);
//			ps.setInt(2, permitType);
//			ps.setString(3, flag);
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				unqNo = rs.getString("document_no");
//				num = rs.getString("num");
//
//				sql = "Delete " + " from  " + TableList.TM_DOCUMENT_NO_AVAILABLE
//						+ " where  state_cd=?  and  permit_type=? and flag =? and document_no=? ";
//
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, stateCd);
//				ps.setInt(2, permitType);
//				ps.setString(3, flag);
//				ps.setString(4, num);
//				ps.executeUpdate();
//
//				sql = "select * from " + TableList.TM_DOCUMENT_NO_ALLOTED + " where document_no=?";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, unqNo);
//				RowSet rsa = tmgr.fetchDetachedRowSet_No_release();
//				if (!rsa.next()) {
//					sql = "insert into  " + TableList.TM_DOCUMENT_NO_ALLOTED + " values(?,?,current_timestamp) ";
//					ps = tmgr.prepareStatement(sql);
//					ps.setString(1, unqNo);
//					ps.setLong(2, Long.parseLong(Util.getMobile_no()));
//					ps.executeUpdate();
//				}
//				return unqNo;
//			} else {
//				fillUpDocumentPool(stateCd, permitType, flag);
//				unqNo = getUniqueNumber(tmgr, stateCd, permitType, flag);
//			}
//
//		} catch (VahanException ex) {
//			throw ex;
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException("Something Went Wrong during Unique Number Generation, "
//					+ " Please Contact to the System Administrator");
//		}
//
//		return unqNo;
//
//	}
//
//	public static void fillUpDocumentPool(String stateCd, int permitType, String flag) throws VahanException {
//		TransactionManager tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		String sql;
//
//		try {
//			tmgr = new TransactionManager("fillUpDocumentPool");
//
//			sql = "update " + " tm_document_no" + " set permit_type=permit_type "
//					+ " where state_cd=? and permit_type=? and flag =? ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, stateCd);
//			ps.setInt(2, permitType);
//			ps.setString(3, flag);
//			int i = ps.executeUpdate();
//			if (i <= 0) {
//				LOGGER.error("No Valid Configuaration Found For Generating Number stateCd =" + stateCd + " permitType= "
//						+ permitType + " flag: " + flag);
//				throw new VahanException("No Valid Configuaration Found For Generating Number");
//			}
//
//			// For Double Check
//			sql = "select  state_cd||to_char(CURRENT_DATE,'YYMM')||prefix||document_no as document_no,document_no as num"
//					+ " from TM_DOCUMENT_NO_AVAILABLE " + "  where  state_cd=?  and  permit_type=? and flag =? "
//					+ " and prefix_year=date_part('year',CURRENT_DATE) " + " LIMIT 1 ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, stateCd);
//			ps.setInt(2, permitType);
//			ps.setString(3, flag);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				return;
//			}
//
//			// update tm_document_no to new prefix_yymm,sequence_no and
//			// sequence_flag
//			sql = "update tm_document_no b " + "set  sequence_no=9999 , " + "sequence_flag = (case "
//					+ "	 when a.prefix_year != date_part('year',CURRENT_DATE)::numeric then 'A'  "
//					+ "  when length(a.sequence_flag)=1 and a.sequence_flag='Z' then 'AA' "
//					+ "  when length(a.sequence_flag)=1 and a.sequence_flag in ('H','N') then CHR(ASCII(a.sequence_flag) +2) "
//					+ "  when length(a.sequence_flag)=1 then CHR(ASCII(a.sequence_flag) +1) "
//					+ "  when length(a.sequence_flag)=2 and a.sequence_flag ='ZZ' then 'A' "
//					+ "	 when length(a.sequence_flag)=2 and substring(a.sequence_flag,2,1) ='Z' and substring(a.sequence_flag,1,1) "
//					+ "  not in('H','N') " + "  then CHR(ASCII(substring(a.sequence_flag,1,1))+1)||'A' "
//					+ "  when length(a.sequence_flag)=2 and substring(a.sequence_flag,2,1) ='Z' and substring(a.sequence_flag,1,1) "
//					+ "  in('H','N') " + "  then CHR(ASCII(substring(a.sequence_flag,1,1))+2)||'A' "
//					+ "  when length(a.sequence_flag)=2 and substring(a.sequence_flag,2,1) in('H','N') "
//					+ "  then substring(a.sequence_flag,1,1)|| CHR(ASCII(substring(a.sequence_flag,2,1))+2) "
//					+ "   when length(a.sequence_flag)=2 and  substring(a.sequence_flag,2,1) !='Z' "
//					+ "  then substring(a.sequence_flag,1,1)|| CHR(ASCII(substring(a.sequence_flag,2,1))+1) "
//					+ "  end) ," + "  prefix_year =date_part('year',CURRENT_DATE)::numeric "
//					+ "  from tm_document_no a  where a.state_cd=b.state_cd and a.permit_type=b.permit_type and a.flag =b.flag "
//					+ "  and b.state_cd=? and b.permit_type=? and b.flag =?  ";
//
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, stateCd);
//			ps.setInt(2, permitType);
//			ps.setString(3, flag);
//			ps.executeUpdate();
//
//			// Remove all Unused Document Numbers
//			sql = "Delete from " + TableList.TM_DOCUMENT_NO_AVAILABLE
//					+ " where state_cd=? and permit_type=? and flag =? ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, stateCd);
//			ps.setInt(2, permitType);
//			ps.setString(3, flag);
//			ps.executeUpdate();
//
//			// Insert records into TM_DOCUMENT_NO_AVAILABLE
//			sql = "insert into" + " tm_document_no_available "
//					+ " select a.state_cd,a.prefix_year,a.prefix,b.document_no||a.sequence_flag, "
//					+ "  a.flag,a.permit_type,?,current_timestamp "
//					+ "  from 	tm_document_no a,(SELECT lpad(regn_seq::varchar, 4, '0') as document_no "
//					+ "  from generate_series(1, 9999) regn_seq) b "
//					+ "  where a.state_cd=?  and a.permit_type=? and a.flag=? ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setLong(1, Long.parseLong(Util.getMobile_no()));
//			ps.setString(2, stateCd);
//			ps.setInt(3, permitType);
//			ps.setString(4, flag);
//			ps.executeUpdate();
//
//			tmgr.commit();
//
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException("Something Went Wrong during Unique Number Generation, "
//					+ " Please Contact to the System Administrator");
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception ex) {
//					LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//				}
//			}
//		}
//	}
//
//	public static String viewUploadedDocument(String applNo) throws VahanException {
//		String Url = "";
//		try {
//
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//
//			Client client = Client.create();
//			if (!isTestingEnviroment) {
//				// Url ="https://vahan.parivahan.gov.in/dms-app/getVtDocList/";
//				Url = "http://10.246.40.132/dms-app/getVtDocList/";
//			} else {
//				Url = "http://10.248.93.17/dms-app/getVtDocList/";
//			}
//
//			WebResource wr = client.resource(Url.trim() + applNo);
//			ClientResponse res = wr.accept("application/json").get(ClientResponse.class);
//			if (res.getStatus() != 200) {
//				// //System.out.println("Failed : When getting data from DMS
//				// HTTP
//				// error code : " + res.getStatus());
//				throw new VahanException("Failed : When getting data from DMS HTTP error code : " + res.getStatus());
//
//			}
//			String str = res.getEntity(String.class);
//			// //System.out.println("Response from dms as string " + str);
//			JSONObject js = new JSONObject(str);
//			if (js.has("vtDocuments")) {
//				JSONArray arr = js.getJSONArray("vtDocuments");
//				for (int i = 0; i < arr.length(); i++) {
//					JSONObject ob = (JSONObject) arr.get(i);
//					if (ob.has("doc_catg_id") && ob.get("doc_catg_id").equals("PAL")) {
//						return (String) ob.get("doc_object_id");
//					}
//				}
//			}
//
//		} catch (Exception e) {
//			LOGGER.error("current_url_id" + Url);
//			LOGGER.error(e.getMessage() + " applNo " + applNo);
//			throw new VahanException("Failed : When getting data from DMS HTTP error code : ");
//		}
//		return null;
//	}
//
//	public static Map<String, Object> getEmergencyRebateFeeAndFine(String state_cd, int pur_cd, Date expire) {
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		Map<String, Object> exemptedMap = new HashMap<>();
//		String whereIam = "ServerUtils.getEmergencyRebateFeeAndFine";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			sql = "select rebate_fee_condition, rebate_fine_condition, rebate_from, rebate_upto, emergency_purpose, full_rebate_only_expiration_in_between_period, rebate_only_for_expiredin_exemption_period from onlinepermit.emergency_rebate_config where state_cd = ? and pur_cd = ? and iscurrent_emergency = true ";
//			// "and (? between rebate_from and rebate_upto) and (current_date
//			// between rebate_from and rebate_upto)"
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd, "AN")) {
//				ps.setString(1, state_cd);
//			}
//			if (POSValidator.validate(pur_cd + "", "N")) {
//				ps.setInt(2, pur_cd);
//			}
//			// if (expire != null) {
//			// ps.setDate(3, new java.sql.Date(expire.getTime()));
//			// }
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				exemptedMap.put("rebate_fee_condition", rs.getString("rebate_fee_condition"));
//				exemptedMap.put("rebate_fine_condition", rs.getString("rebate_fine_condition"));
//				exemptedMap.put("emergency_purpose", rs.getString("emergency_purpose"));
//				exemptedMap.put("rebate_from", rs.getDate("rebate_from"));
//				exemptedMap.put("rebate_upto", rs.getDate("rebate_upto"));
//				exemptedMap.put("full_rebate_only", rs.getBoolean("full_rebate_only_expiration_in_between_period"));
//				exemptedMap.put("rebate_only_exp_exemption",
//						rs.getBoolean("rebate_only_for_expiredin_exemption_period"));
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return exemptedMap;
//	}
//
//	public static NewPermitDetailsDobj getPmtDtlsforOfferLetter(String state_cd, int off_cd, String regn_no,
//			NewPermitDetailsDobj permitDobj) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		RowSet rs = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("haveOfferLetter");
//			String strSQL = "SELECT pmt_type FROM " + TableList.VA_PERMIT + " where state_cd = ? and regn_no=? ";
//			ps = tmgr.prepareStatement(strSQL);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//
//			if (POSValidator.validate(regn_no, "AN")) {
//				ps.setString(2, regn_no);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				permitDobj.setPmt_type(rs.getInt("pmt_type"));
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to Get Configure Details For Offer Letter.. ");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (rs != null) {
//					rs.close();
//					rs = null;
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return permitDobj;
//	}
//
//
//	public static DMSResponseMaterDO getDMSResponseMasterDobj(String state_cd, int pur_cd, String apppl_no,
//			String pmt_typr, String trans_type, String authMode, int vh_class) throws VahanException {
//		DMSResponseMaterDO dmsResponseMaterDO = null;
//
//		try {
//
//			dmsResponseMaterDO = CommonUtils.getDMSResponse(apppl_no, pur_cd, state_cd, pmt_typr, trans_type, authMode, vh_class);
//			
//
//		} catch (VahanException ve) {
//			throw new VahanException(ve.getMessage());
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException("Unable to get Details from DMS...");
//		}
//
//		return dmsResponseMaterDO;
//	}
//
//	public static byte[] fetchdigitalsignature(String appl_no, String state_cd, int off_cd, String signtype, int pur_cd,
//			int user_cd) {
//
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		byte[] signature = null;
//		String sql = "";
//
//		try {
//			tmgr = new TransactionManagerReadOnly("fetchdigitalsignature");
//			sql = "select doc_sign from tm_user_sign where user_cd=("
//					+ " select emp_cd from vha_status a where appl_no = ? "
//					+ " and flow_slno=(select max(flow_slno) from vha_status b where a.appl_no=b.appl_no and a.state_cd=b.state_cd and a.off_cd=b.off_cd)"
//					+ " order by moved_on desc limit 1)";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, appl_no);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				signature = rs.getBytes("doc_sign");
//			} else {
//				sql = "";
//				signature = null;
//				ps = null;
//				sql = "select doc_sign from tm_user_sign where user_cd in (select user_cd from tm_user_info where state_cd = ? and off_cd = ? and user_catg = ?)";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, state_cd);
//				ps.setInt(2, off_cd);
//				ps.setString(3, "A");
//				ResultSet rss = ps.executeQuery();
//				if (rss.next()) {
//					signature = rss.getBytes("doc_sign");
//				}
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//
//		return signature;
//	}
//
//	public static boolean isPaymorthfee(String state_cd) {
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		boolean isPaymorthfee = false;
//		String whereIam = "ServerUtils.isPaymorthfee";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			sql = "select paymorthfee from onlinepermit.tm_off_configuration where state_cd = ? ";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd, "AN")) {
//				ps.setString(1, state_cd);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				isPaymorthfee = rs.getBoolean("paymorthfee");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return isPaymorthfee;
//	}
//
//	////////////////////// token for Dms//////////////////////////
//	public static TokenData getDMSTokenId() throws VahanException {
//		TokenData dmsResponse = null;
//		String url = "";
//		boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//				.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//		ApplicationConfig appConfig = new ApplicationConfig(
//				(isTestingEnviroment ? ApplicationConfig.PROP_ONLINE_PERMIT_TEST
//						: ApplicationConfig.PROP_ONLINE_PERMIT_LIVE));
//		url = new String(appConfig.getProperties(ApplicationConfig.PROP_DMS_TOKEN_URL));
//		try {
//			DefaultHttpClient client = new DefaultHttpClient();
//			HttpPost request = new HttpPost(url);
//			HttpResponse httpresponse = client.execute(request);
//			HttpEntity entity = httpresponse.getEntity();
//			String response = EntityUtils.toString(entity);
//			ObjectMapper mapper = new ObjectMapper();
//			dmsResponse = mapper.readValue(response, TokenData.class);
//			if (dmsResponse == null) {
//				throw new VahanException("DMS response related to its reference not available");
//			} else if (CommonUtils.isNullOrBlank(dmsResponse.getToken())) {
//				throw new VahanException("DMS response related to its reference not generated");
//			} else if (CommonUtils.isNullOrBlank(dmsResponse.getTokenRefId())) {
//				throw new VahanException("DMS response related to its reference value not generated");
//			}
//		} catch (VahanException e) {
//			throw e;
//		} catch (Exception e) {
//			LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//			throw new VahanException("DMS refference can't be generated");
//		}
//		return dmsResponse;
//	}
//
//	public static String encryptData(String s, String secretKey) {
//		String encData = null;
//		try {
//			SecretKeySpec privateKey = setKey(secretKey);
//			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
//			byte[] encrypted = cipher.doFinal(s.getBytes("UTF-8"));
//			LOGGER.info("encrypted string:" + Base64.encodeBase64String(encrypted));
//			encData = Base64.encodeBase64String(encrypted);
//		} catch (Exception ex) {
//			LOGGER.error(ex.getCause(), ex);
//		}
//		return encData;
//	}
//
//	private static SecretKeySpec setKey(String myKey) {
//		MessageDigest sha = null;
//		SecretKeySpec privateKey = null;
//		try {
//			byte[] key = myKey.getBytes("UTF-8");
//			sha = MessageDigest.getInstance("SHA-1");
//			key = sha.digest(key);
//			key = Arrays.copyOf(key, 16);
//			privateKey = new SecretKeySpec(key, "AES");
//		} catch (NoSuchAlgorithmException ex) {
//			LOGGER.error(ex.getCause(), ex);
//		} catch (UnsupportedEncodingException ex) {
//			LOGGER.error(ex.getCause(), ex);
//		}
//		return privateKey;
//	}
//
//	
//
//	public static boolean isPermitParameterBasedOnTax(String state_cd) throws VahanException {
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		boolean permitParameterBasedOnTax = true;
//		String whereIam = "ServerUtils.isPermitParameterBasedOnTax";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			sql = "select is_permit_parameter_based_on_tax from onlinepermit.tm_online_state_configuration where state_cd = ?";
//			ps = tmgr.prepareStatement(sql);
//
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				permitParameterBasedOnTax = rs.getBoolean("is_permit_parameter_based_on_tax");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Not able to idententify permit detail whether is it is depend on tax or not !!!");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//				throw new VahanException(
//						"Not able to idententify permit detail whether is it is depend on tax or not !!!");
//			}
//		}
//		return permitParameterBasedOnTax;
//	}
//
//	public static Map<String, String> npApprovalActionFlow(String state_cd, int pur_cd) {
//		Map<String, String> map = new LinkedHashMap<String, String>();
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String payablePurCd = null;
//		String whereIam = "ServerUtils.npApprovalActionFlow";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			sql = "select a.state_cd, a.pur_cd, a.flow_srno, a.action_cd from tm_purpose_action_flow a"
//					+ " inner join onlinepermit.np_portal_approval b on a.state_cd = b.state_cd and a.pur_cd = b.pur_cd and a.action_cd = b.to_action"
//					+ " where a.state_cd = ? and a.pur_cd = ?";
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			if (POSValidator.validate(state_cd, "AN")) {
//				ps.setString(i++, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(pur_cd), "N")) {
//				ps.setInt(i++, pur_cd);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			ResultSetMetaData rsmd = rs.getMetaData();
//			while (rs.next()) {
//				for (int j = 1; j <= rsmd.getColumnCount(); j++) {
//					map.put(rsmd.getColumnName(j), rs.getString(j));
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return map;
//	}
//
//	public static String payablePurCd(String state_cd, String regn_no, String appl_no, int off_cd) {
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String payablePurCd = null;
//		String whereIam = "ServerUtils.payablePurCd";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			sql = "select string_agg(distinct pur_cd::text,',') as pur_cd from (select pur_cd  from "
//					+ TableList.VT_TEMP_APPL_TRANSACTION
//					+ " where state_cd = ? and off_cd = ? and regn_no = ? and transaction_no = ? union select trans_pur as pur_cd from "
//					+ TableList.EMERGENCY_RELATED_EXCEMPTED_RECORD
//					+ " where state_cd = ? and off_cd = ?  and transaction_no = ? union select pur_cd from "
//					+ TableList.VP_APPL_RCPT_MAPPING + " a inner join " + TableList.VT_FEE
//					+ " b on a.state_cd = b.state_cd and a.off_cd = b.off_cd  and a.rcpt_no = b.rcpt_no where a.state_cd = ? and a.off_cd = ? and b.regn_no = ? and a.appl_no = ?  )a";
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			if (POSValidator.validate(state_cd, "AN")) {
//				ps.setString(i++, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				ps.setInt(i++, off_cd);
//			}
//			if (POSValidator.validate(regn_no, "AN")) {
//				ps.setString(i++, regn_no);
//			}
//			if (POSValidator.validate(appl_no, "AN")) {
//				ps.setString(i++, appl_no);
//			}
//			if (POSValidator.validate(state_cd, "AN")) {
//				ps.setString(i++, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				ps.setInt(i++, off_cd);
//			}
//			if (POSValidator.validate(appl_no, "AN")) {
//				ps.setString(i++, appl_no);
//			}
//			if (POSValidator.validate(state_cd, "AN")) {
//				ps.setString(i++, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				ps.setInt(i++, off_cd);
//			}
//			if (POSValidator.validate(regn_no, "AN")) {
//				ps.setString(i++, regn_no);
//			}
//			if (POSValidator.validate(appl_no, "AN")) {
//				ps.setString(i++, appl_no);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				payablePurCd = rs.getString("pur_cd");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return payablePurCd;
//	}
//
//	public static String getSmsMsg(String appl_no, boolean verify) {
//		String msg = "";
//		msg = "Your application is saved with temporary application number " + appl_no;
//		msg = verify ? msg.concat(
//				". Mention this no. in any kind of future communication. Payment needs to be initiated now.MoRTH")
//				: msg.concat(". Use this number for verification of details.MoRTH");
//
//		return msg;
//	}
//
//	public static String isEsignPermitPrint(String state_cd) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		String is_esigend_pmt_prt = null;
//		RowSet rs = null;
//		String sql = null;
//		sql = "select is_esigend_pmt_prt from onlinepermit.tm_online_state_configuration where state_cd = ?  ";
//
//		try {
//			tmgr = new TransactionManagerReadOnly("isEsignPermitPrint");
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(i++, state_cd);
//			}
//
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				is_esigend_pmt_prt = rs.getString("is_esigend_pmt_prt");
//			}
//		} catch (Exception ex) {
//			throw new VahanException("Unable to Get Permit PrintPermission..");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception ee) {
//				LOGGER.error(ee);
//			}
//		}
//
//		return is_esigend_pmt_prt;
//	}
//
//	public static boolean isChallanValidationEnable(String state_cd, int pur_cd) throws VahanException {
//		boolean validate = false;
//		String sql = "select tm.e_challan_validation from " + TableList.ONLINE_PERMIT_STATE_CONFIGURE
//				+ " tm where tm.state_cd = ?";
//		TransactionManagerReadOnly tmgr = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("isChallanValidationEnable");
//			PreparedStatement ps = tmgr.prepareStatement(sql);
//			ps.setString(1, state_cd);
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				String challanValidation = rs.getString("e_challan_validation");
//				if (!CommonUtils.isNullOrBlank(challanValidation)) {
//					if (challanValidation.equalsIgnoreCase("FALSE")) {
//						validate = false;
//					} else if (challanValidation.equalsIgnoreCase("TRUE")) {
//						validate = true;
//					} else {
//						validate = challanValidation.contains("," + pur_cd + ",");
//					}
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Challan validation is not configured.");
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//		}
//		return validate;
//	}
//
//	public static boolean ispassengerListUrlenable(String appl_no, String state_cd, int pur_cd) throws VahanException {
//		PreparedStatement ps = null;
//		boolean viewUrl = false;
//		TransactionManagerInterface tmgr = null;
//		String sql = "";
//		String tablename = null;
//
//		try {
//			if (pur_cd == TableConstants.VM_PMT_TEMP_PUR_CD) {
//				tablename = "onlinepermit.tm_configuration_temp_pmt ";
//			} else if (pur_cd == TableConstants.VM_PMT_SPECIAL_PUR_CD) {
//				tablename = "onlinepermit.tm_configuration_spl_pmt";
//			}
//			tmgr = new TransactionManagerReadOnly("printCertificateImpl.ispassengerListUrlenable");
//			sql = "select * from " + tablename + " where state_cd = ?";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, state_cd);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				
//				if (pur_cd == TableConstants.VM_PMT_TEMP_PUR_CD) {
//					if (rs.getBoolean("passenger_list_temp")) {
//						viewUrl = true;
//					}
//				}
//				if (pur_cd == TableConstants.VM_PMT_SPECIAL_PUR_CD) {
//					if (rs.getBoolean("passenger_list_spl")) {
//						viewUrl = true;
//					}
//				}
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to get configuration of  passenger List...");
//
//		} finally {
//
//			try {
//				if (tmgr != null) {
//
//					tmgr.release();
//				}
//
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//
//			}
//		}
//
//		return viewUrl;
//
//	}
//
//	public static boolean isFailedTransChk(String state_cd) throws VahanException {
//		PreparedStatement ps = null;
//		boolean isFailedTransChk = false;
//		TransactionManagerInterface tmgr = null;
//		String sql = "";
//		String tablename = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("Servertil.isFailedTransChk");
//			sql = "select is_failed_trans_chk from " + TableList.ONLINE_PERMIT_STATE_CONFIGURE + " where state_cd = ?";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, state_cd);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				if (rs.getBoolean("is_failed_trans_chk")) {
//					isFailedTransChk = true;
//				}
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to get validation for failed application...");
//
//		} finally {
//
//			try {
//				if (tmgr != null) {
//
//					tmgr.release();
//				}
//
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//
//			}
//		}
//
//		return isFailedTransChk;
//
//	}
//
//	public static boolean vahanPgiAndVtTempApplStatus(String applNo, String vtTempStatus, String pgiStatus)
//			throws VahanException {
//		PreparedStatement ps = null;
//		boolean isFailedTransChk = false;
//		TransactionManagerInterface tmgr = null;
//		String sql = "";
//		try {
//			tmgr = new TransactionManagerReadOnly("Servertil.vahanPgiAndVtTempApplStatus");
//			sql = "select vp.response_code,vt.application_status from vahanpgi.vp_pgi_details vp inner join "
//					+ TableList.VT_TEMP_APPL_TRANSACTION + " vt on vp.payment_id = vt.transaction_no inner join"
//					+ " vahanpgi.vh_temp_approve vh on vh.payment_id =vt.transaction_no"
//					+ " where vp.payment_id = ? and vt.application_status= ? and vp.response_code =? ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, applNo);
//			ps.setString(2, vtTempStatus);
//			ps.setString(3, pgiStatus);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				isFailedTransChk = true;
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Unable to get validation for failed application...");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return isFailedTransChk;
//	}
//
//	public static Integer getRePrintPermitPeriod(String state_cd) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		Integer reprint_periods = null;
//		RowSet rs = null;
//		String sql = null;
//		sql = "select reprintpermit_periods from " + TableList.ONLINE_PERMIT_STATE_CONFIGURE + " where state_cd = ? ";
//
//		try {
//			tmgr = new TransactionManagerReadOnly("getPermitPrintPur_cd");
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(i++, state_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				reprint_periods = rs.getInt("reprintpermit_periods");
//			}
//		} catch (Exception ex) {
//			throw new VahanException("Unable to Get Permit PrintPermission..");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception ee) {
//				LOGGER.error(ee);
//			}
//		}
//
//		return reprint_periods;
//	}
//
//	public static InsuranceInfoDobj getDataFromInsuranceServiceNew(String regn_no) throws VahanException, IOException {
//		InsuranceInfoDobj insuranceInfoDobj = null;
//		try {
//			
//			String urlParameters = "regnNo=" + regn_no;
//			byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
//			int postDataLength = postData.length;
//			String BASE_URI = "http://127.0.0.1/vahanInsuranceWS/insuranceInfo/";
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (isTestingEnviroment) {
//				BASE_URI = "https://staging.parivahan.gov.in/vahanInsuranceWS/insuranceInfo/";
//			}
//			
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			URL obj = new URL(BASE_URI);
//			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//			con.setConnectTimeout(10000);
//			con.setReadTimeout(15000);
//
//			con.setRequestMethod("POST");
//			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			con.setRequestProperty("Accept", "application/json");
//			// Send post request
//			con.setDoOutput(true);
//			con.setInstanceFollowRedirects(false);
//			con.setRequestProperty("charset", "utf-8");
//			con.setRequestProperty("Content-Length", Integer.toString(postDataLength));
//			con.setUseCaches(false);
//			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
//				wr.write(postData);
//				wr.flush();
//			}
//			int responseCode = con.getResponseCode();
//			if (responseCode == 200) {
//				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//				String inputLine;
//				StringBuilder response = new StringBuilder();
//				while ((inputLine = in.readLine()) != null) {
//					response.append(inputLine);
//				}
//				in.close();
//				if (!(response.toString().contains("No record found against this regn no")
//						|| response.toString().contains("Regn No. Cannot be more than 10 digit")
//						|| response.toString().contains("Regn No. Cannot be less than 6 digit"))) {
//					insuranceInfoDobj = mapper.readValue(response.toString(), InsuranceInfoDobj.class);
//					if (insuranceInfoDobj != null) {
//						LOGGER.info("insurance details from service for this regn_no " + regn_no
//								+ insuranceInfoDobj.getInsFrom() + " " + insuranceInfoDobj.getInsUpto() + " "
//								+ insuranceInfoDobj.getInsuranceType() + " " + insuranceInfoDobj.getIssuerCd() + " "
//								+ insuranceInfoDobj.getPolicyNo());
//						
//					}
//				}
//			}
//		} catch (IOException ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0] + " regn_no: " + regn_no + " - ");
//		}
//		return insuranceInfoDobj;
//	}
//
//	public static boolean isFaceLessService(String state_cd, int pur_cd, DOVP_Details dobj,
//			String withaadh_facelesspurcd, String withaadhar_faceless_else_normal, boolean aadhres,
//			VehicleParameters parameter) throws VahanException {
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		boolean faceless = false;
//		boolean iswithaadh_facelesspurcd = false;
//		boolean iswithaadhar_faceless_else_normal = false;
//		String whereIam = "ServerUtils.isFaceLessService";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereIam);
//			sql = "select  enab_faceless_serv,is_aadhar_authecation from onlinepermit.tm_online_state_configuration where state_cd = ?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//
//				String face_less_cond = rs.getString("enab_faceless_serv");
//				if (face_less_cond.equalsIgnoreCase("FALSE") || face_less_cond.equalsIgnoreCase("TRUE")) {
//					faceless = FormulaUtils.isCondition(face_less_cond, "isFaceLessService");
//				} else {
//
//					// parameter.setIS_AADHAR(rs.getString("is_aadhar_authecation"));
//					parameter.setIS_AADHAR(String.valueOf(aadhres));
//					parameter.setPUR_CD(pur_cd);
//					faceless = FormulaUtils.isCondition(FormulaUtils.replaceTagPermitValues(face_less_cond, parameter),
//							"isFaceLessService");
//				}
//			}
//			if (faceless) {
//				if (!CommonUtils.isNullOrBlank(withaadh_facelesspurcd)) {
//
//					iswithaadh_facelesspurcd = FormulaUtils.isCondition(
//							FormulaUtils.replaceTagPermitValues(withaadh_facelesspurcd, parameter),
//							"isFaceLessService");
//					if (iswithaadh_facelesspurcd && !aadhres) {
//						faceless = false;
//					}
//
//				}
//				if (!CommonUtils.isNullOrBlank(withaadhar_faceless_else_normal)) {
//					iswithaadhar_faceless_else_normal = FormulaUtils.isCondition(
//							FormulaUtils.replaceTagPermitValues(withaadhar_faceless_else_normal, parameter),
//							"isFaceLessService");
//					if (iswithaadhar_faceless_else_normal && !aadhres) {
//						faceless = false;
//					}
//
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException("Not able to idententify faceless service");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//				throw new VahanException("Not able to idententify faceless service");
//			}
//		}
//		return faceless;
//	}
//
//	public static String getLastFiveCharOfChasiNo(TransactionManager tmgr, String regn_no) throws VahanException {
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String chasi_no = null;
//		try {
//
//			String query = "select substring(chasi_no from char_length(chasi_no)-4 for char_length(chasi_no)) as chasino_lastfivechar from vt_owner WHERE status!='N' and regn_no = ?";
//			ps = tmgr.prepareStatement(query);
//			if (POSValidator.validate(regn_no, "AN")) {
//				ps.setString(1, regn_no);
//			}
//
//			rs = tmgr.fetchDetachedRowSet_No_release();
//
//			while (rs.next()) {
//				chasi_no = rs.getString("chasino_lastfivechar");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//			throw new VahanException(
//					"Something went wrong while getting the flow for application, Please try after some time.");
//			// TODO: handle exception
//		}
//
//		return chasi_no;
//	}
//
//	/*
//	 * Fetching details from aitp portal.
//	 * 
//	 */
//	public static AITPCertificateDobj getPermitDetailsFromAITP(String regn_no, String chasi_no) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String sql = "";
//		AITPCertificateDobj aitpCertificateDobj = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getPermitDetailsFromAITP");
//			sql = "SELECT * from vahan4.get_aitp_printdetails(?,?);";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, regn_no);
//			ps.setString(2, chasi_no);
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				aitpCertificateDobj = new AITPCertificateDobj();
//				aitpCertificateDobj.setPmtNo(rs.getString("aitp_pmt_no"));
//				aitpCertificateDobj.setPmtFrom(rs.getString("from_date"));
//				aitpCertificateDobj.setPmtUpto(rs.getString("upto_date"));
//				aitpCertificateDobj.setRegnNo(regn_no);
//			}
//
//			sql = "SELECT * from vahan4.get_aitpauth_printdetails(?,?);";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, regn_no);
//			ps.setString(2, chasi_no);
//			RowSet rs1 = tmgr.fetchDetachedRowSet();
//			if (rs1.next()) {
//				aitpCertificateDobj = new AITPCertificateDobj();
//				aitpCertificateDobj.setAuthNo(rs1.getString("auth_no"));
//				aitpCertificateDobj.setAuthFrom(rs1.getString("from_date"));
//				aitpCertificateDobj.setAuthUpto(rs1.getString("upto_date"));
//			}
//
//		} catch (SQLException e) {
//			throw new VahanException("Authorization details are missing. Please contact system administrator.");
//		} finally {
//			try {
//				tmgr.release();
//			} catch (Exception e) {
//				LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//			}
//		}
//		return aitpCertificateDobj;
//	}
//	/*
//	 * Fetching VLTD details
//	 * 
//	 */
//
//	public static VehicleTrackingDetailsDobj getVehicleTrackingDetailsByChasiOrRegn_no(String chasi_no, String regno)
//			throws VahanException {
//		VehicleTrackingDetailsDobj dobj = null;
//		PreparedStatement ps = null;
//		String sql = null;
//		String parameter_regn_no_or_chassi_no = null;
//		TransactionManagerInterface tmgr = null;
//		try {
//			if (chasi_no != null) {
//				parameter_regn_no_or_chassi_no = chasi_no;
//			} else {
//				parameter_regn_no_or_chassi_no = regno;
//			}
//
//			sql = " SELECT * from getvltdinfo(?)";
//			tmgr = new TransactionManagerReadOnly("getVehicleTrackingDetailsByChasiOrRegn_no");
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, parameter_regn_no_or_chassi_no);
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				dobj = new VehicleTrackingDetailsDobj();
//				dobj.setRegn_no(regno);
//				dobj.setChasi_no(chasi_no);
//				dobj.setDevice_sr_no(rs.getString("device_sr_no"));
//				dobj.setImei_no(rs.getString("imei_no"));
//				dobj.setIcc_id(rs.getString("icc_id"));
//				dobj.setActivation_rcpt_no(rs.getString("activation_rcpt_no"));
//				dobj.setDevice_activation_date(rs.getDate("device_activation_date"));
//				dobj.setDevice_activated_upto(rs.getDate("device_activated_upto"));
//				dobj.setDevice_activation_status(rs.getString("device_activation_status"));
//				dobj.setVltd_manufacturer(rs.getString("vltd_manufacturer"));
//				dobj.setFitted_date(rs.getDate("fitted_date"));
//				dobj.setFitment_center(rs.getString("fitment_center"));
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.toString() + " " + e.getStackTrace()[0] + "-Appl_no-" + parameter_regn_no_or_chassi_no);
//			throw new VahanException("Error: Unable to get VLTD Details!!!!!");
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//				}
//			}
//		}
//
//		return dobj;
//
//	}
//
//	/**
//	 * for digital signature
//	 */
//	public static boolean isDsignPermitPrint(String stateCd, int offCd, int purCd, int pmtType) throws VahanException {
//		String Query;
//		boolean allowDsign = false;
//		PreparedStatement ps;
//		TransactionManagerReadOnly tmgr = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("isDsignPermitPrint");
//			if (pmtType == 0) {
//				Query = "SELECT * FROM " + TableList.TM_CONFIGURATION_DIGITAL_SIGNED
//						+ " WHERE state_cd= ? and (off_cd='ALL' OR ? = ANY(STRING_TO_ARRAY(off_cd,',')::integer[])) and ? = ANY(STRING_TO_ARRAY(pur_cd,',')::integer[]) ";
//			} else {
//				Query = "SELECT * FROM " + TableList.TM_CONFIGURATION_DIGITAL_SIGNED
//						+ " WHERE state_cd= ? and (off_cd='ALL' OR ? = ANY(STRING_TO_ARRAY(off_cd,',')::integer[])) and ? = ANY(STRING_TO_ARRAY(pur_cd,',')::integer[]) and ? = ANY(STRING_TO_ARRAY(pmt_type,',')::integer[]) ";
//			}
//			ps = tmgr.prepareStatement(Query);
//			ps.setString(1, stateCd);
//			ps.setInt(2, offCd);
//			ps.setInt(3, purCd);
//			if (pmtType != 0) {
//				ps.setInt(4, pmtType);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				allowDsign = true;
//				break;
//			}
//		} catch (Exception e) {
//			throw new VahanException("Unable to get Digital Signature Permit Print Details." + e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//			}
//		}
//		return allowDsign;
//	}
//
//	public static String getServerIpAdd() {
//		String serverIpAddress = "";
//		try {
//			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
//					.getRequest();
//			serverIpAddress = req.getLocalAddr();
//
//			int serverPort = req.getLocalPort();
//			if (serverIpAddress != null && serverIpAddress.length() > 0) {
//				String strPort = (serverPort - 80) + "";
//				String splitIp[] = serverIpAddress.split("\\.");
//				if (splitIp.length > 3) {
//					serverIpAddress = splitIp[3];
//
//					serverIpAddress = "~" + serverIpAddress + "~" + strPort;
//				} else {
//					serverIpAddress = "";
//				}
//			} else {
//				serverIpAddress = "";
//			}
//
//		} catch (Exception e) {
//			LOGGER.error("ServerUtil : getPageTitle: " + e);
//			serverIpAddress = "";
//		}
//		return serverIpAddress;
//	}
//
//	public static String getPermitQRURL(String regn_no, String pmt_no, int doc_type) throws VahanException {
//		PreparedStatement ps = null;
//		String qrurl = null;
//		String sql = null;
//		RowSet rs = null;
//		String tableName = null;
//		String whereClause = null;
//		String vahanExpMsg = null;
//		TransactionManagerInterface tmgr = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getPermitQRURL");
//			switch (doc_type) {
//			case DocumentType.VT_PERMIT_PARTA_QR:
//				if (!CommonUtils.isNullOrBlank(regn_no)) {
//					tableName = TableList.VT_PERMIT_QR;
//					whereClause = "regn_no = '" + regn_no + "' and pmt_no = '" + pmt_no + "' and doc_id = '"
//							+ TableConstants.PERMIT_DOC_PART_A + "'";
//					break;
//				} else {
//					vahanExpMsg = "Details not found for Registration No " + regn_no;
//				}
//			case DocumentType.VT_PERMIT_PARTB_QR:
//				if (!CommonUtils.isNullOrBlank(regn_no)) {
//					tableName = TableList.VT_PERMIT_QR;
//					whereClause = "regn_no = '" + regn_no + "' and pmt_no = '" + pmt_no + "' and doc_id = '"
//							+ TableConstants.PERMIT_DOC_PART_B + "'";
//					break;
//				} else {
//					vahanExpMsg = "Details not found for Registration No " + regn_no;
//				}
//			case DocumentType.VT_PERMIT_AUTH_QR:
//				if (!CommonUtils.isNullOrBlank(regn_no)) {
//					tableName = TableList.VT_PERMIT_AUTH_QR;
//					whereClause = "regn_no = '" + regn_no + "' and pmt_no = '" + pmt_no + "' and doc_id = '"
//							+ TableConstants.PERMIT_HOME_AUTH_DOC + "'";
//					break;
//				} else {
//					vahanExpMsg = "Details not found for Registration No " + regn_no;
//				}
//			case DocumentType.VT_TEMP_PERMIT_QR:
//				if (!CommonUtils.isNullOrBlank(regn_no)) {
//					tableName = TableList.VT_TEMPSPL_PERMIT_QR;
//					whereClause = "regn_no = '" + regn_no + "' and pmt_no = '" + pmt_no + "' and doc_id = '"
//							+ TableConstants.PERMIT_DOC_TEMP_PMT + "'";
//					break;
//				} else {
//					vahanExpMsg = "Temporary Permit Details not found for Registration No " + regn_no;
//				}
//			case DocumentType.VT_SPL_PERMIT_QR:
//				if (!CommonUtils.isNullOrBlank(regn_no)) {
//					tableName = TableList.VT_TEMPSPL_PERMIT_QR;
//					whereClause = "regn_no = '" + regn_no + "' and pmt_no = '" + pmt_no + "' and doc_id = '"
//							+ TableConstants.PERMIT_DOC_SPL_PMT + "'";
//					break;
//				} else {
//					vahanExpMsg = "Special Permit Details not found for Registration No " + regn_no;
//				}
//			default:
//				vahanExpMsg = "Details not found for Registration/Application no " + regn_no + " with doc type "
//						+ doc_type + ". Please try after sometime!";
//
//			}
//			if (vahanExpMsg != null) {
//				throw new VahanException(vahanExpMsg);
//			}
//			sql = "select qrhash from " + tableName + " WHERE " + whereClause;
//			ps = tmgr.prepareStatement(sql);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				if (rs.getString("qrhash") != null && !rs.getString("qrhash").isEmpty()) {
//					qrurl = rs.getString("qrhash");
//					// qrurl =
//					// "https://qr.parivahan.gov.in/vq/qr?v=".concat(rs.getString("qrhash"));
//					// qrurl =
//					// "https://staging.parivahan.gov.in/vq/qr?v=".concat(rs.getString("qrhash"));
//				}
//			}
//			return qrurl;
//		} catch (VahanException ve) {
//			throw ve;
//		} catch (SQLException e) {
//			LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//			throw new VahanException("Problem occurred during getting the Permit QR details.");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//			}
//		}
//	}
//	// for blacklist added by sushmita
//
//	public static boolean checkBlklistCondFormula(String state_cd, int pur_cd, String regn_no, String chasi_no)
//			throws VahanException {
//		String chasino = ServerUtil.getChasiNo(regn_no);
//		boolean flag = false;
//		BlackListedVehicleDobj BlackListedVehicleDobj = getBlacklistedVehicleDetails(regn_no, chasino);
//		if (BlackListedVehicleDobj != null) {
//			int code1 = BlackListedVehicleDobj.getComplain_type();
//			String code = Integer.toString(code1);
//			String blackListCondFormula = null;
//			TransactionManagerReadOnly tmgr = null;
//			PreparedStatement ps = null;
//			String sql = null;
//			RowSet rs = null;
//			sql = "select allowservice_whenblacklist from " + TableList.ONLINE_PERMIT_STATE_CONFIGURE
//					+ "  where state_cd= ?";
//			try {
//				tmgr = new TransactionManagerReadOnly("getBlacklistCondition");
//				ps = tmgr.prepareStatement(sql);
//				int i = 1;
//				if (POSValidator.validate(state_cd, "A")) {
//					ps.setString(i++, state_cd);
//				}
//				rs = tmgr.fetchDetachedRowSet();
//				if (rs.next())
//					blackListCondFormula = rs.getString("allowservice_whenblacklist");
//				if (blackListCondFormula == null)
//					return true;
//				if (blackListCondFormula != null) {
//					blackListCondFormula = blackListCondFormula.replace("<", "".trim());
//					blackListCondFormula = blackListCondFormula.replace(">", "".trim());
//					String[] split_combination_allowed = blackListCondFormula.split("&");
//					for (int k = 0; k < split_combination_allowed.length; k++) {
//						String temp = split_combination_allowed[k];
//						if (temp.contains(",")) {
//							int index = temp.indexOf(",");
//							temp = temp.substring(0, index);
//							if (temp.equalsIgnoreCase(Integer.toString(pur_cd))) {
//								
//								split_combination_allowed[i] = split_combination_allowed[i].substring(
//										ServerUtil.getfirstSemicolonFromString(split_combination_allowed[i]) + 1,
//										split_combination_allowed[i].length());
//								split_combination_allowed[i] = split_combination_allowed[i].replace("(", "");
//								split_combination_allowed[i] = split_combination_allowed[i].replace(")", "");
//								String[] split1 = split_combination_allowed[i].split(",");
//								if (split1 != null && split1.length > 0) {
//									flag = false;
//									for (int j = 0; j < split1.length; j++) {
//										if (split1.length == 1 && split1[j].contains("0")) { 
//											flag = true;
//											break;
//										}
//										if (code.equalsIgnoreCase(split1[j])) {
//											flag = true;
//											break;
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//
//			} catch (Exception e) {
//				throw new VahanException("Unable to get blacklist details");
//			} finally {
//				try {
//					if (tmgr != null) {
//						tmgr.release();
//					}
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//				}
//			}
//			return flag;
//		} else {
//			return true;
//		}
//	}
//
//	public static int getfirstSemicolonFromString(String pattern) {
//
//		for (int i = 0; i < pattern.length(); i++) {
//			char a = pattern.charAt(i);
//			if (a == ',') {
//
//				return pattern.indexOf(",");
//			}
//		}
//		return 0;
//
//	}
//
//	public static void insertAndMoveToHistoryTempSplPERMITQRDetails(QrCodeDobj qrdobj, TransactionManager tmgr)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		RowSet rs = null;
//		RowSet rs1 = null;
//		long emp_cd = Long.parseLong(Util.getEmpCode());
//		try {
//			if (qrdobj != null && !CommonUtils.isNullOrBlank(qrdobj.getApplNO())
//					&& !CommonUtils.isNullOrBlank(qrdobj.getRegnNO())
//					&& !CommonUtils.isNullOrBlank(qrdobj.getPmtNo())) {
//				sql = "select * from " + TableList.VT_TEMPSPL_PERMIT_QR + " WHERE regn_no = ? and pmt_no=?";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, qrdobj.getRegnNO());
//				ps.setString(2, qrdobj.getPmtNo());
//				rs = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//				if (rs.next()) {
//					sql = "INSERT INTO " + TableList.VH_TEMPSPL_PERMIT_QR
//							+ " select state_cd,off_cd,appl_no,regn_no,pmt_no,doc_id,qrhash,op_dt,current_timestamp,? "
//							+ " from " + TableList.VT_TEMPSPL_PERMIT_QR + " where regn_no = ? and pmt_no=?";
//					ps = tmgr.prepareStatement(sql);
//					ps.setLong(1, emp_cd);
//					ps.setString(2, qrdobj.getRegnNO());
//					ps.setString(3, qrdobj.getPmtNo());
//					ps.executeUpdate();
//					sql = "DELETE FROM " + TableList.VT_TEMPSPL_PERMIT_QR + " where regn_no = ? and pmt_no=?";
//					ps = tmgr.prepareStatement(sql);
//					ps.setString(1, qrdobj.getRegnNO());
//					ps.setString(2, qrdobj.getPmtNo());
//					ps.executeUpdate();
//
//				}
//				sql = "INSERT INTO " + TableList.VT_TEMPSPL_PERMIT_QR
//						+ "(state_cd,off_cd,appl_no,regn_no,pmt_no,doc_id,qrhash,op_dt)\n"
//						+ " VALUES (?,?,?,?,?,?,?,current_timestamp)";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, qrdobj.getStateCD());
//				ps.setInt(2, qrdobj.getOffCD());
//				ps.setString(3, qrdobj.getApplNO());
//				ps.setString(4, qrdobj.getRegnNO());
//				ps.setString(5, qrdobj.getPmtNo());
//				ps.setString(6, qrdobj.getPmtDocId());
//				ps.setString(7, qrdobj.getQrhash());
//				ps.executeUpdate();
//				tmgr.commit();
//			} else {
//				throw new VahanException("Registration / Application No / Permit No Detail not found.");
//			}
//		} catch (VahanException vex) {
//			throw vex;
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + "Regn No :" + qrdobj.getRegnNO() + ex.getStackTrace()[0]);
//			throw new VahanException("Problem occurred during record insert in PERMIT QR code");
//		}
//	}
//
//	public static void insertForQRDetails(QrCodeDobj qrDobj, TransactionManager tmgr) throws VahanException {
//		RowSet rs = null;
//		if (qrDobj != null) {
//			try {
//				if (qrDobj.getDocument_type() == DocumentType.RECEIPT_QR && qrDobj.getReceiptNo() != null
//						&& !qrDobj.isCancel_receipt()) {
//					String qrhash = Util.getEncriptString(qrDobj.getDocument_type());
//					qrDobj.setQrhash(qrhash);
//					ServerUtil.insertQRReceiptDetails(qrDobj, tmgr);
//					return;
//				}
//				if (qrDobj.getDocument_type() == DocumentType.VT_PERMIT_PARTA_QR
//						|| qrDobj.getDocument_type() == DocumentType.VT_PERMIT_PARTB_QR) {
//					String qrhash = Util.getEncriptString(qrDobj.getDocument_type());
//					qrDobj.setQrhash(qrhash);
//					ServerUtil.insertAndMoveToHistoryPERMITQRDetails(qrDobj, tmgr);
//					return;
//				}
//				if (qrDobj.getDocument_type() == DocumentType.VT_PERMIT_AUTH_QR) {
//					String qrhash = Util.getEncriptString(qrDobj.getDocument_type());
//					qrDobj.setQrhash(qrhash);
//					ServerUtil.insertAndMovePERMITAuthQRDetails(qrDobj, tmgr);
//					return;
//				}
//				if (qrDobj.getDocument_type() == DocumentType.VT_TEMP_PERMIT_QR
//						|| qrDobj.getDocument_type() == DocumentType.VT_SPL_PERMIT_QR) {
//					String qrhash = Util.getEncriptString(qrDobj.getDocument_type());
//					qrDobj.setQrhash(qrhash);
//					ServerUtil.insertAndMoveToHistoryTempSplPERMITQRDetails(qrDobj, tmgr);
//					return;
//				}
//				if (qrDobj.getDocument_type() == DocumentType.VT_PERMIT_SURRENDER_QR) {
//					String qrhash = Util.getEncriptString(qrDobj.getDocument_type());
//					qrDobj.setQrhash(qrhash);
//					ServerUtil.MoveAndDeletePermitQRDetails(qrDobj, tmgr);
//					return;
//				} else {
//					throw new VahanException("Invalid Document Type , Please try with valid Document Type.");
//				}
//			} catch (VahanException vex) {
//				throw vex;
//			}
//		}
//	}
//
//	public static void insertAndMovePERMITAuthQRDetails(QrCodeDobj qrdobj, TransactionManager tmgr)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		RowSet rs = null;
//		long emp_cd = Long.parseLong(Util.getEmpCode());
//		try {
//			if (qrdobj != null && !CommonUtils.isNullOrBlank(qrdobj.getApplNO())
//					&& !CommonUtils.isNullOrBlank(qrdobj.getRegnNO())) {
//				sql = "select * from " + TableList.VT_PERMIT_AUTH_QR + " WHERE regn_no = ? ";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, qrdobj.getRegnNO());
//				rs = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//				if (rs.next()) {
//					sql = "INSERT INTO " + TableList.VH_PERMIT_AUTH_QR + " select *,current_timestamp,? " + " from "
//							+ TableList.VT_PERMIT_AUTH_QR + " where regn_no = ? ";
//					ps = tmgr.prepareStatement(sql);
//					ps.setLong(1, emp_cd);
//					ps.setString(2, qrdobj.getRegnNO());
//					ps.executeUpdate();
//					sql = "DELETE FROM " + TableList.VT_PERMIT_AUTH_QR + " where regn_no = ?  ";
//					ps = tmgr.prepareStatement(sql);
//					ps.setString(1, qrdobj.getRegnNO());
//					ps.executeUpdate();
//
//				}
//				sql = "INSERT INTO " + TableList.VT_PERMIT_AUTH_QR
//						+ "(state_cd,off_cd,appl_no,regn_no,pmt_no,doc_id,qrhash,op_dt)\n"
//						+ " VALUES (?,?,?,?,?,?,?,current_timestamp)";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, qrdobj.getStateCD());
//				ps.setInt(2, qrdobj.getOffCD());
//				ps.setString(3, qrdobj.getApplNO());
//				ps.setString(4, qrdobj.getRegnNO());
//				ps.setString(5, qrdobj.getPmtNo());
//				ps.setString(6, qrdobj.getPmtDocId());
//				ps.setString(7, qrdobj.getQrhash());
//				ps.executeUpdate();
//			} else {
//				throw new VahanException("Registration / Application No should not be blank.");
//			}
//		} catch (VahanException vex) {
//			throw vex;
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException("Problem occurred during getting the permit Home Auth QR details.");
//		}
//	}
//
//	public static String getQRURL(String regnOrRcptOrPmtNO, int dock_type, TransactionManager tmgr)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String qrurl = null;
//		String sql = null;
//		RowSet rs = null;
//		String tableName = null;
//		String whereClause = null;
//		String vahanExpMsg = null;
//		try {
//			switch (dock_type) {
//			
//			case DocumentType.RECEIPT_QR:
//				if (!CommonUtils.isNullOrBlank(regnOrRcptOrPmtNO)) {
//					tableName = TableList.VT_RECEIPT_QR;
//					whereClause = "rcpt_no = '" + regnOrRcptOrPmtNO + "'";
//					break;
//				} else {
//					vahanExpMsg = "Details not found for Receipt No " + regnOrRcptOrPmtNO;
//				}
//				
//			default:
//				vahanExpMsg = "Details not found for Registration/Application no " + regnOrRcptOrPmtNO
//						+ " with dock type " + dock_type + ". Please try after sometime!";
//
//			}
//			if (vahanExpMsg != null) {
//				throw new VahanException(vahanExpMsg);
//			}
//			sql = "select qrhash from " + tableName + " WHERE " + whereClause;
//			ps = tmgr.prepareStatement(sql);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				if (rs.getString("qrhash") != null && !rs.getString("qrhash").isEmpty()) {
//					qrurl = "https://qr.parivahan.gov.in/vq/qr?v=".concat(rs.getString("qrhash"));
//					
//				}
//			}
//			return qrurl;
//		} catch (VahanException ve) {
//			throw ve;
//		} catch (SQLException e) {
//			LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//			throw new VahanException("Problem occurred during getting the QR details.");
//		}
//	}
//
//	public static void MoveAndDeletePermitQRDetails(QrCodeDobj qrdobj, TransactionManager tmgr) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		RowSet rs = null;
//		long emp_cd = Long.parseLong(Util.getEmpCode());
//		try {
//			if (qrdobj != null && !CommonUtils.isNullOrBlank(qrdobj.getApplNO())
//					&& !CommonUtils.isNullOrBlank(qrdobj.getRegnNO())
//					&& !CommonUtils.isNullOrBlank(qrdobj.getPmtNo())) {
//				sql = "select * from " + TableList.VT_PERMIT_QR + " WHERE state_cd=? and regn_no = ? and pmt_no=? ";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, qrdobj.getStateCD());
//				ps.setString(2, qrdobj.getRegnNO());
//				ps.setString(3, qrdobj.getPmtNo());
//				rs = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//				if (rs.next()) {
//					sql = "INSERT INTO " + TableList.VH_PERMIT_QR
//							+ " select state_cd,off_cd,appl_no,regn_no,pmt_no,doc_id,qrhash,op_dt,current_timestamp,?,pmt_type "
//							+ " from " + TableList.VT_PERMIT_QR + " where  regn_no = ? and pmt_no=?";
//					;
//
//					ps = tmgr.prepareStatement(sql);
//					ps.setLong(1, emp_cd);
//					ps.setString(2, qrdobj.getRegnNO());
//					ps.setString(3, qrdobj.getPmtNo());
//					ps.executeUpdate();
//
//					sql = "DELETE FROM " + TableList.VT_PERMIT_QR + " where regn_no = ? and pmt_no=? ";
//					ps = tmgr.prepareStatement(sql);
//					ps.setString(1, qrdobj.getRegnNO());
//					ps.setString(2, qrdobj.getPmtNo());
//					ps.executeUpdate();
//					MoveAndDeletePermitAUTHQRDetails(qrdobj, tmgr);
//				}
//				sql = "INSERT INTO " + TableList.VT_PERMIT_SURRENDER_QR
//						+ "(state_cd,off_cd,appl_no,regn_no,pmt_no,doc_id,qrhash,op_dt,surrender_on,surrender_by)\n"
//						+ " VALUES (?,?,?,?,?,?,?,current_timestamp,current_timestamp,?)";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, qrdobj.getStateCD());
//				ps.setInt(2, qrdobj.getOffCD());
//				ps.setString(3, qrdobj.getApplNO());
//				ps.setString(4, qrdobj.getRegnNO());
//				ps.setString(5, qrdobj.getPmtNo());
//				ps.setString(6, qrdobj.getPmtDocId());
//				ps.setString(7, qrdobj.getQrhash());
//				ps.setLong(8, emp_cd);
//				ps.executeUpdate();
//				// tmgr.commit();
//			} else {
//				throw new VahanException("Registration / Application No should not be blank.");
//			}
//		} catch (VahanException vex) {
//			throw vex;
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException("Problem occurred during getting the permit Home Auth QR details.");
//		}
//	}
//
//	public static void MoveAndDeletePermitAUTHQRDetails(QrCodeDobj qrdobj, TransactionManager tmgr)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		RowSet rs = null;
//		long emp_cd = Long.parseLong(Util.getEmpCode());
//		try {
//			if (qrdobj != null && !CommonUtils.isNullOrBlank(qrdobj.getApplNO())
//					&& !CommonUtils.isNullOrBlank(qrdobj.getRegnNO())
//					&& !CommonUtils.isNullOrBlank(qrdobj.getPmtNo())) {
//				sql = "select * from " + TableList.VT_PERMIT_AUTH_QR + " WHERE regn_no = ? and pmt_no=? ";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, qrdobj.getRegnNO());
//				ps.setString(2, qrdobj.getPmtNo());
//				rs = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//				if (rs.next()) {
//					sql = "INSERT INTO " + TableList.VH_PERMIT_AUTH_QR + " select *,current_timestamp,? " + " from "
//							+ TableList.VT_PERMIT_AUTH_QR + " where regn_no = ? and pmt_no=?";
//					ps = tmgr.prepareStatement(sql);
//					ps.setLong(1, emp_cd);
//					ps.setString(2, qrdobj.getRegnNO());
//					ps.setString(3, qrdobj.getPmtNo());
//					ps.executeUpdate();
//					sql = "DELETE FROM " + TableList.VT_PERMIT_AUTH_QR + " where regn_no = ? and pmt_no=? ";
//					ps = tmgr.prepareStatement(sql);
//					ps.setString(1, qrdobj.getRegnNO());
//					ps.setString(2, qrdobj.getPmtNo());
//					ps.executeUpdate();
//				}
//
//			} else {
//				throw new VahanException("Registration / Application No should not be blank.");
//			}
//		} catch (VahanException vex) {
//			throw vex;
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException("Problem occurred during getting the permit Home Auth QR details.");
//		}
//	}
//
//	public static String moveSurrenderRevertPermitQRDetails(String state_cd, String regn_no, String pmt_no,
//			TransactionManager tmgr) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		RowSet rs, rs1 = null;
//		String appl_no = "";
//		long emp_cd = Long.parseLong(Util.getEmpCode());
//		try {
//			if (!CommonUtils.isNullOrBlank(state_cd) && !CommonUtils.isNullOrBlank(regn_no)
//					&& !CommonUtils.isNullOrBlank(pmt_no)) {
//				sql = "select * from " + TableList.VT_PERMIT_SURRENDER_QR
//						+ " WHERE  state_cd=? and regn_no = ? and pmt_no=? ";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, state_cd);
//				ps.setString(2, regn_no);
//				ps.setString(3, pmt_no);
//				rs = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//				if (rs.next()) {
//					sql = "INSERT INTO " + TableList.VH_PERMIT_SURRENDER_QR + " select *,clock_timestamp(),? "
//							+ " from " + TableList.VT_PERMIT_SURRENDER_QR
//							+ " where state_cd=? and regn_no = ? and pmt_no=?";
//					ps = tmgr.prepareStatement(sql);
//					ps.setLong(1, emp_cd);
//					ps.setString(2, state_cd);
//					ps.setString(3, regn_no);
//					ps.setString(4, pmt_no);
//					ps.executeUpdate();
//					sql = "DELETE FROM " + TableList.VT_PERMIT_SURRENDER_QR
//							+ " where state_cd=? and regn_no = ? and pmt_no=? ";
//					ps = tmgr.prepareStatement(sql);
//					ps.setString(1, state_cd);
//					ps.setString(2, regn_no);
//					ps.setString(3, pmt_no);
//					ps.executeUpdate();
//					sql = "select appl_no from  " + TableList.VH_PERMIT_QR
//							+ " where state_cd=? and regn_no = ? and pmt_no=? order by moved_on desc limit 1";
//					ps = tmgr.prepareStatement(sql);
//					ps.setString(1, state_cd);
//					ps.setString(2, regn_no);
//					ps.setString(3, pmt_no);
//					rs1 = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//					if (rs1.next()) {
//						appl_no = rs1.getString("appl_no");
//					}
//				}
//
//			} else {
//				throw new VahanException("Registration / Permit No should not be blank.");
//			}
//		} catch (VahanException vex) {
//			throw vex;
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException("Problem occurred during getting surrender Restore/Revert permit QR details.");
//		}
//		return appl_no;
//	}
//
//	public static void moveVHToVtPermitQRDetail(String state_cd, String regn_no, String pmt_no, String appl_no,
//			TransactionManager tmgr) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		RowSet rs = null;
//		long emp_cd = Long.parseLong(Util.getEmpCode());
//		try {
//			if (!CommonUtils.isNullOrBlank(state_cd) && !CommonUtils.isNullOrBlank(regn_no)
//					&& !CommonUtils.isNullOrBlank(pmt_no) && !CommonUtils.isNullOrBlank(appl_no)) {
//				sql = "select * from " + TableList.VT_PERMIT_QR + " WHERE  state_cd=? and regn_no = ? and pmt_no=? ";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, state_cd);
//				ps.setString(2, regn_no);
//				ps.setString(3, pmt_no);
//				rs = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//				if (rs.next()) {
//					sql = "INSERT INTO " + TableList.VH_PERMIT_QR
//							+ " select state_cd,off_cd,appl_no,regn_no,pmt_no,doc_id,qrhash,op_dt,current_timestamp,?,pmt_type "
//							+ " from " + TableList.VT_PERMIT_QR + " where state_cd=? and regn_no = ? and pmt_no=?";
//					ps = tmgr.prepareStatement(sql);
//					ps.setLong(1, emp_cd);
//					ps.setString(2, state_cd);
//					ps.setString(3, regn_no);
//					ps.setString(4, pmt_no);
//					ps.executeUpdate();
//					sql = "DELETE FROM " + TableList.VT_PERMIT_QR + " where state_cd=? and regn_no = ? and pmt_no=? ";
//					ps = tmgr.prepareStatement(sql);
//					ps.setString(1, state_cd);
//					ps.setString(2, regn_no);
//					ps.setString(3, pmt_no);
//					ps.executeUpdate();
//				}
//				sql = "INSERT INTO " + TableList.VT_PERMIT_QR
//						+ " select state_cd,off_cd,appl_no,regn_no,pmt_no,doc_id,qrhash,op_dt,pmt_type from "
//						+ TableList.VH_PERMIT_QR + " where \n"
//						+ "state_cd=? and  regn_no=? and pmt_no=? and appl_no=? ";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, state_cd);
//				ps.setString(2, regn_no);
//				ps.setString(3, pmt_no);
//				ps.setString(4, appl_no);
//				ps.executeUpdate();
//
//			} else {
//				throw new VahanException("Registration / Permit No should not be blank.");
//			}
//		} catch (VahanException vex) {
//			throw vex;
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException("Problem occurred during getting surrender Restore/Revert permit QR details.");
//		}
//
//	}
//
//	/*
//	 * Get temporary/special permit details
//	 */
//	public static NewPermitSpecialTemporaryDetailsDobj set_vt_temp_permit_regnNo_to_dobj(String regn_no)
//			throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		NewPermitSpecialTemporaryDetailsDobj permit_dobj = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("set_vt_temp_permit_regnNo_to_dobj");
//			String query;
//			query = "SELECT a.*, b.descr as catg_desc from " + TableList.VT_TEMP_PERMIT + " a" + " left join "
//					+ TableList.VM_PERMIT_CATG
//					+ " b on b.code=a.pmt_catg and b.state_cd=a.state_cd and b.permit_type=a.pmt_type "
//					+ " where a.regn_no= ? ";
//			ps = tmgr.prepareStatement(query);
//			if (POSValidator.validate(regn_no, "ANS")) {
//				ps.setString(1, regn_no);
//			}
//
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				permit_dobj = new NewPermitSpecialTemporaryDetailsDobj();
//				permit_dobj.setAppl_no(rs.getString("appl_no"));
//				permit_dobj.setRegn_no(rs.getString("regn_no"));
//				permit_dobj.setPmt_type(rs.getInt("pmt_type"));
//				permit_dobj.setPmt_catg(rs.getInt("pmt_catg"));
//				permit_dobj.setState_cd(rs.getString("state_cd"));
//				permit_dobj.setOff_cd(rs.getInt("off_cd"));
//				permit_dobj.setPur_cd(rs.getInt("pur_cd"));
//				permit_dobj.setPmt_no(rs.getString("pmt_no"));
//				permit_dobj.setValid_from(rs.getDate("valid_from"));
//				permit_dobj.setValid_upto((rs.getDate("valid_upto")));
//				permit_dobj.setIssue_dt(rs.getDate("issue_dt"));
//
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return permit_dobj;
//	}
//
//	public static void insertQRReceiptDetails(QrCodeDobj qrdobj, TransactionManager tmgr) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		RowSet rs = null;
//		long emp_cd = Long.parseLong(Util.getEmpCode());
//		try {
//			if (qrdobj != null && !CommonUtils.isNullOrBlank(qrdobj.getApplNO())
//					&& !CommonUtils.isNullOrBlank(qrdobj.getReceiptNo())) {
//				sql = "INSERT INTO " + TableList.VT_RECEIPT_QR + "(state_cd,off_cd,appl_no,rcpt_no,qrhash)\n"
//						+ " VALUES (?,?,?,?,?)";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, qrdobj.getStateCD());
//				ps.setInt(2, qrdobj.getOffCD());
//				ps.setString(3, qrdobj.getApplNO());
//				ps.setString(4, qrdobj.getReceiptNo());
//				ps.setString(5, qrdobj.getQrhash());
//				ps.executeUpdate();
//
//			} else {
//				throw new VahanException("Receipt No should not be blank.");
//			}
//		} catch (VahanException vex) {
//			throw vex;
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException("Problem occurred during inserting the QR data for Receipt details.");
//		}
//	}
//
//	public static String getPmtTypeByPmtNo(TransactionManager tmgr, String regn_no, String pmt_no)
//			throws VahanException {
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String sql = null;
//		String pmt_type = null;
//		try {
//			sql = "SELECT pmt_type from " + TableList.VT_PERMIT + " where state_cd = ? and regn_no=? and pmt_no = ?";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, Util.getUserStateCode());
//			ps.setString(2, regn_no);
//			ps.setString(3, pmt_no);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				pmt_type = rs.getString("pmt_type");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//		}
//		return pmt_type;
//	}
//
//	public static void insertAndMoveToHistoryPERMITQRDetails(QrCodeDobj qrdobj, TransactionManager tmgr)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		RowSet rs = null;
//		RowSet rs1 = null;
//		long emp_cd = Long.parseLong(Util.getEmpCode());
//		try {
//			if (qrdobj != null && !CommonUtils.isNullOrBlank(qrdobj.getApplNO())
//					&& !CommonUtils.isNullOrBlank(qrdobj.getRegnNO())
//					&& !CommonUtils.isNullOrBlank(qrdobj.getPmtNo())) {
//				sql = "select * from " + TableList.VT_PERMIT_QR + " WHERE regn_no = ? and state_cd<>'PB'";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, qrdobj.getRegnNO());
//				rs = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//				if (rs.next()) {
//					if ((!rs.getString("appl_no").equalsIgnoreCase(qrdobj.getApplNO()))
//							&& (rs.getString("pmt_no").equalsIgnoreCase(qrdobj.getPmtNo()))) {
//						sql = "INSERT INTO " + TableList.VH_PERMIT_QR
//								+ " select state_cd,off_cd,appl_no,regn_no,pmt_no,doc_id,qrhash,op_dt,current_timestamp,?,pmt_type "
//								+ " from " + TableList.VT_PERMIT_QR + " where regn_no = ?";
//						ps = tmgr.prepareStatement(sql);
//						ps.setLong(1, emp_cd);
//						ps.setString(2, qrdobj.getRegnNO());
//						ps.executeUpdate();
//						sql = "DELETE FROM " + TableList.VT_PERMIT_QR + " where regn_no = ?";
//						ps = tmgr.prepareStatement(sql);
//						ps.setString(1, qrdobj.getRegnNO());
//						ps.executeUpdate();
//					}
//				} else {
//					sql = "select * from " + TableList.VT_PERMIT_QR + " WHERE regn_no = ? and state_cd='PB'";
//					ps = tmgr.prepareStatement(sql);
//					ps.setString(1, qrdobj.getRegnNO());
//					rs1 = tmgr.fetchDetachedRowSetWithoutTrim_No_release();
//					while (rs1.next()) {
//						if ((!rs1.getString("appl_no").equalsIgnoreCase(qrdobj.getApplNO()))
//								&& ((rs1.getString("pmt_no").equalsIgnoreCase(qrdobj.getPmtNo())) && "101,102,103,104"
//										.contains(getPmtTypeByPmtNo(tmgr, qrdobj.getRegnNO(), qrdobj.getPmtNo())))) {
//							sql = "INSERT INTO " + TableList.VH_PERMIT_QR
//									+ " select state_cd,off_cd,appl_no,regn_no,pmt_no,doc_id,qrhash,op_dt,current_timestamp,?,pmt_type "
//									+ " from " + TableList.VT_PERMIT_QR + " where regn_no = ?";
//							ps = tmgr.prepareStatement(sql);
//							ps.setLong(1, emp_cd);
//							ps.setString(2, qrdobj.getRegnNO());
//							ps.executeUpdate();
//							sql = "DELETE FROM " + TableList.VT_PERMIT_QR + " where regn_no = ?";
//							ps = tmgr.prepareStatement(sql);
//							ps.setString(1, qrdobj.getRegnNO());
//							ps.executeUpdate();
//						}
//						// else if
//						// ((!rs1.getString("appl_no").equalsIgnoreCase(qrdobj.getApplNO()))
//						// &&
//						// (rs1.getString("regn_no").equalsIgnoreCase(qrdobj.getRegnNO())
//						// &&
//						// (!rs1.getString("pmt_no").equalsIgnoreCase(qrdobj.getPmtNo()))
//						// && "105,106".contains(getPmtTypeByPmtNo(tmgr,
//						// qrdobj.getRegnNO(), qrdobj.getPmtNo()))
//						// && (getPmtTypeByPmtNo(tmgr, rs1.getString("regn_no"),
//						// rs1.getString("pmt_no")).equalsIgnoreCase(getPmtTypeByPmtNo(tmgr,
//						// qrdobj.getRegnNO(), qrdobj.getPmtNo()))))) {
//						// sql = "INSERT INTO " + TableList.VH_PERMIT_QR
//						// + " select *,current_timestamp,? "
//						// + " from " + TableList.VT_PERMIT_QR + " where regn_no
//						// = ? and pmt_no=?";
//						// ps = tmgr.prepareStatement(sql);
//						// ps.setLong(1, emp_cd);
//						// ps.setString(2, qrdobj.getRegnNO());
//						// ps.setString(3, qrdobj.getPmtNo());
//						// ps.executeUpdate();
//						// sql = "DELETE FROM " + TableList.VT_PERMIT_QR + "
//						// where regn_no = ? and pmt_no=?";
//						// ps = tmgr.prepareStatement(sql);
//						// ps.setString(1, qrdobj.getRegnNO());
//						// ps.setString(2, qrdobj.getPmtNo());
//						// ps.executeUpdate();
//						// }
//					}
//				}
//				sql = "INSERT INTO " + TableList.VT_PERMIT_QR
//						+ "(state_cd,off_cd,appl_no,regn_no,pmt_no,doc_id,qrhash,op_dt,pmt_type)\n"
//						+ " VALUES (?,?,?,?,?,?,?,current_timestamp,?)";
//				ps = tmgr.prepareStatement(sql);
//				ps.setString(1, qrdobj.getStateCD());
//				ps.setInt(2, qrdobj.getOffCD());
//				ps.setString(3, qrdobj.getApplNO());
//				ps.setString(4, qrdobj.getRegnNO());
//				ps.setString(5, qrdobj.getPmtNo());
//				ps.setString(6, qrdobj.getPmtDocId());
//				ps.setString(7, qrdobj.getQrhash());
//				ps.setInt(8, qrdobj.getPmt_type());
//				ps.executeUpdate();
//			} else {
//				throw new VahanException("Registration / Application No / Permit No Detail not found.");
//			}
//		} catch (VahanException vex) {
//			throw vex;
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + "Regn No :" + qrdobj.getRegnNO() + ex.getStackTrace()[0]);
//			throw new VahanException("Problem occurred during record insert in PERMIT QR code");
//		}
//	}
//
//	public static boolean isDuplicateTransactionForPaymentforvp_details(String state_cd, int off_cd, String transID,
//			TransactionManager tmgr) throws VahanException, SQLException {
//		
//		String sql2 = "SELECT * FROM " + TableList.vp_details + " WHERE transaction_no=? ";
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		TransactionManager iTmgr = null;
//		
//		iTmgr = new TransactionManager("isDuplicateTransactionForPaymentforvp_details");
//		
//		try {
//
//			ps = iTmgr.prepareStatement(sql2);
//			if (POSValidator.validate(transID, "AN")) {
//				ps.setString(1, transID);
//			}
//			
//			rs = iTmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (Exception ex) {
//			LOGGER.error("inside method: isDuplicateTransactionForPaymentforvp_details" + ex.toString() + " "
//					+ ex.getStackTrace()[0]);
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//			} catch (SQLException ex) {
//				LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			} catch (Exception e) {
//				LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//
//			}
//		}
//		return false;
//	}
//
//	public static boolean checkdatainPGIVt_temp_approve(String transID) throws VahanException {
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		TransactionManagerInterface tmgr = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("checkdatainPGIVt_temp_approve");
//			String sqlTmpApprove = "SELECT * FROM vahanpgi.vt_temp_approve WHERE payment_id=?";
//			ps = tmgr.prepareStatement(sqlTmpApprove);
//			if (POSValidator.validate(transID, "AN")) {
//				ps.setString(1, transID);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				return false;
//			}
//		} catch (SQLException se) {
//			LOGGER.error(se);
//			throw new VahanException(se.getMessage());
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//				}
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e);
//			}
//		}
//		return true;
//	}
//
//	public static boolean isTaxPaidOrCleared(String regNo, String stateCd, int offCd) throws VahanException {
//		boolean isTaxPaid = false;
//		Date taxPaidUpto = null;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("isTaxPaidOrCleared");
//			String sql = "Select tax_upto,tax_mode from " + TableList.VT_TAX
//					+ " where regn_no=? and pur_cd=? and state_cd=? and off_cd=? and tax_mode != 'B' order by rcpt_dt desc limit 1 ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, regNo);
//			ps.setInt(2, TableConstants.TM_PASS_TAX);
//			ps.setString(3, stateCd);
//			ps.setInt(4, offCd);
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				taxPaidUpto = rs.getDate("tax_upto");
//			} else {
//				rs.close();
//				if (ps != null) {
//					ps.close();
//				}
//			}
//			Date taxClearFrom = null;
//			Date taxClearTo = null;
//			sql = "Select clear_fr,clear_to from " + TableList.VT_TAX_CLEAR
//					+ " where regn_no=? and pur_cd=? and state_cd=? and off_cd=? order by op_dt desc limit 1 ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, regNo);
//			ps.setInt(2, TableConstants.TM_PASS_TAX);
//			ps.setString(3, stateCd);
//			ps.setInt(4, offCd);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				taxClearFrom = rs.getDate("clear_fr");
//				taxClearTo = rs.getDate("clear_to");
//			} else {
//				rs.close();
//			}
//			if (taxPaidUpto != null && DateUtils.compareDates(DateUtils.getCurrentLocalDate(), taxPaidUpto) <= 1) {
//				return isTaxPaid = true;
//			} else if ((taxClearFrom != null && taxClearTo == null) || (taxClearTo != null
//					&& DateUtils.compareDates(DateUtils.getCurrentLocalDate(), taxClearTo) <= 1)) {
//				return isTaxPaid = true;
//			}
//			return isTaxPaid;
//		} catch (Exception e) {
//			LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//			throw new VahanException(
//					"Error in Fetching Details of Tax Paid or Clear Details, Please Contact to the System Administrator.");
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//				}
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e);
//			}
//		}
//	}
//
//	public static String getFareMtrDetail(int purCd, PermitOwnerDetailDobj dobj) {
//		String sql = null, fareMtrNo = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		RowSet rs = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("ServerUtil.getFareMtrDetail");
//
//			if (dobj != null && dobj.getFit_upto() != null) {
//				sql = "SELECT fare_mtr_no FROM " + TableList.VT_FITNESS
//						+ " WHERE regn_no=? and state_cd=? and off_cd=? order by op_dt desc limit 1";
//				ps = tmgr.prepareStatement(sql);
//				if (POSValidator.validate(dobj.getRegn_no(), "ANS")) {
//					ps.setString(1, dobj.getRegn_no());
//				}
//				ps.setString(2, dobj.getState_cd());
//				ps.setInt(3, dobj.getOff_cd());
//
//				rs = tmgr.fetchDetachedRowSet();
//				if (rs.next()) {
//					fareMtrNo = rs.getString("fare_mtr_no");
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e.getMessage());
//
//				}
//			}
//		}
//
//		return fareMtrNo;
//	}
//
//	public static boolean isGreenTaxPaidOrCleared(String regNo, String stateCd, int offCd) throws VahanException {
//		boolean isTaxPaid = false;
//		Date taxPaidUpto = null;
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("isGreenTaxPaidOrCleared");
//			String sql = "Select tax_upto,tax_mode from " + TableList.VT_TAX
//					+ " where regn_no=? and pur_cd=? and state_cd=? and off_cd=? and tax_mode != 'B' order by rcpt_dt desc limit 1 ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, regNo);
//			ps.setInt(2, TableConstants.TM_GREEN_TAX);
//			ps.setString(3, stateCd);
//			ps.setInt(4, offCd);
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				taxPaidUpto = rs.getDate("tax_upto");
//			} else {
//				rs.close();
//				if (ps != null) {
//					ps.close();
//				}
//			}
//			Date taxClearFrom = null;
//			Date taxClearTo = null;
//			sql = "Select clear_fr,clear_to from " + TableList.VT_TAX_CLEAR
//					+ " where regn_no=? and pur_cd=? and state_cd=? and off_cd=? order by op_dt desc limit 1 ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, regNo);
//			ps.setInt(2, TableConstants.TM_GREEN_TAX);
//			ps.setString(3, stateCd);
//			ps.setInt(4, offCd);
//			rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				taxClearFrom = rs.getDate("clear_fr");
//				taxClearTo = rs.getDate("clear_to");
//			} else {
//				rs.close();
//			}
//			if (taxPaidUpto != null && DateUtils.compareDates(DateUtils.getCurrentLocalDate(), taxPaidUpto) <= 1) {
//				return isTaxPaid = true;
//			} else if ((taxClearFrom != null && taxClearTo == null) || (taxClearTo != null
//					&& DateUtils.compareDates(DateUtils.getCurrentLocalDate(), taxClearTo) <= 1)) {
//				return isTaxPaid = true;
//			}
//			return isTaxPaid;
//		} catch (Exception e) {
//			LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//			throw new VahanException(
//					"Error in Fetching Details of Tax Paid or Clear Details, Please Contact to the System Administrator.");
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//				}
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e);
//			}
//		}
//	}
//
//	public static String getVaTempPermitReason(String appl_no, String state_cd, int off_cd) throws VahanException {
//
//		String reason = null;
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String sql = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getVaTempPermitReason");
//			sql = "Select split_part(max(reason), ':', 1) as reason from " + TableList.VA_TEMP_PERMIT_ONLINE
//					+ " where appl_no=? and state_cd=? and off_cd =? and period_mode=?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(appl_no, "ANS")) {
//				ps.setString(1, appl_no);
//			}
//			ps.setString(2, state_cd);
//			ps.setInt(3, off_cd);
//			ps.setString(4, "M");
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				reason = rs.getString("reason");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return reason;
//	}
//
//	/*
//	 * Get Lease Agreement details.
//	 */
//	public static String getLeaseAgreement(String regnNo, String state_cd, int off_cd) throws VahanException {
//
//		String hpType = null;
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String sql = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getLeaseAgreement");
//			sql = "Select hp_type from " + TableList.VT_HYPTH
//					+ " where regn_no=? and state_cd=? and off_cd =? and hp_type=?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//			ps.setString(2, state_cd);
//			ps.setInt(3, off_cd);
//			ps.setString(4, "LA");
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				hpType = rs.getString("hp_Type");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return hpType;
//	}
//
//	public static void updateOnlineVhaDetailsAndVhaStatus(String transNo, String regnNo, String status)
//			throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManager tmgr = null;
//		String sqlTempApplTrans = "INSERT INTO onlineschema.vha_details_appl("
//				+ " appl_no, pur_cd, appl_dt, regn_no, user_id, user_type, entry_ip,"
//				+ " entry_status, confirm_ip, confirm_status, confirm_date, state_cd," + " off_cd, moved_on, moved_by)"
//				+ " SELECT appl_no, pur_cd, appl_dt, regn_no, user_id, user_type, entry_ip,"
//				+ " ?, confirm_ip, confirm_status, confirm_date, state_cd,"
//				+ " off_cd, current_timestamp, ?  FROM onlineschema.va_details_appl where appl_no = ? and regn_no = ?  ";
//		String appl_no = ServerUtil.formatTransID(transNo, 'P');
//		try {
//			tmgr = new TransactionManager("updateOnlineVhaDetailsAndVhaStatus");
//			ps = tmgr.prepareStatement(sqlTempApplTrans);
//			if (POSValidator.validate(status, "A")) {
//				ps.setString(1, status);
//			}
//			if (POSValidator.validate(Util.getMobile_no(), "N")) {
//				ps.setString(2, Util.getMobile_no());
//			}
//			if (POSValidator.validate(appl_no, "AN")) {
//				ps.setString(3, appl_no);
//			}
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(4, regnNo);
//			}
//
//			if (ps.executeUpdate() < 1) {
//				throw new VahanException(" Unable To Update table ");
//			} else {
//				LOGGER.info("updateOnlineVhaDetailsAndVhaStatus failed case vha move problem " + status + "" + transNo);
//				sqlTempApplTrans = "DELETE FROM onlineschema.va_details_appl where appl_no = ? and regn_no = ? ";
//				ps = tmgr.prepareStatement(sqlTempApplTrans);
//				if (POSValidator.validate(appl_no, "AN")) {
//					ps.setString(1, appl_no);
//				}
//				if (POSValidator.validate(regnNo, "ANS")) {
//					ps.setString(2, regnNo);
//				}
//				if (ps.executeUpdate() < 1) {
//					throw new VahanException(" Unable To Update table ");
//				} else {
//					sqlTempApplTrans = "INSERT INTO onlineschema.vha_status_appl("
//							+ " state_cd, off_cd, appl_no, pur_cd, flow_slno, file_movement_slno,"
//							+ " action_cd, seat_cd, cntr_id, status, office_remark, public_remark,"
//							+ " file_movement_type, emp_cd, op_dt, moved_from_online, moved_on)"
//							+ " SELECT state_cd, off_cd, appl_no, pur_cd, flow_slno, file_movement_slno,"
//							+ " action_cd, seat_cd, cntr_id, status, office_remark, public_remark,"
//							+ " file_movement_type, emp_cd, op_dt, moved_from_online, current_timestamp"
//							+ " FROM onlineschema.va_status_appl where appl_no = ? ";
//					ps = tmgr.prepareStatement(sqlTempApplTrans);
//					if (POSValidator.validate(appl_no, "AN")) {
//						ps.setString(1, appl_no);
//					}
//					if (ps.executeUpdate() < 1) {
//						throw new VahanException(" Unable To Update table ");
//					} else {
//						LOGGER.info("updateOnlineVhaDetailsAndva_status_appl failed case vha move problem " + status
//								+ "" + transNo);
//						sqlTempApplTrans = "DELETE FROM onlineschema.va_status_appl WHERE appl_no = ? ";
//						ps = tmgr.prepareStatement(sqlTempApplTrans);
//						if (POSValidator.validate(appl_no, "AN")) {
//							ps.setString(1, appl_no);
//						}
//						if (ps.executeUpdate() < 1) {
//							throw new VahanException(" Unable To Update table ");
//						}
//					}
//				}
//			}
//			tmgr.commit();
//		} catch (SQLException se) {
//			LOGGER.error(se);
//			throw new VahanException(" Unable To Update table ");
//		} catch (Exception e) {
//			LOGGER.error(e);
//			throw new VahanException(" Unable To Update table ");
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//			} catch (SQLException se) {
//				LOGGER.error(se);
//			}
//		}
//
//	}
//
//	public static void moveFromVTtoVHTempApprove(String transID, String regnNo, String statusDesc)
//			throws SQLException, VahanException {
//		PreparedStatement pstmt = null;
//		TransactionManager tmgr = null;
//		try {
//			tmgr = new TransactionManager("moveFromVTtoVHTempApprove");
//			String sqlInsert = "INSERT INTO " + TableList.vh_temp_approve
//					+ " (state_cd, off_cd, transaction_no, pur_cd, desp, deal_cd, op_dt, reason, conf_dt, user_id, dealer_cd) "
//					+ " SELECT state_cd, off_cd, transaction_no, pur_cd, desp, deal_cd, op_dt ,'" + statusDesc
//					+ "',CURRENT_TIMESTAMP,null,0 FROM " + TableList.VT_TEMP_APPROVE
//					+ " WHERE transaction_no=? and DEAL_CD=?";
//			pstmt = tmgr.prepareStatement(sqlInsert);
//			if (POSValidator.validate(transID, "AN")) {
//				pstmt.setString(1, transID);
//			}
//			if (POSValidator.validate(regnNo, "ANS")) {
//				pstmt.setString(2, regnNo);
//			}
//			
//			int rowsUpdated = pstmt.executeUpdate();
//			if (rowsUpdated > 0) {
//				LOGGER.info("insertion vh_temp_approve " + transID);
//				String sqlDel = "DELETE FROM  " + TableList.VT_TEMP_APPROVE + " WHERE transaction_no=? and DEAL_CD=?";
//				pstmt = tmgr.prepareStatement(sqlDel);
//				if (POSValidator.validate(transID, "AN")) {
//					pstmt.setString(1, transID);
//				}
//				if (POSValidator.validate(regnNo, "ANS")) {
//					pstmt.setString(2, regnNo);
//				}
//				
//				if (pstmt.executeUpdate() < 1) {
//					throw new VahanException(" Unable To delete details ");
//				} else {
//					LOGGER.info("delete vt_temp_approve " + transID);
//				}
//			} else {
//				throw new VahanException(" Unable To insert details ");
//			}
//			tmgr.commit();
//		} catch (SQLException se) {
//			LOGGER.error(se);
//			throw se;
//		} catch (Exception e) {
//			LOGGER.error(e);
//			throw new VahanException("Unable to move vtTovhTempApprove");
//		}
//
//		finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//					pstmt = null;
//				}
//			} catch (Exception e) {
//				LOGGER.error(e);
//				throw new VahanException("Unable to move vtTovhTempApprove");
//			}
//		}
//	}
//
//	public static String getPeriodMode(String regnNo, String state_cd, int off_cd) throws VahanException {
//
//		String periodMode = null;
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String sql = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getPeriodMode");
//			sql = "Select period_mode from " + TableList.VA_TEMP_PERMIT_ONLINE
//					+ " where regn_no=? and state_cd=? and off_cd =?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//			ps.setString(2, state_cd);
//			ps.setInt(3, off_cd);
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				periodMode = rs.getString("period_mode");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return periodMode;
//	}
//
//	public static int selectInRouteTimeChart(String appl_no, String state_cd, int off_cd) throws VahanException {
//		int count = 0;
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String sql = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("selectInRouteTimeChart");
//			sql = "Select * from onlinepermit.va_permit_route_time_chart where appl_no=? and state_cd=? and off_cd =?";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, appl_no);
//			ps.setString(2, state_cd);
//			ps.setInt(3, off_cd);
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				count = rs.getRow();
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return count;
//	}
//
//	public void insertIntoVt_server_route_log(TransactionManager tmgr, String transId, Status_dobj dobj,
//			String serverIp) throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		try {
//
//			sql = " INSERT INTO " + TableList.VT_SERVER_ROUTE_LOG + "(state_cd, off_cd, appl_no, regn_no,"
//					+ "  from_server_ip, backto_server_ip)" + " VALUES (?, ?, ?, ?, ?, ?)";
//
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			ps.setString(i++, dobj.getState_cd());
//			ps.setInt(i++, dobj.getOff_cd());
//			ps.setString(i++, transId);
//			ps.setString(i++, dobj.getRegn_no());
//			ps.setString(i++, serverIp);
//			ps.setString(i++, "");
//			ps.executeUpdate();
//		} catch (SQLException ex) {
//			LOGGER.error("insertIntoVt_server_route_log.." + ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException(ex.getMessage());
//		}
//	}
//
//	public String getServerIpFromVT_SERVER_ROUTE_LOG(String appl_no, String state_cd, int off_cd)
//			throws VahanException {
//
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String bfrpayserverIp = "";
//
//		try {
//
//			String query = "SELECT  from_server_ip  FROM " + TableList.VT_SERVER_ROUTE_LOG
//					+ " where state_cd=? and off_cd=? and appl_no=? ";
//
//			tmgr = new TransactionManagerReadOnly("getServerIpFromVT_SERVER_ROUTE_LOG");
//
//			ps = tmgr.prepareStatement(query);
//			int i = 1;
//			ps.setString(i++, state_cd);
//			ps.setInt(i++, off_cd);
//			ps.setString(i++, appl_no);
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				bfrpayserverIp = rs.getString("from_server_ip");
//			}
//
//		} catch (Exception ex) {
//			LOGGER.error("Error in fetching getServerIpFromVT_SERVER_ROUTE_LOG for applNo [ " + appl_no + "].." + ex);
//			throw new VahanException(
//					"Error in fetching getServerIpFromVT_SERVER_ROUTE_LOG for applNo [ " + appl_no + "]");
//
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e);
//			}
//		}
//
//		return bfrpayserverIp;
//	}
//
//	public void upDateBackto_server_ipVt_server_route_log(String appl_no, String state_cd, int off_cd)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		TransactionManager tmgr = null;
//		try {
//			sql = " UPDATE " + TableList.VT_SERVER_ROUTE_LOG
//					+ " SET backto_server_ip=? where state_cd=? and off_cd=? and appl_no=? ";
//			tmgr = new TransactionManager("upDateBackto_server_ipVt_server_route_log");
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			ps.setString(i++, Util.getServerIpAddDb());
//			ps.setString(i++, state_cd);
//			ps.setInt(i++, off_cd);
//			ps.setString(i++, appl_no);
//			ps.executeUpdate();
//			tmgr.commit();
//		} catch (SQLException ex) {
//			LOGGER.error("upDateBackto_server_ipVt_server_route_log.." + ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException("upDateBackto_server_ipVt_server_route_log.." + ex.getMessage());
//		} finally {
//			try {
//
//				if (tmgr != null) {
//					tmgr.release();
//				}
//				if (ps != null) {
//					ps.close();
//				}
//			} catch (Exception e) {
//				LOGGER.info(e);
//				throw new VahanException(e.getMessage());
//			}
//		}
//	}
//
//	public void upDateVt_server_route_logCheckPending(String appl_no, String state_cd, int off_cd)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		TransactionManager tmgr = null;
//		try {
//			tmgr = new TransactionManager("upDateVt_server_route_logCheckPending");
//			int result = movetoVHFromVt_server_route_log(tmgr, appl_no, state_cd, off_cd);
//			if (result > 0) {
//				sql = " UPDATE " + TableList.VT_SERVER_ROUTE_LOG
//						+ " SET from_server_ip=?,backto_server_ip=? where state_cd=? and off_cd=? and appl_no=? ";
//
//				ps = tmgr.prepareStatement(sql);
//				int i = 1;
//				ps.setString(i++, Util.getServerIpAddDb());
//				ps.setString(i++, "");
//				ps.setString(i++, state_cd);
//				ps.setInt(i++, off_cd);
//				ps.setString(i++, appl_no);
//				ps.executeUpdate();
//				tmgr.commit();
//			}
//		} catch (SQLException ex) {
//			LOGGER.error("upDateVt_server_route_logCheckPending.." + ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException("upDateVt_server_route_logCheckPending.." + ex.getMessage());
//		} finally {
//			try {
//
//				if (tmgr != null) {
//					tmgr.release();
//				}
//				if (ps != null) {
//					ps.close();
//				}
//			} catch (Exception e) {
//				LOGGER.info(e);
//				throw new VahanException(e.getMessage());
//			}
//		}
//	}
//
//	public int movetoVHFromVt_server_route_log(TransactionManager tmgr, String transId, String state_cd, int off_cd)
//			throws VahanException {
//		PreparedStatement ps = null;
//		String sql = null;
//		int result = 0;
//		try {
//			sql = " INSERT INTO " + TableList.VH_SERVER_ROUTE_LOG
//					+ " (state_cd, off_cd, appl_no, regn_no, from_server_ip, backto_server_ip,transaction_dt, moved_on) "
//					+ " SELECT state_cd, off_cd, appl_no, regn_no, from_server_ip, backto_server_ip,transaction_dt,current_timestamp FROM "
//					+ TableList.VT_SERVER_ROUTE_LOG + " where state_cd=? and off_cd=? and appl_no=? ";
//
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//			ps.setString(i++, state_cd);
//			ps.setInt(i++, off_cd);
//			ps.setString(i++, transId);
//			result = ps.executeUpdate();
//		} catch (SQLException ex) {
//			LOGGER.error("movetoVHFromVt_server_route_log.." + ex.toString() + " " + ex.getStackTrace()[0]);
//			throw new VahanException(ex.getMessage());
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//				}
//			} catch (Exception e) {
//				LOGGER.info(e);
//				throw new VahanException(e.getMessage());
//			}
//		}
//		return result;
//	}
//
//	public static boolean isDuplicateTransactionForPaymentforVp_Account(String state_cd, int off_cd, String transID)
//			throws VahanException, SQLException {
//		String sqlVpAccount = "SELECT * FROM " + TableList.VP_ACCOUNT + " WHERE transaction_no=? ";
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		TransactionManagerInterface iTmgr = null;
//		iTmgr = new TransactionManagerReadOnly("isDuplicateTransactionForPaymentforVp_Account");
//		try {
//			ps = iTmgr.prepareStatement(sqlVpAccount);
//			if (POSValidator.validate(transID, "AN")) {
//				ps.setString(1, transID);
//			}
//			rs = iTmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//			} catch (SQLException ex) {
//				LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			} catch (Exception e) {
//				LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//
//			}
//		}
//		return false;
//	}
//
//	public static boolean isDuplicateTransactionForPaymentforVp_Appl_Rcpt_Mapping(String state_cd, int off_cd,
//			String appl_no) throws VahanException, SQLException {
//		String sqlVpAccount = "SELECT * FROM " + TableList.VP_APPL_RCPT_MAPPING + " WHERE appl_no=? ";
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		TransactionManagerInterface iTmgr = null;
//		iTmgr = new TransactionManagerReadOnly("isDuplicateTransactionForPaymentforVp_Appl_Rcpt_Mapping");
//		try {
//			ps = iTmgr.prepareStatement(sqlVpAccount);
//			if (POSValidator.validate(appl_no, "AN")) {
//				ps.setString(1, appl_no);
//			}
//			rs = iTmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//			} catch (SQLException ex) {
//				LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			} catch (Exception e) {
//				LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//
//			}
//		}
//		return false;
//	}
//
//	public static boolean isTempRegionCoveredAvailable(String state_cd, int off_cd, String appl_no)
//			throws VahanException, SQLException {
//		String sqlVpAccount = "SELECT * FROM " + TableList.VA_TEMP_PERMIT_ROUTE_ONLINE
//				+ " WHERE appl_no=? and state_cd=? and off_cd=?";
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		TransactionManagerInterface iTmgr = null;
//		iTmgr = new TransactionManagerReadOnly("isTempRegionCoveredAvailable");
//		try {
//			ps = iTmgr.prepareStatement(sqlVpAccount);
//			if (POSValidator.validate(appl_no, "AN")) {
//				ps.setString(1, appl_no);
//			}
//			ps.setString(2, state_cd);
//			ps.setInt(3, off_cd);
//
//			rs = iTmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//			} catch (SQLException ex) {
//				LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			} catch (Exception e) {
//				LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//
//			}
//		}
//		return false;
//	}
//
//	public static int getVehAgeValidity(NewPermitDetailsDobj ownerDobj, VehicleParameters parameters)
//			throws VahanException {
//		String sql = null;
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		int vehAge = 0;
//		Exception e = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getVehAgeValidity");
//			sql = "SELECT *  FROM " + TableList.VM_VEHICLE_AGE_VALIDITY
//					+ " WHERE state_cd=? and pmt_type in(?,0) and pmt_catg in(?,0) and fuel in(?,0) "
//					+ " order by pmt_type desc, pmt_catg desc";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, ownerDobj.getState_cd());
//			ps.setInt(2, ownerDobj.getPmt_type());
//			ps.setInt(3, ownerDobj.getPmt_catg());
//			ps.setInt(4, ownerDobj.getFuel());
//			RowSet rs = tmgr.fetchDetachedRowSet();
//
//			int counter = 0;
//			while (rs.next()) {
//				vehAge = rs.getInt("veh_age");
//				parameters.setOFF_CD(ownerDobj.getOff_cd());
//				if ((rs.getInt("pmt_type") == ownerDobj.getPmt_type() && rs.getInt("pmt_catg") == 0)
//						|| (rs.getInt("pmt_type") == ownerDobj.getPmt_type()
//								&& rs.getInt("pmt_catg") == ownerDobj.getPmt_catg())) {
//					if (rs.getString("condition_formula").equalsIgnoreCase("true")) {
//						break;
//					} else {
//						String age_formula = FormulaUtils.getSqlFormulaValue(
//								FormulaUtils.replaceTagPermitValues(rs.getString("condition_formula"), parameters),
//								"getVehAgeValidity()");
//						if (!CommonUtils.isNullOrBlank(age_formula) && JSFUtils.isNumeric(age_formula)
//								&& Integer.parseInt(age_formula) > 0) {
//							vehAge = Integer.parseInt(age_formula);
//							break;
//						} else if (FormulaUtils.isCondition(
//								FormulaUtils.replaceTagPermitValues(rs.getString("condition_formula"), parameters),
//								"getVehAgeValidity()")) {
//							break;
//						}
//					}
//				}
//				counter++;
//				if (counter > 1) {
//					throw new VahanException(
//							"Multiple Record Found in Database for Calculating Vehicle Age Validity, Please Contact to the System Administrator.");
//				}
//			}
//
//		} catch (SQLException sq) {
//			e = sq;
//			LOGGER.error(sq.toString() + " " + sq.getStackTrace()[0]);
//		} catch (Exception ex) {
//			e = ex;
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception ee) {
//				LOGGER.error(ee.toString() + " " + ee.getStackTrace()[0]);
//			}
//		}
//
//		if (e != null) {
//			throw new VahanException(
//					"Error in Getting Maximum Vehicle Age of the Vehicle, Please Contact to the System Administrator.");
//		}
//
//		if (vehAge == 0) {
//			if (ownerDobj.getPmt_type() > 0) {
//				throw new VahanException(
//						"Maximum Age of Vehicle Can not be Zero, Please Contact to the System Administrator.");
//			}
//		}
//
//		return vehAge;
//	}
//
//	public static String getTransportVchType(int vh_class) throws VahanException {
//		String transportVchType = "";
//		PreparedStatement psmt = null;
//		TransactionManagerReadOnly tmgr = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("isTransport");
//			String strSQL = "SELECT COALESCE(transport_catg, '') as transport_catg FROM " + TableList.VM_VH_CLASS
//					+ " where vh_class = ?";
//			psmt = tmgr.prepareStatement(strSQL);
//			psmt.setInt(1, vh_class);
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				transportVchType = rs.getString("transport_catg");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//			}
//		}
//
//		if (transportVchType == null) {
//			throw new VahanException("No Transport Vehicle Type Found");
//		}
//		return transportVchType;
//	}
//
//	public static Date dateRanges(Date date, int year, int month, int day_of_month) {
//		java.util.Calendar cal = new GregorianCalendar();
//		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
//		sdf.setTimeZone(TimeZone.getDefault());
//		cal.setTime(date);
//		cal.add(java.util.Calendar.YEAR, year);
//		cal.add(java.util.Calendar.MONTH, month);
//		cal.add(java.util.Calendar.DAY_OF_MONTH, day_of_month);
//		return cal.getTime();
//	}
//
//	public static String isInwardAppl(String appl_no, String state_cd) {
//
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		String sql = "", regn_no = "";
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				tmgr = new TransactionManagerReadOnly("ServerUtil.isInwardAppl");
//
//			} else {
//				tmgr = new TransactionManager("ServerUtil.isInwardAppl");
//			}
//			sql = "SELECT regn_no from onlineschema.va_details_appl details where (details.regn_no=? or details.appl_no=?) and details.state_cd=? order by details.appl_dt desc limit 1";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, appl_no);
//			ps.setString(2, appl_no);
//			ps.setString(3, state_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				regn_no = rs.getString("regn_no");
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(e);
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e);
//				}
//			}
//		}
//
//		return regn_no;
//	}
//
//	public static int getOffCd(String appl_no, String state_cd) {
//
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		String sql = "";
//		int off_cd = 0;
//		try {
//			String regn_no = isInwardAppl(appl_no, state_cd);
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				tmgr = new TransactionManagerReadOnly("ServerUtil.getOffCd");
//
//			} else {
//				tmgr = new TransactionManager("ServerUtil.getOffCd");
//			}
//			if (!CommonUtils.isNullOrBlank(regn_no)) {
//				sql = "SELECT off_cd from vt_owner where regn_no=? and state_cd=?";
//			} else {
//				sql = "SELECT off_cd from onlineschema.va_details_appl details where (details.regn_no=? or details.appl_no=?) and details.state_cd=? order by details.appl_dt desc limit 1";
//			}
//			ps = tmgr.prepareStatement(sql);
//			if (!CommonUtils.isNullOrBlank(regn_no)) {
//				ps.setString(1, regn_no);
//				ps.setString(2, state_cd);
//			} else {
//				ps.setString(1, appl_no);
//				ps.setString(2, appl_no);
//				ps.setString(3, state_cd);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				off_cd = rs.getInt("off_cd");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e);
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e);
//				}
//			}
//		}
//
//		return off_cd;
//	}
//
//	public static String getStateCod(String regn_no) {
//		PreparedStatement ps;
//		RowSet rs;
//		String state_cd = "";
//		TransactionManagerInterface iTmgr = null;
//		String insQuery = null;
//
//		insQuery = "SELECT state_cd from onlineschema.va_details_appl details where details.appl_no=? or details.regn_no=? order by details.appl_dt desc limit 1";
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				iTmgr = new TransactionManagerReadOnly("ServerUtil.getStateCod()");
//
//			} else {
//				iTmgr = new TransactionManager("ServerUtil.getStateCod()");
//			}
//			ps = iTmgr.prepareStatement(insQuery);
//			ps.setString(1, regn_no);
//			ps.setString(2, regn_no);
//			rs = iTmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				state_cd = rs.getString("state_cd");
//			}
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return state_cd;
//	}
//
//	public static boolean isPuccCheck(String state_cd) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		boolean isPucc = false;
//		RowSet rs = null;
//		String sql = null;
//		sql = "select pucc_check from " + TableList.ONLINE_PERMIT_STATE_CONFIGURE + " where state_cd = ?  ";
//		try {
//			tmgr = new TransactionManagerReadOnly("isPuccCheck");
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(i++, state_cd);
//			}
//
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				isPucc = rs.getBoolean("pucc_check");
//			}
//		} catch (Exception ex) {
//			throw new VahanException("Unable to Get Permit Pucc..");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception ee) {
//				LOGGER.error(ee);
//			}
//		}
//
//		return isPucc;
//	}
//
//	public static boolean isEmailCheck(String state_cd) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		boolean isEmailAvailable = false;
//		RowSet rs = null;
//		String sql = null;
//		sql = "select email_check from " + TableList.ONLINE_PERMIT_STATE_CONFIGURE + " where state_cd = ?  ";
//		try {
//			tmgr = new TransactionManagerReadOnly("isEmailCheck");
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(i++, state_cd);
//			}
//
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				isEmailAvailable = rs.getBoolean("email_check");
//			}
//		} catch (Exception ex) {
//			throw new VahanException("Unable to Get Email..");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception ee) {
//				LOGGER.error(ee);
//			}
//		}
//
//		return isEmailAvailable;
//	}
//
//	public static boolean isAllowBlackListedVehicle(String state_cd) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		boolean isAllowBlackListedVehicle = false;
//		RowSet rs = null;
//		String sql = null;
//		sql = "select isAllowBlackListedVehicles from " + TableList.ONLINE_PERMIT_STATE_CONFIGURE
//				+ " where state_cd = ?  ";
//		try {
//			tmgr = new TransactionManagerReadOnly("isAllowBlackListedVehicle");
//			ps = tmgr.prepareStatement(sql);
//			int i = 1;
//
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(i++, state_cd);
//			}
//
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				isAllowBlackListedVehicle = rs.getBoolean("isAllowBlackListedVehicles");
//			}
//		} catch (Exception ex) {
//			throw new VahanException("Unable to Get BlackLListed Details..");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception ee) {
//				LOGGER.error(ee);
//			}
//		}
//
//		return isAllowBlackListedVehicle;
//	}
//
//	/*
//	 * Sanction order permissible date.
//	 */
//
//	public static void getSanctionOrderPermission(String state_cd, String permit_type) throws VahanException {
//
//		String state_code = "", permitType = "";
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String sql = null;
//		String sanctionFromDt = null, sanctionUptoDt = null, currentDate = DateUtil.getCurrentDateDDMMYYY(),
//				msg = "Process of submitting application for Sanction order is closed. May please contact RTO/STA for detailed clarification.";
//		try {
//			tmgr = new TransactionManagerReadOnly("getSanctionOrderPermission");
//			sql = "Select *  from " + TableList.VM_SANCTION_ORDER_PERMISSIBLE + "  where state_cd=?";
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				state_code = rs.getString("state_cd");
//				permitType = rs.getString("permit_type");
//				sanctionFromDt = DateUtils.parseDate(rs.getDate("sanction_from_dt"));
//				sanctionUptoDt = DateUtils.parseDate(rs.getDate("sanction_upto_dt"));
//			}
//			if (state_cd.equalsIgnoreCase(state_code) && permit_type.equalsIgnoreCase(permitType)) {
//				if (DateUtils.compareDates(currentDate, sanctionFromDt) == 1) {
//					throw new VahanException(msg);
//				} else if (DateUtils.compareDates(currentDate, sanctionUptoDt) == 2) {
//					throw new VahanException(msg);
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//	}
//
//	public static String getReceiptNo(String state_cd, String transID) throws VahanException, SQLException {
//		String sql2 = "SELECT rcpt_no FROM " + TableList.vp_details + " WHERE transaction_no=? and state_cd=?";
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String rcptNo = null;
//		TransactionManagerInterface iTmgr = null;
//		iTmgr = new TransactionManagerReadOnly("getReceiptNo");
//		try {
//			ps = iTmgr.prepareStatement(sql2);
//			if (POSValidator.validate(transID, "AN")) {
//				ps.setString(1, transID);
//			}
//			if (POSValidator.validate(state_cd, "AN")) {
//				ps.setString(2, state_cd);
//			}
//			rs = iTmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				rcptNo = rs.getString("rcpt_no");
//			}
//		} catch (Exception ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//			} catch (SQLException ex) {
//				LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			} catch (Exception e) {
//				LOGGER.error(e.toString() + " " + e.getStackTrace()[0]);
//
//			}
//		}
//		return rcptNo;
//	}
//
//	// Added By Kamal Nayan Sahu
//	public static String getAuthTypeFromVT_TEMP_APPL_TRANSACTION(String transNo, String state_cd, int off_cd)
//			throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String authType = "";
//		try {
//			String query = "SELECT  auth_type  FROM " + TableList.VT_TEMP_APPL_TRANSACTION
//					+ " where state_cd=? and off_cd=? and transaction_no=? ";
//			tmgr = new TransactionManagerReadOnly("getAuthTypeFromVT_TEMP_APPL_TRANSACTION");
//			ps = tmgr.prepareStatement(query);
//			int i = 1;
//			ps.setString(i++, state_cd);
//			ps.setInt(i++, off_cd);
//			ps.setString(i++, transNo);
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				authType = rs.getString("auth_type");
//			}
//		} catch (Exception ex) {
//			LOGGER.error(
//					"Error in fetching getAuthTypeFromVT_TEMP_APPL_TRANSACTION for transNo [ " + transNo + "].." + ex);
//			throw new VahanException(
//					"Error in fetching getAuthTypeFromVT_TEMP_APPL_TRANSACTION for transNo [ " + transNo + "]");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e);
//			}
//		}
//		return authType;
//	}
//
//	public static AdjustmentTaxDobj getAdjustTaxDeatilsFromvahan4(String regn_no, String state_cd) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		AdjustmentTaxDobj dobj = null;
//		AdjustmentTaxDobj returndobj = new AdjustmentTaxDobj();
//		String sql = "";
//		String adjustment_type = "";
//		String appl_no = "";
//		double bal_amt = 0.0;
//		double excess_amt = 0.0;
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				tmgr = new TransactionManagerReadOnly("ServerUtil.getAdjustTaxDeatilsFromvahan4()");
//
//			} else {
//				tmgr = new TransactionManager("ServerUtil.getAdjustTaxDeatilsFromvahan4()");
//			}
//			sql = "select appl_no,a.pur_cd,to_char(taxfrom,'dd-MM-yyyy')taxfrom,to_char(taxupto,'dd-MM-yyyy')taxupto,balance_amt,excess_amt,bal_tax_appl_no,b.descr  from vt_refund_excess a left join tm_purpose_mast b on a.pur_cd=b.pur_cd where regn_no=? and road_tax_appl_no is null and  bal_tax_appl_no is null";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, regn_no);
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				dobj = new AdjustmentTaxDobj();
//				dobj.setRegn_no(regn_no);
//				dobj.setPayemt_id(rs.getString("appl_no"));
//				appl_no = dobj.getPayemt_id();
//				dobj.setBalance_amt(rs.getDouble("balance_amt"));
//				dobj.setExcess_amt(rs.getDouble("excess_amt"));
//				bal_amt += dobj.getBalance_amt();
//				excess_amt += dobj.getExcess_amt();
//				dobj.setFrom_dt(rs.getString("taxfrom"));
//				dobj.setUpto_dt(rs.getString("taxupto"));
//				dobj.setTransaction_id(rs.getString("bal_tax_appl_no"));
//				// dobj.setAdjustment_type("D");
//				dobj.setPur_cd(rs.getInt("pur_cd"));
//				dobj.setPur_desc(rs.getString("descr"));
//				returndobj.getAdjustmentlist().add(dobj);
//			}
//			if (returndobj.getAdjustmentlist().size() > 0) {
//
//				returndobj.setPayemt_id(appl_no);
//				if (ServerUtil.checkTaxadjustmentforExcess(state_cd)) {
//
//					if (excess_amt > bal_amt) {
//						returndobj.setExcess_amt(excess_amt - bal_amt);
//						returndobj.setPrevious_excess_amt(excess_amt - bal_amt);
//						returndobj.setBalance_amt(0);
//						returndobj.setAdjustment_type("C");
//					} else {
//						returndobj.setBalance_amt(bal_amt - excess_amt);
//						returndobj.setExcess_amt(0);
//						returndobj.setAdjustment_type("D");
//					}
//
//				} else {
//					returndobj.setBalance_amt(bal_amt);
//					returndobj.setExcess_amt(excess_amt);
//					returndobj.setPrevious_excess_amt(excess_amt);
//					returndobj.setAdjustment_type("D");
//
//				}
//
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(e);
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e);
//				}
//			}
//		}
//
//		return returndobj;
//	}
//
//	public static boolean checkTaxadjustmentforExcess(String state_cd) throws VahanException {
//
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "ServerUtil.checkTaxadjustmentforExcess";
//		String sqlPendingPermitAppl = "select * from tm_configuration_tax where state_cd=? ";
//		TransactionManagerInterface tmgr = null;
//		try {
//
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				tmgr = new TransactionManagerReadOnly(whereiam);
//
//			} else {
//				tmgr = new TransactionManager(whereiam);
//			}
//			pstm = tmgr.prepareStatement(sqlPendingPermitAppl);
//
//			if (POSValidator.validate(state_cd, "A")) {
//				pstm.setString(1, state_cd);
//			}
//
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				return rs.getBoolean("allow_prev_tax_excess_balance");
//			}
//		} catch (SQLException ex) {
//			LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception ex) {
//				LOGGER.error(ex.toString() + " " + ex.getStackTrace()[0]);
//			}
//		}
//		return false;
//	}
//
//	public static AdjustmentTaxDobj getAdjustTaxDeatils(String regn_no) {
//
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		AdjustmentTaxDobj dobj = null;
//		AdjustmentTaxDobj returndobj = new AdjustmentTaxDobj();
//		String adjustment_type = "";
//		String appl_no = "";
//		double bal_amt = 0.0;
//		;
//		String sql = "";
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				tmgr = new TransactionManagerReadOnly("ServerUtil.getAdjustTaxDeatils()");
//
//			} else {
//				tmgr = new TransactionManager("ServerUtil.getAdjustTaxDeatils()");
//			}
//			sql = "SELECT * from onlineschema.vt_tax_adjustment  where regn_no=?";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, regn_no);
//			rs = tmgr.fetchDetachedRowSet();
//			while (rs.next()) {
//				dobj = new AdjustmentTaxDobj();
//				dobj.setRegn_no(regn_no);
//				dobj.setPayemt_id(rs.getString("payment_id"));
//				appl_no = dobj.getPayemt_id();
//				dobj.setBalance_amt(rs.getDouble("balance_amt"));
//				bal_amt += dobj.getBalance_amt();
//				dobj.setFrom_dt(rs.getString("from_dt"));
//				dobj.setPur_cd(ApplicationConfig.PUR_RD_TAX);
//				dobj.setUpto_dt(rs.getString("upto_dt"));
//				dobj.setTransaction_id(rs.getString("transaction_id"));
//				dobj.setAdjustment_type(rs.getString("adjustment_type"));
//				adjustment_type = dobj.getAdjustment_type();
//				returndobj.getAdjustmentlist().add(dobj);
//
//			}
//
//			if (returndobj.getAdjustmentlist().size() > 0) {
//				returndobj.setAdjustment_type(adjustment_type);
//				returndobj.setBalance_amt(bal_amt);
//				returndobj.setPayemt_id(appl_no);
//
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(e);
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e);
//				}
//			}
//		}
//
//		return returndobj;
//	}
//
//	public static VmonlineservicechecksDobj fillVmonlineservicechecksDobj(String state_cd) {
//		String sql = null;
//		PreparedStatement ps = null;
//		VmonlineservicechecksDobj dobj = null;
//		TransactionManagerInterface tmgr = null;
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				tmgr = new TransactionManagerReadOnly("ConfigurationImpl.fillVmonlineservicechecksDobj");
//
//			} else {
//				tmgr = new TransactionManager("ConfigurationImpl.fillVmonlineservicechecksDobj");
//			}
//			sql = "select * from " + TableList.VM_ONLINE_SERVICECHECKS + "  where state_cd=?";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, state_cd);
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				dobj = new VmonlineservicechecksDobj();
//				dobj.setAllow_rc_services(rs.getString("allow_rc_services"));
//				dobj.setAlloweservice_insuranceinvalid(rs.getString("allowservice_insuranceinvalid"));
//				dobj.setAllowservice_fitnessinvalid(rs.getString("allowservice_fitnessinvalid"));
//				dobj.setAllowservice_rcvalidinvalid(rs.getString("allowservice_rcvalidinvalid"));
//				dobj.setAllowservice_taxinvalid(rs.getString("allowservice_taxinvalid"));
//				dobj.setAllowservice_whenblacklist(rs.getString("allowservice_whenblacklist"));
//				dobj.setChallanpndingrestrictpur_cd(rs.getString("challanpndingrestrictpur_cd"));
//				dobj.setIsncrbblacklist(rs.getString("isncrbblacklist"));
//				dobj.setOwnercdinsuranceexempt(rs.getString("ownercdinsuranceexempt"));
//				dobj.setTaxrestrictedsaleamt(rs.getBoolean("taxrestrictedsaleamt"));
//				dobj.setMobile_no_checkduration(rs.getInt("mobile_no_checkduration"));
//				dobj.setMobile_no_checklimit(rs.getInt("mobile_no_checklimit"));
//				dobj.setRrWithTax(rs.getBoolean("rrwithtax"));
//				dobj.setService_auth_mode(rs.getString("service_auth_mode"));
//				dobj.setFee_exempt_owner_cd(rs.getString("fee_exempt_owner_cd"));
//				dobj.setHptcheck_skip_pur_cd("," + rs.getString("hptcheck_skip_pur_cd") + ",");
//				dobj.setMvtax_notpermittedpurcd(rs.getString("mvtax_notpermittedpurcd"));
//				dobj.setZero_vtfee_pur_cd(rs.getString("zero_vtfee_pur_cd"));
//				dobj.setAny_where_rto_district_mapping(rs.getString("any_where_rto_district_mapping"));
//				dobj.setHsrp_mandatory(rs.getBoolean("hsrp_mandatory"));
//				dobj.setTax_nid_days(rs.getInt("tax_nid_days"));
//				dobj.setRestrictTOwithoutTax(rs.getBoolean("restrictTOwithoutTax"));
//				dobj.setLttTaxBacklogEntry(rs.getBoolean("LttTaxBacklogEntry"));
//				dobj.setOtherTaxservicesAllow(rs.getString("otherTaxservicesAllow"));
//				dobj.setTaxMsgSkipModes("," + rs.getString("taxMsgSkipModes") + ",");
//				dobj.setRandomOfficeAllotmentPurcd("," + rs.getString("randomOfficeAllotmentPurcd") + ",");
//				dobj.setTaxrecovery_servicerestrictformula(rs.getString("taxrecovery_servicerestrictformula"));
//
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e);
//		} catch (Exception e) {
//			LOGGER.error(e);
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e);
//			}
//		}
//		return dobj;
//	}
//
//	// Added By Kamal Nayan Sahu
//	public static String getPermitTypeDescr(int pmt_type) throws VahanException {
//		String pmtType = "";
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String sql = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getstate_name");
//			sql = "Select descr  from " + TableList.VM_PERMIT_TYPE + "   where code=? ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setInt(1, pmt_type);
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				pmtType = rs.getString("descr");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return pmtType;
//	}
//
//	// Added By Kamal Nayan Sahu
//	public static String getApplStatusFromVT_TEMP_APPL_TRANSACTION(String transNo, String state_cd, int off_cd,
//			String user_name) throws VahanException {
//		PreparedStatement ps = null;
//		TransactionManagerInterface tmgr = null;
//		String application_status = "";
//		try {
//			String query = "SELECT  application_status  FROM " + TableList.VT_TEMP_APPL_TRANSACTION
//					+ " where state_cd=? and off_cd=? and transaction_no=? and user_name=?";
//			tmgr = new TransactionManagerReadOnly("getAuthTypeFromVT_TEMP_APPL_TRANSACTION");
//			ps = tmgr.prepareStatement(query);
//			int i = 1;
//			ps.setString(i++, state_cd);
//			ps.setInt(i++, off_cd);
//			ps.setString(i++, transNo);
//			ps.setString(i++, user_name);
//			RowSet rs = tmgr.fetchDetachedRowSet_No_release();
//			if (rs.next()) {
//				application_status = rs.getString("application_status");
//			}
//		} catch (Exception ex) {
//			LOGGER.error("Error in fetching getApplStatusFromVT_TEMP_APPL_TRANSACTION for transNo [ " + transNo + "].."
//					+ ex);
//			throw new VahanException(
//					"Error in fetching getApplStatusFromVT_TEMP_APPL_TRANSACTION for transNo [ " + transNo + "]");
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e);
//			}
//		}
//		return application_status;
//	}
//
//	// Get purpose code value
//	public static int getPurCd(String descr) {
//		String whereiam = "Get Purpose code";
//		int pur_cd = 0;
//		PreparedStatement psmt = null;
//		TransactionManagerInterface tmgr = null;
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			String strSQL = "SELECT pur_cd FROM " + TableList.TM_PURPOSE_MAST + " where descr = ?";
//			psmt = tmgr.prepareStatement(strSQL);
//			psmt.setString(1, descr);
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				pur_cd = rs.getInt("pur_cd");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return pur_cd;
//	}
//
//	public static int getPermitTypeDescr(String pmt_type) throws VahanException {
//		int pmtType = 0;
//		PreparedStatement ps = null;
//		TransactionManagerReadOnly tmgr = null;
//		String sql = null;
//		try {
//			tmgr = new TransactionManagerReadOnly("getstate_name");
//			sql = "Select code  from " + TableList.VM_PERMIT_TYPE + "   where descr=? ";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, pmt_type);
//			RowSet rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				pmtType = rs.getInt("code");
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return pmtType;
//	}
//
//	// Added By Kamal Nayan Sahu
//	// To get other necessary details of vehicle owner
//	public static OwnerIdentificationDobj getOwnerIdentificationDetails(String regnNo) {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		try {
//			String sqlOwnerId = "SELECT * FROM " + TableList.VT_OWNER_IDENTIFICATION + " WHERE regn_no =?";
//			tmgr = new TransactionManagerReadOnly("getOwnerIdentificationDetails");
//			ps = tmgr.prepareStatement(sqlOwnerId);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				ps.setString(1, regnNo);
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			return fillOwnerIdentityDobj(rs);
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return null;
//	}
//
//	// Added By Kamal Nayan Sahu
//	// To get Challan Info
//	public static ChallanApidobj challanapiInfo(String regn_no) throws Exception {
//		String response = "";
//		String URL = "";
//		String token = "Bearer NDI5MGQ4ZDVhOTM3MzhiYWNhODlkOWViNGIxMzQ4ZWYyOTJkNGIzZGViNGQzNjZjNTcxYjkxODNhYWY5MThiYQ==";
//		ChallanApidobj dobj = null;
//		ObjectMapper mapper = new ObjectMapper();
//
//		if (!CommonUtils.isNullOrBlank(regn_no)) {
//			dobj = new ChallanApidobj();
//			DefaultHttpClient httpClient = new DefaultHttpClient();
//			boolean isLocalEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (isLocalEnviroment) {
//				URL = ApplicationConfig.CHALLANAPI_STAGING_URL;
//
//			} else {
//				URL = ApplicationConfig.CHALLANAPI_PROD_URL;
//			}
//
//			URL = URL + "?rc=" + regn_no;
//			try {
//				HttpGet getreq = new HttpGet(URL);
//				getreq.addHeader("content-type", "application/json");
//				getreq.addHeader("Authorization", token);
//				HttpResponse httpresponse = httpClient.execute(getreq);
//				HttpEntity httpEntity = httpresponse.getEntity();
//				response = EntityUtils.toString(httpEntity);
//				mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//				mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//				if (!CommonUtils.isNullOrBlank(response)) {
//					dobj = mapper.readValue(response, ChallanApidobj.class);
//				}
//			} catch (Exception jb) {
//				LOGGER.error("challanapiInfo exception: " + jb);
//				throw new Exception(jb);
//			} finally {
//				if (null != httpClient) {
//					httpClient.getConnectionManager().shutdown();
//				}
//			}
//
//		}
//		return dobj;
//	}
//
//	// Added By Kamal Nayan Sahu
//	// To get Latest application number
//	public static String getApplNo(String regnNo, String state_cd) {
//
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps;
//		RowSet rs;
//		String sql = "", appl_no = "";
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				tmgr = new TransactionManagerReadOnly("ServerUtil.getApplNo");
//
//			} else {
//				tmgr = new TransactionManager("ServerUtil.getApplNo");
//			}
//			sql = "SELECT appl_no from " + TableList.VA_DETAILS_APPL
//					+ " where regn_no=? and state_cd=? order by appl_dt desc limit 1";
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, regnNo);
//			ps.setString(2, state_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				appl_no = rs.getString("appl_no");
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(e);
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e);
//				}
//			}
//		}
//
//		return appl_no;
//	}
//
//	// To get office code for entered application
//	// Added by Kamal Nayan Sahu
//	public static int getOfficeCode(String appl_no) {
//		PreparedStatement ps;
//		RowSet rs;
//		int off_cd = 0;
//		TransactionManagerInterface iTmgr = null;
//		String insQuery = null;
//
//		insQuery = "SELECT off_cd from " + TableList.VA_DETAILS_APPL + " where appl_no=? order by appl_dt desc limit 1";
//		try {
//			boolean isTestingEnviroment = Boolean.valueOf(new ApplicationConfig(ApplicationConfig.PROP_PAYMENT_MODE)
//					.getProperties(ApplicationConfig.PROP_KEY_MODE_ISTESTING));
//			if (!isTestingEnviroment) {
//				iTmgr = new TransactionManagerReadOnly("ServerUtil.getOfficeCode()");
//
//			} else {
//				iTmgr = new TransactionManager("ServerUtil.getOfficeCode()");
//			}
//			ps = iTmgr.prepareStatement(insQuery);
//			ps.setString(1, appl_no);
//
//			rs = iTmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				off_cd = rs.getInt("off_cd");
//			}
//		} catch (SQLException | VahanException e) {
//			LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (iTmgr != null) {
//					iTmgr.release();
//				}
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage());
//			}
//		}
//		return off_cd;
//	}
//}
