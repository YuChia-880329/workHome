package bean.dto;

import java.util.Date;

import enumeration.DiaryContentStatus;

public class DiaryContentDTO {

	private int id;
	private Date date;
	private int projectId;
	private int phaseId;
	private int workId;
	private String text;
	private double workHour;
	private DiaryContentStatus status;
	
	public DiaryContentDTO() {
	}
	public DiaryContentDTO(int id, Date date, int projectId, int phaseId, int workId, String text, double workHour,
			DiaryContentStatus status) {
		this.id = id;
		this.date = date;
		this.projectId = projectId;
		this.phaseId = phaseId;
		this.workId = workId;
		this.text = text;
		this.workHour = workHour;
		this.status = status;
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
	public DiaryContentStatus getStatus() {
		return status;
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
	public void setStatus(DiaryContentStatus status) {
		this.status = status;
	}
}
