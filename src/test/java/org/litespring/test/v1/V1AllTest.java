package org.litespring.test.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by dengrd on 2018/6/18.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTest.class,
        BeanFactoryTest.class,
        ResourceTest.class
})
public class V1AllTest {

}
