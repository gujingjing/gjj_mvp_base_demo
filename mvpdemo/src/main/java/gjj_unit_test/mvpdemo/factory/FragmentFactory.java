package gjj_unit_test.mvpdemo.factory;

import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import java.util.HashMap;

import gjj.android.base.base.BaseFragment;
import gjj_unit_test.mvpdemo.fragment.fragment1.Fragment1;
import gjj_unit_test.mvpdemo.fragment.fragment2.Fragment2;
import gjj_unit_test.mvpdemo.fragment.fragment3.Fragment3;

public class FragmentFactory {
	//SparseArray性能优化
	private static SparseArray<BaseFragment> sparseArray=new SparseArray<>();
	private static SparseArray<Long> fragmentCreateTime=new SparseArray<>();//记录创建的时间
	public static BaseFragment createFragment(int arg0){
		BaseFragment baseFragment = null;
		if(sparseArray.get(arg0)!=null){
			baseFragment=sparseArray.get(arg0);
		}else{
			switch (arg0){
				case 0:
					baseFragment = new Fragment1();
					fragmentCreateTime.put(0,System.currentTimeMillis());
					break;
				case 1:
					baseFragment = new Fragment2();
					fragmentCreateTime.put(1,System.currentTimeMillis());
					break;
				case 2:
					baseFragment = new Fragment3();
					fragmentCreateTime.put(2,System.currentTimeMillis());
					break;
				case 3:
					baseFragment = new Fragment2();
					fragmentCreateTime.put(3,System.currentTimeMillis());
					break;
			}
			sparseArray.put(arg0,baseFragment);
		}
		return baseFragment;
	}
	/**
	 * 获取对应fragment创建额时间
	 */
	public static long getFragmentCreateTime(int arg0){
		long time=0;
		if(fragmentCreateTime.get(arg0)<1){//时间不存在
			return time;
		}else{
			return fragmentCreateTime.get(arg0);
		}
	}
}
