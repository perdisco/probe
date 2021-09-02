package org.uservs.probe.controller.data;

import io.vavr.collection.List;
import lombok.*;
import lombok.experimental.Accessors;
import org.uservs.probe.data.Config;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ConfigListResponse extends ConfigResponse {
    List<Config> configs;
}
