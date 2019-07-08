package com.yang.interce;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        String body = new String(event.getBody());
       Map<String,String> headers = event.getHeaders();
       if(body.contains("start")){
           headers.put("topic","topic_start");
       }else {
           headers.put("topic","topic_event");
       }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        List<Event> events = new ArrayList<>();
        for(Event event:list){
            Event event1=intercept(event);
            events.add(event1);
        }
        return events;
    }

    @Override
    public void close() {

    }
    public static class MyTypeInterceptor implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new TypeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
