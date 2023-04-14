package com.database.shard.datasource;


public class DynamicDataSourceContentHodler {

    private static ThreadLocal<String> dataSourceThreadLocal = new ThreadLocal<>();

    public static void set(String value){
        dataSourceThreadLocal.set(value);
    }

    public static void clear(){
        dataSourceThreadLocal.remove();
    }

    public static String get(){
        return dataSourceThreadLocal.get();
    }

}
