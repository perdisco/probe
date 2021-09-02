package org.uservs.probe.data;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SocketProbe extends Probe {
    public static SocketProbe of(@NotNull org.uservs.probe.entity.Probe probe){
        return SocketProbe.builder()
                .id( probe.getId() )
                .port( probe.getPort() ) // TODO: reimplement
                .build();
    }

    Integer port;
}
