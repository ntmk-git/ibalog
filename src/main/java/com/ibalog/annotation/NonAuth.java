package com.ibalog.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * loginInterceptor�p
 * ���O�C�����s�v��Method�ڈ�p�A�m�e�[�V����
 * @author ntmk
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface NonAuth {

}
