package bean.vo;

public class DiaryContentVO{

	private int count;
	private String date;
	private int projectId;
	private int phaseId;
	private int workId;
	private String text;
	private double workHour;
	
	
	public DiaryContentVO() {
	}
	public DiaryContentVO(int count, String date, int projectId, int phaseId, int workId, String text, double workHour) {
		this.count = count;
		this.date = date;
		this.projectId = projectId;
		this.phaseId = phaseId;
		this.workId = workId;
		this.text = text;
		this.workHour = workHour;
	}
	
	public int getCount() {
		return count;
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
	public String getText() {
		return text;
	}
	public double getWorkHour() {
		return workHour;
	}
	
	
	public void setCount(int count) {
		this.count = count;
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
	public void setText(String text) {
		this.text = text;
	}
	public void setWorkHour(double workHour) {
		this.workHour = workHour;
	}
}
