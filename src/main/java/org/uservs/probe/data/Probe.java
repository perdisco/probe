package org.uservs.probe.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SocketProbe.class, name = "SocketProbe") } )
public class Probe {
    public static Set<Probe> setOf(@NotNull Set<org.uservs.probe.entity.Probe> probes){
        return probes.stream()
                .map( SocketProbe::of ) // TODO: reimplement to polymorph
                .map( sp -> (Probe)sp )
                .collect(Collectors.toSet());
    }

    Integer id;
}
