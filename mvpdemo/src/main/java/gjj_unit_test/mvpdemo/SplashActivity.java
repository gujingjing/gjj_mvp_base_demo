package gjj_unit_test.mvpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import gjj.android.base.base.BaseActivity;
import gjj.android.base.cash.spCash.SharedPrefsUtil;
import gjj_unit_test.mvpdemo.activity.HomeActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToActivity();
    }

    @Override
    public boolean finiActivity() {
        return false;
    }

    @Override
    public boolean setNotifyColor() {
        return true;
    }

    @Override
    public boolean setNotifyNetSatte() {
        return true;
    }

    public void goToActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                //判断是否是第一次加载
//                if(!SharedPrefsUtil.getBoolean(SplashActivity.this,AppParmers.IF_HASE_OPEN)){
//                    intent = new Intent(SplashActivity.this, GuideActivity.class);//进入引导界面
//                }else{
                    intent = new Intent(SplashActivity.this, HomeActivity.class);
//                }
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
