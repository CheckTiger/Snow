package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class SnowFox {
    public static void main(String[] args) throws Exception {
        int version = 1;
        String defaultJavaPackage = "cn.sxh.snow.greendao";
        Schema schema = new Schema(version, defaultJavaPackage);
        createTable(schema);
        new DaoGenerator().generateAll(schema,"./app/src/main/java-gen");
    }

    private static void createTable(Schema schema) {
        Entity entity = schema.addEntity("SnowFoxTable");
        /**
         * 频道名称
         */
        entity.addStringProperty("SnowFoxChannelName").notNull().primaryKey().index();
        /**
         * 频道id
         */
        entity.addStringProperty("SnowFoxChannelId").notNull();
        /**
         * 频道类型
         */
        entity.addStringProperty("SnowFoxChannelType").notNull();
        /**
         * 选中的频道
         */
        entity.addBooleanProperty("SnowFoxChannelSelect").notNull();
        /**
         * 频道的排序位置
         */
        entity.addIntProperty("SnowFoxChannelIndex").notNull();
        /**
         * 频道是否是固定的
         */
        entity.addBooleanProperty("SnowFoxChannelFixed");
    }
}
