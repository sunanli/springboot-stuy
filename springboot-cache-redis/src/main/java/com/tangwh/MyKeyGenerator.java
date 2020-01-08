package com.tangwh;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 13:42
 */
@Component
public class MyKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {

        return method.getName()+":"+ Arrays.toString(objects);
    }
}
