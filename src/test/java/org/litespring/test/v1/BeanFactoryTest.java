package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.xml.XMLBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.service.v1.PetStoreService;

/**
 * Created by dengrd on 2018/6/18.
 */


public class BeanFactoryTest {
    DefaultBeanFactory beanFactory ;
    XMLBeanDefinitionReader reader ;
    Resource resource;

    @Before
    public void setUp(){
        beanFactory = new DefaultBeanFactory();
        reader = new XMLBeanDefinitionReader(beanFactory);
    }


    @Test
    public void testGetBean(){
        resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinitions(resource);

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");
        String beanClassName = beanDefinition.getBeanClassName();
        Assert.assertEquals("org.litespring.service.v1.PetStoreService",beanClassName);

        PetStoreService petStore = (PetStoreService) beanFactory.getBean("petStore");

        Assert.assertNotNull(petStore);
    }

    @Test
    public void testInvalidBean(){
        resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinitions(resource);

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
            resource = new ClassPathResource("xxx.xml");
            reader.loadBeanDefinitions(resource);
        }catch (BeanDefinitionStoreException e){
            return;
        }

        Assert.fail("except BeanDefinitionStoreException");
    }
}
