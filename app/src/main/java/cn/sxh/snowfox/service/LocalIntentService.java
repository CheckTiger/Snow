package cn.sxh.snowfox.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.socks.library.KLog;

/**
 * @package-name: cn.sxh.snowfox.service
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/4/9 0009 : 20 :14
 * @project-name: Snow
 */

public class LocalIntentService extends IntentService {

    private static final String TAG = LocalIntentService.class.getSimpleName();


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public LocalIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getStringExtra("task_action");
        KLog.e("sxh", "receive the task --->>>" + action);
        if (action.equals("sxh")) {
            KLog.e("sxh", "handle the task --->>>" + action);
        }
    }

    @Override
    public void onDestroy() {
        KLog.e("sxh","service is dead-->>>"+TAG);
        super.onDestroy();
    }
}
