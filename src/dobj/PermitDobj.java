package dobj;

import java.util.ArrayList;

public class PermitDobj {

	private boolean isServiceRto;
	private boolean isServiceCitizen;
	private boolean isFeeRequired;
	boolean via_registered_mobile;
	private boolean aadharAuthentication;
	private String faceless;
	private boolean uploadDocument;
	private String uploadDocumentCondition;
	private ArrayList<Object> flow;
	public boolean isServiceRto() {
		return isServiceRto;
	}
	public void setServiceRto(boolean isServiceRto) {
		this.isServiceRto = isServiceRto;
	}
	public boolean isServiceCitizen() {
		return isServiceCitizen;
	}
	public void setServiceCitizen(boolean isServiceCitizen) {
		this.isServiceCitizen = isServiceCitizen;
	}
	public boolean isFeeRequired() {
		return isFeeRequired;
	}
	public void setFeeRequired(boolean isFeeRequired) {
		this.isFeeRequired = isFeeRequired;
	}
	public boolean isAadharAuthentication() {
		return aadharAuthentication;
	}
	public void setAadharAuthentication(boolean aadharAuthentication) {
		this.aadharAuthentication = aadharAuthentication;
	}
	public boolean isUploadDocument() {
		return uploadDocument;
	}
	public void setUploadDocument(boolean uploadDocument) {
		this.uploadDocument = uploadDocument;
	}
	public ArrayList<Object> getFlow() {
		return flow;
	}
	public void setFlow(ArrayList<Object> flow) {
		this.flow = flow;
	}
	public String getUploadDocumentCondition() {
		return uploadDocumentCondition;
	}
	public void setUploadDocumentCondition(String uploadDocumentCondition) {
		this.uploadDocumentCondition = uploadDocumentCondition;
	}
	public boolean isVia_registered_mobile() {
		return via_registered_mobile;
	}
	public void setVia_registered_mobile(boolean via_registered_mobile) {
		this.via_registered_mobile = via_registered_mobile;
	}
	public String getFaceless() {
		return faceless;
	}
	public void setFaceless(String faceless) {
		this.faceless = faceless;
	}
	
	
	
	
}
