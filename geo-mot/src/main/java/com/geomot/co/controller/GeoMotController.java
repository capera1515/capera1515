package com.geomot.co.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.geomot.co.model.dto.DataMotoDTO;
import com.geomot.co.model.dto.RequestMoto;
import com.geomot.co.model.dto.ResponseData;
import com.geomot.co.model.dto.StatusResponse;
import com.geomot.co.model.entity.DataMoto;
import com.geomot.co.service.GeoMotService;
import com.geomot.co.validation.ResponseCodes;

@CrossOrigin
@RestController
@RequestMapping(value = "v1/")
public class GeoMotController {

	@Autowired
	GeoMotService service;

	@RequestMapping(value = "coordinates-all", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public List<DataMoto> getAllMoto() throws Exception {
		return service.getAllMoto();
	}

	@RequestMapping(value = "coordinates-info", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<ResponseData> getAllMotoPlaca(@RequestBody RequestMoto dto) throws Exception {
		ResponseEntity<ResponseData> responseData = null;
		if (dto != null && dto.getPlaca() != null && dto.getPlaca() != "" && !dto.getPlaca().isEmpty()) {
			responseData = service.getAllMotoPlaca(dto.getPlaca());
			return responseData;
		}else {
			ResponseData res = new ResponseData();
			res.setStatusResponse(ResponseCodes.UNPROCESSABLE_ENTITY);
			return new ResponseEntity<ResponseData>(res, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "coordinates", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<StatusResponse> getResponseDocuments(@RequestBody DataMotoDTO dto) throws Exception {
		ResponseEntity<StatusResponse> response;
		try {
			response = service.insertMoto(dto);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return response;
	}

}
