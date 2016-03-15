package gjj_unit_test.mvpdemo.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import gjj.android.base.db.BaseDao;

/**
 * Created by Administrator on 2015/10/23 0023.
 */
public class BaseDaoImpl<T,Integer> extends BaseDao<T,Integer> {

    private Dao<T, Integer> mDao;
    private Class<T> clazz;
    private DatabaseHelper mDatabaseHelper;

    public BaseDaoImpl(Context context, Class<T> clazz) {
        this.clazz=clazz;
        mDatabaseHelper = DatabaseHelper.getInstance(context);
    }

    @Override
    public Dao<T, Integer> getDao() throws SQLException {
        if (null == mDao){
            mDao = mDatabaseHelper.getDao(clazz);
        }
        return mDao;
    }
}
