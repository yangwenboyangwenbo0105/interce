package com.yang.interce;

import org.apache.flume.Context;
import org.apache.flume.Event;

import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;

public class MyInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        boolean flag;
        String body = new String(event.getBody());
        if(body.contains("start")){
            flag=ETUtil.valiStartData(body);
        }else {
            flag = ETUtil.valiEventData(body);
        }
        if(flag){
            return event;
        }else {

            return null;
        }

    }

    @Override
    public List<Event> intercept(List<Event> list) {
        List<Event> interceptors = new ArrayList<>();
        for(Event event:list){
            Event event1 = intercept(event);
            interceptors.add(event1);
        }
        return interceptors;
    }

    @Override
    public void close() {

    }
    public static class MyBuilder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new MyInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
