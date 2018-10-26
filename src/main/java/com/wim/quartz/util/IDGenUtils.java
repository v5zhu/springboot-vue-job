package com.wim.quartz.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class IDGenUtils {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    private static Long MIN_SEQ = 1L;
    private static Long MAX_SEQ = 999999L;

    public static final String timeSeqId() {
        // todo需用redis来替代
        String prefix = format.format(new Date());
        if (MIN_SEQ > MAX_SEQ) {
            MIN_SEQ = 1L;
        }
        String id = prefix + StringUtils.leftPad(String.valueOf(MIN_SEQ++), 6, "0");
        return id;
    }
}
