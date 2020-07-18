package com.candy.mz.support;

/**
 * @author: candy
 * @date: 2020/7/18
 * @description :
 **/
public class SimpleResponse {

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public SimpleResponse(Object content) {
        this.content = content;
    }
}
