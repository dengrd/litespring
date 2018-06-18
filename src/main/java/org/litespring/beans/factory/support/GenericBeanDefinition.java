package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * Created by dengrd on 2018/6/18.
 */
public class GenericBeanDefinition implements BeanDefinition {
    private String id;
    private String className;

    public GenericBeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    @Override
    public String getBeanClassName() {
        return this.className;
    }
}
