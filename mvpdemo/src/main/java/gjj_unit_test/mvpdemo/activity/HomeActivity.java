package gjj_unit_test.mvpdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.android.base.base.BaseActivity;
import gjj.android.base.base.BaseFragment;
import gjj.android.base.manager.AppManager;
import gjj.android.base.view.viewpager.MyViewPager;
import gjj_unit_test.mvpdemo.R;
import gjj_unit_test.mvpdemo.factory.FragmentFactory;
import gjj_unit_test.mvpdemo.fragment.fragment1.Fragment1;
import gjj_unit_test.mvpdemo.fragment.fragment1.MainFragmentAdapter;

/**
 * 作者：gjj on 2016/3/4 16:36
 * 邮箱：Gujj512@163.com
 */
public class HomeActivity extends BaseActivity {
    @Bind(R.id.contentView)
    MyViewPager viewpager;
    @Bind(R.id.me_0)
    RadioButton me0;
    @Bind(R.id.me_1)
    RadioButton me1;
    @Bind(R.id.me_2)
    RadioButton me2;
    @Bind(R.id.me_3)
    RadioButton me3;
    @Bind(R.id.main_select)
    RadioGroup mainSelect;
    @Bind(R.id.main_table_item)
    LinearLayout mainTableItem;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private List<BaseFragment> fragments = new ArrayList<>();
    private MainFragmentAdapter adapter;
    private long exitTime = 0;
    private Fragment1 fragment1,fragment2,fragment3,fragment4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    @OnClick({})
    void onclicl(View view) {
        switch (view.getId()) {

        }
    }

    public void initData() {
        initFragment();
        initViewPager();
    }
    public void initViewPager(){
        adapter=new MainFragmentAdapter(getSupportFragmentManager(),fragments);
        viewpager.setOffscreenPageLimit(fragments.size());//设置每次缓存的次数
//        viewpager.setPageTransformer(true, new ZoomOutPageTransformer());//设置viewpager翻页的动画
//        controlViewPagerSpeed();//设置viewpager滑动速度
        viewpager.setAdapter(adapter);

        //setCurrentItem第二个参数true,表示页面划过去,false 不划过去
        viewpager.setCurrentItem(0, false);
    }
    public void initFragment() {
        fragments.add(FragmentFactory.createFragment(0));
        fragments.add(FragmentFactory.createFragment(1));
        fragments.add(FragmentFactory.createFragment(2));
        fragments.add(FragmentFactory.createFragment(3));
    }

    public void initListener() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_original1:
//                        break;
                    case R.id.navigation_original2:
//                        break;
                    case R.id.navigation_original3:
//                        break;
                    case R.id.navigation_original4:
                        item.setChecked(true);
                        SnackbarManager.show(
                                Snackbar.with(context)
                                        .text(item.getTitle()));
                        break;
                    case R.id.navigation_original5:
                        SnackbarManager.show(
                                Snackbar.with(context)
                                        .text(item.getTitle()));
                        break;
                    case R.id.image:
                        SnackbarManager.show(
                                Snackbar.with(context)
                                        .text("点击了图片"));
                        break;
                }
                return false;
            }
        });
        //radioGroup设置点击事件
        mainSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) group.findViewById(id);
                String page = (String) rb.getTag();
                long time=FragmentFactory.getFragmentCreateTime(Integer.parseInt(page));

                //setCurrentItem第二个参数true,表示页面划过去,false 不划过去
                viewpager.setCurrentItem(Integer.parseInt(page), false);

                if(System.currentTimeMillis()-time>=10*60*1000){//大于10分钟的刷新
                    fragments.get(Integer.parseInt(page)).loadData(false);
                }
            }
        });
    }

    @Override
    public boolean finiActivity() {
        return false;
    }

    @Override
    public boolean setNotifyColor() {
        return false;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getAppManager().AppExit(context);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
