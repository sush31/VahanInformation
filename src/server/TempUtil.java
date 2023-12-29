///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package nic.vahan.server;
//
//import java.io.Serializable;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import javax.faces.application.FacesMessage;
//import javax.sql.RowSet;
//import nic.java.util.DateUtilsException;
//import nic.rto.vahan.common.VahanException;
//import nic.vahan.common.jsf.utils.validators.POSValidator;
//import nic.vahan.db.connection.TransactionManager;
//import nic.vahan.form.dobj.DOTaxDetail;
//import nic.vahan.form.dobj.OwnerDetailsDobj;
//import nic.vahan.utils.DateUtil;
//import static nic.vahan.utils.DateUtil.parseDate;
//import org.apache.log4j.Logger;
//import org.primefaces.context.RequestContext;
//
///**
// *
// * @author acer
// */
//public class TempUtil implements Serializable{
//
//	private static final Logger LOGGER = Logger.getLogger(TempUtil.class);
//
//	public static Boolean adtlPendingTax(List<DOTaxDetail> listTaxBreakUp, OwnerDetailsDobj dobj)
//			throws SQLException, DateUtilsException, VahanException {
//		RowSet rs = null;
//		PreparedStatement ps = null;
//		TransactionManager tmgr = null;
//		String tax_Pending_opdt = null;
//		String dateTaxUptoPending = null;
//		String dateTaxFromPending = null;
//		String dateTaxFromPending1 = null;
//		String taxMode = null;
//		Boolean flag = true;
//		try {
//			String sql = "select * from onlineschema.addtional_tax_pending_jh"
//					+ " where r_reg_no=?  and state_cd=? and off_cd=? and flag_status=false  order by recp_dt";
//			tmgr = new TransactionManager("adtlPendingTax");
//			ps = tmgr.prepareStatement(sql);
//			if (POSValidator.validate(dobj.getRegn_no(), "ANS")) {
//				ps.setString(1, dobj.getRegn_no());
//			}
//			if (POSValidator.validate(dobj.getState_cd(), "A")) {
//				ps.setString(2, dobj.getState_cd());
//			}
//			if (POSValidator.validate(String.valueOf(dobj.getOff_cd()), "N")) {
//				ps.setInt(3, dobj.getOff_cd());
//			}
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				flag = false;
//				tax_Pending_opdt = DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("recp_dt"));
//				// tax_Pending_opdt=DateUtil.getDateInDDMMYYYY(DateUtil.addToDate(tax_Pending_opdt,
//				// 1, 15));
//				dateTaxFromPending = DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("tax_from"));
//				dateTaxFromPending1 = DateUtil.getDateInDDMMYYYY(DateUtil.addToDate(dateTaxFromPending, 1, 15));
//				dateTaxUptoPending = DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("tax_upto"));
//				taxMode = rs.getString("tax_mode");
//				if (taxMode.equalsIgnoreCase(listTaxBreakUp.get(0).getTAX_MODE())) {
//					if (DateUtil.isAfter(tax_Pending_opdt, dateTaxFromPending1)) {
//						for (int i = 0; i < listTaxBreakUp.size(); i++) {
//							// listTaxBreakUp.get(i).setPENALTY(panalty);//by
//							// jyoti chuadhary
//
//							if (!listTaxBreakUp.get(i).getTAX_FROM().equalsIgnoreCase(dateTaxFromPending)
//									&& !listTaxBreakUp.get(i).getTAX_UPTO().equalsIgnoreCase(dateTaxUptoPending)
//									&& (DateUtil.isAfter(listTaxBreakUp.get(i).getTAX_UPTO(), dateTaxUptoPending))) {
//								listTaxBreakUp.remove(i);// byKartik 02June17
//							}
//						}
//					} else {
//						for (int i = 0; i < listTaxBreakUp.size(); i++) {
//							listTaxBreakUp.get(i).setPENALTY(0);
//							if (!listTaxBreakUp.get(i).getTAX_FROM().equalsIgnoreCase(dateTaxFromPending)
//									&& !listTaxBreakUp.get(i).getTAX_UPTO().equalsIgnoreCase(dateTaxUptoPending)) {
//								listTaxBreakUp.remove(i);// byKartik 02June17
//							}
//						}
//					}
//				} else {
//					// FacesMessage message = new
//					// FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Please
//					// Select the Right Tax Mode");
//					// RequestContext.getCurrentInstance().showMessageInDialog(message);
//					RequestContext.getCurrentInstance().execute("PF('notrightmode').show();");
//				}
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
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
//	public static long penalitytax(String regnNo, String stateCd, int off_cd, int taxAmt, String tableName)
//			throws DateUtilsException {
//		// Date diff by jyoti Chaudhary
//		RowSet rs = null;
//		PreparedStatement ps = null;
//		TransactionManager tmgr = null;
//		String tax_Pending_opdt = null;
//		String dateTaxUptoPending = null;
//		String dateTaxFromPending = null;
//		String dateTaxFromPending1 = null;
//		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");
//		Date d1 = null;
//		Date d2 = null;
//		String regn_no = null;
//		long panalty = 0;
//		int penalty_percent = 0;// Jyoti Chaudhary
//		if (tableName.equalsIgnoreCase("onlineschema.addtional_tax_pending_jh")) {
//			regn_no = "r_reg_no";
//		} else {
//			regn_no = "r_regn_no";
//		}
//		try {
//			String sql = "select * from " + tableName + " where " + regn_no
//					+ "=?  and state_cd=? and off_cd=? and flag_status=false  order by recp_dt";
//			tmgr = new TransactionManager("adtlPendingTax");
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, regnNo);
//
//			ps.setString(2, stateCd);
//			ps.setInt(3, off_cd);
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				tax_Pending_opdt = DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("recp_dt"));
//				dateTaxFromPending = DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("tax_from"));
//				dateTaxFromPending1 = DateUtil.getDateInDDMMYYYY(DateUtil.addToDate(dateTaxFromPending, 1, 15));
//				dateTaxUptoPending = DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("tax_upto"));
//			}
//		} catch (SQLException e) {
//			LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				 LOGGER.error(e.getMessage());
//
//			}
//		}
//		try {
//			if (dateTaxFromPending != null) {
//				d1 = format.parse(dateTaxFromPending);
//			}
//			if (tax_Pending_opdt != null) {
//				d2 = format.parse(tax_Pending_opdt);
//			}
//		} catch (ParseException e) {
//			 LOGGER.error(e.getMessage());
//		}
//
//		long diffInDays = 0;
//		if (d1 != null && d2 != null) {
//			diffInDays = getDate1MinusDate2_Days(d1, d2);
//		} // this fun is for date diff calulation
//		//System.out.println("Time in diffInDays: " + diffInDays + " days.");
//		if (diffInDays >= 16 && diffInDays <= 30) {
//			penalty_percent = 25;
//		} else if (diffInDays >= 31 && diffInDays <= 60) {
//			penalty_percent = 50;
//		} else if (diffInDays >= 61 && diffInDays <= 90) {
//			penalty_percent = 100;
//		} else if (diffInDays > 90) {
//			penalty_percent = 200;
//		}
//		//System.out.println("penalty_percent=" + penalty_percent);
//		// pennalty calculation
//		long amt = (long) taxAmt;
//		//System.out.println("amt=" + amt);
//		panalty = (amt * penalty_percent) / 100;
//		//System.out.println("panalty=" + panalty);
//		// Date diff by jyoti Chaudhary
//		return panalty;
//	}
//
//	public static Boolean adtlTaxPayer(List<DOTaxDetail> listTaxBreakUp, OwnerDetailsDobj dobj)
//			throws SQLException, DateUtilsException {
//		RowSet rs = null;
//		PreparedStatement ps = null;
//		TransactionManager tmgr = null;
//		String tax_Pending_opdt = null;
//		String dateTaxUptoPending = null;
//		String dateTaxFromPending = null;
//		String dateTaxuptoPending = null;
//		String dateTaxFromPending1 = null;
//		double taxamount = 0;
//		double taxfine = 0;
//
//		String taxMode = null;
//		Boolean flag = true;
//		try {
//			String sql = "select * from onlineschema.VT_TAXPAYER_RECORD"
//					+ " where  regn_no=?  and state_cd=? and off_cd=? and flag=false and pur_cd=59  order by recp_dt";
//			tmgr = new TransactionManager("adtlTaxPayer");
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, dobj.getRegn_no());
//			ps.setString(2, dobj.getState_cd());
//			ps.setInt(3, dobj.getOff_cd());
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//
//				// flag = false;
//				tax_Pending_opdt = DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("recp_dt"));
//				taxamount = rs.getDouble("tax_amt");
//				taxfine = rs.getDouble("fine");
//				dateTaxFromPending = DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("tax_from"));
//				dateTaxuptoPending = DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("tax_upto"));
//				taxMode = rs.getString("tax_mode");
//				if (taxMode.equalsIgnoreCase(listTaxBreakUp.get(0).getTAX_MODE())) {
//					flag = false;
//					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert",
//							"your Amount is due firstly pay this");
//					RequestContext.getCurrentInstance().showMessageInDialog(message);
//					// if (DateUtil.isAfter(tax_Pending_opdt,
//					// dateTaxFromPending1)) {
//					for (int i = 0; i < listTaxBreakUp.size(); i++) {
//						// if
//						// (listTaxBreakUp.get(i).getTAX_FROM().equalsIgnoreCase(dateTaxFromPending)
//						// &&
//						// listTaxBreakUp.get(i).getTAX_UPTO().equalsIgnoreCase(dateTaxUptoPending))
//						// {
//						listTaxBreakUp.get(i).setAMOUNT(taxamount);
//						listTaxBreakUp.get(i).setFINE(taxfine);
//						listTaxBreakUp.get(i).setTAX_FROM(dateTaxFromPending);
//						listTaxBreakUp.get(i).setTAX_UPTO(dateTaxuptoPending);
//						// }
//					}
//					// } else {
//					// for (int i = 0; i < listTaxBreakUp.size(); i++) {
//
//					// // if
//					// (listTaxBreakUp.get(i).getTAX_FROM().equalsIgnoreCase(dateTaxFromPending)
//					// &&
//					// listTaxBreakUp.get(i).getTAX_UPTO().equalsIgnoreCase(dateTaxUptoPending))
//					// {
//					// listTaxBreakUp.get(i).setPENALTY(0);
//					// // }
//					// }
//				} else {
//					flag = true;
//					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert",
//							"Please Select the Right Tax Mode");
//					RequestContext.getCurrentInstance().showMessageInDialog(message);
//				}
//			}
//		} catch (SQLException e) {
//			 LOGGER.error(e.getMessage());
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				 LOGGER.error(e.getMessage());
//			}
//		}
//		return flag;
//	}
//
//	// jyoti Chaudhary changes- for MV Tax Calculation 20June2017
//
//	public static Boolean MVPendingTax(List<DOTaxDetail> listTaxBreakUp, OwnerDetailsDobj dobj)
//			throws SQLException, DateUtilsException {
//		RowSet rs = null;
//		PreparedStatement ps = null;
//		TransactionManager tmgr = null;
//		String tax_Pending_opdt = null;
//		String dateTaxUptoPending = null;
//		String dateTaxFromPending = null;
//		String dateTaxFromPending1 = null;
//		String taxMode = null;
//		Boolean flag = true;
//		try {
//			String sql = "select * from onlineschema.MV_tax_pending_jh"
//					+ " where r_regn_no=?  and state_cd=? and off_cd=? and flag_status=false  order by recp_dt";
//			tmgr = new TransactionManager("MVPendingTax");
//			ps = tmgr.prepareStatement(sql);
//			ps.setString(1, dobj.getRegn_no());
//
//			ps.setString(2, dobj.getState_cd());
//			ps.setInt(3, dobj.getOff_cd());
//			rs = tmgr.fetchDetachedRowSet();
//			if (rs.next()) {
//				flag = false;
//				tax_Pending_opdt = DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("recp_dt"));
//				// tax_Pending_opdt=DateUtil.getDateInDDMMYYYY(DateUtil.addToDate(tax_Pending_opdt,
//				// 1, 15));
//				dateTaxFromPending = DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("tax_from"));
//				dateTaxFromPending1 = DateUtil.getDateInDDMMYYYY(DateUtil.addToDate(dateTaxFromPending, 1, 15));
//				dateTaxUptoPending = DateUtil.getStringYYYYMMDDtoStringDDMMYYYY(rs.getString("tax_upto"));
//				taxMode = rs.getString("tax_mode");
//				if (taxMode.equalsIgnoreCase(listTaxBreakUp.get(0).getTAX_MODE())) {
//					if (DateUtil.isAfter(tax_Pending_opdt, dateTaxFromPending1)) {
//						for (int i = 0; i < listTaxBreakUp.size(); i++) {
//							if (!listTaxBreakUp.get(i).getTAX_FROM().equalsIgnoreCase(dateTaxFromPending)
//									&& !listTaxBreakUp.get(i).getTAX_UPTO().equalsIgnoreCase(dateTaxUptoPending)) {
//								listTaxBreakUp.remove(i);
//							}
//						}
//					} else {
//						for (int i = 0; i < listTaxBreakUp.size(); i++) {
//							listTaxBreakUp.get(i).setPENALTY(0);
//							if (!listTaxBreakUp.get(i).getTAX_FROM().equalsIgnoreCase(dateTaxFromPending)
//									&& !listTaxBreakUp.get(i).getTAX_UPTO().equalsIgnoreCase(dateTaxUptoPending)) {
//								listTaxBreakUp.remove(i);
//
//							}
//						}
//
//					}
//				} else {
//					// FacesMessage message = new
//					// FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Please
//					// Select the Right Tax Mode");
//					// RequestContext.getCurrentInstance().showMessageInDialog(message);
//					// //itrTax.remove();
//					// RequestContext.getCurrentInstance().showMessageInDialog(new
//					// FacesMessage(FacesMessage.SEVERITY_WARN, "Alert", "Please
//					// Select the Right Tax Mode"));
//					RequestContext.getCurrentInstance().execute("PF('notrightmode').show();");
//
//				}
//
//			}
//			//
//		} catch (SQLException e) {
//			 LOGGER.error(e.getMessage());
//
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (tmgr != null) {
//					tmgr.release();
//				}
//			} catch (Exception e) {
//				 LOGGER.error(e.getMessage());
//			}
//		}
//		return flag;
//
//	}
//
//	/**
//	 * For given two dates return the difference in days.
//	 *
//	 * @param dateStr1
//	 *            Date format string ('MM/DD/YYYY HH24:MM:SS' format)
//	 * @param dateStr2
//	 *            Date format string ('MM/DD/YYYY HH24:MM:SS' format)
//	 *
//	 * @return Number of days difference
//	 *
//	 * @throws DateUtilsException
//	 */
//	public static long getDate1MinusDate2_Days(String dateStr1, String dateStr2) throws DateUtilsException {
//
//		// Parse the date
//		Date date1 = parseDate(dateStr1);
//		Date date2 = parseDate(dateStr2);
//
//		// Return
//		return getDate1MinusDate2_Days(date1, date2);
//	}
//
//	/**
//	 * For given two dates return the difference in days.
//	 *
//	 * @param date1
//	 *            Date object
//	 * @param date2
//	 *            Date object
//	 *
//	 * @return Number of days difference
//	 *
//	 * @throws DateUtilsException
//	 */
//	public static long getDate1MinusDate2_Days(Date date1, Date date2) throws DateUtilsException {
//		long MILLISECONDS_IN_ONE_DAY = 1000 * 60 * 60 * 24;
//		if (date1 == null || date2 == null) {
//			throw new DateUtilsException("DEV_ERROR : Check the dates '" + date1 + "', '" + date2 + "'");
//		}
//
//		// Time in milliseconds since "the epoch" (January 1, 1970,
//		// 00:00:00 GMT)
//		long t1 = date1.getTime();
//		long t2 = date2.getTime();
//		long diff = t2 - t1;
//		long days = diff / MILLISECONDS_IN_ONE_DAY; // Integral Division
//		return days;
//	}
//	// jyoti Chaudhary changess 20June2017
//
//}
