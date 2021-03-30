package com.demo.jni.jin;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;

/**
 * class summary
 *
 * @author heziqi@cvte
 * @ClassName LibLoader.java
 * @Description code
 * @createTime 2020/03/10 21:03:00
 */
public class LibLoader {
    public static void load(){
        String resourcePath = "/jni/ffmpeg_portal.so";
        String folderName = System.getProperty("java.io.tmpdir") + "/lib/";
        File folder = new File(folderName);
        folder.mkdirs();
        File libFile = new File(folder, "jni/ffmpeg_portal.so");
        if (libFile.exists()) {
            System.load(libFile.getAbsolutePath());
        } else {
            try {
                InputStream in = LibLoader.class.getResourceAsStream(resourcePath);
                FileUtils.copyInputStreamToFile(in, libFile);
                in.close();
                System.load(libFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load required lib", e);
            }
        }
    }
}
