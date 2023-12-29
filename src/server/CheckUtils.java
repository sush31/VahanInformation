///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package server;
//
//import java.io.Serializable;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//import javax.sql.RowSet;
//
//import CommonUtils.POSValidator;
//import databaseconnection.TableConstants;
//import databaseconnection.TableList;
//import databaseconnection.TransactionManagerInterface;
//import databaseconnection.TransactionManagerReadOnly;
////import nic.rto.vahan.common.VahanException;
//import nic.vahan.form.dobj.OwnerDetailsDobj;
//import resources.ApplicationConfig;
//
///**
// * !
// *
// * @author vinay chaudhary
// */
//public class CheckUtils implements Serializable {
//
//	//private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CheckUtils.class);
//
////	public static String isValidPayment(String regnNO, OwnerDetailsDobj dobj, int purCD)
////			throws ParseException, VahanException {
////		boolean flag = false;
////		String message = null;
////		Date current_dt = new Date();
////		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
////		// OwnerDetailsDobj dobj = ServerUtil.getRegnBasedOwnerDetails(regnNO);
////		String chassiNo = dobj.getChasi_no_original();
////		// common check
////		// boolean blacklistedFlag =
////		// ServerUtil.checkForBlacklistedVehicle(regnNO, chassiNo);
////		// LOGGER.info("blacklistedFlag : " + blacklistedFlag);
////		//
////		// if (blacklistedFlag) {
////		// message = "There is some problem,Please contact to the RTO office.";
////		// }
////		PermitDetailDobj permitDetailDobj = CommonServerImpl.getPermitdetailsFromRegnNo(regnNO);
////		dobj.setVehAgeExpire(CommonServerImpl.isVehAgeExpired(dobj, permitDetailDobj));
////		
////		
//////		 if (dobj.getRegn_uptoAsDate().before(current_dt) && ApplicationConfig.PUR_RD_TAX != purCD
////		if (dobj.isVehAgeExpire() && ApplicationConfig.PUR_RD_TAX != purCD
////				&& !(dobj.getState_cd().equalsIgnoreCase("ML") || dobj.getState_cd().equalsIgnoreCase("OR")
////						|| dobj.getState_cd().equalsIgnoreCase("MH") || dobj.getState_cd().equalsIgnoreCase("UP"))
////				&& (purCD == ApplicationConfig.PUR_FITNESS || purCD == ApplicationConfig.PUR_REN_REGISTRATION)) {
////			message = "Vehicle Registration has been expired! Vehicle Registration valid upto " + dobj.getRegn_upto();
////			return message;
////		}
////
////		BlackListedVehicleDobj dobjBlackListedVehicleDobj = ServerUtil.getBlacklistedVehicleDetails(regnNO, chassiNo);
////		if (dobjBlackListedVehicleDobj != null && ApplicationConfig.PUR_MULTIPLE_FEES != purCD) {
////			message = "Vehicle is Blacklisted due to Reason ["
////					+ dobjBlackListedVehicleDobj.getComplainDesc().toUpperCase() + "] in State [ "
////					+ dobjBlackListedVehicleDobj.getStateName() + " ] at Office [ "
////					+ dobjBlackListedVehicleDobj.getOfficeName() + " ],Please Contact to the RTO.";
////		}
////
////		boolean isTaxPending = ServerUtil.isTaxPending(dobj, ApplicationConfig.PUR_RD_TAX);
////		//LOGGER.info("isTaxPending : " + isTaxPending);
////		if (isTaxPending && ApplicationConfig.PUR_RD_TAX != purCD
////				&& isValidCheckForRenRegTaxPending(dobj.getState_cd(), purCD)) {
////			if (!(dobj.getVehTypeAsInt() == TableConstants.VM_VEHTYPE_NON_TRANSPORT
////					&& dobj.getState_cd().equalsIgnoreCase("DL"))) {
////				message = "Vehicle tax is not upto date,You can't proceed Online.";
////			}
////		}
////
////		// Date fitDT = ServerUtil.parseDate(dobj.getFit_upto());
////		Date fitDT = DateUtil.parseDateFromYYYYMMDD(dobj.getFit_upto());
////		if (fitDT.before(current_dt) && !(ApplicationConfig.PUR_REN_REGISTRATION == purCD 
////				|| ApplicationConfig.PUR_FITNESS == purCD || ApplicationConfig.PUR_RD_TAX == purCD)) {
//////		if (DateUtil.isBefore(DateUtil.parseDate(fitDT), DateUtil.parseDate(current_dt))
//////				&& !(ApplicationConfig.PUR_REN_REGISTRATION == purCD || ApplicationConfig.PUR_FITNESS == purCD
//////						|| ApplicationConfig.PUR_RD_TAX == purCD)) {
//////			if (fitDT != null && (DateUtil.isBefore(DateUtil.parseDate(fitDT), DateUtil.parseDate(new Date())))) {
////			 if (fitDT != null && (fitDT.before(new Date()))) {
////				message = "Vehicle Fitness is not upto date,You can't proceed Online.Fitness Upto Date is "
////						+ dobj.getFit_upto();
////			}
////		}
////		if (fitDT != null) {
////			if (ApplicationConfig.PUR_FITNESS == purCD) {
////				Date nid = dateRange(fitDT, 0, 0,
////						ServerUtil.getTmConfigurationParameters(dobj.getState_cd()).getNid_days());
////				if (nid != null && DateUtil.compareDates(nid, new Date()) == 2) {
////					message = "Can not Request for Fitness Certificate before NID (Next Inspection Date) is "
////							+ parseDateToString(nid);
////				}
////			}
////		}
////		// Date fitDT = ServerUtil.parsdobj.getFit_upto());
////		try {
////			// perticular check
////			switch (purCD) {
////			case ApplicationConfig.PUR_HPT:
////				boolean isHypth = ServerUtil.checkHypthOrNot(regnNO);
////				//LOGGER.info("isHypth : " + isHypth);
////				if (!isHypth) {
////					message = "Vehicle is not HYPOTHECATED, You can't Request for HPT";
////				}
////				break;
////			case ApplicationConfig.PUR_FITNESS:
////				boolean isValidTime = ServerUtil.isValidPaymentTime();
////				//LOGGER.info("isValidTime for Fitness : " + isValidTime);
////				// if (!isValidTime) {
////				// message = "You can't pay Fitness Fee. Please pay Fitness fees
////				// between 8:30 AM to 6:00 PM.";
////				// }
////				break;
////			case ApplicationConfig.PUR_HPC:
////				boolean isHypthForHPC = ServerUtil.checkHypthOrNot(regnNO);
////				//LOGGER.info("isHypth : " + isHypthForHPC);
////				if (!isHypthForHPC) {
////					message = "Vehicle is not HYPOTHECATED, You can't Request for HPC";
////				}
////				break;
////			case ApplicationConfig.PUR_NOC:
////				// if (!dobj.getState_cd().equalsIgnoreCase("UP")) {
////
//////				boolean isHPT = ServerUtil.checkHypthOrNot(regnNO);
//////				LOGGER.info("isHypth : " + isHPT);
//////				if (isHPT) {
//////					message = "Vehicle is  HYPOTHECATED, First apply HPT or HP Continuation";
//////				}
//////				// }
////				break;
////			// case ApplicationConfig.PUR_PERMIT_FEE:
////			//
////			// PermitCheckDetailsDobj validDobj =
////			// ServerUtil.getPrmtInsChalanDtls(regnNO);
////			// if (!(validDobj.isInsValid())) {
////			// message = "Your Vehicle Insurance is Expired.";
////			// } else if (!validDobj.isChalPending()) {
////			// message = "Please pay Challan first.";
////			// }
////			// // LOGGER.info("Validation msg : " + message);
////			// break;
////
////			}
////		} catch (Exception ex) {
////			LOGGER.error(ex);
////		}
////		return message;
////	}
//
//	private static boolean isValidCheckForRenRegTaxPending(String stateCD, int purCD) {
//		boolean flag = true;
//		if (ApplicationConfig.PUR_REN_REGISTRATION == purCD && stateCD.equalsIgnoreCase("PB")) {
//			flag = false;
//		}
//		return flag;
//	}
//
//	public static String getMsgForAllowedUserBasedOnStateAndRto(String regn_no, int pur_cd, String state_cd)
//			throws VahanException {
//		OwnerDetailsDobj dobj = ServerUtil.getRegnBasedOwnerDetails(regn_no, state_cd);
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rowSet = null;
//		String msg = null;
//		if (dobj != null) {
//			String sql = " SELECT * FROM " + TableList.VP_ALLOWED_TRANSACTIONS + " WHERE state_cd = ? and off_cd=?";
//			try {
//				tmgr = new TransactionManagerReadOnly("CHeckUtils.getMsgForAllowedUserBasedOnStateAndRto()");
//				ps = tmgr.prepareStatement(sql);
//				if (POSValidator.validate(dobj.getState_cd(), "A")) {
//					ps.setString(1, dobj.getState_cd());
//				}
//				if (POSValidator.validate(String.valueOf(dobj.getOff_cd()), "N")) {
//					ps.setInt(2, dobj.getOff_cd());
//				}
//				rowSet = tmgr.fetchDetachedRowSet();
//				if (rowSet.next()) {
//					if (ApplicationConfig.PUR_RD_TAX == pur_cd && !rowSet.getBoolean("istax")) {
//						msg = "This service has not been activated for the concern State/RTO.";
//					} else if (ApplicationConfig.PUR_NOC == pur_cd && !rowSet.getBoolean("isnoc")) {
//						msg = "This service has not been activated for the concern State/RTO.";
//					} else if (ApplicationConfig.PUR_FITNESS == pur_cd && !rowSet.getBoolean("isfit")) {
//						msg = "This service has not been activated for the concern State/RTO.";
//					} else if (ApplicationConfig.PUR_PERMIT_FEE == pur_cd && !rowSet.getBoolean("isprmt")) {
//						msg = "This service has not been activated for the concern State/RTO.";
//					} else if (ApplicationConfig.PUR_MULTIPLE_FEES == pur_cd
//							&& !(rowSet.getBoolean("isto") || rowSet.getBoolean("isca") || rowSet.getBoolean("ishpa")
//									|| rowSet.getBoolean("ishpt") || rowSet.getBoolean("isduprc"))) {
//						msg = "This service has not been activated for the concern State/RTO.";
//					} else if (ApplicationConfig.PUR_ALTERATION_OF_VEH == pur_cd && !rowSet.getBoolean("isvehalt")) {
//						msg = "This service has not been activated for the concern State/RTO.";
//					} else if (ApplicationConfig.PUR_REASSIGN_VEH == pur_cd && !rowSet.getBoolean("isvehreasgn")) {
//						msg = "This service has not been activated for the concern State/RTO.";
//					} else if (ApplicationConfig.PUR_REN_REGISTRATION == pur_cd && !rowSet.getBoolean("isrenreg")) {
//						msg = "This service has not been activated for the concern State/RTO.";
//					} else if (ApplicationConfig.PUR_DUPLICATE_FC == pur_cd && !rowSet.getBoolean("isdupfit")) {
//						msg = "This service has not been activated for the concern State/RTO.";
//					}
//
//				}
//			} catch (SQLException se) {
//				LOGGER.error(se);
//			} finally {
//				try {
//					if (tmgr != null) {
//						tmgr.release();
//					}
//				} catch (Exception e) {
//					LOGGER.error(e);
//				}
//			}
//		} else {
//			msg = "This service has not been activated for the concern State/RTO.";
//		}
//		return msg;
//	}
//
//	public static String getMsgForAllowedUserBasedOnLiveStateAndRto(String regnNo, String state_cd)
//			throws VahanException {
//		OwnerDetailsDobj dobj = ServerUtil.getRegnBasedOwnerDetails(regnNo, state_cd);
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rs = null;
//		String sql = "";
//		String msg = null;
//		sql = " SELECT vow4 FROM  " + TableList.TM_OFFICE + " WHERE state_cd = ? and off_cd=? ";
//		try {
//			tmgr = new TransactionManagerReadOnly("CHeckUtils.getMsgForAllowedUserBasedOnLiveStateAndRto()");
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(dobj.getState_cd(), "A")) {
//				ps.setString(1, dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getOff_cd()), "N")) {
//				ps.setInt(2, dobj.getOff_cd());
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				String vowDat = rs.getString("vow4");
//				if (vowDat.equals("")) {
//					msg = "This service has not been activated for the concern State/RTO.";
//				} else {
//					return msg;
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e);
//
//		} finally {
//			if (tmgr != null) {
//				try {
//					tmgr.release();
//				} catch (Exception e) {
//					LOGGER.error(e);
//
//				}
//			}
//		}
//		return msg;
//	}
//
//	public static String getMsgForAllowedUserBasedOnStateCdandOfficeName(String state_cd, int off_cd, int pur_cd)
//			throws VahanException {
//
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement ps = null;
//		RowSet rowSet = null;
//		String msg = null;
//
//		String sql = " SELECT * FROM " + TableList.VP_ALLOWED_TRANSACTIONS + " WHERE state_cd = ? and off_cd=?";
//		try {
//			tmgr = new TransactionManagerReadOnly("CHeckUtils.getMsgForAllowedUserBasedOnStateAndRto()");
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(state_cd, "A")) {
//				ps.setString(1, state_cd);
//			}
//			if (POSValidator.validate(String.valueOf(off_cd), "N")) {
//				ps.setInt(2, off_cd);
//			}
//			rowSet = tmgr.fetchDetachedRowSet();
//			if (rowSet.next()) {
//				if (ApplicationConfig.PUR_RD_TAX == pur_cd && !rowSet.getBoolean("istax")) {
//					msg = "You are not allowed to pay online tax here. Please visit ";
//				} else if (ApplicationConfig.PUR_NOC == pur_cd && !rowSet.getBoolean("isnoc")) {
//					msg = "You are not allowed for online application here. Please visit ";
//				} else if (ApplicationConfig.PUR_FITNESS == pur_cd && !rowSet.getBoolean("isfit")) {
//					msg = "You are not allowed for fitness application here. Please visit ";
//				} else if (ApplicationConfig.PUR_PERMIT_FEE == pur_cd && !rowSet.getBoolean("isprmt")) {
//					msg = "You are not allowed for permit application here. Please visit ";
//				} else if (ApplicationConfig.PUR_MULTIPLE_FEES == pur_cd
//						&& !(rowSet.getBoolean("isto") || rowSet.getBoolean("isca") || rowSet.getBoolean("ishpa")
//								|| rowSet.getBoolean("ishpt") || rowSet.getBoolean("isduprc"))) {
//					msg = "You are not allowed for online application here. Please visit ";
//				} else if (ApplicationConfig.PUR_ALTERATION_OF_VEH == pur_cd && !rowSet.getBoolean("isvehalt")) {
//					msg = "You are not allowed for Alteration of Vehicle application here.Contact to RTO OR Please visit ";
//				} else if (ApplicationConfig.PUR_REASSIGN_VEH == pur_cd && !rowSet.getBoolean("isvehreasgn")) {
//					msg = "You are not allowed for Reassign of Vehilce application here.Contact to RTO OR Please visit ";
//				} else if (ApplicationConfig.PUR_REN_REGISTRATION == pur_cd && !rowSet.getBoolean("isrenreg")) {
//					msg = "You are not allowed for Renewal of Registration application here.Contact to RTO OR Please visit ";
//				} else if (ApplicationConfig.PUR_DUPLICATE_FC == pur_cd && !rowSet.getBoolean("isdupfit")) {
//					msg = "You are not allowed for Duplicate Fitness application here.Contact to RTO OR Please visit ";
//				}
//
//			}
//		} catch (SQLException se) {
//			LOGGER.error(se);
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
//		return msg;
//	}
//
//	public static boolean checkPandingTrans(String regnNo) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "checkPandingTrans";
//		// String sqlPgiVPDtls = "SELECT * FROM " + TableList.VT_TEMP_APPROVE +
//		// " WHERE DEAL_CD=? and PUR_CD=?";
//		String sqlPgiVPDtls = "SELECT * FROM " + TableList.VT_TEMP_APPROVE + " APPRV INNER JOIN "
//				+ TableList.VT_TEMP_APPL_TRANSACTION + " APPL "
//				+ " ON APPRV.transaction_no=APPL.transaction_no and APPRV.state_cd=APPL.state_cd and APPRV.OFF_cd=APPL.OFF_cd "
//				+ " WHERE APPRV.DEAL_CD=? AND APPL.APPLICATION_STATUS='R' AND APPRV.DEAL_CD<>'NEW' and APPRV.PUR_CD <> ?";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlPgiVPDtls);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				pstm.setString(1, regnNo);
//			}
//
//			pstm.setInt(2, TableConstants.VM_TRANSACTION_MAST_FIT_CERT);
//
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (SQLException se) {
//			LOGGER.error(se);
//			throw new VahanException("Unable to get validation details for any previous transactions");
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
//		return false;
//	}
//
//	public static boolean isApplicationATPaymentPeding(String regnNo, String trans_no) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		RowSet rs = null;
//		String whereiam = "checkPandingTrans";
//		// String sqlPgiVPDtls = "SELECT * FROM " + TableList.VT_TEMP_APPROVE +
//		// " WHERE DEAL_CD=? and PUR_CD=?";
//		String sqlPgiVPDtls = "SELECT * FROM " + TableList.VT_TEMP_APPROVE + " APPRV INNER JOIN "
//				+ TableList.VT_TEMP_APPL_TRANSACTION + " APPL "
//				+ " ON APPRV.transaction_no=APPL.transaction_no and APPRV.state_cd=APPL.state_cd and APPRV.OFF_cd=APPL.OFF_cd "
//				+ " WHERE APPRV.DEAL_CD=? AND APPL.APPLICATION_STATUS='R' AND APPRV.transaction_no= ?";
//		try {
//
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlPgiVPDtls);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				pstm.setString(1, regnNo);
//			}
//			if (POSValidator.validate(trans_no, "AN")) {
//				pstm.setString(2, trans_no);
//			}
//
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (SQLException se) {
//			LOGGER.error(se);
//			throw new VahanException("Unable to get validation details for any previous transactions");
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
//		return false;
//	}
//
//	public static String checkPandingTransTransactioNo(String regnNo, int pur_cd) throws VahanException {
//		TransactionManagerInterface tmgr = null;
//		PreparedStatement pstm = null;
//		String appl_no = "";
//		RowSet rs = null;
//		String whereiam = "checkPandingTrans";
//		// String sqlPgiVPDtls = "SELECT * FROM " + TableList.VT_TEMP_APPROVE +
//		// " WHERE DEAL_CD=? and PUR_CD=?";
//		String sqlPgiVPDtls = "SELECT * FROM " + TableList.VT_TEMP_APPROVE + " APPRV INNER JOIN "
//				+ TableList.VT_TEMP_APPL_TRANSACTION + " APPL "
//				+ " ON APPRV.transaction_no=APPL.transaction_no and APPRV.state_cd=APPL.state_cd and APPRV.OFF_cd=APPL.OFF_cd "
//				+ " WHERE APPRV.DEAL_CD=? AND APPL.APPLICATION_STATUS='R' AND APPRV.DEAL_CD<>'NEW' and CASE WHEN ? in (?,?) THEN APPRV.PUR_CD <> ? else true end order by APPRV.op_dt ";
//		try {
//			tmgr = new TransactionManagerReadOnly(whereiam);
//			pstm = tmgr.prepareStatement(sqlPgiVPDtls);
//			if (POSValidator.validate(regnNo, "ANS")) {
//				pstm.setString(1, regnNo);
//			}
//			pstm.setInt(2, pur_cd);
//			pstm.setInt(3, TableConstants.VM_PMT_SPECIAL_PUR_CD);
//			pstm.setInt(4, TableConstants.VM_PMT_TEMP_PUR_CD);
//			pstm.setInt(5, TableConstants.VM_TRANSACTION_MAST_FIT_CERT);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				return rs.getString("transaction_no");
//
//			}
//		} catch (SQLException se) {
//			LOGGER.error(se);
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
//		return appl_no;
//	}
//
//	public static Date dateRange(Date date, int year, int month, int day_of_month) {
//		java.util.Calendar cal = new GregorianCalendar();
//		cal.setTime(date);
//		cal.add(java.util.Calendar.YEAR, year);
//		cal.add(java.util.Calendar.MONTH, month);
//		cal.add(java.util.Calendar.DAY_OF_MONTH, day_of_month);
//		return cal.getTime();
//	}
//
//	public static String parseDateToString(Date dt) {
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
//		sdf.applyPattern("dd-MMM-yyyy");
//		String nDate = sdf.format(dt);
//		return nDate;
//	}
//}
