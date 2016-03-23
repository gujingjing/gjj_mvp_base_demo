package gjj.android.base.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import gjj.android.base.R;
import gjj.android.base.manager.AppManager;
import gjj.android.base.manager.SystemBarTintManager;
import gjj.android.base.view.swipClassView.SwipeBackLayout;
import gjj.android.base.tools.NetWorkUtil;
import gjj.android.base.tools.ToastUtils;

/**
 * 作者：gjj on 2016/3/4 13:43
 * 邮箱：Gujj512@163.com
 * MvpActivity  最原始的mvp模式activity
 * MvpLceActivity   使用loading加载的activity
 *MvpViewStateActivity  查看mvpView的状态使用的
 * MvpLceViewStateActivity  使用loading加载,查看mvpView的状态的activity
 */
public abstract class MvpBaseActivity  <CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends MvpLceActivity<CV, M, V, P> implements MvpLceView<M> {

    public MvpBaseActivity context;
    protected SwipeBackLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //顺序很重要，必须先设置是否需要滑动activity----然后在设置顶部标题栏的颜色
        if (finiActivity()) {
            //是否需要滑动消除界面的主要代码
            layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
                    R.layout.activity_base, null);
            layout.attachToActivity(this);
        }
        if(!setNotifyColor()){
            setStateBarColor(R.color.colorPrimary);//设置最顶部标题栏颜色
        }
        context = this;
        AppManager.getAppManager().addActivity(this);
    }
    /**
     * 是否需要右滑删除界面
     */
    public abstract boolean finiActivity();
    /**
     * 是否需要设置导航栏的颜色
     */
    public abstract boolean setNotifyColor();
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.base_slide_right_out);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    // Press the back button in mobile phone
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.base_slide_right_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除activity的时候使用AppManager.finishActivity();
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 当没有网络的时候
     */
    public void onDisConnect() {
//        ToastUtils.setToastShot(context, "暂时没有网络,请稍后重试");
        SnackbarManager.show(
                Snackbar.with(context)
                        .text("暂时没有网络,请稍后重试"));
    }

    /**
     * 当网络连接的时候
     * 获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap 网络3：net网络
     * wifi, CMNET, CMWAP, noneNet
     */
    public void onConnect(NetWorkUtil.NetType type) {
        String netType="";
        ToastUtils.setToastShot(context, "网络已经连接");
        switch (type){
            case wifi:
                netType="当前网络状态为wifi网络";
                break;
            case CMNET:
            case CMWAP:
                netType="当前网络状态为手机网络";
                break;
            case noneNet:
                netType="当前没有网络,请连接网络";
                break;

        }
        SnackbarManager.show(
                Snackbar.with(context)
                        .text(netType));
    }
    protected void setStateBarColor(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(resId);
            tintManager.setStatusBarDarkMode(true, this);
        }
    }
}
