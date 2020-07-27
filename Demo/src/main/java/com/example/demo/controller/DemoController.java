package com.example.demo.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.example.demo.dao.Response;
import com.example.demo.dao.request.DemoRequest;
import com.example.demo.dao.request.ImportExampleModel;
import com.example.demo.dao.request.ModelRequest;
import com.example.demo.service.DemoService;
import com.example.demo.util.DateUtil;
import com.example.demo.util.FileUtil;
import com.example.demo.util.ThreadUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @Author zhourui
 * @Date 2019/9/29 14:18
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @Autowired
    private DemoService demoService;

    private static final String templatePath = System.getProperty("user.dir");

    @PostMapping("/generateWord")
    @ApiOperation("生成word文档并返回")
    public void generateWord(HttpServletRequest request, HttpServletResponse response, @RequestBody DemoRequest req) {
        if (StringUtils.isBlank(req.getCompName()) || StringUtils.isBlank(req.getAddress()) || StringUtils.isBlank(req.getContent()) ||
                StringUtils.isBlank(req.getLegalName()) || StringUtils.isBlank(req.getName())) {
            log.error("请求参数不能为空!,{}", JSON.toJSONString(req));
        }
        req.setDate(DateUtil.formatDate("yyyy年MM月dd日", new Date()));

        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            File file = demoService.generateWordFile(req, templatePath);
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
            IOUtils.copy(fis,response.getOutputStream());
            response.flushBuffer();
            fis.close();
        } catch (Exception e) {
            log.error("生成word文档失败!,{}", JSON.toJSONString(req), e);
        }
    }

    @PostMapping("/uploadFile")
    @ApiOperation("上传Excel文件")
    public Response uploadFile(@RequestParam(value = "file") MultipartFile file) {
        Response resp = Response.success();
        if (null == file) {
            log.error("请上传文件!");
            return Response.fail(401L, "请上传文件!");
        }
        try {
            ExecutorService executorService = ThreadUtil.newThreadPool();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        ImportParams params = new ImportParams();
                        List<ImportExampleModel> dataList = ExcelImportUtil.importExcel(file.getInputStream(), ImportExampleModel.class, params);
                        log.info("dataList = {}", JSON.toJSONString(dataList));
                        String fontPath = FileUtil.getFontPath();
                        dataList.forEach(data -> {
                            demoService.handleTtemplateFile(data, fontPath);
                        });
                    } catch (Exception e) {
                        log.error("获取上传文件内容失败!,{}",e);
                    }
                }
            });
        } catch (Exception e) {
            log.error("多线程处理excel文件失败!,{}",e);
            return Response.fail(500L, "多线程处理excel文件失败!");
        }
        return resp;
    }

    @PostMapping("/generateImageByData")
    @ApiOperation("对象生成图片")
    public Response generateImageByData(@RequestBody ModelRequest request) {
        Response resp = Response.success();
        if (null == request || CollectionUtils.isEmpty(request.getList())) {
            log.error("参数不能为空!, {}", request);
            return Response.fail(404L, "参数不能为空!");
        }
        try {
            log.info("request.getList() = {}", JSON.toJSONString(request.getList()));
            String fontPath = FileUtil.getFontPath();
            request.getList().forEach(data -> {
                demoService.handleTtemplateFile(data, fontPath);
            });
        } catch (Exception e) {
            log.error("获取内容失败!,{}", request, e);
            return Response.fail(500L, "获取内容失败!");
        }
        return resp;
    }

    /*@PostMapping("/test1")
    @ApiOperation("测试1")
    public void test1() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            System.out.println("Local HostAddress:"+addr.getHostAddress());
                    String hostname = addr.getHostName();
            System.out.println("Local host name: "+hostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/test2")
    @ApiOperation("测试2")
    public String test2() {
        return System.getProperty("user.dir");
    }*/
}
