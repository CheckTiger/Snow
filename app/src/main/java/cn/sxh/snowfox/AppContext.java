package cn.sxh.snowfox;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;

import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Arrays;
import java.util.List;

import cn.sxh.greendao.DaoMaster;
import cn.sxh.greendao.DaoSession;
import cn.sxh.greendao.TechnologyTable;
import cn.sxh.greendao.TechnologyTableDao;
import cn.sxh.snowfox.common.Constants;
import cn.sxh.snowfox.di.component.ApplicationComponent;
import cn.sxh.snowfox.di.component.DaggerApplicationComponent;
import cn.sxh.snowfox.di.module.ApplicationModule;

/**
 * Created by snow on 2017/8/8.
 */

public class AppContext extends Application {

    private static AppContext instance;
    protected AppConfig config;
    private ApplicationComponent mApplicationComponent;
    private RefWatcher refWatcher;
    private static DaoSession mDaoSession;

    public static RefWatcher getRefWatcher(Context context) {
        AppContext appContext = (AppContext) context.getApplicationContext();
        return appContext.refWatcher;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = (AppContext) getApplicationContext();
        initLeakCanary();
        initStrictMode();
        KLog.init(BuildConfig.DEBUG);
        MultiTypeInstaller.start();
        config = AppConfig.getConfig(getInstance());
        setupDatabase();
        initApplicationComponent();
        initDB();
    }

    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            refWatcher = LeakCanary.install(this);
        } else {
            refWatcher = installLeakCanary();
        }
    }
    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .penaltyLog() // 在logcat中打印违规异常信息
                            .build());
            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog()
                            .build());
        }
    }
    private RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }

    public static AppContext getInstance(){return instance;}

    /**
     * 初始化ApplicationComponent
     */
    private void initApplicationComponent(){
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }

    private void setupDatabase(){
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
        QueryBuilder.LOG_SQL = BuildConfig.DEBUG;
        QueryBuilder.LOG_VALUES = BuildConfig.DEBUG;
    }

    public static TechnologyTableDao getTechnologyTableDao(){
        return mDaoSession.getTechnologyTableDao();
    }
    
    public static void initDB(){
        if (!AppConfig.getDB(instance, Constants.SNOW_FOX, Constants.INIT_DB)) {
            TechnologyTableDao dao = getTechnologyTableDao();
            List<String> technologyName = Arrays.asList(AppContext.getInstance().getResources()
                    .getStringArray(R.array.order_titles));
            List<String> technologyId = Arrays.asList(AppContext.getInstance().getResources().getStringArray(R.array.order_titles_id));
            for (int i = 0; i < technologyName.size(); i++) {
                TechnologyTable entity = new TechnologyTable(technologyName.get(i),technologyId.get(i),i+"");
                dao.insert(entity);
            }
            AppConfig.isInitDB(instance, Constants.SNOW_FOX, Constants.INIT_DB, true);
        }
    }
}
