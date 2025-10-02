package com.example.EcommerceBackend.controllers.files;



import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.EcommerceBackend.services.CloudinayService;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final CloudinayService fileService;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
        String uploadedFileResponse = fileService.uploadImgeString(file);
        return ResponseEntity.ok(uploadedFileResponse);
    } catch (Exception e) {
        return ResponseEntity.status(500).body(
                 java.util.Map.of("error", "Upload failed", "details", e.getMessage())
        );
    }
    }


}
