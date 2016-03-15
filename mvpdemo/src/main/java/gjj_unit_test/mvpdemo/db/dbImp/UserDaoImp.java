package gjj_unit_test.mvpdemo.db.dbImp;

import android.content.Context;

import gjj_unit_test.mvpdemo.bean.User;
import gjj_unit_test.mvpdemo.db.BaseDaoImpl;


/**
 * 作者：gjj on 2016/3/15 15:10
 * 邮箱：Gujj512@163.com
 */
public class UserDaoImp extends BaseDaoImpl<User,Integer> {

    public UserDaoImp(Context context, Class<User> clazz) {
        super(context, clazz);
    }
}
