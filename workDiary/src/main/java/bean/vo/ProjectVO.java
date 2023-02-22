package bean.vo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ProjectVO {

	@SerializedName("project_id")
	private int projectId;
	@SerializedName("project_name")
	private String projectName;
	@SerializedName("phase_vos")
	private List<PhaseVO> phaseVOs;
	
	
	
	public ProjectVO() {
	}
	public ProjectVO(int projectId, String projectName, List<PhaseVO> phaseVOs) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.phaseVOs = phaseVOs;
	}
	
	
	public int getProjectId() {
		return projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public List<PhaseVO> getPhaseVOs() {
		return phaseVOs;
	}
	
	
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public void setPhaseVOs(List<PhaseVO> phaseVOs) {
		this.phaseVOs = phaseVOs;
	}

}
