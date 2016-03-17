package gjj_unit_test.mvpdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import gjj.android.base.base.BaseActivity;

/**
 * 作者：gjj on 2016/3/17 11:08
 * 邮箱：Gujj512@163.com
 */
public class GuideActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
    }

    @Override
    public boolean finiActivity() {
        return false;
    }

    @Override
    public boolean setNotifyColor() {
        return true;
    }
}
