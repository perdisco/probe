package org.uservs.probe.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uservs.probe.service.ConfigService;


@RestController
@RequestMapping("/config")
@Log4j2
public class ConfigController {
    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> list() {
        return ResponseEntity
                .ok()
                .body( "" );
    }

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> get(@RequestParam Integer id) {
        return ResponseEntity
                .ok()
                .body( "" );
    }

    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> newConfig() {
        return ResponseEntity
                .ok()
                .body( "" );
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> update() {
        return ResponseEntity
                .ok()
                .body( "" );
    }

    @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Integer id) {
        return ResponseEntity
                .ok()
                .body( "" );
    }

    @Autowired
    private ConfigService configService;
}
