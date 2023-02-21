package bean.vo;

import com.google.gson.annotations.SerializedName;

public class DiaryContentVO{

	@SerializedName("count")
	private String count;
	@SerializedName("date")
	private String date;
	@SerializedName("projectId")
	private String projectId;
	@SerializedName("phaseId")
	private String phaseId;
	@SerializedName("workId")
	private String workId;
	@SerializedName("text")
	private String text;
	@SerializedName("workHour")
	private String workHour;
	@SerializedName("status")
	private String status;
	
	
	public DiaryContentVO() {
	}
	public DiaryContentVO(String count, String date, String projectId, String phaseId, String workId, String text,
			String workHour, String status) {
		this.count = count;
		this.date = date;
		this.projectId = projectId;
		this.phaseId = phaseId;
		this.workId = workId;
		this.text = text;
		this.workHour = workHour;
		this.status = status;
	}
	
	
	public String getCount() {
		return count;
	}
	public String getDate() {
		return date;
	}
	public String getProjectId() {
		return projectId;
	}
	public String getPhaseId() {
		return phaseId;
	}
	public String getWorkId() {
		return workId;
	}
	public String getText() {
		return text;
	}
	public String getWorkHour() {
		return workHour;
	}
	public String getStatus() {
		return status;
	}
	
	
	public void setCount(String count) {
		this.count = count;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setWorkHour(String workHour) {
		this.workHour = workHour;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
