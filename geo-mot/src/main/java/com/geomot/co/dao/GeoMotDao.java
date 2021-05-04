/**
 * 
 */
package com.geomot.co.dao;

import java.util.List;

import com.geomot.co.model.dto.DataMotoDTO;
import com.geomot.co.model.dto.MotoResponse;
import com.geomot.co.model.dto.ResponseMotoDTO;
import com.geomot.co.model.dto.StatusResponse;
import com.geomot.co.model.entity.DataMoto;

/**
 * @author Andres Capera
 *
 */
public interface GeoMotDao {
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DataMoto> getAllMoto() throws Exception;

	/**
	 * 
	 * @param placa
	 * @return
	 * @throws Exception
	 */
	public ResponseMotoDTO getAllMotoPlaca(String placa) throws Exception;

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public StatusResponse insertMoto(DataMotoDTO dto) throws Exception;
	
	/**
	 * 
	 * @param moto
	 * @return
	 * @throws Exception
	 */
	public StatusResponse updatetMoto(DataMoto moto) throws Exception ;

	/**
	 * 
	 * @param placa
	 * @return
	 * @throws Exception
	 */
	public MotoResponse getMoto(String placa) throws Exception;
}
