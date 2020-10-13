package com.tumi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tumi.dto.ResultadoList;
import com.tumi.service.UtilServiceImpl;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/util")
public class UtilController {

	@Autowired
	UtilServiceImpl utilServiceImpl;
	
	@GetMapping("/getListaDepartamentos")
	public List<ResultadoList> getListaDepartamentos(){
		return utilServiceImpl.getListaDepartamentos();
	}	
	
	@GetMapping("/getListaProvincias")
	public List<ResultadoList> getListaProvincias(@RequestParam(required = true) String pdepartamento){
		return utilServiceImpl.getListaProvincias(pdepartamento);
	}		
	
	@GetMapping("/getListaDistritos")
	public List<ResultadoList> getListaDistritos(@RequestParam(required = true) String pprovincia,@RequestParam(required = true) String pdepartamento){
		return utilServiceImpl.getListaDistritos(pprovincia,pdepartamento);
	}
	
	@GetMapping("/getListaGrupoEdad")
	public List<ResultadoList> getListaGrupoEdad(){
		return utilServiceImpl.getListaGrupoEdad();
	}
	
	@GetMapping("/getListaTipoGrupo")
	public List<ResultadoList> getListaTipoGrupo(){
		return utilServiceImpl.getListaTipoGrupo();
	}
	
	@GetMapping("/getListaSector")
	public List<ResultadoList> getListaSector(){
		return utilServiceImpl.getListaSector();
	}
	
	@GetMapping("/getListaCategoria")
	public List<ResultadoList> getListaCategoria(){
		return utilServiceImpl.getListaCategoria();
	}	
	
}
