package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

/**
 * Created by dengrd on 2018/6/18.
 */
public interface BeanFactory {

    BeanDefinition getBeanDefinition(String beanId);

    Object getBean(String beanId);
}
