package bean.model;

public class WorkModel {

	private int workId;
	private String workName;
	private int phaseId;
	
	
	public WorkModel() {
	}
	public WorkModel(int workId, String workName, int phaseId) {
		this.workId = workId;
		this.workName = workName;
		this.phaseId = phaseId;
	}
	
	
	public int getWorkId() {
		return workId;
	}
	public String getWorkName() {
		return workName;
	}
	public int getPhaseId() {
		return phaseId;
	}
	
	
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public void setPhaseId(int phaseId) {
		this.phaseId = phaseId;
	}
	
}
