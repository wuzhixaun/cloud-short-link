package com.wuzx.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: wuzhixuan
 * @date 2022/11/27 14:26
 * @Version 1.0
 */
public class ShadingTabeConfig {


    /**
     * 存储数据库位置编号
     */
    private static final List<String> tablePrefix = new ArrayList<>();

    /**
     * 配置启用哪些库的前缀
     */
    static {
        tablePrefix.add("0");
        tablePrefix.add("a");
    }

    private static Random random = new Random();

    /**
     * 获取随机前缀
     * @return
     */
    public static String getRandomTablePrefix() {
        int index = random.nextInt(tablePrefix.size());
        return tablePrefix.get(index);
    }
}
