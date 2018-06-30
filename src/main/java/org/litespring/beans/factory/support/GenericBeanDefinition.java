package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * Created by dengrd on 2018/6/18.
 */
public class GenericBeanDefinition implements BeanDefinition {
    private String id;
    private String className;
    private String scope = SCOPE_DEFAULT;

    public GenericBeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    @Override
    public String getBeanClassName() {
        return this.className;
    }

    @Override
    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(this.scope) || SCOPE_DEFAULT.equals(this.scope);
    }

    @Override
    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(this.scope);
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }
}
