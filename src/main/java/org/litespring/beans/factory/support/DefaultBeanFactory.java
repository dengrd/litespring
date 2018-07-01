package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.utils.ClassUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dengrd on 2018/6/18.
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory,BeanDefinitionRegistry{

    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ClassLoader beanClassLoader;

    public DefaultBeanFactory() {
    }


    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanId,beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = getBeanDefinition(beanId);
        if (beanDefinition == null){
            throw new BeanCreationException("Bean Definition does not exist");
        }

        if (beanDefinition.isSingleton()){
            Object singletonBean = getSingleton(beanId);
            if (singletonBean == null){
                singletonBean = createBean(beanDefinition);
                registrySingleton(beanId,singletonBean);
            }
            return singletonBean;
        }

        return createBean(beanDefinition);
    }

    private Object createBean(BeanDefinition beanDefinition) {
        ClassLoader classLoader = this.getBeanClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> clazz = classLoader.loadClass(beanClassName);
            Object object = clazz.newInstance();
            BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
            for(PropertyValue pv : beanDefinition.getPropertyValues()){
                String propertyName = pv.getName();
                pv.setConvertedValue(resolver.resolveValueIfNecessary(pv.getValue()));
                Class argClass = classLoader.loadClass(getBeanDefinition(propertyName).getBeanClassName());
                Method setMethod = clazz.getMethod("set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1),argClass);
                if (pv.isConverted()) {
                    setMethod.invoke(object, pv.getConvertedValue());
                }else {
                    setMethod.invoke(object,pv.getValue());
                }
            }
            return object;
        } catch (Exception e) {
            throw new BeanCreationException("create bean for '"+ beanClassName + "' failed",e);
        }
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
