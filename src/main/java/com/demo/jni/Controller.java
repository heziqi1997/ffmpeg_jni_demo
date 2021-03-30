package com.demo.jni;

import com.demo.jni.jin.FFmpegUtils;
import com.demo.jni.jin.LibLoader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 测试Jni
 *
 * @author heziqi@cvte
 * @ClassName Controller.java
 * @Description code
 * @createTime 2020/03/10 19:25:00
 */
@RequestMapping("/jni")
@RestController
@Slf4j
public class Controller {

    @Autowired
    private FFmpegUtils fFmpegUtils;

    static {
        LibLoader.load();
    }

    @GetMapping("/cmd")
    public String add(@RequestParam("num") int num) {
        String c = "ffmpeg -i /Users/heziqi/Public/springBootTest/" +
                num +
                "M.mp4 -c:v wmv1 -c:a flac /Users/heziqi/Public/springBootTest/" +
                num +
                "M_wmv1_flac_" +
                RandomUtils.nextInt(0, 10000) +
                RandomUtils.nextInt(0, 10000) +
                ".avi";
        String[] strings = c.split(" ");
        long start = System.currentTimeMillis();
        fFmpegUtils.run(strings);
        long cost = System.currentTimeMillis() - start;
        log.info("命令：{}  耗时：{}ms", c, cost);
        return "d";
    }

    @GetMapping("/cmdProcess")
    public String test(@RequestParam("num") int num) {
        List<Long> costList = new ArrayList<>();
        for (int j = 0; j < 1; j++) {
            String c = "ffmpeg -i /Users/heziqi/Public/springBootTest/" +
                    num +
                    "M.mp4 -c:v wmv1 -c:a flac /Users/heziqi/Public/springBootTest/" +
                    num +
                    "M_wmv1_flac_" +
                    RandomUtils.nextInt(0, 10000) +
                    RandomUtils.nextInt(0, 10000) +
                    ".avi";
            long start = System.currentTimeMillis();
            Process p;
            try {
                p = Runtime.getRuntime().exec(c);
                p.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
            long cost = System.currentTimeMillis() - start;
            log.info("命令：{}  耗时：{}ms", c, cost);

            costList.add(cost);
        }
        log.info("转换" + num + "M视频平均消费时间：{}ms  各时间： {}", costList.stream().mapToLong(it -> it).average().getAsDouble(), costList.toString());
        return "d";
    }

}
