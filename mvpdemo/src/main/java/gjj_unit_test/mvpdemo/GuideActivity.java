package gjj_unit_test.mvpdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.android.base.base.BaseActivity;

/**
 * 作者：gjj on 2016/3/17 11:08
 * 邮箱：Gujj512@163.com
 */
public class GuideActivity extends BaseActivity {


    @Bind(R.id.bRetry)
    Button bRetry;
    @Bind(R.id.container)
    FrameLayout container;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.bRetry})
    void onclick(View view) {
        switch (view.getId()) {
            case R.id.bRetry://重新刷新
                break;
        }
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

}
