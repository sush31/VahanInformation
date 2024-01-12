package dobj;

import java.io.Serializable;
import java.util.ArrayList;

public class PermitDobj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean serviceRto;
	public boolean serviceCitizen;
	public boolean via_registered_mobile;
	public String aadharAuthentication;
	public String uploadDocument;
	public String citizenFeeExempt;
	//public String rtoFeeExempt;

	public PermitDobj() {

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

	public String isAadharAuthentication() {
		return aadharAuthentication;
	}

	public void setAadharAuthentication(String aadharAuthentication) {
		this.aadharAuthentication = aadharAuthentication;
	}

	public String isUploadDocument() {
		return uploadDocument;
	}

	public void setUploadDocument(String uploadDocument) {
		this.uploadDocument = uploadDocument;
	}

	public boolean isVia_registered_mobile() {
		return via_registered_mobile;
	}

	public void setVia_registered_mobile(boolean via_registered_mobile) {
		this.via_registered_mobile = via_registered_mobile;
	}

	public String getCitizenFeeExempt() {
		return citizenFeeExempt;
	}

	public void setCitizenFeeExempt(String citizenFeeExempt) {
		this.citizenFeeExempt = citizenFeeExempt;
	}

//	public String getRtoFeeExempt() {
//		return rtoFeeExempt;
//	}
//
//	public void setRtoFeeExempt(String rtoFeeExempt) {
//		this.rtoFeeExempt = rtoFeeExempt;
//	}

	public String getAadharAuthentication() {
		return aadharAuthentication;
	}

	public String getUploadDocument() {
		return uploadDocument;
	}

}
