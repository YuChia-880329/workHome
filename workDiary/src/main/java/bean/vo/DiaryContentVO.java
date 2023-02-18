package bean.vo;

import org.apache.struts.action.ActionForm;

public class DiaryContentVO extends ActionForm {

	private int projectId;
	private int phaseId;
	private int workId;
	private double workHour;
	
	
	public DiaryContentVO() {
	}
	public DiaryContentVO(int projectId, int phaseId, int workId, double workHour) {
		this.projectId = projectId;
		this.phaseId = phaseId;
		this.workId = workId;
		this.workHour = workHour;
	}
	
	
	public int getProjectId() {
		return projectId;
	}
	public int getPhaseId() {
		return phaseId;
	}
	public int getWorkId() {
		return workId;
	}
	public double getWorkHour() {
		return workHour;
	}
	
	
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public void setPhaseId(int phaseId) {
		this.phaseId = phaseId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public void setWorkHour(double workHour) {
		this.workHour = workHour;
	}
}
