package cn.smart.caton.model;

import cn.smart.caton.annotation.Table;

/**
 * Created by user on 2017/7/6.
 */
@Table("USER")
public class User extends BaseEntity {

    private String userName;
    private String password;
    private String gender;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
