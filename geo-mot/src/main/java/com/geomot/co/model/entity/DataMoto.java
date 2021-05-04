/**
 * 
 */
package com.geomot.co.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * @author Andres Capera
 *
 */
@Data
@Entity
@Table(name = "MOTO")
public class DataMoto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MOT_ID")
	private Integer id;

	@Column(name = "MOT_LONGITUD")
	private String longitud;

	@Column(name = "MOT_LATIDUD")
	private String latitud;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MOT_DATE")
	private Date date;

	@Column(name = "MOT_PLACA")
	private String placa;

	@Column(name = "MOT_ESTATUS")
	private Integer estatus;

	public DataMoto() {
	}
}
