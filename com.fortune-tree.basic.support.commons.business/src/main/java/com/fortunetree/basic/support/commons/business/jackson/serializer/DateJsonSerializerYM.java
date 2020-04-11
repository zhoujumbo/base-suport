package com.fortunetree.basic.support.commons.business.jackson.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * json序列化Date类型为'yyyy-MM'格式
 * 
 * @author zy.wu
 * 
 */
public class DateJsonSerializerYM extends JsonSerializer<Date> {
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM");

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		String dateStr = (date != null) ? simpleDateFormat.format(date) : "";
		jsonGenerator.writeString(dateStr);
	}

}
