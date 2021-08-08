package org.uservs.probe.test;

import fj.data.Either;
import fj.data.Option;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Helper {
    public static Either<Exception, File> getResourceFile(String path){
        return Option.fromNull(path)
                .map( Helper.class.getClassLoader()::getResource )
                .map( URL::getFile )
                .map( File::new )
                .toEither(null);
    }

    public static Either<Exception, Path> getResourcePath(String path){
        return Helper.getResourceFile(path)
                .right().map( File::getAbsolutePath )
                .right().map( Paths::get );
    }

    public static Either<Exception, byte[]> readResourceAsBytes(String path){
        return Helper.getResourcePath(path)
                .right().map( p -> {
                    try {
                        return Files.readAllBytes(p);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                });
    }

    public static Either<Exception, ByteBuffer> readResourceAsBuffer(String path){
        return Helper.readResourceAsBytes(path)
                .right().map( ByteBuffer::wrap );
    }
}
