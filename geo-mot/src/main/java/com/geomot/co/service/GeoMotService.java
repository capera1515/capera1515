/**
 * 
 */
package com.geomot.co.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.geomot.co.model.dto.DataMotoDTO;
import com.geomot.co.model.dto.ResponseData;
import com.geomot.co.model.dto.StatusResponse;
import com.geomot.co.model.entity.DataMoto;

/**
 * @author Andres Capera
 *
 */
public interface GeoMotService {
	/**
	 * Metodo de consulta de personas
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DataMoto> getAllMoto() throws Exception;

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<StatusResponse> insertMoto(DataMotoDTO dto) throws Exception;

	/**
	 * 
	 * @param placa
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<ResponseData> getAllMotoPlaca(String placa) throws Exception;
}
