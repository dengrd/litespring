package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * Created by dengrd on 2018/6/18.
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanId, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanId);
}
