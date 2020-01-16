package com.changyue.j2eefinal.utils;


import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 袁阊越
 * @title: DateConverters
 * @package com.changyue.springwork9.utils
 * @description: 日期转换转换器
 * @date 2019/12/3/003
 */
public class DateConverters implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
