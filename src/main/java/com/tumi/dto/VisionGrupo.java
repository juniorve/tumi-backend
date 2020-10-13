package com.tumi.dto;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Id;

@Entity
@NamedStoredProcedureQuery(name = "vision.registrargrupo", 
procedureName = "sp_registrar_grupo", parameters = {
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "pnombInstitucion", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "ptipoInstitucion", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "psectorEconomico", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "pcategoria", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "platitud", type = BigDecimal.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "plongitud", type = BigDecimal.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "pvision", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "pconcepto", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "puuid", type = String.class)
  })
public class VisionGrupo {
	
	@Id
	private String nombInstitucion;
	private String tipoInstitucion;
	private String sectorEconomico;
	private String categoria;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private String vision;
	private String concepto;
    @Lob
    private byte[] adjunto;
	
    public VisionGrupo() {
		super();
	}



	public VisionGrupo(String nombInstitucion, String tipoInstitucion, String sectorEconomico, String categoria,
			BigDecimal latitud, BigDecimal longitud, String vision, String concepto, byte[] adjunto) {
		super();
		this.nombInstitucion = nombInstitucion;
		this.tipoInstitucion = tipoInstitucion;
		this.sectorEconomico = sectorEconomico;
		this.categoria = categoria;
		this.latitud = latitud;
		this.longitud = longitud;
		this.vision = vision;
		this.concepto = concepto;
		this.adjunto = adjunto;
	}

	public String getNombInstitucion() {
		return nombInstitucion;
	}

	public void setNombInstitucion(String nombInstitucion) {
		this.nombInstitucion = nombInstitucion;
	}

	public String getTipoInstitucion() {
		return tipoInstitucion;
	}

	public void setTipoInstitucion(String tipoInstitucion) {
		this.tipoInstitucion = tipoInstitucion;
	}

	public String getSectorEconomico() {
		return sectorEconomico;
	}

	public void setSectorEconomico(String sectorEconomico) {
		this.sectorEconomico = sectorEconomico;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getLatitud() {
		return latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public String getVision() {
		return vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}



	public byte[] getAdjunto() {
		return adjunto;
	}



	public void setAdjunto(byte[] adjunto) {
		this.adjunto = adjunto;
	}



	@Override
	public String toString() {
		return "VisionGrupo [nombInstitucion=" + nombInstitucion + ", tipoInstitucion=" + tipoInstitucion
				+ ", sectorEconomico=" + sectorEconomico + ", categoria=" + categoria + ", latitud=" + latitud
				+ ", longitud=" + longitud + ", vision=" + vision + ", concepto=" + concepto + ", adjunto="
				+ Arrays.toString(adjunto) + "]";
	}



    
}
