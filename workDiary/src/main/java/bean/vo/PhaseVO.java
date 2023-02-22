package bean.vo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PhaseVO {

	@SerializedName("phase_id")
	private int phaseId;
	@SerializedName("phase_name")
	private String phaseName;
	@SerializedName("work_vos")
	private List<WorkVO> workVOs;
	
	
	public PhaseVO() {
	}
	public PhaseVO(int phaseId, String phaseName, List<WorkVO> workVOs) {
		this.phaseId = phaseId;
		this.phaseName = phaseName;
		this.workVOs = workVOs;
	}
	
	
	public int getPhaseId() {
		return phaseId;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public List<WorkVO> getWorkVOs() {
		return workVOs;
	}
	
	
	public void setPhaseId(int phaseId) {
		this.phaseId = phaseId;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public void setWorkVOs(List<WorkVO> workVOs) {
		this.workVOs = workVOs;
	}
}
