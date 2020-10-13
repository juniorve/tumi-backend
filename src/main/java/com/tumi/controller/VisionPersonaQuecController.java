package com.tumi.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tumi.dto.VisionPersonaQuec;
import com.tumi.service.VisionPersonaQuecServiceImpl;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/vpersonaquec")
public class VisionPersonaQuecController {

	@Autowired
	VisionPersonaQuecServiceImpl visionPersonaQuecServiceImpl;
	
	@PostMapping("/registrar")
	public String registrarParticipacion(@RequestBody VisionPersonaQuec visionPersonaQuec) {
		return visionPersonaQuecServiceImpl.registrarParticipacionPerQuec(visionPersonaQuec);
	}
	
    @PostMapping("/transcribirBatch")
    public String transcribirBatch(@RequestParam("cadena") String cadena,
    							   @RequestParam("key") String key,
    								HttpServletResponse response){
    	visionPersonaQuecServiceImpl.tareaRegistrarAPImanual(cadena,key);
        response.setStatus(HttpServletResponse.SC_OK);
        return "OK";
    }
	
}
