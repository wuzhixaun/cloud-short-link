package com.wuzx.strategy;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 水平分表-标准分片策略-精准分片算法
 *
 * @author: wuzhixuan
 * @date 2022/11/27 14:16
 * @Version 1.0
 */
public class CustomTablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    /**
     * @param collection           数据源集合,在分库时值为所有分片库的集合,分表时为对应分片库中所有的分片表的集合
     * @param preciseShardingValue 分片属性，包括logicTableName逻辑表，columnName 分片字段，value SQL 中解析出的分片键值
     * @return
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {

        // 获取逻辑表
        final String targetName = collection.iterator().next();

        // 短链码
        final String value = preciseShardingValue.getValue();

        // 获取短链码最后一位
        final String codeSuffix = value.substring(value.length() - 1);

        // 拼接Actual table

        return targetName +"_"+ codeSuffix;
    }
}
