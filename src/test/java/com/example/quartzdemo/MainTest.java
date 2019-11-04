package com.example.quartzdemo;

import com.example.quartzdemo.util.DateFormatUtils;
import com.example.quartzdemo.util.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class MainTest {

    @Test
    public void test() {
        System.out.println(DateFormatUtils.parse("2019-09-27 11:36:41", DateFormatUtils.DATE_TIME_PATTERN).getTime());
    }
}
