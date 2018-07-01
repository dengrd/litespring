package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.xml.XMLBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;

import java.util.List;

/**
 * Created by dengrd on 2018/7/1.
 */
public class BeanDefinitionTest {

    @Test
    public void testGetBeanDefinition(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));

        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");
        List<PropertyValue> pvs = beanDefinition.getPropertyValues();

        Assert.assertEquals(2,pvs.size());
        {
            PropertyValue pv = getPropertyValue("accountDao",pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }

        {
            PropertyValue pv = getPropertyValue("itemDao",pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
    }

    private PropertyValue getPropertyValue(String name,List<PropertyValue> pvs){
        for (PropertyValue pv : pvs){
            if (pv.getName().equals(name)){
                return pv;
            }
        }
        return null;
    }
}
