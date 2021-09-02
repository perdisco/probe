package org.uservs.probe.service;

import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.uservs.probe.data.Config;
import org.uservs.probe.repo.IConfigRepo;
import org.uservs.probe.repo.IProbeRepo;

import java.util.stream.Collectors;

@Service
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Log4j2
public class ConfigService {
    public Integer configNew(@NotNull Config config){
        var configEntity = configRepo.save( org.uservs.probe.entity.Config.of(config) );
        var probesSaved = config.getProbes().stream()
                .map( probe -> probeRepo.save(org.uservs.probe.entity.Probe.of(probe).setConfig(configEntity) ))
                .collect(Collectors.toSet());
        return configRepo.save( configEntity.setProbes(probesSaved) ).getId();
    }

    public List<Config> configList(){
        return List.ofAll( configRepo.findAll() )
                .toStream()
                .map( Config::of )
                .toList();
    }

    public Config configGetById(Integer id){
        return configRepo.findById(id)
                .map( Config::of )
                .orElse(null);
    }

    @Autowired
    private IConfigRepo configRepo;

    @Autowired
    private IProbeRepo probeRepo;
}
