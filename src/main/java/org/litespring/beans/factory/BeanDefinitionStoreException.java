package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

/**
 * Created by dengrd on 2018/6/18.
 */
public class BeanDefinitionStoreException extends BeansException{

    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
