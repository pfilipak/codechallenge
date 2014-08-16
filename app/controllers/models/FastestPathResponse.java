package controllers.models;

public class FastestPathResponse {

	private Double time;
	private String from;
	private String to;

	public FastestPathResponse(double time, String from, String to) {
		this.time = time;
		this.from = from;
		this.to = to;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	
	
}
