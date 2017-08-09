/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.model.sys;

import cn.smart.caton.annotation.DBExclude;
import cn.smart.caton.annotation.Table;
import cn.smart.caton.model.BaseEntity;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
@Table("Dept")
public class Dept  extends BaseEntity {

    /**
     * name       db_column: NAME 
     */ 
	private String name;
    /**
     * valid       db_column: VALID 
     */ 
	private String valid;
    /**
     * parentid       db_column: PARENTID 
     */ 
	private String parentId;

	@DBExclude
	private String parentName;

	public Dept(){
	}
	public String getName() {
		return this.name;
	}
	public void setName(String value) {
		this.name = value;
	}
	public String getValid() {
		return this.valid;
	}
	public void setValid(String value) {
		this.valid = value;
	}
	public String getParentId() {
		return this.parentId;
	}
	public void setParentId(String value) {
		this.parentId = value;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
