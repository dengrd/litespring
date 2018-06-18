package org.litespring.beans.factory.config;

import org.litespring.beans.factory.BeanFactory;

/**
 * Created by dengrd on 2018/6/18.
 */
public interface ConfigurableBeanFactory extends BeanFactory {
    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();
}
