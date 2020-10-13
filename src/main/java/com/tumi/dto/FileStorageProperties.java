package com.tumi.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
    private String keyBatch;
    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

	public String getKeyBatch() {
		return keyBatch;
	}

	public void setKeyBatch(String keyBatch) {
		this.keyBatch = keyBatch;
	}


}