package gjj_unit_test.mvpdemo.fragment.fragment2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import gjj.android.base.base.BaseFragment;
import gjj_unit_test.mvpdemo.R;

/**
 * 作者：gjj on 2016/3/7 15:24
 * 邮箱：Gujj512@163.com
 */
public class Fragment2 extends BaseFragment<LinearLayout, Fragment2Model, Fragment2View, Fragment2Present>
        implements Fragment2View{


    @Bind(R.id.loadingView)
    ProgressBar loadingView;
    @Bind(R.id.errorView)
    TextView errorView;
    @Bind(R.id.contentView)
    LinearLayout contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData(false);
    }



    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public Fragment2Present createPresenter() {
        return new Fragment2Present();
    }


    @Override
    public void setData(Fragment2Model data) {

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
