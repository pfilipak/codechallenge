package controllers.models;

import java.util.Date;

import controllers.util.ResponseCode;

public class MetroResponse {

	private Integer responseCode;
	private String responseMessage;
	private Integer offset = 0;
	private Integer limit = 0;
	private Long timestamp = new Date().getTime();
	private Object data;

	public MetroResponse(Integer responseCode, String responseMessage, Object data) {
		this(responseCode, responseMessage);
		this.data = data;
	}

	public MetroResponse(int responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	
	public MetroResponse(int responseCode, String responseMessage, Integer offset, Integer limit) {
		this(responseCode, responseMessage);
		this.offset = offset;
		this.limit = limit;
	}

	public MetroResponse(ResponseCode responseCode, Object data) {
		this(responseCode.getCode(), responseCode.getMessage());
		this.data = data;
	}
	
	public MetroResponse(ResponseCode responseCode, Object data, Integer offset, Integer limit) {
		this(responseCode.getCode(), responseCode.getMessage(), data);
		this.offset = offset;
		this.limit = limit;
	}
	
	public MetroResponse(ResponseCode responseCode) {
		this(responseCode.getCode(), responseCode.getMessage());
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}
