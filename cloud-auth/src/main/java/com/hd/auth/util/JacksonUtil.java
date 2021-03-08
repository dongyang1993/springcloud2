package com.hd.auth.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author DongYang
 * @Description
 * @Date 2020/9/7 15:13
 **/
public class JacksonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtil.class);

    private static final DateTimeFormatter yyyyMMddHHmms = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.findAndRegisterModules();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(yyyyMMddHHmms));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(yyyyMMddHHmms));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(yyyyMMdd));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(yyyyMMdd));
        mapper.registerModule(javaTimeModule);
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.error("对象转JSON异常", e);
            return null;
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            LOGGER.error("JSON转对象异常", e);
            return null;
        }
    }

    public static <T> T toObject(String json, TypeReference<T> reference) {
        try {
            return mapper.readValue(json, reference);
        } catch (Exception e) {
            LOGGER.error("JSON转对象异常", e);
            return null;
        }
    }

    public static <T> Optional<T> toOptionalObject(String json, TypeReference<T> reference) {
        try {
            return Optional.of(mapper.readValue(json, reference));
        } catch (Exception e) {
            LOGGER.error("JSON转对象异常", e);
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "kangkang");
        map.put("age", null);
        String json = toJson(map);
        System.out.println(json);
        Map<String, String> hashMap = toObject(json, new TypeReference<HashMap<String, String>>() {});
        System.out.println("");
    }
}
