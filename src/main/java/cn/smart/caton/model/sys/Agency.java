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
@Table("Agency")
public class Agency  extends BaseEntity {
	private static final long serialVersionUID = 5454155825314635342L;
	
    /**
     * name       db_column: NAME 
     */ 
	private String name;
    /**
     * city       db_column: CITY 
     */ 
	private String city;
    /**
     * valid       db_column: VALID 
     */ 
	private String valid;
    /**
     * priceflag       db_column: PRICEFLAG 
     */ 
	private String priceflag;
    /**
     * remark       db_column: REMARK 
     */ 
	private String remark;

	public Agency(){
	}
	public String getName() {
		return this.name;
	}
	public void setName(String value) {
		this.name = value;
	}
	public String getCity() {
		return this.city;
	}
	public void setCity(String value) {
		this.city = value;
	}
	public String getValid() {
		return this.valid;
	}
	public void setValid(String value) {
		this.valid = value;
	}
	public String getPriceflag() {
		return this.priceflag;
	}
	public void setPriceflag(String value) {
		this.priceflag = value;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}

}
