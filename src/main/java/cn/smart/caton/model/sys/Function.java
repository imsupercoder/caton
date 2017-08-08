/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.model.sys;

import cn.smart.caton.annotation.Table;
import cn.smart.caton.model.BaseEntity;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
@Table("Function")
public class Function  extends BaseEntity {

    /**
     * name       db_column: NAME 
     */ 
	private String name;
    /**
     * remark       db_column: REMARK 
     */ 
	private String remark;
    /**
     * module       db_column: MODULE 
     */ 
	private String module;

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Function(){
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
	public String getModule() {
		return this.module;
	}
	public void setModule(String value) {
		this.module = value;
	}

}
