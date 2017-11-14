package cn.sxh.snowfox.UI.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.sxh.snowfox.CurrentConditionsDisplay;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.WeatherData;

public class PaintActivity extends AppCompatActivity {
    private WeatherData weatherData;
    private CurrentConditionsDisplay weatherDataDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        init();
    }

    private void init() {
        weatherData = new WeatherData();
        weatherDataDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(80,65,30.4f);
//        weatherDataDisplay.display();
    }
}
