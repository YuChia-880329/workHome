package bean.model;

public class ProjectModel {

	private int projectId;
	private String projectName;
	
	
	public ProjectModel() {
	}
	public ProjectModel(int projectId, String projectName) {
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
