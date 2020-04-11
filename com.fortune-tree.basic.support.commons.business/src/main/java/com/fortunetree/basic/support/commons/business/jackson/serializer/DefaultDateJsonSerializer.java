package com.fortunetree.basic.support.commons.business.jackson.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * json序列化Date类型为'yyyy-MM-dd HH:mm:ss'格式
 * 
 * @author zy.wu
 * 
 */
public class DefaultDateJsonSerializer extends JsonSerializer<Date> {
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		jsonGenerator.writeString(simpleDateFormat.format(date));
	}

}
