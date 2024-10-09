package com.jaibas.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaibas.hackathon.service.TestService;

@RestController
@RequestMapping("api")
public class TestController {

  @Autowired
  TestService testService;

  @GetMapping("/nombre")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return testService.response(name);
  }
}