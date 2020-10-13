package com.tumi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tumi.dao.IUtilDAO;
import com.tumi.dto.ResultadoList;

@Service
public class UtilServiceImpl implements IUtilService {

	@Autowired
	IUtilDAO iutilDAO;
	
	@Override
	public List<ResultadoList> getListaDepartamentos() {
		return iutilDAO.getListaDepartamentos() ;
	}

	public List<ResultadoList> getListaProvincias(String pdepartamento) {
		return iutilDAO.getListaProvincias(pdepartamento);
	}

	@Override
	public List<ResultadoList> getListaDistritos(String pprovincia, String pdepartamento) {
		return iutilDAO.getListaDistritos(pprovincia, pdepartamento) ;
	}

	@Override
	public List<ResultadoList> getListaGrupoEdad() {
		return iutilDAO.getListaGrupoEdad();
	}

	@Override
	public List<ResultadoList> getListaTipoGrupo() {
		return iutilDAO.getListaTipoGrupo();
	}

	@Override
	public List<ResultadoList> getListaSector() {
		return iutilDAO.getListaSector();
	}

	@Override
	public List<ResultadoList> getListaCategoria() {
		return iutilDAO.getListaCategoria();
	}

}
