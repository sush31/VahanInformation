package dobj;

import java.util.Map;

public class TransferOfOwnershipDobj {

	public boolean serviceRto;
	public boolean serviceCitizen;
	public String feeExempt;
	public String taxExempt;
	public boolean uploadDocumentRto;
	public boolean uploadDocumentCitizen;
	public boolean mobileAuthenticationCitizen;
	public boolean aadharAuthenticationCitizen;
	public boolean applInwardOtherRto;
    public String transferOfOwnershipWithoutNoc;
	public boolean transferOfOwnershipWithtNoc;
	public boolean toRetention;
	public Map<String,String> feesApplicable;
	
	
	
	public Map<String, String> getFeesApplicable() {
		return feesApplicable;
	}
	public void setFeesApplicable(Map<String, String> feesApplicable) {
		this.feesApplicable = feesApplicable;
	}
	public String getTransferOfOwnershipWithoutNoc() {
		return transferOfOwnershipWithoutNoc;
	}
	public void setTransferOfOwnershipWithoutNoc(String transferOfOwnershipWithoutNoc) {
		this.transferOfOwnershipWithoutNoc = transferOfOwnershipWithoutNoc;
	}
	public boolean getTransferOfOwnershipWithtNoc() {
		return transferOfOwnershipWithtNoc;
	}
	public void setTransferOfOwnershipWithtNoc(boolean transferOfOwnershipWithtNoc) {
		this.transferOfOwnershipWithtNoc = transferOfOwnershipWithtNoc;
	}
	public boolean isToRetention() {
		return toRetention;
	}
	public void setToRetention(boolean toRetention) {
		this.toRetention = toRetention;
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
	public void setFeeExempt(String feeExempt) {
		this.feeExempt = feeExempt;
	}
	public boolean isUploadDocumentRto() {
		return uploadDocumentRto;
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
	public String getTaxExempt() {
		return taxExempt;
	}
	public void setTaxExempt(String taxExempt) {
		this.taxExempt = taxExempt;
	}
	
	
	
}
