package com.juzi.cqupt.sdk.jwzx.util;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZoneOffset;

/**
 * @author Juzi
 * @since 2019/9/17 13:28
 * Blog https://juzibiji.top
 */
public class LocalDateSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
        } else {
            LocalDate result = (LocalDate) object;
            long timestamp = result.atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            out.writeString(String.valueOf(timestamp));
        }
    }
}
