package org.litespring.context.support;

import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

/**
 * Created by dengrd on 2018/6/18.
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String configFile) {
        return new ClassPathResource(configFile,getBeanClassLoader());
    }

}
