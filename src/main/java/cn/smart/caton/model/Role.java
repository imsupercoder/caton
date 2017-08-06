/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.model;

import cn.smart.caton.annotation.DBExclude;
import cn.smart.caton.annotation.Table;

import java.util.List;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
@Table("Role")
public class Role  extends BaseEntity{

    /**
     * name       db_column: NAME 
     */ 
	private String name;
    /**
     * remark       db_column: REMARK 
     */ 
	private String remark;

	@DBExclude
	private List<Function> functions;

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

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
}
