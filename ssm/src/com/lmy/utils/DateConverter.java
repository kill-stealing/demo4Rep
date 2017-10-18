package com.lmy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ParseException;

class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

