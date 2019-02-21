package com.loncom.ismac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * * @author 作者 E-mail: youtao
 * 
 * @date 创建时间：2017年7月24日 上午11:26:40
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Attachment {

	String KEY() default "";

	boolean ISENABLE() default true;

	String TABLE() default "";
	
	boolean UPDATE() default false;
	
	
	boolean DELETE() default false;
	
	String  TEMPLATE() default "%s like '%s'";
	
	String SQL() default "";
	
	boolean UPDATENULL() default false;
	
	String COLUMNNAME() default "";
	
	boolean ISNULL() default false;
	
}
