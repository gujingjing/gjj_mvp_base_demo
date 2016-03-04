package gjj.android.base.base;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * 作者：gjj on 2016/3/4 15:24
 * 邮箱：Gujj512@163.com
 * MvpNullObjectBasePresenter   -----设个比mvpBasePresenter好一点，解决了为空的异常
 */
public abstract class BasePresenter<V extends MvpView> extends MvpNullObjectBasePresenter<V> {

}
