package com.geomot.util;

import com.geomot.co.model.dto.StatusResponse;

public class ResponseCodes {
	public static final StatusResponse SUCCESS = new StatusResponse("200", "Success");
	public static final StatusResponse TECHNICAL_ERROR = new StatusResponse("400", "Technical Error");
	public static final StatusResponse UNPROCESSABLE_ENTITY = new StatusResponse("422", "Unprocessable Entity");
	public static final StatusResponse NOT_FOUND = new StatusResponse("404", "Not Found");
	public static final StatusResponse DATA_NOT_FOUND = new StatusResponse("204", "Data Not Found");
	public static final StatusResponse TIMEOUT_EXCEPTION = new StatusResponse("408", "ConnectionTimeout");
	public static final StatusResponse DATABASE_EXCEPTION = new StatusResponse("430", "Database Exception");

	private ResponseCodes() {
		super();
	}
}
