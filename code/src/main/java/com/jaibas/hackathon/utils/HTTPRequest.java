package com.jaibas.hackathon.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;

public class HTTPRequest {
  private static String prefix = "https://gb04ac592cc4c15-dbahackathon2024.adb.us-phoenix-1.oraclecloudapps.com/ords/admin/";

  public static JsonArray get(String query) throws Exception {
    HttpClient client = HttpClient.newBuilder().build();
    HttpRequest req = HttpRequest.newBuilder(new URI(prefix + query)).GET().build();
    HttpResponse<String> res = client.send(req, BodyHandlers.ofString());
    JsonObject json = JsonParser.parseString(res.body().toString()).getAsJsonObject();

    return json.get("items").getAsJsonArray();
  }
}