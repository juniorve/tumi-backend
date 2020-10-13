package com.tumi.service;

import com.tumi.dto.VisionPersonaQuec;

public interface IVisionPersonaQuecService {
	public String registrarParticipacionPerQuec(VisionPersonaQuec visionPersonaQuec);
	public void tareaRegistrarAPI();
	public void tareaRegistrarAPImanual(String cadena, String key);
}
