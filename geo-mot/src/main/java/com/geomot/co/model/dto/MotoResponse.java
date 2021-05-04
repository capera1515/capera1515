/**
 * 
 */
package com.geomot.co.model.dto;

import com.geomot.co.model.entity.DataMoto;

import lombok.Data;

/**
 * @author Andres Capera
 *
 */
@Data
public class MotoResponse {
	private DataMoto dataMoto;
	private StatusResponse statusResponse;
}
