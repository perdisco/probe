package org.uservs.probe.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.uservs.probe.data.SocketProbe;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

// TODO: redesign as Probe data is polymorphic based on probe type
@Entity
@Table(name="probe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Probe {
    public static Probe of(@NotNull org.uservs.probe.data.Probe probe){
        return Probe.builder()
                .port( ((SocketProbe)probe).getPort() ) // TODO: reimplement
                .build();
    }

    // TODO: use Probe instead of SocketProbe
    public static Set<Probe> setOf(@NotNull Set<org.uservs.probe.data.Probe> probes){
        return probes.stream()
                .map( Probe::of )
                .collect( Collectors.toSet() );
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "probe_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="config_id")
    private Config config;

    @Column(name = "port")
    private Integer port;
}
