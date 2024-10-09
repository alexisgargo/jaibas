package com.jaibas.hackathon.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

  public String response(String name) {
    return "Hola, " + name;
  }
}
