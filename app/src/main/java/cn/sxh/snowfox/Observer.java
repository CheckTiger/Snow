package cn.sxh.snowfox;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public interface Observer {
    void update(float temp, float humidity, float pressure);
}
