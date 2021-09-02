package org.uservs.probe.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="config")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Config {
    public static Config of(@NotNull org.uservs.probe.data.Config configData){
        return Config.builder()
                .name( configData.getName() )
                .build();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "config_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="config", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Probe> probes;
}
