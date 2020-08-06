package com.example.demo.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.example.demo.dao.Response;
import com.example.demo.dao.request.ArbitrationModel;
import com.example.demo.dao.request.ContractDisputeModel;
import com.example.demo.service.DemoService;
import com.example.demo.util.ThreadUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @Author zhourui
 * @Date 2020/8/6 9:32
 */
@RestController
@RequestMapping("/contractDispute")
@Slf4j
public class ContractDisputeController {

    @Autowired
    private DemoService demoService;

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
            executorService.execute(() -> {
                try {
                    ImportParams params = new ImportParams();
                    List<ContractDisputeModel> dataList = ExcelImportUtil.importExcel(file.getInputStream(), ContractDisputeModel.class, params);
                    dataList.forEach(data -> {
                        demoService.handleContractDisputeFile(data);
                    });
                    log.info("dataList = {}", JSON.toJSONString(dataList));
                } catch (Exception e) {
                    log.error("获取上传文件内容失败!,{}",e);
                }
            });
        } catch (Exception e) {
            log.error("多线程处理excel文件失败!,{}",e);
            return Response.fail(500L, "多线程处理excel文件失败!");
        }
        return resp;
    }
}
