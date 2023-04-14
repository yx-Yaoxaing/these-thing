package com.database.shard.datasource.shard;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class Sharddelivery<T> implements DatabaseShardTableDivisionAlgorithm {

    static class SharddeliveryBuilder<T>{

        private Integer tableTotal;
        private T indexParam;
        private Integer sideTotal;

        public static SharddeliveryBuilder builder(){
            return new SharddeliveryBuilder();
        }

        public SharddeliveryBuilder setTableTotal(int tableTotal){
            this.tableTotal = tableTotal;
            return this;
        }

        public SharddeliveryBuilder setSideTotal(int sideTotal){
            this.sideTotal = sideTotal;
            return this;
        }

        public SharddeliveryBuilder setIndexParam(T indexParam){
            this.indexParam = indexParam;
            return this;
        }
        public Sharddelivery build(){
            Sharddelivery sharddelivery = new Sharddelivery(indexParam,tableTotal,sideTotal);
            return sharddelivery;
        }

    }

    private Integer tableTotal;

    private Integer sideTotal;

    private T indexParam;

    public Sharddelivery(T indexParam,Integer tableTotal, Integer sideTotal) {
        this.indexParam = indexParam;
        this.tableTotal = tableTotal;
        this.sideTotal = sideTotal;
    }

    @Override
    public int executorTable() {
        int index = 0;
        if (indexParam instanceof String) {
            index = (indexParam.hashCode()) & (tableTotal - 1);
        } else if (indexParam instanceof Number) {
            index = ((int)indexParam) & (tableTotal - 1);
        }
        return index;
    }


    @Override
    public int executoroutside() {
        return 0;
    }

}
