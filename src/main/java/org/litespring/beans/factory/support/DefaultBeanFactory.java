package org.litespring.beans.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dengrd on 2018/6/18.
 */
public class DefaultBeanFactory implements BeanFactory {

    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";

    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactory(String configFile) {
        loadBeanDefinition(configFile);
    }

    private void loadBeanDefinition(String configFile) {
        InputStream in = null;
        try {
            ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            in = classLoader.getResourceAsStream(configFile);

            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);

            //<beans>
            Element root = doc.getRootElement();
            Iterator<Element> iterator = root.elementIterator();

            while (iterator.hasNext()){
                Element beanElement = iterator.next();
                String id = beanElement.attributeValue(ID_ATTRIBUTE);
                String className = beanElement.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id,className);
                this.beanDefinitionMap.put(id,beanDefinition);
            }
        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("Exception on parsing XML document '"+configFile+"'" ,e);
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> clazz = classLoader.loadClass(beanClassName);
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for '"+ beanClassName + "' failed",e);
        }
    }
}
