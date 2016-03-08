package gjj_unit_test.mvpdemo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import gjj_unit_test.mvpdemo.R;
import gjj_unit_test.mvpdemo.header.ProgressHeaderViewNew;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * 作者：gjj on 2016/3/8 16:38
 * 邮箱：Gujj512@163.com
 */
public class BaseFragmentDemo extends Fragment implements Toolbar.OnMenuItemClickListener {

    AppCompatActivity compatActivity;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.loadingView)
    ProgressBar loadingView;
    @Bind(R.id.errorView)
    TextView errorView;
    @Bind(R.id.contentView)
    PtrFrameLayout frame;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        compatActivity = (AppCompatActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefresh();
        initTotalBar();
    }

    public void initTotalBar() {
        setHasOptionsMenu(true);
        // App Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("My Title");
        // Sub Title
        toolbar.setSubtitle("Sub title");

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        // Navigation Icon 要設定在 setSupoortActionBar 才有作用
        // 否則會出現 back bottom
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        // Menu item click 的監聽事件一樣要設定在 setSupportActionBar 才有作用
        toolbar.setOnMenuItemClickListener(this);
    }

    public void initRefresh() {
        // header
//        final RentalsSunHeaderView header = new RentalsSunHeaderView(getContext());
//        final ProgressHeaderView header=new ProgressHeaderView(getContext());//github旋转的动画
        final ProgressHeaderViewNew header = new ProgressHeaderViewNew(getActivity());//可以控制的加载动画
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, PtrLocalDisplay.dp2px(10));
//        header.setUp(frame);

//        frame.setLoadingMinTime(1000);
//        frame.setDurationToCloseHeader(1500);
        frame.setHeaderView(header);
        frame.addPtrUIHandler(header);

        // the following are default settings
//        frame.setResistance(1.4f);
//        frame.setRatioOfHeaderHeightToRefresh(1.2f);
//        frame.setDurationToClose(200);
//        frame.setDurationToCloseHeader(1000);

        // default is true
        frame.setKeepHeaderWhenRefresh(true);
        //true表示下拉刷新,false表示释放刷新
        frame.setPullToRefresh(false);
//        frame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                frame.autoRefresh(true);//可以再次刷新
//            }
//        }, 100);


        frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();//3秒后刷新完成
                    }
                }, 3000);
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
//        .getMenuInflater().inflate(R.menu.menu_main, menu);//正常的activity用这个
        inflater.inflate(R.menu.menu_main, menu);//fragment用这个
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
//                break;
            case R.id.action_share:
//                break;
            case R.id.action_settings:
                SnackbarManager.show(
                        Snackbar.with(getActivity())
                                .text(item.getTitle()));
                break;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
