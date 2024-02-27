package bean;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import org.apache.poi.xssf.model.Table;

import CommonUtils.FillMapUtility;
import databaseconnection.TableConstants;
import databaseconnection.TableList;
import databaseconnection.TransactionManager;
import dobj.CitizenServiceFlowDobj;
import dobj.CommonDobj;
import dobj.ConversionOfVehicleDobj;
import dobj.ConvertibleClasses;
import dobj.FitnessDobj;
import dobj.FitnessValidityDobj;
import dobj.NewRegistrationDobj;
import dobj.NottobeTransactedDobj;
import dobj.PermitDobj;
import dobj.RenewalOfRegistrationDobj;
import dobj.RtoServiceFlowDobj;
import dobj.TransferOfOwnershipDobj;
import impl.CommonServiceImpl;
import impl.FitnessImpl;
import impl.NewRegistrationImpl;
import impl.NottobeTransactedImpl;
import impl.PermitImpl;
import impl.RenewalOfRegistrationImpl;
import impl.TransferOfOwnershipImpl;

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
	public NewRegistrationDobj newregndobj = new NewRegistrationDobj();
	public CitizenServiceFlowDobj citizenFlow = new CitizenServiceFlowDobj();
	public RenewalOfRegistrationDobj renewalRegDobj = new RenewalOfRegistrationDobj();
	public TransferOfOwnershipDobj transferOwnershipDobj = new TransferOfOwnershipDobj();
	public CommonDobj commonDobj = new CommonDobj();
	public ConversionOfVehicleDobj convDobj = new ConversionOfVehicleDobj();
	public RtoServiceFlowDobj rtoFlowdobj = new RtoServiceFlowDobj();
	ArrayList<CitizenServiceFlowDobj> flowCitizen = new ArrayList<>();
	ArrayList<RtoServiceFlowDobj> flowRto = new ArrayList<>();
	ConversionOfVehicleDobj conversionDobj = new ConversionOfVehicleDobj();
	NottobeTransactedDobj nottobeTransactedDobj=new NottobeTransactedDobj();
	ArrayList<ConvertibleClasses> list = new ArrayList<>();
	FitnessDobj fitnessDobj = new FitnessDobj();
	ArrayList<FitnessValidityDobj> fitnessValidityList = new ArrayList<>();
	public static Map<String, String> vmtaxslabfieldsmap = new LinkedHashMap<String, String>();
	public static Map<String, Map<String, String>> contextAwareCodeMeanings;
	

	@PostConstruct
	public void init() {

		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		state_list = getStateList();
		vmtaxslabfieldsmap = FillMapUtility.getCodeDescr();
		vmtaxslabfieldsmap.remove("<46>");
		vmtaxslabfieldsmap.put("<46>", "transport type");
		contextAwareCodeMeanings = FillMapUtility
				.fetchContextAwareCodeMeaningsFromDatabase();
		
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
		String sql = "select distinct pur_cd,descr from  " + TableList.TM_PURPOSE_MAST
				+ " where pur_cd IN (select pur_cd from " + TableList.TM_PURPOSE_ACTION_FLOW + " where state_cd=?)";
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

	public String redirectToSelectedService() {
		String outcome = "";

		if (selectedService != null) {

			session.setAttribute("purcd", selectedService);
			purposeDescr = FillMapUtility.getPurposeDescr(Integer.parseInt(selectedService));

		}
		int purCd = (Integer.parseInt(selectedService));
		commonDobj.setServiceCitizen(FillMapUtility.isServiceCitizen(selectedState, purCd));
		commonDobj.setServiceRto(FillMapUtility.isServiceRto(selectedState, purCd));
		commonDobj.setApplInwardOtherRto(FillMapUtility.getApplInwardOtherRto(selectedState, purCd));
		commonDobj.setFeeExempt(FillMapUtility.getFeesExempt(selectedState, purCd));
		commonDobj.setTaxExempt(FillMapUtility.getFeesExempt(selectedState, purCd));
		commonDobj.setUploadDocumentRto(FillMapUtility.getDocumentUploadRTO(selectedState, purCd));
		commonDobj.setFeesApplicable(FillMapUtility.getFeesApplicableForNewRegn(selectedState, purCd));
		flowRto = new PermitImpl().getRtoServiceFlow(selectedState, purCd, rtoFlowdobj);
		if (commonDobj.isServiceCitizen() == true) {
			commonDobj.setUploadDocumentCitizen(FillMapUtility.getDocumentUploadCitizen(selectedState, purCd));
			boolean auth[] = FillMapUtility.getAdhaarAndMobAuthentication(selectedState, purCd);
			commonDobj.setAadharAuthenticationCitizen(auth[0]);
			commonDobj.setMobileAuthenticationCitizen(auth[1]);
			flowCitizen = FillMapUtility.getCitizenServiceFlow(selectedState, purCd, citizenFlow);
		}

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

			permitdobj = new PermitImpl().getPermitServiceAttributes(selectedState, purCd, permitdobj);
			flowCitizen = new PermitImpl().getCitizenServiceFlow(selectedState, purCd, citizenFlow);
			outcome = "redirectToPermit";

		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_NEW_VEHICLE
				|| purCd == TableConstants.VM_TRANSACTION_MAST_DEALER_NEW_VEHICLE
				|| purCd == TableConstants.VM_TRANSACTION_MAST_TEMP_REG
				|| purCd == TableConstants.VM_TRANSACTION_MAST_DEALER_NEW_TEMP_VEHICLE) {

			newregndobj = new NewRegistrationImpl().getNewRegistrationAttributes(selectedState, newregndobj);

			outcome = "redirectToNewregistration";

		}

		else if (purCd == TableConstants.VM_TRANSACTION_MAST_REN_REG) {

			renewalRegDobj = new RenewalOfRegistrationImpl().getRenewalRegistrationAttributes(selectedState,
					renewalRegDobj);
			outcome = "redirectToRenewalRegistration";

		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_TO) {

			transferOwnershipDobj.setServiceCitizen((FillMapUtility.isServiceCitizen(selectedState, purCd)));
			transferOwnershipDobj.setServiceRto(FillMapUtility.isServiceRto(selectedState, purCd));
			transferOwnershipDobj
					.setUploadDocumentCitizen(FillMapUtility.getDocumentUploadCitizen(selectedState, purCd));
			transferOwnershipDobj.setUploadDocumentRto(FillMapUtility.getDocumentUploadRTO(selectedState, purCd));
			transferOwnershipDobj.setApplInwardOtherRto(FillMapUtility.getApplInwardOtherRto(selectedState, purCd));
			transferOwnershipDobj.setFeeExempt(FillMapUtility.getFeesExempt(selectedState, purCd));
			transferOwnershipDobj.setTaxExempt(FillMapUtility.getFeesExempt(selectedState, purCd));
			boolean auth1[] = FillMapUtility.getAdhaarAndMobAuthentication(selectedState, purCd);
			transferOwnershipDobj.setAadharAuthenticationCitizen(auth1[0]);
			transferOwnershipDobj.setMobileAuthenticationCitizen(auth1[1]);
			transferOwnershipDobj.setFeesApplicable(FillMapUtility.getFeesApplicableForNewRegn(selectedState, purCd));
			transferOwnershipDobj = new TransferOfOwnershipImpl().getTOAttributes(transferOwnershipDobj);
			outcome = "redirectToTransferOfOwnership";

		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_CHG_ADD
				|| purCd == TableConstants.VM_TRANSACTION_MAST_FRESH_RC
				|| purCd == TableConstants.VM_TRANSACTION_MAST_DUP_RC
				|| purCd == TableConstants.VM_TRANSACTION_MAST_VEH_ALTER
				|| purCd == TableConstants.VM_TRANSACTION_MAST_RC_PARTICULARS
				|| purCd == TableConstants.VM_MAST_RC_SURRENDER || purCd == TableConstants.VM_MAST_RC_RELEASE
				|| purCd == TableConstants.VM_MAST_RC_CANCELLATION || purCd == TableConstants.VM_TRANSACTION_MAST_DUP_RC
				|| purCd == TableConstants.VM_TRANSACTION_MAST_DUP_FC
				|| purCd == TableConstants.VM_TRANSACTION_CONVERSION_PAPER_RC_TO_SMARTCARD
				|| purCd == TableConstants.VM_TRANSACTION_ADD_MODIFY_NOMINEE) {

			outcome = "redirectToCommonService";

		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_DUP_HSRP) {
			commonDobj.setHsrp(new CommonServiceImpl().getHsrp(selectedState));
			commonDobj.setOldhsrp(new CommonServiceImpl().getOldHsrp(selectedState));
			outcome = "redirectToHsrp";

		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_ADD_HYPO
				|| purCd == TableConstants.VM_TRANSACTION_MAST_REM_HYPO
				|| purCd == TableConstants.VM_TRANSACTION_MAST_HPC) {

			commonDobj.setVerifyBankOnhypth(new CommonServiceImpl().getHypthVerifyBank(selectedState, purCd));
			outcome = "redirectToHypothecationService";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_VEH_CONVERSION) {
			convDobj.setCommonDobj(commonDobj);
			convDobj.setReassignOrRetainNo(new CommonServiceImpl().getReassignNumberInConversion(selectedState));
			list = new CommonServiceImpl().getConvertibleClasses();
			outcome = "redirectToConversionOfVehicle";

		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_FIT_CERT
				|| purCd == TableConstants.VM_TRANSACTION_MAST_NEW_VEHICLE_FITNESS) {
			fitnessDobj.setCommonDobj(commonDobj);
			new FitnessImpl().getFitnessAttributes(selectedState, fitnessDobj);
			fitnessValidityList = new FitnessImpl().getFitnessValidityList(selectedState);
			outcome = "redirectToFitness";
		} else if (purCd == TableConstants.VM_TRANSACTION_MAST_NOC
				|| purCd == TableConstants.VM_TRANSACTION_MAST_NOC_CANCEL) {
			commonDobj.setNocAllowForSameState(new CommonServiceImpl().nocRequiredForSameState(selectedState));
			outcome = "noc";
		}
		else if(purCd==TableConstants.VM_TRANSACTION_NOT_TO_BE_TRANSACTED)
		{
			nottobeTransactedDobj.setAllowedConditionFormulaForAction(new NottobeTransactedImpl().getAllowedConditionFormulaDescrForAction(selectedState));
			nottobeTransactedDobj.setAllowedConditionFormulaForPurpose(new NottobeTransactedImpl().getAllowedConditionFormulaDescrForPurpose(selectedState));
			
		}
		return outcome;

	}

	public ArrayList<CitizenServiceFlowDobj> getFlowCitizen() {
		return flowCitizen;
	}

	public void setFlowCitizen(ArrayList<CitizenServiceFlowDobj> flowCitizen) {
		this.flowCitizen = flowCitizen;
	}

	public RenewalOfRegistrationDobj getRenewalRegDobj() {
		return renewalRegDobj;
	}

	public void setRenewalRegDobj(RenewalOfRegistrationDobj renewalRegDobj) {
		this.renewalRegDobj = renewalRegDobj;
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

	public TransferOfOwnershipDobj getTransferOwnershipDobj() {
		return transferOwnershipDobj;
	}

	public void setTransferOwnershipDobj(TransferOfOwnershipDobj transferOwnershipDobj) {
		this.transferOwnershipDobj = transferOwnershipDobj;
	}

	public CommonDobj getCommonDobj() {
		return commonDobj;
	}

	public void setCommonDobj(CommonDobj commonDobj) {
		this.commonDobj = commonDobj;
	}

	public ConversionOfVehicleDobj getConvDobj() {
		return convDobj;
	}

	public void setConvDobj(ConversionOfVehicleDobj convDobj) {
		this.convDobj = convDobj;
	}

	public ConversionOfVehicleDobj getConversionDobj() {
		return conversionDobj;
	}

	public void setConversionDobj(ConversionOfVehicleDobj conversionDobj) {
		this.conversionDobj = conversionDobj;
	}

	public ArrayList<ConvertibleClasses> getList() {
		return list;
	}

	public void setList(ArrayList<ConvertibleClasses> list) {
		this.list = list;
	}

	public FitnessDobj getFitnessDobj() {
		return fitnessDobj;
	}

	public void setFitnessDobj(FitnessDobj fitnessDobj) {
		this.fitnessDobj = fitnessDobj;
	}

	public ArrayList<FitnessValidityDobj> getFitnessValidityList() {
		return fitnessValidityList;
	}

	public void setFitnessValidityList(ArrayList<FitnessValidityDobj> fitnessValidityList) {
		this.fitnessValidityList = fitnessValidityList;
	}

}
