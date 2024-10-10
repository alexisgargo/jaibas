package com.jaibas.hackathon.service;

import java.io.File;
import java.io.StringReader;
import java.nio.file.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

@Service
public class ImageService {
    private Path imageDir = Paths.get("/tmp/image");
    private final Path pythonDir = Paths.get("/tmp/final");

    private final String imageName = "currentImage.jpg";


    public JsonObject getResponse(MultipartFile file) throws Exception {
        File image = new File(imageDir + "/" + imageName);
        file.transferTo(image);
        
        Process pythonScript = Runtime.getRuntime().exec("python3 " + pythonDir + "/predict_image.py");
        int exitCode = pythonScript.waitFor();

        if (exitCode != 0){
            throw new Exception();
        }
        
        Path output = pythonDir.resolve("out/result.json");
        String pythonResponse = Files.readString(output);

        StringReader reader = new StringReader(pythonResponse);
        JsonParser jsonParser = Json.createParser(reader);

        Event event;
        
        JsonObjectBuilder builder = Json.createBuilderFactory(null).createObjectBuilder();
        while (jsonParser.hasNext()) {
            event = jsonParser.next();
            switch (event) {
                case KEY_NAME:
                    String key = jsonParser.getString();
                    event = jsonParser.next();
                    String value = jsonParser.getString();

                    builder.add(key, value);
                    break;
                default:
                    break;
            }
        } 
        
        if (!image.delete()) {            
            throw new Exception();
        }

        return builder.build();
    }
}
