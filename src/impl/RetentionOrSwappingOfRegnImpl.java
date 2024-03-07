package impl;

import java.sql.PreparedStatement;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import databaseconnection.TableList;
import databaseconnection.TransactionManagerReadOnly;
import dobj.NonuseRestoreRemoveDobj;
import dobj.RetentionOrSwappingOfRegnDobj;

public class RetentionOrSwappingOfRegnImpl {
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	int purCd = Integer.parseInt((String) session.getAttribute("purcd"));
	String stateCd = (String) session.getAttribute("state");
	

	public void getSwappingAttributes(RetentionOrSwappingOfRegnDobj swappingDobj) {
	
			TransactionManagerReadOnly tmgr = null;
			PreparedStatement ps = null;
			RowSet rs = null;
			String sql = null;
			sql = "select same_owner_name,same_father_name,same_vehicle_class,multiple_swapping_allowed,old_vehicle_age,new_vehicle_age,swapping_allowed_theft_untraced_case,"
					+"one_regn_must_be_fancy_no,same_vehicle_category from "+TableList.TM_CONFIGURATION_SWAPPING+" where state_cd=?";
			try {
				tmgr = new TransactionManagerReadOnly("fetch nonuse properties");
				ps = tmgr.prepareStatement(sql);
				ps.setString(1, stateCd);
				rs = tmgr.fetchDetachedRowSet();
				if (rs.next()) {
	
					swappingDobj.setFancyNum(rs.getBoolean("one_regn_must_be_fancy_no"));
					swappingDobj.setMultipleSwap(rs.getBoolean("multiple_swapping_allowed"));
					swappingDobj.setNewVehAge(rs.getInt("new_vehicle_age"));
					swappingDobj.setOldVehAge(rs.getInt("old_vehicle_age"));
					swappingDobj.setSameFatherName(rs.getBoolean("same_father_name"));
					swappingDobj.setSameOwnerName(rs.getBoolean("same_owner_name"));
					swappingDobj.setSameVehCatg(rs.getBoolean("same_vehicle_category"));
					swappingDobj.setSameVehCls(rs.getBoolean("same_vehicle_class"));
					swappingDobj.setTheftCaseSwappingAllowed(rs.getBoolean("swapping_allowed_theft_untraced_case"));
					
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

	
	
	

