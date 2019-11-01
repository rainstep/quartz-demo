package com.example.quartzdemo.web;

import com.example.quartzdemo.util.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String strDate) {
        if (StringUtils.isBlank(strDate)) return null;
        String datePattern;
        if (strDate.contains("-")) {
            if (strDate.contains(":")) {
                datePattern = DateFormatUtils.DATE_TIME_PATTERN;
            } else {
                datePattern = DateFormatUtils.DATE_PATTERN;
            }
        } else if (strDate.contains("/")) {
            if (strDate.contains(":")) {
                datePattern = DateFormatUtils.DATE_TIME_PATTERN_TWO;
            } else {
                datePattern = DateFormatUtils.DATE_PATTERN_TWO;
            }
        } else {
            return null;
        }
        Date date = DateFormatUtils.parse(strDate, datePattern);
        return date;
    }
}
