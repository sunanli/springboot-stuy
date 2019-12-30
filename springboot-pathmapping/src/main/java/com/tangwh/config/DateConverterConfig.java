package com.tangwh.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//组件 日期类型转换器
@Component
public class DateConverterConfig implements Converter<String, Date> {


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String souce) {
        System.out.println("进入日期类型转换器");

        if (souce !=null && "".equals(souce)){
            try {
                return sdf.parse(souce);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
