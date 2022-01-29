package com.techmarket.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

	public static Date getDate(String dateString) throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			LOGGER.error("Erro ao formatar data recebida: ", e);
		}
		
		return date;
		
	}
	
	public static Timestamp dateNowTimestamp() {
		
    	Long datetime = System.currentTimeMillis();
    	Timestamp timestampDateNow = new Timestamp(datetime);
    	return timestampDateNow;
    	
	}

}
