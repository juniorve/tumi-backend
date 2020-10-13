package com.tumi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tumi.dao.IVisionGrupoDAO;
import com.tumi.dto.FileStorageProperties;
import com.tumi.dto.VisionGrupo;

@Service
public class VisionGrupoServiceImpl implements IVisionGrupoService {

	@Autowired
	IVisionGrupoDAO ivisionGrupoDAO;
	
	private final Path fileStorageLocation;
	
    @Autowired
    public VisionGrupoServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            //throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    
    public String registrarParticipacionGrupo(VisionGrupo visionGrupo) {
    	return ivisionGrupoDAO.registrarGrupo(
					visionGrupo.getNombInstitucion(), 
					visionGrupo.getTipoInstitucion(),
					visionGrupo.getSectorEconomico(), 
					visionGrupo.getCategoria(), 
					visionGrupo.getLatitud(), 
					visionGrupo.getLongitud(), 
					visionGrupo.getVision(), 
					visionGrupo.getConcepto());
    }    
    
    public void almacenarDescarga(MultipartFile file, String filename, String tipo) {

        String fileName = StringUtils.cleanPath(filename);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String fechacode = dateFormat.format(new Date());
        fechacode = fechacode.concat(fileName);
        fechacode = fechacode.concat(tipo);
        fileName = fechacode.concat(".wav");

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
            	// throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            //return fileName;
        } catch (IOException ex) {
           // throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    
    public void registrarAdjunto(MultipartFile file,String code) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
               // throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            ivisionGrupoDAO.registrarAdjunto(code, file.getBytes());

        } catch (IOException ex) {
           // throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    
}
