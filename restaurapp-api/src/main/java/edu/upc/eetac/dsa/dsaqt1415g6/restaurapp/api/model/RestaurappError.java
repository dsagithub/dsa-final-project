package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model;

public class RestaurappError {
	private int status;
	private String message;

	public RestaurappError() {
		super();
	}

	public RestaurappError(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

