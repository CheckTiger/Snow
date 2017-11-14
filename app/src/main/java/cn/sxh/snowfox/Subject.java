package cn.sxh.snowfox;


/**
 * Created by Administrator on 2017/11/14 0014.
 * 主题接口
 */

public interface Subject {
     void registerObserver(Observer o);
     void removeObserver(Observer o);
     void notifyObservers();
}
