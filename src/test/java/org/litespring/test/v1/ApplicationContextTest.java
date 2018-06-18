package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.context.support.FileSystemXmlApplicationContext;
import org.litespring.service.v1.PetStoreService;

/**
 * Created by dengrd on 2018/6/18.
 */
public class ApplicationContextTest {

    @Test
    public void testGetBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStore);
    }

    @Test
    public void testGetBeanFromFileSystemContext(){
        ApplicationContext context = new FileSystemXmlApplicationContext("F:\\JavaProjects\\litespring\\src\\test\\resources\\petstore-v1.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStore);
    }
}
