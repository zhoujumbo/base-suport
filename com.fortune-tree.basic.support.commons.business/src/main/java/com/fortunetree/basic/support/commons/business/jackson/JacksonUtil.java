package com.fortunetree.basic.support.commons.business.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JacksonUtil
 * @Description TODO
 * @Author jb.zhou
 * @Date 2020/1/6
 * @Version 1.0
 */
public class JacksonUtil {
    private static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        // JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Bean.class);
        //如果是Map类型  mapper.getTypeFactory().constructParametricType(HashMap.class,String.class, Bean.class);
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static String toJson(Object obj) throws IOException {
//        StringWriter sw = new StringWriter();
//        JsonGenerator gen = new JsonFactory().createGenerator(sw);
//        mapper.writeValue(gen, obj);
//        gen.close();
//        return sw.toString();
        return mapper.writeValueAsString(obj);
    }

    public static <T> T toBean(String jsonStr, Class<T> objClass)
            throws  IOException {
        return mapper.readValue(jsonStr, objClass);
    }

    public static <T> T toBeanList(String jsonStr, TypeReference<T> jsonTypeReference) throws IOException {
        // List<Bean> beanList = mapper.readValue(jsonString, new TypeReference<List<Bean>>() {});
        return mapper.readValue(jsonStr, jsonTypeReference);
    }

    public static <T> List<T> toBeanList(String jsonString, JavaType javaType) throws IOException {
         return (List<T>)mapper.readValue(jsonString, javaType);
    }

    public static <T> Map<String, T> toStringBeanMap(String jsonString, Class<T> tClass) throws IOException {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class,String.class, tClass);
        return mapper.readValue(jsonString, javaType);
    }


}
