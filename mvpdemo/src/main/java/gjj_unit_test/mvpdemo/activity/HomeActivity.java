package gjj_unit_test.mvpdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import gjj.android.base.base.BaseActivity;
import gjj_unit_test.mvpdemo.R;

/**
 * 作者：gjj on 2016/3/4 16:36
 * 邮箱：Gujj512@163.com
 */
public class HomeActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    public boolean finiActivity() {
        return false;
    }

    @Override
    public boolean setNotifyColor() {
        return false;
    }
}
