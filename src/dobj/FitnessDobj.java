package dobj;

public class FitnessDobj {
	
	CommonDobj commonDobj;
	FitnessValidityDobj fitnessValidityDobj;
	private String vltdCheck;
	private boolean docUpload;
	private boolean newVehFitness;
	private boolean fitnessRevokeAllowed;
	private int fitnessRevokeAllowedDays;
	private int graceDays;
	private boolean fcAfterHsrp;
	
	
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
	public boolean isFitnessRevokeAllowed() {
		return fitnessRevokeAllowed;
	}
	public void setFitnessRevokeAllowed(boolean fitnessRevokeAllowed) {
		this.fitnessRevokeAllowed = fitnessRevokeAllowed;
	}
	public int getFitnessRevokeAllowedDays() {
		return fitnessRevokeAllowedDays;
	}
	public void setFitnessRevokeAllowedDays(int fitnessRevokeAllowedDays) {
		this.fitnessRevokeAllowedDays = fitnessRevokeAllowedDays;
	}
	public int getGraceDays() {
		return graceDays;
	}
	public void setGraceDays(int graceDays) {
		this.graceDays = graceDays;
	}
	public boolean isFcAfterHsrp() {
		return fcAfterHsrp;
	}
	public void setFcAfterHsrp(boolean fcAfterHsrp) {
		this.fcAfterHsrp = fcAfterHsrp;
	}
	
	
	
	
}
