/**
 * 
 */
package com.geomot.co.model.dto;

import lombok.Data;

/**
 * @author andre
 *
 */
@Data
public class StatusResponse {
	private String status;
	private String description;

	public StatusResponse(String status, String description) {
		this.status = status;
		this.description = description;
	}
}
