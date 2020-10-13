package com.tumi.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tumi.dto.VisionGrupo;

public interface IVisionGrupoDAO extends JpaRepository<VisionGrupo,String> {
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_registrarAdjunto(:pcode,:padjunto)}", nativeQuery = true)	
	public void registrarAdjunto(
			@Param("pcode") String pcode,
			@Param("padjunto") byte[] padjunto
			);

    @Procedure(name = "vision.registrargrupo")
    String registrarGrupo(
    		@Param("pnombInstitucion") String pnombInstitucion,
    		@Param("ptipoInstitucion") String ptipoInstitucion,
    		@Param("psectorEconomico") String psectorEconomico,
    		@Param("pcategoria") String pcategoria,
    		@Param("platitud") BigDecimal platitud,
    		@Param("plongitud") BigDecimal plongitud,
    		@Param("pvision") String pvision,
    		@Param("pconcepto") String pconcepto
    		);
	
}
