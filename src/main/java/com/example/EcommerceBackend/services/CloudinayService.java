package com.example.EcommerceBackend.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinayService {

    private final Cloudinary cloudinary;

    private String getResourceType(String contentType) {
        if (contentType == null) return "raw";

        if (contentType.startsWith("image")) return "image";
        if (contentType.startsWith("audio") || contentType.startsWith("video")) return "video";

        // PDF, ZIP y otros documentos → raw
        return "raw";
    }

        public String uploadImgeString(MultipartFile file) throws IOException { 
        String resourceType = getResourceType(file.getContentType()); 
        Map uploadResult = cloudinary.uploader().upload( 
            file.getBytes(), 
            ObjectUtils.asMap("resource_type", resourceType) 
            ); 
        return uploadResult.get("secure_url").toString(); 
    }
}