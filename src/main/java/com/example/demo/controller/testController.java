package com.example.demo.controller;

import com.example.demo.config.PropertiesConfig;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class testController {

    private static Logger log = LoggerFactory.getLogger(testController.class);

    @Autowired
    private PropertiesConfig config;

    @Value("${test:Welcome to Youtube}")
    private String test;


    @GetMapping
    public String welcome() {
        log.info(test);
        return test;
    }

    @GetMapping("/data")
    public ResponseEntity<ResponseData> getData() {
        ResponseData responseData = new ResponseData();
        responseData.setId(1);
        responseData.setName(config.getName());
        responseData.setPlace("Hyderabad");
        responseData.setValue(config.toString());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/document/{ruta}")
    public String getDocument(@PathVariable("ruta") String ruta) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta + "/application.yaml"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        return br.toString();
    }

    @Getter
    @Setter
    public class ResponseData {
        private String name;
        private Integer id;
        private String place;
        private String value;
    }

    @GetMapping("/document")
    public String getDocument() throws IOException {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            log.info(envName + ": " + env.get(envName));
        }
        return env.getOrDefault(env.get("application.properties"),"NotFound");
    }

}
