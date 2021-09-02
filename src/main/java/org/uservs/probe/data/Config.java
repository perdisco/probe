package org.uservs.probe.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.vavr.collection.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Config {
    public static Config of(@NotNull org.uservs.probe.entity.Config config){
        return Config.builder()
                .id( config.getId() )
                .name( config.getName() )
                .probes( Probe.setOf( Stream.ofAll( config.getProbes() ).toJavaSet() ) )
                .build();
    }

    Integer id;
    String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Set<Probe> probes;
}
