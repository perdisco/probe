package org.uservs.probe.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uservs.probe.service.Sniffer;

@RestController
@RequestMapping("/sniff")
@Log4j2
public class SniffController {
    @PostMapping(path = "/start",
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> start(@RequestParam Integer configId) {
        return ResponseEntity
                .ok()
                .header("Cache-control", "private")
                .body( "" );
    }

    @PutMapping(path = "/stop",
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> stop(@RequestParam Integer sessionId) {
        return ResponseEntity
                .ok()
                .header("Cache-control", "private")
                .body( "" );
    }

    @GetMapping(path = "/sessions",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> sessions() {
        return ResponseEntity
                .ok()
                .header("Cache-control", "private")
                .body( "" );
    }

    @Autowired
    private Sniffer sniffer;
}
