package gjj_unit_test.mvpdemo.header;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import gjj_unit_test.mvpdemo.R;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 作者：gjj on 2016/2/15 15:29
 * 邮箱：Gujj512@163.com
 */
public class ProgressHeaderView extends LinearLayout implements PtrUIHandler {

    private Context context;
    public ProgressHeaderView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public ProgressHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    public void init(){
        View view= View.inflate(context, R.layout.progress_view,null);
        LayoutParams viewsParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                                    ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(viewsParam);
        this.addView(view);
    }
    @Override
    public void onUIReset(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }
}
