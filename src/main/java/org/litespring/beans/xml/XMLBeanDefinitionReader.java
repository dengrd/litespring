package org.litespring.beans.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;


/**
 * Created by dengrd on 2018/6/18.
 */
public class XMLBeanDefinitionReader {

    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String SCOPE_ATTRIBUTE = "scope";

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XMLBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream in = null;
        try {
            in = resource.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);

            //<beans>
            Element root = doc.getRootElement();
            Iterator<Element> iterator = root.elementIterator();

            while (iterator.hasNext()){
                Element beanElement = iterator.next();
                String id = beanElement.attributeValue(ID_ATTRIBUTE);
                String className = beanElement.attributeValue(CLASS_ATTRIBUTE);
                String scope = beanElement.attributeValue(SCOPE_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id,className);
                if (scope != null){
                    beanDefinition.setScope(scope);
                }
                beanDefinitionRegistry.registerBeanDefinition(id,beanDefinition);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("Exception on parsing XML document '"+ resource.getDescription() +"'" ,e);
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
}
