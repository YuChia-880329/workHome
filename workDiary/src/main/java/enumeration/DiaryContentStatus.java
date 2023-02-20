package enumeration;

public enum DiaryContentStatus {

	NOT_SAVED("未送交", 1), SAVED("已儲存", 2), SENT("已送出", 3);
	
	private String discription;
	private int code;
	
	private DiaryContentStatus(String discription, int code) {
		
		this.discription = discription;
		this.code = code;
	}
	
	public String getDiscription() {
		
		return discription;
	}
	public int getCode() {
		
		return code;
	}
}
