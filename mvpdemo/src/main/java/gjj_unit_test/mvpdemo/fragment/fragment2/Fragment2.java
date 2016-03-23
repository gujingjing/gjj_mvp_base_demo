package gjj_unit_test.mvpdemo.fragment.fragment2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.android.base.base.BaseFragment;
import gjj.android.base.tools.LogUtils;
import gjj.android.base.tools.ToastUtils;
import gjj_unit_test.mvpdemo.R;
import gjj_unit_test.mvpdemo.bean.User;
import gjj_unit_test.mvpdemo.db.dbImp.UserDaoImp;

/**
 * 作者：gjj on 2016/3/7 15:24
 * 邮箱：Gujj512@163.com
 */
public class Fragment2 extends BaseFragment<LinearLayout, Fragment2Model, Fragment2View, Fragment2Present>
        implements Fragment2View {


    @Bind(R.id.loadingView)
    ProgressBar loadingView;
    @Bind(R.id.errorView)
    TextView errorView;
    @Bind(R.id.contentView)
    LinearLayout contentView;
    @Bind(R.id.db_test_save)
    Button dbTestSave;
    @Bind(R.id.db_test_get)
    Button dbTestGet;


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
    @OnClick({R.id.db_test_save,R.id.db_test_get,R.id.dialog_test})void onclick(View view){
        switch (view.getId()){
            case R.id.db_test_save://数据库存储
                testAddArticle();
                break;
            case R.id.db_test_get://数据库获取数据
                testRead();
                break;
            case R.id.dialog_test://dialog弹出测试

                break;
        }
    }
    public void testAddArticle() {
        User u = new User();
        u.setName("gjj");
        u.setPwd("123456");
        u.setId(1);
        UserDaoImp userDao = new UserDaoImp(getActivity(), User.class);
        try {
            if (userDao == null) {
                SnackbarManager.show(Snackbar.with(getActivity())
                .text("user表创建失败"));
                return;
            }
            int result = userDao.save(u);
            if(result>0){
                SnackbarManager.show(Snackbar.with(getActivity())
                        .text("新增user--成功"));
            }else{
                SnackbarManager.show(Snackbar.with(getActivity())
                        .text("新增user--失败"));
            }
            LogUtils.e(result + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testRead() {
        UserDaoImp userDao = new UserDaoImp(getActivity(), User.class);
        if (userDao == null) {
            SnackbarManager.show(Snackbar.with(getActivity())
                    .text("user表创建失败"));
            return;
        }
        try {
            List<User> list = userDao.queryAll();
            Log.e("list.size()=====", list.size() + "");
            for (User user : list) {
                Log.e("user=====", user.toString());
                SnackbarManager.show(Snackbar.with(getActivity())
                        .text(user.toString()));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
