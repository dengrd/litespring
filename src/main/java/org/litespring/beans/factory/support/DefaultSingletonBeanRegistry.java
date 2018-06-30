package org.litespring.beans.factory.support;

import org.litespring.beans.factory.config.SingletonBeanRegistry;
import org.litespring.utils.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dengrd on 2018/6/30.
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String,Object> singletonObjects = new ConcurrentHashMap<>(64);


    @Override
    public void registrySingleton(String beanName, Object singletonBean) {
        Assert.notNull(beanName,"bean's name cannot be null");

        Object oldObject = this.singletonObjects.get(beanName);
        if (oldObject != null){
            throw new IllegalStateException("Could not registry Object [" + singletonBean + "] with bean name "+beanName +": cuz there is already Object [" +
                    oldObject +"] bound");
        }

        this.singletonObjects.put(beanName,singletonBean);
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }
}
