package org.uservs.probe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uservs.probe.controller.data.SniffStartResponse;
import org.uservs.probe.service.Sniffer;

@RestController
@RequestMapping("/sniff")
@Log4j2
public class SniffController {
    @PostMapping(path = "/start", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> start(@RequestParam Integer id) {
        return ResponseEntity
                .ok()
                .header("Cache-control", "private")
                .body( startResponse(sniffer.start(id)) );
    }

    String startResponse(Integer status){
        try {
            return new ObjectMapper()
                    .writeValueAsString(
                            new SniffStartResponse()
                                    .setStatus(status) );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Autowired
    private Sniffer sniffer;
}
