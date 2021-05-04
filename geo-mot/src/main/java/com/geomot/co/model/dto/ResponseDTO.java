/**
 * 
 */
package com.geomot.co.model.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Andres Capera
 *
 */
@Data
public class ResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String longitud;

	private String latitud;

	private String date;

	private String placa;

	private Integer estatus;
}
