package org.uservs.probe.service;

import io.vavr.collection.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uservs.probe.data.Probe;

@Service
public class Sniffer {
    public Integer start(Integer id) {
        var config = configService.configGetById(id);
        //initializeProbes(config.getProbes());
        return 0;
    }

    protected void initializeProbes(Set<Probe> probes){

    }

    @Autowired
    private ConfigService configService;
}
