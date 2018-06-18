package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;

/**
 * Created by dengrd on 2018/6/18.
 */


public class BeanFactoryTest {

    @Test
    public void testGetBean(){
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");
        String beanClassName = beanDefinition.getBeanClassName();
        Assert.assertEquals("org.litespring.service.v1.PetStoreService",beanClassName);

        PetStoreService petStore = (PetStoreService) beanFactory.getBean("petStore");

        Assert.assertNotNull(petStore);
    }

    @Test
    public void testInvalidBean(){
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
        try {
            beanFactory.getBean("invalidBean");
        }catch (BeanCreationException e){
            return;
        }

        Assert.fail("except BeanCreationException");

    }

    @Test
    public void testInvalidXML(){
        try {
            new DefaultBeanFactory("xxx.xml");
        }catch (BeanDefinitionStoreException e){
            return;
        }

        Assert.fail("except BeanDefinitionStoreException");
    }
}
