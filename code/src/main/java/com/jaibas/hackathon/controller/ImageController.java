package com.jaibas.hackathon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("image")
public class ImageController {

  @PostMapping("")
  public ResponseEntity<String> classifyImage(@RequestParam("image") MultipartFile file) {

    return new ResponseEntity<String>("hola", HttpStatus.ACCEPTED);
  }
}
