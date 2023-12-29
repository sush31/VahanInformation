package bean;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import databaseconnection.TableConstants;
import databaseconnection.TableList;
import databaseconnection.TransactionManager;
import dobj.PermitDobj;
import impl.PermitImpl;

import javax.faces.model.SelectItem;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpSession session = null;
	private List<SelectItem> state_list;
	private List<SelectItem> service_list;
	private  String selectedState;
	private  String selectedService;

	@PostConstruct
	public void init() {

		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		state_list = getStateList();
		//session.setAttribute("state", selectedState);
	}

	public  List<SelectItem> getStateList() {
		state_list = new ArrayList<>();
		TransactionManager tmgr;
		String Sql = "Select state_code,descr from " + TableList.TM_STATE;
		PreparedStatement ps;
		RowSet rs;
		try {
			tmgr = new TransactionManager("fetch state list");
			ps = tmgr.prepareStatement(Sql);
			rs = tmgr.fetchDetachedRowSet_No_release();
			while (rs.next()) {
				state_list.add(new SelectItem(rs.getString("state_code"), rs.getString("descr")));
			}
		} catch (SQLException e) {
			System.out.println(e);

		} catch (Exception e) {
			System.out.println(e);
		}
		return state_list;
	}

   public List<SelectItem> getServicesList(javax.faces.event.AjaxBehaviorEvent event) {
	    session.setAttribute("state", selectedState);
		service_list = new ArrayList<>();
		TransactionManager tmgr=null;
		String sql = "select distinct tmf.pur_cd,tp.descr from "+ TableList.TM_PURPOSE_ACTION_FLOW +" tmf inner join "+ TableList.TM_PURPOSE_MAST+" tp"
				+ " on tmf.pur_cd=tp.pur_cd  where state_cd=? group by tmf.pur_cd,tp.descr order by pur_cd";
		PreparedStatement ps;
		RowSet rs;
		try {
			tmgr = new TransactionManager("fetch services list");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1,selectedState);
			rs = tmgr.fetchDetachedRowSet_No_release();
			while (rs.next()) {
				service_list.add(new SelectItem(rs.getString("pur_cd"), rs.getString("descr")));
			}
		} catch (SQLException e) {
			System.out.println(e);

		} catch (Exception e) {
			System.out.println(e);
		}
		finally
		{
			try {
				if (tmgr != null) {
					tmgr.release();
				}
			} catch (Exception ee) {
				System.out.println(ee);
			}
		}
       return service_list;
	}
   
   public void redirectToSelectedService()
	{
		//System.out.println(selectedService);
	  int purCd=(Integer.parseInt(selectedService));
	  if(purCd==TableConstants.VM_PMT_FRESH_PUR_CD)
	  {
		  PermitDobj permitdobj= new PermitDobj();
		  permitdobj=new PermitImpl().getPermitServiceAttributes(selectedState,purCd);
	  }
		
	}

	

	public List<SelectItem> getState_list() {
		return state_list;
	}

	public void setState_list(List<SelectItem> state_list) {
		this.state_list = state_list;
	}

	public String getSelectedState() {
		return selectedState;
	}

	public void setSelectedState(String selectedState) {
		this.selectedState = selectedState;
	}

	public List<SelectItem> getService_list() {
		return service_list;
	}

	public void setService_list(List<SelectItem> service_list) {
		this.service_list = service_list;
	}

	public String getSelectedService() {
		return selectedService;
	}

	public void setSelectedService(String selectedService) {
		this.selectedService = selectedService;
	}
	
	
	

}
