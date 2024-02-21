package dobj;

public class FitnessDobj {

	CommonDobj commonDobj;
	FitnessValidityDobj fitnessValidityDobj;
	private String vltdCheck;
	private boolean docUpload;
	private boolean newVehFitness;
	
	public CommonDobj getCommonDobj() {
		return commonDobj;
	}
	public void setCommonDobj(CommonDobj commonDobj) {
		this.commonDobj = commonDobj;
	}
	public FitnessValidityDobj getFitnessValidityDobj() {
		return fitnessValidityDobj;
	}
	public void setFitnessValidityDobj(FitnessValidityDobj fitnessValidityDobj) {
		this.fitnessValidityDobj = fitnessValidityDobj;
	}
	public String getVltdCheck() {
		return vltdCheck;
	}
	public void setVltdCheck(String vltdCheck) {
		this.vltdCheck = vltdCheck;
	}
	public boolean isDocUpload() {
		return docUpload;
	}
	public void setDocUpload(boolean docUpload) {
		this.docUpload = docUpload;
	}
	public boolean isNewVehFitness() {
		return newVehFitness;
	}
	public void setNewVehFitness(boolean newVehFitness) {
		this.newVehFitness = newVehFitness;
	}
	
	
	
}
