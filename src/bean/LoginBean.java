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

import CommonUtils.FillMapUtility;
import databaseconnection.TableConstants;
import databaseconnection.TableList;
import databaseconnection.TransactionManager;
import dobj.CitizenServiceFlowDobj;
import dobj.NewRegistrationDobj;
import dobj.PermitDobj;
import dobj.RtoServiceFlowDobj;
import impl.NewRegistrationImpl;
import impl.PermitImpl;

import javax.faces.model.SelectItem;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpSession session = null;
	private List<SelectItem> state_list;
	private List<SelectItem> service_list;
	private String selectedState;
	private String selectedService;
	private String purposeDescr;
	public PermitDobj permitdobj = new PermitDobj();
	public  NewRegistrationDobj newregndobj= new NewRegistrationDobj();
	public CitizenServiceFlowDobj citizenFlow = new CitizenServiceFlowDobj();
	public RtoServiceFlowDobj rtoFlowdobj = new RtoServiceFlowDobj();
	ArrayList<CitizenServiceFlowDobj> flowCitizen = new ArrayList<>();
	ArrayList<RtoServiceFlowDobj> flowRto = new ArrayList<>();
	public boolean tableShowPermit=false;
	public boolean tableShow=false;
	public boolean renderNewRegn=false;

	

	@PostConstruct
	public void init() {

		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		state_list = getStateList();
        session.setAttribute("state", selectedState);
	}

	public List<SelectItem> getStateList() {
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
		TransactionManager tmgr = null;
		String sql = "select distinct tmf.pur_cd,tp.descr from " + TableList.TM_PURPOSE_ACTION_FLOW + " tmf inner join "
				+ TableList.TM_PURPOSE_MAST + " tp"
				+ " on tmf.pur_cd=tp.pur_cd  where state_cd=? group by tmf.pur_cd,tp.descr order by pur_cd";
		PreparedStatement ps;
		RowSet rs;
		try {
			tmgr = new TransactionManager("fetch services list");
			ps = tmgr.prepareStatement(sql);
			ps.setString(1, selectedState);
			rs = tmgr.fetchDetachedRowSet_No_release();
			while (rs.next()) {
				service_list.add(new SelectItem(rs.getString("pur_cd"), rs.getString("descr")));
			}
		} catch (SQLException e) {
			System.out.println(e);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (tmgr != null) {
					tmgr.release();
				}
			} catch (Exception ee) {

			}
		}
		return service_list;
	}

	public void redirectToSelectedService() {
		 
		if (selectedService != null) {
			tableShow=true;
			session.setAttribute("purcd", selectedService);
			purposeDescr = FillMapUtility.getPurposeDescr(Integer.parseInt(selectedService));

		} else {
			tableShow = false;
		}
		int purCd = (Integer.parseInt(selectedService));
		if (purCd == TableConstants.VM_PMT_FRESH_PUR_CD || purCd == TableConstants.VM_PMT_APPLICATION_PUR_CD
				|| purCd == TableConstants.VM_PMT_RENEWAL_PUR_CD || purCd == TableConstants.VM_PMT_TRANSFER_PUR_CD
				|| purCd == TableConstants.VM_PMT_TRANSFER_DEATH_CASE_PUR_CD
				|| purCd == TableConstants.VM_PMT_VARIATION_ENDORSEMENT_PUR_CD
				|| purCd == TableConstants.VM_PMT_CA_PUR_CD || purCd == TableConstants.VM_PMT_CANCELATION_PUR_CD
				|| purCd == TableConstants.VM_PMT_DUPLICATE_PUR_CD || purCd == TableConstants.VM_PMT_TEMP_PUR_CD
				|| purCd == TableConstants.VM_PMT_SPECIAL_PUR_CD
				|| purCd == TableConstants.VM_PMT_TRANSFER_DEATH_CASE_PUR_CD
				|| purCd == TableConstants.VM_PMT_HOME_AUTH_PERMIT_PUR_CD
				|| purCd == TableConstants.VM_PMT_COUNTER_SIGNATURE_AFTER_AUTH
				|| purCd == TableConstants.VM_PMT_AUTH_COUNTER_SIGNATURE_PUR_CD
				|| purCd == TableConstants.VM_PMT_RENEWAL_HOME_AUTH_PERMIT_PUR_CD
				|| purCd == TableConstants.VM_PMT_SURRENDER_PUR_CD || purCd == TableConstants.VM_PMT_RESTORE_PUR_CD
				|| purCd == TableConstants.VM_PMT_REPLACE_VEH_PUR_CD) {
			tableShowPermit = true;
			permitdobj = new PermitImpl().getPermitServiceAttributes(selectedState, purCd, permitdobj);
			flowCitizen = new PermitImpl().getCitizenServiceFlow(selectedState, purCd, citizenFlow);
			flowRto = new PermitImpl().getRtoServiceFlow(selectedState, purCd, rtoFlowdobj);
		}
		else if(purCd==TableConstants.VM_TRANSACTION_MAST_NEW_VEHICLE)
		{
			
			newregndobj=new NewRegistrationImpl().getNewRegistrationAttributes(selectedState,newregndobj);
			renderNewRegn=true;
			tableShowPermit=false;
		}

	}

	public ArrayList<CitizenServiceFlowDobj> getFlowCitizen() {
		return flowCitizen;
	}

	public void setFlowCitizen(ArrayList<CitizenServiceFlowDobj> flowCitizen) {
		this.flowCitizen = flowCitizen;
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

	public PermitDobj getPermitdobj() {
		return permitdobj;
	}

	public void setPermitdobj(PermitDobj permitdobj) {
		this.permitdobj = permitdobj;
	}
	

	

	public boolean isTableShow() {
		return tableShow;
	}

	public void setTableShow(boolean tableShow) {
		this.tableShow = tableShow;
	}

	public boolean isTableShowPermit() {
		return tableShowPermit;
	}

	public void setTableShowPermit(boolean tableShowPermit) {
		this.tableShowPermit = tableShowPermit;
	}

	public String getPurposeDescr() {
		return purposeDescr;
	}

	public void setPurposeDescr(String purposeDescr) {
		this.purposeDescr = purposeDescr;
	}

	public CitizenServiceFlowDobj getCitizenFlow() {
		return citizenFlow;
	}

	public void setCitizenFlow(CitizenServiceFlowDobj citizenFlow) {
		this.citizenFlow = citizenFlow;
	}

	public RtoServiceFlowDobj getRtoFlowdobj() {
		return rtoFlowdobj;
	}

	public void setRtoFlowdobj(RtoServiceFlowDobj rtoFlowdobj) {
		this.rtoFlowdobj = rtoFlowdobj;
	}

	public ArrayList<RtoServiceFlowDobj> getFlowRto() {
		return flowRto;
	}

	public void setFlowRto(ArrayList<RtoServiceFlowDobj> flowRto) {
		this.flowRto = flowRto;
	}

	public NewRegistrationDobj getNewregndobj() {
		return newregndobj;
	}

	public void setNewregndobj(NewRegistrationDobj newregndobj) {
		this.newregndobj = newregndobj;
	}
	public boolean isRenderNewRegn() {
		return renderNewRegn;
	}

	public void setRenderNewRegn(boolean renderNewRegn) {
		this.renderNewRegn = renderNewRegn;
	}
	

}
