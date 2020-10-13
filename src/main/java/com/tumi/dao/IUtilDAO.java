package com.tumi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tumi.dto.ResultadoList;

public interface IUtilDAO extends JpaRepository<ResultadoList, String> {
	
	@Query(value = "{call sp_listar_departamentos()}", nativeQuery = true)
	public List<ResultadoList> getListaDepartamentos();
	
	@Query(value = "{call sp_listar_provincias(:pdepartamento)}", nativeQuery = true)
	public List<ResultadoList> getListaProvincias(@Param("pdepartamento") String pdepartamento);
	
	@Query(value = "{call sp_listar_distritos(:pprovincia,:pdepartamento)}", nativeQuery = true)
	public List<ResultadoList> getListaDistritos(@Param("pprovincia") String pprovincia,@Param("pdepartamento") String pdepartamento);
	
	@Query(value = "{call sp_listar_grupoedad()}", nativeQuery = true)
	public List<ResultadoList> getListaGrupoEdad();	
	
	@Query(value = "{call sp_listar_tipogrupo()}", nativeQuery = true)
	public List<ResultadoList> getListaTipoGrupo();	
	
	@Query(value = "{call sp_listar_sector()}", nativeQuery = true)
	public List<ResultadoList> getListaSector();	
	
	@Query(value = "{call sp_listar_categoria()}", nativeQuery = true)
	public List<ResultadoList> getListaCategoria();		
	
}
