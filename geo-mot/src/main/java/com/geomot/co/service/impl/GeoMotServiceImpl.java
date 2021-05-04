/**
 * 
 */
package com.geomot.co.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.geomot.co.dao.GeoMotDao;
import com.geomot.co.model.dto.Cordenada;
import com.geomot.co.model.dto.DataMotoDTO;
import com.geomot.co.model.dto.DatosMoto;
import com.geomot.co.model.dto.MotoResponse;
import com.geomot.co.model.dto.ResponseData;
import com.geomot.co.model.dto.ResponseMotoDTO;
import com.geomot.co.model.dto.StatusResponse;
import com.geomot.co.model.entity.DataMoto;
import com.geomot.co.service.GeoMotService;
import com.geomot.co.validation.ResponseCodes;

/**
 * @author Andres Capera
 *
 */
@Service
@Transactional
public class GeoMotServiceImpl implements GeoMotService {
	@Autowired
	private GeoMotDao service;
	
	public static final int VL = 0;

	/**
	 * Metodo de consulta de personas
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DataMoto> getAllMoto() throws Exception {
		return service.getAllMoto();
	}

	/**
	 * 
	 * @param placa
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unlikely-arg-type")
	public ResponseEntity<ResponseData> getAllMotoPlaca(String placa) throws Exception {
		DataMotoDTO dataMotoDTO = new DataMotoDTO();
		Cordenada cordenada = new Cordenada();
		DatosMoto datosMoto = new DatosMoto();
		ResponseData responseData = new ResponseData();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			ResponseMotoDTO responseMotoDTO = service.getAllMotoPlaca(placa);
			responseData.setStatusResponse(responseMotoDTO.getStatusResponse());
			if (responseMotoDTO.getStatusResponse().equals(ResponseCodes.SUCCESS)) {
				Date date = formatter.parse(responseMotoDTO.getResponseDTO().getDate());
				Timestamp timeStampDate = new Timestamp(date.getTime());
				cordenada.setFecha(timeStampDate.getTime());
				cordenada.setLatitud(responseMotoDTO.getResponseDTO().getLatitud());
				cordenada.setLongitud(responseMotoDTO.getResponseDTO().getLongitud());
				dataMotoDTO.setCordenada(cordenada);
				datosMoto.setEstatus(responseMotoDTO.getResponseDTO().equals(GeoMotServiceImpl.VL) ? false : true);
				datosMoto.setPlaca(responseMotoDTO.getResponseDTO().getPlaca());
				dataMotoDTO.setDatosMoto(datosMoto);
				responseData.setDataMotoDTO(dataMotoDTO);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			responseData.setStatusResponse(ResponseCodes.TECHNICAL_ERROR);
			return new ResponseEntity<ResponseData>(responseData, HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData>(responseData, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseEntity<StatusResponse> insertMoto(DataMotoDTO dto) throws Exception {
		StatusResponse statusResponse = null;
		try {
			MotoResponse motoResponse = service.getMoto(dto.getDatosMoto().getPlaca());
			if (motoResponse.getStatusResponse().equals(ResponseCodes.SUCCESS)) {
				motoResponse.getDataMoto().setLatitud(dto.getCordenada().getLatitud());
				motoResponse.getDataMoto().setLongitud(dto.getCordenada().getLongitud());
				motoResponse.getDataMoto().setPlaca(dto.getDatosMoto().getPlaca());
				motoResponse.getDataMoto().setEstatus(dto.getDatosMoto().getEstatus() ? 1 : 0);
				statusResponse = service.updatetMoto(motoResponse.getDataMoto());
				return new ResponseEntity<StatusResponse>(statusResponse, HttpStatus.CREATED);
			} else if (motoResponse.getStatusResponse().equals(ResponseCodes.DATA_NOT_FOUND)) {
				return new ResponseEntity<StatusResponse>(service.insertMoto(dto), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<StatusResponse>(motoResponse.getStatusResponse(), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			statusResponse = ResponseCodes.DATABASE_EXCEPTION;
			System.out.println(e.getMessage().toString());
			return new ResponseEntity<StatusResponse>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
