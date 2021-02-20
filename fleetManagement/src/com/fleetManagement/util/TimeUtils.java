package com.fleetManagement.util;

import java.time.LocalDate;

// 时间工具类

public class TimeUtils {

    public static LocalDate str2Date(String str){
        String[] strs = str.split("-");
        return LocalDate.of(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]), Integer.parseInt(strs[2]));
    }
}
