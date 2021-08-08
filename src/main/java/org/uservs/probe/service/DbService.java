package org.uservs.probe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.uservs.probe.data.Config;


@Service
public class DbService {
    public Config[] configList(){
        return restTemplate.getForEntity(
                "http://db_service/config",
                Config[].class).getBody();
    }

    public Config configGetById(Integer id){
        return restTemplate.getForEntity(
                "http://db_service/config/" + id,
                Config.class).getBody();
    }

    @Autowired
    private RestTemplate restTemplate;
}
