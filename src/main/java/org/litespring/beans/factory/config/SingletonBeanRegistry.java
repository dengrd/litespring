package org.litespring.beans.factory.config;

/**
 * Created by dengrd on 2018/6/30.
 */
public interface SingletonBeanRegistry {

    void registrySingleton(String beanName,Object singletonBean);

    Object getSingleton(String beanName);
}
