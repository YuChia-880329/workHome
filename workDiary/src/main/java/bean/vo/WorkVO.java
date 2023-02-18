package bean.vo;

import com.google.gson.annotations.SerializedName;

public class WorkVO {

	@SerializedName("work_id")
	private int workId;
	@SerializedName("work_name")
	private String workName;
	

	public WorkVO() {
	}
	public WorkVO(int workId, String workName) {
		this.workId = workId;
		this.workName = workName;
	}
	
	
	public int getWorkId() {
		return workId;
	}
	public String getWorkName() {
		return workName;
	}
	
	
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
}
