package org.litespring.context.support;

import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

/**
 * Created by dengrd on 2018/6/18.
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String configFile) {
        return new FileSystemResource(configFile);
    }


}
