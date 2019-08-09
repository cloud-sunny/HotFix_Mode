package test.fixhot.com;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.hotfix.library.utils.FixDexUtil;

/**
 * @author sunxiaoyun
 * @description $
 * @time 19/6/4
 */
public class BaseApplication extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        FixDexUtil.loadFixedDex(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
