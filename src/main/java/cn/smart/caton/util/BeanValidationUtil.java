/** 
 * @Title:TODO  
 * @Desription:TODO
 * @Company:CSN
 * @ClassName:BeanValidationUtil.java
 * @Author:yezeping
 * @CreateDate:2017年1月9日 上午11:28:58 
 * @UpdateUser:yezeping  
 * @Version:0.1 
 */
package cn.smart.caton.util;


import cn.smart.caton.exceptions.BeanValidatorException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BeanValidationUtil {
	
	public  static Validator validator;
	/**
	 * 服务端参数有效性验证
	 * 
	 * @param object
	 * @param groups
	 * @return
	 */
	public static void beanValidator(Object object, Class<?>... groups) {
		StringBuffer errorMsg = new StringBuffer();
		try {
			Set constraintViolations = validator.validate(object, groups);
			if (!constraintViolations.isEmpty()) {
				throw new ConstraintViolationException(constraintViolations);
			}
		} catch (ConstraintViolationException ex) {
			List<String> errorMessages = new ArrayList<String>();
			for (ConstraintViolation violation : ex.getConstraintViolations()) {
				errorMessages.add(violation.getPropertyPath() + ": " + violation.getMessage());
			}
			errorMsg.append("数据验证失败：");
			for (String string : errorMessages) {
				errorMsg.append(string);
				errorMsg.append(",");
			}
			errorMsg.deleteCharAt(errorMsg.length() - 1);
			throw new BeanValidatorException(errorMsg.toString());
		}
	}
	public  Validator getValidator() {
		return validator;
	}
	public  void setValidator(Validator validator) {
		BeanValidationUtil.validator = validator;
	}
	
	
}

	