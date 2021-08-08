package org.uservs.probe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uservs.probe.data.Config;

@Service
public class ConfigService {
    public Config[] configList(){
        return dbService.configList();
    }

    public Config configGetById(Integer id){
        return dbService.configGetById(id);
    }

    @Autowired
    private DbService dbService;
}
