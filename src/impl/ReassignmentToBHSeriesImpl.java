package impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import CommonUtils.FillMapUtility;
import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import dobj.FitnessValidityDobj;
import dobj.ReassignmentToBHSeriesDobj;

public class ReassignmentToBHSeriesImpl {
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	int purCd = Integer.parseInt((String) session.getAttribute("purcd"));
	String stateCd = (String) session.getAttribute("state");
	
	public void getReassignmentToBhSeries(ReassignmentToBHSeriesDobj bhSeriesDobj) {
		TransactionManagerReadOnly tmgr = null;
		PreparedStatement ps = null;
		RowSet rs = null;
		String sql = null;
		sql = "select bh_series_allowed,bh_series_to_allowed from " + TableList.TM_CONFIGURATION_BH_SERIES
				+ "  where state_cd=?";

		try {
			tmgr = new TransactionManagerReadOnly("fetch bh series properties ");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, stateCd);
			rs = tmgr.fetchDetachedRowSet();
			if (rs.next()) {
				bhSeriesDobj.setBhSeriesAllowed(rs.getBoolean("bh_series_allowed"));
				bhSeriesDobj.setBhSeriesToAllowed(rs.getBoolean("bh_series_to_allowed"));
				   
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
