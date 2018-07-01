package org.litespring.beans;

import org.litespring.beans.factory.config.RuntimeBeanReference;

/**
 * Created by dengrd on 2018/6/30.
 */
public class PropertyValue {
    private final String name;

    private final Object value;

    private boolean isConverted = false;

    private Object convertedValue;

    public PropertyValue(String name,Object value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public synchronized boolean isConverted(){
        return this.isConverted;
    }

    public synchronized Object getConvertedValue() {
        return convertedValue;
    }

    public synchronized void setConvertedValue(Object convertedValue) {
        this.isConverted = true;
        this.convertedValue = convertedValue;
    }
}
