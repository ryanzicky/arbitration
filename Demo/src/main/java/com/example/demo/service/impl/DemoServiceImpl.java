package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.dao.request.*;
import com.example.demo.service.DemoService;
import com.example.demo.util.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @Author zhourui
 * @Date 2019/9/29 15:17
 */
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    private static final String path = System.getProperty("user.dir");

    @Override
    public File generateWordFile(DemoRequest request, String path) {
        File file = DocxUtil.handleWordFile(request, path);
        return file;
    }

    @Override
    public void handleTtemplateFile(ImportExampleModel model, String fontPath) {
        Map<String, String> params = Maps.newHashMap();
        params.put("borrowerName", model.getBorrowerName());
        params.put("clientName", model.getClientName());
        params.put("borrowerAmount", String.valueOf(model.getBorrowerAmount()));
        params.put("stages", model.getStages());
        params.put("overdueDays", model.getOverdueDays());
        params.put("accountName", model.getAccountName());
        params.put("bank", model.getBank());
        params.put("borrowerDate", DateUtil.formatDate("yyyy年MM月dd日",model.getBorrowerDate()));
        params.put("bankAccount", model.getBankAccount());
        params.putAll(DateUtil.getDate(new Date(), Lists.newArrayList("year1","month1","date1")));
        params.put("assistant", model.getAssistant());
        params.put("phone1", model.getPhone1());
        params.put("lawer", model.getLawer());
        params.put("phone2", model.getPhone2());
        params.put("orderNo", model.getOrderNo());

        log.info("params = {}", JSON.toJSONString(params));
        PDFUtil.handleTtemplate(params, path, model.getContractNum() + "_" + model.getBorrowerName(), fontPath);
    }

    @Override
    public void handleSueFile(IndictmentModel model) {
        Map<String, Object> map = CommontUtils.objectToMap(model);
        map.put("liquidatedDamagesDate", DateUtil.getDateFormatStr(DateUtil.getDateAdd(model.getClaimDate())));
        map.put("sueDate", DateUtil.getDateFormatStr(model.getSueDate()));
        map.put("loanDate", DateUtil.getDateFormatStr(model.getLoanDate()));
        map.put("overdueDate", DateUtil.getDateFormatStr(model.getOverdueDate()));
        map.put("claimDate", DateUtil.getDateFormatStr(model.getClaimDate()));
        map.put("insureSudDate", DateUtil.getDateFormatStr(model.getInsureSudDate()));
        map.put("loanSendDate", DateUtil.getDateFormatStr(model.getLoanSendDate()));

        map.put("arrearAmount", model.getArrearAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        map.put("suspenseAmount", model.getSuspenseAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        map.put("monthReturnAmount", model.getMonthReturnAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        map.put("insureAmount", model.getInsureAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        map.put("loanAmount", model.getLoanAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        map.put("liquidatedDamagesAmount", model.getLiquidatedDamagesAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        map.put("claimAmount", model.getClaimAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        DocxUtil.makeSueWordFile(map,  path);
    }

    @Override
    public void handleArbrationFile(ArbitrationModel model) {
        Map<String, Object> map = CommontUtils.objectToMap(model);
        map.put("claimDate", DateUtil.getDateFormatStr(model.getClaimDate()));
        map.put("applyDate", DateUtil.getDateFormatStr(model.getApplyDate()));
        map.put("borrowerDate", DateUtil.getDateFormatStr(model.getBorrowerDate()));
        map.put("contractSignDate", DateUtil.getDateFormatStr(model.getContractSignDate()));
        map.put("loanEndDate", DateUtil.getDateFormatStr(DateUtil.getDateAddByTerm(model.getBorrowerDate(), model.getLoanTerm())));
        map.put("issueDate", DateUtil.getDateFormatStr(model.getIssueDate()));

        map.put("offerRate", BigDecimalUtil.getPercent().format(model.getOfferRate()));
        map.put("loanYearRate", BigDecimalUtil.getPercent().format(model.getLoanYearRate()));
        map.put("interest", model.getInterest().setScale(2, BigDecimal.ROUND_DOWN));
        map.put("totalAmount", model.getTotalAmount().setScale(2, BigDecimal.ROUND_DOWN));
        DocxUtil.makeArbrationFile(map, path);
    }

    @Override
    public void handleContractDisputeFile(ContractDisputeModel model) {
        Map<String, Object> map = CommontUtils.objectToMap(model);
        map.put("contractDate", DateUtil.getDateFormatStr(model.getContractDate()));
        map.put("overdueDate", DateUtil.getDateFormatStr(model.getOverdueDate()));
        map.put("guaranteeContractDate", DateUtil.getDateFormatStr(model.getGuaranteeContractDate()));
        map.put("payoutTime", DateUtil.getDateFormatStr(model.getPayoutTime()));
//        map.put("loanRate", BigDecimalUtil.getPercent().format(model.getLoanRate()));
        map.put("date", DateUtil.getDateFormatStr(new Date()));

        DocxUtil.makeContractDisputeFile(map, path);
    }
}
