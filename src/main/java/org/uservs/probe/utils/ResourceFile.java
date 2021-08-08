package org.uservs.probe.utils;


import lombok.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Value
public class ResourceFile {
    String name;

    public String readAsString() throws IOException {
        var file = new File(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource(name) )
                        .getFile() );
        return new String(
                Files.readAllBytes(
                        Paths.get(
                                file.getPath() ) ) ) ;
    }
}
