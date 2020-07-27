package com.example.demo.dao.request;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zhourui
 * @Date 2019/10/3 20:47
 */
@Data
@ExcelTarget("importExampleModel1")
public class ImportExampleModel1 {

    @Excel(name = "借款人名称", orderNum = "0")
    private String borrowerName;
    @Excel(name = "身份证号", orderNum = "1")
    private String idCardNo;
    @Excel(name = "委托方名称", orderNum = "2")
    private String clientName;
    @Excel(name = "委托方联系人", orderNum = "3")
    private String clientContact;
    @Excel(name = "联系电话", orderNum = "4")
    private String phone;
    @Excel(name = "撮合平台名称", orderNum = "5")
    private String matchPlatform;
    @Excel(name = "截止日期", orderNum = "6")
    private Date endDate;
    @Excel(name = "欠款总额", orderNum = "7")
    private BigDecimal residuePrincipal;
    @Excel(name = "律师事务所名称", orderNum = "8")
    private String lawOffice;
    @Excel(name = "律师名称", orderNum = "9")
    private String lawer;
    @Excel(name = "编号", orderNum = "10")
    private String orderNo;
    @Excel(name = "律所联系电话", orderNum = "11")
    private String lawOfficePhone;
    @Excel(name = "合同编号", orderNum = "12")
    private String contatcNo;
    @Excel(name = "页眉", orderNum = "13")
    private String header;

}
