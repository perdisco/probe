package org.uservs.probe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.List;
import io.vavr.jackson.datatype.VavrModule;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uservs.probe.controller.data.ConfigGetResponse;
import org.uservs.probe.controller.data.ConfigListResponse;
import org.uservs.probe.controller.data.ConfigNewResponse;
import org.uservs.probe.data.Config;
import org.uservs.probe.service.ConfigService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/config")
@Log4j2
public class ConfigController {
    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> list() {
        return ResponseEntity
                .ok()
                .header("Cache-control", "private")
                .body( listResponse( configService.configList() ) );
    }

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> get(@RequestParam Integer id) {
        return ResponseEntity
                .ok()
                .body( getResponse( configService.configGetById(id) ) );
    }

    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> newConfig(@NotNull HttpServletRequest request) throws IOException, DecoderException {
        String requestBody = new URLCodec().decode(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());
        return ResponseEntity
                .ok()
                .body( newResponse( configService.configNew( mapper.readValue(requestBody, Config.class) ) ) );
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

    @Nullable
    private String listResponse(List<Config> configs){
        try {
            return new ObjectMapper()
                    .writeValueAsString(
                            new ConfigListResponse()
                                    .setConfigs(configs)
                                    .setStatus(0) );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    private String getResponse(Config config){
        try {
            return new ObjectMapper()
                    .writeValueAsString(
                            new ConfigGetResponse()
                                    .setConfig(config)
                                    .setStatus(0) );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    private String newResponse(Integer id){
        try {
            return new ObjectMapper()
                    .writeValueAsString(
                            new ConfigNewResponse()
                                    .setId(id)
                                    .setStatus(0) );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Autowired
    private ConfigService configService;
}
