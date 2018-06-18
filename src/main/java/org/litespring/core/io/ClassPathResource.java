package org.litespring.core.io;

import org.litespring.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dengrd on 2018/6/18.
 */
public class ClassPathResource implements Resource {

    private String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path,null);
    }

    public ClassPathResource(String path,ClassLoader classLoader){
        this.path = path;
        this.classLoader = classLoader == null ? ClassUtils.getDefaultClassLoader() : classLoader;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = this.classLoader.getResourceAsStream(path);

        if (inputStream == null){
            throw new FileNotFoundException("path : "+ path + " can not be opened");
        }
        return inputStream;
    }

    @Override
    public String getDescription() {
        return this.path;
    }
}
