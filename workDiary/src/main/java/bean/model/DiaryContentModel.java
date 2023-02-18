package bean.model;

public class DiaryContentModel {

	private int id;
	private int employeeId;
	private String date;
	private int projectId;
	private int phaseId;
	private int workId;
	private double workHour;
	private int status;
	
	
	public DiaryContentModel() {
	}
	public DiaryContentModel(int id, int employeeId, String date, int projectId, int phaseId, int workId,
			double workHour, int status) {
		this.id = id;
		this.employeeId = employeeId;
		this.date = date;
		this.projectId = projectId;
		this.phaseId = phaseId;
		this.workId = workId;
		this.workHour = workHour;
		this.status = status;
	}
	
	
	public int getId() {
		return id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public String getDate() {
		return date;
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
	public int getStatus() {
		return status;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public void setDate(String date) {
		this.date = date;
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
	public void setStatus(int status) {
		this.status = status;
	}
}
