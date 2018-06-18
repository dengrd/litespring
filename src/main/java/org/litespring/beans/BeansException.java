package org.litespring.beans;

/**
 * Created by dengrd on 2018/6/18.
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg){
        super(msg);
    }

    public BeansException(String msg,Throwable cause){
        super(msg,cause);
    }
}
