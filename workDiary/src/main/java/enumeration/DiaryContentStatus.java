package enumeration;

public enum DiaryContentStatus {

	NOT_SAVED("未送交", "1"), SAVED("已儲存", "2"), SENT("已送出", "3");
	
	private String discription;
	private String code;
	
	private DiaryContentStatus(String discription, String code) {
		
		this.discription = discription;
		this.code = code;
	}
	
	public String getDiscription() {
		
		return discription;
	}
	public String getCode() {
		
		return code;
	}
	
	public static DiaryContentStatus getStatus(String statusStr) {
		
		int status = Integer.parseInt(statusStr);
		DiaryContentStatus[] statuses = DiaryContentStatus.values();
		if(status < statuses.length)
			return DiaryContentStatus.values()[status-1];
		
		return null;
	}
}
