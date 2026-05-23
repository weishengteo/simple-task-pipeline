package com.example.taskmanager.util;

public enum Status {

	SUCCESS(200, "Success"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    ERROR(500, "Internal Server Error");

	private final Integer code;
	private final String message;
	
	private Status(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
