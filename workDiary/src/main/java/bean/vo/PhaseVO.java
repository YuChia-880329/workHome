package bean.vo;

import com.google.gson.annotations.SerializedName;

public class PhaseVO {

	@SerializedName("phase_id")
	private int phaseId;
	@SerializedName("phase_name")
	private String phaseName;
	
	
	
	public PhaseVO() {
	}
	public PhaseVO(int phaseId, String phaseName) {
		this.phaseId = phaseId;
		this.phaseName = phaseName;
	}
	
	
	public int getPhaseId() {
		return phaseId;
	}
	public String getPhaseName() {
		return phaseName;
	}
	
	
	public void setPhaseId(int phaseId) {
		this.phaseId = phaseId;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
}
