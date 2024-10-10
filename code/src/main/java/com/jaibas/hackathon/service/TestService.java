package com.jaibas.hackathon.service;

import com.jaibas.hackathon.utils.Request;
import org.springframework.stereotype.Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

@Service
public class TestService {

  public String response(String name) {
    getData();
    return "Hola, " + name;
  }

  private void getData() {
    try {
      JsonArray response = Request.get("/team");
      for (JsonElement member : response) {
        System.out.println(
            "id: " + member.getAsJsonObject().get("id") +
                " name: " + member.getAsJsonObject().get("name") +
                " team: " + member.getAsJsonObject().get("team"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
