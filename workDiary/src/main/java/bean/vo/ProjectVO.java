package bean.vo;

import com.google.gson.annotations.SerializedName;

public class ProjectVO {

	@SerializedName("project_id")
	private int projectId;
	@SerializedName("project_name")
	private String projectName;
	
	
	
	public ProjectVO() {
	}
	public ProjectVO(int projectId, String projectName) {
		this.projectId = projectId;
		this.projectName = projectName;
	}
	
	
	public int getProjectId() {
		return projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	
	
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
