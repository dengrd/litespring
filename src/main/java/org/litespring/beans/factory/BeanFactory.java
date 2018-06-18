package org.litespring.beans.factory;


/**
 * Created by dengrd on 2018/6/18.
 */
public interface BeanFactory {

    Object getBean(String beanId);
}
