package controllers.util;

public enum ResponseCode {

	APPROVED(0,"Approved"),
	GENERIC(10, "Generic");
	
	private Integer code;
	private String message;

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	ResponseCode(Integer code, String message){
		this.code = code;
		this.message = message;
	}
}
