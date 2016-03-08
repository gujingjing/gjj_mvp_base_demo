package gjj_unit_test.mvpdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import gjj.android.base.base.BaseFragment;


/**
 * 作者：gjj on 2016/1/4 21:52
 * 邮箱：xjs250@163.com
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    public MainFragmentAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
