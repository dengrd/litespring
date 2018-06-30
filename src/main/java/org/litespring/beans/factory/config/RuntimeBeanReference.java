package org.litespring.beans.factory.config;

/**
 * Created by dengrd on 2018/6/30.
 */
public class RuntimeBeanReference {

    private final String beanName;

    public RuntimeBeanReference(String beanName){
        this.beanName = beanName;
    }

    public String getBeanName() {
        return this.beanName;
    }
}
