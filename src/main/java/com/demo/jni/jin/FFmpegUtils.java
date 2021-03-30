package com.demo.jni.jin;

import org.springframework.stereotype.Component;

/**
 * class summary
 *
 * @author heziqi@cvte
 * @ClassName FFmpegUtils.java
 * @Description code
 * @createTime 2020/03/15 11:03:00
 */
@Component
public class FFmpegUtils {

    /**
     * 执行FFmpeg命令
     * @param cmd 命令字符串
     * @return 执行结果代码
     */
    public native int run(String[] cmd);

}
