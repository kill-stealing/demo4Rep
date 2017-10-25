package com.mianshi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface FruitColor {
	
	public enum Color {BLUE,RED,GREEN};
	Color fruitColor() default Color.GREEN;
}
