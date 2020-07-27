package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhourui
 * @Date 2020/4/28 10:50
 */
@Slf4j
public class FileUtil {

    /**
     * 获取不同服务器字体路径
     *
     * @Author zhourui
     */
    public static String getFontPath(){
        String prefixFont = "";
        String os = System.getProperties().getProperty("os.name");
        log.info("getFontPath.os = {}", os);
        if (os.startsWith("win") || os.startsWith("Win")) {
            prefixFont = "C:\\Windows\\Fonts";
        } else {
            prefixFont = "/usr/share/fonts/chinese";
        }
        log.info("getFontPath.prefixFont = {}", prefixFont);
        return prefixFont;
    }
}
