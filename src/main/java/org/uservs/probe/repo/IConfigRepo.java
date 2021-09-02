package org.uservs.probe.repo;

import org.springframework.data.repository.CrudRepository;
import org.uservs.probe.entity.Config;

public interface IConfigRepo extends CrudRepository<Config, Integer> {
}
