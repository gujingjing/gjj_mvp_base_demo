package gjj_unit_test.mvpdemo.fragment.fragment3;

import gjj.android.base.base.BasePresenter;

/**
 * 作者：gjj on 2016/3/7 15:36
 * 邮箱：Gujj512@163.com
 */
public class Fragment3Present extends BasePresenter<Fragment3View> {

    public void refreshView(final boolean pullToRefresh){
        getView().showLoading(pullToRefresh);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                ToastUtils.setToastShot(context,"刷新数据成功");
                getView().showContent();
//                getView().showError(new Throwable("fdfds"),pullToRefresh);
            }
        }, 3000);
    }
}
