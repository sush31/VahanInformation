package impl;

import java.sql.PreparedStatement;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import CommonUtils.FillMapUtility;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import dobj.NonuseRestoreRemoveDobj;
import dobj.RenewalOfRegistrationDobj;
import server.CommonUtils;

public class NonUseRestoreImpl {

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	int purCd = Integer.parseInt((String) session.getAttribute("purcd"));
	String stateCd = (String) session.getAttribute("state");
	
	public void fetchNonuseAttributes(NonuseRestoreRemoveDobj nonuseDobj) {
			TransactionManagerReadOnly tmgr = null;
			PreparedStatement ps = null;
			RowSet rs = null;
			String sql = null;
			sql = "select nonuse_adjust_in_tax_amt,skip_fitness_validation_in_nonuse,nonuse_continue_without_restore,disable_nonuse_fromdate_in_nonuse_continue,"
					+ "docs_Surrender,vehicle_inspect_mandatory,declare_withdrawl_date,exemfrom_first_dateofmonth,exemto_last_dateofmonth,"
					+ "exemupto_financial_year,require_advance_tax,vehicle_inspect_for_removalshift,taxclear_for_nonuse_rebate_in_duration,"
					+ "remove_frm_nonuse_in_removalshift,nonuse_frm_backdate,exemupto_quarter_month,allow_nonuse_if_tax_uptodate,nonuse_allowed_min_month,"
					+ "nonuse_allowed_max_month,nonuse_allowed_min_month from " + TableList.TM_CONFIGURATION_NONUSE+ " where state_cd=?";
			try {
				tmgr = new TransactionManagerReadOnly("fetch nonuse properties");
				ps = tmgr.prepareStatement(sql);
				ps.setString(1, stateCd);
				rs = tmgr.fetchDetachedRowSet();
				if (rs.next()) {
					nonuseDobj.setAdvTax(rs.getBoolean("nonuse_adjust_in_tax_amt"));
					nonuseDobj.setAllowNonUseIfTaxUptodate(rs.getBoolean("nonuse_adjust_in_tax_amt"));
					nonuseDobj.setDeclareWithdrawdate(rs.getBoolean("decalre_withdrawl_date"));
					nonuseDobj.setDisableFromDate(rs.getBoolean("disable_nonuse_fromdate_in_nonuse_continue"));
					nonuseDobj.setDocsSurr(rs.getBoolean("docs_Surrender"));
					nonuseDobj.setExempUptoFinYr(rs.getBoolean("nonuse_adjust_in_tax_amt"));
					nonuseDobj.setFirstDateOfMonth(rs.getBoolean("exemfrom_first_dateofmonth"));
					nonuseDobj.setLastDateOfMonth(rs.getBoolean("exemto_last_dateofmonth"));
					nonuseDobj.setMaxMonth(rs.getInt("nonuse_allowed_max_month"));
					nonuseDobj.setMinMonth(rs.getInt("nonuse_allowed_min_month"));
					nonuseDobj.setNonuseContinueWithoutRestore(rs.getBoolean("nonuse_continue_without_restore"));
					nonuseDobj.setNonUseFromBackdate(rs.getBoolean("nonuse_adjust_in_tax_amt"));
					nonuseDobj.setQuarterMonth(rs.getBoolean("nonuse_adjust_in_tax_amt"));
					nonuseDobj.setRemoveFromNonuseInRemovalshift(rs.getBoolean("nonuse_adjust_in_tax_amt"));
					nonuseDobj.setSkipFitness(rs.getBoolean("skip_fitness_validation_in_nonuse"));
					nonuseDobj.setTaxAmountAdjustment(rs.getBoolean("nonuse_adjust_in_tax_amt"));
					nonuseDobj.setTaxClrForNonuseRebate(rs.getBoolean("nonuse_adjust_in_tax_amt"));
					nonuseDobj.setVehicleInspection(rs.getBoolean("vehicle_inspect_mandatory"));
					nonuseDobj.setVehInspectForRemovalshift(rs.getBoolean("nonuse_adjust_in_tax_amt"));
				
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

		
	}
	


