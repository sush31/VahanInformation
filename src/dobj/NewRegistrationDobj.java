package dobj;

import java.util.Map;

public class NewRegistrationDobj {

	public boolean serviceRto;
	public boolean serviceCitizen;
	public String feesApplicableCondition;
	public String feeExempt;
	public String taxExempt;
	public boolean uploadDocument;
	public String mobileAuthentication;
	public boolean aadharAuthentication;
	public Map<String,String> feesApplicable;
	
	public NewRegistrationDobj()
	{
		
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



	public Map<String, String> getFeesApplicable() {
		return feesApplicable;
	}

	public void setFeesApplicable(Map<String, String> feesApplicable) {
		this.feesApplicable = feesApplicable;
	}

	public String getFeeExempt() {
		return feeExempt;
	}

	public void setFeeExempt(String feeExempt) {
		this.feeExempt = feeExempt;
	}

	public String isTaxExempt() {
		return taxExempt;
	}

	public void setTaxExempt(String taxExempt) {
		this.taxExempt = taxExempt;
	}

	public boolean isUploadDocument() {
		return uploadDocument;
	}

	public void setUploadDocument(boolean uploadDocument) {
		this.uploadDocument = uploadDocument;
	}

	public String isMobileAuthentication() {
		return mobileAuthentication;
	}

	public void setMobileAuthentication(String mobileAuthentication) {
		this.mobileAuthentication = mobileAuthentication;
	}
	

	public boolean getAadharAuthentication() {
		return aadharAuthentication;
	}

	public void setAadharAuthentication(boolean aadharAuthentication) {
		this.aadharAuthentication = aadharAuthentication;
	}

	public String getTaxExempt() {
		return taxExempt;
	}
	
	
	public String getMobileAuthentication() {
		return mobileAuthentication;
	}

	public String getFeesApplicableCondition() {
		return feesApplicableCondition;
	}

	public void setFeesApplicableCondition(String feesApplicableCondition) {
		this.feesApplicableCondition = feesApplicableCondition;
	}
	
	
	
}
