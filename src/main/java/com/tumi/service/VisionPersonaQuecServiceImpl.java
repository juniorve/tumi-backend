package com.tumi.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tumi.dao.IVisionPersonaQuecDAO;
import com.tumi.dto.FileStorageProperties;
import com.tumi.dto.VisionPersonaQuec;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;

@Service
public class VisionPersonaQuecServiceImpl implements IVisionPersonaQuecService {
	
	@Autowired
	IVisionPersonaQuecDAO iVisionPersonaQuecDAO;

	private final Path fileStorageLocation;
	private final String clave;
	
    @Autowired
    public VisionPersonaQuecServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.clave = fileStorageProperties.getKeyBatch();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            //throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }	
	
	@Override
	public String registrarParticipacionPerQuec(VisionPersonaQuec visionPersonaQuec) {
				
		return iVisionPersonaQuecDAO.registrarParticipacionPersona(
				visionPersonaQuec.getGrupoEdad(),
				visionPersonaQuec.getSexo(),
				visionPersonaQuec.getIdioma(),
				visionPersonaQuec.getDepartamento(),
				visionPersonaQuec.getProvincia(),
				visionPersonaQuec.getDistrito(),
				visionPersonaQuec.getLatitud(),
				visionPersonaQuec.getLongitud(),
				visionPersonaQuec.getVision(),
				visionPersonaQuec.getConcepto(),
				visionPersonaQuec.getCategoria()
				);
	}
	
    public void tareaRegistrarAPI() { 	
        final SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String fechaayer = dateFormat.format(new Date().getTime()-86400000);   	
    	
        String codigo , fecha, enlaceCode,transcripcion;

        File f = new File(this.fileStorageLocation.toString());
        
        if (f.exists()){ 
        	File[] ficheros = f.listFiles();
        	
        	for (int x=0; x < ficheros.length;x++) {
        		
        		codigo = ficheros[x].getName();
        		fecha = codigo.substring(0,8);
        		if(fechaayer.equals(fecha)) {
        			enlaceCode = codigo.substring(8, 16);
        			transcripcion = transcribir(ficheros[x]);
        			iVisionPersonaQuecDAO.registrarTranscripcion(enlaceCode, transcripcion);
        		}
        	}  
        }
    }
    
    public void tareaRegistrarAPImanual(String cadena, String key) { 	

    	String codigo , codigov, codigoc, enlaceCode, transcripcion;
    	if (key.equals(clave)) {
            File f = new File(this.fileStorageLocation.toString());
            if (f.exists()){ 
            	File[] ficheros = f.listFiles();	
				List<String> codeList = Arrays.asList(cadena.split(","));
				for (int i=0;i<codeList.size();i++ ) {
					codigov = codeList.get(i).concat("v");
					codigoc = codeList.get(i).concat("c");
		        	for (int x=0; x < ficheros.length;x++) {
		        		codigo = ficheros[x].getName();
		        		enlaceCode = codigo.substring(8, 16);
		        		if(enlaceCode.equals(codigov) || enlaceCode.equals(codigoc)) {	
		        			transcripcion = transcribir(ficheros[x]);
		        			iVisionPersonaQuecDAO.registrarTranscripcion(enlaceCode, transcripcion);
		        		}
		        	}  
				}
            }
    	}  
    }    
	
	public String transcribir(File fileToUpload) {

        final String API_URI = "http://18.218.29.107:5000/upload";

        final ClientConfig config = new DefaultClientConfig();
        final Client client = Client.create(config);
        final WebResource resource = client.resource(API_URI);

        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("files",
                fileToUpload,
                MediaType.APPLICATION_OCTET_STREAM_TYPE);
        fileDataBodyPart.setContentDisposition(
                FormDataContentDisposition.name("files")
                        .fileName(fileToUpload.getName()).build());

        final MultiPart multiPart = new FormDataMultiPart().bodyPart(fileDataBodyPart);
        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
        
        ClientResponse response = resource
                .type("multipart/form-data").post(ClientResponse.class,
                        multiPart);
        final String result = getResultado(response.getEntityInputStream());
        client.destroy();	
        return result;
	}

    private String getResultado(InputStream is) {
    	String resultado="";
    	BufferedReader bR = null;
		try {
			bR = new BufferedReader(  new InputStreamReader(is));
			String line = "";

			StringBuilder responseStrBuilder = new StringBuilder();
			while((line =  bR.readLine()) != null){

			    responseStrBuilder.append(line);
			}
			is.close();

			JSONObject result= new JSONObject(responseStrBuilder.toString());    
			resultado = result.getString("text_source");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		finally {
            if (bR != null) {
                try {
                	bR.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	    return resultado;
    }
	
}
