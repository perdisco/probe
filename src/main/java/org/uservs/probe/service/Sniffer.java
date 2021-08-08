package org.uservs.probe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uservs.probe.data.Probe;

import java.util.List;

@Service
public class Sniffer {
    public Integer start(Integer id) {
        return 0;
    }

    protected void initializeProbes(List<Probe> probes){

    }

    @Autowired
    private ConfigService configService;
}
