package cn.sxh.snowfox;

import com.socks.library.KLog;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class CurrentConditionsDisplay implements Observer,DispalyElement {

    private static final String TAG = CurrentConditionsDisplay.class.getSimpleName();
    private float temperature;
    private float humidity;
    private Subject weatherdata;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherdata = weatherData;
        weatherdata.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        KLog.e(TAG,"观察者模式试验数据------>>>>>"+temperature+"---"+humidity);
    }
}
