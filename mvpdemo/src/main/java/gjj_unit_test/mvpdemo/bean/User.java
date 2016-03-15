package gjj_unit_test.mvpdemo.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 作者：gjj on 2016/3/10 16:36
 * 邮箱：Gujj512@163.com
 */
//首先在User类上添加@DatabaseTable(tableName = "tb_user")，标明这是数据库中的一张表，标明为tb_user
@DatabaseTable(tableName = "tb_user")
public class User {

    public User(){
    }
    public User(String name, String pwd){
        this.name = name;
        this.pwd = pwd;
    }

    //@DatabaseField(generatedId = true) ，generatedId 表示id为主键且自动生成
    @DatabaseField(generatedId = true)
    private int id;

    //然后分别在属性上添加@DatabaseField(columnName = "name") ，columnName的值为该字段在数据中的列名
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "pwd")
    private String pwd;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
