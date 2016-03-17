package gjj_unit_test.mvpdemo;

import com.squareup.leakcanary.LeakCanary;

import gjj.android.base.base.BaseApplication;
import gjj.android.base.cash.spCash.SharedPrefsUtil;
import gjj.android.base.tools.LogUtils;

/**
 * 作者：gjj on 2016/3/17 10:56
 * 邮箱：Gujj512@163.com
 */
public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initLogUtils();
        initSPUtils();
        //初始化内存泄漏
        LeakCanary.install(this);

    }

    private void initLogUtils() {
        // 配置日志是否输出(默认true)
        LogUtils.setIfLog(true);

        // 配置日志前缀
        LogUtils.customTagPrefix = "gjj-";
    }
    private void initSPUtils(){
        //初始化配置sp文件,注意，只是配置了，但是有最新的diskCash进行保存
        SharedPrefsUtil.SHARE_SP_NAME="gjj_shareSP";
    }
}
