package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.xml.XMLBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.utils.ClassUtils;

/**
 * Created by dengrd on 2018/6/18.
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory defaultBeanFactory;
    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configFile) {
        defaultBeanFactory = new DefaultBeanFactory();
        defaultBeanFactory.setBeanClassLoader(this.getBeanClassLoader());
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(defaultBeanFactory);
        Resource resource = getResourceByPath(configFile);
        reader.loadBeanDefinitions(resource);
    }

    protected abstract Resource getResourceByPath(String configFile);

    @Override
    public Object getBean(String beanId) {
        return defaultBeanFactory.getBean(beanId);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader == null ? ClassUtils.getDefaultClassLoader() : this.beanClassLoader;
    }
}
