package org.uservs.probe.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    Integer id;
    String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<Probe> probes;
}
