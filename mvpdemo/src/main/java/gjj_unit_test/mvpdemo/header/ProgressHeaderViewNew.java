package gjj_unit_test.mvpdemo.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import gjj_unit_test.mvpdemo.R;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 作者：gjj on 2016/2/15 15:29
 * 邮箱：Gujj512@163.com
 */
public class ProgressHeaderViewNew extends LinearLayout implements PtrUIHandler {

    private ImageView animationIV;
    private Context context;
    private AnimationDrawable animationDrawable;
    public ProgressHeaderViewNew(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public ProgressHeaderViewNew(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    public void init(){
        View view= View.inflate(context, R.layout.progress_view_new,null);
        animationIV= (ImageView) view.findViewById(R.id.animationIV);
        LayoutParams viewsParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                                    ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(viewsParam);
        this.addView(view);
    }
    @Override
    public void onUIReset(PtrFrameLayout frame) {
        animationDrawable = (AnimationDrawable) animationIV.getDrawable();
        animationDrawable.stop();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        animationDrawable = (AnimationDrawable) animationIV.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        animationDrawable = (AnimationDrawable) animationIV.getDrawable();
        animationDrawable.stop();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }
}
