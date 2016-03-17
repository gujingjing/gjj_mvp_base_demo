package gjj_unit_test.mvpdemo.view.guideView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class GuideViewPager extends ViewPager {
    private int childId;
    float mStartDragX;
    OnSwipeOutListener mListener;

    public GuideViewPager(Context context) {
        super(context);
    }

    public GuideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnSwipeOutListener(OnSwipeOutListener listener) {
        mListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCurrentItem() == getAdapter().getCount() - 1) {
            float x = event.getX();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mStartDragX = x;
                    return true;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    if (mStartDragX > x) {
                        super.setCurrentItem(getCurrentItem() - 1);
                    } else {
                        mListener.onSwipeOutAtEnd();
                    }
                    return false;
                default:
                    return super.onTouchEvent(event);
            }
        } else {
            return super.onTouchEvent(event);
        }
    }

    public interface OnSwipeOutListener {
        void onSwipeOutAtEnd();
    }

    public void setChildId(int id) {
        this.childId = id;
    }
}
