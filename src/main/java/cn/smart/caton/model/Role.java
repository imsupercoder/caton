/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.model;

import cn.smart.caton.annotation.Table;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
@Table("Role")
public class Role  extends BaseEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
    /**
     * name       db_column: NAME 
     */ 
	private String name;
    /**
     * remark       db_column: REMARK 
     */ 
	private String remark;

	public Role(){
	}
	public String getName() {
		return this.name;
	}
	public void setName(String value) {
		this.name = value;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}

}
