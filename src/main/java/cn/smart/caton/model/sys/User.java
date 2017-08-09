package cn.smart.caton.model.sys;

import cn.smart.caton.annotation.DBExclude;
import cn.smart.caton.annotation.Table;
import cn.smart.caton.model.BaseEntity;

/**
 * Created by user on 2017/7/6.
 */
@Table("USER")
public class User extends BaseEntity {

    private String userCode;
    private String userName;
    private String password;
    private String gender;
    @DBExclude
    private String roleName;
    @DBExclude
    private String roleId;

    private String deptId;
    @DBExclude
    private String deptName;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
