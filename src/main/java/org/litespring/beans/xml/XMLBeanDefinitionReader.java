package org.litespring.beans.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.utils.StringUtils;

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
    public static final String PROPERTY_ATTRIBUTE = "property";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String VALUE_ATTRIBUTE = "value";

    protected final Log logger = LogFactory.getLog(XMLBeanDefinitionReader.class);
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
                parsePropertyElement(beanElement,beanDefinition);
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

    public void parsePropertyElement(Element beanElem, BeanDefinition bd) {
        Iterator iterator = beanElem.elementIterator(PROPERTY_ATTRIBUTE);
        while (iterator.hasNext()){
            Element element = (Element) iterator.next();
            String propertyName = element.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }

            Object value = parsePropertyValue(element,bd,propertyName);
            PropertyValue pv = new PropertyValue(propertyName,value);
            bd.getPropertyValues().add(pv);
        }


    }

    public Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName){
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";

        boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE) != null);
        boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) != null);

        if (hasRefAttribute){
            String refName = ele.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }

            RuntimeBeanReference reference = new RuntimeBeanReference(refName);
            return reference;
        }else if (hasValueAttribute){
            TypedStringValue value = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));
            return value;
        }else {
            throw new RuntimeException(elementName + " must specify a ref or value");
        }

    }
}
