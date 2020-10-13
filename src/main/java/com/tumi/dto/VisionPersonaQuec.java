package com.tumi.dto;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

@Entity
@NamedStoredProcedureQuery(name = "vision.registrarpersona", 
procedureName = "sp_registrar_personaQuec", parameters = {
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "pgrupoEdad", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "psexo", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "pidioma", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "pdepartamento", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "pprovincia", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "pdistrito", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "platitud", type = BigDecimal.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "plongitud", type = BigDecimal.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "pfile_vision", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "pfile_concepto", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "pcategoria", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "puuid", type = String.class)
  })
public class VisionPersonaQuec {

	@Id
	private String grupoEdad;
	private String sexo;
	private String idioma;
	private String departamento;
	private String provincia;
	private String distrito;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private String vision;
	private String concepto;
	private String categoria;

	public VisionPersonaQuec() {
		super();
	}

	public VisionPersonaQuec(String grupoEdad, String sexo, String idioma, String departamento, String provincia,
		String distrito, BigDecimal latitud, BigDecimal longitud, String vision, String concepto,
		String categoria) {
	super();
	this.grupoEdad = grupoEdad;
	this.sexo = sexo;
	this.idioma = idioma;
	this.departamento = departamento;
	this.provincia = provincia;
	this.distrito = distrito;
	this.latitud = latitud;
	this.longitud = longitud;
	this.vision = vision;
	this.concepto = concepto;
	this.categoria = categoria;
}
	
	public String getGrupoEdad() {
		return grupoEdad;
	}	
	public void setGrupoEdad(String grupoEdad) {
		this.grupoEdad = grupoEdad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "VisionPersonaQuec [grupoEdad=" + grupoEdad + ", sexo=" + sexo + ", idioma=" + idioma + ", departamento="
				+ departamento + ", provincia=" + provincia + ", distrito=" + distrito + ", latitud=" + latitud
				+ ", longitud=" + longitud + ", vision=" + vision + ", concepto=" + concepto
				+ ", categoria=" + categoria + "]";
	}
	

}
