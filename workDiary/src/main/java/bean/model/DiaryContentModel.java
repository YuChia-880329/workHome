package bean.model;

import java.sql.Date;

public class DiaryContentModel {

	private int id;
	private Date date;
	private int projectId;
	private int phaseId;
	private int workId;
	private String text;
	private double workHour;
	
	
	public DiaryContentModel() {
	}
	public DiaryContentModel(int id, Date date, int projectId, int phaseId, int workId, String text, double workHour) {
		this.id = id;
		this.date = date;
		this.projectId = projectId;
		this.phaseId = phaseId;
		this.workId = workId;
		this.text = text;
		this.workHour = workHour;
	}
	
	
	public int getId() {
		return id;
	}
	public Date getDate() {
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
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setDate(Date date) {
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
