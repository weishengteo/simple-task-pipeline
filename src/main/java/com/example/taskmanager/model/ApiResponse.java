package com.example.taskmanager.model;

import com.example.taskmanager.util.Status;

public class ApiResponse<T> {

	private Integer code;
	private String message;
	private T data;
	
	public ApiResponse() {}
	
	public ApiResponse(Status status, T data) {
		this.code = status.getCode();
		this.message = status.getMessage();
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public static <T> ApiResponse<T> ok(T data) {
		return new ApiResponse<>(Status.SUCCESS, data);
	}
	
	public static <T> ApiResponse<T> error(Status status, String customMessage) {
		ApiResponse<T> res = new ApiResponse<>();
		res.setCode(status.getCode());
		res.setMessage(customMessage);
		res.setData(null);
		return res;
	}
}
