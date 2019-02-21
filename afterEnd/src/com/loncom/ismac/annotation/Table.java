package com.loncom.ismac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** * @author  作者 E-mail: youtao
* @date 创建时间：2017年7月25日 下午2:09:36 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public abstract @interface Table  {
	public abstract String NAME() default "";
	
	public  abstract String ORDER() default "";
}
