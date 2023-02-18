package bean.model;

public class PhaseModel {

	private int phaseId;
	private String phaseName;
	private int projectId;
	
	
	public PhaseModel() {
	}
	public PhaseModel(int phaseId, String phaseName, int projectId) {
		this.phaseId = phaseId;
		this.phaseName = phaseName;
		this.projectId = projectId;
	}
	
	
	public int getPhaseId() {
		return phaseId;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public int getProjectId() {
		return projectId;
	}
	
	
	public void setPhaseId(int phaseId) {
		this.phaseId = phaseId;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
}
