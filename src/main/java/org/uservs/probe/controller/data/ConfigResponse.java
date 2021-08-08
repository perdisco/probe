package org.uservs.probe.controller.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ConfigResponse extends Response {
    public ConfigResponse(Integer status){
        super(status);
    }
}
