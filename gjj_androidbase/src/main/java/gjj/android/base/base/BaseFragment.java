package gjj.android.base.base;

import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.layout.MvpViewStateFrameLayout;

/**
 * 作者：gjj on 2016/3/4 15:41
 * 邮箱：Gujj512@163.com
 * MvpViewStateFrameLayout---这是一个自定义的控件，里面实现数据加载显示---可以不用
 *MvpFragment----不使用loading加载的原始fragment
 */
public abstract class BaseFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends MvpLceFragment<CV,M,V,P> implements MvpLceView<M>{
}
