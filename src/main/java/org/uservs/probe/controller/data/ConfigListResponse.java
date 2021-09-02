package org.uservs.probe.controller.data;

import lombok.*;
import lombok.experimental.Accessors;
import org.uservs.probe.data.Config;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ConfigListResponse extends ConfigResponse {
    List<Config> configs;
}
