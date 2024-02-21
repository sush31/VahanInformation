package dobj;

import java.util.Map;

public class CommonDobj {

	public boolean serviceRto;
	public boolean serviceCitizen;
	public String feeExempt;
	public Map<String,String> feesApplicable;
	public String taxExempt;
	public boolean uploadDocumentRto;
	public boolean uploadDocumentCitizen;
	public boolean mobileAuthenticationCitizen;
	public boolean aadharAuthenticationCitizen;
	public boolean applInwardOtherRto;
	public boolean verifyBankOnhypth;
	public boolean nocAllowForSameState;
	public boolean hsrp;
	public boolean oldhsrp;
	
	
	
	public CommonDobj()
	{
		
	}
	
	
	
	public boolean isNocAllowForSameState() {
		return nocAllowForSameState;
	}



	public void setNocAllowForSameState(boolean nocAllowForSameState) {
		this.nocAllowForSameState = nocAllowForSameState;
	}



	public Map<String, String> getFeesApplicable() {
		return feesApplicable;
	}


	public void setFeesApplicable(Map<String, String> feesApplicable) {
		this.feesApplicable = feesApplicable;
	}


	public boolean isServiceRto() {
		return serviceRto;
	}
	public void setServiceRto(boolean serviceRto) {
		this.serviceRto = serviceRto;
	}
	public boolean isServiceCitizen() {
		return serviceCitizen;
	}
	public void setServiceCitizen(boolean serviceCitizen) {
		this.serviceCitizen = serviceCitizen;
	}
	public String getFeeExempt() {
		return feeExempt;
	}
	
	public String getTaxExempt() {
		return taxExempt;
	}

	public void setTaxExempt(String taxExempt) {
		this.taxExempt = taxExempt;
	}

	public void setFeeExempt(String feeExempt) {
		this.feeExempt = feeExempt;
	}
	public boolean isUploadDocumentRto() {
		return uploadDocumentRto;
	}
	
	

	public boolean isVerifyBankOnhypth() {
		return verifyBankOnhypth;
	}

	public void setVerifyBankOnhypth(boolean verifyBankOnhypth) {
		this.verifyBankOnhypth = verifyBankOnhypth;
	}

	public void setUploadDocumentRto(boolean uploadDocumentRto) {
		this.uploadDocumentRto = uploadDocumentRto;
	}
	public boolean isUploadDocumentCitizen() {
		return uploadDocumentCitizen;
	}
	public void setUploadDocumentCitizen(boolean uploadDocumentCitizen) {
		this.uploadDocumentCitizen = uploadDocumentCitizen;
	}
	public boolean getMobileAuthenticationCitizen() {
		return mobileAuthenticationCitizen;
	}
	public void setMobileAuthenticationCitizen(boolean mobileAuthenticationCitizen) {
		this.mobileAuthenticationCitizen = mobileAuthenticationCitizen;
	}
	public boolean isAadharAuthenticationCitizen() {
		return aadharAuthenticationCitizen;
	}
	public void setAadharAuthenticationCitizen(boolean aadharAuthenticationCitizen) {
		this.aadharAuthenticationCitizen = aadharAuthenticationCitizen;
	}
	public boolean isApplInwardOtherRto() {
		return applInwardOtherRto;
	}
	public void setApplInwardOtherRto(boolean applInwardOtherRto) {
		this.applInwardOtherRto = applInwardOtherRto;
	}



	public boolean isHsrp() {
		return hsrp;
	}



	public void setHsrp(boolean hsrp) {
		this.hsrp = hsrp;
	}



	public boolean isOldhsrp() {
		return oldhsrp;
	}



	public void setOldhsrp(boolean oldhsrp) {
		this.oldhsrp = oldhsrp;
	}
	
	
	

	

	
	
	
	
	
}
