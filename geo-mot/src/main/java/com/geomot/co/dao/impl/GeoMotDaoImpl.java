/**
 * 
 */
package com.geomot.co.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.geomot.co.dao.GeoMotDao;
import com.geomot.co.model.dto.DataMotoDTO;
import com.geomot.co.model.dto.MotoResponse;
import com.geomot.co.model.dto.ResponseDTO;
import com.geomot.co.model.dto.ResponseMotoDTO;
import com.geomot.co.model.dto.StatusResponse;
import com.geomot.co.model.entity.DataMoto;
import com.geomot.co.validation.ResponseCodes;

/**
 * @author Andres Capera
 *
 */
@Repository
public class GeoMotDaoImpl implements GeoMotDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * Metodo de consulta de personas
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DataMoto> getAllMoto() throws Exception {
		List<DataMoto> personas = new ArrayList<>();
		try {
			personas = getSession().createQuery("from DataMoto").list();
		} catch (Exception e) {
			throw new Exception();
		}
		return personas;
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public ResponseMotoDTO getAllMotoPlaca(String placa) throws Exception {
		ResponseMotoDTO responseMotoDTO = new ResponseMotoDTO();
		ResponseDTO lista = new ResponseDTO();
		String hql = "SELECT  \r\n"
				+ "MOT_ID AS id,  \r\n"
				+ "MOT_LONGITUD AS longitud, \r\n"
				+ "MOT_LATIDUD AS latitud,  \r\n"
				+ "MOT_DATE AS date,  \r\n"
				+ "MOT_PLACA  AS placa,  \r\n"
				+ "MOT_ESTATUS  AS estatus  \r\n"
				+ "FROM geomot.MOTO  \r\n"
				+ "WHERE MOT_PLACA = :PLACA ";
		try {
			Query query = getSession().createSQLQuery(hql)
					.addScalar("id", new IntegerType())
					.addScalar("longitud", new StringType())
					.addScalar("latitud", new StringType())
					.addScalar("date", new StringType())
					.addScalar("placa", new StringType())
					.addScalar("estatus", new IntegerType());
			query.setParameter("PLACA", placa);
			lista = (ResponseDTO) query.setResultTransformer(Transformers.aliasToBean(ResponseDTO.class))
					.uniqueResult();
			responseMotoDTO.setResponseDTO(lista);
			responseMotoDTO.setStatusResponse(lista != null ? ResponseCodes.SUCCESS : ResponseCodes.DATA_NOT_FOUND);
		} catch (Exception e) {
			responseMotoDTO.setStatusResponse(ResponseCodes.TECHNICAL_ERROR);
			throw new Exception(e.getMessage());
		}
		return responseMotoDTO;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public StatusResponse insertMoto(DataMotoDTO dto) throws Exception {
		StatusResponse statusResponse = null;
		DataMoto moto = new DataMoto();
		try {
			moto.setLatitud(dto.getCordenada().getLatitud());
			moto.setLongitud(dto.getCordenada().getLongitud());
			moto.setDate(new Date(dto.getCordenada().getFecha()));
			moto.setPlaca(dto.getDatosMoto().getPlaca());
			moto.setEstatus(dto.getDatosMoto().getEstatus() ? 1 : 0);
			getSession().save(moto);
			statusResponse = ResponseCodes.SUCCESS;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return statusResponse;
	}
	
	public StatusResponse updatetMoto(DataMoto moto) throws Exception {
		StatusResponse statusResponse = null;
		try {
			getSession().update(moto);
			statusResponse = ResponseCodes.SUCCESS;
		} catch (Exception e) {
			statusResponse = ResponseCodes.DATABASE_EXCEPTION;
			throw new Exception(e.getMessage());
		}
		return statusResponse;
	}
	
	public MotoResponse getMoto(String placa) throws Exception {
		MotoResponse motoResponse = new MotoResponse();
		DataMoto personas = new DataMoto();
		try {
			personas = (DataMoto) getSession().createQuery("from DataMoto WHERE placa = :pc").setParameter("pc", placa)
					.uniqueResult();
			motoResponse.setDataMoto(personas);
			motoResponse.setStatusResponse(personas != null ? ResponseCodes.SUCCESS : ResponseCodes.DATA_NOT_FOUND);
		} catch (Exception e) {
			throw new Exception();
		}
		return motoResponse;
	}
}
