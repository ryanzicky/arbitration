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
@ExcelTarget("importExampleModel")
public class ImportExampleModel {

    @Excel(name = "债务人名称", orderNum = "0")
    private String borrowerName;
    @Excel(name = "主体名称", orderNum = "1")
    private String clientName;
    @Excel(name = "借款日期", orderNum = "2")
    private Date borrowerDate;
    @Excel(name = "提款额", orderNum = "3")
    private BigDecimal borrowerAmount;
    @Excel(name = "分期期数", orderNum = "4")
    private String stages;
    @Excel(name = "逾期天数", orderNum = "5")
    private String overdueDays;
    @Excel(name = "户名", orderNum = "6")
    private String accountName;
    @Excel(name = "开户行", orderNum = "7")
    private String bank;
    @Excel(name = "账号", orderNum = "8")
    private String bankAccount;
    @Excel(name = "助理", orderNum = "9")
    private String assistant;
    @Excel(name = "联系方式", orderNum = "10")
    private String phone1;
    @Excel(name = "律师名称", orderNum = "11")
    private String lawer;
    @Excel(name = "联系电话", orderNum = "12")
    private String phone2;
    @Excel(name = "编号", orderNum = "13")
    private String orderNo;
    @Excel(name = "合同号", orderNum = "14")
    private String contractNum;

}
