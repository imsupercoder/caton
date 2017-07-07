package cn.smart.caton.model;

import cn.smart.caton.annotation.Column;
import cn.smart.caton.annotation.Table;

import java.io.Serializable;

/**
 * Created by user on 2017/7/6.
 */
@Table("USER")
public class User implements Serializable {

    @Column("ID")
    private String id;
    @Column("USERNAME")
    private String username;
    @Column("PASSWORD")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
