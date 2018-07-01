package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v2.PetStoreService;

/**
 * Created by dengrd on 2018/7/1.
 */
public class ApplicationContextTest {

    @Test
    public void testGetBeanProperty(){
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");

        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());
    }
}
