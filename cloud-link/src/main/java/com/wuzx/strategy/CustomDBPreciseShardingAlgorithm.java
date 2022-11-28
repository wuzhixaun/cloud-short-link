package com.wuzx.strategy;

import com.wuzx.enums.BizCodeEnum;
import com.wuzx.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 水平分库-标准分片策略-精准分片算法
 * @author: wuzhixuan
 * @date 2022/11/27 13:58
 * @Version 1.0
 */
public class CustomDBPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {


    /**
     * @param collection           数据源集合,在分库时值为所有分片库的集合,分表时为对应分片库中所有的分片表的集合
     * @param preciseShardingValue 分片属性，包括logicTableName逻辑表，columnName 分片字段，value SQL 中解析出的分片键值
     * @return
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        // 获取短链码第一位
        final String codePrefix = preciseShardingValue.getValue().substring(0, 1);

        for (String targetName : collection) {
            // 获取库名的最后一位，真实配置的ds
            final String targetNameSuffix = targetName.substring(targetName.length() - 1);

            // 如果一致则返回
            if (StringUtils.equals(codePrefix, targetNameSuffix)) {
                return targetName;
            }
        }

        throw new BizException(BizCodeEnum.DB_ROUTE_NOT_FOUND);
    }
}
