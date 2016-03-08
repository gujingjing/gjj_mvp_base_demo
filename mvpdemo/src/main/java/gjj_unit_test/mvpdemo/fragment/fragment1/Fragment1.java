package gjj_unit_test.mvpdemo.fragment.fragment1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import gjj.android.base.base.BaseFragment;
import gjj_unit_test.mvpdemo.R;
import gjj_unit_test.mvpdemo.header.ProgressHeaderViewNew;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * 作者：gjj on 2016/3/7 15:24
 * 邮箱：Gujj512@163.com
 */
public class Fragment1 extends BaseFragment<PtrFrameLayout, Fragment1Model, Fragment1View, Fragment1Present>
        implements Fragment1View {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.contentView)
    PtrFrameLayout frame;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefresh();
        loadData(false);
    }

    public void initRefresh(){
        // header
//        final RentalsSunHeaderView header = new RentalsSunHeaderView(getContext());
//        final ProgressHeaderView header=new ProgressHeaderView(getContext());//github旋转的动画
        final ProgressHeaderViewNew header=new ProgressHeaderViewNew(getActivity());//可以控制的加载动画
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
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public Fragment1Present createPresenter() {
        return new Fragment1Present();
    }

    @Override
    public void setData(Fragment1Model data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.refreshView(pullToRefresh);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showContent() {
        super.showContent();
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);

    }
}
