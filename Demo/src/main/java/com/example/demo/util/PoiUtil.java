package com.example.demo.util;

import com.example.demo.dao.request.DemoRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * poi工具类
 *
 * @Author zhourui
 * @Date 2019/9/29 15:18
 */
@Slf4j
public class PoiUtil {

    public static void main(String[] args) {
        String path = "templates/template1222222.docx";
        DemoRequest request = DemoRequest.builder().address("111")
                .compName("222")
                .content("dqweqweq")
                .legalName("333")
                .name("4444")
                .date("2019年9月29日")
                .build();
        File file = DocxUtil.handleWordFile(request, path);
    }

    /**
     * 读取模版文件内容
     *
     * @Author zhourui
     */
    private static String readWordTemplate(String path) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        String text = "";
        try (InputStream in = classPathResource.getInputStream()) {
            //解析docx模板并获取document对象
            XWPFDocument doc = new XWPFDocument(in);
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            text = extractor.getText();
            log.info("readWordTemplate.text = {}", text);
        } catch (IOException e) {
            log.error("读取模版文件失败!,{}", e);
            throw new RuntimeException();
        }
        return text;
    }

}
