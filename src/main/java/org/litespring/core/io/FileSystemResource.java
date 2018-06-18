package org.litespring.core.io;

import org.litespring.utils.Assert;

import java.io.*;

/**
 * Created by dengrd on 2018/6/18.
 */
public class FileSystemResource implements Resource {
    private final String path;
    private final File file;

    public FileSystemResource(String path) {
        Assert.notNull(path,"path must be not null");
        this.path = path;
        this.file = new File(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public String getDescription() {
        return "File path [" + this.path + "]";
    }
}
