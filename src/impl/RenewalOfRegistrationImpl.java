package impl;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import CommonUtils.FillMapUtility;
import dobj.RenewalOfRegistrationDobj;

public class RenewalOfRegistrationImpl {
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	int purCd = Integer.parseInt((String) session.getAttribute("purcd"));
	String stateCd= (String) session.getAttribute("state");

	public RenewalOfRegistrationDobj getRenewalRegistrationAttributes(String selectedState,RenewalOfRegistrationDobj renewalRegDobj) {
		
		renewalRegDobj.setServiceRto(new PermitImpl().isServiceRto(stateCd, purCd));
		renewalRegDobj.setServiceCitizen(new PermitImpl().isServiceCitizen(stateCd, purCd));
		boolean auth[]=FillMapUtility.getAdhaarAndMobAuthentication(stateCd, purCd);
		renewalRegDobj.setAadharAuthenticationCitizen(auth[0]);
		renewalRegDobj.setMobileAuthenticationCitizen(auth[1]);
		renewalRegDobj.setAadharAuthenticationRto(false);
		renewalRegDobj.setMobileAuthenticationRto(false);
		renewalRegDobj.setUploadDocumentCitizen(FillMapUtility.getDocumentUploadCitizen(stateCd, purCd));
		renewalRegDobj.setUploadDocumentRto(FillMapUtility.getDocumentUploadRTO(stateCd, purCd));
		renewalRegDobj.setFeesApplicable(FillMapUtility.getFeesApplicableForNewRegn(stateCd,purCd));
		
		
		
		return null;
	}
	
	

	
	
}
