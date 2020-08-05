package com.example.demo.controller;

import com.example.demo.config.PropertiesConfig;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class testController {

    private static Logger log = LoggerFactory.getLogger(testController.class);

    @Autowired
    PropertiesConfig config;

    @GetMapping("/data")
    public ResponseEntity<ResponseData> getData() {
        ResponseData responseData = new ResponseData();
        responseData.setId(1);
        responseData.setName(config.getName());
        responseData.setPlace("Hyderabad");
        responseData.setValue(config.getTest());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
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
        BufferedReader br = new BufferedReader(new FileReader("/config/application.yml"));
        String line;
        while ((line = br.readLine()) != null) {
            log.info(line);
        }
        return br.toString();
    }

}
