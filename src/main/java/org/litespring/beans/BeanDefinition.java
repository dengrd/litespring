package org.litespring.beans;

import java.util.List;

/**
 * Created by dengrd on 2018/6/18.
 */
public interface BeanDefinition {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    String SCOPE_DEFAULT = "";

    String getBeanClassName();

    boolean isSingleton();

    boolean isPrototype();

    String getScope();

    void setScope(String scope);

    List<PropertyValue> getPropertyValues();
}
