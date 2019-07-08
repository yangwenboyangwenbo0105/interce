package com.yang.interce;

import org.apache.commons.lang.math.NumberUtils;

public class ETUtil {
    public static boolean valiStartData(String body){
        if(body==null){
            return false;
        }

       return body.startsWith("{")&&body.endsWith("}");
    }
    public static boolean valiEventData(String body){
        if(body==null){
            return false;
        }
        String[] spilts = body.split("\\|");
        if(spilts.length!=2){
            return false;
        }
        if(!(spilts[0].length()==13)||!NumberUtils.isDigits(spilts[0])){
            return false;

        }
        return spilts[1].startsWith("{")&&spilts[1].endsWith("}");
    }
}
