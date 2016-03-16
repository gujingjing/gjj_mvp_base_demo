package gjj_unit_test.mvpdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.DatabaseTableConfigUtil;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import gjj_unit_test.mvpdemo.bean.User;

/**
 * Helper类，提供单例Helper
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "android_test.db";
    //数据库名
    private static final int DB_VERSION = 1;
    //数据库版本
    private static DatabaseHelper mInstance;

    //Helper单例
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //创建表
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     SQLiteOpenHelper类的构造函数有一个参数是int version，它的意思就是指数据库版本号。比如在应用1.0版本中，我们使用
     SQLiteOpenHelper访问数据库时，该参数为1，那么数据库版本号1就会写在我们的数据库中。到了1.1版本，我们的数据库需要发生变化，
     那么我们1.1版本的程序中就要使用一个大于1的整数来构造SQLiteOpenHelper类，用于访问新的数据库，
     比如2。当我们的1.1新程序读取1.0版本的老数据库时，就发现老数据库里存储的数据库版本是1，而我们新程序访问它时填的版本号为2，系统就知道数据库需要升级。

     总结:当我们的1.1新程序读取1.0版本的老数据库时，就发现老数据库里存储的数据库版本是1，而我们新程序访问它时填的版本号为2，系统就知道数据库需要升级。
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //更新表
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 双重加锁检查
     *
     * @param context 上下文
     * @return 单例
     */
    public static DatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DatabaseHelper.class) {
                if (mInstance == null) {
                    mInstance = new DatabaseHelper(context);
                }
            }
        }
        return mInstance;
    }

    /*private static Object mHistoryObject = new Object();
    private static BaseDao<History, Integer> mHistoryDao;

    public static BaseDao<History, Integer> getHistoryDao(Context context) {
        if (mHistoryDao == null) {
            synchronized (mHistoryObject) {
                if (mHistoryDao == null) {
                    mHistoryDao = new HistoryDao(context, History.class);
                }
            }

        }
        return mHistoryDao;
    }

    private static Object mNewsItemObject = new Object();
    private static BaseDao<NewsItem, Integer> mNewsItemDao;

    public static BaseDao<NewsItem, Integer> getNewsItemDao(Context context) {
        if (mNewsItemDao == null) {
            synchronized (mNewsItemObject) {
                if (mNewsItemDao == null) {
                    mNewsItemDao = new NewsItemDao(context, NewsItem.class);
                }
            }
        }
        return mNewsItemDao;
    }*/

    public <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws SQLException {
        // lookup the dao, possibly invoking the cached database config
        Dao<T, ?> dao = DaoManager.lookupDao(connectionSource, clazz);
        if (dao == null) {
            // try to use our new reflection magic
            DatabaseTableConfig<T> tableConfig = DatabaseTableConfigUtil.fromClass(connectionSource, clazz);
            if (tableConfig == null) {
                /**
                 * TODO: we have to do this to get to see if they are using the deprecated annotations like
                 * {@link DatabaseFieldSimple}.
                 */
                dao = (Dao<T, ?>) DaoManager.createDao(connectionSource, clazz);
            } else {
                dao = (Dao<T, ?>) DaoManager.createDao(connectionSource, tableConfig);
            }
        }

        @SuppressWarnings("unchecked")
        D castDao = (D) dao;
        return castDao;
    }

    public void close(){
        if(mInstance!=null){
            OpenHelperManager.releaseHelper();
            mInstance = null;
        }
    }
}