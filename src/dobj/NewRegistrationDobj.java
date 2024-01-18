package dobj;

import java.util.ArrayList;

public class NewRegistrationDobj {

	public boolean serviceRto;
	public boolean serviceCitizen;
	public ArrayList<String> feesApplicable;
	public String feeExempt;
	public String taxExempt;
	public boolean uploadDocument;
	public String mobileAuthentication;
	
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

	public ArrayList<String> getFeesApplicable() {
		return feesApplicable;
	}

	public void setFeesApplicable(ArrayList<String> feesApplicable) {
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

	public String getTaxExempt() {
		return taxExempt;
	}
	
	
	public String getMobileAuthentication() {
		return mobileAuthentication;
	}
	
}
