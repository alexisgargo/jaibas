package com.jaibas.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jaibas.hackathon.service.ImageService;

@RestController
@RequestMapping("api")
public class ImageController {

  @Autowired
  ImageService imageService;

  @PostMapping("/classifier")
  public ResponseEntity<String> classifyImage(@RequestParam("image") MultipartFile file) {

    if (file.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("empty file");
    }
    if (!file.getContentType().startsWith("image/")) {
      return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Not an image");
    }

    try {

      // byte[] bytes = file.getBytes();
      // System.out.println(bytes.length);

      return ResponseEntity.status(HttpStatus.ACCEPTED).body(imageService.getResponse(file).toString());
      
    } catch (Exception e) {
      System.err.println(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error processing image");
    }

  }
}
