package org.uservs.probe.repo;

import org.springframework.data.repository.CrudRepository;
import org.uservs.probe.entity.Probe;

public interface IProbeRepo extends CrudRepository<Probe, Integer> {
}
