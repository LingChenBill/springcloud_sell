package com.lc.user.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Json工具类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/14
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转换成json字符串.
     * @param object
     * @return
     */
    public static String toJson(Object object) {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * json转换对象.
     * @param str
     * @param classType
     * @return
     */
    public static Object fromJson(String str, Class classType) {
        try {
            return objectMapper.readValue(str, classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转换对象(List<>对象).
     * @param str
     * @param typeReference
     * @return
     */
    public static Object fromJson(String str, TypeReference typeReference) {
        try {
            return objectMapper.readValue(str, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
